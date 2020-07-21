package com.sanmina.basictemplate.pojo.conduit;

import java.util.List;

import lombok.Data;

@Data
public class ConduitCommandResponse {

    private Boolean abort;
    private Boolean noOp;
    private Boolean flowError;
    private Boolean serialNotFound;
    private Boolean prompt;
    private Boolean exec;
    private Boolean ok;
    private Boolean empty;
    private Boolean error;
    private String response;
    private String serialNumber;
    private List<ConduitDataResponse> dataResponse;
}