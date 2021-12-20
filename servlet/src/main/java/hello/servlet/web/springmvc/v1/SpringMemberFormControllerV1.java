package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// (1) 스프링이 자동으로 빈으로 등록, 내부에 컴포넌트 어노테이션 붙어있음. & (2) 스프링MVC 에서 애노테이현 기반 컨트롤러로 인식
// RequestMappingHandlerMapping 은 스프링 빈 중에서 @RequestMapping, @Controller 가 클래스 레벨에 붙어있는 경우에 매핑정보로 인식
@Controller
//@Component
//@RequestMapping
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")  // 요청 정보를 매핑
    public ModelAndView process() {
        return new ModelAndView("new-form");  // 뷰를 반환
    }
}
