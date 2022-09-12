package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    @Autowired // 1.생성자 주입. 딱 한번만 호출되는 것이 보장됨.
    // 생성자가 하나 있으면 기본으로 Autowired 를 부여한다.
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
        this.memberRepository = memberRepository;
    }

//    @Autowired(required = false) // 2. setter 주입. 변경 가능한 경우
//    public void setMemberRepository(MemberRepository memberRepository){
//        this.memberRepository = memberRepository;
//    }
    // 3. 필드 주입 => 코드가 간결하긴 하지만, DI 컨테이너에 너무 의존해서 외부에서 내가 주입할 수 없다.
    // 테스트 작성을 위해 setter 를 추가로 만들어주어야 하는데, setter 주입이 더 낫다.
    // springBootTest 통합 테스트 같은 경우에만 사용된다.

    // 4. 메서드 주입 => 일반 메서드에 autowired 붙이는 것. setter 와 같은 의미. 1,2에서 대부분 해결되기 때문에 사용되지 않는다.


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
