package com.github.shortmikelon.solvoassingment.controllers;

import com.github.shortmikelon.solvoassingment.dto.CreateTransactionRequest;
import com.github.shortmikelon.solvoassingment.dto.TransactionResponse;
import com.github.shortmikelon.solvoassingment.services.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @Operation(
            summary = "Get all transaction",
            description = "Returns a list of all transactions"
    )
    @GetMapping
    public Flux<TransactionResponse> getAllTransaction() {
        return transactionService.getAllTransactions();
    }

    @Operation(
            summary = "Get transactions exceeding the limit",
            description = "Returns a list of transactions exceeding the limit"
    )
    @GetMapping("/limit-exceeded")
    public Flux<TransactionResponse> getTransactionLimitExceeded() {
        return transactionService.getTransactionExceededLimit();
    }

    @Operation(
            summary = "Adds a new transaction",
            description = "Adds a new transaction"
    )
    @PostMapping("/add-transaction")
    public Mono<ResponseEntity<?>> addTransaction(@RequestBody CreateTransactionRequest request) {
        return transactionService.addTransaction(request)
                .map(ResponseEntity::ok);
    }
}
