package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class LuigiFlag extends BaseDynamicEntity {
	
	public static boolean touched = false;
	public Animation anim;

	public LuigiFlag(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.luigiFlag[0]);
			anim = new Animation(160,Images.luigiFlag);
	
	}
	
	@Override
	public void tick() {
		anim.tick();
		super.tick();
	}
	
	


}
