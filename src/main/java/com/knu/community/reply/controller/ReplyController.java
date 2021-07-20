package com.knu.community.reply.controller;

import com.knu.community.reply.dto.ReplyForm;
import com.knu.community.reply.service.DeleteReplyService;
import com.knu.community.reply.service.EditReplyService;
import com.knu.community.reply.service.WriteReplyService;
import com.knu.community.util.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/reply")
public class ReplyController {
    private final WriteReplyService writeReplyService;
    private final EditReplyService editReplyService;
    private final DeleteReplyService deleteReplyService;

    @PostMapping("/comment/{comment_id}")
    public ApiUtils.ApiResult<String> writeReply(@Valid @RequestBody ReplyForm replyForm, @PathParam("comment_id") Long commentId){
        writeReplyService.write(commentId, replyForm);
        return ApiUtils.success("성공했습니다.");
    }

    @PutMapping("/{reply_id}")
    public ApiUtils.ApiResult<String> editReply(@Valid @RequestBody ReplyForm replyForm, @PathParam("reply_id") Long replyId){
        editReplyService.edit(replyId, replyForm);
        return ApiUtils.success("성공했습니다.");
    }

    @DeleteMapping("/{reply_id}")
    public ApiUtils.ApiResult<String> deleteReply(@PathParam("reply_id") Long replyId){
        deleteReplyService.delete(replyId);
        return ApiUtils.success("성공했습니다.");
    }
}
