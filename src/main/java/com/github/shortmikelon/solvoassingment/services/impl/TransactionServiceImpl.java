package com.github.shortmikelon.solvoassingment.services.impl;

import com.github.shortmikelon.solvoassingment.domain.Transaction;
import com.github.shortmikelon.solvoassingment.dto.CreateTransactionRequest;
import com.github.shortmikelon.solvoassingment.dto.TransactionResponse;
import com.github.shortmikelon.solvoassingment.repositories.TransactionRepository;
import com.github.shortmikelon.solvoassingment.services.ExchangeRateService;
import com.github.shortmikelon.solvoassingment.services.SpendingLimitService;
import com.github.shortmikelon.solvoassingment.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final ExchangeRateService exchangeRateService;

    private final SpendingLimitService spendingLimitService;

    private final TransactionRepository transactionRepository;

    @Override
    public Flux<TransactionResponse> getAllTransactions() {
        return Flux.fromIterable(transactionRepository.findAll())
                .flatMap(this::responseFromTransaction);
    }

    @Override
    public Flux<TransactionResponse> getTransactionExceededLimit() {
        return Flux.fromIterable(transactionRepository.findByLimitExceededTrue())
                .flatMap(this::responseFromTransaction);
    }

    @Override
    public Mono<TransactionResponse> addTransaction(CreateTransactionRequest request) {
        Transaction transaction = new Transaction();

        transaction.setId(0L);
        transaction.setAccountTo(request.getAccountTo());
        transaction.setAccountFrom(request.getAccountFrom());
        transaction.setCurrencyShortname(request.getCurrencyShortname());
        transaction.setSum(request.getSum());
        transaction.setCategory(request.getCategory());

        LocalDateTime now = LocalDateTime.now();
        transaction.setDatetime(now);

        Double monthlyExpenses = transactionRepository.fetchMonthlyExpenses(
                now.getMonthValue(),
                now.getYear(),
                request.getCategory()
        );

        if (monthlyExpenses == null) monthlyExpenses = 0.0;

        return spendingLimitService.isLimitExceeded(
                monthlyExpenses,
                request.getSum(),
                request.getCategory()
        ).flatMap(it -> {
            transaction.setLimitExceeded(it);
            return responseFromTransaction(transactionRepository.save(transaction));
        });
    }

    private Mono<TransactionResponse> responseFromTransaction(Transaction transaction) {
        TransactionResponse response = new TransactionResponse();

        response.setAccountTo(transaction.getAccountTo());
        response.setAccountFrom(transaction.getAccountFrom());
        response.setLimitExceeded(transaction.isLimitExceeded());
        response.setCategory(transaction.getCategory());
        response.setDatetime(transaction.getDatetime());
        response.setCurrencyShortname(transaction.getCurrencyShortname());

        String currency = response.getCurrencyShortname();
        double sum = transaction.getSum();

        Mono<Double> sumInKzt = exchangeRateService.convertSumToKzt(sum, currency, transaction.getDatetime().toLocalDate());
        Mono<Double> sumInRub = exchangeRateService.convertSumToRub(sum, currency, transaction.getDatetime().toLocalDate());
        Mono<Double> sumInUsa = exchangeRateService.convertSumToUsd(sum, currency, transaction.getDatetime().toLocalDate());

        return Mono.zip(sumInKzt, sumInRub, sumInUsa)
                .map(tuple -> {
                    response.setSumInKzt(tuple.getT1());
                    response.setSumInRub(tuple.getT2());
                    response.setSumInUsa(tuple.getT3());
                    return response;
                });
    }
}
