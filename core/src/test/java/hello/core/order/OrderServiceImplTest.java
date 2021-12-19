package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderServiceImplTest {

    @Test
    void createOrder() {
        // 순수한 자바 코드 테스트 작성, 생성자와 setter 주입 차이점, 생성자를 권장.
        MemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new RateDiscountPolicy()); // setter 주입을 사용하면 컴파일 에러빨간줄이 나오지 않음.
        Order order = orderService.createOrder(1L, "itemA", 10000);
        assertThat(order.getItemName()).isEqualTo("itemA");
        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
