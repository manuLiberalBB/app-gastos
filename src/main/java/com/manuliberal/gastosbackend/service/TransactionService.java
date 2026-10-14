package com.manuliberal.gastosbackend.service;

import com.manuliberal.gastosbackend.exception.BadRequestException;
import com.manuliberal.gastosbackend.exception.ResourceNotFoundException;
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
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado con ID: " + userId));
        // 2. Verifica que la categoria exista
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrado con ID: " + categoryId));

        // Regla de integridad: la categoría debe pertenecer al mismo usuario
        if (category.getUser() == null || !category.getUser().getId().equals(user.getId())) {
            throw new BadRequestException("La categoría indicada no pertenece al usuario especificado.");
        }

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
    public BigDecimal getMonthlyBalance(Long userId, int month, int year) {
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
    public Transaction tooglePaidStatus(Long id) {
        // 1. Buscamos la transacción existente en la BD
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transacción no encontrada con ID: " + id));

        // 2. Invertimos el valor booleano (!true es false, !false es true)
        transaction.setPaid(!transaction.isPaid());

        // 3. Guardamos (Hibernate detecta que ya tiene ID y ejecuta un UPDATE en la BD)
        return transactionRepository.save(transaction);
    }
}
