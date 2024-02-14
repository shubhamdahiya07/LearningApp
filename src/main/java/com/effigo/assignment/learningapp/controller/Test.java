package com.effigo.assignment.learningapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Test {
	@RequestMapping("/hello")
	@ResponseBody
	public String helloPrinter() {
		return "hello";
	}
}
