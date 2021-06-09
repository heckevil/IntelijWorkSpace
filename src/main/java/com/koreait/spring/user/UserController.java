package com.koreait.spring.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


/* Controller는 맵핑 담담 Service는 로직 담담.*/

@Controller /* Bean 등록  as Controll 등록하는 순간 Servlet 으로 연결*/
@RequestMapping("/user") /* 클래스 위에 맵핑 1차 주소값 */
public class UserController {

    @Autowired
    private UserService service;
    /* service 주소값 가져오기.*/

    @RequestMapping("/login") /* 매소드위에 맵핑 2차 주소값 뒤에 get,post방식 적어줘야함 안적는건 default 가 get방식*/
    public String login(@RequestParam(value = "err", required = false, defaultValue = "0") int err, Model model) {
       /* @RequestParam("보내는 명" int 변수명) int까지 붙으면 Parse까지 해줌
        단, @RequestParam를 꼭보내줘야 하는 강제성이 있다. + 문자열도 껴있으면 파싱조차 안댐
        그럴때 RequestParam에게 강제성을 빼야한다. 빼는법 : required = false
        Tip: defaultValue=0을 쓰면 int일때 null들어오면 0으로 바꿀수 있다.*/

        /* err=0 일때 는 에러가 없다는 뜻이다. err는 1,2만 들어갔으니 0일땐 에러가없다! */
        System.out.println("err : "+err);

        switch (err){
            case 1: //아이디없음
                model.addAttribute("errMsg","아이디를 확인해 주세요.");
                /* request.setAttribute("",)랑 같은 것 */
                break;
            case 2: //비밀번호 틀림
                model.addAttribute("errMsg","비밀번호를 확인해 주세요.");
                break;
        }
        return "user/login";
        /* 리턴 값을 받아서 DispatcherServlet이 forward를 해줌
         *  언제? mapping 한대로 /user/login가 들어오면 경로+리턴값+.jsp 완성한다*/
    }
    @RequestMapping("/logout")
    public String logout(HttpSession hs, HttpServletRequest req){
        hs.invalidate();
        String referer = req.getHeader("Referer");
//        이전에 방문했던 주소 String 으로 정의 req.getHeader("Referer") < 이전 방문 주소
        return "redirect:"+referer;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST) /* 매소드위에 맵핑 2차 주소값 뒤에 get,post방식 적어줘야함 안적는건 default 가 get방식*/
    public String login(UserEntity param) {
        service.login(param);
        return "redirect:" + service.login(param);
        /* post방식은 무조건 이동하는게 아니기 때문에 redirect 필수*/
    }


    @RequestMapping("/join")
    public String join() {
        return "user/join";
    }

    @RequestMapping(value = "/join", method = RequestMethod.POST)
    public String join(UserEntity param) {
        System.out.println("uid" + param);
        service.join(param);
        return "redirect:/user/login";
        /* redirect = senRedirect 랑 같다 */
    }

    @RequestMapping("/profile")
    public String profile(){
        return "user/profile";
    }
    //@RequestMapping(value = "/profile",method = RequestMethod.POST)


    @PostMapping("/profile")
    public String profile (MultipartFile profileImg){
//        파일한개 : MultipartFile, 파일 여러개 MultiPartfile[]
        return "redirect:"+service.uploadProfile(profileImg);
    }
}
