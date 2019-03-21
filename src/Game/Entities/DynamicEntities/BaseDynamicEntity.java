package Game.Entities.DynamicEntities;

import Game.Entities.EntityBase;
import Main.Handler;

import java.awt.image.BufferedImage;

public class BaseDynamicEntity extends EntityBase {

    public BaseDynamicEntity(int x, int y, int width, int height, Handler handler, BufferedImage sprite) {
        super(x, y, width, height,handler,sprite);
    }
    public void tick(){

    }

}
