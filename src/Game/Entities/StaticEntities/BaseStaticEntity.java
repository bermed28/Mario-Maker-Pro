package Game.Entities.StaticEntities;

import Game.Entities.EntityBase;
import Main.Handler;

import java.awt.image.BufferedImage;

public class BaseStaticEntity extends EntityBase {

    public BaseStaticEntity(int x, int y, int width, int height, Handler handler, BufferedImage sprite) {
        super(x, y, width, height,handler,sprite);
    }

}
