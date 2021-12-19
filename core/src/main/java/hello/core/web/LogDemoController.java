package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {
    private final LogDemoService logDemoService;
    // request scope 는 요청이 발생하는 시점에 생성되어야 하는데, (일종의 prototype scope 인듯)
    // 컨테이너 생성할 때 의존관계 주입하려고 보면 request 에 할당된 쓰레드가 없어서 오류!
    // Provider 를 사용하면 그때그때 필요한 빈을 생성해서 주입.
    // 같은 http 요청이면 controller, service 에서 따로 getObject 해도 같은 빈이 반환됨!!
    //private final ObjectProvider<MyLogger> myLoggerProvider;
    private final MyLogger myLogger;

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        //MyLogger myLogger = myLoggerProvider.getObject(); // 이시점에 생성!
        String requestURL = request.getRequestURL().toString();

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller log");
        logDemoService.logic("testId");
        return "OK";
    }
}
