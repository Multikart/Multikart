package com.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = "com.ecommerce")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	String role[] = {"ADMIN"};
	String user[] = {"ADMIN","MANAGER"};

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// KHAI BÁO LỚP SERVICE DÙNG ĐỂ CHECK EMAIL LẤY THÔNG TIN NGƯỜI DÙNG
	// KHAI BÁO ĐỐI TƯỢNG GIẢI MÃ PASS VÀ CHECK PASS
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	// PHÂN QUYỀN NGƯỜI DÙNG
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		// CẤU HÌNH PHÂN QUYỀN
		http.csrf().disable()
				.antMatcher("/admin/**").authorizeRequests()
				.antMatchers("/admin/login").permitAll() // Bỏ qua ko kiểm tra																					// nhập
				.antMatchers("/admin/role").hasAnyRole(role) // Có quyền ADMIN mới đc vào
				.antMatchers("/admin/user").hasAnyRole(user) // Có quyền ADMIN hoặc TEACHER mới đc vào
				.antMatchers("/admin/product").hasAnyRole("ADMIN", "MANAGER","STAFF")
				.antMatchers("/admin/itempf").hasAnyRole("ADMIN")
				.anyRequest().authenticated(); // Bắt buộc phải đăng nhập rồi mới đc vào

		http.exceptionHandling().accessDeniedPage("/error/403");

		// CẤU HÌNH ĐĂNG NHẬP
		http.formLogin().loginPage("/admin/login") // Link của hàm GET dùng để show form đăng nhập
				.loginProcessingUrl("/admin/login") // Link khai bao trong action của form đăng nhập
				.usernameParameter("email") // name của ô input dùng để nhập email
				.passwordParameter("password") // name của ô input dùng để nhập mật khẩu
				.defaultSuccessUrl("/admin") // Nếu đăng nhập thành công thì sẽ chuyển hướng về trang chủ
				.failureUrl("/admin/login?error=1"); // Nếu đăng nhập thất bại thì sẽ chuyển hướng về trang đăng nhập

		// CẤU HÌNH ĐĂNG XUẤT
		http.logout()
		.logoutUrl("/admin/logout")
		.logoutSuccessUrl("/admin/login")
		.deleteCookies("JSESSIONID");
	}
}
