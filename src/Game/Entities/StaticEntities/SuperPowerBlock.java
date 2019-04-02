package Game.Entities.StaticEntities;

import Main.Handler;
import Resources.Images;


public class SuperPowerBlock extends BaseStaticEntity {
	
	public SuperPowerBlock(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.superPB);
	}
	
	public boolean hascollided() {
		if(handler.getMario().getTopBounds().intersects(this.getBottomBounds())) {
			return true;
		}
		return false;
	}

}
