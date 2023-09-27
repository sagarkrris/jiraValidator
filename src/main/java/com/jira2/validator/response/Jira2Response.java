package com.jira2.validator.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
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
@JsonPropertyOrder(alphabetic = true)
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
    private String reporter;
    private Assignee assignee;
    private List<Attachment> attachments = new ArrayList<>();
    private List<String> labels;
    private List<String> ppmSolutionDetailId = new ArrayList<>();
    private String devSummary;
}
