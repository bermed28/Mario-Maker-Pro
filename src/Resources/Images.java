package Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class Images {


    public static BufferedImage[] butstart;
    public static BufferedImage[] backgrounds;
    public static BufferedImage[] marioSmallWalkLeft;
    public static BufferedImage[] marioSmallWalkRight;
    public static BufferedImage title;
    public static BufferedImage Pause;
    public static BufferedImage testMap;
    public static BufferedImage testMaptwo;
    public static BufferedImage breakBlock;
    public static BufferedImage misteryBlock;
    public static BufferedImage surfaceBlock;
    public static BufferedImage boundBlock;
    private SpriteSheet mainmenuSpriteSheet;
    private SpriteSheet backgroundSpriteSheet;
    private SpriteSheet interactableSpriteSheet;
    private SpriteSheet playerSpriteSheet;

    public Images() {

        butstart = new BufferedImage[3];
        backgrounds = new BufferedImage[9];
        marioSmallWalkLeft = new BufferedImage[4];
        marioSmallWalkRight = new BufferedImage[4];



        try {


            //spriteSheets
            mainmenuSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/mainmenuSheet.png")));
            backgroundSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/backgroundSheet.png")));
            interactableSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/interactablesSheet.png")));
            playerSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/marioSheets.png")));


            //Images
            title = mainmenuSpriteSheet.crop(16,16,256,224);
            Pause = ImageIO.read(getClass().getResourceAsStream("/Sheets/Pause.png"));
            butstart[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/NormBut.png"));//normbut
            butstart[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/HoverBut.png"));//hoverbut
            butstart[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/ClickedBut.png"));//clickbut

            backgrounds[0] = backgroundSpriteSheet.crop(2,2,512,432);
            backgrounds[1] = backgroundSpriteSheet.crop(516,2,512,432);
            backgrounds[2] = backgroundSpriteSheet.crop(2,438,512,432);
            backgrounds[3] = backgroundSpriteSheet.crop(516,438,512,432);
            backgrounds[4] = backgroundSpriteSheet.crop(2,872,512,432);
            backgrounds[5] = backgroundSpriteSheet.crop(516,872,512,432);
            backgrounds[6] = backgroundSpriteSheet.crop(2,1306,512,432);
            backgrounds[7] = backgroundSpriteSheet.crop(516,1306,512,432);
            backgrounds[8] = backgroundSpriteSheet.crop(2,1740,512,432);


            //player sprites
            marioSmallWalkLeft[0] = playerSpriteSheet.crop(0,48,39,48);
            marioSmallWalkLeft[1] = playerSpriteSheet.crop(0,96,48,48);
            marioSmallWalkLeft[2] = playerSpriteSheet.crop(0,144,36,48);
            marioSmallWalkLeft[3] = playerSpriteSheet.crop(0,195,42,45);

            marioSmallWalkRight[0] = playerSpriteSheet.crop(52,48,39,48);
            marioSmallWalkRight[1] = playerSpriteSheet.crop(52,96,48,45);
            marioSmallWalkRight[2] = playerSpriteSheet.crop(52,144,36,48);
            marioSmallWalkRight[3] = playerSpriteSheet.crop(52,195,42,45);

            //maps
            testMap = ImageIO.read(getClass().getResourceAsStream("/maps/testmap1.png"));
            testMaptwo = ImageIO.read(getClass().getResourceAsStream("/maps/testmap2.png"));

            //blocks
            boundBlock = interactableSpriteSheet.crop(12,73,16,16);
            misteryBlock = interactableSpriteSheet.crop(32,93,16,16);
            surfaceBlock = interactableSpriteSheet.crop(112,93,16,16);
            breakBlock = interactableSpriteSheet.crop(72,73,16,16);


        }catch (IOException e) {
        e.printStackTrace();
    }


    }

    public static BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(Images.class.getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }

}
