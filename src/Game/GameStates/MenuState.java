package Game.GameStates;


import Display.UI.UIStringButton;
import Game.World.MapBuilder;
import Main.Handler;
import Resources.Images;
import Display.UI.UIImageButton;
import Display.UI.UIManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class MenuState extends State {

    private UIManager uiManager;
    private int background;
    private String mode= "Menu";

    public MenuState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUimanager(uiManager);
        background = new Random().nextInt(9);


        uiManager.addObjects(new UIImageButton(handler.getWidth()/2-64, handler.getHeight()/2+(handler.getHeight()/8), 128, 64, Images.butstart, () -> {
            mode = "Select";
        }));
    }

    @Override
    public void tick() {
        handler.getMouseManager().setUimanager(uiManager);
        uiManager.tick();
        if(mode.equals("Select")){
            mode="Selecting";
            uiManager = new UIManager(handler);
            handler.getMouseManager().setUimanager(uiManager);
            //testMap1
            uiManager.addObjects(new UIStringButton(handler.getWidth()/2-64, handler.getHeight()/2+(handler.getHeight()/8), 128, 64, "Map 1", () -> {
                mode = "Menu";
                handler.setMap(MapBuilder.createMap(Images.testMap,handler));
                State.setState(handler.getGame().gameState);

            },handler));

            //testmap2
            uiManager.addObjects(new UIStringButton(handler.getWidth()/2-64, handler.getHeight()/2+(handler.getHeight()/8)+(64+32), 128, 64, "Map 2", () -> {
                mode = "Menu";
                handler.setMap(MapBuilder.createMap(Images.testMaptwo,handler));
                State.setState(handler.getGame().gameState);

            },handler));
        }
        if(mode.equals("Selecting")&&handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
            mode = "Menu";
            uiManager = new UIManager(handler);
            handler.getMouseManager().setUimanager(uiManager);
            uiManager.addObjects(new UIImageButton(handler.getWidth()/2-64, handler.getHeight()/2+(handler.getHeight()/8), 128, 64, Images.butstart, () -> {
                mode = "Select";
            }));
        }


    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.GREEN);
        g.drawImage(Images.backgrounds[background],0,0,handler.getWidth(),handler.getHeight(),null);
        g.drawImage(Images.title,0,0,handler.getWidth(),handler.getHeight(),null);
        uiManager.Render(g);

    }


}
