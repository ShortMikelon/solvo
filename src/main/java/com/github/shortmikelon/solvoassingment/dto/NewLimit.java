package com.github.shortmikelon.solvoassingment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewLimit {
    private double limitSum;
    private String limitCurrencyShortname;
    private String limitCategory;
}
