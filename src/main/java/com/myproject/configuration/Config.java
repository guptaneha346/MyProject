package com.myproject.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private CustomLoginSuccessHandler sucessHandler;

    @Autowired
    private DataSource dataSource;




    private final String USERS_QUERY = "select email, password, '1' as enabled from usersignup where email=? and active='1'";
    private final String ROLES_QUERY = "select u.email, r.role from usersignup u inner join user_role ur on(u.id=ur.user_id) inner join role r on(ur.role_id=r.role_id) where u.email=?";


    private final String USER_QUERY = "select email, password, '2' as enabled from employeesignup where email=? and active='2'";
    private final String ROLE_QUERY = "select u.email, r.role from employeesignup u inner join employee_role ur on(u.id=ur.employee_id) inner join role r on(ur.role_id=r.role_id) where u.email=?";




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().usersByUsernameQuery(USERS_QUERY).authoritiesByUsernameQuery(ROLES_QUERY)
                .dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);

        auth.jdbcAuthentication().usersByUsernameQuery(USER_QUERY).authoritiesByUsernameQuery(ROLE_QUERY)
                .dataSource(dataSource).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/signup").permitAll().antMatchers("user/user_list").permitAll()
                .antMatchers("/user/**").permitAll()

                .antMatchers("/user/saveUser").permitAll()
                .antMatchers("/user/addUser/").permitAll()
                .antMatchers("/templates/**").permitAll()

                .antMatchers("/user/uploadcsvfile/").permitAll()


                // URLs matching for access rights
                .antMatchers("/employeeregister").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/login1").permitAll()
                .antMatchers("/register").permitAll()
                .antMatchers("/home/**").hasAnyAuthority("USER")
                .antMatchers("/admin/**").hasAnyAuthority( "ADMIN")
                .anyRequest().authenticated()
                .and()
                // form login
                .csrf().disable().formLogin()
                .loginPage("/login1")
                .failureUrl("/login?error=true")
                .successHandler(sucessHandler)
                .usernameParameter("email")
                .passwordParameter("password")

                .and()
                // logout
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/").and()
                .exceptionHandling()
                .accessDeniedPage("/access-denied");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
}