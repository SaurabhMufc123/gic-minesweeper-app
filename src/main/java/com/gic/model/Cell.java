package com.gic.model;

import lombok.Data;

@Data
public class Cell {
    private boolean isMine;
    private boolean isRevealed;
    private int adjacentMines;
}

