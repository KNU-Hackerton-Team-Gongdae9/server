package com.knu.community.member.domain;


import com.knu.community.base.BaseTimeEntity;
import com.knu.community.board.domain.WriteBoard;
import com.knu.community.comment.domain.WriteComment;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

import com.knu.community.message.domain.Message;
import com.knu.community.profile.domain.Profile;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor @AllArgsConstructor
@Getter
public class Member extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    private String email;
    private String password;
    private String username;
    private String nickname;
    private String studentId;

    private Integer grade;


    @Enumerated(EnumType.STRING)
    private Major major;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @Builder.Default private MemberRole role = MemberRole.ROLE_NOT_PERMITTED;


    @OneToMany(mappedBy="member")
    @Builder.Default private List<WriteBoard> boardList = new ArrayList<>();

    @OneToMany(mappedBy="member")
    @Builder.Default private List<WriteComment> commentList = new ArrayList<>();

    // message
    @OneToMany(mappedBy = "sender")
    @Builder.Default private List<Message> sentMessageList = new ArrayList<>();

    @OneToMany(mappedBy = "receiver")
    @Builder.Default private List<Message> receivedMessageList = new ArrayList<>();

    @OneToOne(mappedBy = "member")
    private Profile profile;

    public void changeRole(MemberRole memberRole){
        this.role = memberRole;
    }

    public void setProfile(Profile profile) { this.profile = profile; }
}