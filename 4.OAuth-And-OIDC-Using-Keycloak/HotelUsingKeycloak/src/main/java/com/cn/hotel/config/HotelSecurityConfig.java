package com.cn.hotel.config;

//import com.cn.hotel.jwt.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2UserAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.*;
import java.util.stream.Collectors;

@Configuration
@EnableAutoConfiguration
@EnableMethodSecurity(prePostEnabled = true) //@EnableGlobalMethodSecurity() is deprecated
public class HotelSecurityConfig {

//    public final UserDetailsService userDetailsService; //5
//    public HotelSecurityConfig(UserDetailsService userDetailsService) {
//        this.userDetailsService = userDetailsService;
//    }

//    @Autowired
//    JwtAuthenticationFilter filter;
/** refactored code according to Spring3 */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf().disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/login").permitAll()
                        .anyRequest()
                        .authenticated())
//                .requestMatchers("/user/register","/auth/login").permitAll() //antMatchers() removed in spring security version 6.0, this is replacement
//                .anyRequest()
//                .authenticated()
//                .and()
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .defaultSuccessUrl("/hotel/getAll")); //once the authentication is successful land the user to this url
//                .formLogin()
//                .loginPage("/login").permitAll();
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); //used in realtime applications, you can use .httpBasic() authentication mechanism

//        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class); //mentioning to use our JwtAuthenticationFilter
        return  http.build();
    }

    @Bean
    public GrantedAuthoritiesMapper useAuthoritiesMapper(){
        return (authorities -> {
            Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
            authorities.forEach(authority -> {
                if(OAuth2UserAuthority.class.isInstance(authority)) { //security check if authority is a instance of type OAuth2UserAuthority & also since we have to typecast it
                    OAuth2UserAuthority oAuth2UserAuthority = (OAuth2UserAuthority) authority;
                    Map<String, Object> userAttributesMap = oAuth2UserAuthority.getAttributes();

                    if(userAttributesMap.containsKey("realm_access")) {
                        Map<String, Object> realmAccessMap = (Map<String, Object>) userAttributesMap.get("realm_access");
                        if(realmAccessMap.containsKey("roles")) {
                            List<String> roles =(List<String>) realmAccessMap.get("roles");
                            mappedAuthorities.addAll(roles.stream()
                                    .map(role ->new SimpleGrantedAuthority("ROLE_"+role.toUpperCase()))
                                    .collect(Collectors.toSet())
                            );
                        }
                    }
                }
            });
            return mappedAuthorities;
        });
    }

    /**
     * 1. Creating the bean GrantedAuthorityMapper
     * 2. Get realm access from the token given by keycloak
     * 3. if(roles) -> get roles
     * 4. get all the roles into a list, then set inside a granted authority container
     * 5. return the granted authority container
     * @return ()
     */
    @Bean
    public JwtDecoder jwtDecoder() {
        return JwtDecoders.fromIssuerLocation("https://lemur-0.cloud-iam.com/auth/realms/abhee");
    }

//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception {
//        return builder.getAuthenticationManager();
//    }

//    @Bean
//    public UserDetailsService users() {
//        UserDetails user1 = User.builder()
//                .username("steve")
//                .password(passwordEncoder().encode("nopassword"))
//                .roles("ADMIN")
//                .build();
//        UserDetails user2 = User.builder()
//                .username("tony")
//                .password(passwordEncoder().encode("password"))
//                .roles("NORMAL")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, user2);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
