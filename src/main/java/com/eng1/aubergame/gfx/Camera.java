package com.eng1.aubergame.gfx;

import com.eng1.aubergame.Game;

import java.awt.*;
import java.awt.image.ImageObserver;

import static java.lang.Math.sqrt;

public class Camera {

    private final Game game;

    private float xOffset, yOffset, xStart, xEnd, yStart, yEnd;
    public static final double DIAGONAL_MOVE_ADJUSTMENT = sqrt(0.5);

    public Camera(Game game, float xOffset, float yOffset){
        this.xOffset = xOffset;
        this.yOffset = yOffset;
        this.game = game;
        xStart = 0;
        yStart = 0;
        xEnd = this.game.getWidth();
        yEnd = this.game.getHeight();
    }

    public void move(float xAmt, float yAmt){
        if (xAmt == 0 || yAmt == 0) {
            this.xOffset += xAmt;
            this.yOffset += yAmt;
            this.xStart += xAmt;
            this.xEnd += xAmt;
            this.yStart += yAmt;
            this.yEnd += yAmt;
        } else {
            this.xOffset += DIAGONAL_MOVE_ADJUSTMENT * xAmt;
            this.yOffset += DIAGONAL_MOVE_ADJUSTMENT * yAmt;
            this.xStart += DIAGONAL_MOVE_ADJUSTMENT * xAmt;
            this.xEnd += DIAGONAL_MOVE_ADJUSTMENT * xAmt;
            this.yStart += DIAGONAL_MOVE_ADJUSTMENT * yAmt;
            this.yEnd += DIAGONAL_MOVE_ADJUSTMENT * yAmt;
        }
    }

    public void drawOffsetImage(Image image, int x, int y, int width, int height, ImageObserver observer, Graphics g){
        //if (onScreen(x, y, width, height)) {
            g.drawImage(image, (int) (x - this.xOffset), (int) (y - this.yOffset), width, height, null);
        //}
    }

    //public boolean onScreen(int x, int y, int width, int height){
        //boolean visible = true;
        //if(x + width < xStart || x > xEnd || y + height < yStart || y > yEnd){
            //visible = false;
        //}
        //return visible;
    //}

    public float getXOffset() {
        return xOffset;
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
