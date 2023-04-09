package com.jira2.validator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jira2.validator.response.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Log4j2
public class Jira2Service
{
    @Autowired
    private RestTemplate restTemplate;

    /**
     * Returns the Jira Issue Details for the specified issue Id
     * @param issueId the issue Id of the jira issue
     * @return {@link Jira2Response}
     */
    public Jira2Response getIssueDetails(String issueId)
    {
        //URL Formation
        //change the url
        String url = "https://jira.organization.com/rest/api/2/issue/" + issueId;
        // create headers
        HttpHeaders headers = new HttpHeaders();
        //change the jira token
        headers.add("Authorization", "Basic " + "your_personal_access_jira_token");
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        // create request
        HttpEntity<?> request = new HttpEntity<>(headers);
        log.info("URL: {}", url);
        ResponseEntity<ComponentResponse> responseEntity = new RestTemplate().exchange(url, HttpMethod.GET, request,
                ComponentResponse.class);
        ComponentResponse componentResponse = responseEntity.getBody();
        ObjectMapper om = new ObjectMapper();
        List<Attachment> attachmentList = null;
        try
        {
            attachmentList = om.readValue(
                    om.writerWithDefaultPrettyPrinter().writeValueAsString(componentResponse.getFields().getAttachment()),
                    new TypeReference<List<Attachment>>()
                    {
                    });
        }
        catch(JsonProcessingException e)
        {
            log.error("Exception processing the json: " + e.getMessage(), e);
            throw new RuntimeException(e);

        }
        return Jira2Response.builder().acceptanceCriteria(componentResponse.getFields().getCustomfield_11800())
                .jiraGroups(componentResponse.getFields().getCustomfield_14802())
                .issueType(componentResponse.getFields().getIssuetype().getName())
                .components(componentResponse.getFields().getComponents())
                .epicName(componentResponse.getFields().getCustomfield_11002())
                .projectNumberOrTime(componentResponse.getFields().getCustomfield_12301())
                .status(componentResponse.getFields().getStatus().getName()).assignee(componentResponse.getFields().getAssignee())
                .description(componentResponse.getFields().getDescription()).summary(componentResponse.getFields().getSummary())
                .dueDate(componentResponse.getFields().getDuedate()).description(componentResponse.getFields().getDescription())
                .labels(componentResponse.getFields().getLabels()).timeEstimate(componentResponse.getFields().getTimeestimate())
                .attachments(attachmentList).build();
    }

    /**
     * Returns the JForm details of the given jFormId
     * @param jformId the given jFormId
     * @return {@link JFormsResponse}
     */
    public JFormsResponse getJFormDetails(String jformId)
    {
        //URL Formation please change this
        String url = "https://jira.organization.com/rest/api/2/issue/" + jformId;
        // create headers
        HttpHeaders headers = new HttpHeaders();
        //please change the access jira token
        headers.add("Authorization", "Basic " + "your_personal_access_jira_token");
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        // create request
        HttpEntity<?> request = new HttpEntity<>(headers);
        log.info("URL: {}", url);
        ResponseEntity<ComponentResponse> responseEntity = new RestTemplate().exchange(url, HttpMethod.GET, request,
                ComponentResponse.class);
        ComponentResponse componentResponse = responseEntity.getBody();
        ObjectMapper objectMapper = new ObjectMapper();
        String valueAsString;
        List<People> diApproverList;
        List<People> doApproverList;
        List<Attachment> attachmentList;
        try
        {
            valueAsString = objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(componentResponse.getFields().getCustomfield_20323());
            diApproverList = objectMapper.readValue(valueAsString, new TypeReference<List<People>>()
            {
            });
            attachmentList = objectMapper.readValue(objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(componentResponse.getFields().getAttachment()), new TypeReference<List<Attachment>>()
            {
            });
            doApproverList = objectMapper.readValue(objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(componentResponse.getFields().getCustomfield_20327()), new TypeReference<List<People>>()
            {
            });
        }
        catch(JsonProcessingException e)
        {
            log.error("Exception processing the json: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }
        return JFormsResponse.builder().acceptanceCriteria(componentResponse.getFields().getCustomfield_11800())
                .jiraGroups(componentResponse.getFields().getCustomfield_14802())
                .issueType(componentResponse.getFields().getIssuetype().getName())
                .components(componentResponse.getFields().getComponents())
                .epicName(componentResponse.getFields().getCustomfield_11002())
                .projectNumberOrTime(componentResponse.getFields().getCustomfield_12301())
                .status(componentResponse.getFields().getStatus().getName()).assignee(componentResponse.getFields().getAssignee())
                .description(componentResponse.getFields().getDescription()).summary(componentResponse.getFields().getSummary())
                .dueDate(componentResponse.getFields().getDuedate()).description(componentResponse.getFields().getDescription())
                .labels(componentResponse.getFields().getLabels()).timeEstimate(componentResponse.getFields().getTimeestimate())
                .attachments(attachmentList).diApprovers(diApproverList).doApprovers(doApproverList).build();
    }
}