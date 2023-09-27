package com.jira2.validator.controllers;

import com.jira2.validator.response.JFormsResponse;
import com.jira2.validator.response.Jira2Response;
import com.jira2.validator.services.Jira2Service;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Jira2Controller
{
    @GetMapping("/issue/{issueId}")
    public Jira2Response getIssueDetails(@PathVariable String issueId)
    {
        if(StringUtils.isNotEmpty(issueId))
        {
            return Jira2Service.getIssueDetails(issueId);
        }
        else
        {
            throw new IllegalArgumentException("Issue ID cannot be null or empty");
        }
    }

    @GetMapping("/jform/{jformId}")
    public JFormsResponse getJFormDetails(@PathVariable String jformId)
    {
        if(StringUtils.isNotEmpty(jformId))
        {
            return Jira2Service.getJFormDetails(jformId);
        }
        else
        {
            throw new IllegalArgumentException("JForm ID cannot be null or empty");
        }
    }

    @GetMapping("/jira/{jiraId}")
    public Jira2Response getDevSummary(@PathVariable String jiraId)
    {
        if(StringUtils.isNotEmpty(jiraId))
        {
            return Jira2Service.getDevSummary(jiraId);
        }
        else
        {
            throw new IllegalArgumentException("Jira ID cannot be null or empty");
        }
    }
}
