package Main;

import Input.KeyManager;
import Input.MouseManager;

import java.awt.*;


/**
 * Created by AlexVR on 7/1/2018.
 */

public class Handler {

    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    int DEFAULTWIDTH = gd.getDisplayMode().getWidth();
    int DEFAULTHEIGHT = gd.getDisplayMode().getHeight();

    int width,height;

    private GameSetUp game;

    public Handler(){

        height=(DEFAULTHEIGHT/2) +(DEFAULTHEIGHT/4)  ;
        width =(DEFAULTWIDTH/3);

    }

    public int getWidth(){
        return width;
    }

    public int getHeight(){
        return height;
    }

    public GameSetUp getGame() {
        return game;
    }

    public void setGame(GameSetUp game) {
        this.game = game;
    }

    public KeyManager getKeyManager(){
        return game.getKeyManager();
    }

    public MouseManager getMouseManager(){
        return game.getMouseManager();
    }


}
