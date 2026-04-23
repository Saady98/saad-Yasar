package com.campus.api.resources;
import com.campus.api.models.RoomData;
import com.campus.api.store.DataStorage;
import com.campus.api.exceptions.RoomNotEmptyException;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.ArrayList;
@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomSensorResource {
    @GET
    public Response getRooms() {
        return Response.ok(new ArrayList<>(DataStorage.rooms.values())).build();
    }
    @POST
    public Response createRoom(RoomData room) {
        if (room.getId() == null) {
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"error\":\"ID missing\"}").build();
        }
        if (DataStorage.rooms.containsKey(room.getId())) {
            return Response.status(Response.Status.CONFLICT).entity("{\"error\":\"Room with this ID already exists\"}").build();
        }
        DataStorage.rooms.put(room.getId(), room);
        return Response.status(Response.Status.CREATED)
                .entity(java.util.Map.of("message", "Room created successfully", "room", room))
                .build();
    }
    @GET
    @Path("/{roomId}")
    public Response getRoom(@PathParam("roomId") String roomId) {
        RoomData room = DataStorage.rooms.get(roomId);
        if (room == null) return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Room not found\"}").build();
        return Response.ok(room).build();
    }
    @DELETE
    @Path("/{roomId}")
    public Response deleteRoom(@PathParam("roomId") String roomId) {
        RoomData room = DataStorage.rooms.get(roomId);
        if (room == null) return Response.status(Response.Status.NOT_FOUND).entity("{\"error\":\"Room not found\"}").build();
        if (room.getSensorIds() != null && !room.getSensorIds().isEmpty()) {
            throw new RoomNotEmptyException("Room " + roomId + " cannot be deleted as it has active sensors.");
        }
        DataStorage.rooms.remove(roomId);
        return Response.noContent().build();
    }
}