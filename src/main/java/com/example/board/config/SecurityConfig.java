package com.example.board.config;

import com.example.board.config.auth.PrincipalDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration//Bean 등록
@EnableWebSecurity//Security filter 추가
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final AuthenticationFailureHandler customFailureHandler;
    private static final String[] PERMIT_URL_ARRAY = {
            "/", "/auth/**", "/js/**", "/css/**", "/image/**", "/dummy/**",
            /* h2 */
            "/h2-console/**",
            /* swagger v2 */
            "/v2/api-docs",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            /* swagger v3 */
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/actuator/**"
    };
    private final PrincipalDetailService principalDetailService;

    public SecurityConfig(AuthenticationFailureHandler customFailureHandler, PrincipalDetailService principalDetailService) {
        this.customFailureHandler = customFailureHandler;
        this.principalDetailService = principalDetailService;
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 시큐리티가 대신 로그인 해주는데 비밀번호 해쉬 비교를 위한 로직
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(principalDetailService).passwordEncoder(bCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin();
        http.csrf().disable()//csrf token disable -> form tag를 이용해서 요청이 아닌 ajax call에 토큰이 없어서 block당함.
                .authorizeRequests()
                .antMatchers(PERMIT_URL_ARRAY)
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/loginForm")
                .loginProcessingUrl("/auth/loginProc")
                .defaultSuccessUrl("/")
                .failureHandler(customFailureHandler) // 로그인 실패 핸들러
                .and()
                .logout()
                .and()
                .userDetailsService(principalDetailService);
        //intercept by spring security
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
