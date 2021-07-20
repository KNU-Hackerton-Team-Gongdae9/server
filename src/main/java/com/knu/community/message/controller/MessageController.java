package com.knu.community.message.controller;

import com.knu.community.Response;
import com.knu.community.message.dto.MessageForm;
import com.knu.community.message.service.SearchMessageService;
import com.knu.community.message.service.SendMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/message")
public class MessageController {

    private final SearchMessageService searchMessageService;
    private final SendMessageService sendMessageService;

    @GetMapping("/received/user/{user_id}")
    public Response getReceived(@PathVariable("user_id") Long userId){
        return new Response("success", "쪽지!", searchMessageService.searchReceived(userId));
    }

    @GetMapping("/sent/user/{user_id}")
    public Response getSent(@PathVariable("user_id") Long userId){
        return new Response("success", "쪽지!", searchMessageService.searchSent(userId));
    }

    @PostMapping("/from/{sender_id}/to/{receiver_id}")
    public Response send(@Valid @RequestBody MessageForm messageForm, @PathVariable("sender_id") Long senderId, @PathVariable("receiver_id") Long receiverId){
        sendMessageService.send(senderId, receiverId, messageForm);
        return new Response("success", "쪽지 전송 완료!", null);
    }
}