package Game.Entities;

import Main.Handler;

import java.awt.image.BufferedImage;

public class EntityBase {
    public int x,y,width,height;

    protected Handler handler;
    public BufferedImage sprite;
    public EntityBase(int x, int y, int width, int height, Handler handler,BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.handler = handler;
        this.sprite = sprite;
    }
}
