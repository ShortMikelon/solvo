package com.github.shortmikelon.solvoassingment.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.shortmikelon.solvoassingment.domain.ExchangeRate;
import com.github.shortmikelon.solvoassingment.domain.ExchangeRatesExternalApiResponse;
import com.github.shortmikelon.solvoassingment.repositories.ExchangeRateRepository;
import com.github.shortmikelon.solvoassingment.services.ExchangeRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Service
@RequiredArgsConstructor
public class WebClientExchangeRateService implements ExchangeRateService {

    private final WebClient webClient;

    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public Mono<Double> convertSumToKzt(double sum, String currentCurrency, LocalDate date) {
        return convertSum(currentCurrency, "KZT", sum, date);
    }

    @Override
    public Mono<Double> convertSumToUsd(double sum, String currentCurrency, LocalDate date) {
        return convertSum(currentCurrency, "USD", sum, date);
    }

    @Override
    public Mono<Double> convertSumToRub(double sum, String currentCurrency, LocalDate date) {
        return convertSum(currentCurrency, "RUB", sum, date);
    }

    private Mono<Double> parse(String responseString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            ExchangeRatesExternalApiResponse response = objectMapper.readValue(responseString, ExchangeRatesExternalApiResponse.class);

            LocalDate date = Instant.ofEpochSecond(response.getTimeLastUpdate())
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
            ExchangeRate newExchangeRate = new ExchangeRate(
                    0L,
                    response.getBaseCode(),
                    response.getTargetCode(),
                    response.getConversionRate(),
                    date
            );
            exchangeRateRepository.save(newExchangeRate);

            return Mono.just(response.getConversionResult());
        } catch (JsonProcessingException e) {
            return Mono.error(new RuntimeException("Ошибка при обработке JSON", e));
        }
    }

    private Mono<Double> convertSum(String currentCurrency, String targetCurrency, double sum, LocalDate date) {
        if (currentCurrency.equalsIgnoreCase(targetCurrency)) return Mono.just(sum);

        return Mono.justOrEmpty(exchangeRateRepository.findByBaseCurrencyAndTargetCurrency(currentCurrency, targetCurrency, date))
                .flatMap(exchangeRate -> Mono.just(sum * exchangeRate.getRate()))
                .switchIfEmpty(
                    webClient
                        .get()
                        .uri("pair/" + currentCurrency + "/" + targetCurrency + "/" + sum)
                        .retrieve()
                        .bodyToMono(String.class)
                        .flatMap(this::parse)
                );
    }
}
