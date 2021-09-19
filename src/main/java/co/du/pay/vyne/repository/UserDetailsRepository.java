package co.du.pay.vyne.repository;

import co.du.pay.vyne.configuration.UserDetailsConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDetailsRepository {

    @Autowired
    private UserDetailsConfig userDetailsConfig;

    public List<UserDetails> getUsers() {
        return userDetailsConfig.getUserDetails();
    }
}
