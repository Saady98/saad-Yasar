package com.campus.api;
import com.campus.api.resources.*;
import com.campus.api.filters.LogFilter;
import com.campus.api.exceptions.mappers.*;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;
@ApplicationPath("/api/v1")
public class SmartCampusApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        classes.add(Discover.class);
        classes.add(RoomSensorResource.class);
        classes.add(Sensor.class);
        classes.add(LogFilter.class);
        classes.add(RoomNotEmptyExceptionMapper.class);
        classes.add(ResourceNotFoundExceptionMapper.class);
        classes.add(SensorUnavailableExceptionMapper.class);
        classes.add(GlobalExceptionMapper.class);
        return classes;
    }
}