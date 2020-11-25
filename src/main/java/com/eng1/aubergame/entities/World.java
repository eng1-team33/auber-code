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

        //Rooms sorted top left to bottom right, read like a book
        Room infirmary = new Room(2607, 967, 258, 387);
        Room lab = new Room(3117, 967, 384, 387);
        Room cargo1 = new Room(4002, 967, 381, 381);
        Room cargo2 = new Room(4509, 970, 381, 381);
        Room empty1 = new Room(2610, 1729, 381, 380);
        Room captains = new Room(3117, 1603, 381, 635);
        Room weaponControl = new Room(3618, 1726, 261, 383);
        Room missileStorage = new Room(4002, 1603, 381, 635);
        Room brig = new Room(4506, 1603, 387, 635);
        Room empty2 = new Room(2610, 2487, 381, 384);
        Room empty3 = new Room(3117, 2484, 381, 387);
        Room empty4 = new Room(4002, 2487, 381, 384);
        Room empty5 = new Room(4509, 2487, 381, 384);
        Room cafeLeft = new Room(2610, 2994, 443, 255);
        Room cafeRight = new Room(3053, 2994, 445, 255);
        Room decontamination = new Room(4002, 2994, 381, 255);
        Room empty6 = new Room(4509, 2994, 381, 255);

        addRoomConnection(infirmary, lab);
        addRoomConnection(lab, cargo1);
        addRoomConnection(lab, captains);
        addRoomConnection(cargo1, cargo2);
        addRoomConnection(cargo1, missileStorage);
        addRoomConnection(cargo2, brig);
        addRoomConnection(empty1, captains);
        addRoomConnection(captains, weaponControl);
        addRoomConnection(weaponControl, missileStorage);
        addRoomConnection(missileStorage, brig);
        addRoomConnection(empty1, empty2);
        addRoomConnection(captains, empty3);
        addRoomConnection(empty2, cafeLeft);
        addRoomConnection(empty2, empty3);
        addRoomConnection(cafeLeft, cafeRight);
        addRoomConnection(cafeRight, empty3);
        addRoomConnection(empty3, empty4);
        addRoomConnection(missileStorage, empty4);
        addRoomConnection(brig, empty5);
        addRoomConnection(empty4, decontamination);
        addRoomConnection(empty4, empty5);
        addRoomConnection(empty5, empty6);

        //Add all systems to each room.systems

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

    public void addRoomConnection(Room room1, Room room2){
        room1.addAdjacentRoom(room2);
        room2.addAdjacentRoom(room1);
    public int totalSystemsActive(){
        int totalSystems=0;
        for(Room room: rooms){
        totalSystems+=room.activeSystems(); }
        return totalSystems;
    }

}
