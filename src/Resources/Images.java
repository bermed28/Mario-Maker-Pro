package Resources;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
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
    public static BufferedImage[] marioSmallJumpLeft;//also store ide sprites like looking up or down
    public static BufferedImage[] marioSmallJumpRight;

    public static BufferedImage[] marioBigWalkLeft;
    public static BufferedImage[] marioBigWalkRight;
    public static BufferedImage[] marioBigRunLeft;
    public static BufferedImage[] marioBigRunRight;
    public static BufferedImage[] marioBigJumpLeft;//also store ide sprites like looking up or down and left<->right transition
    public static BufferedImage[] marioBigJumpRight;

    public static BufferedImage[] goomba;


    public static BufferedImage title;
    public static BufferedImage Pause;
    public static BufferedImage Cursor;

    public static BufferedImage testMap;
    public static BufferedImage testMaptwo;

    public static BufferedImage breakBlock;
    public static BufferedImage misteryBlock;
    public static BufferedImage surfaceBlock;
    public static BufferedImage boundBlock;
    public static BufferedImage mushroom;
    public static BufferedImage goombaDies;

    private SpriteSheet mainmenuSpriteSheet;
    private SpriteSheet backgroundSpriteSheet;
    private SpriteSheet interactableSpriteSheet;
    private SpriteSheet playerSpriteSheet;
    private SpriteSheet blockSpriteSheet;
    private SpriteSheet goombaSpriteSheet;

    public Images() {

        butstart = new BufferedImage[3];

        backgrounds = new BufferedImage[9];

        marioSmallWalkLeft = new BufferedImage[2];
        marioSmallWalkRight = new BufferedImage[2];
        marioSmallJumpLeft = new BufferedImage[4];
        marioSmallJumpRight = new BufferedImage[4];

        marioBigWalkLeft = new BufferedImage[2];
        marioBigWalkRight = new BufferedImage[2];
        marioBigRunLeft = new BufferedImage[2];
        marioBigRunRight = new BufferedImage[2];
        marioBigJumpLeft = new BufferedImage[5];
        marioBigJumpRight = new BufferedImage[5];
        goomba = new BufferedImage[2];



        try {


            //spriteSheets
            mainmenuSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/mainmenuSheet.png")));
            backgroundSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/backgroundSheet.png")));
            interactableSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/interactablesSheet.png")));
            playerSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/marioSNESSheet.png")));
            blockSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/blocksSheet.png")));
            goombaSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/goombaSprite.png")));


            //Images
            title = mainmenuSpriteSheet.crop(16,16,256,224);
            Pause = ImageIO.read(getClass().getResourceAsStream("/Sheets/Pause.png"));
            Cursor = ImageIO.read(getClass().getResourceAsStream("/Sheets/cursor.png"));
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
            //Small
            marioSmallWalkLeft[0] = playerSpriteSheet.crop(169,0,14,20);
            marioSmallWalkLeft[1] = playerSpriteSheet.crop(49,0,15,19);

            marioSmallWalkRight[0] = playerSpriteSheet.crop(209,0,14,20);
            marioSmallWalkRight[1] = playerSpriteSheet.crop(328,0,15,19);

            marioSmallJumpLeft[0] = playerSpriteSheet.crop(168,39,16,22);
            marioSmallJumpLeft[1] = playerSpriteSheet.crop(128,40,16,20);
            marioSmallJumpLeft[2] = playerSpriteSheet.crop(49,39,15,21);//up
            marioSmallJumpLeft[3] = playerSpriteSheet.crop(89,43,15,14);//down

            marioSmallJumpRight[0] = playerSpriteSheet.crop(208,39,16,22);
            marioSmallJumpRight[1] = playerSpriteSheet.crop(248,40,16,20);
            marioSmallJumpRight[2] = playerSpriteSheet.crop(328,39,15,21);//up
            marioSmallJumpRight[3] = playerSpriteSheet.crop(288,43,15,14);//down

            // Big
            marioBigWalkLeft[0] = playerSpriteSheet.crop(169,76,15,28);
            marioBigWalkLeft[1] = playerSpriteSheet.crop(8,76,16,28);

            marioBigWalkRight[0] = playerSpriteSheet.crop(208,76,15,28);
            marioBigWalkRight[1] = playerSpriteSheet.crop(368,76,16,28);

            marioBigRunLeft[0] = playerSpriteSheet.crop(169,76,15,28);
            marioBigRunLeft[1] = playerSpriteSheet.crop(48,76,16,27);

            marioBigRunRight[0] = playerSpriteSheet.crop(208,76,15,28);
            marioBigRunRight[1] = playerSpriteSheet.crop(328,76,16,27);

            marioBigJumpLeft[0] = playerSpriteSheet.crop(168,114,16,31);
            marioBigJumpLeft[1] = playerSpriteSheet.crop(128,115,16,29);
            marioBigJumpLeft[2] = playerSpriteSheet.crop(129,196,15,27);//up
            marioBigJumpLeft[3] = playerSpriteSheet.crop(88,122,16,15);//down
            marioBigJumpLeft[4] = playerSpriteSheet.crop(8,115,16,29);//change

            marioBigJumpRight[0] = playerSpriteSheet.crop(208,114,16,31);
            marioBigJumpRight[1] = playerSpriteSheet.crop(248,115,16,29);
            marioBigJumpRight[2] = playerSpriteSheet.crop(248,196,15,27);//up
            marioBigJumpRight[3] = playerSpriteSheet.crop(287,122,16,15);//down
            marioBigJumpRight[4] = playerSpriteSheet.crop(368,115,16,29);//Change


            //maps
            testMap = ImageIO.read(getClass().getResourceAsStream("/maps/testmap1.png"));
            testMaptwo = ImageIO.read(getClass().getResourceAsStream("/maps/testmap2.png"));

            //blocks
            boundBlock = interactableSpriteSheet.crop(12,73,16,16);
            misteryBlock = interactableSpriteSheet.crop(32,93,16,16);
            surfaceBlock = interactableSpriteSheet.crop(112,93,16,16);
            //breakBlock = blockSpriteSheet.crop(272,112,16,16);
            breakBlock = ImageIO.read(getClass().getResourceAsStream("/Sheets/brick.png"));

            //items
            mushroom = interactableSpriteSheet.crop(112,34,16,16);

            //enemy
            goomba[0]=goombaSpriteSheet.crop(119,40,162,162);
            goomba[1]= goombaSpriteSheet.crop(329,40,162,162);
            goombaDies=goombaSpriteSheet.crop(539,100,162,81);


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

    public static BufferedImage tint(BufferedImage src, float r, float g, float b) {

        // Copy image ( who made that so complicated :< )
        BufferedImage newImage = new BufferedImage(src.getWidth(), src.getHeight(), BufferedImage.TRANSLUCENT);
        Graphics2D graphics = newImage.createGraphics();
        graphics.drawImage(src, 0, 0, null);
        graphics.dispose();

        // Color image
        for (int i = 0; i < newImage.getWidth(); i++) {
            for (int j = 0; j < newImage.getHeight(); j++) {
                int ax = newImage.getColorModel().getAlpha(newImage.getRaster().getDataElements(i, j, null));
                int rx = newImage.getColorModel().getRed(newImage.getRaster().getDataElements(i, j, null));
                int gx = newImage.getColorModel().getGreen(newImage.getRaster().getDataElements(i, j, null));
                int bx = newImage.getColorModel().getBlue(newImage.getRaster().getDataElements(i, j, null));
                rx *= r;
                gx *= g;
                bx *= b;
                newImage.setRGB(i, j, (ax << 24) | (rx << 16) | (gx << 8) | (bx << 0));
            }
        }
        return newImage;
    }



}
