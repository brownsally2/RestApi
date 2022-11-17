package com.kh.RestApi2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin (origins = "http://localhost:3000")
//@RequestMapping("/api/v1/post-api")
public class PostController {
    @RequestMapping(value = "/domain", method = RequestMethod.POST)
    public String postEx() {
        return "Hello Post API";
    }
    // RequestBody : post 형식의 데이터를 Body에서 추출
    @PostMapping("/member")
    public String postMember(@RequestBody Map<String, Object> postData) {
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        return sb.toString();
    }
    @PostMapping("/Login2")
    public ResponseEntity<Map<String, String>> memberLogin(@RequestBody Map<String, String> postData) {
        String getId = postData.get("id");
        String getPwd = postData.get("pwd");

        Map<String, String> map = new HashMap<>();
        map.put("result", "OK");
        return new ResponseEntity(map, HttpStatus.OK);
    }
}
