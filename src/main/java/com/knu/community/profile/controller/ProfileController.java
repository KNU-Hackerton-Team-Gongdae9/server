package com.knu.community.profile.controller;

import com.knu.community.profile.domain.Profile;
import com.knu.community.profile.dto.ProfileForm;
import com.knu.community.profile.service.ChangeProfileService;
import com.knu.community.profile.service.CreateProfileService;
import com.knu.community.profile.service.SearchProfileService;
import com.knu.community.util.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/profile")
@SessionAttributes("profile")
public class ProfileController {
    private final CreateProfileService createProfileService;
    private final ChangeProfileService changeProfileService;
    private final SearchProfileService searchProfileService;

    @GetMapping("/user/{member_id}")
    public ApiUtils.ApiResult<Profile> getProfile(@PathVariable("member_id") Long memberId){
        return ApiUtils.success(searchProfileService.searchProfile(memberId));
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
}
