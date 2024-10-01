package com.github.shortmikelon.solvoassingment.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_to", nullable = false)
    private int accountTo;

    @Column(name = "account_from", nullable = false)
    private int accountFrom;

    @Column(name = "currency_shortname", nullable = false)
    private String currencyShortname;

    @Column(nullable = false)
    private double sum;

    @Column(nullable = false)
    private String category;

    @Column(name = "limit_exceeded", nullable = false)
    private boolean limitExceeded;

    @Column(nullable = false)
    private LocalDateTime datetime;
}
