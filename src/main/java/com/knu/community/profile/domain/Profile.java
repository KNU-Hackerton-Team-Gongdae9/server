package com.knu.community.profile.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.knu.community.base.BaseTimeEntity;
import com.knu.community.member.domain.Member;
import com.knu.community.profile.dto.ProfileForm;
import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Profile extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "profile_id")
    private Long id;

    private String language;
    private String interest;
    private String githubLink;
    private String blogLink;
    // 포트폴리오 추가해야함
    private String imageLink;

    @OneToOne
    @JoinColumn(name = "member_id")
    @JsonIgnore
    private Member member;

    public void update(ProfileForm profileForm){
        language = changedInfo(language, profileForm.getLanguage());
        interest = changedInfo(interest, profileForm.getInterest());
        githubLink = changedInfo(githubLink, profileForm.getGithubLink());
        blogLink = changedInfo(blogLink, profileForm.getBlogLink());
        imageLink = changedInfo(imageLink, profileForm.getImageLink());
    }

    private String changedInfo(String original, String changed){
        return (changed == null || changed.equals("")) ? original : changed;
    }

    public void setMember(Member member) {
        this.member = member;
        member.setProfile(this);
    }

    public static Profile createProfile(Member member, ProfileForm profileForm){
        Profile profile =  Profile.builder().language(profileForm.getLanguage())
                .interest(profileForm.getInterest())
                .githubLink(profileForm.getGithubLink())
                .blogLink(profileForm.getBlogLink())
                .imageLink(profileForm.getImageLink())
                .build();
        profile.setMember(member);
        return profile;
    }
}
