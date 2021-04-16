package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * Controller는 spring이 자동으로 관리하는 대상인데
 * 생성할 때 Autowired로 인해 MemberService객체가 필요한데 이는 현재 자동으로 등록되어 있지 않다.
 *  -> 1. 컴포넌트 스캔과 자동 의존관계 설정
 *  -> 2. 자바코드로 직접 스프링 빈 등록
 * */
@Controller
public class MemberController {

    private final MemberService memberService;

    /**
     * Autowired는 스프링이 자동으로 주입
     * */
//    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();

        model.addAttribute("members", members);

        return "members/memberList";
    }
}
