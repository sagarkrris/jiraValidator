package com.jira2.validator.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CachedValue{
    public ArrayList<Object> errors;
    public ArrayList<Object> configErrors;
    public Summary summary;
}