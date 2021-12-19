package hello.core.lifecycle;


import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

// InitializingBean, DisposableBean 는 spring에 지나치게 의존적인 성격이 있어서 지금은 사용하지 않음.
public class NetworkClient {

    private  String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메세지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call + " + url + "message = " + message);
    }

    // 서비스 종료시 호출
    public  void disconnect() {
        System.out.println("close : " + url);
    }

    @PostConstruct
    public void init() {
        connect();
        call("초기화 연결 메세지");
    }

    @PreDestroy
    public void close(){
        disconnect();
    }
}
