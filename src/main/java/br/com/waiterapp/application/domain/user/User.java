package br.com.waiterapp.application.domain.user;

import br.com.waiterapp.application.domain.user.enums.UserOfficeStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="users")
public class User  implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date birthDate;
    private String email;
    private String password;
    @Enumerated(EnumType.ORDINAL)
    private UserOfficeStatus status;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this
                .getStatus()
                .getCurrentStatus() == 0
                ?
                List.of( new SimpleGrantedAuthority("ADMIN"), new SimpleGrantedAuthority("WAITER"))
                :
                List.of( new SimpleGrantedAuthority("ADMIN"));
    }

    @Override
    public String getUsername() {
        return this.email;
    }

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
