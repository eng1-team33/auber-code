package com.eng1.aubergame.entities;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.creatures.Player;
import com.eng1.aubergame.entities.systems.HealthStation;
import com.eng1.aubergame.entities.systems.System;
import com.eng1.aubergame.entities.systems.Teleporter;
import com.eng1.aubergame.gfx.Assets;
import com.eng1.aubergame.gfx.Camera;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class World extends Entity {

    private final Game game;
    private final Camera camera;
    private Player player;
    private ArrayList<Room> rooms = new ArrayList<>();

    public World(Game game, float x, float y, int width, int height) {
        super(x, y, width, height);
        this.game = game;
        this.camera = game.getCamera();

        //Rooms
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

        rooms.add(infirmary);
        rooms.add(lab);
        rooms.add(cargo1);
        rooms.add(cargo2);
        rooms.add(empty1);
        rooms.add(captains);
        rooms.add(weaponControl);
        rooms.add(missileStorage);
        rooms.add(brig);
        rooms.add(empty2);
        rooms.add(empty3);
        rooms.add(empty4);
        rooms.add(empty5);
        rooms.add(cafeLeft);
        rooms.add(cafeRight);
        rooms.add(decontamination);
        rooms.add(empty6);



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

        //Systems
        System system1 = new System(game,3132,973,82,123);
        System system2 = new System(game,3396,1260,82,79);
        System system3 = new System(game,4022,1034,82,79);
        System system4 = new System(game,4787,1037,82,79);
        System system5 = new System(game,3715,1884,73,73);
        System system6 = new System(game,4034,1849,88,144);
        System system7 = new System(game,4146,1849,88,144);
        System system8 = new System(game,4263,1849,88,144);
        System system9 = new System(game,4269,3012,94,190);

        lab.addSystem(system1);
        lab.addSystem(system2);
        cargo1.addSystem(system3);
        cargo2.addSystem(system4);
        weaponControl.addSystem(system5);
        missileStorage.addSystem(system6);
        missileStorage.addSystem(system7);
        missileStorage.addSystem(system8);
        decontamination.addSystem(system9);

        addSystemToRoom(lab, system1);
        addSystemToRoom(lab, system2);
        addSystemToRoom(cargo1, system3);
        addSystemToRoom(cargo2, system4);
        addSystemToRoom(weaponControl, system5);
        addSystemToRoom(missileStorage, system6);
        addSystemToRoom(missileStorage, system7);
        addSystemToRoom(missileStorage, system8);
        addSystemToRoom(decontamination, system9);

        //Teleporters
        Teleporter infirmaryTeleporter = new Teleporter(game, player, 2774, 1131,  "Infirmary");
        Teleporter cargo1Teleporter = new Teleporter(game, player,4163, 1134, "Cargo Bay");
        Teleporter captainsTeleporter = new Teleporter(game, player, 3275, 1884, "Captains Quarters");
        Teleporter brigTeleporter = new Teleporter(game, player, 4670, 1878, "Brig");

        game.getTeleporterManager().add(brigTeleporter);
        game.getTeleporterManager().add(infirmaryTeleporter);
        game.getTeleporterManager().add(cargo1Teleporter);
        game.getTeleporterManager().add(captainsTeleporter);

        //Health Stations
        HealthStation healthStation = new HealthStation(game, player, 2619, 1061, 114, 167);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        this.camera.drawOffsetImage(Assets.world, (int) x, (int) y, width, height, null, g);
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Room getRandomRoom() {
        java.lang.System.out.println((int)(Math.abs(ThreadLocalRandom.current().nextInt()) % rooms.size()));
        return rooms.get((int)((int)(Math.abs(ThreadLocalRandom.current().nextInt()) % rooms.size())));
    }

    public int[] randomPointInWorld() {
        int[] randomPoint = new int[2];
        Room randomRoom = getRandomRoom();
        randomPoint[0] = (int) (randomRoom.getX() + (randomRoom.getWidth() / 2));
        randomPoint[1] = (int) (randomRoom.getY() + (randomRoom.getHeight() / 2));
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

    public void addSystemToRoom(Room room, System system){
        room.addSystem(system);
        system.setRoom(room);
    }

    public void addRoomConnection(Room room1, Room room2) {
        room1.addAdjacentRoom(room2);
        room2.addAdjacentRoom(room1);
    }

    public int totalSystemsActive(){
        int totalSystems=0;
        for(Room room: rooms){
        totalSystems+=room.activeSystems(); }
        return totalSystems;
    }

}
