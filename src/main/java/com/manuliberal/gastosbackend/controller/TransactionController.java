package com.manuliberal.gastosbackend.controller;

import com.manuliberal.gastosbackend.dto.transaction.TransactionMapper;
import com.manuliberal.gastosbackend.dto.transaction.TransactionRequest;
import com.manuliberal.gastosbackend.dto.transaction.TransactionResponse;
import com.manuliberal.gastosbackend.model.Transaction;
import com.manuliberal.gastosbackend.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TransactionResponse> createTransaction(
            @RequestParam Long userId,
            @RequestParam Long categoryId,
            @Valid @RequestBody TransactionRequest request) {

        Transaction transactionToCreate = TransactionMapper.toEntity(request);
        Transaction savedTransaction = transactionService.createTransaction(userId, categoryId, transactionToCreate);
        TransactionResponse response = TransactionMapper.toResponse(savedTransaction);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // lista las transacciones

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByUserId(@PathVariable Long userId) {
        List<Transaction> transactions = transactionService.getTransactionsByUserId(userId);
        List<TransactionResponse> response = transactions.stream()
                .map(TransactionMapper::toResponse)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/balance")
    public ResponseEntity<BigDecimal> getMonthlyBalance(
            @RequestParam Long userId,
            @RequestParam int month,
            @RequestParam int year) {
        BigDecimal balance = transactionService.calculateMonthlyBalance(userId, month, year);
        return ResponseEntity.ok(balance);
    }

    @PatchMapping("/{id}/toogle-paid")
    public ResponseEntity<TransactionResponse> togglePaid(@PathVariable Long id) {
        Transaction updatedTransaction = transactionService.togglePaid(id);
        TransactionResponse response = TransactionMapper.toResponse(updatedTransaction);
        return ResponseEntity.ok(response);
    }

}
