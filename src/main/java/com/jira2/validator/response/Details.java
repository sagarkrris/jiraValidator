package com.jira2.validator.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Details{
    public int openCount;
    public int mergedCount;
    public int declinedCount;
    public int total;
}