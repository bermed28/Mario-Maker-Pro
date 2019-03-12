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
    public Animation playerSmallLeftAnimation;
    public Animation playerSmallRightAnimation;
    public boolean falling = true, jumping = false;
    public double gravityAcc = 0.38;

    public Mario(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.marioSmallWalkRight[0]);
        playerSmallLeftAnimation = new Animation(225,Images.marioSmallWalkLeft);
        playerSmallRightAnimation = new Animation(225,Images.marioSmallWalkRight);
    }

    public void tick(){

        checkBottomCollisions();
        checkMarioHorizontalCollision();
        checkTopCollisions();

        if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_SPACE)){
            this.jump();
        }

        if(facing.equals("Left")&&moving){
            playerSmallLeftAnimation.tick();
        }else if(facing.equals("Right")&&moving){
            playerSmallRightAnimation.tick();
        }


        if(handler.getKeyManager().right){
            velX=5;
            facing = "Right";
            moving=true;
        }else if(handler.getKeyManager().left){
            velX=-5;
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
        if(facing.equals("Left")&&moving){
            g2.drawImage(playerSmallLeftAnimation.getCurrentFrame(),x,y,width,height,null);
        }else if(facing.equals("Right")&&moving){
            g2.drawImage(playerSmallRightAnimation.getCurrentFrame(),x,y,width,height,null);
        }
        if(facing.equals("Left")&&!moving){
            g2.drawImage(Images.marioSmallWalkLeft[0],x,y,width,height,null);
        }else if(facing.equals("Right")&&!moving){
            g2.drawImage(Images.marioSmallWalkRight[0],x,y,width,height,null);
        }

    }

    public void jump() {
        if(!jumping && !falling){
            jumping=true;
            velY=10;
//            handler.getGame().getMusicHandler().playJump();
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
