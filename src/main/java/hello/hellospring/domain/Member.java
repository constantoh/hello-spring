package hello.hellospring.domain;

import javax.persistence.*;

/**
 * Domain -> 비즈니스 도메인 객체
 * */
/**
 * Entity -> mapping -> jpa가 관리하는 엔티티
 * */
@Entity
public class Member {

    /**
     * Id -> pk mapping
     * GeneratedValue - identity -> db가 자동으로 키 채번해주는 전략
     * */
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * column -> db의 컬럼명
     * */
//    @Column(name = "username")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
