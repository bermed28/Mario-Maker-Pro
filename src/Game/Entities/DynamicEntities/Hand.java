package Game.Entities.DynamicEntities;

import java.awt.AlphaComposite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Game.World.MapBuilder;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Hand {
	private Handler handler;
	private int xPos;
	private int yPos;
	private int size;
	private boolean attacked = false;
	private Animation anim;
	private Rectangle bounds;
	private boolean front = true;
	private long start;
	private long time;
	public Hand(Handler handler) {
		this.handler = handler;
		this.size = 96;
		this.xPos = 63 * MapBuilder.pixelMultiplier;
		this.yPos = 99 * MapBuilder.pixelMultiplier - 48;
		this.anim = new Animation(150, Images.enemy);
		this.bounds = new Rectangle();
	}

	public void tick() {
		this.anim.tick();
		if(this.handler.getMap().getListener().getAppear()) {
			this.move();
		}
		if(this.handler.getMario().getBounds().intersects(this.bounds)) {
			this.attacked = true;
			this.handler.getMario().setHit(true);
		}
		if(this.attacked) this.bounds.setBounds(0, 0, 0, 0);
		else this.bounds.setBounds(this.xPos, this.yPos,  this.size,  this.size);
		this.time = System.currentTimeMillis();
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(this.handler.getMap().getListener().getAppear()) g2.drawImage(this.anim.getCurrentFrame(), this.xPos, this.yPos, this.size, this.size, null);
		g2.draw(this.bounds);		
	}

	public void move() {
		int pos = 55 * MapBuilder.pixelMultiplier;
		int pos2 = 61 * MapBuilder.pixelMultiplier;
		if(this.front && this.xPos >= pos) this.xPos-=2;
		if(!this.front && this.xPos <= pos2) {
			this.xPos+=2;
			if(this.xPos >= pos2) this.handler.getMap().getListener().setAppear(false);
		}
		if(this.xPos >= pos) this.start = System.currentTimeMillis();
		if(this.time - this.start >= 2000) this.front = false;
	}

	public Rectangle getBounds() {
		return this.bounds;
	}

}
