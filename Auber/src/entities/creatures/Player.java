package entities.creatures;

import java.awt.Graphics;

public class Player extends Creature {

    public Player(float x, float y) {
        super(x, y);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        //g.drawImage(Assets.player, (int) x, (int) y, null)
    }

}
