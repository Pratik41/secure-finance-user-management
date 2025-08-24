package com.expense.expensetracker.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.expense.expensetracker.entity.Expense;
import com.expense.expensetracker.entity.User;
import com.expense.expensetracker.repository.UserRepository;
import com.expense.expensetracker.service.ExpenseService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;
    private final UserRepository userRepository;

    public ExpenseController(ExpenseService expenseService, UserRepository userRepository) {
        this.expenseService = expenseService;
        this.userRepository = userRepository;
    }

    private Long getCurrentUserId(Authentication authentication) {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User not found: " + username)
        );
        return user.getId();
    }

    // Create Expense
    @PostMapping
    public ResponseEntity<Expense> addExpense(@RequestBody Expense expense, Authentication authentication) {
        Long userId = getCurrentUserId(authentication);
        Expense saved = expenseService.addExpense(expense, userId);
        return ResponseEntity.ok(saved);
    }

    // Get All Expenses for logged user
    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses(Authentication authentication) {
        Long userId = getCurrentUserId(authentication);
        return ResponseEntity.ok(expenseService.getAllExpensesForUser(userId));
    }

    // Get Expense By Id (only if owned)
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id, Authentication authentication) {
        Long userId = getCurrentUserId(authentication);
        return expenseService.getExpenseByIdForUser(id, userId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Update Expense
    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(@PathVariable Long id, @RequestBody Expense expense, Authentication authentication) {
        Long userId = getCurrentUserId(authentication);
        return ResponseEntity.ok(expenseService.updateExpense(id, expense, userId));
    }

    // Delete Expense
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id, Authentication authentication) {
        Long userId = getCurrentUserId(authentication);
        expenseService.deleteExpense(id, userId);
        return ResponseEntity.noContent().build();
    }
}
