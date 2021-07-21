package com.knu.community.member.controller;


import com.knu.community.email.service.AuthService;
import com.knu.community.email.util.CookieUtil;
import com.knu.community.email.util.JwtUtil;
import com.knu.community.email.util.RedisUtil;
import com.knu.community.error.NotFoundException;
import com.knu.community.member.domain.Member;
import com.knu.community.member.dto.RequestVerifyEmail;
import com.knu.community.member.dto.SignInForm;
import com.knu.community.member.dto.SignUpForm;
import com.knu.community.util.ApiUtils;
import com.knu.community.util.ApiUtils.ApiResult;
import io.swagger.annotations.ApiOperation;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class MemberController {

    private final AuthService authService;
    private final JwtUtil jwtUtil;
    private final CookieUtil cookieUtil;
    private final RedisUtil redisUtil;

    @ApiOperation(notes = "로그인", value = "회원정보를 입력해 로그인을 수행하고 JWT 토큰 발급")
    @PostMapping("/signIn")
    public ApiResult<String> signIn(@Valid @RequestBody SignInForm signInForm, HttpServletResponse res) throws Exception {
        final Member member = authService.loginUser(signInForm);
        final String token = jwtUtil.generateToken(member.getEmail());
        final String refreshJwt = jwtUtil.generateRefreshToken(member);
        Cookie accessToken = cookieUtil.createCookie(JwtUtil.ACCESS_TOKEN_NAME, token);
        Cookie refreshToken = cookieUtil.createCookie(JwtUtil.REFRESH_TOKEN_NAME, refreshJwt);
        redisUtil.setDataExpire(refreshJwt, member.getEmail(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
        res.addCookie(accessToken);
        res.addCookie(refreshToken);
        return ApiUtils.success("로그인에 성공했습니다. \n발급받은 토큰 : " + token);
    }

    @ApiOperation(notes = "회원가입", value = "회원가입 메소드")
    @PostMapping("/signUp")
    public ApiResult<String> signUpSubmit(@Valid @RequestBody SignUpForm signUpForm, Errors errors) throws NotFoundException {
        Member savedMember = authService.signUpMember(signUpForm);
        authService.sendVerificationMail(savedMember);
        return ApiUtils.success("회원가입을 성공적으로 완료했습니다.");
    }

    @ApiOperation(notes = "이메일 인증", value = "회원가입 시 이메일 인증을 수행하는 메소드")
    @PostMapping("/verify")
    public ApiResult<String> verify(@RequestBody RequestVerifyEmail requestVerifyEmail) throws NotFoundException {
        Member member = authService.findByEmail(requestVerifyEmail.getEmail());
        authService.sendVerificationMail(member);
        return ApiUtils.success("성공적으로 인증메일을 보냈습니다.");
    }

    @ApiOperation(notes = "이메일 인증", value="회원가입 시 이메일로 전송한 인증 링크를 확인하는 메소드")
    @GetMapping("/verify/{key}")
    public ApiResult<String> getVerify(@PathVariable String key) throws NotFoundException{
        authService.verifyEmail(key);
        return ApiUtils.success("성공적으로 인증메일을 확인했습니다.");
    }


    @ApiOperation(notes = "회원 고유 ID 반환", value="현재 로그인한 유저의 고유 ID 를 반환하는 메소드")
    @GetMapping("/getUserId")
    public Long getUserEmail(HttpServletRequest req) throws NotFoundException {
        return authService.getUserIdFromJWT(req);
    }
}
