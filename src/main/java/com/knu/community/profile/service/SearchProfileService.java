package com.knu.community.profile.service;

import com.knu.community.error.NotFoundException;
import com.knu.community.profile.domain.Profile;
import com.knu.community.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SearchProfileService {
    private final ProfileRepository profileRepository;

    public Profile searchProfileBy(String nickName) {
        return profileRepository.findByMember_Nickname(nickName).orElseThrow(()->new NotFoundException("프로필이 없습니다."));
    }
}
