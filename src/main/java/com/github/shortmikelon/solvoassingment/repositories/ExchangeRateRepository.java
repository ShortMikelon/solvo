package com.github.shortmikelon.solvoassingment.repositories;

import com.github.shortmikelon.solvoassingment.domain.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRate, Long> {

    @Query(value = """
            SELECT
                *
            FROM
                exchange_rates er
            WHERE
                er.base_currency = :baseCurrency
            AND
                er.target_currency = :targetCurrency
            AND
                er.date = :date
            """, nativeQuery = true)
    ExchangeRate findByBaseCurrencyAndTargetCurrency(
            String baseCurrency,
            String targetCurrency,
            LocalDate date
    );
}