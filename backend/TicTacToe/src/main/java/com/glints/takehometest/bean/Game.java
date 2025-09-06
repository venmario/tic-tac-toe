package com.glints.takehometest.bean;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class Game {

    @NonNull
    private String board;
    @NonNull
    private BoardGrid boardGrid;
    @NonNull
    private String playerLetter;
    @NonNull
    private Integer totalConsecutive;
}
