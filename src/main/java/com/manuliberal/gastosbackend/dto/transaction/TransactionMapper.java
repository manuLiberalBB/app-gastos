package com.manuliberal.gastosbackend.dto.transaction;

import com.manuliberal.gastosbackend.model.Transaction;

public class TransactionMapper {
    // 1. De request a Entidad
    public static Transaction toEntity(TransactionRequest request) {
        if (request == null) {
            return null;
        }

        Transaction transaction = new Transaction();
        transaction.setAmount(request.amount());
        transaction.setDescription(request.description());
        transaction.setDate(request.date());
        transaction.setDueDate(request.dueDate());
        // Si el cliente no especifica el estado paid, lo inicializamos en false por defecto
        transaction.setPaid(request.paid() != null ? request.paid() : false);
        transaction.setPaymentMethod(request.paymentMethod());
        transaction.setRecurrenceType(request.recurrenceType());
        transaction.setBrandWallet(request.brandWallet());
        transaction.setEmoji(request.emoji());

        return transaction;
    }

    // 2. De entidad a response
    public static TransactionResponse toResponse(Transaction transaction) {
        if (transaction == null) {
            return null;
        }

        Long userId = (transaction.getUser() != null) ? transaction.getUser().getId() : null;
        Long categoryId = (transaction.getCategory() != null) ? transaction.getCategory().getId() : null;
        String categoryName = (transaction.getCategory() != null) ? transaction.getCategory().getName() : null;

        return new TransactionResponse(
                transaction.getId(),
                transaction.getAmount(),
                transaction.getDescription(),
                transaction.getDate(),
                transaction.getDueDate(),
                transaction.isPaid(),
                transaction.getPaymentMethod(),
                transaction.getRecurrenceType(),
                transaction.getBrandWallet(),
                transaction.getEmoji(),
                userId,
                categoryId,
                categoryName
        );
    }
}
