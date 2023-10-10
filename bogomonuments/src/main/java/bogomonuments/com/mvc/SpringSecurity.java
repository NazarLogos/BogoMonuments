package bogomonuments.com.mvc;


import bogomonuments.com.security.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
//@ComponentScan(basePackageClasses= CustomUserDetailsService.class)
public class SpringSecurity {
    private final UserDetailsService userDetailsService;

    public SpringSecurity(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // configure SecurityFilterChain
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/register/**").permitAll()
                .requestMatchers("/").permitAll()
                .requestMatchers("/monuments").permitAll()
                .requestMatchers("/monuments_single").permitAll()
                .requestMatchers("/monuments_double").permitAll()
                .requestMatchers("/monuments_children").permitAll()
                .requestMatchers("/contacts").permitAll()
                .requestMatchers("/home").permitAll()
                .requestMatchers("/monuments/{monumentId}/view").permitAll()
                .requestMatchers("/style/**").permitAll()
                .requestMatchers("/about").permitAll()
                .requestMatchers("/403").permitAll()
                .requestMatchers("/monuments/new").hasRole("ADMIN")
                .requestMatchers("/monuments/{monumentId}/edit").hasRole("ADMIN")
                .requestMatchers("/monuments/{monumentId}").hasRole("ADMIN")
                .requestMatchers("/monuments/{monumentId}/delete").hasRole("ADMIN")
                .requestMatchers("/user-photos/**").permitAll()
                .and()
                .formLogin(
                        form -> form
                                .loginPage("/login")
                                .loginProcessingUrl("/login")
                                .defaultSuccessUrl("/")
                                .permitAll()
                ).logout(
                        logout -> logout
                                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                                .permitAll()

                ).exceptionHandling((exception)-> exception.accessDeniedPage("/403"));
        return http.build();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
