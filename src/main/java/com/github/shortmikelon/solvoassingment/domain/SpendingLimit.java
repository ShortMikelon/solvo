package com.github.shortmikelon.solvoassingment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "spending_limits")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SpendingLimit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "limit_sum", nullable = false)
    private double limitSum;

    @Column(name = "limit_datetime", nullable = false)
    private LocalDateTime limitDatetime;

    @Column(name = "limit_currency_shortname", nullable = false)
    private String limitCurrencyShortname;

    @Column(name = "limit_category", nullable = false)
    private String limitCategory;
}
