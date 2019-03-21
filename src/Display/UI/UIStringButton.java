package Display.UI;

import Main.Handler;

import java.awt.*;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class UIStringButton extends UIObject{
    private String text;
    private ClickListlener clicker;
    public Handler handler;
    Color color;

    public UIStringButton(float x, float y, int width, int height, String s, ClickListlener clicker, Handler handler ,Color color) {
        super(x, y, width, height);
        this.clicker=clicker;
        text=s;
        this.handler=handler;
        this.color=color;
    }


    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        g.setColor(color);
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
