package com.sanmina.basictemplate.pojo.conduit;

import java.util.List;

import lombok.Data;

@Data
public class ConduitRequest {
    private String conduitServer;
    private String clientId;
    private String employeeNumber;
    private String password;
    private String deviceId;
    private String locationId;
    private String proccessId;
    private List<ConduitData> conduitData;

}