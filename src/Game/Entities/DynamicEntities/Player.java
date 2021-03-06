package Game.Entities.DynamicEntities;


import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Game.Entities.StaticEntities.BaseStaticEntity;
import Game.Entities.StaticEntities.BoundBlock;
import Game.Entities.StaticEntities.BreakBlock;
import Game.Entities.StaticEntities.MisteryBlock;
import Game.Entities.StaticEntities.SuperPowerBlock;
import Game.Entities.StaticEntities.TeleportationBlock;
import Game.GameStates.Player_Selection;
import Game.GameStates.State;
import Game.GameStates.WinState;
import Main.Handler;
import Resources.Animation;

public class Player extends BaseDynamicEntity {

	protected double velX,velY;

	public String facing = "Left";
	public boolean moving = false;
	public Animation playerSmallLeftAnimation,playerSmallRightAnimation,playerBigLeftWalkAnimation,playerBigRightWalkAnimation,playerBigLeftRunAnimation,playerBigRightRunAnimation,
	playerKnucklesLeftRunAnimation,playerKnucklesLeftWalkAnimation,playerKnucklesRightRunAnimation,playerKnucklesRightWalkAnimation,playerKnucklesLeftAnimation,playerKnucklesRightAnimation;
	public boolean falling = true, jumping = false,isBig=false,running = false,changeDirrection=false;
	public double gravityAcc = 0.38;
	int changeDirectionCounter=0;
	public boolean hit = false;
	public boolean grabbed =false;
	private int jump_counter = 0; 
	private int blue_knuckles_jump_counter = 0; 

	public Player(int x, int y, int width, int height, Handler handler, BufferedImage sprite,Animation PSLA,Animation PSRA,Animation PBLWA,Animation PBRWA,Animation PBLRA,Animation PBRRA,Animation PKLRA, Animation PKLWA,Animation PKRRA,Animation PKRWA, Animation PKLA, Animation PKRA) {
		super(x, y, width, height, handler, sprite);
		playerBigLeftRunAnimation=PBLRA;
		playerBigLeftWalkAnimation=PBLWA;
		playerBigRightRunAnimation=PBRRA;
		playerBigRightWalkAnimation=PBRWA;
		playerSmallLeftAnimation=PSLA;
		playerSmallRightAnimation=PSRA;
		playerKnucklesLeftRunAnimation=PKLRA;
		playerKnucklesLeftWalkAnimation=PKLWA;
		playerKnucklesRightRunAnimation=PKRRA;
		playerKnucklesRightWalkAnimation=PKRWA;
		playerKnucklesLeftAnimation=PKLA;
		playerKnucklesRightAnimation=PKRA;



	}

	@Override
	public void tick(){

		if (changeDirrection) {
			changeDirectionCounter++;
		}
		if(changeDirectionCounter>=10){
			changeDirrection=false;
			changeDirectionCounter=0;
		}


		checkBottomCollisions();
		checkMarioHorizontalCollision();
		checkTopCollisions();
		checkItemCollision();

		if(Mario.redKnuckles) {
			if(this instanceof Mario) {
				if (facing.equals("Left") && moving && !running) {
					playerKnucklesLeftWalkAnimation.tick();
				} else if (facing.equals("Left") && moving && running) {
					playerKnucklesLeftRunAnimation.tick();
				} else if (facing.equals("Right") && moving && !running) {
					playerKnucklesRightWalkAnimation.tick();
				} else if (facing.equals("Right") && moving && running) {
					playerKnucklesRightRunAnimation.tick();
				}
			} else {
				if(!isBig) {
					if (facing.equals("Left") && moving) {
						playerSmallLeftAnimation.tick();
					} else if (facing.equals("Right") && moving) {
						playerSmallRightAnimation.tick();
					}
				}else{
					if (facing.equals("Left") && moving && !running) {
						playerBigLeftWalkAnimation.tick();
					} else if (facing.equals("Left") && moving && running) {
						playerBigLeftRunAnimation.tick();
					} else if (facing.equals("Right") && moving && !running) {
						playerBigRightWalkAnimation.tick();
					} else if (facing.equals("Right") && moving && running) {
						playerBigRightRunAnimation.tick();
					}
				}
			}

		} 
		if(Luigi.blueKnuckles) { 
			if(this instanceof Luigi) {
				if (facing.equals("Left") && moving && !running) {
					playerKnucklesLeftWalkAnimation.tick();
				} else if (facing.equals("Left") && moving && running) {
					playerKnucklesLeftRunAnimation.tick();
				} else if (facing.equals("Right") && moving && !running) {
					playerKnucklesRightWalkAnimation.tick();
				} else if (facing.equals("Right") && moving && running) {
					playerKnucklesRightRunAnimation.tick();
				}
			} else {
				if(!isBig) {
					if (facing.equals("Left") && moving) {
						playerSmallLeftAnimation.tick();
					} else if (facing.equals("Right") && moving) {
						playerSmallRightAnimation.tick();
					}
				}else{
					if (facing.equals("Left") && moving && !running) {
						playerBigLeftWalkAnimation.tick();
					} else if (facing.equals("Left") && moving && running) {
						playerBigLeftRunAnimation.tick();
					} else if (facing.equals("Right") && moving && !running) {
						playerBigRightWalkAnimation.tick();
					} else if (facing.equals("Right") && moving && running) {
						playerBigRightRunAnimation.tick();
					}
				}
			}
		} 

		if(!isBig) {
			if (facing.equals("Left") && moving) {
				playerSmallLeftAnimation.tick();
			} else if (facing.equals("Right") && moving) {
				playerSmallRightAnimation.tick();
			}
		}else{
			if (facing.equals("Left") && moving && !running) {
				playerBigLeftWalkAnimation.tick();
			} else if (facing.equals("Left") && moving && running) {
				playerBigLeftRunAnimation.tick();
			} else if (facing.equals("Right") && moving && !running) {
				playerBigRightWalkAnimation.tick();
			} else if (facing.equals("Right") && moving && running) {
				playerBigRightRunAnimation.tick();
			}
		}

	}

	private void checkItemCollision() {

		for (BaseDynamicEntity entity : handler.getMap().getEnemiesOnMap()) {
			if (entity != null && getBounds().intersects(entity.getBounds()) && entity instanceof Item && !isBig) {
				isBig = true;
				this.y -= 8;
				this.height += 8;
				setDimension(new Dimension(width, this.height));
				((Item) entity).used = true;
				entity.y = -100000;
			}
		}
	}


	public void checkBottomCollisions() {
		Player mario = this;
		ArrayList<BaseStaticEntity> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamicEntity> enemies =  handler.getMap().getEnemiesOnMap();

		Rectangle marioBottomBounds =mario.getBottomBounds();

		if (!mario.jumping) {
			falling = true;
		}


		for (BaseStaticEntity brick : bricks) {
			Rectangle brickTopBounds = brick.getTopBounds();
			if( brick instanceof BoundBlock && mario.getBottomBounds().intersects(brick.getTopBounds())) {
				if(Player_Selection.MultiPlayer) {
					if(this instanceof Mario) {
						WinState.marioWon = true;
					}
					else if(this instanceof Luigi) {
						WinState.luigiWon = true;
					}
				}

				else {
					State.setState(handler.getGame().gameoverState);
				}
			}

			if (marioBottomBounds.intersects(brickTopBounds)) {
				mario.setY(brick.getY() - mario.getDimension().height + 1);
				falling = false;
				velY=0;
				jump_counter = 0; 
				blue_knuckles_jump_counter = 0;


			}
		}

		for (BaseDynamicEntity enemy : enemies) {
			Rectangle enemyTopBounds = enemy.getTopBounds();
			if (marioBottomBounds.intersects(enemyTopBounds) && !(enemy instanceof Item)) {
				if (mario instanceof Luigi && enemy instanceof MarioFlag) {
					velY = 10;
				}
				else if (mario instanceof Mario && enemy instanceof LuigiFlag) {
					velY = 10;
				}
				else if(!enemy.ded) {
					if(!mario.isBig) {

						if(enemy.getLeftBounds().intersects(mario.getRightBounds()) || enemy.getRightBounds().intersects(mario.getLeftBounds()) ) {
							if(!Player_Selection.MultiPlayer) {
								if(mario instanceof Mario){
									State.setState(handler.getGame().gameoverState);
								}
							}
							else {
								if(mario instanceof Mario){
									WinState.marioWon = true;
									State.setState(handler.getGame().winState);
								}
								if(mario instanceof Luigi){
									WinState.luigiWon = true;
									State.setState(handler.getGame().winState);
								}
							}
						}

						handler.getGame().getMusicHandler().playStomp();
						enemy.kill();
						falling=false;
						velY=0;

					}
					
					else {
						mario.isBig = false;
						enemy.kill();
						falling=false;
						velY=0;
					}

				}
			}
		}
	}

	public void checkTopCollisions() {
		Player mario = this;
		ArrayList<BaseStaticEntity> bricks = handler.getMap().getBlocksOnMap();

		Rectangle marioTopBounds = mario.getTopBounds();
		for (BaseStaticEntity brick : bricks) {
			Rectangle brickBottomBounds = brick.getBottomBounds();
			if(brick instanceof TeleportationBlock) {
				if (marioTopBounds.intersects(brickBottomBounds)) {
					for (BaseStaticEntity otherbrick : bricks) {
						if(otherbrick instanceof TeleportationBlock && !(otherbrick.getX() == mario.getX()) && !(otherbrick.getY() + otherbrick.height == mario.getY())) {

							mario.setX(otherbrick.getX());
							mario.setY(otherbrick.getY() - otherbrick.height);

							if(mario instanceof Luigi) {

								handler.getLuigiCamera().setX(mario.getX()-350);
								handler.getLuigiCamera().setY(mario.getY()-300);
								velY = 0;
							}
							if(mario instanceof Mario) {

								handler.getCamera().setX(mario.getX()-350);
								handler.getCamera().setY(mario.getY()-300);
								velY=0;
							}


						}
					}
				}
			}


			else if(brick instanceof MisteryBlock) {
				if (marioTopBounds.intersects(brickBottomBounds)) {
					mario.isBig = true;
				}
			}

			else if(brick instanceof SuperPowerBlock) {


				if(mario instanceof Luigi) {
					if (marioTopBounds.intersects(brickBottomBounds)) {
						mario.isBig = true;
						Luigi.blueKnuckles = true;
						velY=0;
						mario.setY(brick.getY() + brick.height);
					}
				}
				if(mario instanceof Mario) {
					if (marioTopBounds.intersects(brickBottomBounds)) {
						mario.isBig = true;
						Mario.redKnuckles = true;
						velY=0;
						mario.setY(brick.getY() + brick.height);
					}

				}

			}

			else if (marioTopBounds.intersects(brickBottomBounds)) {
				velY=0;
				mario.setY(brick.getY() + brick.height);
			}
		}
	}

	public void checkMarioHorizontalCollision(){
		Player mario = this;
		ArrayList<BaseStaticEntity> bricks = handler.getMap().getBlocksOnMap();
		ArrayList<BaseDynamicEntity> enemies = handler.getMap().getEnemiesOnMap();

		boolean marioDies = false;
		boolean toRight = moving && facing.equals("Right");

		Rectangle marioBounds = toRight ? mario.getRightBounds() : mario.getLeftBounds();

		for (BaseStaticEntity brick : bricks) {
			Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();

			if (marioBounds.intersects(brickBounds)){
				velX=0;
				if(toRight)
					mario.setX(brick.getX() - mario.getDimension().width);
				else
					mario.setX(brick.getX() + brick.getDimension().width);

			}

		}

		for(BaseDynamicEntity enemy : enemies){
			Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
			if (marioBounds.intersects(enemyBounds)) {
				marioDies = true;
				break;
			} 

		}

		if(marioDies) {
			handler.getMap().reset();
		}
	}

	public void jump() {
		if(!jumping && !falling){
			jumping=true;
			velY=10;
			handler.getGame().getMusicHandler().playJump();

		}
		if(this instanceof Luigi) {
			if(Luigi.blueKnuckles) {
				if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_CONTROL) && blue_knuckles_jump_counter < 1) {
					jumping=true;
					velY=9;
					gravityAcc = .175;
					handler.getGame().getMusicHandler().playJump();
					blue_knuckles_jump_counter ++;

				}
			}
		}
		else if(this instanceof Mario) {
			if(Mario.redKnuckles) {

				if(jumping && handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE) && jump_counter < 2) {
					jumping=true;
					velY=11;
					handler.getGame().getMusicHandler().playJump();
					jump_counter++;
				}
			}


		}
	}

	public double getVelX() {
		return velX;
	}

	public double getVelY() {
		return velY;
	}

	public boolean getHit() {
		return this.hit;
	}
	public void setHit(Boolean hit) {
		this.hit = hit;
	}

	public Player getPlayer() {
		return this;
	}


}
