package com.toy.mall.user.controller;

import com.toy.mall.user.controller.request.UserAddressRequest;
import com.toy.mall.user.controller.request.UserRegistRequest;
import com.toy.mall.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    @PostMapping("/regist")
    public ResponseEntity<?> regist(@Valid UserRegistRequest userRegistRequest) {

        userService.regist(userRegistRequest.toServiceRequest());

        return ResponseEntity.ok("회원가입 완료");
    }

    @PostMapping("/address")
    public ResponseEntity<?> change(@Valid UserAddressRequest userAddressRequest) {

        userService.changeAddress(userAddressRequest.toServiceRequest());

        return ResponseEntity.ok("주소 수정 완료");
    }
}
