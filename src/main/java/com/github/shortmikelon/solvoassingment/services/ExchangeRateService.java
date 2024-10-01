package com.github.shortmikelon.solvoassingment.services;

import reactor.core.publisher.Mono;

import java.time.LocalDate;

public interface ExchangeRateService {
    Mono<Double> convertSumToKzt(double sum, String currentCurrency, LocalDate date);

    Mono<Double> convertSumToUsd(double sum, String currentCurrency, LocalDate date);

    Mono<Double> convertSumToRub(double sum, String currentCurrency, LocalDate date);
}
