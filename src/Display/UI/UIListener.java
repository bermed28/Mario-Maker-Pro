package Display.UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import Game.Entities.StaticEntities.BaseStaticEntity;
import Game.World.MapBuilder;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class UIListener{
	Handler handler;
	private int xPos;
	private int yPos;
	private int size;
	private boolean up = false;
	private boolean down = false;
	private double velY;
	private Animation smash;
	private Rectangle bounds;

	public UIListener(Handler handler) {
		this.handler = handler;
		this.xPos = 53 * MapBuilder.pixelMultiplier;
		this.yPos = 99 * MapBuilder.pixelMultiplier;
		this.size = MapBuilder.pixelMultiplier;
		smash = new Animation(60, Images.item);	
		this.bounds = new Rectangle(this.xPos, this.yPos, this.size,this.size);
	}

	public void tick() {
		this.smash.tick();		
		if(this.handler.getMario().getBounds().intersects(this.getBounds())) {
			this.bounds.setBounds(0, 0, 1, 1);
			this.handler.getGame().getMusicHandler().playItem();
			this.Adquire();
		}
		this.Adquired();
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(this.smash.getCurrentFrame(), this.xPos, this.yPos, this.size, this.size, null);
		g2.setColor(Color.WHITE);
		g2.draw(this.bounds);
	}		
	
	public void Adquired() {
		if(this.up) {
            velY = velY - 0.6;
            this.yPos = (int)(this.yPos - velY);
        }
		else if(this.up && velY <= 0){
            this.up = false;
            this.down = true;
        }	
		if(this.down){
            this.yPos = (int)(this.yPos + velY);
            velY = velY + 0.6;
        }
	}

	
	private void Adquire() {
		if(!this.up && !this.down){
            this.up=true;
            velY=10;
        }
	}

	public Rectangle getBounds() {	
		return this.bounds;
	}

}
