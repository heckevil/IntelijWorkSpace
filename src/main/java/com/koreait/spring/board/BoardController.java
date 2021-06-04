package com.koreait.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller /* handler mapping */
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService service;

    @RequestMapping("/list")
    public String list(Model model){
        /* model = request */
      List<BoardDomain> list = service.selboardList();
        model.addAttribute("list",list);
        return "board/list";
    }
    @RequestMapping("/detail")
    public String detail(BoardDTO param,Model model){
        System.out.println("iboard : " +param.getIboard() );
        BoardDomain data =service.selBoard(param);
        model.addAttribute("data",data);
        return "board/detail";
    }

    @ResponseBody
    /* 이하 내용을 json 파일로 만들어어 전하는 목적의 Annotation
     JSP파일로 여는목적에서 json파일로 만드는 목적으로 바꿔준다.
      jsp로 안열린다는 소리 */
    @RequestMapping(value = "/cmtInsSel",method = RequestMethod.POST)
    public Map<String,Integer> cmtInsSel(@RequestBody boardCmtEntity param){
        System.out.println("param : " + param);
        Map<String, Integer> data = new HashMap();
        /* 키값은 String, Value 값은 int값으로
          List<String> list = new ArrayList 랑 비슷
          List는 순서가 중요하는데 map은 키와 밸류로만 이루어진 배열
           순서라는 개념이 없다 => foreach문 불가 */
        data.put("result", 1);
        return data;
    }
}
