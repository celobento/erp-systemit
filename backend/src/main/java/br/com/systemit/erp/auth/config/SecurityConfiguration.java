package br.com.systemit.erp.auth.config;

import br.com.systemit.erp.auth.security.CustomUserDetailsService;
import br.com.systemit.erp.auth.security.JwtCustomAuthenticationFilter;
import br.com.systemit.erp.auth.security.LoginSocialSucessHandler;
import br.com.systemit.erp.auth.service.UsuarioAutenticacaoService;
import br.com.systemit.erp.auth.service.UsuarioService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Resource server
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity( securedEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http,
            LoginSocialSucessHandler sucessHandler,
            JwtCustomAuthenticationFilter jwtCustomAuthenticationFilter
    )  throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .formLogin(Customizer.withDefaults())
                //.formLogin(configurer -> configurer.loginPage("/login.html"))
                .httpBasic(Customizer.withDefaults())
                .authorizeHttpRequests(authorize -> {
                    authorize.requestMatchers("/auth").permitAll();
                    authorize.requestMatchers(HttpMethod.GET,"/usuarios/**").hasAnyRole("adm");
                    //authorize.requestMatchers(HttpMethod.GET,"/perfis/**").hasAnyRole("ADMIN");
                    //authorize.requestMatchers(HttpMethod.POST,"/perfis/**").hasRole("ADMIN");
                    authorize.anyRequest().permitAll();
                })
                .oauth2Login(
                        //Customizer.withDefaults()
                        oauth2 -> {
                            oauth2.successHandler(sucessHandler);
                        })
                .oauth2ResourceServer(oauth2RS -> oauth2RS.jwt(Customizer.withDefaults()))
                // adicionando para pergar dados do token no filter
                .addFilterAfter(jwtCustomAuthenticationFilter, BearerTokenAuthenticationFilter.class)
                .build();
    }

    // configura, no token jwt, o prefixo scope
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        var authotitiesConverter = new JwtGrantedAuthoritiesConverter();
        authotitiesConverter.setAuthorityPrefix("");
        var converter = new JwtAuthenticationConverter();
        converter.setJwtGrantedAuthoritiesConverter(authotitiesConverter);
        return converter;
    }


    @Bean
    public WebSecurityCustomizer webSecutiryCustomizer() {
        return web -> {
             web.ignoring().requestMatchers(
              "/v2/api-docs/**",
              "/v3/api-docs/**",
              "/swagger-resources/**",
              "/swagger-ui.html",
              "/swagger-ui/**",
              "/webjars/**",
              "/actuator/**"
            );
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(10);
    }

    // authentication
//    @Bean
//    public UserDetailsService userDetailService(UsuarioService usuarioService, UsuarioAutenticacaoService usuarioAutenticacaoService) {
//       return new CustomUserDetailsService(usuarioService, usuarioAutenticacaoService);
//    }

    /*para uso com bd em memoria para facilitar alguns testes
    @Bean
    public UserDetailsService userDetailService(PasswordEncoder encoder) {
        UserDetails user1 = User.builder()
                .username("user")
                .password(encoder.encode("123"))
                .roles("USER")
                .build();
        UserDetails user2 = User.builder()
                .username("admin")
                .password(encoder.encode("123"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2);
    }*/

    // removing prefix ROLE_
    @Bean
    public GrantedAuthorityDefaults grantedAuthorityDefaults() {
        return new GrantedAuthorityDefaults("");
    }
}
