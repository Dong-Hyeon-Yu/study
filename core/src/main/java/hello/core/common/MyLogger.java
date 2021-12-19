package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
// 가짜 프록시 객체 주입. 이 클래스를 상속받아서 생김.
// 메소드 호출시 가짜 메소드 호출 -> 그 안에서 진짜 빈 찾음
// 마치 싱글톤을 사용하는 착각 => 주의해서 사용해야 함. 테스트하기 어려움. 최소화할 것.
// 프록시를 사용하면 클라이언트 코드에는 변경이 안생김. 굳굳@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "] [" + requestURL + "] " + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}

