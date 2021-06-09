package com.koreait.spring.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller /* handler mapping */
@RequestMapping("/board")
public class BoardController {
    @Autowired
    private BoardService service;

    @RequestMapping("/list")
    public String list(Model model) {
        /* model = request */
        List<BoardDomain> list = service.selboardList();
        model.addAttribute("list", list);
        return "board/list";
    }

    @RequestMapping("/detail")
    public String detail(BoardDTO param, Model model) {
        System.out.println("iboard : " + param.getIboard());
        BoardDomain data = service.selBoard(param);
        model.addAttribute("data", data);
        return "board/detail";
    }

    @GetMapping("/writeMod")
    public void writeMod(){}

    @PostMapping("writeMod")
    public String writeMod(BoardCmtEntity param){
        int iboard= service.writeMod(param);
        return "redirect:detail?iboard = "+iboard;
    }


    /* 이하 내용을 json 파일로 만들어어 전하는 목적의 Annotation
   JSP파일로 여는목적에서 json파일로 만드는 목적으로 바꿔준다.
    jsp로 안열린다는 소리 */
    @ResponseBody
    @RequestMapping(value = "/cmt", method = RequestMethod.POST)
    public Map<String, Integer> cmtInsSel(@RequestBody BoardCmtEntity param) {
        int result = service.insBoardCmt(param);
        Map<String, Integer> data = new HashMap();
        /* 키값은 String, Value 값은 int값으로
          List<String> list = new ArrayList 랑 비슷
          List는 순서가 중요하는데 map은 키와 밸류로만 이루어진 배열
           순서라는 개념이 없다 => foreach문 불가 */
        data.put("result", result);
        System.out.println("insSel data : "+data);
        System.out.println(" ");
        return data;
    }

    @ResponseBody
    @RequestMapping(value = "/cmt/{iboard}")/* pass 배리어블*/
    public List<BoardCmtDomain> cmtSel(@PathVariable("iboard") int iboard){
        /* @PassVariable > /cmt/{iboard}로 들어온 iboard값을 받고 싶다
         들오오운 값({iboard})을  @PathVariable("iboard")/변수명이 일치하면 없어도됨 다르면 필요/ int iboard(변수명 일치)로 받겠다.
         EX) /cmt/{icmt} @PathVariable("icmt") int aaa*/
        BoardCmtEntity param = new BoardCmtEntity();
        param.setIboard(iboard);
        System.out.println(param);
        System.out.println("Cmt : " + param.getCmt());
        System.out.println(" ");
        return service.selBoardCmtList(param);
    }

    @ResponseBody
    @RequestMapping(value = "/cmt/{icmt}", method = RequestMethod.DELETE)
    public Map<String, Integer> cmtDel(@PathVariable int icmt){
        /* json 형태로 받으려면 MAP을 써주자 */
        BoardCmtEntity param = new BoardCmtEntity();
        param.setIcmt(icmt);

        Map<String, Integer> data = new HashMap<>();
        int result = service.delBoardCmt(param);
        System.out.println("del data1 : " + data);
        data.put("result", result);
        System.out.println("del data2 : " + data);
        System.out.println(" ");

        return data;
    }
//@ResponseBody
//@RequestMapping(value = "/cmt/{icmt}", method = RequestMethod.DELETE)
//public int cmtDel(@PathVariable int icmt){
//    /* json 형태로 받으려면 MAP을 써주자 */
//    BoardCmtEntity param = new BoardCmtEntity();
//    param.setIcmt(icmt);
//
//    Map<String, Integer> data = new HashMap<>();
//    int result = service.delBoardCmt(param);
//    data.put("result", result);
//
//    System.out.println("data : " + data);
//
//    return result;
//}

    @ResponseBody
    @RequestMapping(value = "/cmt",method = RequestMethod.PUT)
    public Map<String, Integer>cmtUpd(@RequestBody BoardCmtEntity param){
       int result = service.updBoardCmt(param);
        Map<String , Integer> data = new HashMap<>();
        data.put("result",result);
        return data;
    }



}
