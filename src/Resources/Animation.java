package Resources;

import java.awt.image.BufferedImage;

public class Animation {

    private int speed,index;
    private long lastTime,timer;
    private BufferedImage[] frames;
    public boolean end=false;

    public Animation(int speed,BufferedImage[] frames) {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    public Animation(int speed,BufferedImage[] frames,int index){
        this.speed=speed;
        this.frames=frames;
        this.index=index;
        timer = 0;
        lastTime = System.currentTimeMillis();

    }

    public void tick(){
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if(timer > speed){
            index++;
            timer = 0;
            if(index >= frames.length){
                end =true;
                index = 0;
            }
        }

    }

    public BufferedImage getCurrentFrame(){
        return frames[index];
    }

    public int getIndex() {
        return index;
    }

    public void reset(){
        index =0;
        end = false;
    }
    public void end() {
    	this.index = 0;
    }
}
