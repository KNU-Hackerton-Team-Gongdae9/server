package com.knu.community.profile.dto;

import com.knu.community.member.domain.Major;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@NoArgsConstructor
@Setter
@Getter
public class ProfileForm {
    private String email;
    private Integer grade;

    private String language;
    private String nickname;

    private String githubLink;
    private String blogLink;
}
