package com.github.shortmikelon.solvoassingment.services;

import com.github.shortmikelon.solvoassingment.dto.CreateTransactionRequest;
import com.github.shortmikelon.solvoassingment.dto.TransactionResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionService {

    Flux<TransactionResponse> getAllTransactions();

    Flux<TransactionResponse> getTransactionExceededLimit();

    Mono<TransactionResponse> addTransaction(CreateTransactionRequest request);

}

