package hello.hellospring.controller;

public class MemberForm {
    private String name; // createMemberForm.html의 name과 일치

    public String getName() { // alt+insert: generate - getter and setter
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
