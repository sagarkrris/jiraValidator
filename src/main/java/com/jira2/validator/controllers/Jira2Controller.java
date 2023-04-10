package com.jira2.validator.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.jira2.validator.response.JFormsResponse;
import com.jira2.validator.response.Jira2Response;
import com.jira2.validator.reviewchecklist.JformCheckList;
import com.jira2.validator.services.Jira2Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Jira2Controller
{
    private Jira2Service jira2Service;

    public Jira2Controller(Jira2Service jira2Service)
    {
        this.jira2Service = jira2Service;
    }

    @GetMapping("/issue/{issueId}")
    public Jira2Response getIssueDetails(@PathVariable String issueId) throws JsonProcessingException
    {
        if(null != issueId)
        {
            return jira2Service.getIssueDetails(issueId);
        }
        else
        {
            throw new IllegalArgumentException("Issue ID cannot be null or empty");
        }
    }

    @GetMapping("/jform/{jformId}")
    public JformCheckList getJFormDetails(@PathVariable String jformId) throws JsonProcessingException
    {
        if(null != jformId)
        {
            return jira2Service.getJFormDetails(jformId);
        }
        else
        {
            throw new IllegalArgumentException("JForm ID cannot be null or empty");
        }
    }
}
