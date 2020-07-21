package com.sanmina.basictemplate.pojo.conduit;

import java.util.List;

import lombok.Data;

@Data
public class ConduitCommand {
    private String command;
    private List<List<String>> propertiesCommand;

}