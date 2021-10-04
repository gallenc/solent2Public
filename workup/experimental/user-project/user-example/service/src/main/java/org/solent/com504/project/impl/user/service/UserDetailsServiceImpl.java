package org.solent.com504.project.impl.user.service;

import org.solent.com504.project.model.user.dto.Role;
import org.solent.com504.project.model.user.dto.User;
import org.solent.com504.project.impl.dao.user.springdata.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import org.solent.com504.project.model.party.dto.Party;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    final static Logger LOG = LogManager.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user==null) throw new UsernameNotFoundException("could not find username: "+username);

        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        // add roles
        for (Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
            LOG.debug("   added granted authority role to username " + username + " " + role.getName());
        }
        // add party authorities
        LOG.debug("   number of uuid granted authorities for username " + username + " = " + user.getParties().size());
        for (Party party : user.getParties()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(party.getUuid()));
            LOG.debug("   added granted authority uuid to username " + username + " " + party.getUuid());
        }

        boolean enabled = true;
        // set login enabled depending upon user enabled status
        if (user.getEnabled() == null || !user.getEnabled()) {
            enabled = false;
        }

        // User(java.lang.String username, java.lang.String password, 
        // boolean enabled, 
        // boolean accountNonExpired,
        // boolean credentialsNonExpired,
        // boolean accountNonLocked, 
        // java.util.Collection<? extends GrantedAuthority> authorities)
        org.springframework.security.core.userdetails.User userDetailsUser
                = new org.springframework.security.core.userdetails.User(
                        user.getUsername(),
                        user.getPassword(),
                        enabled,
                        true,
                        true,
                        true,
                        grantedAuthorities);

        return userDetailsUser;
    }
}
