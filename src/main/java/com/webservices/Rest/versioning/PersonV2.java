package com.webservices.Rest.versioning;

public class PersonV2 {
    private MyName name;

    public PersonV2(MyName name) {
        this.name = name;
    }

    public MyName getName() {
        return name;
    }

    public void setName(MyName name) {
        this.name = name;
    }
}
