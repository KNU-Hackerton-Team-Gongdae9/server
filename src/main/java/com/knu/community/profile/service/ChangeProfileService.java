package com.knu.community.profile.service;

import com.knu.community.error.NotFoundException;
import com.knu.community.image.S3UploadService;
import com.knu.community.member.domain.Member;
import com.knu.community.member.repository.MemberRepository;
import com.knu.community.profile.domain.Profile;
import com.knu.community.profile.dto.ProfileForm;
import com.knu.community.profile.repository.ProfileRepository;
import com.knu.community.util.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ChangeProfileService {
    private final MemberRepository memberRepository;
    private final ProfileRepository profileRepository;
    private final S3UploadService s3UploadService;
    private final CreateProfileService createProfileService;

    @Transactional
    public void changeProfile(Long memberId, ProfileForm profileForm) {
        Optional<Profile> profileOptional = profileRepository.findByMember_Id(memberId);
        if(!profileOptional.isPresent())
            createProfileService.createProfile(memberId, profileForm);

        Profile profile = profileRepository.findByMember_Id(memberId).orElseThrow(NotFoundException::new);
        profile.update(profileForm);

        Member member = memberRepository.findById(memberId).orElseThrow(() -> new NotFoundException("no user"));
        member.updateInfo(profileForm);
    }

    @Transactional
    public Profile changeProfileImage(Long memberId, MultipartFile file) throws IOException {
        Profile profile = profileRepository.findByMember_Id(memberId).orElseThrow(() ->new NotFoundException("프로필이 없습니다."));
        profile.setImageLink(s3UploadService.upload(file, "static"));
        return profile;
    }
}
