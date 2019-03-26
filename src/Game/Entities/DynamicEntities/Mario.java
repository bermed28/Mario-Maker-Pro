package Game.Entities.DynamicEntities;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Mario extends Player{

	private boolean hit = false;
	public boolean grabbed =false;

	public Mario(int x, int y, int width, int height, Handler handler) {
		super(x, y, width, height, handler, Images.marioSmallWalkRight[0]
				,new Animation(175,Images.marioSmallWalkLeft)
				, new Animation(175,Images.marioSmallWalkRight)
				, new Animation(150,Images.marioBigWalkLeft)
				, new Animation(150,Images.marioBigWalkRight)
				, new Animation(115,Images.marioBigRunLeft)
				, new Animation(115,Images.marioBigRunRight));
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
                if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE) && !handler.getKeyManager().up && !handler.getKeyManager().down) {
                    this.jump();
                }

                if (handler.getKeyManager().right && !handler.getKeyManager().up && !handler.getKeyManager().down) {
                    if (handler.getKeyManager().runbutt) {
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
                } else if (handler.getKeyManager().left && !handler.getKeyManager().up && !handler.getKeyManager().down) {
                    if (handler.getKeyManager().runbutt) {
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

	public void drawMario(Graphics2D g2) {
		if(!grabbed) {
			if (!isBig) {
				if (handler.getKeyManager().up) {
					if (facing.equals("Left")) {
						g2.drawImage(Images.marioSmallJumpLeft[2], x, y, width, height, null);
					} else {
						g2.drawImage(Images.marioSmallJumpRight[2], x, y, width, height, null);
					}
				} else if (handler.getKeyManager().down) {
					if (facing.equals("Left")) {
						g2.drawImage(Images.marioSmallJumpLeft[3], x, y, width, height, null);
					} else {
						g2.drawImage(Images.marioSmallJumpRight[3], x, y, width, height, null);
					}
				} else if (!jumping && !falling) {
					if (facing.equals("Left") && moving) {
						g2.drawImage(playerSmallLeftAnimation.getCurrentFrame(), x, y, width, height, null);
					} else if (facing.equals("Right") && moving) {
						g2.drawImage(playerSmallRightAnimation.getCurrentFrame(), x, y, width, height, null);
					}
					if (facing.equals("Left") && !moving) {
						g2.drawImage(Images.marioSmallWalkLeft[0], x, y, width, height, null);
					} else if (facing.equals("Right") && !moving) {
						g2.drawImage(Images.marioSmallWalkRight[0], x, y, width, height, null);
					}
				} else {
					if (jumping) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.marioSmallJumpLeft[0], x, y, width, height, null);
						} else {
							g2.drawImage(Images.marioSmallJumpRight[0], x, y, width, height, null);
						}

					} else {
						if (facing.equals("Left")) {
							g2.drawImage(Images.marioSmallJumpLeft[1], x, y, width, height, null);
						} else {
							g2.drawImage(Images.marioSmallJumpRight[1], x, y, width, height, null);
						}
					}
				}
			} else {
				if (!changeDirrection) {
					if (handler.getKeyManager().up) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.marioBigJumpLeft[4], x, y, width, height, null);
						} else {
							g2.drawImage(Images.marioBigJumpRight[4], x, y, width, height, null);
						}
					} else if (handler.getKeyManager().down) {
						if (facing.equals("Left")) {
							g2.drawImage(Images.marioBigJumpLeft[3], x, y, width, height, null);
						} else {
							g2.drawImage(Images.marioBigJumpRight[3], x, y, width, height, null);
						}
					} else if (!jumping && !falling) {
						if (facing.equals("Left") && moving && running) {
							g2.drawImage(playerBigLeftRunAnimation.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Left") && moving && !running) {
							g2.drawImage(playerBigLeftWalkAnimation.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Left") && !moving) {
							g2.drawImage(Images.marioBigWalkLeft[0], x, y, width, height, null);
						} else if (facing.equals("Right") && moving && running) {
							g2.drawImage(playerBigRightRunAnimation.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Right") && moving && !running) {
							g2.drawImage(playerBigRightWalkAnimation.getCurrentFrame(), x, y, width, height, null);
						} else if (facing.equals("Right") && !moving) {
							g2.drawImage(Images.marioBigWalkRight[0], x, y, width, height, null);
						}
					} else {
						if (jumping) {
							if (facing.equals("Left")) {
								g2.drawImage(Images.marioBigJumpLeft[0], x, y, width, height, null);
							} else {
								g2.drawImage(Images.marioBigJumpRight[0], x, y, width, height, null);
							}

						} else {
							if (facing.equals("Left")) {
								g2.drawImage(Images.marioBigJumpLeft[1], x, y, width, height, null);
							} else {
								g2.drawImage(Images.marioBigJumpRight[1], x, y, width, height, null);
							}
						}
					}
				} else {
					if (!running) {
						changeDirrection = false;
						changeDirectionCounter = 0;
						drawMario(g2);
					}
					if (facing.equals("Right")) {
						g2.drawImage(Images.marioBigJumpRight[4], x, y, width, height, null);
					} else {
						g2.drawImage(Images.marioBigJumpLeft[4], x, y, width, height, null);
					}
				}
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
