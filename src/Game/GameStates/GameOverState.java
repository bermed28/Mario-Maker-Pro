package Game.GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Display.UI.UIManager;
import Display.UI.UIStringButton;
import Main.Handler;
import Resources.Images;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class GameOverState extends State {

    private UIManager uiManager;

    public GameOverState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.addObjects(new UIStringButton(256, 546, 128, 64, "Title", () -> {
            handler.getMouseManager().setUimanager(null);
            handler.setIsInMap(false);
            State.setState(handler.getGame().menuState);
        },handler,Color.WHITE));
     

        uiManager.addObjects(new UIStringButton(400, 546, 128, 64, "Quit", () -> {
            handler.getMouseManager().setUimanager(null);
            handler.setIsInMap(false);
            System.exit(0);
        },handler,Color.WHITE));

    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();
       
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Images.gameover,-20,0,handler.getWidth()+20,handler.getHeight(),null);
        uiManager.Render(g);
    }
}
