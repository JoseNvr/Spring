package com.sanmina.basictemplate.pojo.conduit;

import java.util.List;

import lombok.Data;

@Data
public class ConduitData {
    private String serialNumber;
    private String partNumber;
    private Integer quantity = 1;
    private String revision = "";
    private List<ConduitCommand> conduitCommand;
    private List<String> commandStringList;

}