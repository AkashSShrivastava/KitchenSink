package com.hitachi.KitchenSink.service;

import com.hitachi.KitchenSink.model.Member;
import com.hitachi.KitchenSink.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberRegistration {

    @Autowired
    private MemberRepository memberRepository;

    public void register(Member member) {
        memberRepository.save(member);
    }
}
