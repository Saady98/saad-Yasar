package com.campus.api.models;

import java.util.Objects;

public class SensorReading {
    private String id;
    private long timestamp;
    private double value;

    public SensorReading() {}

    public SensorReading(double value) {
        this.value = value;
        this.timestamp = System.currentTimeMillis();
    }

    public SensorReading(String id, long timestamp, double value) {
        this.id = id;
        this.timestamp = timestamp;
        this.value = value;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }

    public double getValue() { return value; }
    public void setValue(double value) { this.value = value; }

    public boolean isTimestampMissing() {
        return timestamp <= 0;
    }

    public void ensureTimestamp() {
        if (isTimestampMissing()) {
            this.timestamp = System.currentTimeMillis();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SensorReading)) {
            return false;
        }
        SensorReading that = (SensorReading) o;
        return timestamp == that.timestamp
                && Double.compare(that.value, value) == 0
                && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timestamp, value);
    }

    @Override
    public String toString() {
        return "SensorReading{" +
                "id='" + id + '\'' +
                ", timestamp=" + timestamp +
                ", value=" + value +
                '}';
    }
}