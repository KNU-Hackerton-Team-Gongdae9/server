package com.knu.community.reply.controller;

import com.knu.community.Response;
import com.knu.community.reply.dto.ReplyForm;
import com.knu.community.reply.service.DeleteReplyService;
import com.knu.community.reply.service.EditReplyService;
import com.knu.community.reply.service.WriteReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/reply")
public class ReplyController {
    private final WriteReplyService writeReplyService;
    private final EditReplyService editReplyService;
    private final DeleteReplyService deleteReplyService;

    @PostMapping("/comment/{comment_id}")
    public Response writeReply(@Valid @RequestBody ReplyForm replyForm, @PathVariable("comment_id") Long commentId){
        writeReplyService.write(commentId, replyForm);
        return new Response("success", "답글이 달렸습니다.", null);
    }

    @PutMapping("/{reply_id}")
    public Response editReply(@Valid @RequestBody ReplyForm replyForm, @PathVariable("reply_id") Long replyId){
        editReplyService.edit(replyId, replyForm);
        return new Response("success", "답글이 수정 되었습니다.", null);
    }

    @DeleteMapping("/{reply_id}")
    public Response deleteReply(@PathVariable("reply_id") Long replyId){
        deleteReplyService.delete(replyId);
        return new Response("success", "답글이 삭제 되었습니다.", null);
    }
}
