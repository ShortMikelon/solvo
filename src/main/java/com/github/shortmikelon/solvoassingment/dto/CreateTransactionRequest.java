package com.github.shortmikelon.solvoassingment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionRequest {
    private int accountTo;
    private int accountFrom;
    private String currencyShortname;
    private double sum;
    private String category;
}
