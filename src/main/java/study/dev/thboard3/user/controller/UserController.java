package study.dev.thboard3.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import study.dev.thboard3.user.model.UserVo;
import study.dev.thboard3.user.service.UserService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 로그인 폼
     * @param userVo
     * @return
     */
    @GetMapping("/login")
    public ModelAndView loginForm(UserVo userVo) {
        ModelAndView mv = new ModelAndView("pages/login");
        return mv;
    }
    //주석테스트
    /**
     * 로그인 처리
     * @param session
     * @param br
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(@ModelAttribute @Valid UserVo userVo, BindingResult br, HttpSession session) throws Exception {
        String redirectUrl = "";
        UserVo findUser = (UserVo) userService.loginProc(userVo, br);
        session.setAttribute("userVo", findUser);
        return redirectUrl = (findUser != null) ? "redirect:/" : "redirect:/user/login";
    }

    /**
     * 로그아웃 처리
     * @param session
     * @return
     */
    @PostMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/user/login";
    }

    /**
     * 회원가입 폼
     * @param userVo
     * @return
     */
    @GetMapping("/reg")
    public String regForm(UserVo userVo) {
        return "pages/user/register";
    }
}
