package com.kh.RestApi2.controller;

import com.kh.RestApi2.service.MemberService;
import com.kh.RestApi2.vo.MemberVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@CrossOrigin (origins = "http://localhost:3000")
@RestController
public class MemberController {
    //memberController 가져옴
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass().getSimpleName());
    // Service 로직 연결
    private MemberService memberService;
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    @GetMapping("/GetMember")
    // cmd형식으로 받겠다
    public ResponseEntity <List<MemberVO>> memberList(@RequestParam String id) {

        LOGGER.info("회원 조회 아이디 : " + id);
        List<MemberVO> list =  memberService.getMemberList(id);

       return new ResponseEntity<>(list, HttpStatus.OK);
    }
    @PostMapping("/Login")
    // 키와 키로 날림(이미 json형식으로 넘어옴) RestAPI 적용만 했음
    public Map<String, String> memberLogin(@RequestBody Map<String, String> loginData){
        // 키 값을 넣어줌
        String getId = loginData.get("id");
        String getPwd = loginData.get("pwd");
        LOGGER.info("LoginController Call !!!!!!!");


        Map<String, String> map = new HashMap<>();
        map.put("result", "OK");

        return map;
    }
    //get, post 방식 혼용해서 사용하기
    @PostMapping("/MemberCheck")
    public ResponseEntity<Map<String, String>> memberCheck(@RequestBody Map<String, String> chkData){
        String getId = chkData.get("id");

        Map<String, String> map = new HashMap<>();
            map.put("result", "OK");
            return new ResponseEntity(map, HttpStatus.OK);

    }

    //값이 노출되면 안되기 때문, 키와 값을
    @PostMapping("/RegMember")
    public ResponseEntity<Map<String, String>> memberRegister(@RequestBody Map<String, String> regData){
        String getId = regData.get("id");
        String getPwd = regData.get("pwd");
        String getName = regData.get("name");
        String getMail = regData.get("mail");

        Map<String, String> map = new HashMap<>();

            map.put("result", "OK");
            return new ResponseEntity(map, HttpStatus.OK);
        }
    }


