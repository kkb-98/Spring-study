package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPolicy = 10;

    //밑의 코드를 테스트 하려면 ctrl + shift + T를 눌러 test해보자.
    @Override
    public int discount(Member member, int price) {
        if (member.getGrade() == Grade.VIP) {
            return price * discountPolicy / 100;
        }else{
            return 0;
        }
    }
}
