package Game.Entities.StaticEntities;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.Random;

import Game.GameStates.State;
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
	private boolean draw = false;
	private Random rand;
	private Rectangle rect;
	private String str = "";
	private int sX = 70 * MapBuilder.pixelMultiplier;
	private int sY;
	private int opacity = 0;
	private int alpha = 0;
	private int d;

	public Wall(Handler handler) {
		this.handler = handler;
		this.rand = new Random();
		this.setX((int) this.handler.getCamera().getX());
		this.setY((int) this.handler.getCamera().getY());
		this.size = this.handler.getWidth();
		this.d = this.rand.nextInt(2);
		this.anim = new Animation(25, Images.hitWall);
		this.setSY((int) (this.handler.getCamera().getY() + this.handler.getHeight() / 2 + 50));
		this.rect = new Rectangle(38 * MapBuilder.pixelMultiplier, 90 * MapBuilder.pixelMultiplier, 440, 50);
	}

	public void tick() {
		if(this.anim.getIndex() == Images.hitWall.length - 1) {
			this.setPlay(false);
			if(this.d == 0) this.handler.getGame().getMusicHandler().play("defeated");
			else this.handler.getGame().getMusicHandler().play("failure");
			this.anim.end();
			this.draw = true;
		}
		if(this.play) this.anim.tick();
		this.setX((int) this.handler.getCamera().getX());
		this.setY((int) this.handler.getCamera().getY());
		this.setSY((int) (this.handler.getCamera().getY() + this.handler.getHeight() / 2 + 50));
		if(this.handler.getMario().getHit()) str = "DEFEATED!";
		else str = "CONGRATS!";
		if(this.opacity >= 254) this.opacity = 255;
		if(this.opacity == 255) {
			this.alpha +=2;
			if(this.alpha >= 254) this.alpha = 255;
		}
		if(this.handler.getKeyManager().keyJustPressed(KeyEvent.VK_ENTER) && this.opacity == 255) {
			this.handler.getGame().reStart();
			State.setState(this.handler.getGame().menuState);
		}
	}

	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		Font font = new Font("IMPACT", Font.ITALIC, 115);
		Font font2 = new Font("IMPACT", Font.BOLD, 20);
		if(this.play) g2.drawImage(this.anim.getCurrentFrame(), this.xPos, this.yPos, this.size, this.size, null);
		if(this.draw) {
			g2.setColor(new Color(255, 255 ,255 , this.opacity++));
			g2.fillRect(this.xPos, this.yPos, this.size, this.size);
			g2.setFont(font);
			if(str.equals("DEFEATED!")) g2.setColor(Color.BLACK);
			else g2.setColor(Color.red);
			g2.drawString(this.str, this.sX, this.sY);
			this.drawDef();
			g2.setFont(font2);
			g2.setColor(Color.gray);
			g2.drawString("#JJMP", this.xPos + this.handler.getWidth() - 100, this.yPos + this.handler.getHeight() - 60);
			g2.setColor(Color.ORANGE);
			g2.setColor(new Color(0 ,0 ,0 , this.alpha));
			g2.drawString("'Enter' --  Exit Game.", this.sX + 140, this.sY + 50);	
			if(str.equals("CONGRATS!")) {
				g2.setColor(Color.ORANGE);
				g2.drawString("Skill or just Luck?", this.sX + 150, this.sY - 125);
				g2.setColor(new Color(0,0, 255, this.alpha));
				g2.setFont(new Font("AR ESSENCE", Font.BOLD, 50));
				g2.drawString("Sub to Pewdiepie", this.sX + 65, this.sY + 175);
			}
		}
	}
	public void drawDef() {
		this.sX -= 15;
		if(this.sX <= this.handler.getCamera().getX() + this.handler.getHeight() / 6) {
			this.sX = (int) (this.handler.getCamera().getX() + this.handler.getHeight() / 6);
		}
	}
	public void setPlay(boolean play) {
		this.play = play;
	}
	public Rectangle getRect() {
		return this.rect;
	}
	public void setDraw(boolean b) {
		this.draw = b;
	}
	public void setSY(int sy) {
		this.sY = sy;
	}
	public void setX(int x) {
		this.xPos = x;
	}
	public void setY(int y) {
		this.yPos = y;
	}

}
