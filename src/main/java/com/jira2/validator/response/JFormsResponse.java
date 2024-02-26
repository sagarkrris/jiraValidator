package com.jira2.validator.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class JFormsResponse
{
    private List<String> solution;
    private List<String> solutionDetails;
    private List<String> jiraGroup;
    private String projectIdentifiers;
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
    private String requirements;
    private String projectTraceability;
    private String sourceCode;
    private String technicalDesignDocumentation;
    private String testCaseReview;
    private String dIApprovalsComplete;
    private String dOApprovalsComplete;
    private String dIOeSignatureComplete;
    private String developmentConsiderationsIncluded;
    private String addChangeSolutionCapabilities;
    private String changesOrImpactToHazardAnalysis;

    private Assignee assignee;
    private List<Attachment> attachments = new ArrayList<>();
    private List<String> labels;
    private List<People> diApprovers;
    private List<People> doApprovers;
    private List<String> ppmSolutionDetailId = new ArrayList<>();
}