package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Coin extends BaseDynamicEntity {
	
	
	public Animation anim;

	public Coin(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.coinitem[0]);
			anim = new Animation(160,Images.coinitem);
	
	}
	
	@Override
	public void tick() {
		anim.tick();
		super.tick();
	}
	
	


}
