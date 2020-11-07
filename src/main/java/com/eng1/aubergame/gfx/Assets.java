package com.eng1.aubergame.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    //List out all image assets used in the game
    //public static BufferedImage player, dirt, grass, stone, tree, ... ;
    public static BufferedImage player;

    public static void init() {
        //SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(path));

        player = ImageLoader.loadImage("/textures/Auber1.png");
        //Crop out image from sheet using SpriteSheet.crop and set equal to BufferedImage
        //player = sheet.crop(0, 0, width, height);
        //width and height are width and height of each image in SpriteSheet
    }

}
