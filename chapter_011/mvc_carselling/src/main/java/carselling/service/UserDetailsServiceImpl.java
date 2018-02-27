package carselling.service;

import carselling.logic.DaoUser;
import carselling.model.Role;
import carselling.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.*;

class UserDetailsServiceImpl implements UserDetailsService {

    DaoUser users;

    public void setDaoUser(DaoUser daoUser) {
        this.users = daoUser;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

        User user = users.getByLogin(login);
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
