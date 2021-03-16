package com.agiklo.HeathProject.model;

import com.agiklo.HeathProject.model.enums.USER_ROLE;
import com.agiklo.HeathProject.model.workout.Workout;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class ApplicationUser implements UserDetails {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "sequence_generator"
    )
    @SequenceGenerator(
            name="sequence_generator",
            sequenceName = "user_sequence",
            allocationSize = 1,
            initialValue = 100
    )
    @Column(name = "user_id")
    private Long id;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    @JoinColumn(name = "USER_ROLE")
    private USER_ROLE role;

    @Column(name = "IS_LOCKED")
    private Boolean isLocked = false;

    @Column(name = "IS_ENABLED")
    private Boolean isEnabled = false;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<Workout> workout;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority(role.name());
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !isLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }
}
