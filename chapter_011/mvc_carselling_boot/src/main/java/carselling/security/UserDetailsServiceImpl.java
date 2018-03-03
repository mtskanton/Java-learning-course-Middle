package carselling.security;

import carselling.logic.RepositoryUser;
import carselling.model.Role;
import carselling.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

@Service
class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private RepositoryUser users;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = users.findByLogin(login);

        Set<Role> userRoles = user.getRoles();

        Set<GrantedAuthority> roles = new HashSet<>();

        for (Role role : userRoles) {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                roles);
    }
}
