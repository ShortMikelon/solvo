package com.github.shortmikelon.solvoassingment.repositories;

import com.github.shortmikelon.solvoassingment.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    List<Transaction> findByLimitExceededTrue();

    @Query(value = """
            SELECT
              SUM(sum)
            FROM
              transactions t
            WHERE
              EXTRACT(MONTH FROM t.datetime) = :month
            AND
              EXTRACT(YEAR FROM t.datetime) = :year
            AND
              t.category = :category
            AND
              limit_exceeded = true
            """,
        nativeQuery = true)
    Double fetchMonthlyExpenses(@Param("month") int month,
                                @Param("year") int year,
                                @Param("category") String category);
}