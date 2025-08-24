package com.expense.expensetracker.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expense.expensetracker.entity.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	List<Expense> findAllByUserId(Long userId);
}