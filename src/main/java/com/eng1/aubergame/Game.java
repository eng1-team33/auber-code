package com.eng1.aubergame;

import com.eng1.aubergame.display.Display;
import com.eng1.aubergame.entities.systems.System;
import com.eng1.aubergame.gfx.Assets;
import com.eng1.aubergame.gfx.Camera;
import com.eng1.aubergame.handlers.CollisionManager;
import com.eng1.aubergame.handlers.TeleporterManager;
import com.eng1.aubergame.input.KeyManager;
import com.eng1.aubergame.input.MouseManager;
import com.eng1.aubergame.states.*;

import java.awt.*;
import java.awt.image.BufferStrategy;


public class Game implements Runnable {

    private Display display;
    private int width, height;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //States
    public State gameState;
    public State menuState;
    public State settingsState;
    public State demoState;

    //Input
    private final KeyManager keyManager;
    private final MouseManager mouseManager;


    //Camera
    private Camera camera;

    private final CollisionManager collisionManager;
    private final TeleporterManager teleporterManager;

    private boolean sabotageInProgress = false;

    private System systemBeingSabotaged;

    private int innocentsArrested = 0;
    private int infiltratorsArrested = 0;
    private int infiltratorsInGame = 7;

    private int systemsRemaining = 9;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        collisionManager = new CollisionManager();
        teleporterManager = new TeleporterManager(this);
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);
        Assets.init();

        camera = new Camera(this, 2000,700);

        menuState = new MenuState(this);
        settingsState = new SettingsState(this);
        gameState = new GameState(this, camera);
        demoState = new DemoState(this);
        State.setState(menuState);
    }

    private void update() {
        keyManager.update();

        if (State.getState() != null) {
            State.getState().update();
        }
    }

    private void render() {
        bs = display.getCanvas().getBufferStrategy();
        if (bs == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0, width, height);

        if (State.getState() != null) {
            State.getState().render(g);
        }

        bs.show();
        g.dispose();
    }

    public void run() {

        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = java.lang.System.nanoTime();
        long timer = 0;
        int updates = 0;

        while (running) {
            now = java.lang.System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1) {
                update();
                render();
                updates++;
                delta--;
            }

            if (timer >= 1000000000) {
                updates = 0;
                timer = 0;
            }
        }

    }
    public CollisionManager getCollisionManager() {
        return collisionManager;
    }

    public KeyManager getKeyManager() {
        return keyManager;
    }

    public MouseManager getMouseManager(){return mouseManager;}
    public TeleporterManager getTeleporterManager() {
        return teleporterManager;
    }

    public Camera getCamera(){
        return camera;
    }

    public int getSystemsRemaining() {
        return systemsRemaining;
    }

    public void setSystemsRemaining(int systemsRemaining) {
        this.systemsRemaining = systemsRemaining;
    }

    public int getInnocentsArrested() {
        return innocentsArrested;
    }

    public void setInnocentsArrested(int innocentsArrested) {
        this.innocentsArrested = innocentsArrested;
    }

    public void setInfiltratorsArrested(int infiltratorsArrested) {
        this.infiltratorsArrested = infiltratorsArrested;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setSystemBeingSabotaged(System system) {
        this.systemBeingSabotaged = system;
    }

    public System getSystemBeingSabotaged() {
        return this.systemBeingSabotaged;
    }

    public void setSabotageInProgress(boolean sabotage) {
        this.sabotageInProgress = sabotage;
    }

    public boolean getSabotageInProgress() {
        return this.sabotageInProgress;
    }

    public int getInfiltratorsArrested() {
        return infiltratorsArrested;
    }

    public int getInfiltratorsInGame() {
        return infiltratorsInGame;
    }

    public synchronized void start() {
        if (running) {
            return;
        }
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop() {
        if (!running) {
            return;
        }
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
