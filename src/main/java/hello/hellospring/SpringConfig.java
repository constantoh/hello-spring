package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

/**
 * Component Scan이 아닌 직접 스프링 빈 등록하는 config file
 * Service, Repository, Autowired 태그 제거
 * */
@Configuration
public class SpringConfig {
/*
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }
*/
/*
    private final EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }
*/
    private final MemberRepository memberRepository;


    /**
     * Spring Data Jpa가  jpaRepository를 상속받은 인터페이스가 있으면 그 구현체를 만들어서 주입해준다.
     * */
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService(){
//        return new MemberService(memberRepository());
        return new MemberService(memberRepository);
    }
/*
    @Bean
    public MemberRepository memberRepository(){
        return new JpaMemberRepository(em);


    }
*/
    /*@Bean
    public TimeTraceAop timeTraceAop(){
        return new TimeTraceAop();
    }*/

}
