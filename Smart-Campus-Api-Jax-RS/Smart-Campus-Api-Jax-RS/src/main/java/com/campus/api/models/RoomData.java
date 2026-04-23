package com.campus.api.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class RoomData {
    private String id;
    private String name;
    private int capacity;
    private List<String> sensorIds = new ArrayList<>();

    public RoomData() {}

    public RoomData(String id, String name, int capacity) {
        this.id = id;
        this.name = name;
        this.capacity = capacity;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id == null ? null : id.trim(); }

    public String getName() { return name; }
    public void setName(String name) { this.name = name == null ? null : name.trim(); }

    public int getCapacity() { return capacity; }
    public void setCapacity(int capacity) { this.capacity = capacity; }

    public List<String> getSensorIds() { return sensorIds; }

    public void setSensorIds(List<String> sensorIds) {
        this.sensorIds = new ArrayList<>();
        if (sensorIds == null) {
            return;
        }
        for (String sensorId : sensorIds) {
            addSensorId(sensorId);
        }
    }

    public boolean addSensorId(String sensorId) {
        if (sensorId == null) {
            return false;
        }
        String normalized = sensorId.trim();
        if (normalized.isEmpty() || sensorIds.contains(normalized)) {
            return false;
        }
        sensorIds.add(normalized);
        return true;
    }

    public boolean removeSensorId(String sensorId) {
        if (sensorId == null) {
            return false;
        }
        return sensorIds.remove(sensorId.trim());
    }

    public boolean hasSensors() {
        return sensorIds != null && !sensorIds.isEmpty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof RoomData)) {
            return false;
        }
        RoomData roomData = (RoomData) o;
        return Objects.equals(id, roomData.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "RoomData{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", capacity=" + capacity +
                ", sensorCount=" + (sensorIds == null ? 0 : sensorIds.size()) +
                '}';
    }
}
