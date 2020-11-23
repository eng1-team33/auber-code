package com.eng1.aubergame.states;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.gfx.Assets;
import com.eng1.aubergame.ui.ClickListener;
import com.eng1.aubergame.ui.UIImageButton;
import com.eng1.aubergame.ui.UIManager;

import java.awt.*;

public class MenuState extends State {

    private UIManager uiManager;

    public MenuState(Game game) {
        super(game);
        uiManager = new UIManager(game);
        game.getMouseManager().setUIManager(uiManager);

        uiManager.addObject(new UIImageButton(710, 50, 500, 200, Assets.buttonPlay, new ClickListener() {
            @Override
            public void onClick() {
                game.getMouseManager().setUIManager(null);
                State.setState(game.gameState);
            }

        }));
        uiManager.addObject(new UIImageButton(710, 300, 500, 200, Assets.buttonDemo, new ClickListener() {
            @Override
            public void onClick() {
                game.getMouseManager().setUIManager(null);
                State.setState(game.demoState);
            }
        }));
        uiManager.addObject(new UIImageButton(710, 550, 500, 200, Assets.buttonSettings, new ClickListener() {
            @Override
            public void onClick() {
                game.getMouseManager().setUIManager(null);
                State.setState(game.settingsState);
            }
        }));
        uiManager.addObject(new UIImageButton(710, 800, 500, 200, Assets.buttonQuit, new ClickListener() {
            @Override
            public void onClick() {
                game.getMouseManager().setUIManager(null);
                System.exit(0);
            }
        }));

    }
    @Override
    public void update(){
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        uiManager.render(g);
    }

}

