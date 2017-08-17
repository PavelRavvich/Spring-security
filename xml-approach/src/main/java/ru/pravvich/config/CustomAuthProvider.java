package ru.pravvich.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.pravvich.model.Role;
import ru.pravvich.model.User;
import ru.pravvich.repository.RoleRepository;
import ru.pravvich.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : Pavel Ravvich.
 * Created : 16.08.17.
 * <p>
 * CustomAuthProvider
 */
@Service("provider")
public class CustomAuthProvider implements AuthenticationProvider {

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private UserRepository userRepo;

    @Override
    @Transactional
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        final String username = authentication.getName();
        final String password = authentication.getCredentials().toString();

        final User user = userRepo
                .findDistinctFirstByUsernameAndPassword(username, password);

        if (user == null) return null;

        final List<Role> roles = roleRepo.findByUsername(username);

        final List<GrantedAuthority> grantedAut = new ArrayList<>();

        for (Role role : roles) {
            grantedAut.add(new SimpleGrantedAuthority(role.getRole()));
        }

        return new UsernamePasswordAuthenticationToken(
                username, password, grantedAut);
    }

    @Override
    public boolean supports(Class<?> auth) {
        return auth.equals(UsernamePasswordAuthenticationToken.class);
    }
}
