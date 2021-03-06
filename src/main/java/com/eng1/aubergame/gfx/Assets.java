package com.eng1.aubergame.gfx;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

public class Assets {

    //List out all image assets used in the game
    //public static BufferedImage player, dirt, grass, stone, tree, ... ;
    public static Font font28;

    public static BufferedImage player, world;
    public static BufferedImage[] playerWalking;

    public static BufferedImage alien1, alien2;
    public static BufferedImage[] alien1Walking, alien2Walking;

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

        font28 = FontLoader.loadFont(Assets.class.getResourceAsStream("/fonts/MandatoryPlaything-nRRd0.ttf"),28);

        playerWalking = new BufferedImage[2];
        playerWalking[0] = ImageLoader.loadImage("/textures/Auber1.png");
        playerWalking[1] = ImageLoader.loadImage("/textures/Auber2.png");

        alien1Walking = new BufferedImage[2];
        alien1Walking[0] = ImageLoader.loadImage("/textures/alien1_low.png");
        alien1Walking[1] = ImageLoader.loadImage("/textures/alien1_high.png");

        alien2Walking = new BufferedImage[2];
        alien2Walking[0] = ImageLoader.loadImage("/textures/alien2_low.png");
        alien2Walking[1] = ImageLoader.loadImage("/textures/alien2_high.png");

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
        alien1 = ImageLoader.loadImage("/textures/alien1_low.png");
        alien2 = ImageLoader.loadImage("/textures/alien2_low.png");
    }

}
