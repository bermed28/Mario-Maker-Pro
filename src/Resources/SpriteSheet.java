package Resources;

import java.awt.image.BufferedImage;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class SpriteSheet {


    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet){
        this.sheet=sheet;

    }
    public BufferedImage crop(int x, int y, int width, int height){
        return sheet.getSubimage(x,y,width,height);
    }

}
