package com.hngy.lms.entity;

import com.hngy.lms.entity.abstractEntity.IdentityEntity;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Proxy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
@Entity
@Table
//@NoArgsConstructor
public class User extends IdentityEntity implements UserDetails {
    @Column(unique = true,nullable = false)
//    @NotBlank(message = "name.not.null")
    private String username;

    @Column(nullable = false)
//    @NotBlank(message = "password.not.null")
//    @Length(min = 6,message = "password.length")
    private String password;

//    @Pattern(regexp = "0?(13|14|15|18|17)[0-9]{9}",message = "number.format")
    @Column(unique = true,nullable = false)
    private String number;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
//    @NotNull(message = "role.not.null")
    private Role role;

    @OneToOne
    @JoinColumn(name = "address_id")
//    @Valid
    private Address address;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    private List<Clothing> clothing;


    //Returns the authorities granted to the user. Cannot return null.
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));//返回一个 单个元素的不可变集合
    }

    /**
     * Indicates whether the user's account has expired. An expired account cannot be authenticated.
     * @return true if the user's account is valid (ie non-expired), false if no longer valid (ie expired)
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
