package Game.GameStates;

import Game.World.Map;
import Game.World.MapBuilder;
import Game.World.WorldManager;
import Main.Handler;
import Resources.Images;

import java.awt.*;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class GameState extends State {


    public GameState(Handler handler){
        super(handler);

    }


    @Override
    public void tick() {

        handler.getMario().tick();

    }

    @Override
    public void render(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();

        handler.getMap().drawMap(g2);

    }

}
