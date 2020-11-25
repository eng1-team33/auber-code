package com.eng1.aubergame.entities;

import com.eng1.aubergame.entities.systems.System;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Room extends Entity{

    private ArrayList<System> activeSystems = new ArrayList<>();
    private ArrayList<Room> adjacentRooms = new ArrayList<>();

    private int randomIndex;

    public Room(float x, float y, int width, int height) {
        super(x,y,width,height);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {

    }

    public void addSystem(System system) {
        activeSystems.add(system);
    }

    public void removeSystem(System system) {

    }

    public void addAdjacentRoom(Room room) {
        adjacentRooms.add(room);
    }

    public System getRandomSystem() {
        randomIndex = ThreadLocalRandom.current().nextInt(0, activeSystems.size());
        return activeSystems.get(randomIndex);
    }

    public Room getRandomAdjacentRoom() {
        randomIndex = ThreadLocalRandom.current().nextInt(0, adjacentRooms.size());
        return adjacentRooms.get(randomIndex);
    }

    public Room getRandomRoomExceptPrevious(Room previous) {
        if(this.adjacentRooms.size() == 1){
            return adjacentRooms.get(0);
        } else {
            boolean roomNotFound = true;
            Room nextRoom = this;
            while(roomNotFound) {
                randomIndex = ThreadLocalRandom.current().nextInt(0, adjacentRooms.size());
                nextRoom = adjacentRooms.get(randomIndex);
                if(nextRoom != previous) {
                    roomNotFound = false;
                }
            }
            return nextRoom;
        }
    }

    public int activeSystems() {
        return activeSystems.size();
    }
}
