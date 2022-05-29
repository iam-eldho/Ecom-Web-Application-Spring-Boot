/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecom.configuration;

import com.ecom.security.AccessTokenProcessingFilter;
import com.ecom.security.AccessTokenUserDetailsService;
import com.ecom.security.config.SecurityConfig;
import com.ecom.security.util.TokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.springframework.http.HttpMethod.OPTIONS;
import static org.springframework.http.HttpMethod.POST;
import static org.springframework.http.HttpMethod.PUT;
import static org.springframework.security.config.Customizer.withDefaults;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.NegatedRequestMatcher;

/**
 *
 * @author eldho
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter{

    public WebSecurityConfiguration() {
        super(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.cors(withDefaults())
                .requestMatcher(new NegatedRequestMatcher(new AntPathRequestMatcher("/error")))
                .addFilter(accessTokenProcessingFilter())
                .authenticationProvider(preAuthenticatedAuthenticationProvider())
                .exceptionHandling().and()
                .headers().and()
                .sessionManagement().sessionCreationPolicy(STATELESS).and()
                .securityContext().and()
                .anonymous().and()
                .authorizeRequests()
                .antMatchers(POST, "/login").anonymous()
                .anyRequest().authenticated();
    }

    @Bean
    protected AccessTokenUserDetailsService accessTokenUserDetailsService() {
        return new AccessTokenUserDetailsService();
    }

    @Bean
    protected PreAuthenticatedAuthenticationProvider preAuthenticatedAuthenticationProvider() {
        PreAuthenticatedAuthenticationProvider authProvider = new PreAuthenticatedAuthenticationProvider();
        authProvider.setPreAuthenticatedUserDetailsService(accessTokenUserDetailsService());
        return authProvider;
    }

    @Bean
    @Autowired
    protected AccessTokenProcessingFilter accessTokenProcessingFilter() throws Exception {
        AccessTokenProcessingFilter filter = new AccessTokenProcessingFilter();
        filter.setAuthenticationManager(authenticationManager());
        return filter;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    @ConfigurationProperties("app.security")
    public SecurityConfig securityConfig() {
        return new SecurityConfig();
    }

    @Bean
    @ConfigurationProperties("app.security.configuration")
    public TokenGenerator tokenGenerator(SecurityConfig securityConfig) {
        return new TokenGenerator(securityConfig.getTokenGeneratorPassword(), securityConfig.getTokenGeneratorSalt());
    }

}
