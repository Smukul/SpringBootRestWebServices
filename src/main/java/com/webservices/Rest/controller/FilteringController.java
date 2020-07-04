package com.webservices.Rest.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.webservices.Rest.entity.SomeBean;
import com.webservices.Rest.entity.SomeBeanStaticFilter;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

import static com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter.filterOutAllExcept;

@RestController
public class FilteringController {

    @GetMapping("/filter")
    public MappingJacksonValue retrivingBean(){
        SomeBean someBean = new SomeBean("Value1","Value2","Value3");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.
                filterOutAllExcept("field1","field3");
        FilterProvider filterProvider = new SimpleFilterProvider().
                addFilter("SomeBeanFilter",filter);

        MappingJacksonValue mapping = new MappingJacksonValue(someBean);
        mapping.setFilters(filterProvider);
        return mapping;
    }

    @GetMapping("/filter-list")
    public List<SomeBeanStaticFilter> retrivingListBean(){
        return Arrays.asList(new SomeBeanStaticFilter("Value1","Value2","Value3"),
                new SomeBeanStaticFilter("Value1","Value2","Value3"));
    }
}
