package com.manuliberal.gastosbackend.dto.transaction;

import com.manuliberal.gastosbackend.model.BrandWallet;
import com.manuliberal.gastosbackend.model.PaymentMethod;
import com.manuliberal.gastosbackend.model.RecurrenceType;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionResponse(
        Long id,
        BigDecimal amount,
        String description,
        LocalDate date,
        LocalDate dueDate,
        Boolean paid,
        PaymentMethod paymentMethod,
        RecurrenceType recurrenceType,
        BrandWallet brandWallet,
        String emoji,
        Long userId,
        Long categoryId,
        String categoryName
) {
}