package com.jira2.validator.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Jira2Response
{
    private List<Object> components;
    private String status;
    private String description;
    private List<String> jiraGroups;
    private String acceptanceCriteria;
    private String epicName;
    private String projectNumberOrTime;
    private String issueType;
    private String summary;
    private String dueDate;
    private String timeEstimate;
    private Assignee assignee;
    private List<Attachment> attachments = new ArrayList<>();
    private List<String> labels;
}
