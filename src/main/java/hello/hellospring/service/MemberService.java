package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Transactional -> jpa를 위한 transaction
 * */
//@Service
@Transactional
public class MemberService {

    // MemberService에서 공통으로 쓸 repository
    //
    // 1. 기존에는 그냥 인스턴스 객체를 MemberService에서 생성
    // private final MemberRepository memberRepository =  new MemoryMemberRepository();

    // 2. 생성자DI로 변경
    private final MemberRepository memberRepository;

//    @Autowired
    public MemberService(MemberRepository memberRepository){
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * */
    public Long join(Member member){

        /* 요구사항 - 이름이 같은 회원은 있을 수 없다.*/
        validateDuplicateMember(member);

        return memberRepository.save(member).getId();
    }
    // 중복제거 로직
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 회원 조회(one or all)
     * */
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }
}
