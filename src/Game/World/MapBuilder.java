package Game.World;

import java.awt.Color;
import java.awt.image.BufferedImage;

import Game.Entities.DynamicEntities.BaseDynamicEntity;
import Game.Entities.DynamicEntities.Coin;
import Game.Entities.DynamicEntities.Goomba;
import Game.Entities.DynamicEntities.Luigi;
import Game.Entities.DynamicEntities.LuigiFlag;
import Game.Entities.DynamicEntities.Mario;
import Game.Entities.DynamicEntities.MarioFlag;
import Game.Entities.DynamicEntities.Mushroom;
import Game.Entities.DynamicEntities.PiranhaPlant;
import Game.Entities.StaticEntities.BaseStaticEntity;
import Game.Entities.StaticEntities.BoundBlock;
import Game.Entities.StaticEntities.BreakBlock;
import Game.Entities.StaticEntities.MisteryBlock;
import Game.Entities.StaticEntities.SuperPowerBlock;
import Game.Entities.StaticEntities.SurfaceBlock;
import Game.Entities.StaticEntities.TeleportationBlock;
import Main.Handler;
import Resources.Images;

public class MapBuilder {
 
	public static int limitOfOtherBlocks = 2;
	public static int pixelMultiplier = 48;
	public static int boundBlock = new Color(0,0,0).getRGB();
	public static int mario = new Color(255,0,0).getRGB();
	public static int luigi = new Color(32,148,16).getRGB();
	public static int marioFlag = new Color(244,66,92).getRGB();
	public static int luigiFlag = new Color(66,244,146).getRGB();
	public static int coinitem = new Color(205,183,46).getRGB();
	public static int surfaceBlock = new Color(255,106,0).getRGB();
	public static int breakBlock = new Color(0,38,255).getRGB();
	public static int misteryBlock = new Color(255,216,0).getRGB();
	public static int mushroom = new Color(178,0,255).getRGB();
	public static int goomba = new Color(167,15,1).getRGB();
	public static int piranhaPlant = new Color(99,231,12).getRGB();	
	public static int superpowerblock = new Color(129,41,19).getRGB();
	public static int teleportationB = new Color(186,6,213).getRGB();
	public static boolean mapDone = false;

	
	// Codes Images that are passed as parameters. By going pixel by pixel.
	public static Map createMap(BufferedImage mapImage, Handler handler){
		Map mapInCreation = new Map(handler);
		for (int i = 0; i < mapImage.getWidth(); i++) {
			for (int j = 0; j < mapImage.getHeight(); j++) {
				int currentPixel = mapImage.getRGB(i, j);
				int xPos = i*pixelMultiplier;
				int yPos = j*pixelMultiplier;
				if(currentPixel == boundBlock){
					BaseStaticEntity BoundBlock = new BoundBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(BoundBlock);
				}else if(currentPixel == mario){
					BaseDynamicEntity Mario = new Mario(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(Mario);
				}else if(currentPixel == surfaceBlock){
					BaseStaticEntity SurfaceBlock = new SurfaceBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(SurfaceBlock);
				}else if(currentPixel == marioFlag){
					BaseDynamicEntity marioFlag = new MarioFlag(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(marioFlag);
				}else if(currentPixel == luigiFlag){
					BaseDynamicEntity luigiFlag = new LuigiFlag(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(luigiFlag);
				}else if(currentPixel == breakBlock){
					BaseStaticEntity BreakBlock = new BreakBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(BreakBlock);
				}else if(currentPixel == misteryBlock){
					BaseStaticEntity MisteryBlock = new MisteryBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(MisteryBlock);
				}else if(currentPixel == mushroom){
					BaseDynamicEntity Mushroom = new Mushroom(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(Mushroom);
				}else if(currentPixel == goomba){
					BaseDynamicEntity Goomba = new Goomba(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(Goomba);
				}else if(currentPixel == superpowerblock){
					BaseStaticEntity SuperPowerBlock = new SuperPowerBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addBlock(SuperPowerBlock);
				}else if(currentPixel == piranhaPlant){
					BaseDynamicEntity piranhaPlant = new PiranhaPlant(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(piranhaPlant);
				}else if(currentPixel == coinitem){
					BaseDynamicEntity coinitem = new Coin(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(coinitem);
				}else if(currentPixel == teleportationB){
					BaseStaticEntity TeleportationBlock = new TeleportationBlock(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					if(limitOfOtherBlocks > 0) {
					mapInCreation.addBlock(TeleportationBlock);
					limitOfOtherBlocks --;
					}
				}else if(currentPixel == luigi){
					BaseDynamicEntity Luigi = new Luigi(xPos,yPos,pixelMultiplier,pixelMultiplier,handler);
					mapInCreation.addEnemy(Luigi);
				}
			}

		}
		if(mapDone) {
			Images.makeMap(50, pixelMultiplier, mapImage.getWidth(), 100, mapInCreation, handler);
			for(int i = 96; i < 101; i++) {
				mapInCreation.addBlock(new BreakBlock(49*pixelMultiplier, i*pixelMultiplier,48,48,handler));
				mapInCreation.addBlock(new BreakBlock(54*pixelMultiplier, i*pixelMultiplier,48,48,handler));
			}
		}
		return mapInCreation;
	}

}
