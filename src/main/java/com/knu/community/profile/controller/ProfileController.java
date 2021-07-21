package com.knu.community.profile.controller;

import com.knu.community.profile.domain.Profile;
import com.knu.community.profile.dto.ProfileForm;
import com.knu.community.profile.service.ChangeProfileService;
import com.knu.community.profile.service.CreateProfileService;
import com.knu.community.profile.service.SearchProfileService;
import com.knu.community.util.ApiUtils;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/profile")
public class ProfileController {
    private final CreateProfileService createProfileService;
    private final ChangeProfileService changeProfileService;
    private final SearchProfileService searchProfileService;

    @GetMapping("/user/{nickname}")
    public ApiUtils.ApiResult<Profile> getProfile(@PathVariable("nickname") String nickName){
        return ApiUtils.success(searchProfileService.searchProfileBy(nickName));
    }

    @PostMapping("/user/{member_id}")
    public ApiUtils.ApiResult<String> createProfile(@Valid @RequestBody ProfileForm profileForm, @PathVariable("member_id") Long memberId){
        createProfileService.createProfile(memberId, profileForm);
        return ApiUtils.success("성공");
    }

    @PutMapping("/user/{member_id}")
    public ApiUtils.ApiResult<String> changeProfile(@Valid @RequestBody ProfileForm profileForm, @PathVariable("member_id") Long memberId){
        changeProfileService.changeProfile(memberId, profileForm);
        return ApiUtils.success("성공");
    }

    @PutMapping("/uploadProfileImage/user/{member_id}")
    public ApiUtils.ApiResult<Profile> changeProfileImage(@RequestParam MultipartFile file, @PathVariable("member_id") Long memberId) throws IOException {
        return ApiUtils.success(changeProfileService.changeProfileImage(memberId, file));
    }
}
