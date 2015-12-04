package com.paychex.exstream.boot;

import java.io.IOException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@EnableAutoConfiguration
public class ExstreamApplication {
	
	private static final String command = "/opt/exstream/8.0/Engine -CONTROLFILE=";
	
	private static final String tps = "/TPS_SPRDR1/TPS_SPRDR1.control";
	
	private static final String yem = "/YEM_IRS943/YEM_IRS943.control";
	
	@RequestMapping("/template")
	public @ResponseBody String run() throws IOException, InterruptedException {
		
		Runtime runtime = Runtime.getRuntime();
		Process process = null;
		
		process = runtime.exec(command + tps);
		
		/*if (template.equalsIgnoreCase("TPS")) {
			process = runtime.exec(command + tps);
		}
		else if (template.equalsIgnoreCase("YEM")) {
			process = runtime.exec(command + yem);
		}
		else
			return new String("666");*/
		
		Integer exitCode = process.waitFor();
		return exitCode.toString();
	}

}
