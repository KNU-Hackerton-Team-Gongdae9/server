package com.knu.community.message.controller;

import com.knu.community.message.domain.Message;
import com.knu.community.message.dto.MessageDto;
import com.knu.community.message.dto.MessageForm;
import com.knu.community.message.service.SearchMessageService;
import com.knu.community.message.service.SendMessageService;
import com.knu.community.util.ApiUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/message")
public class MessageController {

    private final SearchMessageService searchMessageService;
    private final SendMessageService sendMessageService;

    @GetMapping("/received/user/{user_id}")
    public ApiUtils.ApiResult<List<MessageDto>> getReceived(@PathVariable("user_id") Long userId){
        return ApiUtils.success(searchMessageService.searchReceived(userId).stream().map(MessageDto::new).collect(Collectors.toList()));
    }

    @GetMapping("/sent/user/{user_id}")
    public ApiUtils.ApiResult<List<MessageDto>> getSent(@PathVariable("user_id") Long userId){
        return ApiUtils.success(searchMessageService.searchSent(userId).stream().map(MessageDto::new).collect(Collectors.toList()));
    }

    @GetMapping("/user/{my_id}/sender/{other_nickname}")
    public ApiUtils.ApiResult<List<MessageDto>> getChatList(@PathVariable("my_id") Long myId, @PathVariable("other_nickname") String otherNickname){
        return ApiUtils.success(searchMessageService.searchChatList(myId, otherNickname).stream().map(MessageDto::new).collect(Collectors.toList()));
    }

    @PostMapping("/from/{sender_id}/to/{receiver_nickname}")
    public ApiUtils.ApiResult<String> send(@Valid @RequestBody MessageForm messageForm, @PathVariable("sender_id") Long senderId, @PathVariable("receiver_nickname") String receiverNickname){
        sendMessageService.send(senderId, receiverNickname, messageForm);
        return ApiUtils.success("성공");
    }
}