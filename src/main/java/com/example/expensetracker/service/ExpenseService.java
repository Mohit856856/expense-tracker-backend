package com.example.expensetracker.service;

import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    // Add a new expense
    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    // Get all expenses
    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    // Get expense by ID
    public Optional<Expense> getExpenseById(Long id) {
        return expenseRepository.findById(id);
    }

    // Get expenses by category
    public List<Expense> getExpensesByCategory(String category) {
        return expenseRepository.findByCategory(category);
    }

    // Get expenses by date
    public List<Expense> getExpensesByDate(LocalDate date) {
        return expenseRepository.findByDate(date);
    }

    // Get total spending
    public double getTotalExpenses() {
        return expenseRepository.getTotalExpenses();
    }

    // Get spending grouped by category
    public List<Object[]> getTotalExpensesByCategory() {
        return expenseRepository.getTotalExpensesByCategory();
    }

    // Update an expense
    public Expense updateExpense(Long id, Expense updatedExpense) {

        Expense existingExpense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Expense not found"));

        existingExpense.setDescription(
                updatedExpense.getDescription()
        );

        existingExpense.setAmount(
                updatedExpense.getAmount()
        );

        existingExpense.setCategory(
                updatedExpense.getCategory()
        );

        existingExpense.setDate(
                updatedExpense.getDate()
        );

        return expenseRepository.save(existingExpense);
    }

    // Delete an expense
    public void deleteExpense(Long id) {

        Expense existingExpense =
                expenseRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException("Expense not found"));

        expenseRepository.delete(existingExpense);
    }
}