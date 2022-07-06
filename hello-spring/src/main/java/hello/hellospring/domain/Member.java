package hello.hellospring.domain;
// entity: 데이터 저장 및 관리 테이블
// attribute: entity의 한 부분. column
// domain: 속성의 값, 타입, 제약사항등에 대한 값의 범위
public class Member {
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
