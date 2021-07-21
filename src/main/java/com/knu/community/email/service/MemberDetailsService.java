package com.knu.community.email.service;

import com.knu.community.error.NotFoundException;
import com.knu.community.member.domain.Member;
import com.knu.community.member.repository.MemberRepository;
import com.knu.community.member.security.SecurityMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberDetailsService implements UserDetailsService {
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email).orElseThrow(()->
            new UsernameNotFoundException(email + " : 사용자 존재하지 않음")
        );
        return new SecurityMember(member);
    }
}