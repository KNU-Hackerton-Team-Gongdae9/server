package com.knu.community.board.domain;




import com.knu.community.base.BaseTimeEntity;
import com.knu.community.member.domain.Member;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;

@Entity
@Getter
public class WriteBoard extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name="write_board_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name="board_id")
    private Board board;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

    public WriteBoard(){}

    public WriteBoard (Member member,Board board){
        setMember(member);
        setBoard(board);
    }


    public void setMember(Member member){
        if(this.member!=null){
            this.member.getBoardList().remove(this);
        }
        this.member=member;
        member.getBoardList().add(this);
    }

    public void setBoard(Board board){
        if(this.board!=null){
            this.board.getWriterList().remove(this);
        }
        this.board=board;
        board.getWriterList().add(this);
    }
}
