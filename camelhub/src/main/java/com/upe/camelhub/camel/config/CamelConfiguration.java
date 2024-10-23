//package com.upe.camelhub.camel.config;
//
//import org.apache.camel.CamelContext;
//import org.apache.camel.component.servlet.CamelHttpTransportServlet;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class CamelConfiguration {
//
//  @Bean
//  public ServletRegistrationBean camelServlet(CamelContext camelContext) {
//    ServletRegistrationBean servlet = new ServletRegistrationBean(new CamelHttpTransportServlet(),
//        "/camel/*");
//    servlet.setName("CamelServlet");
//    return servlet;
//  }
//}
