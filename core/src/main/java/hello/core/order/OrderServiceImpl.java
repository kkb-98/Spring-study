package hello.core.order;
import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;


import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor    //생성자 자동 생성해주는 lombok 어노테이션  + 의존관계가 추가될때 아주 편리함
public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository;

    //밑의 코드로 인해 DIP , OCP 위반됨.
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    //따라서 밑의 코드로 인터페이스에만 의존하도록 바꿔줌.
    private final DiscountPolicy discountPolicy;
    // 그런데 위와 같이 수정을 하면 코드가 실행이 되지않는다. 구현체가 따로 정해지지 않아서이다.


    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository,@MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice  = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
