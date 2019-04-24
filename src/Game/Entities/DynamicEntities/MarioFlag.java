package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class MarioFlag extends BaseDynamicEntity {
	
	public static boolean touched = false;
	public Animation anim;

	public MarioFlag(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.marioFlag[0]);
			anim = new Animation(160,Images.marioFlag);
	
	}
	
	@Override
	public void tick() {
		anim.tick();
		super.tick();
	}
	
	


}
