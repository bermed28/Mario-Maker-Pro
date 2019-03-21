package Game.Entities.DynamicEntities;

import Game.Entities.EntityBase;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Main.Handler;
import Resources.Animation;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;

public class Player extends BaseDynamicEntity {

    protected double velX,velY;


    public String facing = "Left";
    public boolean moving = false;
    public Animation playerSmallLeftAnimation,playerSmallRightAnimation,playerBigLeftWalkAnimation,playerBigRightWalkAnimation,playerBigLeftRunAnimation,playerBigRightRunAnimation;
    public boolean falling = true, jumping = false,isBig=false,running = false,changeDirrection=false;
    public double gravityAcc = 0.38;
    int changeDirectionCounter=0;

    int Jump,Run,Up,Down,Left,Right;

    public Player(int x, int y, int width, int height, Handler handler, BufferedImage sprite,Animation PSLA,Animation PSRA,Animation PBLWA,Animation PBRWA,Animation PBLRA,Animation PBRRA) {
        super(x, y, width, height, handler, sprite);
        playerBigLeftRunAnimation=PBLRA;
        playerBigLeftWalkAnimation=PBLWA;
        playerBigRightRunAnimation=PBRRA;
        playerBigRightWalkAnimation=PBRWA;
        playerSmallLeftAnimation=PSLA;
        playerSmallRightAnimation=PSRA;
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

        for (Iterator<BaseDynamicEntity> iterator = handler.getMap().getEnemiesOnMap().iterator(); iterator.hasNext(); ) {
            BaseDynamicEntity entity = iterator.next();
            if(entity !=null && getBounds().intersects(entity.getBounds()) && entity instanceof Item && !isBig){
                isBig=true;
                this.y-=8;
                this.height+=8;
                setDimension(new Dimension(width, this.height));
                ((Item) entity).used=true;
            }
        }
    }


    public void checkBottomCollisions() {
        Player mario = this;
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



        //removeObjects(toBeRemoved);
    }

    public void checkTopCollisions() {
        Player mario = this;
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

    public void checkMarioHorizontalCollision(){
        Player mario = this;
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


//        if(marioDies) {
//            resetCurrentMap(engine);
//        }
    }

    public void jump() {
        if(!jumping && !falling){
            jumping=true;
            velY=10;
            handler.getGame().getMusicHandler().playJump();
        }
    }

    public double getVelX() {
        return velX;
    }
    public double getVelY() {
        return velY;
    }


}
