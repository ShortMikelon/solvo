package com.github.shortmikelon.solvoassingment.services;

import com.github.shortmikelon.solvoassingment.domain.SpendingLimit;
import com.github.shortmikelon.solvoassingment.dto.NewLimit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface SpendingLimitService {
    Mono<Boolean> isLimitExceeded(double monthlyExpenses,
                                  double newTransactionSum,
                                  String category);

    Mono<SpendingLimit> newLimit(NewLimit limit);

    Flux<SpendingLimit> getAllLimits();
}
