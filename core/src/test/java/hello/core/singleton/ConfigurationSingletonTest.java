package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository memberRepository1 = memberService.getMemberRepository();
        MemberRepository memberRepository2 = orderService.getMemberRepository();

        System.out.println("memberService -> memberRepository = " + memberRepository1);
        System.out.println("orderService -> memberRepository = " + memberRepository2);
        System.out.println("memberRepository = " + memberRepository);

        Assertions.assertThat(memberService.getMemberRepository()).isSameAs(memberRepository1);
        Assertions.assertThat(orderService.getMemberRepository()).isSameAs(memberRepository2);

    }

    @Test
    void configurationDeep() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        // 스프링이 CGLIB 라는 바이트코드 조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고,
        // 그 클래스를 스프링 빈으로 등록! AppConfig$$EnhancerBySpringCGLIB$$xxxxxx
        // AppConfig 에서 @Configuration 을 붙이면 위의 작업을 해줌.
        // 붙이지 않으면 중복 빈 등록되어버리는 듯한 모양이지만, 생성자 내부에서 new 한 것은 스프링 컨테이너가 관리하는 스프링 빈이 아니다.
        // @Configuration 을 사용하지 않으면서 싱글톤을 보장하려면, new 를 사용하지 않고 @Autowired 사용한 객체를 생성자에서 사용
        System.out.println("bean = " + bean.getClass());
    }
}
