package com.jira2.validator.response;

import lombok.Data;

@Data
public class Issuetype
{
    private String self;
    private String id;
    private String description;
    private String iconUrl;
    private String name;
    private boolean subtask;
    private float avatarId;
}