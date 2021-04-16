package hello.hellospring.repository;


import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

/**
 * Repository -> DB 접근, 도메인의 객체를 DB에 저장, 조회
 * */
public interface MemberRepository {

    // 멤버를 저장한다.
    Member save(Member member);

    // id로 멤버를 찾는다.
    Optional<Member> findById(Long id);

    // name으로 멤버를 찾는다.
    Optional<Member> findByName(String name);

    // 전체 멤버를 조회한다.
    List<Member> findAll();
}
