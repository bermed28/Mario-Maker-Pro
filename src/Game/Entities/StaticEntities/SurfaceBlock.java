package Game.Entities.StaticEntities;

import Main.Handler;
import Resources.Images;

public class SurfaceBlock extends BaseStaticEntity {

    public SurfaceBlock(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height,handler, Images.surfaceBlock);
    }

}
