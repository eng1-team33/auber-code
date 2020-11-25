package com.eng1.aubergame.gfx;

import com.eng1.aubergame.Game;

import java.awt.*;
import java.awt.image.ImageObserver;

import static java.lang.Math.sqrt;

public class Camera {

    private final Game game;

    private float xOffset;
    private float yOffset;
    public static final double DIAGONAL_MOVE_ADJUSTMENT = sqrt(0.5);

    public Camera(Game game, float xOffset, float yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.game = game;
    }

    public void move(float xAmt, float yAmt){
        if (xAmt == 0 || yAmt == 0) {
            this.xOffset += xAmt;
            this.yOffset += yAmt;
        } else {
            this.xOffset += DIAGONAL_MOVE_ADJUSTMENT * xAmt;
            this.yOffset += DIAGONAL_MOVE_ADJUSTMENT * yAmt;
        }
    }

    public void drawOffsetImage(Image image, int x, int y, int width, int height, ImageObserver observer, Graphics g){
        //if (onScreen(x, y, width, height)) {
            g.drawImage(image, (int) (x - this.xOffset), (int) (y - this.yOffset), width, height, null);
        //}
    }

    public void drawOffsetRectangle(int x, int y, int width, int height, Graphics g) {
        g.setColor(Color.RED);
        g.fillRect((int) (x - this.xOffset), (int) (y - this.yOffset), width, height);
        g.setColor(Color.black);
    }

    public float getXOffset() {
        return xOffset;
    }

    public void addXOffset(int i) {
        xOffset += i;
    }

    public void addYOffset(int i) {
        yOffset += i;
    }

    public void setXOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public float getYOffset() {
        return yOffset;
    }

    public void setYOffset(float yOffset) {
        this.yOffset = yOffset;
    }
}
