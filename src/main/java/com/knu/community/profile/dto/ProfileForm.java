package com.knu.community.profile.dto;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

@NoArgsConstructor
@Setter
@Getter
public class ProfileForm {
    private String language;
    private String interest;
    private String githubLink;
    private String blogLink;
    // 포트폴리오 추가해야함
    private String imageLink;
}
