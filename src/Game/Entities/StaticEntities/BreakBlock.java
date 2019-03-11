package Game.Entities.StaticEntities;

import Main.Handler;
import Resources.Images;

public class BreakBlock extends BaseStaticEntity {

    public BreakBlock(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height,handler, Images.breakBlock);
    }

}
