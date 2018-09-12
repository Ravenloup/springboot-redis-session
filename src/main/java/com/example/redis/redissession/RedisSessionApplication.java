package com.example.redis.redissession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableRedisHttpSession
@RestController
public class RedisSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisSessionApplication.class, args);
	}

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testSession(HttpServletRequest request) {
		return request.getSession(true).getId();
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public boolean logout(HttpSession session) {
		if (!session.isNew()) {
			session.invalidate();
			return true;
		}
		return false;
	}

	@Bean
	public static ConfigureRedisAction configureRedisAction() {
		return ConfigureRedisAction.NO_OP;
	}
}
