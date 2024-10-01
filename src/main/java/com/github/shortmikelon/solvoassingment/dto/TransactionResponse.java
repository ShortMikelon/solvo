package com.github.shortmikelon.solvoassingment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {
    private Long id;
    private int accountTo;
    private int accountFrom;
    private double sumInKzt;
    private double sumInRub;
    private double sumInUsa;
    private String category;
    private String currencyShortname;
    private boolean limitExceeded;
    private LocalDateTime datetime;
}
