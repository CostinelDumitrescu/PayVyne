package co.du.pay.vyne.configuration;

import co.du.pay.vyne.model.AppUser;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "auth")
public class UserDetailsConfig {

    @Getter
    @Setter
    private List<AppUser> users = new ArrayList<>();

    public List<UserDetails> getUserDetails() {
        return users.stream()
                .map(AppUser::getUserDetails)
                .collect(Collectors.toList());
    }
}
