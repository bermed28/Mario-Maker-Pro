package Game.Entities.DynamicEntities;

import Game.Entities.EntityBase;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Mario extends BaseDynamicEntity{

    private double velX,velY;

    public String facing = "Left";
    public boolean moving = false;
    public Animation playerSmallLeftAnimation,playerSmallRightAnimation,playerBigLeftWalkAnimation,playerBigRightWalkAnimation,playerBigLeftRunAnimation,playerBigRightRunAnimation;
    public boolean falling = true, jumping = false,isBig=true,running = false,changeDirrection=false;
    public double gravityAcc = 0.38;
    int changeDirectionCounter=0;

    public Mario(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.marioSmallWalkRight[0]);
        playerSmallLeftAnimation = new Animation(175,Images.marioSmallWalkLeft);
        playerSmallRightAnimation = new Animation(175,Images.marioSmallWalkRight);
        playerBigLeftWalkAnimation = new Animation(150,Images.marioBigWalkLeft);
        playerBigRightWalkAnimation = new Animation(150,Images.marioBigWalkRight);
        playerBigLeftRunAnimation = new Animation(115,Images.marioBigRunLeft);
        playerBigRightRunAnimation = new Animation(115,Images.marioBigRunRight);
        if(isBig){
            this.y-=8;
            this.height+=8;
            setDimension(new Dimension(width, this.height));
        }

    }

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

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)&&!handler.getKeyManager().up&&!handler.getKeyManager().down){
            this.jump();
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


        if(handler.getKeyManager().right&&!handler.getKeyManager().up&&!handler.getKeyManager().down){
            if(handler.getKeyManager().runbutt){
                velX = 6;
                running=true;
            }else {
                velX = 3;
                running=false;
            }
            if(facing.equals("Left")){changeDirrection=true;}
            facing = "Right";
            moving=true;
        }else if(handler.getKeyManager().left&&!handler.getKeyManager().up&&!handler.getKeyManager().down){
            if(handler.getKeyManager().runbutt){
                velX = -6;
                running=true;
            }else {
                velX = -3;
                running=false;
            }
            if(facing.equals("Right")){changeDirrection=true;}
            facing = "Left";
            moving=true;
        }else{
            velX=0;
            moving = false;
        }
        if(jumping && velY <= 0){
            jumping = false;
            falling = true;
        }
        else if(jumping){
            velY = velY - gravityAcc;
            y = (int)(y - velY);
        }

        if(falling){
            y = (int)(y + velY);
            velY = velY + gravityAcc;
        }
        x+=velX;
    }

    public void drawMario(Graphics2D g2) {
        if(!isBig) {
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
        }else {
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
            }else{
                if(!running){
                    changeDirrection=false;
                    changeDirectionCounter=0;
                    drawMario(g2);
                }
                if(facing.equals("Right")){
                    g2.drawImage(Images.marioBigJumpRight[4], x, y, width, height, null);
                }else{
                    g2.drawImage(Images.marioBigJumpLeft[4], x, y, width, height, null);
                }
            }
        }

    }

    public void jump() {
        if(!jumping && !falling){
            jumping=true;
            velY=10;
            handler.getGame().getMusicHandler().playJump();
        }
    }

    private void checkBottomCollisions() {
        Mario mario = this;
        ArrayList<BaseStaticEntity> bricks = handler.getMap().getBlocksOnMap();
        ArrayList<BaseDynamicEntity> enemies =  handler.getMap().getEnemiesOnMap();
        ArrayList<EntityBase> toBeRemoved = new ArrayList<>();

        Rectangle marioBottomBounds =getBottomBounds();

        if (!mario.jumping) {
            falling = true;
        }

        for (BaseStaticEntity brick : bricks) {
            Rectangle brickTopBounds = brick.getTopBounds();
            if (marioBottomBounds.intersects(brickTopBounds)) {
                mario.setY(brick.getY() - mario.getDimension().height + 1);
                falling = false;
                velY=0;
            }
        }

        for (BaseDynamicEntity enemy : enemies) {
            Rectangle enemyTopBounds = enemy.getTopBounds();
            if (marioBottomBounds.intersects(enemyTopBounds)) {
                //mario.acquirePoints(100);
                toBeRemoved.add(enemy);
                //engine.playStomp();
            }
        }

        if (mario.getY() + mario.height >= handler.getMap().getBottomBorder()) {
            mario.setY((int)(handler.getMap().getBottomBorder() - mario.height));
            falling=false;
            velY=0;
        }

        //removeObjects(toBeRemoved);
    }

    private void checkTopCollisions() {
        Mario mario = this;
        ArrayList<BaseStaticEntity> bricks = handler.getMap().getBlocksOnMap();

        Rectangle marioTopBounds = mario.getTopBounds();
        for (BaseStaticEntity brick : bricks) {
            Rectangle brickBottomBounds = brick.getBottomBounds();
            if (marioTopBounds.intersects(brickBottomBounds)) {
                velY=0;
                mario.setY(brick.getY() + brick.height);
            }
        }
    }

    private void checkMarioHorizontalCollision(){
        Mario mario = this;
        ArrayList<BaseStaticEntity> bricks = handler.getMap().getBlocksOnMap();
        ArrayList<BaseDynamicEntity> enemies = handler.getMap().getEnemiesOnMap();
        ArrayList<EntityBase> toBeRemoved = new ArrayList<>();

        boolean marioDies = false;
        boolean toRight = moving && facing.equals("Right");

        Rectangle marioBounds = toRight ? mario.getRightBounds() : mario.getLeftBounds();

        for (BaseStaticEntity brick : bricks) {
            Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
            if (marioBounds.intersects(brickBounds)) {
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
                //marioDies = mario.onTouchEnemy(engine);
                toBeRemoved.add(enemy);
            }
        }
        //removeObjects(toBeRemoved);


        if (mario.getX() <= handler.getCamera().getX() && mario.getVelX() < 0) {
            velX=0;
            mario.setX((int)handler.getCamera().getX());
        }

//        if(marioDies) {
//            resetCurrentMap(engine);
//        }
    }

    public double getVelX() {
        return velX;
    }
    public double getVelY() {
        return velY;
    }





}
