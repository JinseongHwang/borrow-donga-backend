package cs.borrowdonga.service;

import cs.borrowdonga.domain.member.Member;
import cs.borrowdonga.domain.member.MemberRepository;
import cs.borrowdonga.dto.member.DeleteRequestDto;
import cs.borrowdonga.dto.member.JoinRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원 가입
     */
    @Transactional
    public Long join(JoinRequestDto requestDto) {
        Member member = requestDto.toEntity();
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }

    @Transactional
    public void delete(DeleteRequestDto requestDto) {
        Member member = memberRepository.findMemberByStudentNumber(requestDto.getStudentNumber());
        memberRepository.delete(member);
    }
}
