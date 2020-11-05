package com.eng1.aubergame.states;

import java.awt.Graphics;

import com.eng1.aubergame.entities.creatures.Player;
import com.eng1.aubergame.Game;

public class GameState extends State {

    private Player player;

    public GameState(Game game){
        this(game, new Player(game, 100, 100));
    }

    public GameState(Game game, Player player){
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
