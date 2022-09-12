package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class); // 빈은 객체 생성 -> 의존관계 주입 -> 초기화 콜백함수 사용 -> 사용 -> 소멸전 콜백 -> 스프링 종료
        ac.close();
    }

    @Configuration
    static class LifeCycleConfig {
        // 2. @Bean(initMethod = "init", destroyMethod = "close")
        // 메서드 이름을 자유롭게 지정 가능.
        // 스프링 빈이 스프링 코드에 의존하지 않음. 클라이언트 코드에 영향이 없다.
        // 클라이언트 코드가 아니라 구성정보를 사용하기 때문에 코드를 고칠 수 없는 외부 라이브러리에도 초기화와 종료 메서드를 적용할 수 있다.
        // destroyMethod = "(inferred)" 가 기본값. close, shutdown이라는 이름의 메서드를 자동으로 호출함.
        // destroyMethod = "" 이면 사용하지 않음.

        // 3. 초기화 메서드에 @PostConstruct, 종료 메서드에 @PreDestroy 를 붙이면 쉽다. 최신 스프링에서 권장하는 방식.
        // 자바 표준으로 스프링이 아닌 다른 컨테이너에서도 작동함.
        // 유일한 단점은 외부 라이브러리에는 적용하지 못한다는 점. => @Bean 을 사용하자
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient(); // url이 초기화되지 않고 connection 호출
            networkClient.setUrl("http://hello-spring.dev");  // 생성자에서는 개체 생성을 위한 작업만 하고, 초기화 부분은 명확하게 분리하는 것이 좋다.
            return networkClient;
        }
    }
}
