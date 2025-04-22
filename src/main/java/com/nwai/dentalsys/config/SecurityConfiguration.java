package com.nwai.dentalsys.config;

import com.nwai.dentalsys.user.Permission;
import com.nwai.dentalsys.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final JwtFilter jwtFilter;
    private final AuthenticationProvider authenticationProvider;

    //to permit register and login form for all user
    //whilelist for URL
    @Bean
    public SecurityFilterChain securyityFilterChain(HttpSecurity http) throws Exception {
        http.
                csrf(CsrfConfigurer::disable) //disable csrf
                                .authorizeHttpRequests(
                                        authorizeRequests -> authorizeRequests
                                                .requestMatchers("/adsweb/api/v1/auth/*").permitAll()
                                                .requestMatchers("/adsweb/api/v1/patients", "/adsweb/api/v1/patients/**")
                                                .hasAnyRole(Role.ADMIN.name(), Role.MEMBER.name())
                                                .requestMatchers("/adsweb/api/v1/appointments", "/adsweb/api/v1/appointments/**")
                                                .hasAnyRole(Role.ADMIN.name(), Role.MEMBER.name())
                                                .requestMatchers("/adsweb/api/v1/addresses", "/adsweb/api/v1/addresses/**")
                                                .hasAnyRole(Role.ADMIN.name(), Role.MEMBER.name())
//                                                .requestMatchers("/api/v1/admin/**")
//                                                .hasAnyRole(Role.ADMIN.name())
//                                                .requestMatchers("/api/v1/management/admin-write")
//                                                .hasAuthority(Permission.ADMIN_WRITE.getPermission())
                                                .anyRequest().authenticated()
                                )
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider)
                .sessionManagement(
                        sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
        ;
        return http.build();
    }
}
