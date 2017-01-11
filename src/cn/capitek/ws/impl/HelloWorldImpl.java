package cn.capitek.ws.impl;

import javax.jws.WebService;

import cn.capitek.ws.HelloWorld;

@WebService(endpointInterface = "cn.capitek.ws.HelloWorld")
public class HelloWorldImpl implements HelloWorld {

	public String sayHi(String text) {
		 System.out.println("sayHi called");
	     return "Hello " + text;
	}

}
