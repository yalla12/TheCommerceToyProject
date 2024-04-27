package thecommerceproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import thecommerceproject.domain.Member;
import thecommerceproject.dto.request.MemberRequestDto;
import thecommerceproject.repository.MemberRepository;



@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    /**
     * 회원가입
     *
     * @param memberRequestDto 입력한 회원 정보
     * @Return Member
     */
    public Member createMember(MemberRequestDto memberRequestDto) throws Exception {

        //아이디 중복체크
        boolean dupMemberId = memberRepository.existsByMemberId(memberRequestDto.getMemberId());
        if(dupMemberId) throw new RuntimeException("아이디 중복");

        //전화번호 중복체크
        boolean dupphoneNumber =  memberRepository.existsByPhoneNumber(memberRequestDto.getPhoneNumber());
        if(dupphoneNumber) throw new RuntimeException("전화번호 중복");

        Member member = new Member(
                memberRequestDto.getMemberId(),
                memberRequestDto.getMemberPwd(),
                memberRequestDto.getNickname(),
                memberRequestDto.getName(),
                memberRequestDto.getPhoneNumber(),
                memberRequestDto.getEmail()
        );

        return memberRepository.save(member);
    }


}
