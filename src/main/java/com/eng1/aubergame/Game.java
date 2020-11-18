package com.eng1.aubergame;

import com.eng1.aubergame.display.Display;
import com.eng1.aubergame.gfx.Assets;
import com.eng1.aubergame.gfx.Camera;
import com.eng1.aubergame.input.KeyManager;
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
    private State gameState;
    private State menuState;
    private State settingsState;
    private State demoState;

    //Input
    private final KeyManager keyManager;


    //Camera
    private Camera camera;

    public CollisionManager collisionManager;

    public Game(String title, int width, int height) {
        this.width = width;
        this.height = height;
        this.title = title;
        keyManager = new KeyManager();
        collisionManager = new CollisionManager();
    }

    private void init() {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(keyManager);
        Assets.init();

        camera = new Camera(this, 0,0);

        menuState = new MenuState(this);
        settingsState = new SettingsState(this);
        gameState = new GameState(this);
        demoState = new DemoState(this);
        State.setState(gameState);
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
        long lastTime = System.nanoTime();
        long timer = 0;
        int updates = 0;

        while (running) {
            now = System.nanoTime();
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
                System.out.println("ups and fps: " + updates);
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

    public Camera getCamera(){
        return camera;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
