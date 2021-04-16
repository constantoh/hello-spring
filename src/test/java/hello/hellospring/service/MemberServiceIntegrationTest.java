package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @SpringBootTest, @Transactional
 * SpringBootTest   ->
 * Transactional    ->
 * */
@SpringBootTest
@Transactional
public class MemberServiceIntegrationTest {

    // 1.
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
/*
    @BeforeEach
    public void BeforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }

    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }
*/

    @Test
    @Commit
    public void 회원가입(){
        //given
        Member member = new Member();
        member.setName("오상수3");

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
/*
    @Test
    public void 회원검색(){
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member insertResult1 = memberRepository.save(member1);

//        Member member2 = new Member();
//        member2.setName("spring2");
//        Member insertResult2 = memberRepository.save(member2);

        //when
        Member searchResult = memberService.findOne(member1.getId()).get();

        //then
        assertThat(searchResult.getName()).isEqualTo(insertResult1.getName());
    }

    @Test
    public void 전체회원검색(){
        //given
        Member member1 = new Member();
        member1.setName("spring");
        Member insertResult1 = memberRepository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        Member insertResult2 = memberRepository.save(member2);

        //when
        List<Member> members = memberService.findMembers();

        //then
        assertThat(members.size()).isEqualTo(2);
    }*/
}
