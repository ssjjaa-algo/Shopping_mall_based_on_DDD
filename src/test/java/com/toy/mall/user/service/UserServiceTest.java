package com.toy.mall.user.service;

import com.toy.mall.user.domain.Address;
import com.toy.mall.user.domain.User;
import com.toy.mall.user.repository.UserRepository;
import com.toy.mall.user.service.request.UserServiceRegistRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("유저 등록")
    public void regist() {
        //given
        String loginId = "testId";
        String phoneNumber = "010-1234-5678";
        String city = "서울시";
        String street = "강남구";
        String zipcode = "강남대로 1234길";
        String detailedAddress = "강남아파트 999동 999호";
        UserServiceRegistRequest request = makeUserServiceRegistRequest(
                loginId, phoneNumber,
                city, street, zipcode, detailedAddress
        );
        //when
        userService.regist(request);

        //then

        User user = userRepository.findByLogin("testId").orElseThrow();
        Assertions.assertThat(user.getLoginId()).isEqualTo("testId");

    }



    public UserServiceRegistRequest makeUserServiceRegistRequest
            (String loginId, String phoneNumber, String city, String street, String zipcode,
             String detailedAddress) {

        return new UserServiceRegistRequest(
                loginId, phoneNumber,
                new Address(city, street, zipcode, detailedAddress));

    }

}