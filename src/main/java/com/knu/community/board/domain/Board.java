package com.knu.community.board.domain;


import com.knu.community.base.BaseTimeEntity;
import com.knu.community.board.dto.BoardForm;
import com.knu.community.comment.domain.Comment;
import com.knu.community.member.domain.Member;
import com.knu.community.reply.dto.ReplyForm;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor @AllArgsConstructor
@Builder
@Getter
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name="board_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String title;

    private String content;

    private String author;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    @OneToMany(mappedBy="board")
    @Builder.Default private List<Comment> commentList = new ArrayList<>();


    public void setMember(Member member){
        if(this.member!=null){
            this.member.getBoardList().remove(this);
        }
        this.member=member;
        member.getBoardList().add(this);
    }
    public void edit(BoardForm boardForm){
        content = changedInfo(content, boardForm.getContent());
        author = changedInfo(author, boardForm.getAuthor());
    }

    private String changedInfo(String original, String changed){
        return (changed == null || changed.equals("")) ? original : changed;
    }


    public static Board createBoard(Member member,BoardForm boardForm){
        Board board =Board.builder()
            .category(boardForm.getCategory())
            .title(boardForm.getTitle())
            .content(boardForm.getContent())
            .author(boardForm.getAuthor())
            .build();

        board.setMember(member);

        return board;
    }
}
