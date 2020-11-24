package com.eng1.aubergame.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    //List out all image assets used in the game
    //public static BufferedImage player, dirt, grass, stone, tree, ... ;
    public static BufferedImage player, world;
    public static BufferedImage[] playerWalking;

    public static BufferedImage[] buttonPlay;
    public static BufferedImage[] buttonDemo;
    public static BufferedImage[] buttonSettings;
    public static BufferedImage[] buttonQuit;

    public static BufferedImage[] buttonBrig;
    public static BufferedImage[] buttonInfirmary;
    public static BufferedImage[] buttonCargo;
    public static BufferedImage[] buttonKitchen;
    public static BufferedImage[] buttonLounge;

    public static void init() {
        //SpriteSheet sheet = new SpriteSheet(ImageLoader.loadImage(path));

        playerWalking = new BufferedImage[2];
        playerWalking[0] = ImageLoader.loadImage("/textures/Auber1.png");
        playerWalking[1] = ImageLoader.loadImage("/textures/Auber2.png");

        buttonPlay = new BufferedImage[2];
        buttonPlay[0] = ImageLoader.loadImage("/textures/play.jpg");
        buttonPlay[1] = ImageLoader.loadImage("/textures/playHovered.jpg");

        buttonDemo = new BufferedImage[2];
        buttonDemo[0] = ImageLoader.loadImage("/textures/demo.jpg");
        buttonDemo[1] = ImageLoader.loadImage("/textures/demoHovered.jpg");

        buttonSettings = new BufferedImage[2];
        buttonSettings[0] = ImageLoader.loadImage("/textures/settings.jpg");
        buttonSettings[1] = ImageLoader.loadImage("/textures/settingsHovered.jpg");

        buttonQuit = new BufferedImage[2];
        buttonQuit[0] = ImageLoader.loadImage("/textures/quit.jpg");
        buttonQuit[1] = ImageLoader.loadImage("/textures/quitHovered.jpg");

        buttonBrig = new BufferedImage[2];
        buttonBrig[0] = ImageLoader.loadImage("/textures/brig.png");
        buttonBrig[1] = ImageLoader.loadImage("/textures/brigHovered.png");

        buttonInfirmary = new BufferedImage[2];
        buttonInfirmary[0] = ImageLoader.loadImage("/textures/infirmary.png");
        buttonInfirmary[1] = ImageLoader.loadImage("/textures/infirmaryHovered.png");

        buttonCargo = new BufferedImage[2];
        buttonCargo[0] = ImageLoader.loadImage("/textures/cargo.png");
        buttonCargo[1] = ImageLoader.loadImage("/textures/cargoHovered.png");

        buttonKitchen = new BufferedImage[2];
        buttonKitchen[0] = ImageLoader.loadImage("/textures/kitchen.png");
        buttonKitchen[1] = ImageLoader.loadImage("/textures/kitchenHovered.png");

        buttonLounge = new BufferedImage[2];
        buttonLounge[0] = ImageLoader.loadImage("/textures/lounge.png");
        buttonLounge[1] = ImageLoader.loadImage("/textures/loungeHovered.png");

        world = ImageLoader.loadImage("/textures/map.png");
        player = ImageLoader.loadImage("/textures/Auber1.png");
        //Crop out image from sheet using SpriteSheet.crop and set equal to BufferedImage
        //player = sheet.crop(0, 0, width, height);
        //width and height are width and height of each image in SpriteSheet
    }

}
