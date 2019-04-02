package Game.Entities.StaticEntities;

import Main.Handler;
import Resources.Images;


public class TeleportationBlock extends BaseStaticEntity {
	
	
	public TeleportationBlock(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.teleportationB);
		
	}
	
	public boolean hascollided() {
		if(handler.getMario().getTopBounds().intersects(this.getBottomBounds())) {
			return true;
		}
		return false;
	}

}
