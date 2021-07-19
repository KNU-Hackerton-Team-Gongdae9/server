package com.knu.community.member.domain;


import com.knu.community.base.BaseTimeEntity;
import com.knu.community.board.domain.WriteBoard;
import com.knu.community.comment.domain.WriteComment;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
    private MemberRole role = MemberRole.ROLE_NOT_PERMITTED;


    @OneToMany(mappedBy="member")
    private List<WriteBoard> boardList;

    @OneToMany(mappedBy="member")
    private List<WriteComment> commentList;

    public void changeRole(MemberRole memberRole){
        this.role = memberRole;
    }


}