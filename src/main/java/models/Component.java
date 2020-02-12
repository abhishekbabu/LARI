package src.main.java.models;

import java.time.Duration;
import java.util.Date;
import java.util.Objects;

public class Component {

    //region Fields

    private int id;
    private String name;
    private String description;
    private String serialNum;
    private Date startDate;
    private Duration flightTime;
    private String location;
    private String history;
    private boolean damaged;
    private boolean active;
    private String system;

    //endregion

    public Component(int id, String name, String description, String serialNum, Date startDate,
                     Duration flightTime, String location, String history, boolean damaged,
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

    //region Observers

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Duration getFlightTime() {
        return flightTime;
    }

    public String getLocation() {
        return location;
    }

    public String getHistory() {
        return history;
    }

    public boolean isDamaged() {
        return damaged;
    }

    public boolean isActive() {
        return active;
    }

    public String getSystem() {
        return system;
    }

    //endregion

    //region Mutators

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setFlightTime(Duration flightTime) {
        this.flightTime = flightTime;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public void setDamaged(boolean damaged) {
        this.damaged = damaged;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setSystem(String system) {
        this.system = system;
    }

    //endregion

    //region Public Methods

    public Component deepCopy() {
        return new Component(id, name, description, serialNum, startDate, flightTime, location,
                history, damaged, active, system);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Component component = (Component) o;
        return id == component.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    //endregion

}
