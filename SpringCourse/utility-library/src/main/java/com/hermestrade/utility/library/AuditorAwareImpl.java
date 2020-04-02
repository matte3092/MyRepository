package com.hermestrade.utility.library;

import java.util.Optional;

import org.keycloak.representations.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;

/**
 * The Class AuditorAwareImpl.
 */
public class AuditorAwareImpl implements AuditorAware<String> {

	/** The access token. */
	@Autowired(required = false)
	AccessToken accessToken;

	/* (non-Javadoc)
	 * @see org.springframework.data.domain.AuditorAware#getCurrentAuditor()
	 */
	@Override
	public Optional<String> getCurrentAuditor() {
		try {
			return Optional.ofNullable(accessToken.getPreferredUsername());
		} catch (Exception e) {
			return Optional.empty();
		}

	}

}
