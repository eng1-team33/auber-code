package com.eng1.aubergame.handlers;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.CollisionRectangle;
import com.eng1.aubergame.entities.World;
import com.eng1.aubergame.entities.creatures.Player;
import com.eng1.aubergame.entities.systems.Teleporter;
import com.eng1.aubergame.gfx.Assets;
import com.eng1.aubergame.ui.ClickListener;
import com.eng1.aubergame.ui.UIImageButton;
import com.eng1.aubergame.ui.UIManager;

import java.awt.*;
import java.util.ArrayList;

public class TeleporterManager {

    private ArrayList<Teleporter> teleporters = new ArrayList();
    private boolean menuActive = false;
    private UIManager teleportButtons;
    private Game game;

    public TeleporterManager(Game game){
        this.game = game;
    }

    public boolean isMenuActive() {
        return menuActive;
    }

    public void add(Teleporter teleporter) {
        this.teleporters.add(teleporter);
    }


    public void teleportMenu(Player player) {
        menuActive = true;
        teleportButtons.addObject(new UIImageButton(710, 50, 300, 150, Assets.buttonBrig, new ClickListener() {
            @Override
            public void onClick() {
                game.getMouseManager().setUIManager(null);
                player.setX(teleporters.get(0).getX());
                player.setY(teleporters.get(0).getY());
            }
        }));
        teleportButtons.addObject(new UIImageButton(710, 250, 300, 150, Assets.buttonInfirmary, new ClickListener() {
            @Override
            public void onClick() {
                game.getMouseManager().setUIManager(null);

                player.setX(teleporters.get(1).getX());
                player.setY(teleporters.get(1).getY());
            }
        }));
        teleportButtons.addObject(new UIImageButton(710, 450, 300, 150, Assets.buttonCargo, new ClickListener() {
            @Override
            public void onClick() {
                game.getMouseManager().setUIManager(null);
                player.setX(teleporters.get(2).getX());
                player.setY(teleporters.get(2).getY());
            }
        }));
        teleportButtons.addObject(new UIImageButton(710, 650, 300, 150, Assets.buttonLounge, new ClickListener() {
            @Override
            public void onClick() {
                game.getMouseManager().setUIManager(null);
                player.setX(teleporters.get(3).getX());
                player.setY(teleporters.get(3).getY());
            }
        }));
        teleportButtons.addObject(new UIImageButton(710, 850, 300, 150, Assets.buttonKitchen, new ClickListener() {
            @Override
            public void onClick() {
                game.getMouseManager().setUIManager(null);
                player.setX(teleporters.get(4).getX());
                player.setY(teleporters.get(4).getY());
            }
        }));
    }



    public void update(){
        teleportButtons.tick();
    }


    public void render(Graphics g) {
        teleportButtons.render(g);
    }
}

