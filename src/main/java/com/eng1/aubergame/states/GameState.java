package com.eng1.aubergame.states;

import com.eng1.aubergame.Game;
import com.eng1.aubergame.entities.World;
import com.eng1.aubergame.entities.creatures.Player;
import com.eng1.aubergame.gfx.Assets;
import com.eng1.aubergame.gfx.Text;
import com.eng1.aubergame.gfx.Camera;
import com.eng1.aubergame.handlers.AlienManager;
import com.eng1.aubergame.handlers.InfiltratorManager;
import com.eng1.aubergame.handlers.TeleporterManager;

import java.awt.*;

import static java.awt.Color.*;

public class GameState extends State {

    private final Player player;
    private final World world;
    private final Camera camera;
    private final InfiltratorManager infiltratorManager;
    private final AlienManager alienManager;


    public GameState(Game game, Camera camera) {
        super(game);
        this.world = new World(game, 0, 0, 7500, 4219);
        this.player = new Player(game, world, 2960, 1100);
        this.camera = camera;
        this.infiltratorManager = new InfiltratorManager(world, game, player);
        this.alienManager = new AlienManager(world, game, player);
    }

    public Player getPlayer() {
        return player;
    }

    @Override
    public void update() {
        if (game.getTeleporterManager().isMenuActive()) {
            return;
        }
       // if (game.getInfiltratorsArrested()== game.getInfiltratorsInGame()){
       //     playerWin();
       // }else if (world.totalSystemsActive() == 0){
       //     playerLose();
       // }

        world.update();
        player.update();
        infiltratorManager.update();
        alienManager.update();
    }

    @Override
    public void render(Graphics g) {
        if (game.getTeleporterManager().isMenuActive()) {
            return;
        }
        world.render(g);
        infiltratorManager.render(g);
        alienManager.render(g);
        player.render(g);
        Text.drawString(g, ("Health: "+player.getHealth()),10,30,false,red, Assets.font28);
        Text.drawString(g, ("Imposters Remaining: "+(game.getInfiltratorsInGame()-game.getInfiltratorsArrested())),10,60,false,red, Assets.font28);
        Text.drawString(g, ("Systems Remaining: "+(game.getSystemsRemaining())),10,90,false,red, Assets.font28);

/*
        camera.drawOffsetRectangle(0,0,2607,4219, g);
        camera.drawOffsetRectangle(0,3252,7500,996, g);
        camera.drawOffsetRectangle(0,0,7500,967, g);
        camera.drawOffsetRectangle(4893,0,2607,4219, g);
        camera.drawOffsetRectangle(2865,0,249,1093, g);
        camera.drawOffsetRectangle(3501,0,498,1093, g);
        camera.drawOffsetRectangle(4383,0,123,1093, g);
        camera.drawOffsetRectangle(4767,1354,2733,249, g);
        camera.drawOffsetRectangle(4767,2238,2733,249, g);
        camera.drawOffsetRectangle(4767,2874,2733,117, g);
        camera.drawOffsetRectangle(0,2112,2733,372, g);
        camera.drawOffsetRectangle(0,2874,2733,117, g);
        camera.drawOffsetRectangle(2868,2874,372,117, g);
        camera.drawOffsetRectangle(2994,2745,120,123, g);
        camera.drawOffsetRectangle(4386,2745,123,504, g);
        camera.drawOffsetRectangle(4260,2874,372,117, g);
        camera.drawOffsetRectangle(3501,2745,498,504, g);
        camera.drawOffsetRectangle(3375,2874,750,120, g);
        camera.drawOffsetRectangle(4386,1228,120,624, g);
        camera.drawOffsetRectangle(4260,1351,372,252, g);
        camera.drawOffsetRectangle(4257,2238,375,246, g);
        camera.drawOffsetRectangle(4383,1983,123,627, g);
        camera.drawOffsetRectangle(2610,1351,504,375, g);
        camera.drawOffsetRectangle(2865,1225,252,126, g);
        camera.drawOffsetRectangle(3117,1351,126,252, g);
        camera.drawOffsetRectangle(2991,1729,126,126, g);
        camera.drawOffsetRectangle(2865,2109,126,378, g);
        camera.drawOffsetRectangle(2991,1983,126,633, g);
        camera.drawOffsetRectangle(3117,2238,126,249, g);
        camera.drawOffsetRectangle(3372,2238,756,249, g);
        camera.drawOffsetRectangle(3498,2109,504,504, g);
        camera.drawOffsetRectangle(3498,1983,123,126, g);
        camera.drawOffsetRectangle(3879,1983,123,126, g);
        camera.drawOffsetRectangle(3372,1351,126,252, g);
        camera.drawOffsetRectangle(3999,1348,126,255, g);
        camera.drawOffsetRectangle(3498,1225,504,504, g);
        camera.drawOffsetRectangle(3498,1729,123,126, g);
        camera.drawOffsetRectangle(3879,1729,123,126, g);

 */
    }

    public void pauseGame(){

    }

    public void playerWin(){

    }

    public void playerLose(){

    }

}
