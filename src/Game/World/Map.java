package Game.World;

import Game.Entities.DynamicEntities.*;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Main.Handler;

import java.awt.*;
import java.util.ArrayList;

import Display.UI.UIListener;

public class Map {
	UIListener listener;
    ArrayList<BaseStaticEntity> blocksOnMap;
    ArrayList<BaseDynamicEntity> enemiesOnMap;
    Handler handler;
    private double bottomBorder;

    public Map(Handler handler) {
    	this.handler=handler;
    	listener = new UIListener( this.handler);
        this.blocksOnMap = new ArrayList<>();
        this.enemiesOnMap = new ArrayList<>();
        bottomBorder=handler.getHeight();
    }

    public void addBlock(BaseStaticEntity block){
        blocksOnMap.add(block);
    }
    public void addEnemy(BaseDynamicEntity entity){
        if(entity instanceof Mario){
            handler.setMario((Mario) entity);
            handler.getCamera().setX(handler.getMario().x- (MapBuilder.pixelMultiplier*6));
            handler.getCamera().setY(handler.getMario().y - (MapBuilder.pixelMultiplier*10));
            bottomBorder=handler.getHeight()+handler.getMario().y;
        }else {
            enemiesOnMap.add(entity);
        }
    }

    public void drawMap(Graphics2D g2) {
        handler.setIsInMap(true);
        Point camLocation = new Point((int)handler.getCamera().getX(), (int)handler.getCamera().getY());
        g2.translate(-camLocation.x, -camLocation.y);
        for (BaseStaticEntity block:blocksOnMap) {
            g2.drawImage(block.sprite,block.x,block.y,block.width,block.height,null);
        }
        for (BaseDynamicEntity entity:enemiesOnMap) {
            if(entity instanceof Item){
                if(!((Item)entity).used){
                    g2.drawImage(entity.sprite, entity.x, entity.y, entity.width, entity.height, null);
                }
            }else if(entity instanceof Goomba && !entity.ded){
                    g2.drawImage(((Goomba)entity).anim.getCurrentFrame(), entity.x, entity.y, entity.width, entity.height, null);
            }else {
                g2.drawImage(entity.sprite, entity.x, entity.y, entity.width, entity.height, null);
            }
        }
        handler.getMario().drawMario(g2);
        if(MapBuilder.mapDone) this.listener.render(g2);
        g2.translate(camLocation.x, camLocation.y);

    }

    public ArrayList<BaseStaticEntity> getBlocksOnMap() {
        return blocksOnMap;
    }

    public ArrayList<BaseDynamicEntity> getEnemiesOnMap() {
        return enemiesOnMap;
    }

    public double getBottomBorder() {
        return bottomBorder;
    }
    
    public UIListener getListener() {
    	return this.listener;
    }

    public void reset() {
    }
}
