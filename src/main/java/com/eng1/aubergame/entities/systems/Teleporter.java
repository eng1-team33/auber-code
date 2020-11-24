package com.eng1.aubergame.entities.systems;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.creatures.Player;
import com.eng1.aubergame.gfx.Assets;
import com.eng1.aubergame.gfx.Camera;
import com.eng1.aubergame.handlers.TeleporterManager;

import java.awt.*;

public class Teleporter extends System {

    private final Game game;
    private final Player player;
    private final Camera camera;
    public String name;
    private Rectangle teleRect;

    public Teleporter(Game game,Player player, float x, float y, String name) {
        super(x, y, 45, 70);
        this.game = game;
        this.player = player;
        this.camera = game.getCamera();
        this.name = name;
        game.getTeleporterManager().add(this);
        teleRect = new Rectangle(Math.round(x), Math.round(y), 45, 70);
    }

    public void playerTeleport() {
        Rectangle playerRec = new Rectangle(Math.round(player.getX()), Math.round(player.getY()), player.getWidth(), player.getHeight());
        if (playerRec.intersects(teleRect)) {
            if ((game.getKeyManager().isActionPressed()) && !(game.getTeleporterManager().isMenuActive())) {
                game.getTeleporterManager().teleportMenu(player);
            }
        }
    }
    @Override
    public void update() {

    }

    @Override
    public void render(Graphics g) {
        //this.camera.drawOffsetImage(    , (int) x, (int) y, width, height, null, g);
    }

}
