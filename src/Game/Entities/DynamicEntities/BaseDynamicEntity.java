package Game.Entities.DynamicEntities;

import Game.Entities.EntityBase;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BaseDynamicEntity extends EntityBase {

    protected String direction = "Right";
    public boolean falling = true,ded = false;
    protected int dedCounter=0;
    public double gravityAcc = 0.38;
    protected double velX=1,velY = 7;

    public BaseDynamicEntity(int x, int y, int width, int height, Handler handler, BufferedImage sprite) {
        super(x, y, width, height,handler,sprite);
    }
    public void tick(){

    }
    protected void checkHorizontal() {
        boolean toRight = direction.equals("Right");

        Rectangle mushroomBounds = toRight ? getRightBounds() : getLeftBounds();

        for (BaseStaticEntity brick : handler.getMap().getBlocksOnMap()) {
            Rectangle brickBounds = !toRight ? brick.getRightBounds() : brick.getLeftBounds();
            if (mushroomBounds.intersects(brickBounds)) {
                if(toRight) {
                    direction = "Left";
                    setX(brick.getX() - getDimension().width);
                }
                else{
                    direction = "Right";
                    setX(brick.getX() + brick.getDimension().width);
                }
            }
        }

        for(BaseDynamicEntity enemy : handler.getMap().getEnemiesOnMap()){
            Rectangle enemyBounds = !toRight ? enemy.getRightBounds() : enemy.getLeftBounds();
            if (mushroomBounds.intersects(enemyBounds)) {
                if(toRight) {
                    direction = "Left";
                    setX(enemy.getX() - getDimension().width);
                }
                else{
                    direction = "Right";
                    setX(enemy.getX() + enemy.getDimension().width);
                }
            }
        }

    }

    protected void checkFalling() {
        Rectangle mushroomBottom = getBottomBounds();

        for (BaseStaticEntity brick: handler.getMap().getBlocksOnMap()) {
            Rectangle topBlock = brick.getTopBounds();
            if(mushroomBottom.intersects(topBlock)){
                falling = false;
            }
        }
    }

    protected void move(){

        if(direction.equals("Right")){
            x+=velX;
        }else{
            x-=velX;
        }
        Rectangle mushroomBottom = getBottomBounds();
        for (BaseStaticEntity brick: handler.getMap().getBlocksOnMap()) {
            Rectangle topBlock = brick.getTopBounds();
            if(mushroomBottom.intersects(topBlock)){
                return;
            }
        }
        falling=true;
    }

    public void kill(){

    }

}
