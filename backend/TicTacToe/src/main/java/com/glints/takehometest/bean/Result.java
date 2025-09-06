package com.glints.takehometest.bean;

import lombok.Data;
import lombok.NonNull;

@Data
public class Result {
    @NonNull
    private String board;
    @NonNull
    private String winner;
}
