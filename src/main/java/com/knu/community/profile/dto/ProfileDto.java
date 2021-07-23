package com.knu.community.profile.dto;

import com.knu.community.member.domain.Major;
import com.knu.community.profile.domain.Profile;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;

@Getter
public class ProfileDto {
    private String language;
    private String interest;
    private String githubLink;
    private String blogLink;
    // 포트폴리오 추가해야함
    private String imageLink;


    private String email;
    private Integer grade;

    @Enumerated(EnumType.STRING)
    private Major major;

    public ProfileDto(Profile profile){
        this.language = profile.getLanguage();
        this.interest = profile.getInterest();
        this.githubLink = profile.getGithubLink();
        this.blogLink = profile.getBlogLink();
        this.imageLink = profile.getImageLink();
        this.email=profile.getMember().getEmail();
        this.grade=profile.getMember().getGrade();
        this.major=profile.getMember().getMajor();
    }
}


