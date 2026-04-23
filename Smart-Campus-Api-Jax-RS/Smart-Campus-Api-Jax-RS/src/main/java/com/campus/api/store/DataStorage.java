package com.campus.api.store;
import com.campus.api.models.RoomData;
import com.campus.api.models.SensorData;
import java.util.HashMap;
import java.util.Map;
public class DataStorage {
    public static final Map<String, RoomData> rooms = new HashMap<>();
    public static final Map<String, SensorData> sensors = new HashMap<>();
}
