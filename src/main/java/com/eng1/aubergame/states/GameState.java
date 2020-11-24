package com.eng1.aubergame.states;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.World;
import com.eng1.aubergame.entities.creatures.Player;
import com.eng1.aubergame.handlers.InfiltratorManager;

import java.awt.*;

public class GameState extends State {

    private final Player player;
    private final World world;
    //private final InfiltratorManager infiltratorManager;


    public GameState(Game game) {
        super(game);
        this.world = new World(game, 0, 0, 7500, 4219);
        this.player = new Player(game, world, 1960, 400);
        //this.infiltratorManager = new InfiltratorManager(world, game);
    }

    @Override
    public void update() {
        player.update();
        //infiltratorManager.update();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }

}
