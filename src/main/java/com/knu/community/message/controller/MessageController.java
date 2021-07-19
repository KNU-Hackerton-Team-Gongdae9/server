package com.knu.community.message.controller;

import com.knu.community.Response;
import com.knu.community.message.dto.MessageForm;
import com.knu.community.message.service.SearchMessageService;
import com.knu.community.message.service.SendMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/message")
public class MessageController {

    private final SearchMessageService searchMessageService;
    private final SendMessageService sendMessageService;

    @GetMapping("/received/user/{user_id}")
    public Response getReceived(@PathParam("user_id") Long userId){
        return new Response("success", "쪽지!", searchMessageService.searchReceived(userId));
    }

    @GetMapping("/sent/user/{user_id}")
    public Response getSent(@PathParam("user_id") Long userId){
        return new Response("success", "쪽지!", searchMessageService.searchSent(userId));
    }

    @PostMapping("/from/{sender_id}/to/{receiver_id}")
    public Response send(@Valid @RequestBody MessageForm messageForm, @PathParam("sender_id") Long senderId, @PathParam("receiver_id") Long receiverId){
        sendMessageService.send(senderId, receiverId, messageForm);
        return new Response("success", "쪽지 전송 완료!", null);
    }
}