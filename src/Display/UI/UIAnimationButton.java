package Display.UI;

import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

import Main.Handler;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class UIAnimationButton extends UIObject{
	private Handler handler;
	private BufferedImage[] images;
	private boolean draw = false;
	private ClickListlener clicker;
	Animation anim;

	public UIAnimationButton(float x, float y, int width, int height, BufferedImage[] images, ClickListlener clicker, Handler handler) {
		super(x, y, width, height);
		this.handler = handler;
		this.images=images;
		this.clicker=clicker;
		anim = new Animation(60,images);
	}


	@Override
	public void tick() {
		if(this.draw) anim.tick();
	}

	@Override
	public void render(Graphics g) {
		if(this.draw) {
			if(active){
				g.drawImage(Images.tint(anim.getCurrentFrame(),0.25f,0.25f,0.25f), (int) x, (int) y, width, heith, null);
			}
			else if(hovering){
				g.drawImage(Images.tint(anim.getCurrentFrame(),0.3215f,0.3215f,0.3215f),(int)x,(int)y,width,heith,null);
			}else{
				g.drawImage(anim.getCurrentFrame(),(int)x,(int)y,width,heith,null);

			}
		}
	}


	@Override
	public void onClick()
	{
		clicker.onClick();
	}
	public boolean getdraw() {
		return this.draw;
	}
	public void setDraw(boolean d) {
		this.draw = d;
	}
}
