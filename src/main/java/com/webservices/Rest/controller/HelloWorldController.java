package com.webservices.Rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
public class HelloWorldController {
    @Autowired
    public MessageSource messageSource;

    //@RequestMapping(method=RequestMethod.GET,path = "/hello")
    @GetMapping(path="/hello")
    public String getMessage(){
        return "Hello World!!";
    }

    @GetMapping(path="/hello-bean")
    public HelloWorldBean getMessageBean(){
        return new HelloWorldBean("Hello World-Bean!!");
    }

    @GetMapping(path="/hello/pathvariable/{name}")
    public HelloWorldBean getMessagePathVariable(@PathVariable String name){
        return new HelloWorldBean(String.format("Hello World- Path Variable : %s",name));
    }

    @GetMapping(path = "/hello-internationalized")
    public String helloInternationalized(@RequestHeader(name="Accept-Language",required = false) Locale locale){
        return messageSource.getMessage("good.morning.message",null,locale);
    }

    @GetMapping(path = "/hello-internationalized-new")
    public String helloInternationalizedAnotherWay(){
        return messageSource.getMessage("good.morning.message",null, LocaleContextHolder.getLocale());
    }
}
