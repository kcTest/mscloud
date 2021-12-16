package com.zkc.springcloud.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigClientController {
	
	@Value("${config.info}")
	private String configInfo;
	
	@Value("${config.info.msg}")
	private String msg;
	
	@GetMapping(value = "/config/info")
	public String getConfigInfo() {
		return configInfo + ", " + msg;
	}
}
