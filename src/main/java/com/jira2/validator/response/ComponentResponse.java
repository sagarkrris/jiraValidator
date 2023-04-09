package com.jira2.validator.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ComponentResponse
{
    private String id;
    private String self;
    private String key;
    Fields fields;
    private String jiraTitle;
    private List<String> acceptanceCriteria = new ArrayList<>();
}
