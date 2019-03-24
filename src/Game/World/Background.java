package Game.World;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Background {
	private Handler handler;
	private int xPos;
	private int xPos2;
	private int yPos;
	private int size;
	private boolean attacked = false;
	private boolean willA = false;
	private Animation anim;
	private Rectangle bounds;
	private Random rand;
	private boolean front = true;
	private long start;
	private long time;
	private int d;
	
	public Background(Handler handler) {
		this.handler = handler;
		this.rand = new Random();
		this.d = this.rand.nextInt(2);
		this.size = 96;
		this.xPos = 63 * MapBuilder.pixelMultiplier;
		this.xPos2 = 70 * MapBuilder.pixelMultiplier;
		this.yPos = 99 * MapBuilder.pixelMultiplier - 48;
		this.anim = new Animation(150, Images.enemy);
		this.bounds = new Rectangle();
	}

	public void tick() {
		this.anim.tick();
		if(this.anim.getIndex()>=7) this.anim.reset();
		if(this.handler.getMap().getListener().getAppear()) {
			this.move();
		}
		if(this.willA) this.attack();
		if(this.handler.getMario().getBounds().intersects(this.bounds)) {
			this.attacked = true;
			this.handler.getMario().setHit(true);
		}
		if(this.attacked) this.bounds.setBounds(0, 0, 0, 0);
		else this.bounds.setBounds(this.xPos2, this.yPos,  this.size,  this.size);
		this.time = System.currentTimeMillis();
		if(!this.handler.getMario().getHit() && this.getBounds().getX() <= 40 * MapBuilder.pixelMultiplier && 
				this.getBounds().getX() >= 39 * MapBuilder.pixelMultiplier) {
			this.handler.getMap().getWalls().setDraw(true);
			if(this.d == 0) this.handler.getGame().getMusicHandler().play("congrats");
			else this.handler.getGame().getMusicHandler().play("wow");
		}
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(this.handler.getMap().getListener().getAppear()) g2.drawImage(this.anim.getCurrentFrame(), this.xPos, this.yPos, this.size, this.size, null);
		if(this.willA) g2.drawImage(Images.enemy[8], this.xPos2, this.yPos, this.size, this.size, null);
		if(this.willA) this.attack();	
	}

	public void move() {
		int pos = 56 * MapBuilder.pixelMultiplier;
		int pos2 = 65 * MapBuilder.pixelMultiplier;
		if(this.front && this.xPos >= pos) this.xPos-=2;
		if(!this.front && this.xPos <= pos2) {
			this.xPos+=2;
			if(this.xPos >= pos2) {
				this.handler.getMap().getListener().setAppear(false);
				this.willA = true;
			}
		}
		if(this.xPos >= pos) this.start = System.currentTimeMillis();
		if(this.time - this.start >= 2000) this.front = false;
	}
	public void attack() {
		this.xPos2 -= 15;
	}

	public Rectangle getBounds() {
		return this.bounds;
	}

}
