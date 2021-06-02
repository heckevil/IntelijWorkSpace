package com.koreait.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/* Controller는 맵핑 담담 Service는 로직 담담.*/

@Controller /* Bean 등록  as Controll 등록하는 순간 Servlet 으로 연결*/
@RequestMapping("/user") /* 클래스 위에 맵핑 1차 주소값 */
public class UserController {

    @Autowired
    private UserService service;
    /* service 주소값 가져오기.*/

    @RequestMapping("/login") /* 매소드위에 맵핑 2차 주소값 뒤에 get,post방식 적어줘야함 안적는건 default 가 get방식*/
    public String login() {
        return "user/login";
        /* 리턴 값을 받아서 DispatcherServlet이 forward를 해줌
         *  언제? mapping 한대로 /user/login가 들어오면 경로+리턴값+.jsp 완성한다*/
    }

    @RequestMapping("/join")
    public String join() {
        return "user/join";
    }

    @RequestMapping(value="/join", method=RequestMethod.POST)
    public String join(UserEntity param) {
        System.out.println("uid" + param);
        service.join(param);
        return "redirect:/user/login";
        /* redirect = senRedirect 랑 같다 */
    }
}
