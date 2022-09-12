package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 구현 객체를 여기서 생성 (생성자 주)
// 역할과 구현이 한눈에 보이게 구성하기.
// IoC 제어의 역전, 프로그램의 제어권을 여기서 갖는다.
// DI 의존관계 주입. 정적인 클래스의 의존관계 vs 동적인 객체의 의존관계,
// 클래스의 의존관계를 바꾸지 않으면서 객체 의존관계를 동적으로 결정할 수 있다.
// AppConfig 는 DI container 역할

// ApplicationContext 는 스프링 컨테이너. 인터페이스임. 구현체로는 AnnotationConfigApplicationContext() 등
// 최상위 컨테이너는 BeanFactory. 직접 만질 일은 없음.
// @configuration 을 구성정보로 사용
// @Bean 이 붙은 메소드를 모두 호출. 필요한 것은 메소드 이름으로 컨테이너에서 꺼내옴.
// (1) 빈을 생성 (2) 의존관계 주입 => 싱글톤 컨테이너
@Configuration
public class AppConfig {

    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.memberRepository
    // call AppConfig.orderService
    // call AppConfig.memberRepository
    // ..?

    // call AppConfig.memberService
    // call AppConfig.memberRepository
    // call AppConfig.orderService

    @Bean
    public MemberService memberService() {
        //System.out.println("call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        //System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        //System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
