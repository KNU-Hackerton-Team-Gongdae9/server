package com.knu.community.profile.domain;

import com.knu.community.base.BaseTimeEntity;
import com.knu.community.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MyProfile extends BaseTimeEntity {

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
    private Member member;
}
