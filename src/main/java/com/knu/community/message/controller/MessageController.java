package com.knu.community.message.controller;

import com.knu.community.Response;
import com.knu.community.message.domain.Message;
import com.knu.community.message.dto.MessageForm;
import com.knu.community.message.service.SearchMessageService;
import com.knu.community.message.service.SendMessageService;
import com.knu.community.util.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/message")
public class MessageController {

    private final SearchMessageService searchMessageService;
    private final SendMessageService sendMessageService;

    @GetMapping("/received/user/{user_id}")
    public ApiUtils.ApiResult<List<Message>> getReceived(@PathParam("user_id") Long userId){
        return ApiUtils.success(searchMessageService.searchReceived(userId));
    }

    @GetMapping("/sent/user/{user_id}")
    public ApiUtils.ApiResult<List<Message>> getSent(@PathParam("user_id") Long userId){
        return ApiUtils.success(searchMessageService.searchSent(userId));
    }

    @PostMapping("/from/{sender_id}/to/{receiver_id}")
    public ApiUtils.ApiResult<String> send(@Valid @RequestBody MessageForm messageForm, @PathParam("sender_id") Long senderId, @PathParam("receiver_id") Long receiverId){
        sendMessageService.send(senderId, receiverId, messageForm);
        return ApiUtils.success("성공");
    }
}