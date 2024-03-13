package com.toy.mall.user.domain;

import com.toy.mall.cart.domain.Cart;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@Table(indexes = @Index(name = "login_id_idx", columnList = "loginId", unique = true))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(unique = true)
    private String loginId;

    private String phoneNumber;

    @Embedded
    private Address address;

    @OneToOne(fetch = LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cart_id")
    private Cart cart;

    public User(String loginId, String phoneNumber, Address address) {
        this.loginId = loginId;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }

    public void changeAddress(Address address) {
        this.address = address;
    }
}
