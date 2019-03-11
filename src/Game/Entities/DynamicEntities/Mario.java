package Game.Entities.DynamicEntities;

import Main.Handler;

public class Mario extends BaseDynamicEntity{

    private double velX,velY;

    public Mario(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler,null);
    }

    public void tick(){
        if(handler.getKeyManager().right){
            velX=5;
        }else if(handler.getKeyManager().left){
            velX=-5;
        }else{
            velX=0;
        }
        x+=velX;
    }

    public double getVelX() {
        return velX;
    }
    public double getVelY() {
        return velY;
    }

    public double getX() {
        return this.x;
    }
}
