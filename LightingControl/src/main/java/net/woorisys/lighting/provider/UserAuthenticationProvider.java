package net.woorisys.lighting.provider;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import net.woorisys.lighting.domain.db.Admin;
import net.woorisys.lighting.service.AdminService;

@Component
public class UserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private AdminService adminService;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = (String) authentication.getPrincipal();
        String password = (String) authentication.getCredentials();
        
        Admin user = adminService.login(username, password);
        if (user != null) {
        	Collection<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        	roles.add(new SimpleGrantedAuthority("ROLE_USER"));
  
            return new UsernamePasswordAuthenticationToken(user, password, roles);   
        } else {
            throw new BadCredentialsException("Login Error !!");
        }
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
