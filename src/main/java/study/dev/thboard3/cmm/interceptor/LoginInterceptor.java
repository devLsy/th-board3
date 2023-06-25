package study.dev.thboard3.cmm.interceptor;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import study.dev.thboard3.user.model.UserVo;
import study.dev.thboard3.user.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequiredArgsConstructor
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //요청 URI
        String requestURI = request.getRequestURI();
        log.info("interceptor_requestURI = [{}]", requestURI);
        //세션에서 가져온 사용자 정보
        UserVo userVo = (UserVo) request.getSession().getAttribute("userVo");

        if(userVo == null) {
            log.info("미인증 사용자입니다. 로그인 페이지로 이동합니다.");
            response.sendRedirect("/user/login");
            return false;
        }
        log.info("인증되었습니다.");
        log.info("storedSessionId = [{}]", userVo.getUserId());
        return true;
    }
}
