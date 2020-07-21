package com.sanmina.basictemplate.pojo.conduit;

import java.util.List;

import lombok.Data;

@Data

public class ConduitResponse{
private Boolean success;
private String message;
private Integer errorCode;
private List<ConduitCommandResponse> data;
}