package com.knu.community.profile.service;

import com.knu.community.member.domain.Member;
import com.knu.community.member.repository.MemberRepository;
import com.knu.community.profile.domain.Profile;
import com.knu.community.profile.dto.ProfileForm;
import com.knu.community.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CreateProfileService {
    private final ProfileRepository profileRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void createProfile(Long memberId, ProfileForm profileForm) {
        Member member = memberRepository.findById(memberId).orElseThrow(()->new IllegalArgumentException("사용자가 없습니다."));
        profileRepository.save(Profile.createProfile(member, profileForm));
    }
}
