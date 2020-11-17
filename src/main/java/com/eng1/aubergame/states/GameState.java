package com.eng1.aubergame.states;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.World;
import com.eng1.aubergame.entities.creatures.Player;

import java.awt.*;

public class GameState extends State {

    private final Player player;
    private final World world;

    public GameState(Game game) {
        this(game, new Player(game, 960, 450), new World(game, 0, 0, 3500, 3500));
    }

    public GameState(Game game, Player player, World world) {
        super(game);
        this.player = player;
        this.world = world;
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void render(Graphics g) {
        world.render(g);
        player.render(g);
    }

}
