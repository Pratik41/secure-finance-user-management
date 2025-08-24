package com.expense.expensetracker.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.expense.expensetracker.entity.Expense;
import com.expense.expensetracker.entity.User;
import com.expense.expensetracker.repository.ExpenseRepository;
import com.expense.expensetracker.repository.UserRepository;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseService(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    // Add Expense - binds to user
    public Expense addExpense(Expense expense, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new RuntimeException("User not found")
        );
        expense.setUser(user);
        return expenseRepository.save(expense);
    }

    // Get All Expenses for user
    public List<Expense> getAllExpensesForUser(Long userId) {
        return expenseRepository.findAllByUserId(userId);
    }

    // Get Expense by Id (ensure owner)
    public Optional<Expense> getExpenseByIdForUser(Long id, Long userId) {
        return expenseRepository.findById(id)
                .filter(expense -> expense.getUser() != null && expense.getUser().getId().equals(userId));
    }

    // Update Expense
    public Expense updateExpense(Long id, Expense updatedExpense, Long userId) {
        return expenseRepository.findById(id)
                .filter(exp -> exp.getUser() != null && exp.getUser().getId().equals(userId))
                .map(expense -> {
                    expense.setTitle(updatedExpense.getTitle());
                    expense.setAmount(updatedExpense.getAmount());
                    expense.setDate(updatedExpense.getDate());
                    expense.setCategory(updatedExpense.getCategory());
                    return expenseRepository.save(expense);
                }).orElseThrow(() -> new RuntimeException("Expense not found or not owned by user"));
    }

    // Delete Expense
    public void deleteExpense(Long id, Long userId) {
        expenseRepository.findById(id)
                .filter(exp -> exp.getUser() != null && exp.getUser().getId().equals(userId))
                .ifPresent(expenseRepository::delete);
    }
}
