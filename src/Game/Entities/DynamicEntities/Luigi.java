
package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

import Game.Entities.StaticEntities.BaseStaticEntity;
import Game.Entities.StaticEntities.TeleportationBlock;
import Game.GameStates.State;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

public class Luigi extends Player {

	public boolean hit = false;
	public boolean grabbed = false;
	public static boolean blueKnuckles = false;
	public Luigi(int x, int y, int width, int height, Handler handler) { 
		super(x, y, width, height, handler, Images.luigiSmallWalkRight[0]
				,new Animation(175,Images.luigiSmallWalkLeft)
				, new Animation(175,Images.luigiSmallWalkRight)
				, new Animation(150,Images.luigiBigWalkLeft)
				, new Animation(150,Images.luigiBigWalkRight)
				, new Animation(115,Images.luigiBigRunLeft)
				, new Animation(115,Images.luigiBigRunRight)
				, new Animation(175,Images.blueBruddaWalkLeft)
				, new Animation(175,Images.blueBruddaWalkRight)
				, new Animation(150,Images.blueBruddaJumpLeft)
				, new Animation(150,Images.blueBruddaJumpRight)
				, new Animation(115,Images.blueBruddaRunLeft)
				, new Animation(115,Images.blueBruddaRunRight));
		if(isBig){
			this.y-=8;
			this.height+=8;
			setDimension(new Dimension(width, this.height));
		}
	}

	@Override
	public void tick(){
		if(!grabbed) {
			super.tick();
			if (!this.hit) {
				if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_CONTROL) && !handler.getKeyManager().up_luigi && !handler.getKeyManager().down_luigi) {
					this.jump();
				}

				if (handler.getKeyManager().right_luigi && !handler.getKeyManager().up_luigi && !handler.getKeyManager().down_luigi) {
					if (handler.getKeyManager().luigiRun) {
						velX = 6;
						running = true;
					} else {
						velX = 3;
						running = false;
					}
					if (facing.equals("Left")) {
						changeDirrection = true;
					}
					facing = "Right";
					moving = true;
				} else if (handler.getKeyManager().left_luigi && !handler.getKeyManager().up_luigi && !handler.getKeyManager().down_luigi) {
					if (handler.getKeyManager().luigiRun) {
						velX = -6;
						running = true;
					} else {
						velX = -3;
						running = false;
					}
					if (facing.equals("Right")) {
						changeDirrection = true;
					}
					facing = "Left";
					moving = true;
				} else {
					velX = 0;
					moving = false;
				}
				if (jumping && velY <= 0) {
					jumping = false;
					falling = true;
				} else if (jumping) {
					velY = velY - gravityAcc;
					y = (int) (y - velY);
				}

				if (falling) {
					y = (int) (y + velY);
					velY = velY + gravityAcc;
				}
				x += velX;
			} else {
				this.setX(this.getX() - 30);
				this.setY(this.getY() - 30);
			}
		}
		
	}

	public void drawLuigi(Graphics2D g2) {
		if(blueKnuckles) {
			if (!changeDirrection) {
				if (handler.getKeyManager().up_luigi) {
					if (facing.equals("Left")) {
						g2.drawImage(Images.blueBruddaJumpLeft[2], x, y, width, height, null);
					} else {
						g2.drawImage(Images.blueBruddaJumpRight[2], x, y, width, height, null);
					}
				} else if (handler.getKeyManager().down_luigi){
					if (facing.equals("Left")) {
						g2.drawImage(Images.blueBruddaJumpLeft[3], x, y, width, height, null);
					} else {
						g2.drawImage(Images.blueBruddaJumpRight[3], x, y, width, height, null);
					}
				} else if (!jumping && !falling) {
					if (facing.equals("Left") && moving && running) {
						g2.drawImage(playerKnucklesLeftRunAnimation.getCurrentFrame(), x, y, width, height, null);
					} else if (facing.equals("Left") && moving && !running) {
						g2.drawImage(playerKnucklesLeftWalkAnimation.getCurrentFrame(), x, y, width, height, null);
					} else if (facing.equals("Left") && !moving) {
						g2.drawImage(Images.blueBruddaWalkLeft[0], x, y, width, height, null);
					} else if (facing.equals("Right") && moving && running) {
						g2.drawImage(playerKnucklesRightRunAnimation.getCurrentFrame(), x, y, width, height, null);
					} else if (facing.equals("Right") && moving && !running) {
						g2.drawImage(playerKnucklesRightWalkAnimation.getCurrentFrame(), x, y, width, height, null);
					} else if (facing.equals("Right") && !moving) {
						g2.drawImage(Images.blueBruddaWalkRight[0], x, y, width, height, null);
					}
				} else {
					if (jumping) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.blueBruddaJumpLeft[0], x, y, width, height, null);
						} else {
							g2.drawImage(Images.blueBruddaJumpRight[0], x, y, width, height, null);
						}

					} else {
						if (facing.equals("Left")) {
							g2.drawImage(Images.blueBruddaJumpLeft[1], x, y, width, height, null);
						} else {
							g2.drawImage(Images.blueBruddaJumpLeft[1], x, y, width, height, null);
						}
					}
					
				} 
			}else {
				if (!running) {
					changeDirrection = false;
					changeDirectionCounter = 0;
					drawLuigi(g2);
				}
				if (facing.equals("Right")) {
					g2.drawImage(Images.blueBruddaJumpRight[4], x, y, width, height, null);
				} else {
					g2.drawImage(Images.blueBruddaJumpLeft[4], x, y, width, height, null);
				}
			}
		} 

		else {
			if(!grabbed) {
				if (!isBig) {
					if (handler.getKeyManager().up_luigi) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.luigiSmallJumpLeft[2], x, y, width, height, null);
						} else {
							g2.drawImage(Images.luigiSmallJumpRight[2], x, y, width, height, null);
						}
					} else if (handler.getKeyManager().down_luigi) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.luigiSmallJumpLeft[3], x, y, width, height, null);
						} else {
							g2.drawImage(Images.luigiSmallJumpRight[3], x, y, width, height, null);
						}
					} else if (!jumping && !falling) {
						if (facing.equals("Left") && moving) {
							g2.drawImage(playerSmallLeftAnimation.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Right") && moving) {
							g2.drawImage(playerSmallRightAnimation.getCurrentFrame(), x, y, width, height, null);
						}
						if (facing.equals("Left") && !moving) {
							g2.drawImage(Images.luigiSmallWalkLeft[0], x, y, width, height, null);
						} else if (facing.equals("Right") && !moving) {
							g2.drawImage(Images.luigiSmallWalkRight[0], x, y, width, height, null);
						}
					} else {
						if (jumping) {
							if (facing.equals("Left")) {
								g2.drawImage(Images.luigiSmallJumpLeft[0], x, y, width, height, null);
							} else {
								g2.drawImage(Images.luigiSmallJumpRight[0], x, y, width, height, null);
							}

						} else {
							if (facing.equals("Left")) {
								g2.drawImage(Images.luigiSmallJumpLeft[1], x, y, width, height, null);
							} else {
								g2.drawImage(Images.luigiSmallJumpRight[1], x, y, width, height, null);
							}
						}
					}
				} else {
					if (!changeDirrection) {
						if (handler.getKeyManager().up_luigi) {
							if (facing.equals("Left")) {
								g2.drawImage(Images.luigiBigJumpLeft[4], x, y, width, height, null);
							} else {
								g2.drawImage(Images.luigiBigJumpRight[4], x, y, width, height, null);
							}
						} else if (handler.getKeyManager().down_luigi) {
							if (facing.equals("Left")) {
								g2.drawImage(Images.luigiBigJumpLeft[3], x, y, width, height, null);
							} else {
								g2.drawImage(Images.luigiBigJumpRight[3], x, y, width, height, null);
							}
						} else if (!jumping && !falling) {
							if (facing.equals("Left") && moving && running) {
								g2.drawImage(playerBigLeftRunAnimation.getCurrentFrame(), x, y, width, height, null);
							} else if (facing.equals("Left") && moving && !running) {
								g2.drawImage(playerBigLeftWalkAnimation.getCurrentFrame(), x, y, width, height, null);
							} else if (facing.equals("Left") && !moving) {
								g2.drawImage(Images.luigiBigWalkLeft[0], x, y, width, height, null);
							} else if (facing.equals("Right") && moving && running) {
								g2.drawImage(playerBigRightRunAnimation.getCurrentFrame(), x, y, width, height, null);
							} else if (facing.equals("Right") && moving && !running) {
								g2.drawImage(playerBigRightWalkAnimation.getCurrentFrame(), x, y, width, height, null);
							} else if (facing.equals("Right") && !moving) {
								g2.drawImage(Images.luigiBigWalkRight[0], x, y, width, height, null);
							}
						} else {
							if (jumping) {
								if (facing.equals("Left")) {
									g2.drawImage(Images.luigiBigJumpLeft[0], x, y, width, height, null);
								} else {
									g2.drawImage(Images.luigiBigJumpRight[0], x, y, width, height, null);
								}

							} else {
								if (facing.equals("Left")) {
									g2.drawImage(Images.luigiBigJumpLeft[1], x, y, width, height, null);
								} else {
									g2.drawImage(Images.luigiBigJumpRight[1], x, y, width, height, null);
								}
							}
						}
					} else {
						if (!running) {
							changeDirrection = false;
							changeDirectionCounter = 0;
							drawLuigi(g2);
						}
						if (facing.equals("Right")) {
							g2.drawImage(Images.luigiBigJumpRight[4], x, y, width, height, null);
						} else {
							g2.drawImage(Images.luigiBigJumpLeft[4], x, y, width, height, null);
						}
					}
				}
			}
			if(handler.getMario().hit && !(handler.getMario().isBig)) {
				State.setState(handler.getGame().winState);
			}
		}
	}
	public boolean getHit() {
		return this.hit;
	}
	public void setHit(Boolean hit) {
		this.hit = hit;
	}
}
