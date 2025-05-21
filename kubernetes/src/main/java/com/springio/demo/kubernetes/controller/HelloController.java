package com.springio.demo.kubernetes.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@Value("${HOSTNAME:unset}")
	String hostname;

	@GetMapping("/hello")
	public String hello() {
		return "Hello from Spring! 👋 My name is "+hostname;
	}
}
