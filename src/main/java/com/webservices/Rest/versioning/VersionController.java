package com.webservices.Rest.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersionController {
    @GetMapping("/v1/person")
    public PersonV1 getPersonV1(){
        return new PersonV1("Pratap Singh");
    }

    @GetMapping("/v2/person")
    public PersonV2 getPersonV2(){
        return new PersonV2(new MyName("Pratap","Singh"));
    }

    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 getPersonV1UsingParam(){
        return new PersonV1("Pratap Singh");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 getPersonV2UsingParam(){
        return new PersonV2(new MyName("Pratap","Singh"));
    }

    @GetMapping(value = "/person/header", headers = "API-VERSION=1")
    public PersonV1 getPersonV1UsingHeader(){
        return new PersonV1("Pratap Singh");
    }

    @GetMapping(value = "/person/header", headers = "API-VERSION=2")
    public PersonV2 getPersonV2UsingHeader(){
        return new PersonV2(new MyName("Pratap","Singh"));
    }

    /*@GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v1+josn")
    public PersonV1 getPersonV1UsingProduces(){
        return new PersonV1("Pratap Singh");
    }

    @GetMapping(value = "/person/produces", produces = "application/vnd.company.app-v2+josn")
    public PersonV2 getPersonV2UsingProduces(){
        return new PersonV2(new MyName("Pratap","Singh"));
    }*/
}
