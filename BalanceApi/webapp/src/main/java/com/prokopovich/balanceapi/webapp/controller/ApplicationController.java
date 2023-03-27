package com.prokopovich.balanceapi.webapp.controller;

import com.prokopovich.balanceapi.domain.service.BalanceService;
import com.prokopovich.balanceapi.webapp.request.AmountRequest;
import com.prokopovich.balanceapi.webapp.util.RequestCounter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/account")
@RequiredArgsConstructor
public class ApplicationController {

    private final BalanceService balanceService;

    @GetMapping("/{id}")
    public Long getBalance(@PathVariable Long id) {
        RequestCounter.getBalanceCounter++;

        return balanceService.getBalance(id);
    }

    @PostMapping("/{id}")
    public ResponseEntity<String> changeBalance(
        @PathVariable Long id,
        @RequestBody AmountRequest amountRequest
    ) {
        RequestCounter.changeBalanceCounter++;

        balanceService.changeBalance(id, amountRequest.getAmount());

        return new ResponseEntity<>(
                "Bank account balance changed successfully. Account id: " + id,
                HttpStatus.ACCEPTED
        );
    }
}
