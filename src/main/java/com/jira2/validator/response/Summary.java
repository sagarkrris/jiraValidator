package com.jira2.validator.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@JsonIgnoreProperties
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class Summary
{
    private PullRequest pullRequest;
    private Build build;
    private Review review;
    private DeploymentEnvironment deploymentEnvironment;
    private Repository repository;
    private Branch branch;

    @Override
    public String toString()
    {
        return "Summary{" + "pullRequest=" + pullRequest + ", build=" + build + ", review=" + review + ", deploymentEnvironment=" + deploymentEnvironment + ", repository=" + repository + ", branch=" + branch + '}';
    }
}
