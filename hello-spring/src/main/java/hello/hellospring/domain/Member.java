package hello.hellospring.domain;
// entity: 데이터 저장 및 관리 테이블
// attribute: entity의 한 부분. column
// domain: 속성의 값, 타입, 제약사항등에 대한 값의 범위

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

// jpa 사용 위해서 엔티티와 매핑 해야 함.
@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // db가 알아서 id 생성
    private Long id; // 시스템이 저장하는 아이디
    private String name; // 이름

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
