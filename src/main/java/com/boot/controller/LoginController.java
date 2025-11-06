package com.boot.controller;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.boot.dto.LoginDTO;
import com.boot.service.LoginService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
    
    @Autowired
    private LoginService loginService;
	
    @PostMapping("/loginYn")
    public String loginYn(@RequestParam("accountId") String accountId,
                          @RequestParam("password") String password,
                          @RequestParam(value = "saveId", required = false) String saveId,
                          HttpServletRequest request,
                          HttpServletResponse response,
                          RedirectAttributes redirectAttributes) {
        log.info("@# accountId => {}", accountId);
        log.info("@# password => {}", password);
        
        HttpSession session = request.getSession();
        
        // 세션 기반 로그인 실패 제한
        Integer failCount = (Integer) session.getAttribute("loginFailCount");
        Long lockTime = (Long) session.getAttribute("lockTime");
        if (failCount == null) failCount = 0;

        // 잠금 상태 확인
        if (lockTime != null) {
            long diff = (System.currentTimeMillis() - lockTime) / 1000; // 초 단위 계산
            if (diff < 30) {
                long remain = 30 - diff;
                redirectAttributes.addFlashAttribute("loginFailMsg", "로그인 잠금 상태입니다. " + remain + "초 후 다시 시도하세요.");
                return "redirect:/login";
            } else {
                // 30초 지나면 잠금 해제
                session.removeAttribute("lockTime");
                session.setAttribute("loginFailCount", 0);
                failCount = 0;
            }
        }         

        HashMap<String, String> param = new HashMap<>();
        param.put("accountId", accountId);
        param.put("password", password);

        ArrayList<LoginDTO> dtos = loginService.loginYn(param);

        
        //로그인 확인 로직            
        if (dtos == null || dtos.isEmpty()) {
//        	아이디 없을 시 실패 카운트 추가
        	failCount++;
            session.setAttribute("loginFailCount", failCount);

            if (failCount >= 5) {
                session.setAttribute("lockTime", System.currentTimeMillis());
                redirectAttributes.addFlashAttribute("loginFailMsg", "5회 이상 실패로 30초간 잠금되었습니다.");
            } else {
                redirectAttributes.addFlashAttribute("loginFailMsg", "아이디 또는 비밀번호가 틀렸습니다. (" + failCount + "/5)");
            }
        	
        	redirectAttributes.addFlashAttribute("loginFail", true);
            return "redirect:/login";
        } else {
            // 로그인 정보가 있을 때
            LoginDTO dto = dtos.get(0);

            if (password.equals(dto.getPassword())) { // 비밀번호 일치 확인
                String role = dto.getAccountRole(); // DB에서 불러온 권한(USER/ADMIN)
                
                log.info("@@@ DB password => {}", dto.getPassword());
                log.info("@@@ 입력 password => {}", password);
                log.info("@@@ DB role => {}", dto.getAccountRole());
                                  
                // 아이디 저장 체크박스 처리
                if (saveId != null) { // 체크되어 있음
                    Cookie cookie = new Cookie("savedId", accountId);
                    cookie.setMaxAge(60 * 60 * 24 * 7); // 7일 유지
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    log.info("@@@ 아이디 저장 쿠키 생성");
                } else { // 체크 해제
                    Cookie cookie = new Cookie("savedId", null);
                    cookie.setMaxAge(0); // 즉시 삭제
                    cookie.setPath("/");
                    response.addCookie(cookie);
                    log.info("@@@ 아이디 저장 쿠키 삭제");
                }
                
                if ("USER".equals(role)) {
                    session.setAttribute("ROLE", "USER");//로그인 성공 시 유저 세션추가                     
                                           
                    session.removeAttribute("loginFailCount");
                    session.removeAttribute("lockTime");
                    
                    return "redirect:/main";
                } else if ("ADMIN".equals(role)) {
                    session.setAttribute("ROLE", "ADMIN");//로그인 성공 시 관리자 세션 추가
                    
                    session.removeAttribute("loginFailCount");
                    session.removeAttribute("lockTime");
                    
                    return "redirect:/main";
                } else {
                    return "redirect:/login";
                }

            } else {
            	
            	redirectAttributes.addFlashAttribute("loginFail", true);
                // 비밀번호 불일치
            	
            	   failCount++;
                   session.setAttribute("loginFailCount", failCount);

                   if (failCount >= 5) {
                       session.setAttribute("lockTime", System.currentTimeMillis());
                       redirectAttributes.addFlashAttribute("loginFailMsg", "5회 이상 실패로 30초간 잠금되었습니다.");
                   } else {
                       redirectAttributes.addFlashAttribute("loginFailMsg", "비밀번호가 틀렸습니다. (" + failCount + "/5)");
                   }
            	
            	
                return "redirect:/login";
            }
        }//end) 로그인 정보 존재 시
        
    }
 // 로그아웃
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        log.info("로그아웃 완료");
        return "redirect:/login";
    }
}
