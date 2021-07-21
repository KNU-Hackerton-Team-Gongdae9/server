package com.knu.community.profile.dto;

import com.knu.community.profile.domain.Profile;
import lombok.Getter;

@Getter
public class ProfileDto {
    private String language;
    private String interest;
    private String githubLink;
    private String blogLink;
    // 포트폴리오 추가해야함
    private String imageLink;

    public ProfileDto(Profile profile){
        this.language = profile.getLanguage();
        this.interest = profile.getInterest();
        this.githubLink = profile.getGithubLink();
        this.blogLink = profile.getBlogLink();
        this.imageLink = profile.getImageLink();
    }
}


