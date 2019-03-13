package Game.Entities;

import Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class EntityBase {
    public int x,y,width,height;

    protected Handler handler;
    public BufferedImage sprite;
    private Dimension dimension;
    public EntityBase(int x, int y, int width, int height, Handler handler,BufferedImage sprite) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.handler = handler;
        this.sprite = sprite;
        dimension = new Dimension(width, height);
    }

    public Dimension getDimension(){
        return dimension;
    }


    public void setDimension(Dimension dimension) {
        this.dimension = dimension;
    }

    public Rectangle getTopBounds(){
        return new Rectangle((int)x+dimension.width/6, (int)y, 2*dimension.width/3, dimension.height/2);
    }

    public Rectangle getBottomBounds(){
        return new Rectangle((int)x+dimension.width/6, (int)y + dimension.height/2, 2*dimension.width/3, dimension.height/2);
    }

    public Rectangle getLeftBounds(){
        return new Rectangle((int)x, (int)y + dimension.height/4, dimension.width/4, dimension.height/2);
    }

    public Rectangle getRightBounds(){
        return new Rectangle((int)x + 3*dimension.width/4, (int)y + dimension.height/4, dimension.width/4, dimension.height/2);
    }

    public Rectangle getBounds(){
        return new Rectangle((int)x, (int)y, dimension.width, dimension.height);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
