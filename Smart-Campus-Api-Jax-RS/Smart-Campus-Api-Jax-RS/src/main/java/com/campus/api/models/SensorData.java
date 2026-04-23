package com.campus.api.models;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SensorData {
    private String id;
    private String name;
    private String type;
    private String status;
    private double currentValue;
    private String roomId;
    private List<SensorReading> readings = new ArrayList<>();
    public SensorData() {}

    public String getId() { return id; }
    public void setId(String id) { this.id = id == null ? null : id.trim(); }

    public String getName() { return name; }
    public void setName(String name) { this.name = name == null ? null : name.trim(); }

    public String getType() { return type; }
    public void setType(String type) { this.type = type == null ? null : type.trim(); }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status == null ? null : status.trim(); }

    public double getCurrentValue() { return currentValue; }
    public void setCurrentValue(double currentValue) { this.currentValue = currentValue; }

    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId == null ? null : roomId.trim(); }

    public List<SensorReading> getReadings() { return readings; }

    public void setReadings(List<SensorReading> readings) {
        this.readings = readings == null ? new ArrayList<>() : new ArrayList<>(readings);
    }

    public void addReading(SensorReading reading) {
        if (reading == null) {
            return;
        }
        if (this.readings == null) {
            this.readings = new ArrayList<>();
        }
        this.readings.add(reading);
        this.currentValue = reading.getValue();
    }

    public boolean hasReadings() {
        return readings != null && !readings.isEmpty();
    }

    public boolean isInMaintenance() {
        return "MAINTENANCE".equalsIgnoreCase(status);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SensorData)) {
            return false;
        }
        SensorData that = (SensorData) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", currentValue=" + currentValue +
                ", roomId='" + roomId + '\'' +
                ", readingsCount=" + (readings == null ? 0 : readings.size()) +
                '}';
    }
}
