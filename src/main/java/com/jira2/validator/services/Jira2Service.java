package com.jira2.validator.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.jira2.validator.response.*;
import com.jira2.validator.reviewchecklist.JFormCheckList;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.List;

@Service
@Log4j2
public class Jira2Service
{
    @Autowired
    private RestTemplate restTemplate;
    private static HttpEntity<?> request;

    /**
     * Returns the Jira Issue Details for the specified issue Id
     * @param issueId the issue Id of the jira issue
     * @return {@link Jira2Response}
     */
    public static Jira2Response getIssueDetails(String issueId)
    {
        //URL Formation
        //change the url
        String url = "https://jira2.cerner.com/rest/api/2/issue/" + issueId;
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + "c2sxMDAyNTE6TWFsYXJpYUAyMDIz");
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        // create request
        request = new HttpEntity<>(headers);
        ResponseEntity<ComponentResponse> responseEntity = new RestTemplate().exchange(url, HttpMethod.GET, request,
                ComponentResponse.class);
        ComponentResponse componentResponse = responseEntity.getBody();
        log.info("Response: " + componentResponse);
        ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.registerModule(new Jdk8Module());
        List<Attachment> attachmentList;
        try
        {
            attachmentList = objectMapper.readValue(objectMapper.writerWithDefaultPrettyPrinter()
                    .writeValueAsString(componentResponse.getFields().getAttachment()), new TypeReference<List<Attachment>>()
            {
            });

        }
        catch(JsonProcessingException e)
        {
            log.error("Exception processing the json: " + e.getMessage(), e);
            throw new RuntimeException(e);

        }
        return Jira2Response.builder().acceptanceCriteria(componentResponse.getFields().getCustomfield_11800())
                .reporter(componentResponse.getFields().getCustomfield_22800())
                .jiraGroups(componentResponse.getFields().getCustomfield_14802())
                .issueType(componentResponse.getFields().getIssuetype().getName())
                .components(componentResponse.getFields().getComponents())
                .epicName(componentResponse.getFields().getCustomfield_11002())
                .projectNumberOrTime(componentResponse.getFields().getCustomfield_12301())
                .status(componentResponse.getFields().getStatus().getName()).assignee(componentResponse.getFields().getAssignee())
                .description(componentResponse.getFields().getDescription()).summary(componentResponse.getFields().getSummary())
                .dueDate(componentResponse.getFields().getDuedate()).description(componentResponse.getFields().getDescription())
                .labels(componentResponse.getFields().getLabels())
                .devSummary(componentResponse.getFields().getCustomfield_19700())
                .ppmSolutionDetailId(componentResponse.getFields().getCustomfield_18000())
                .timeEstimate(componentResponse.getFields().getTimeestimate()).attachments(attachmentList).build();
    }

    public static HttpHeaders jiraLogin(String username, String password, HttpHeaders headers)
    {
        System.out.println("JIRA Login");
        // create headers

        //please change the access jira token
        // headers.add("Authorization", "Basic " + "c2sxMDAyNTE6TWFsYXJpYUAyMDIy");
        String encoding = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        log.info("Base64 encoding: {}", encoding);
        headers.add("Authorization", "Basic " + encoding);
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        return headers;
    }

    public static boolean jiraLogin(String username, String password)
    {
        System.out.println("JIRA Login");
        // create headers
        HttpHeaders headers = new HttpHeaders();
        //please change the access jira token
        // headers.add("Authorization", "Basic " + "c2sxMDAyNTE6TWFsYXJpYUAyMDIy");
        String encoding = Base64.getEncoder().encodeToString((username + ":" + password).getBytes());
        log.info("Base64 encoding: {}", encoding);
        headers.add("Authorization", "Basic " + encoding);
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        // create request
        request = new HttpEntity<>(headers);
        System.out.println("request created");
        return true;
    }

    /**
     * Returns the JForm details of the given jFormId
     * @param jformId the given jFormId
     * @return {@link JFormsResponse}
     */
    public static JFormsResponse getJFormDetails(String jformId)
    {
        //URL Formation please change this
        String url = "https://jira.cerner.com/rest/api/2/issue/" + jformId;
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + "c2sxMDAyNTE6TWFsYXJpYUAyMDIz");
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        log.info("URL: {}", url);
        request = new HttpEntity<>(headers);
        ResponseEntity<ComponentResponse> responseEntity = new RestTemplate().exchange(url, HttpMethod.GET, request,
                ComponentResponse.class);
        ComponentResponse componentResponse = responseEntity.getBody();
        log.info(componentResponse);
        ObjectMapper objectMapper = new ObjectMapper();
        String valueAsString;
        List<People> diApproverList;
        List<People> doApproverList;
        List<Attachment> attachmentList;
        assert componentResponse != null;
        Fields fields = componentResponse.getFields();
        SolutionCapabilities solutionCapabilities;
        HazardAnalysis hazardAnalysis;
        DevelopmentConsiderations developmentConsiderations;
        try
        {
            valueAsString = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(fields.getCustomfield_20323());
            diApproverList = objectMapper.readValue(valueAsString, new TypeReference<List<People>>()
            {
            });

            developmentConsiderations = objectMapper.readValue(
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(fields.getCustomfield_20328()),
                    DevelopmentConsiderations.class);
            solutionCapabilities = objectMapper.readValue(
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(fields.getCustomfield_20320()),
                    SolutionCapabilities.class);
            hazardAnalysis = objectMapper.readValue(
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(fields.getCustomfield_20350()),
                    HazardAnalysis.class);
            attachmentList = objectMapper.readValue(
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(fields.getAttachment()),
                    new TypeReference<List<Attachment>>()
                    {
                    });
            doApproverList = objectMapper.readValue(
                    objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(fields.getCustomfield_20327()),
                    new TypeReference<List<People>>()
                    {
                    });
        }
        catch(JsonProcessingException e)
        {
            log.error("Exception processing the json: " + e.getMessage(), e);
            throw new RuntimeException(e);
        }

        JFormsResponse jFormsResponse = JFormsResponse.builder().acceptanceCriteria(fields.getCustomfield_11800())
                .solution(fields.getCustomfield_20337()).solutionDetails(fields.getCustomfield_20338())
                .jiraGroup(fields.getCustomfield_20330()).projectIdentifiers(fields.getCustomfield_20331())
                .jiraGroups(fields.getCustomfield_14802()).issueType(fields.getIssuetype().getName())
                .components(fields.getComponents()).epicName(fields.getCustomfield_11002())
                .projectNumberOrTime(fields.getCustomfield_12301()).status(fields.getStatus().getName())
                .assignee(fields.getAssignee()).requirements(fields.getCustomfield_20336())
                .projectTraceability(fields.getCustomfield_20332()).sourceCode(fields.getCustomfield_20340())
                .testCaseReview(fields.getCustomfield_20342()).technicalDesignDocumentation(fields.getCustomfield_20030())
                .dIApprovalsComplete(fields.getCustomfield_20322()).dOApprovalsComplete(fields.getCustomfield_20326())
                .developmentConsiderationsIncluded(developmentConsiderations.getValue())
                .dIOeSignatureComplete(fields.getCustomfield_20324()).changesOrImpactToHazardAnalysis(hazardAnalysis.getValue())
                .addChangeSolutionCapabilities(solutionCapabilities.getValue()).summary(fields.getSummary())
                .dueDate(fields.getDuedate()).description(fields.getDescription()).labels(fields.getLabels())
                .timeEstimate(fields.getTimeestimate()).attachments(attachmentList).diApprovers(diApproverList)
                .doApprovers(doApproverList).ppmSolutionDetailId(fields.getCustomfield_18000()).build();

        new JFormCheckList().JiraChecklist(jFormsResponse);

        return jFormsResponse;
    }

    /**
     * Returns the Development Summary of the given jFormId
     * @param jiraId the given jira id
     */
    public static Jira2Response getDevSummary(String jiraId)
    {
        //URL Formation please change this
        String url = "https://jira2.cerner.com/rest/api/2/issue/" + jiraId;
        // create headers
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Basic " + "c2sxMDAyNTE6TWFsYXJpYUAyMDIz");
        headers.add("Accept", "application/json");
        headers.add("Content-Type", "application/json");
        log.info("URL: {}", url);
        request = new HttpEntity<>(headers);
        ResponseEntity<ComponentResponse> responseEntity;
        try
        {
            responseEntity = new RestTemplate().exchange(url, HttpMethod.GET, request, ComponentResponse.class);
        }
        catch(Exception e)
        {
            log.error("Exception while generating response: {}", e.getMessage(), e);
            throw e;
        }
        ComponentResponse componentResponse = responseEntity.getBody();

        return Jira2Response.builder().devSummary(componentResponse.getFields().getCustomfield_19700()).build();
    }
}