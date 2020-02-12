package src.main.java;

import src.main.java.models.*;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Equipage {

    //region Fields

    private List<AFSLSystem> fleet;

    //endregion

    //region Constructors

    public Equipage() {
        initializeDb();
        createTables();
    }

    //endregion

    //region Private Methods

    private Connection connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:./data/lari.db";
            conn = DriverManager.getConnection(url);
            System.out.println("Connected to SQLite database successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    private void initializeDb() {
        try {
            Statement command = connect().createStatement();
            command.execute("PRAGMA foreign_keys=ON");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createTables() {
        String sql1 = "CREATE TABLE IF NOT EXISTS Systems (\n" +
                "   name TEXT PRIMARY KEY,\n" +
                "   description TEXT,\n" +
                "   wing_type TEXT,\n" +
                "   start_date TEXT\n" +
                ");\n";
        String sql2 = "CREATE TABLE IF NOT EXISTS Components (\n" +
                "   id INTEGER PRIMARY KEY,\n" +
                "   name TEXT,\n" +
                "   start_date TEXT,\n" +
                "   description TEXT,\n" +
                "   serial_number TEXT,\n" +
                "   flight_time FLOAT,\n" +
                "   location TEXT,\n" +
                "   history TEXT,\n" +
                "   damaged NUMERIC,\n" +
                "   active NUMERIC,\n" +
                "   system TEXT NULL REFERENCES Systems(name)\n" +
                ");";
        try {
            Connection conn = connect();
            PreparedStatement createSystemsTable = conn.prepareStatement(sql1);
            PreparedStatement createComponentsTable = conn.prepareStatement(sql2);
            createSystemsTable.execute();
            createComponentsTable.execute();
            System.out.println("Successfully created tables");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //endregion

    //region Public Methods

    public void insertSystem(AFSLSystem sys) {
        String sql = "INSERT INTO Systems(name, description, wing_type, start_date) VALUES(?,?,?,?)";
        try {
            Connection conn = connect();
            PreparedStatement insertSystemCommand = conn.prepareStatement(sql);
            insertSystemCommand.setString(1, sys.getName());
            insertSystemCommand.setString(2, sys.getDescription());
            insertSystemCommand.setString(3, sys.getWingtype().toString());
            insertSystemCommand.setString(4, sys.getStartDate().toString());
            insertSystemCommand.executeUpdate();
            System.out.println("Successfully inserted system");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //endregion

    public static void main(String[] args) {
        Equipage e = new Equipage();
        e.insertSystem(new AFSLSystem("AFSLSystem", "abhibeast", Wingtype.Octo,
                new Date(2000, 10, 4)));
    }

}
