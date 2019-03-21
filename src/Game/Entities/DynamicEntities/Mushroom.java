package Game.Entities.DynamicEntities;

import Game.Entities.StaticEntities.BaseStaticEntity;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Mushroom extends Item {

    String direction = "Right";
    boolean falling = true;
    public double gravityAcc = 0.38;
    private double velX=1,velY = 7;


    public Mushroom(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.mushroom);
    }

    @Override
    public void tick(){
        if(!used) {
            if (falling) {
                y = (int) (y + velY);
                velY = velY + gravityAcc;
                checkFalling();
            }
            checkHorizontal();
            move();
        }
    }

    public void render(Graphics g){

    }


    private void checkHorizontal() {
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

    private void checkFalling() {
        Rectangle mushroomBottom = getBottomBounds();

        for (BaseStaticEntity brick: handler.getMap().getBlocksOnMap()) {
            Rectangle topBlock = brick.getTopBounds();
            if(mushroomBottom.intersects(topBlock)){
                falling = false;
            }
        }
    }

    public void move(){

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

   


}
