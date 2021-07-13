package kr.co.maptics.mapticslogin.controller;

import kr.co.maptics.mapticslogin.model.LoginModel;
import kr.co.maptics.mapticslogin.model.request.LoginRequest;
import kr.co.maptics.mapticslogin.model.response.LoginResponse;
import kr.co.maptics.mapticslogin.model.response.MapTicsLoginResponse;
import kr.co.maptics.mapticslogin.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

@Slf4j
@RequiredArgsConstructor
@Controller
public class LoginController {
    private final AuthService authService;

    @GetMapping("/")
    public String index(){
        return "/login";
    }

    @PostMapping("/login")
    public String login(@Validated LoginModel loginModel){
        log.info("id: {}, pass: {}", loginModel.getId(), loginModel.getPassword());



        return "/login";
    }

    @PostMapping("/account")
    public ModelAndView account(LoginRequest login){
        log.info("LoginRequest: {}", login);
        MapTicsLoginResponse mapticsLoginResult = authService.accountLogin(login);
        //LoginResponse responseBody = testService.accountLogin(login);
        log.info("responseBody: {}", mapticsLoginResult);
        if(mapticsLoginResult != null){
            String url = "redirect:";
            url += mapticsLoginResult.getMapTicsLoginResponseDataList().get(0).getUrl();
            String token = mapticsLoginResult.getMapTicsLoginResponseDataList().get(0).getToken();
            //token.getBytes(StandardCharsets.UTF_8);
            log.info("url: {}", url);
            log.info("token: {}", token);

            return new ModelAndView( url+token);

            //return "redirect:http://www.naver.com";
        }
        else{
            String Err = "Failed";
            return new ModelAndView("/login", "loginErr", Err);
        }

    }
}
