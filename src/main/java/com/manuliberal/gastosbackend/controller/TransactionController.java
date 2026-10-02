package com.manuliberal.gastosbackend.controller;

import com.manuliberal.gastosbackend.model.Transaction;
import com.manuliberal.gastosbackend.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public Transaction createTransaction(@RequestParam Long userId,@RequestParam Long categoryId,@RequestBody Transaction transaction) {
        return transactionService.createTransaction(userId, categoryId, transaction);
    }

    // lista las transacciones

    @GetMapping("/user/{userId}")
    public List<Transaction> getTransactionsByUserId(@PathVariable Long userId) {
        return transactionService.getTransactionsByUserId(userId);
    }

    @GetMapping("/balance")
    public BigDecimal getMonthlyBalance(@RequestParam Long userId,@RequestParam int month,@RequestParam int year) {
        return transactionService.getMonthlyBalance(userId,month,year);
    }

}
