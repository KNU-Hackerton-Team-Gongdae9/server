package com.knu.community.profile.service;

import com.knu.community.error.NotFoundException;
import com.knu.community.profile.domain.Profile;
import com.knu.community.profile.dto.ProfileForm;
import com.knu.community.profile.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChangeProfileService {
    private final ProfileRepository profileRepository;

    @Transactional
    public void changeProfile(Long memberId, ProfileForm profileForm) {
        Profile profile = profileRepository.findByMember_Id(memberId).orElseThrow(() ->new NotFoundException("프로필이 없습니다."));
        profile.update(profileForm);
    }
}
