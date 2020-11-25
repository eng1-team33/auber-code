package com.eng1.aubergame.handlers;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.World;
import com.eng1.aubergame.entities.creatures.Alien;
import com.eng1.aubergame.entities.creatures.Infiltrator;
import com.eng1.aubergame.entities.creatures.Player;

import java.awt.*;
import java.util.ArrayList;

public class AlienManager {

    private final World world;
    private final Game game;
    private final Player player;

    private ArrayList<Alien> aliens = new ArrayList<>();

    public AlienManager(World world, Game game, Player player) {
        this.world = world;
        this.game = game;
        this.player = player;
        generateAliens(10);
    }

    public void update() {
        for (Alien alien : aliens) {
            alien.update();
        }
    }

    public void render(Graphics g) {
        for (Alien alien : aliens) {
            alien.render(g);
        }
    }

    public void generateAliens(int n) {
        for(int i = 0; i <= n; i++) {
            int[] randomPoint = world.randomPointInWorld();
            Alien alien = new Alien(game, world, player, randomPoint[0], randomPoint[1] + 50);
            this.aliens.add(alien);
        }
    }
}
