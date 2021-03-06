package com.knu.community;

import com.knu.community.board.domain.Board;
import com.knu.community.board.domain.Category;
import com.knu.community.board.dto.BoardDetailDto;
import com.knu.community.board.dto.BoardForm;
import com.knu.community.board.repository.BoardRepository;
import com.knu.community.board.service.BoardService;
import com.knu.community.comment.domain.Comment;
import com.knu.community.comment.dto.CommentForm;
import com.knu.community.comment.repository.CommentRepository;
import com.knu.community.comment.service.CommentService;
import com.knu.community.email.service.AuthService;
import com.knu.community.member.domain.Major;
import com.knu.community.member.domain.Member;
import com.knu.community.member.dto.SignUpForm;
import com.knu.community.member.repository.MemberRepository;
import com.knu.community.reply.domain.Reply;
import com.knu.community.reply.dto.ReplyForm;
import com.knu.community.reply.repository.ReplyRepository;
import com.knu.community.reply.service.GetReplyService;
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
    private BoardService boardService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private WriteReplyService replyService;

    @Autowired
    private GetReplyService getReplyService;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private BoardRepository boardRepository;



    SignUpForm signUpForm1 = new SignUpForm("ggolong2", "ggolong5@knu.ac.kr", "1234", "κΆνμ", "2020202020", 0, Major.ADVANCED);
    SignUpForm signUpForm2 = new SignUpForm("kcm0147", "ggolong2@knu.ac.kr", "1234", "κΉμ°½λ¬΅", "2020202020", 1, Major.ADVANCED);
    SignUpForm signUpForm3 = new SignUpForm("billbillbillbill", "ggolong3@knu.ac.kr", "1234", "μ΄νμ­", "2020202020", 1, Major.ADVANCED);
    SignUpForm signUpForm4 = new SignUpForm("HyunMin", "ggolong4@knu.ac.kr", "1234", "ννλ―Ό", "2020202020", 1, Major.ADVANCED);

    @BeforeEach
    public void initDB() throws Exception{
        Member member1 = authService.signUpMember(signUpForm1);
        Member member2 = authService.signUpMember(signUpForm2);
        Member member3 = authService.signUpMember(signUpForm3);
        Member member4 = authService.signUpMember(signUpForm4);
    }

    @Test
    public void κ²μν_λκΈ_μ μ²΄_μ‘°ν(){
        //given
        Member member1 = memberRepository.findByEmail(signUpForm1.getEmail()).get();
        Member member2 = memberRepository.findByEmail(signUpForm2.getEmail()).get();
        Member member3 = memberRepository.findByEmail(signUpForm3.getEmail()).get();
        Member member4 = memberRepository.findByEmail(signUpForm4.getEmail()).get();

        try {

            /* Board μμ± */
//            BoardForm boardForm = BoardForm.builder()
//                .category(Category.QNA)
//                .author(member1.getUsername())
//                .Title("μ λͺ©1")
//                .content("λ΄μ©1")
//                .build();

 //           Board board = boardService.writeBoard(member1.getId(), boardForm);

            /* Comment μμ± */
//            CommentForm commentForm = CommentForm.builder()
//                .author(member2.getUsername())
//                .content(member2.getUsername() + "μ΄ μμ±ν λκΈ λ΄μ©")
//                .build();
          //  Comment comment = commentService.writeComment(board.getId(), member2.getId(), commentForm);


            /* Reply μμ± */
//            ReplyForm replyForm = new ReplyForm(member3.getUsername() + "μ΄ μμ±ν λ΅κΈ λ΄μ©", member3.getUsername());
//            replyService.write(comment.getId(), replyForm);
//
//            ReplyForm replyForm2 = new ReplyForm(member4.getUsername() + "μ΄ μμ±ν λ΅κΈ λ΄μ©", member4.getUsername());
//            replyService.write(comment.getId(), replyForm2);

            //given
 //           Board boardWithAll = boardRepository.findById(board.getId()).get();

//            System.out.println(boardWithAll.getAuthor() + "κ° μμ±ν κΈμλλ€.");
//            for (Comment commentT : boardWithAll.getCommentList()) {
//                System.out.println(commentT.getAuthor() + "κ° μμ±ν λκΈμλλ€.");
//                for (Reply reply : commentT.getReplyList()) {
//                    System.out.println(reply.getAuthor() + "κ° μμ±ν λ΅κΈμλλ€.");
//                }
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void λ΄κ°_μμ±ν_κ²μλ¬Ό_λκΈ_λ΅κΈ_μ‘°ν() throws Exception {
        //given
        Member member1 = memberRepository.findByEmail(signUpForm1.getEmail()).get();
        Member member2 = memberRepository.findByEmail(signUpForm2.getEmail()).get();
        Member member3 = memberRepository.findByEmail(signUpForm3.getEmail()).get();
        Member member4 = memberRepository.findByEmail(signUpForm4.getEmail()).get();

        //when
        /* Board μμ± */
//        BoardForm boardForm = BoardForm.builder()
//            .category(Category.QNA)
//            .author(member1.getNickname())
//            .Title("μ λͺ©1")
//            .content("λ΄μ©1")
//            .build();

  //      Board board = boardService.writeBoard(member1.getId(), boardForm);

        /* Comment μμ± */
//        CommentForm commentForm = CommentForm.builder()
//            .author(member2.getUsername())
//            .content(member2.getUsername() + "μ΄ μμ±ν λκΈ λ΄μ©")
//            .build();
//        Comment comment = commentService.writeComment(board.getId(), member2.getId(), commentForm);
//
//        /* Comment μμ± */
//        commentForm = CommentForm.builder()
//            .author(member1.getUsername())
//            .content(member1.getUsername() + "μ΄ μμ±ν λκΈ λ΄μ©")
//            .build();
     //   Comment comment2 = commentService.writeComment(board.getId(), member1.getId(), commentForm);

        /* Reply μμ± */
//        ReplyForm replyForm = new ReplyForm(member1.getUsername() + "μ΄ μμ±ν λ΅κΈ λ΄μ©", member1.getUsername());
//        replyService.write(comment.getId(), replyForm);
//
//        ReplyForm replyForm2 = new ReplyForm(member4.getUsername() + "μ΄ μμ±ν λ΅κΈ λ΄μ©", member4.getUsername());
//        replyService.write(comment.getId(), replyForm2);
//
//        ReplyForm replyForm3 = new ReplyForm(member2.getUsername() + "μ΄ μμ±ν λ΅κΈ λ΄μ©", member2.getUsername());
//        replyService.write(comment2.getId(), replyForm3);
//
//        ReplyForm replyForm4 = new ReplyForm(member3.getUsername() + "μ΄ μμ±ν λ΅κΈ λ΄μ©", member3.getUsername());
//        replyService.write(comment2.getId(), replyForm4);

        //then
        List<BoardDetailDto> boards = boardService.findMyBoards(member1.getId());
        for (BoardDetailDto board1 : boards) {
            System.out.println(board1.getTitle());
            System.out.println(board1.getTitle());
        }
        List<Comment> myComments = commentService.findMyComments(member1.getId());
        for (Comment myComment : myComments) {
            System.out.println(myComment.getAuthor());
            System.out.println(myComment.getContent());
        }
        List<Reply> myReplies = getReplyService.findMyReplies(member1.getId());
        for (Reply myReply : myReplies) {
            System.out.println(myReply.getAuthor());
            System.out.println(myReply.getContent());
        }
    }
}
