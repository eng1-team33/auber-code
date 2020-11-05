package states;

import java.awt.Graphics;

import entities.creatures.Player;

public class GameState extends State {

    private Player player;

    public GameState(){
        player = new Player(100,100);
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
