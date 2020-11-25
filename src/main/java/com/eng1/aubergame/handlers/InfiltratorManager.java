package com.eng1.aubergame.handlers;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.World;
import com.eng1.aubergame.entities.creatures.Infiltrator;
import com.eng1.aubergame.entities.creatures.Player;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class InfiltratorManager {

    private ArrayList<Infiltrator> activeInfiltrators = new ArrayList<>();
    private World world;
    private final Game game;
    private long lastTime, timer;
    private int randomTime;
    private int randomIndex;
    private final Player player;

    public InfiltratorManager(World world, Game game, Player player) {
        this.world = world;
        this.game = game;
        this.player = player;
        this.generateInfiltrators(game.getInfiltratorsInGame());
        timer = 0;
        lastTime = System.currentTimeMillis();
        randomTime = 15000;
    }

    public void addInfiltrator(Infiltrator infiltrator) {
        this.activeInfiltrators.add(infiltrator);
    }

    public void removeInfiltrator(Infiltrator infiltrator) {
        this.activeInfiltrators.remove(infiltrator);
    }

    public int infiltratorsRemaining() {
        return activeInfiltrators.size();
    }

    public void update(){
        for (Infiltrator infiltrator : activeInfiltrators) {
            infiltrator.update();
        }
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();
        if(timer > randomTime && !game.getSabotageInProgress()) {
            randomTime = ThreadLocalRandom.current().nextInt(15000,  25000);
            randomIndex = ThreadLocalRandom.current().nextInt(0, activeInfiltrators.size());
            activeInfiltrators.get(randomIndex).setCurrentState(1);
        }
    }
    public void render(Graphics g) {
        for (Infiltrator infiltrator : activeInfiltrators) {
            infiltrator.render(g);
        }
    }

    public void generateInfiltrators(int n) {
        for(int i = 0; i < n; i++) {
            int[] randomPoint = world.randomPointInWorld();
            Infiltrator infiltrator = new Infiltrator(game, world, player, randomPoint[0], randomPoint[1] + 50);
            this.activeInfiltrators.add(infiltrator);
        }
    }
}