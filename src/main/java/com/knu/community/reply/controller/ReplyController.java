package com.knu.community.reply.controller;

import com.knu.community.email.service.AuthService;
import com.knu.community.reply.domain.Reply;
import com.knu.community.reply.dto.ReplyDto;
import com.knu.community.reply.dto.ReplyForm;
import com.knu.community.reply.service.DeleteReplyService;
import com.knu.community.reply.service.EditReplyService;
import com.knu.community.reply.service.GetReplyService;
import com.knu.community.reply.service.WriteReplyService;
import com.knu.community.util.ApiUtils;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/reply")
public class ReplyController {
    private final GetReplyService getReplyService;
    private final WriteReplyService writeReplyService;
    private final EditReplyService editReplyService;
    private final DeleteReplyService deleteReplyService;
    private final AuthService authService;

    @PostMapping("/comment/{comment_id}")
    public ApiUtils.ApiResult<String> writeReply(@Valid @RequestBody ReplyForm replyForm, @PathVariable("comment_id") Long commentId){
        writeReplyService.write(commentId, replyForm);
        return ApiUtils.success("성공했습니다.");
    }

    @PutMapping("/{reply_id}")
    public ApiUtils.ApiResult<String> editReply(@Valid @RequestBody ReplyForm replyForm, @PathVariable("reply_id") Long replyId){
        editReplyService.edit(replyId, replyForm);
        return ApiUtils.success("성공했습니다.");
    }

    @DeleteMapping("/{reply_id}")
    public ApiUtils.ApiResult<String> deleteReply(@PathVariable("reply_id") Long replyId){
        deleteReplyService.delete(replyId);
        return ApiUtils.success("성공했습니다.");
    }

    @GetMapping("/getMyReplies")
    public ApiUtils.ApiResult<List<ReplyDto>> getMyReplies(HttpServletRequest req){
        Long memId = authService.getUserIdFromJWT(req);
        return ApiUtils.success(getReplyService.findMyReplies(memId).stream().map(ReplyDto::new).collect(Collectors.toList()));
    }
}
