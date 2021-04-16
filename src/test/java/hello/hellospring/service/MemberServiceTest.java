package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * cmd shift t -> TEST 파일 자동생성
 * */
public class MemberServiceTest {

    // 1.
    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void BeforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }


    @Test
    public void 회원가입(){
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member result = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(result.getName());
    }
    @Test
    public void 중복회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join(member1);

        // 1. try-catch
/*
        try{
            memberService.join(member2);
            fail();
        }catch (IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
        }
*/
        // 2. assertThrows
//        assertThrows(NullPointerException.class, () -> memberService.join(member2));
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        //then
    }

    @Test
    public void 회원검색(){
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member insertResult1 = memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring");
        Member insertResult2 = memberRepository.save(member2);

        //when
        Member searchResult = memberService.findOne(member2.getId()).get();

        //then
        assertThat(searchResult).isEqualTo(insertResult2);
    }

    @Test
    public void 전체회원검색(){
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member insertResult1 = memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring");
        Member insertResult2 = memberRepository.save(member2);

        //when
        List<Member> members = memberService.findMembers();

        //then
        assertThat(members.size()).isEqualTo(2);
    }
}
