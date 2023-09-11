package com.jira2.validator.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Overall{
    public int count;
    public Object lastUpdated;
    public int stateCount;
    public String state;
    public Details details;
    @JsonProperty("open")
    public boolean myopen;
    public int failedBuildCount;
    public int successfulBuildCount;
    public int unknownBuildCount;
    public Object dueDate;
    public boolean overDue;
    public boolean completed;
    public ArrayList<Object> topEnvironments;
    public boolean showProjects;
    public int successfulCount;
}