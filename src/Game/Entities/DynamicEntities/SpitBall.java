package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Images;

public class SpitBall extends BaseDynamicEntity {


	public static boolean fired = false;

	public SpitBall(int x, int y, int width, int height, Handler handler) {
		super(x+15, y+10, width - 30, height - 30, handler, Images.spitBall);

	}

	@Override
	public void tick(){
		if(fired) {
			fireSpit();
		}
	}
}
