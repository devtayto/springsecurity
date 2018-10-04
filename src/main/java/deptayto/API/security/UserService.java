package deptayto.API.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import deptayto.API.entity.Users;
import deptayto.API.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user = userRepository.findOneByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User '" + username + "' not found");
		}

		return org.springframework.security.core.userdetails.User.withUsername(username).password(user.getPassword())
				.authorities(Collections.emptyList()).accountExpired(false).accountLocked(false)
				.credentialsExpired(false).disabled(false).build();
	}
	
	public Users updateToken(Users users)
	{
		return userRepository.save(users);
	}

} 
