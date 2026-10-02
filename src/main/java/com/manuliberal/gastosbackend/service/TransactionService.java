package com.manuliberal.gastosbackend.service;

import com.manuliberal.gastosbackend.model.Category;
import com.manuliberal.gastosbackend.model.CategoryType;
import com.manuliberal.gastosbackend.model.Transaction;
import com.manuliberal.gastosbackend.model.User;
import com.manuliberal.gastosbackend.repository.CategoryRepository;
import com.manuliberal.gastosbackend.repository.TransactionRepository;
import com.manuliberal.gastosbackend.repository.UserRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;

    // Inyectamos los 3 repositorios necesarios
    public TransactionService(TransactionRepository transactionRepository,
                              UserRepository userRepository,
                              CategoryRepository categoryRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
    }

    // Registrar una acción asociada a un usuario y una categoria
    public Transaction createTransaction(Long userId, Long categoryId, Transaction transaction) {

        // 1. Verifica que el usuario exista
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));
        // 2. Verifica que la categoria exista
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Categoria no encontrado con ID: " + categoryId));
        // 3. Asignar relaciones a la transacción
        transaction.setUser(user);
        transaction.setCategory(category);
        // 4. Guardar en BD
        return transactionRepository.save(transaction);
    }
    // Consultar todas las transacciones de un usuario
    public List<Transaction> getTransactionsByUserId(Long userId) {
        return transactionRepository.findByUserId(userId);
    }

    // Calcular el balance de un mes
    public BigDecimal getMonthlyBalance(long userId, int month, int year) {
        // 1. Definir rango de fechas al mes
        LocalDate starDate = LocalDate.of(year,month,1);
        LocalDate endDate = starDate.withDayOfMonth(starDate.lengthOfMonth());

        // 2. Obtenerr transacciones del periodo
        List<Transaction> transactionsOfPeriod = transactionRepository.findByUserIdAndDateBetween(userId,starDate,endDate);

        // 3. calcular el balance

        BigDecimal balance = BigDecimal.ZERO;

        for (Transaction t : transactionsOfPeriod) {
            if (t.getCategory().getType() == CategoryType.INCOME) {
                balance = balance.add(t.getAmount());
                System.out.println(balance);
            } else {
                balance = balance.subtract(t.getAmount());
            }
        }
        return balance;
    }
}
