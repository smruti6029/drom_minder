package com.dromminder.security;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.dromminder.model.CredentialMaster;
import com.dromminder.repository.CredentialMasterRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private CredentialMasterRepository credentialMasterRepository;

	@Override
	public UserDetails loadUserByUsername(String username) {
		org.springframework.security.core.userdetails.User user = null;
		try {
			Optional<CredentialMaster> credentialMasterOptional = credentialMasterRepository.findByEmail(username);

			if (credentialMasterOptional.isPresent() && credentialMasterOptional.get().getIsActive()) {
				CredentialMaster credentialMaster = credentialMasterOptional.get();
				user = new org.springframework.security.core.userdetails.User(credentialMaster.getEmail(),
						credentialMaster.getPassword(), new ArrayList<>());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	public Optional<CredentialMaster> getUserDetails() {
		try {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			Optional<CredentialMaster> master = credentialMasterRepository.findByEmail(auth.getName());
			return master;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

}
