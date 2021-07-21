package com.knu.community;

import com.knu.community.board.domain.Board;
import com.knu.community.board.domain.Category;
import com.knu.community.board.dto.BoardForm;
import com.knu.community.board.repository.BoardRepository;
import com.knu.community.board.service.BoardService;
import com.knu.community.comment.domain.Comment;
import com.knu.community.comment.dto.CommentForm;
import com.knu.community.comment.service.CommentService;
import com.knu.community.email.service.AuthService;
import com.knu.community.member.domain.Major;
import com.knu.community.member.domain.Member;
import com.knu.community.member.dto.SignUpForm;
import com.knu.community.member.repository.MemberRepository;
import com.knu.community.reply.domain.Reply;
import com.knu.community.reply.dto.ReplyForm;
import com.knu.community.reply.service.WriteReplyService;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class BoardTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private WriteReplyService replyService;

    @Test
    public void 게시판_댓글_전체_조회(){
        SignUpForm signUpForm1 = new SignUpForm("ggolong5", "ggolong5@knu.ac.kr", "1234", "권현수", "2020202020", 0, Major.ADVANCED);
        SignUpForm signUpForm2 = new SignUpForm("ggolong2", "ggolong2@knu.ac.kr", "1234", "김창묵", "2020202020", 1, Major.ADVANCED);
        SignUpForm signUpForm3 = new SignUpForm("ggolong3", "ggolong3@knu.ac.kr", "1234", "이현섭", "2020202020", 1, Major.ADVANCED);
        SignUpForm signUpForm4 = new SignUpForm("ggolong4", "ggolong4@knu.ac.kr", "1234", "한현민", "2020202020", 1, Major.ADVANCED);

        try {
            Member member1 = authService.signUpMember(signUpForm1);
            Member member2 = authService.signUpMember(signUpForm2);
            Member member3 = authService.signUpMember(signUpForm3);
            Member member4 = authService.signUpMember(signUpForm4);

            /* Board 생성 */
            BoardForm boardForm = BoardForm.builder()
                .category(Category.QNA)
                .author(member1.getUsername())
                .Title("제목1")
                .content("내용1")
                .build();

            Board board = boardService.writeBoard(member1.getId(), boardForm);

            /* Comment 생성 */
            CommentForm commentForm = CommentForm.builder()
                .author(member2.getUsername())
                .content(member2.getUsername() + "이 작성한 댓글 내용")
                .build();
            Comment comment = commentService.writeComment(board.getId(), member1.getId(), commentForm);


            /* Reply 생성 */
            ReplyForm replyForm = new ReplyForm(member3.getUsername() + "이 작성한 답글 내용", member3.getUsername());
            replyService.write(comment.getId(), replyForm);

            ReplyForm replyForm2 = new ReplyForm(member4.getUsername() + "이 작성한 답글 내용", member4.getUsername());
            replyService.write(comment.getId(), replyForm2);

            //given
            Board boardWithAll = boardRepository.findById(board.getId()).get();

            System.out.println(boardWithAll.getAuthor() + "가 작성한 글입니다.");
            for (Comment commentT : boardWithAll.getCommentList()) {
                System.out.println(commentT.getAuthor() + "가 작성한 댓글입니다.");
                for (Reply reply : commentT.getReplyList()) {
                    System.out.println(reply.getAuthor() + "가 작성한 답글입니다.");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
