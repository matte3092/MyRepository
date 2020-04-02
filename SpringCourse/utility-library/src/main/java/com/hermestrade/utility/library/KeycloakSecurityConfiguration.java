package com.hermestrade.utility.library;

import javax.servlet.http.HttpServletRequest;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.representations.AccessToken;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * The Class SummerConfiguration.
 */
@Configuration
@EnableTransactionManagement
@EnableJpaAuditing
public class KeycloakSecurityConfiguration {

	/**
	 * Auditor aware.
	 *
	 * @return the auditor aware
	 */
	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}

	/**
	 * Gets the access token.
	 *
	 * @return the access token
	 */
	@ConditionalOnProperty(name = "keycloak.enabled", havingValue = "true", matchIfMissing = false)
	@SuppressWarnings("rawtypes")
	@Bean
	@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public AccessToken getAccessToken() {
		return ((KeycloakPrincipal) getRequest().getUserPrincipal()).getKeycloakSecurityContext().getToken();
	}

	/**
	 * Gets the keycloak security context.
	 *
	 * @return the keycloak security context
	 */
	@ConditionalOnProperty(name = "keycloak.enabled", havingValue = "true", matchIfMissing = false)
	@SuppressWarnings("rawtypes")
	@Bean
	@Scope(scopeName = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
	public KeycloakSecurityContext getKeycloakSecurityContext() {
		return ((KeycloakPrincipal) getRequest().getUserPrincipal()).getKeycloakSecurityContext();
	}

	/**
	 * Gets the request.
	 *
	 * @return the request
	 */
	@ConditionalOnProperty(name = "keycloak.enabled", havingValue = "true", matchIfMissing = false)
	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
	}

}
