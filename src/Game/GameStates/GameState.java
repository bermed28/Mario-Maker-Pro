package Game.GameStates;

import Game.Entities.DynamicEntities.BaseDynamicEntity;
import Main.Handler;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class GameState extends State {

    public GameState(Handler handler){
        super(handler);
    }

    @Override
    public void tick() {
        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
            State.setState(handler.getGame().pauseState);
        }
        handler.getMario().tick();
        for (BaseDynamicEntity entity:handler.getMap().getEnemiesOnMap()) {
            entity.tick();
        }
    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        handler.getMap().drawMap(g2);
    }

}
