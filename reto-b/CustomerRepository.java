package com.bank.refactor.bad;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class CustomerRepository {
    private final JdbcTemplate jdbc;

    public CustomerRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
	
    public List<Map<String, Object>> search(String name, String status) {
        String sql = "SELECT c.id, c.name, c.status, a.number AS account " +
                     "FROM customers c LEFT JOIN accounts a ON a.customer_id = c.id " +
                     "WHERE 1=1 ";

        if (name != null && !name.isBlank()) {
            sql += " AND c.name LIKE '%" + name + "%' ";
        }
        if (status != null && !status.isBlank()) {
            sql += " AND c.status = '" + status + "' ";
        }
        sql += " ORDER BY c.id DESC";

        return jdbc.queryForList(sql);
    }
}
