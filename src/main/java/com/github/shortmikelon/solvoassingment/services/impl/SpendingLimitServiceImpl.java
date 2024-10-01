package com.github.shortmikelon.solvoassingment.services.impl;

import com.github.shortmikelon.solvoassingment.domain.SpendingLimit;
import com.github.shortmikelon.solvoassingment.dto.NewLimit;
import com.github.shortmikelon.solvoassingment.repositories.SpendingLimitRepository;
import com.github.shortmikelon.solvoassingment.services.SpendingLimitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SpendingLimitServiceImpl implements SpendingLimitService {

    private final SpendingLimitRepository spendingLimitRepository;

    @Override
    public Mono<Boolean> isLimitExceeded(double monthlyExpenses, double newTransactionSum, String category) {
        return Mono.fromCallable(() -> {
            Optional<SpendingLimit> optional = spendingLimitRepository.findWithMinLimitDatetime(category);
            SpendingLimit limit = optional.orElseThrow();

            return !(monthlyExpenses + newTransactionSum > limit.getLimitSum());
        });
    }

    @Override
    public Mono<SpendingLimit> newLimit(NewLimit limit) {
        return Mono.fromCallable(() -> {
            SpendingLimit newLimit = new SpendingLimit();

            newLimit.setId(0L);
            newLimit.setLimitCategory(limit.getLimitCategory());
            newLimit.setLimitSum(limit.getLimitSum());
            newLimit.setLimitDatetime(LocalDateTime.now());
            newLimit.setLimitCurrencyShortname(limit.getLimitCurrencyShortname());

            return spendingLimitRepository.save(newLimit);
        });
    }

    @Override
    public Flux<SpendingLimit> getAllLimits() {
        return Flux.fromIterable(spendingLimitRepository.findAll());
    }
}
