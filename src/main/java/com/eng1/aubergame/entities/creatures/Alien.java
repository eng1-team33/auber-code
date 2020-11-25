package com.eng1.aubergame.entities.creatures;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.World;
import com.eng1.aubergame.gfx.Animation;
import com.eng1.aubergame.gfx.Assets;
import com.eng1.aubergame.gfx.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.concurrent.ThreadLocalRandom;

public class Alien extends Creature {

    private final Game game;
    private final Player player;
    private final Camera camera;

    private Animation animWalking;

    private int alienImage;
    private BufferedImage idleAlien;

    public Alien(Game game, World world, Player player, float x, float y) {
        super(game, world, x, y, 49, 78);
        this.game = game;
        this.player = player;
        this.camera = game.getCamera();
        this.alienImage = ThreadLocalRandom.current().nextInt(1,3);
        switch(alienImage) {
            case 1:
                idleAlien = Assets.alien1;
                animWalking = new Animation(250, Assets.alien1Walking);
                break;
            case 2:
                idleAlien = Assets.alien2;
                animWalking = new Animation(250, Assets.alien2Walking);
                break;
        }
    }

    @Override
    public void update() {
        if(!arrested) {
            this.setCurrentRoom();
            this.arrest();
            if(idle) {
                this.wander();
            }
            this.updateMovement();
            this.move();
            this.clearMovement();
        }
    }

    @Override
    public void render(Graphics g) {
        this.camera.drawOffsetImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null, g);
    }

    public void arrest() {
        Rectangle playerRec = new Rectangle(Math.round(player.getX()), Math.round(player.getY()), player.getWidth(), player.getHeight());
        Rectangle alienRec = new Rectangle(Math.round(this.getX()), Math.round(this.getY()), this.getWidth(), this.getHeight());
        if (playerRec.intersects(alienRec)) {
            if (game.getKeyManager().isArrestPressed()) {
                arrested = true;
                this.setX(ThreadLocalRandom.current().nextInt(4506, 4893));
                this.setY(ThreadLocalRandom.current().nextInt(1603,2238));
                game.setInnocentsArrested(game.getInnocentsArrested() + 1);
            }
        }
    }

    private BufferedImage getCurrentAnimationFrame(){
        if(xMove != 0 || yMove != 0){
            return animWalking.getCurrentFrame();
        }
        return idleAlien;
    }

}
