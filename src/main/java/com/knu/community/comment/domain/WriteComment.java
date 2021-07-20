package com.knu.community.comment.domain;


import com.knu.community.base.BaseTimeEntity;
import com.knu.community.member.domain.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class WriteComment extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name="write_comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="comment_id")
    private Comment comment;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;


    public WriteComment(Member member,Comment comment){
        setComment(comment);
        setMember(member);
    }

    public void setMember(Member member){
        if(this.member!=null){
            this.member.getCommentList().remove(this);
        }
        this.member=member;
        member.getCommentList().add(this);
    }

    public void setComment(Comment comment){
        if(this.comment!=null){
            this.comment.getCommenterList().remove(this);
        }
        this.comment=comment;
        comment.getCommenterList().add(this);
    }

}
