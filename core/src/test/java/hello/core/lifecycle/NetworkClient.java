package hello.core.lifecycle;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자를 호출, url = " + url);

    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect: " + url);
    }

    public  void call(String message){
        System.out.println("call = " + url + " message = " + message );
    }

    //서비스 종료시 호출
    public void disonnect(){
        System.out.println("close = " + url);
    }

    //인터페이스 초기화, 종료방법은 요즘 거의 사용하지 않는 초창기 방법이다.

    //밑은 어노테이션 방법 스프링에서 권장하는 간단한 방법임.
    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disonnect();
    }
}
