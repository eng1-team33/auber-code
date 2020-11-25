package com.eng1.aubergame.entities;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.gfx.Assets;
import com.eng1.aubergame.gfx.Camera;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class World extends Entity {

    private final Game game;
    private final Camera camera;
    private Room[] rooms = new Room[16];

    public World(Game game, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.game = game;
        this.camera = game.getCamera();

        //TODO
        //Add all rooms to world.rooms
        //Add all systems to each room.systems
        //Add adjacent rooms to each room.adjacent
    }


    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        this.camera.drawOffsetImage(Assets.world, (int) x, (int) y, width, height, null, g);
    }

    public Room getRandomRoom() {
        return rooms[(int)(System.currentTimeMillis() % rooms.length)];
    }

    public int[] randomPointInWorld() {
        int[] randomPoint = new int[2];
        Room randomRoom = getRandomRoom();
        randomPoint[0] = ThreadLocalRandom.current().nextInt((int)randomRoom.getX() + 10, (int)randomRoom.getX() + randomRoom.getWidth() - 10);
        randomPoint[1] = ThreadLocalRandom.current().nextInt((int)randomRoom.getY() + 10, (int)randomRoom.getY() + randomRoom.getHeight() - 10);
        return randomPoint;
    }

    public Room getCurrentRoom(float x, float y) {
        for(Room room : rooms) {
            if (x > room.getX() && x < room.getX() + room.getWidth() && y > room.getY() && y < room.getY() + room.getHeight()) {
                return room;
            }
        }
        return null;
    }

    public int totalSystemsActive(){
        int totalSystems=0;
        for(Room room: rooms){
        totalSystems+=room.activeSystems(); }
        return totalSystems;
    }

}
