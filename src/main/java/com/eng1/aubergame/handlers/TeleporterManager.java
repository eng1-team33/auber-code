package com.eng1.aubergame.handlers;

import com.eng1.aubergame.entities.CollisionRectangle;
import com.eng1.aubergame.entities.systems.Teleporter;

import java.util.ArrayList;

public class TeleporterManager {

    private ArrayList<Teleporter> teleporters = new ArrayList();

    public TeleporterManager(){

    }

    public void add(Teleporter teleporter) {
        this.teleporters.add(teleporter);
    }

}
