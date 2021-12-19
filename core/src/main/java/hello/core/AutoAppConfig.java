package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan( // 다른 예제 코드 겹쳐지지 않기 위해
        basePackages = {"hello.core.member", "hello.core.order", "hello.core.discount"}, // 탐색할 패키지 지정, default 는 첫 줄에 있는 패키지.
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

    // 수동 등록 빈과 자동 등록 빈이 중복될 때는 수동 우선.
    // => @SpringBootApplication 을 통해서 실행시킬때는 오류가 나게 만들었음.
    // application.property 에 중복등록 옵션 추가할수도 있지만 권장하지 않음.
//    @Bean(name = "memoryMemberRepository")
//    public MemberRepository memberRepository(){
//        return new MemoryMemberRepository();
//    }
}
// @ComponentScan 이 붙으면 @Component 가 붙은 모든 클래스를 @bean 으로 자동 등록해준다.
// AppConfig와 다르게 생성자에 수동으로 주입할 수 없기 때문에, 생성자에 @autowired 를 붙여주면
// 등록된 빈 중에서 타입이 같은 것들을 주입해 줌.

// 자동 등록 빈 이름이 충돌되는 경우는 오류 발생
//