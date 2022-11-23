package com.kh.RestApi2.controller;

import com.kh.RestApi2.service.MemberService;
import com.kh.RestApi2.vo.MemberDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController // view로 날라감
// mvc패턴  : controller - 요청에 대한 응답 view - frontend
public class MemberController {
    // Service 로직 연결
    private MemberService memberService;
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    @GetMapping("/GetMember/list")
    // 정보와 status값(Jpa에 있음)을 날림
    // memberList() Spring 서블릿에서 불러줌
    public ResponseEntity<List<MemberDTO>> memberList() {
        List<MemberDTO> list = memberService.getMemberList();
        // 값을 전달하기 위해서 객체로 만듦
        return new ResponseEntity(list, HttpStatus.OK);
    }
    // userId로 회원 조회 하기
    @GetMapping("/GetMemeber")
    public ResponseEntity<List<MemberDTO>> memberList(@RequestParam String userId){
        MemberDTO memberDTO = memberService.getMemberList(userId);
        return new ResponseEntity(memberDTO, HttpStatus.OK); // 검증 필요
    }
    // 회원 가입 만들기
    @PostMapping("/RegMember")
    // 결과를 t/f로 받기 위해서 Boolean으로 받음
    // body는 외부에서 보이지 않음
    public ResponseEntity<Boolean> registerMember(@RequestBody Map<String, String> regData){
        String userId  = regData.get("user"); // 날려줄 정보
        String pwd = regData.get("pwd");
        String name = regData.get("name");
        String mail = regData.get("mail");
        boolean result = memberService.regMember(userId, pwd, name, mail);
        if(result){
            return new ResponseEntity(true, HttpStatus.OK);
        }else{
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }
    // 로그인 체크
    @PostMapping("/Login")
    public ResponseEntity<Boolean> memberLogin(@RequestBody Map<String, String> loginData){
        String user = loginData.get("user"); // 로그인 키 값
        String pwd = loginData.get("pwd");
        boolean result = memberService.loginCheck(user, pwd);
        if(result) {
            return new ResponseEntity(true, HttpStatus.OK);
        }else{
            return new ResponseEntity(false,HttpStatus.BAD_REQUEST);
        }
    }
    // 회원 가입
    @PostMapping("/reg-member")
    public ResponseEntity<Map<String, String>> memberRegister(@RequestBody Map<String, String> regData) {
        String getUserId = regData.get("user");
        String getPwd = regData.get("pwd");
        String getName = regData.get("name");
        String getMail = regData.get("mail");
        boolean result = memberService.regMember(getUserId, getPwd, getName, getMail);
        if(result) {
            return new ResponseEntity(true, HttpStatus.OK);
        } else {
            return new ResponseEntity(false, HttpStatus.BAD_REQUEST);
        }
    }
}
