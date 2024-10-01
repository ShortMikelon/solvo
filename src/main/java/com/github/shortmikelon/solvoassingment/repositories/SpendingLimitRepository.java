package com.github.shortmikelon.solvoassingment.repositories;

import com.github.shortmikelon.solvoassingment.domain.SpendingLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpendingLimitRepository extends JpaRepository<SpendingLimit, Long> {

    @Query(value = "SELECT " +
           "    * " +
           "FROM " +
           "    spending_limits s1 " +
           "WHERE " +
           "    s1.limit_category = :category " +
           "AND" +
           "    s1.limit_datetime = (" +
           "        SELECT " +
           "            MIN(s2.limit_datetime) " +
           "        FROM " +
           "            spending_limits s2" +
           "        WHERE" +
           "               s2.limit_category = :category" +
           "    )",
            nativeQuery = true
    )
    Optional<SpendingLimit> findWithMinLimitDatetime(@Param("category") String category);
}