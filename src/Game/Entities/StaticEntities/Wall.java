package Game.Entities.StaticEntities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Game.World.MapBuilder;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Wall {
	private Handler handler;
	private int xPos;
	private int yPos;
	private int size;
	private Animation anim;
	private boolean play = false;
	private Rectangle rect;
	
	public Wall(Handler handler) {
		this.handler = handler;
		this.setX((int) this.handler.getCamera().getX());
		this.setY((int) this.handler.getCamera().getY());
		this.size = this.handler.getWidth();
		this.anim = new Animation(20, Images.hitWall);
		this.rect = new Rectangle(43 * MapBuilder.pixelMultiplier, 90 * MapBuilder.pixelMultiplier, 200, 50);
	}
	
	public void tick() {
		if(this.anim.getIndex() >= Images.hitWall.length - 1)
			this.play = false;
		this.anim.tick();
		this.setX((int) this.handler.getCamera().getX());
		this.setY((int) this.handler.getCamera().getY());
	}
	
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		if(this.play) g2.drawImage(this.anim.getCurrentFrame(), this.xPos, this.yPos, this.size, this.size, null);
	}
	public void setPlay(boolean play) {
		this.play = play;
	}
	public Rectangle getRect() {
		return this.rect;
	}
	public void setX(int x) {
		this.xPos = x;
	}
	public void setY(int y) {
		this.yPos = y;
	}

}
