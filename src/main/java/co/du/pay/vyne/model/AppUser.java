package co.du.pay.vyne.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AppUser {

    private String username;
    private String password;
    private String[] roles;

    PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    public UserDetails getUserDetails() {
        User.UserBuilder ub = User.builder()
                .username(username)
                .password(password)
                .roles(roles);
        return ub.build();
    }
}
