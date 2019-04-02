package Game.Entities.DynamicEntities;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class PiranhaPlant extends BaseDynamicEntity {

	public Animation anim;

	
	public PiranhaPlant(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.piranhaPlant[0]);
		anim = new Animation(160,Images.piranhaPlant);
	}

	@Override
	public void tick(){
		if(!ded && dedCounter==0) {
			super.tick();
			anim.tick();
			if (falling) {
				y = (int) (y + velY);
				velY = velY + gravityAcc;
				checkFalling();
			}
			checkHorizontal();
			
		}

		else if(ded&&dedCounter==0){
			y++;
			height--;
			setDimension(new Dimension(width,height));
			if (height==0){
				dedCounter=1;
				y=-10000;
			}
		}
	}

	@Override
	public void kill() {
		sprite = Images.piranhaPlantDies;
		ded=true;
	}

}
