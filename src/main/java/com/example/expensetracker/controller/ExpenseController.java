package com.example.expensetracker.controller;

import com.example.expensetracker.entity.Expense;
import com.example.expensetracker.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/expenses")
@CrossOrigin
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // ==========================================
    // CREATE
    // ==========================================

    @PostMapping
    public ResponseEntity<Expense> addExpense(
            @Valid @RequestBody Expense expense) {

        Expense savedExpense =
                expenseService.addExpense(expense);

        return ResponseEntity.ok(savedExpense);
    }


    // ==========================================
    // READ ALL
    // ==========================================

    @GetMapping
    public ResponseEntity<List<Expense>> getAllExpenses() {

        return ResponseEntity.ok(
                expenseService.getAllExpenses()
        );
    }


    // ==========================================
    // READ ONE
    // ==========================================

    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(
            @PathVariable Long id) {

        return expenseService
                .getExpenseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    // ==========================================
    // FILTER BY CATEGORY
    // ==========================================

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Expense>> getExpensesByCategory(
            @PathVariable String category) {

        return ResponseEntity.ok(
                expenseService.getExpensesByCategory(category)
        );
    }


    // ==========================================
    // FILTER BY DATE
    // ==========================================

    @GetMapping("/date/{date}")
    public ResponseEntity<List<Expense>> getExpensesByDate(
            @PathVariable LocalDate date) {

        return ResponseEntity.ok(
                expenseService.getExpensesByDate(date)
        );
    }


    // ==========================================
    // TOTAL EXPENSES
    // ==========================================

    @GetMapping("/total")
    public ResponseEntity<Double> getTotalExpenses() {

        return ResponseEntity.ok(
                expenseService.getTotalExpenses()
        );
    }


    // ==========================================
    // TOTAL BY CATEGORY
    // ==========================================

    @GetMapping("/total/category")
    public ResponseEntity<List<Object[]>> getTotalExpensesByCategory() {

        return ResponseEntity.ok(
                expenseService.getTotalExpensesByCategory()
        );
    }


    // ==========================================
    // UPDATE
    // ==========================================

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody Expense expense) {

        Expense updatedExpense =
                expenseService.updateExpense(id, expense);

        return ResponseEntity.ok(updatedExpense);
    }


    // ==========================================
    // DELETE
    // ==========================================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(
            @PathVariable Long id) {

        expenseService.deleteExpense(id);

        return ResponseEntity.noContent().build();
    }
}