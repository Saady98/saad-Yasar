package com.campus.api.resources;
import com.campus.api.models.SensorData;
import com.campus.api.models.RoomData;
import com.campus.api.store.DataStorage;
import com.campus.api.exceptions.ResourceNotFoundException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Path("/sensors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Sensor {
    @GET
    public Response getSensors(@QueryParam("type") String type) {
        List<SensorData> result = new ArrayList<>(DataStorage.sensors.values());
        if (type != null && !type.isEmpty()) {
            result = result.stream().filter(s -> type.equalsIgnoreCase(s.getType())).collect(Collectors.toList());
        }
        return Response.ok(result).build();
    }
    @POST
    public Response createSensor(SensorData sensor) {
        if (sensor.getRoomId() == null || !DataStorage.rooms.containsKey(sensor.getRoomId())) {
            throw new ResourceNotFoundException("Room ID " + sensor.getRoomId() + " does not exist.");
        }
        if (sensor.getId() == null) return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"ID missing\"}").build();
        if (DataStorage.sensors.containsKey(sensor.getId())) {
            return Response.status(Response.Status.CONFLICT).entity("{\"error\":\"Sensor with this ID already exists\"}").build();
        }
        DataStorage.sensors.put(sensor.getId(), sensor);
        RoomData room = DataStorage.rooms.get(sensor.getRoomId());
        if(room.getSensorIds() == null) room.setSensorIds(new ArrayList<>());
        room.getSensorIds().add(sensor.getId());
        return Response.status(Response.Status.CREATED)
                .entity(java.util.Map.of("message", "Sensor registered successfully", "sensor", sensor))
                .build();
    }
    @GET
    @Path("/{sensorId}")
    public Response getSensor(@PathParam("sensorId") String sensorId) {
        SensorData sensor = DataStorage.sensors.get(sensorId);
        if (sensor == null) return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Sensor not found\"}").build();
        return Response.ok(sensor).build();
    }
    
    @Path("/{sensorId}/readings")
    public SensorReading getSensorReadingResource(@PathParam("sensorId") String sensorId) {
        return new SensorReading(sensorId);
    }
}