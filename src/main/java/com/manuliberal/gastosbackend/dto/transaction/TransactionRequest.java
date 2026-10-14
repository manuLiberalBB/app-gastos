package com.manuliberal.gastosbackend.dto.transaction;

import com.manuliberal.gastosbackend.model.BrandWallet;
import com.manuliberal.gastosbackend.model.PaymentMethod;
import com.manuliberal.gastosbackend.model.RecurrenceType;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRequest(
        @NotNull(message = "El monto no puede ser nulo")
        @Positive(message = "El monto debe ser mayor a 0")
        BigDecimal amount,

        String description,

        @NotNull(message = "La fecha de la transacción es obligatoria")
        LocalDate date,

        LocalDate dueDate,

        Boolean paid,

        @NotNull(message = "El método de pago es obligatorio")
        PaymentMethod paymentMethod,

        @NotNull(message = "El tipo de recurrencia es obligatorio")
        RecurrenceType recurrenceType,

        BrandWallet brandWallet,

        String emoji
) {
}