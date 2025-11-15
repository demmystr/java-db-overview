package com.example;

public class Main {
    public static void main(String[] args) {

        try {
            DatabaseConnector db = new DatabaseConnector();
            db.connect();
            db.createTable();

            EmployeeDAO dao = new EmployeeDAO(db);

            // ADD
            dao.addEmployee("Alex", 30, "Manager", 3500.00);
            dao.addEmployee("Maria", 25, "Developer", 4200.50);
            dao.getEmployees();

            // UPDATE
            dao.updateEmployee(1, "Alex", 31, "Senior Manager", 5000.00);
            dao.getEmployees();

            // DELETE
            dao.deleteEmployee(2);
            dao.getEmployees();

            db.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
