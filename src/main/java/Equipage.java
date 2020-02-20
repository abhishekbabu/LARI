package src.main.java;

import src.main.java.datatypes.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

/**
 * <b>Equipage</b> encapsulates the interface between the LARI application and
 * its database by acting as a temporary storage during the application
 * lifecycle. Equipage handles the transition of data between the database and
 * the application.
 */
public class Equipage {

    private static final String URL = "jdbc:sqlite:./data/lari.db";

    //region Fields

    /**
     * Set of systems currently stored in the application
     */
    public Set<AFSLSystem> fleet;

    public Set<Component> unconnected;

    //endregion

    //region Constructors

    /**
     * Constructs an equipage with the data from the db
     *
     * @param clear if true, the db is cleared before loading the app (only true during
     *              testing/debugging as of now)
     * @spec.effects Initializes the connection to the database, creates tables in the
     * db if they need to be created, and read the db to create a local storage of the
     * data
     */
    public Equipage(boolean clear) {
        initializeDb();
        if (clear) {
            clearDb();
        }
        createTables();
        initializeFleet();
    }

    //endregion

    //region Private Methods

    /**
     * Returns a connection to the db
     *
     * @return a connection to the db that can be used to execute SQL commands
     */
    private Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            //System.out.println("\nConnected to SQLite database successfully...");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    /**
     * Initializes connection to the db
     *
     * @spec.effects initializes connection to the db and sets foreign keys to be ON
     */
    private void initializeDb() {
        try {
            Connection conn = connect();
            Statement command = conn.createStatement();
            command.execute("PRAGMA foreign_keys=ON");
            //System.out.println("Successfully initialized database connection...");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates Systems and Components tables in the db if they don't exist
     *
     * @spec.effects Creates tables in the db if they don't exist
     */
    private void createTables() {
        String sql1 = "CREATE TABLE IF NOT EXISTS Systems (\n" +
                "   name TEXT PRIMARY KEY,\n" +
                "   description TEXT,\n" +
                "   wing_type TEXT,\n" +
                "   start_date TEXT\n" +
                ");\n";
        String sql2 = "CREATE TABLE IF NOT EXISTS Components (\n" +
                "   id TEXT PRIMARY KEY,\n" +
                "   name TEXT,\n" +
                "   description TEXT,\n" +
                "   serial_number TEXT,\n" +
                "   start_date TEXT,\n" +
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
            //System.out.println("Successfully created tables...");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the set of systems in equipage from the db
     *
     * @spec.effects initializes the set of systems in equipage
     */
    private void initializeFleet() {
        fleet = getAllSystems();
        for (AFSLSystem sys : fleet) {
            sys.setComponents(getAllComponentsForSystem(sys.getName()));
        }
        unconnected = getAllComponentsForSystem("");
    }

    /**
     * FOR TESTING/DEBUGGING PURPOSES ONLY: clears the db
     *
     * @spec.effects clears the db and recreates empty tables in it
     */
    private void clearDb() {
        try {
            Connection conn = connect();
            Statement clearDbCommand = conn.createStatement();
            clearDbCommand.execute("DROP TABLE IF EXISTS Systems");
            clearDbCommand.execute("DROP TABLE IF EXISTS Components");
            //System.out.println("Successfully cleared database...");
            conn.close();
            createTables();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates and returns a LocalDate object equivalent to the given date
     * string
     *
     * @param dateString String formatted date from the db
     * @return a LocalDate representing the specified date string
     */
    private LocalDate dateFromString(String dateString) {
        Scanner sc = new Scanner(dateString);
        sc.useDelimiter("-");
        int year = sc.nextInt();
        int month = sc.nextInt();
        int date = sc.nextInt();
        return LocalDate.of(year, month, date);
    }

    //endregion

    //region Public Methods

    /**
     * Inserts the given system into the db and the fleet
     *
     * @param sys the system to be inserted into the db and fleet
     */
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
            fleet.add(sys);
            //System.out.println("Successfully inserted system: " + sys.getName() + "...");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts the given component into the db and the fleet
     *
     * @param c the component to be inserted into the db and fleet
     */
    public void insertComponent(Component c) {
        String sql = "INSERT INTO Components(id, name, description, serial_number, start_date, flight_time," +
                "location, history, damaged, active, system) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            Connection conn = connect();
            PreparedStatement insertComponentCommand = conn.prepareStatement(sql);
            insertComponentCommand.setString(1, c.getId());
            insertComponentCommand.setString(2, c.getName());
            insertComponentCommand.setString(3, c.getDescription());
            insertComponentCommand.setString(4, c.getSerialNum());
            insertComponentCommand.setString(5, c.getStartDate().toString());
            insertComponentCommand.setDouble(6, c.getFlightTime());
            insertComponentCommand.setString(7, c.getLocation());
            insertComponentCommand.setString(8, c.getHistory());
            insertComponentCommand.setBoolean(9, c.isDamaged());
            insertComponentCommand.setBoolean(10, c.isActive());
            insertComponentCommand.setString(11, c.getSystem());
            insertComponentCommand.executeUpdate();
            if (c.getSystem().isEmpty()) {
                unconnected.add(c);
            } else {
                for (AFSLSystem sys : fleet) {
                    if (sys.getName().equals(c.getSystem())) {
                        sys.addComponent(c);
                    }
                }
            }
            //System.out.println("Successfully inserted component: " + c.getId() + "...");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves and returns the system with the given system name from the db
     *
     * @param sysName the name of the system being searched for
     * @return an AFSLSystem object representing the system that was searched for
     */
    public AFSLSystem getSystem(String sysName) {
        String sql = "SELECT * FROM Systems WHERE name = ?";
        AFSLSystem sys = null;
        try {
            Connection conn = connect();
            PreparedStatement getSystemCommand = conn.prepareStatement(sql);
            getSystemCommand.setString(1, sysName);
            ResultSet rs = getSystemCommand.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                Wingtype wingtype = Wingtype.valueOf(rs.getString("wing_type"));
                LocalDate startDate = dateFromString(rs.getString("start_date"));
                sys = new AFSLSystem(name, description, wingtype, startDate);
                //System.out.println("Successfully retrieved system: " + sysName + "...");
            } else {
                //System.out.println("System: " + sysName + " not retrieved...");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sys;
    }

    /**
     * Retrieves and returns the component with the given system name from the db
     *
     * @param cid the id of the component being searched for
     * @return a Component object representing the component that was searched for
     */
    public Component getComponent(String cid) {
        String sql = "SELECT * FROM Components WHERE id = ?";
        Component c = null;
        try {
            Connection conn = connect();
            PreparedStatement getComponentCommand = conn.prepareStatement(sql);
            getComponentCommand.setString(1, cid);
            ResultSet rs = getComponentCommand.executeQuery();
            if (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String serialNum = rs.getString("serial_number");
                LocalDate startDate = dateFromString(rs.getString("start_date"));
                double flightTime = rs.getDouble("flight_time");
                String location = rs.getString("location");
                String history = rs.getString("history");
                boolean damaged = rs.getBoolean("damaged");
                boolean active = rs.getBoolean("active");
                String system = rs.getString("system");
                c = new Component(id, name, description, serialNum, startDate, flightTime, location, history,
                        damaged, active, system);
                //System.out.println("Successfully retrieved component: " + cid + "...");
            } else {
                //System.out.println("Component: " + cid + " not retrieved...");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

    /**
     * Retrieves and returns a set of all the systems from the db
     *
     * @return a set of AFSLSystem objects representing all objects in the db
     */
    public Set<AFSLSystem> getAllSystems() {
        String sql = "SELECT * FROM Systems";
        Set<AFSLSystem> systems = new HashSet<AFSLSystem>();
        try {
            Connection conn = connect();
            PreparedStatement getAllSystemsCommand = conn.prepareStatement(sql);
            ResultSet rs = getAllSystemsCommand.executeQuery();
            while (rs.next()) {
                String name = rs.getString("name");
                String description = rs.getString("description");
                Wingtype wingtype = Wingtype.valueOf(rs.getString("wing_type"));
                LocalDate startDate = dateFromString(rs.getString("start_date"));
                systems.add(new AFSLSystem(name, description, wingtype, startDate));
            }
            //System.out.println("Retrieved all systems successfully...");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return systems;
    }

    /**
     * Retrieves and returns a set of all the components for the specified system
     * in the db
     *
     * @param sysName 
     * @return
     */
    public Set<Component> getAllComponentsForSystem(String sysName) {
        String sql = "SELECT * FROM Components WHERE system = ?";
        Set<Component> components = new HashSet<Component>();
        try {
            Connection conn = connect();
            PreparedStatement getAllComponentsForSystemCommand = conn.prepareStatement(sql);
            getAllComponentsForSystemCommand.setString(1, sysName);
            ResultSet rs = getAllComponentsForSystemCommand.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String description = rs.getString("description");
                String serialNum = rs.getString("serial_number");
                LocalDate startDate = dateFromString(rs.getString("start_date"));
                double flightTime = rs.getDouble("flight_time");
                String location = rs.getString("location");
                String history = rs.getString("history");
                boolean damaged = rs.getBoolean("damaged");
                boolean active = rs.getBoolean("active");
                String system = rs.getString("system");
                Component c = new Component(id, name, description, serialNum, startDate,
                        flightTime, location, history, damaged, active, system);
                components.add(c);
            }
            //System.out.println("Retrieved all components for system: " + sysName + " successfully...");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return components;
    }

    /**
     * Deletes the specified system from the db
     *
     * @param sysName the name of the system to be deleted
     */
    public void deleteSystem(String sysName) {
        Iterator<AFSLSystem> iter = fleet.iterator();
        while (iter.hasNext()) {
            if (iter.next().getName().equals(sysName)) {
                iter.remove();
            }
        }
        String sql = "DELETE FROM Systems WHERE name = ?";
        try {
            Connection conn = connect();
            PreparedStatement deleteSystemCommand = conn.prepareStatement(sql);
            deleteSystemCommand.setString(1, sysName);
            deleteSystemCommand.executeUpdate();
            //System.out.println("Deleted system: " + sysName + " successfully...");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes the specified component from the db
     *
     * @param cid the id of the component to be deleted
     */
    public void deleteComponent(String cid) {
        for (AFSLSystem sys : fleet) {
            if (sys.containsComponent(cid)) {
                sys.removeComponent(cid);
            }
        }
        String sql = "DELETE FROM Components WHERE id = ?";
        try {
            Connection conn = connect();
            PreparedStatement deleteComponentCommand = conn.prepareStatement(sql);
            deleteComponentCommand.setString(1, cid);
            deleteComponentCommand.executeUpdate();
            //System.out.println("Deleted component: " + cid + " successfully...");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //endregion

}
