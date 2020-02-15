package src.main.java.models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

/**
 * <b>Component</b> represents a component at AFSL that the application keeps
 * track of. A component can be attached to a system or can exist on its own
 */
public class Component {

    //region Fields

    /**
     * The unique id of this component
     */
    private final String id;

    /**
     * The name of this component
     */
    private String name;

    /**
     * The description of this component
     */
    private String description;

    /**
     * The serial number of this component
     */
    private String serialNum;

    /**
     * The starting date of this component's use
     */
    private LocalDate startDate;

    /**
     * The flight time of this component
     */
    private double flightTime;

    /**
     * The location of this component
     */
    private String location;

    /**
     * The history of this component
     */
    private String history;

    /**
     * Whether or not this component is damaged
     */
    private boolean damaged;

    /**
     * Whether or not this component is active
     */
    private boolean active;

    /**
     * The name of the system this component is connected to
     */
    private String system;

    //endregion

    //region Constructors

    /**
     * Constructs a Component with the given parameters
     *
     * @param id the unique id of the component
     * @param name the name of the component
     * @param description the description of the component
     * @param serialNum the serial number of the component
     * @param startDate the starting date of the component
     * @param flightTime the flight time of the component
     * @param location the location of the component
     * @param history the history of the component
     * @param damaged whether or not the component is damaged
     * @param active whether or not the component is active
     * @param system the name of the system this component is attached to
     */
    public Component(String id, String name, String description, String serialNum, LocalDate startDate,
                     double flightTime, String location, String history, boolean damaged,
                     boolean active, String system) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.serialNum = serialNum;
        this.startDate = startDate;
        this.flightTime = flightTime;
        this.location = location;
        this.history = history;
        this.damaged = damaged;
        this.active = active;
        this.system = system;
    }

    /**
     * Constructs a Component with the given parameters and a unique id
     *
     * @param name the name of the component
     * @param description the description of the component
     * @param serialNum the serial number of the component
     * @param startDate the starting date of the component
     * @param flightTime the flight time of the component
     * @param location the location of the component
     * @param history the history of the component
     * @param damaged whether or not the component is damaged
     * @param active whether or not the component is active
     * @param system the name of the system this component is attached to
     */
    public Component(String name, String description, String serialNum, LocalDate startDate,
                     double flightTime, String location, String history, boolean damaged,
                     boolean active, String system) {
        this(UUID.randomUUID().toString(), name, description, serialNum, startDate, flightTime, location, history,
                damaged, active, system);
    }

    /**
     * FOR TESTING/DEBUGGING PURPOSES ONLY: Constructs a default Component
     * attached to the system with the given system name
     *
     * @param sysName the name of the system the component is attached to
     */
    public Component(String sysName) {
        this("default", "", "", LocalDate.now(), 0.0, "", "",
                false, false, sysName);
    }

    /**
     * FOR TESTING/DEBUGGING PURPOSES ONLY: Constructs a default Component
     */
    public Component() {
        this("default", "", "", LocalDate.now(),
                0.0, "", "", false, false, "");
    }

    //endregion

    //region Observers

    /**
     * Returns the unique id of this component
     *
     * @return the unique id of this component
     */
    public String getId() {
        return id;
    }

    /**
     * Returns the name of this component
     *
     * @return the name of this component
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of this component
     *
     * @return the description of this component
     */
    public String getDescription() {
        return description;
    }

    /**
     * Returns the serial number of this component
     *
     * @return the serial number of this component
     */
    public String getSerialNum() {
        return serialNum;
    }

    /**
     * Returns the starting date of this component
     *
     * @return the starting date of this component
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Returns the flight time of this component
     *
     * @return the flight time of this component
     */
    public double getFlightTime() {
        return flightTime;
    }

    /**
     * Returns the location of this component
     *
     * @return the location of this component
     */
    public String getLocation() {
        return location;
    }

    /**
     * Returns the history of this component
     *
     * @return the history of this component
     */
    public String getHistory() {
        return history;
    }

    /**
     * Returns true if this component is damaged, false otherwise
     *
     * @return true if this component is damaged, false otherwise
     */
    public boolean isDamaged() {
        return damaged;
    }

    /**
     * Returns true if this component is active, false otherwise
     *
     * @return true if this component is active, false otherwise
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Returns the name of the system this component is attached to
     *
     * @return the name of the system this component is attached to
     */
    public String getSystem() {
        return system;
    }

    //endregion

    //region Mutators

    /**
     * Sets the name of the component
     *
     * @param name the name of the component
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the description of the component
     *
     * @param description the description of the component
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Sets the serial number of the component
     *
     * @param serialNum the serial number of the component
     */
    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    /**
     * Sets the starting date of the component
     *
     * @param startDate the starting date of the component
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Sets the flight time of the component
     *
     * @param flightTime the flight time of the component
     */
    public void setFlightTime(double flightTime) {
        this.flightTime = flightTime;
    }

    /**
     * Sets the location of the component
     *
     * @param location the location of the component
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets the history of the component
     *
     * @param history the history of the component
     */
    public void setHistory(String history) {
        this.history = history;
    }

    /**
     * Sets whether or not the component is damaged
     *
     * @param damaged whether or not the component is damaged
     */
    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    /**
     * Sets whether or not the component is active
     *
     * @param active whether or not the component is active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Sets the name of the system this component is attached to
     *
     * @param system the name of the system this component is attached to
     */
    public void setSystem(String system) {
        this.system = system;
    }

    //endregion

    //region Public Methods

    /**
     * Returns a string representation of this component i.e. its id
     *
     * @return the id of the component
     */
    public String toString() {
        return getId();
    }

    /**
     * Returns true if and only if the specified component is equal to this
     * component, false otherwise
     *
     * @param o the object to compare to this component
     * @return true if and only if the specified component has the same id
     * as this component, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return id.equals(component.id);
    }

    /**
     * Returns the hash code value of this component
     *
     * @return the hash code value of this component
     */
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //endregion

}
