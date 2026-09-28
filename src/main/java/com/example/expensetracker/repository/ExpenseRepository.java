package com.example.expensetracker.repository;

import com.example.expensetracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

    List<Expense> findByCategory(String category);

    List<Expense> findByDate(LocalDate date);

    @Query("SELECT COALESCE(SUM(e.amount), 0) FROM Expense e")
    double getTotalExpenses();

    @Query("""
            SELECT e.category, SUM(e.amount)
            FROM Expense e
            GROUP BY e.category
            ORDER BY SUM(e.amount) DESC
            """)
    List<Object[]> getTotalExpensesByCategory();
}