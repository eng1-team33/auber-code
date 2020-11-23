package com.eng1.aubergame.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    //List out all image assets used in the game
    //public static BufferedImage player, dirt, grass, stone, tree, ... ;
    public static BufferedImage player, world;
    public static BufferedImage[] playerWalking;
    public static BufferedImage[] buttonStart;
    public static BufferedImage[] buttonDemo;
    public static BufferedImage[] buttonSettings;
    public static BufferedImage[] buttonQuit;

    public static void init() {
        //SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(path));

        playerWalking = new BufferedImage[2];
        playerWalking[0] = ImageLoader.loadImage("/textures/Auber1.png");
        playerWalking[1] = ImageLoader.loadImage("/textures/Auber2.png");

        buttonStart = new BufferedImage[2];
        buttonStart[0] = ImageLoader.loadImage("/textures/start.jpg");
        buttonStart[1] = ImageLoader.loadImage("/textures/startHovered.jpg");

        buttonDemo = new BufferedImage[2];
        buttonDemo[0] = ImageLoader.loadImage("/textures/demo.jpg");
        buttonDemo[1] = ImageLoader.loadImage("/textures/demoHovered.jpg");

        buttonSettings = new BufferedImage[2];
        buttonSettings[0] = ImageLoader.loadImage("/textures/settings.jpg");
        buttonSettings[1] = ImageLoader.loadImage("/textures/settingsHovered.jpg");

        buttonQuit = new BufferedImage[2];
        buttonQuit[0] = ImageLoader.loadImage("/textures/quit.jpg");
        buttonQuit[1] = ImageLoader.loadImage("/textures/quitHovered.jpg");

        world = ImageLoader.loadImage("/textures/map.png");
        player = ImageLoader.loadImage("/textures/Auber1.png");
        //Crop out image from sheet using SpriteSheet.crop and set equal to BufferedImage
        //player = sheet.crop(0, 0, width, height);
        //width and height are width and height of each image in SpriteSheet
    }

}
