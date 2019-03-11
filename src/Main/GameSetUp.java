package Main;

import Display.DisplayScreen;
import Game.GameStates.GameState;
import Game.GameStates.MenuState;
import Game.GameStates.PauseState;
import Game.GameStates.State;
import Input.KeyManager;
import Input.MouseManager;
import Resources.Images;
import Resources.MusicHandler;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;


/**
 * Created by AlexVR on 7/1/2018.
 */

public class GameSetUp implements Runnable {
    private DisplayScreen display;
    public String title;

    private boolean running = false;
    private Thread thread;

    private BufferStrategy bs;
    private Graphics g;

    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Handler
    private Handler handler;

    //States
    public State gameState;
    public State menuState;
    public State pauseState;

    //Res.music
    private MusicHandler musicHandler;

    private BufferedImage loading;

    public GameSetUp(String title,Handler handler){

        this.handler = handler;

        this.title = title;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
        musicHandler = new MusicHandler(handler);

    }

    private void init(){
        display = new DisplayScreen(title, handler.width, handler.height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        Images img = new Images();



        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        pauseState = new PauseState(handler);

        State.setState(menuState);
        musicHandler.restartBackground();
    }

    public void reStart(){
        gameState = new GameState(handler);
    }

    public synchronized void start(){
        if(running)
            return;
        running = true;
        //this runs the run method in this  class
        thread = new Thread(this);
        thread.start();
    }

    public void run(){

        //initiallizes everything in order to run without breaking
        init();

        int fps = 60;
        double timePerTick = 1000000000 / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(running){
            //makes sure the games runs smoothly at 60 FPS
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1){
                //re-renders and ticks the game around 60 times per second
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000){
                ticks = 0;
                timer = 0;
            }
        }

        stop();

    }

    private void tick(){
        //checks for key types and manages them
        keyManager.tick();

        //game states are the menus
        if(State.getState() != null)
            State.getState().tick();
    }

    private void render(){
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        g = bs.getDrawGraphics();
        //Clear Screen
        g.clearRect(0, 0,  handler.width, handler.height);

        //Draw Here!

        g.drawImage(loading ,0,0, handler.width, handler.height,null);
        if(State.getState() != null)
            State.getState().render(g);


        //End Drawing!
        bs.show();
        g.dispose();
    }

    public synchronized void stop(){
        if(!running)
            return;
        running = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public KeyManager getKeyManager(){
        return keyManager;
    }

    public MusicHandler getMusicHandler() {
        return musicHandler;
    }


    public MouseManager getMouseManager(){
        return mouseManager;
    }

}

