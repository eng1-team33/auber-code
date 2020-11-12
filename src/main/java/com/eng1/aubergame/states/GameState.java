package com.eng1.aubergame.states;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.creatures.Player;

import java.awt.*;

public class GameState extends State {

    private final Player player;

    public GameState(Game game) {
        this(game, new Player(game, 960, 540));
    }

    public GameState(Game game, Player player) {
        super(game);
        this.player = player;
    }

    @Override
    public void update() {
        player.update();
    }

    @Override
    public void render(Graphics g) {
        player.render(g);
    }

}
