package com.example.docdelivery;

import com.example.docdelivery.service.UserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.security.auth.login.AccountException;

import static jakarta.servlet.DispatcherType.ERROR;
import static jakarta.servlet.DispatcherType.FORWARD;

@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final UserDetailService userDetailService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws AccountException {
        try {
            return config.getAuthenticationManager();
        } catch (Exception e) {
            throw new AccountException("AuthenticationManager not configured: " + e.getMessage());
        }
    }

    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);  // для получения пользователя из хранилища
        provider.setPasswordEncoder(passwordEncoder()); // для работы с паролями
        return provider;
    }

    /*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/account/registration", "/account/login")// запросы
                        .permitAll()// разрешены всем
                        .dispatcherTypeMatchers(FORWARD, ERROR).permitAll()// для отображения html
                        .requestMatchers("/task") // запросы
                        .hasAnyRole("USER", "SUPER_USER") // разрешены только пользователям с указанными ролями
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // запросы необходимо пропускать через фильтр
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }*/

    // .requestMatchers("/task")
    // .requestMatchers(HttpMethod.GET, "/task")
    // .requestMatchers(HttpMethod.GET)
    // .hasAnyRole("USER", "SUPER_USER") // .hasRole("ADMIN")
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity.csrf(AbstractHttpConfigurer::disable)

                .authenticationProvider(authenticationProvider())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/account/registration", "/account/login")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(form -> form.permitAll())

                /*.formLogin(form -> form
                        .usernameParameter("application_user_username") // значение атрибута name в html форме
                        .passwordParameter("application_user_password") // значение атрибута name в html форме
                        .loginPage("/account/login") // форма доступна по адресу
                        .loginProcessingUrl("/account/login") // обработчик, значение атрибута action тега form
                        .failureUrl("/account/login?failed") // ошибка авторизации
                        .defaultSuccessUrl("/account") // перенаправление после успешной авторизации
                        .permitAll())
                .logout(logout -> logout.logoutUrl("/account/logout") // <a th:href="@{/account/logout}">Выйти</a>
                        .logoutSuccessUrl("/account/login") // перенаправление после /account/logout
                        .permitAll())  // [ВЫЙТИ] /account/logout*/
                .build();
    }

}

