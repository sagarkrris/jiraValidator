package com.jira2.validator.reviewchecklist;

import com.jira2.validator.response.JFormsResponse;
import com.jira2.validator.response.People;
import lombok.extern.log4j.Log4j2;

import java.util.List;

@Log4j2
public class JFormCheckList
{
    public void JiraChecklist(JFormsResponse jFormsResponseResponse){
        System.out.println("JIRA Form (JFORM)");
        statusValidator(jFormsResponseResponse.getStatus());
        generalTabValidator(jFormsResponseResponse.getSolution(), jFormsResponseResponse.getSolutionDetails(), jFormsResponseResponse.getJiraGroup(), jFormsResponseResponse.getProjectIdentifiers());
        designInputTabValidator(jFormsResponseResponse.getAddChangeSolutionCapabilities(), jFormsResponseResponse.getDevelopmentConsiderationsIncluded(),
                jFormsResponseResponse.getRequirements(), jFormsResponseResponse.getProjectTraceability(), jFormsResponseResponse.getChangesOrImpactToHazardAnalysis());
        designOutputTabValidator(jFormsResponseResponse.getTechnicalDesignDocumentation(), jFormsResponseResponse.getSourceCode());
        testCaseTabValidator(jFormsResponseResponse.getTestCaseReview());
        peopleValidator(jFormsResponseResponse.getDiApprovers(), jFormsResponseResponse.getDoApprovers());
    }

    private void peopleValidator(List<People> diApprovers, List<People> doApprovers) {
        if(diApprovers != null && diApprovers.isEmpty()){
            log.info("DI Approvers have not signed");
        }
        else{
            log.info("DI Approvers: {}", diApprovers);
        }
        if(doApprovers != null && doApprovers.isEmpty()){
            log.info("DO Approvers have not signed");
        }
        else{
            log.info("DO Approvers: {}", doApprovers);
        }
    }

    private void testCaseTabValidator(String testCaseReview) {
        if(testCaseReview != null && testCaseReview.isEmpty()){
            log.info("Test Case Review is empty");
        }
        else{
            log.info("Test Case Review is : {}", testCaseReview);
        }
    }

    private void designOutputTabValidator(String technicalDesignDocumentation, String sourceCode) {
        if(technicalDesignDocumentation != null && technicalDesignDocumentation.isEmpty()){
            log.info("Technical Design Documentation is empty");
        }
        else{
            log.info("Technical Design Documentation is : {}", technicalDesignDocumentation);
        }

        if(sourceCode != null && sourceCode.isEmpty()){
            log.info("Technical Design Documentation is empty\n" +
                    "provide detailed explanation");
        }
        else{
            log.info("Source Code is : {}", sourceCode);
        }
    }

    private void designInputTabValidator(String addChangeSolutionCapabilities, String developmentConsiderationsIncluded, String requirements,
                                         String projectTraceability, String changesOrImpactToHazardAnalysis) {
        if(!addChangeSolutionCapabilities.isEmpty()) {
            if (addChangeSolutionCapabilities.equals("No")) {
                log.info("Adds/Changes Solution Capabilities: {}, no further action is required.", addChangeSolutionCapabilities);
            } else {
                log.info("Adds/Changes Solution Capabilities: {}, a review of the Solution Record must attached to the JFORM.", addChangeSolutionCapabilities);
            }
        }

        switch (developmentConsiderationsIncluded) {
            case "Yes" -> log.info("Have you implemented appropriate development considerations?: {}", developmentConsiderationsIncluded);
            default -> log.info("Have you implemented appropriate development considerations?: {}", developmentConsiderationsIncluded);
        }

        if(requirements != null && requirements.isEmpty())
            log.info("Requirements is empty: {}", requirements);

        if(projectTraceability != null && projectTraceability.isEmpty())
            log.info("Project Traceability is empty: {}", projectTraceability);

        if (changesOrImpactToHazardAnalysis.equals("Yes")) {
            log.info("Changes or Impacts to Hazard Analysis: {}", changesOrImpactToHazardAnalysis);
        } else if (changesOrImpactToHazardAnalysis.equals("No")) {
            log.info("Changes or Impacts to Hazard Analysis: {}", changesOrImpactToHazardAnalysis);
            log.info("The \"Hazard Analysis Location\" field must include the location of the hazards (must be a valid Jazz link).\n" +
                    "The \"Related Mitigating Actions\" field must include the mitigating actions for the hazards.\n" );
        } else {
            log.info("Changes or Impacts to Hazard Analysis: {} is not valid", changesOrImpactToHazardAnalysis);
        }
    }

    private void generalTabValidator(List<String> solution, List<String> solutionDetails, List<String> jiraGroup, String projectIdentifiers) {
        if(solution != null && solution.isEmpty())
            log.info("Solution is empty: {}", solution);
        String[] project = projectIdentifiers.split("[,]");
        for (int i=0; i <project.length-1; i++) {
            String[] solutionQueue = project[i].substring(1, project[i].length() - 1).split("[|]");
            String[] s = new String[1];
            for (int j=0; j <solutionQueue.length-1; j++) {
                s = solutionQueue[j].split("[-]");
            }
            log.info("Solution Queue is: {}", s[0]);
            log.info("Solution link is: {}", solutionQueue[1]);
        }

    }

    private void statusValidator(String jiraStatus)
    {
        switch (jiraStatus) {
            case "Complete" -> log.info("Issue Status is: {}", jiraStatus);
            default -> log.info("Issue Status is: {}", jiraStatus);
        }
    }


}
