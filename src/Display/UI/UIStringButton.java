package Display.UI;

import Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class UIStringButton extends UIObject{
    private String text;
    private ClickListlener clicker;
    public Handler handler;

    public UIStringButton(float x, float y, int width, int height, String s, ClickListlener clicker, Handler handler ) {
        super(x, y, width, height);
        this.clicker=clicker;
        text=s;
        this.handler=handler;
    }


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLACK);
        if(active){
            g.setFont(new Font("Calibri", Font.BOLD, 24));
            g.drawString(text,(int)x, (int)y+(this.heith/2));
        }
        else if(hovering){
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
            g.drawString(text,(int)x, (int)y+(this.heith/2));
        }else{
            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
            g.drawString(text,(int)x, (int)y+(this.heith/2));
        }
    }


    @Override
    public void onClick()
    {
        clicker.onClick();
    }
}
