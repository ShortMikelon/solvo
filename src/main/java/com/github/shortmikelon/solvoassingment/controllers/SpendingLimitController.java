package com.github.shortmikelon.solvoassingment.controllers;

import com.github.shortmikelon.solvoassingment.domain.SpendingLimit;
import com.github.shortmikelon.solvoassingment.dto.NewLimit;
import com.github.shortmikelon.solvoassingment.services.SpendingLimitService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/limit")
@RequiredArgsConstructor
public class SpendingLimitController {

    private final SpendingLimitService spendingLimitService;

    @Operation(
            summary = "Get all limits",
            description = "Returns a list of all limits"
    )
    @GetMapping
    public Flux<SpendingLimit> getAllLimits() {
        return spendingLimitService.getAllLimits();
    }

    @Operation(
            summary = "New limit",
            description = "Added new limit"
    )
    @PostMapping("/new-limit")
    public Mono<ResponseEntity<?>> addNewLimit(@RequestBody NewLimit limit) {
        return spendingLimitService.newLimit(limit).map(ResponseEntity::ok);
    }
}
