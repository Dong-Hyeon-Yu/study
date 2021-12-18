package hello.hellospring;

import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    /**
     * DI : 생성자 주입, 필드 주입(불편), setter 주입(런타입에 잘못 바뀔 수 있다.)
     * 정형화된 컨트롤러, 서비스, 리포지토리는 컴포넌트 스캔 사용
     * 상황에 따라 구현 클래스를 달리 해야하면 빈즈를 직접 등록(기존 코드에 손대지 않고 바꿔치기 가능)
     * autowired 는 스프링 컨테이너가 직접 관리하는 객체에서만 작동함.
     */

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //    @PersistenceContext
//    private EntityManager em;
//    @Autowired
//    public SpringConfig(EntityManager em){
//        this.em = em;
//    }

//    private DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    @Bean
//    public MemberRepository memberRepository() {
//        //return new MemoryMemberRepository();
//        //return new JdbcMemberRepository(dataSource);
//        //return new JdbcTemplateMemberRepository(dataSource);
//        //return new JPAMemberRepository(em);
//    }
}
