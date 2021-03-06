package kr.co.maptics.mapticslogin.controller;

import kr.co.maptics.mapticslogin.model.LoginModel;
import kr.co.maptics.mapticslogin.model.request.LoginRequest;
import kr.co.maptics.mapticslogin.model.response.MapTicsLoginResponse;
import kr.co.maptics.mapticslogin.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {
    private final AuthService authService;

    @GetMapping("/")
    public String index(){
        return "login";
    }


    @PostMapping("/account")
    public ModelAndView account(LoginRequest login){
        MapTicsLoginResponse mapticsLoginResult = authService.accountLogin(login);
        //LoginResponse responseBody = testService.accountLogin(login);
        if(mapticsLoginResult != null){
            if( mapticsLoginResult.getLoginResult().equals("EXPIRES")){
                String Err = "EXPIRES";
                return new ModelAndView("login", "loginErr", Err);
            }
            String url = "redirect:";
            url += mapticsLoginResult.getMapTicsLoginResponseData().getUrl();
            String token = mapticsLoginResult.getMapTicsLoginResponseData().getToken();
            //token.getBytes(StandardCharsets.UTF_8);
            log.info("url: {}", url);
            log.info("token: {}", token);

            return new ModelAndView( url+token);

            //return "redirect:http://www.naver.com";

        }
        else{
            String Err = "Failed";
            return new ModelAndView("login", "loginErr", Err);
        }

    }

    @RequestMapping(value = "/robots.txt")
    @ResponseBody
    public String robots() {
        return "<p>User-agent: *</p>"+"\n<p>"+"Disallow: /"+"</p>\n";
    }
}
