package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation
             .authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web
             .builders.HttpSecurity;
import org.springframework.security.config.annotation.web
                        .configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web
                        .configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private UserDetailsService userDetailsService;

  // tag::requireWriteScope[]
  // tag::enableResourceServer[]
  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
    // end::enableResourceServer[]
        .authorizeRequests()
  // end::requireWriteScope[]
      /*
        // tag::enableResourceServer[]
        // tag::requireWriteScope[]
       ...
        // end::requireWriteScope[]
        // end::enableResourceServer[]
       */
      
        .antMatchers(HttpMethod.OPTIONS).permitAll() // needed for Angular/CORS
        // tag::requireWriteScope[]
        .antMatchers(HttpMethod.POST, "/api/ingredients")
            .hasAuthority("SCOPE_writeIngredients")
        .antMatchers(HttpMethod.DELETE, "/api//ingredients")
            .hasAuthority("SCOPE_deleteIngredients")
        // end::requireWriteScope[]
        .antMatchers("/api//tacos", "/api//orders/**")
            .permitAll()
            //.access("hasRole('USER')")
        .antMatchers("/**").access("permitAll")
        // tag::enableResourceServer[]
        .and()
          .oauth2ResourceServer(oauth2 -> oauth2.jwt())
        // end::enableResourceServer[]          
       
        .httpBasic()
          .realmName("Taco Cloud")

      .and()
        .logout()
          .logoutSuccessUrl("/")

      .and()
        .csrf()
          .ignoringAntMatchers("/h2-console/**", "/api/**")

      // Allow pages to be loaded in frames from the same origin; needed for H2-Console
      .and()
        .headers()
          .frameOptions()
            .sameOrigin()
      ;
    
      /*
      // tag::enableResourceServer[]
      // tag::requireWriteScope[]
     ...
      // end::requireWriteScope[]
      // end::enableResourceServer[]
     */
// tag::requireWriteScope[]
// tag::enableResourceServer[]
  }
// end::enableResourceServer[]
// end::requireWriteScope[]

  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth)
      throws Exception {

    auth
      .userDetailsService(userDetailsService)
      .passwordEncoder(encoder());

  }
  
}
