package com.sanmina.basictemplate.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //@formatter:off
        http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);
        http
            .cors()
            .configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
            .and()
            .httpBasic().disable()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) 
            .and()
                .authorizeRequests() 
                 .antMatchers("/",
                        "/**/*.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js",
                        "/**/*.ttf",
                        "/**/*.woff2"
                        )
                        .permitAll()
                .antMatchers("/Auth/User/").permitAll()
                .antMatchers("/Get/Test/").authenticated() // Modo para validar unicamente que el token y la sesión sean correctos
                // .antMatchers("/Get/Test/").hasAnyAuthority("Admin","Other") // Metodo para limitar el acceso a permisos especificos declarados en el app manager
                .anyRequest().authenticated()
            .and()
            .apply(new JwtConfigurer(jwtTokenProvider));
        //@formatter:on
    }

}