package com.eazybytes.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf) -> csrf.ignoringRequestMatchers("/saveMsg").ignoringRequestMatchers("/public/**")
                .ignoringRequestMatchers("/api/**").ignoringRequestMatchers("/data-api/**"))
                .authorizeHttpRequests((requests) -> requests.requestMatchers("/dashboard").authenticated()
                    .requestMatchers("/displayMessages/**").hasRole("ADMIN")
                    .requestMatchers("/closeMsg/**").hasRole("ADMIN")
                    .requestMatchers("/admin/**").hasRole("ADMIN")
                    .requestMatchers("/lecturer/courses").hasRole("LECTURER")
                    .requestMatchers("/lecturer/students").hasRole("LECTURER")
                    .requestMatchers("/lecturer/uploadMaterial").hasRole("LECTURER")
                    .requestMatchers("/lecturer/deleteMaterial").hasRole("LECTURER")
                    .requestMatchers("/api/**").authenticated()
                    .requestMatchers("/data-api/**").authenticated()
                    .requestMatchers("/displayProfile").authenticated()
                    .requestMatchers("/updateProfile").authenticated()
                    .requestMatchers("/student/**").hasRole("STUDENT")
                        .requestMatchers("/", "/home").permitAll()
                    .requestMatchers("/holidays/**").permitAll()
                    .requestMatchers("/contact").permitAll()
                    .requestMatchers("/saveMsg").permitAll()
                    .requestMatchers("/courses").permitAll()
                    .requestMatchers("/about").permitAll()
                    .requestMatchers("/assets/**").permitAll()
                    .requestMatchers("/login").permitAll()
                    .requestMatchers("/logout").permitAll()
                    .requestMatchers("/public/**").permitAll())
                .formLogin(loginConfigurer -> loginConfigurer.loginPage("/login")
                        .defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
                .logout(logoutConfigurer -> logoutConfigurer.logoutSuccessUrl("/login?logout=true")
                        .invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
