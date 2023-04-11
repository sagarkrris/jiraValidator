package com.jira2.validator.reviewchecklist;

import com.jira2.validator.response.Jira2Response;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Log4j2
public class Jira2CheckList
{
    public void jiraChecklist(Jira2Response jira2Response)
    {
        log.info("Solution Change(s)/JIRA(s)");
        statusValidation(jira2Response.getStatus());
        componentsValidator(jira2Response.getComponents());
        issueTypeValidator(jira2Response.getIssueType());
    }

    private void issueTypeValidator(String issueType)
    {
        if(issueType.equals("Defect"))
        {
            log.info("Issue Type is: {}", issueType);
        }
        else
        {
            log.info("Issue Type is: {}", issueType);
        }
    }

    private void componentsValidator(List<Object> components)
    {
        log.info("Component/s");
        if(components.isEmpty())
        {
            log.info("Component/s is empty");
        }
        else
        {
            log.info(components);
        }
    }

    private String statusValidation(String jiraStatus)
    {
        List<String> statusList = List.of("Validating Release", "Client Validation", "Ready for GA", "Closed");
        statusList.forEach(status -> {
            if(StringUtils.equalsIgnoreCase(status, jiraStatus))
            {
                log.info("Valid Status: {}", jiraStatus);
            }
        });
        return jiraStatus;
    }
}
