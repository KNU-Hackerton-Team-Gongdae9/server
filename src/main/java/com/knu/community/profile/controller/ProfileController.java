package com.knu.community.profile.controller;

import com.knu.community.Response;
import com.knu.community.profile.dto.ProfileForm;
import com.knu.community.profile.service.ChangeProfileService;
import com.knu.community.profile.service.CreateProfileService;
import com.knu.community.profile.service.SearchProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/profile")
@SessionAttributes("profile")
public class ProfileController {
    private final CreateProfileService createProfileService;
    private final ChangeProfileService changeProfileService;
    private final SearchProfileService searchProfileService;

    @GetMapping("/user/{member_id}")
    public Response getProfile(@PathParam("member_id") Long memberId){
        return new Response("success", "프로필 참조", searchProfileService.searchProfile(memberId));
    }

    @PostMapping("/user/{member_id}")
    public Response createProfile(@Valid @RequestBody ProfileForm profileForm, @PathParam("member_id") Long memberId){
        createProfileService.createProfile(memberId, profileForm);
        return new Response("success", "프로필 생성 완료", null);
    }

    @PutMapping("/user/{member_id}")
    public Response changeProfile(@Valid @RequestBody ProfileForm profileForm, @PathParam("member_id") Long memberId){
        changeProfileService.changeProfile(memberId, profileForm);
        return new Response("success", "프로필 수정 완료", null);
    }
}
