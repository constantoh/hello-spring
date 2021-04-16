package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    /**
     * 각 테스트는 독립적이기에, 하나의 테스트가 끝났을 때 환경을 초기화해주는 작업이 필요하다.
     */
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    /**
     * @Test태그로 test파일을 구성하고, 메소드명은 아무거나 넣어도 된다.
     */
    @Test
    public void 저장(){
        //given
        Member member = new Member();
        member.setName("spring1");

        //when
        repository.save(member);

        //then
        Member result = repository.findByName(member.getName()).get();
        assertThat(result).isEqualTo(member);
    }
    @Test
    public void 이름으로찾기(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        Member result = repository.findByName("spring1").get();

        //then
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void 전체찾기(){
        //given
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
