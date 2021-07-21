package com.knu.community.reply.service;

import com.knu.community.error.NotFoundException;
import com.knu.community.member.domain.Member;
import com.knu.community.member.repository.MemberRepository;
import com.knu.community.reply.domain.Reply;
import com.knu.community.reply.repository.ReplyRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetReplyService {

    private final MemberRepository memberRepository;
    private final ReplyRepository replyRepository;

    public List<Reply> findMyReplies(Long memId) {
        Member member = memberRepository.findById(memId).orElseThrow(()->
            new NotFoundException("존재하지 않는 유저입니다.")
        );
        return replyRepository.findMyReplies(member.getNickname()).orElseThrow(()->
            new NotFoundException("작성한 답글이 없습니다.")
        );
    }
}
