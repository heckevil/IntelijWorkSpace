package com.koreait.spring.user;

import org.apache.commons.io.FilenameUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserMapper mapper;

    @Autowired
    private HttpSession session;

    public String login(UserEntity param) {
        UserEntity result = mapper.selUser(param);
        if (result == null) { //아이디가 없음.
            return "/user/login?err=1";
        } else if (BCrypt.checkpw(param.getUpw(), result.getUpw())) {
            //비밀번호가 맞음..
            result.setUpw(null);
            session.setAttribute("loginUser",result);
            //세션처리
            return "/board/list";
        } else {//비밀번호 틀림
            return "/user/login?err=2";
        }
    }



    public int join(UserEntity param) {
        String cryptPw = BCrypt.hashpw(param.getUpw(), BCrypt.gensalt());
        param.setUpw(cryptPw);
        return mapper.insUser(param);
    }
    public String uploadProfile(MultipartFile img){
        UserEntity loginUser = (UserEntity) session.getAttribute("loginUser");
        final String PATH = "D:/springImg/"+ loginUser.getIuser();
        /* spring 폴더안 pk 폴더*/


        File folder = new File(PATH);
        folder.mkdirs();
        /* Spring 폴더는 있으니 PK 값 폴더 생성 */


        String ext = FilenameUtils.getExtension(img.getOriginalFilename());
       /* 확장자명 구하는 매소드 */
        String fileNm = UUID.randomUUID().toString()+"."+ext;
        /* 파일 이름 랜덤 생성 */

        File target = new File(PATH+"/"+ fileNm);
        /* 실제 파일 생성 */
        try {
            img.transferTo(target);
//            파라미터에 입력된 img 파일 target경로 파일에 넣어줌

            //이전 이미지 삭제
            File deFile = new File(PATH+"/"+loginUser.getProfileImg());
            if(deFile.exists()){
                deFile.delete();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        UserEntity param = new UserEntity();
        param.setIuser(loginUser.getIuser());
        param.setProfileImg(fileNm);

        mapper.updUser(param);

        loginUser.setProfileImg(fileNm);
        /* 세션에 꼭넣어 줘야함 jsp에 sessiomscope로 탐색 */
        return "/user/profile";
    }


}
