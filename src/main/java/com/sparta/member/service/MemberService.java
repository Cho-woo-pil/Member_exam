package com.sparta.member.service;

import com.sparta.member.dto.MemberResponseDto;
import com.sparta.member.entity.Member;
import com.sparta.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResponseDto findMember(Long id) {
        Member member = checkid(id);
        return new MemberResponseDto(member);
    }

    public List<MemberResponseDto> findAllMember() {
        List<Member> memberList = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();

        for (Member member : memberList) {
            MemberResponseDto a = new MemberResponseDto(member);
            memberResponseDtos.add(a);
        }
        return memberResponseDtos;

    }


    private Member checkid(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                () -> new NullPointerException("선택한 ID는 존재하지 않습니다.")
        );
        return member;
    }
}