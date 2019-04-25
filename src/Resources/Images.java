package Resources;

import javax.imageio.ImageIO;
import javax.swing.*;

import Game.Entities.StaticEntities.BreakBlock;
import Game.World.Map;
import Game.World.MapBuilder;
import Main.Handler;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class Images {


	public static BufferedImage[] butstart;
	public static BufferedImage[] backgrounds;
	public static BufferedImage[] backgrounds2;

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

	public static BufferedImage[] luigiSmallWalkLeft;
	public static BufferedImage[] luigiSmallWalkRight;
	public static BufferedImage[] luigiSmallJumpLeft;//also store ide sprites like looking up or down
	public static BufferedImage[] luigiSmallJumpRight;

	public static BufferedImage[] luigiBigWalkLeft;
	public static BufferedImage[] luigiBigWalkRight;
	public static BufferedImage[] luigiBigRunLeft;
	public static BufferedImage[] luigiBigRunRight;
	public static BufferedImage[] luigiBigJumpLeft;//also store ide sprites like looking up or down and left<->right transition
	public static BufferedImage[] luigiBigJumpRight;




	public static BufferedImage[] blueBruddaWalkLeft;
	public static BufferedImage[] blueBruddaWalkRight;
	public static BufferedImage[] blueBruddaRunLeft;
	public static BufferedImage[] blueBruddaRunRight;
	public static BufferedImage[] blueBruddaJumpLeft;//also store ide sprites like looking up or down and left<->right transition
	public static BufferedImage[] blueBruddaJumpRight;



	public static BufferedImage[] redBruddaWalkLeft;
	public static BufferedImage[] redBruddaWalkRight;
	public static BufferedImage[] redBruddaRunLeft;
	public static BufferedImage[] redBruddaRunRight;
	public static BufferedImage[] redBruddaJumpLeft;//also store ide sprites like looking up or down and left<->right transition
	public static BufferedImage[] redBruddaJumpRight;


	public static BufferedImage[] item;
	public static BufferedImage[] coinitem;
	public static BufferedImage[] coinitemcollected;
	public static BufferedImage[] enemy;
	public static BufferedImage[] hitWall;
	public static BufferedImage[] enemyFG;
	public static BufferedImage[] enemyGB1;
	public static BufferedImage[] enemyGB2;
	public static BufferedImage[] enemyGB3;
	public static BufferedImage enemyBL;
	public static BufferedImage enemyPS;
	public static BufferedImage enemyHT;
	public static BufferedImage enemySmash;


	public static BufferedImage[] goomba;
	public static BufferedImage[] piranhaPlant;
	public static BufferedImage[] marioFlag;
	public static BufferedImage[] luigiFlag;
	


	public static BufferedImage title;
	public static BufferedImage luigiLoading;
	public static BufferedImage Pause;
	public static BufferedImage Cursor;

	public static BufferedImage testMap;
	public static BufferedImage testMaptwo;

	public static BufferedImage breakBlock;
	public static BufferedImage misteryBlock;
	public static BufferedImage surfaceBlock;
	public static BufferedImage boundBlock;
	public static BufferedImage superPB;
	public static BufferedImage teleportationB;
	public static BufferedImage mushroom;
	public static BufferedImage goombaDies;
	public static BufferedImage piranhaPlantDies;
	public static BufferedImage spitBall;

	public static BufferedImage gameover;
	public static BufferedImage marioWinState;
	public static BufferedImage luigiWinState;

	private SpriteSheet mainmenuSpriteSheet;
	private SpriteSheet flagSheet;
	private SpriteSheet coinitemSheet;
	private SpriteSheet backgroundSpriteSheet;
	private SpriteSheet interactableSpriteSheet;
	private SpriteSheet player1SpriteSheet;
	private SpriteSheet blockSpriteSheet;
	private SpriteSheet newblockSpriteSheet;
	private SpriteSheet goombaSpriteSheet;
	private SpriteSheet piranhaPlantSpriteSheet;  
	private SpriteSheet SSpriteSheet;
	private SpriteSheet SAttackSpriteSheet;
	private SpriteSheet player2SpriteSheet;
	private SpriteSheet bruddaSpriteSheet;

	public Images() {

		butstart = new BufferedImage[3];

		backgrounds = new BufferedImage[9];
		backgrounds2 = new BufferedImage[6];

		marioSmallWalkLeft = new BufferedImage[2];
		marioSmallWalkRight = new BufferedImage[2];
		marioSmallJumpLeft = new BufferedImage[4];
		marioSmallJumpRight = new BufferedImage[4];

		luigiSmallWalkLeft = new BufferedImage[2];
		luigiSmallWalkRight = new BufferedImage[2];
		luigiSmallJumpLeft = new BufferedImage[4];
		luigiSmallJumpRight = new BufferedImage[4];


		item = new BufferedImage[19];
		coinitem = new BufferedImage[5];
		coinitemcollected = new BufferedImage[1];
		enemy = new BufferedImage[9];
		hitWall = new BufferedImage[44];
		enemyFG = new BufferedImage[9];
		enemyGB1 = new BufferedImage[3];
		enemyGB2 = new BufferedImage[2];
		enemyGB3 = new BufferedImage[3];
		
		marioFlag = new BufferedImage[3];
		luigiFlag = new BufferedImage[3];


		marioBigWalkLeft = new BufferedImage[2];
		marioBigWalkRight = new BufferedImage[2];
		marioBigRunLeft = new BufferedImage[2];
		marioBigRunRight = new BufferedImage[2];
		marioBigJumpLeft = new BufferedImage[5];
		marioBigJumpRight = new BufferedImage[5];

		luigiBigWalkLeft = new BufferedImage[2];
		luigiBigWalkRight = new BufferedImage[2];
		luigiBigRunLeft = new BufferedImage[2];
		luigiBigRunRight = new BufferedImage[2];
		luigiBigJumpLeft = new BufferedImage[5];
		luigiBigJumpRight = new BufferedImage[5];

		blueBruddaWalkLeft = new BufferedImage[2];
		blueBruddaWalkRight = new BufferedImage[2];
		blueBruddaRunLeft = new BufferedImage[2];
		blueBruddaRunRight = new BufferedImage[2];
		blueBruddaJumpLeft = new BufferedImage[5];
		blueBruddaJumpRight = new BufferedImage[5];

		redBruddaWalkLeft = new BufferedImage[2];
		redBruddaWalkRight = new BufferedImage[2];
		redBruddaRunLeft = new BufferedImage[2];
		redBruddaRunRight = new BufferedImage[2];
		redBruddaJumpLeft = new BufferedImage[5];
		redBruddaJumpRight = new BufferedImage[5];

		goomba = new BufferedImage[2];
		piranhaPlant = new BufferedImage[12];



		try {


			//spriteSheets
			mainmenuSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/mainmenuSheet.png")));
			backgroundSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/backgroundSheet.png")));

			gameover = ImageIO.read(getClass().getResourceAsStream("/Sheets/Gameover2.png"));
			marioWinState = ImageIO.read(getClass().getResourceAsStream("/Sheets/marioWin.png"));
			luigiWinState = ImageIO.read(getClass().getResourceAsStream("/Sheets/luigiWin.png"));

			interactableSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/interactablesSheet.png")));
			coinitemSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/interactablesSheet.png")));
			spitBall = ImageIO.read(getClass().getResourceAsStream("/Sheets/spitBall.png"));
			player1SpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/marioSNESSheet.png")));
			player2SpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/player2Sheet.png")));
			bruddaSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/knucklesSprite.png")));
			blockSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/blocksSheet.png")));
			newblockSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/New_Blocks.png")));
			goombaSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/goombaSprite.png")));
			piranhaPlantSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/pihrannaPlantSheet.png")));
			SSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/Sheets.png")));
			SAttackSpriteSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/enemySheet2.png")));
			flagSheet = new SpriteSheet(ImageIO.read(getClass().getResourceAsStream("/Sheets/flagSprite.png")));


			//Images
			title = mainmenuSpriteSheet.crop(16,16,256,224);
			luigiLoading = ImageIO.read(getClass().getResourceAsStream("/Sheets/luigi-wallpapers.jpg"));
			Pause = ImageIO.read(getClass().getResourceAsStream("/Sheets/SuperPause.jpg"));
			Cursor = ImageIO.read(getClass().getResourceAsStream("/Sheets/cursor.png"));
			butstart[0]= ImageIO.read(getClass().getResourceAsStream("/Buttons/NormBut.png"));//normbut
			butstart[1]= ImageIO.read(getClass().getResourceAsStream("/Buttons/HoverBut.png"));//hoverbut
			butstart[2]= ImageIO.read(getClass().getResourceAsStream("/Buttons/ClickedBut.png"));//clickbut
			superPB =  newblockSpriteSheet.crop(1728,34,347,355);
			teleportationB =  newblockSpriteSheet.crop(24,729,348,357);

			backgrounds[0] = backgroundSpriteSheet.crop(2,2,512,432);
			backgrounds[1] = backgroundSpriteSheet.crop(516,2,512,432);
			backgrounds[2] = backgroundSpriteSheet.crop(2,438,512,432);
			backgrounds[3] = backgroundSpriteSheet.crop(516,438,512,432);
			backgrounds[4] = backgroundSpriteSheet.crop(2,872,512,432);
			backgrounds[5] = backgroundSpriteSheet.crop(516,872,512,432);
			backgrounds[6] = backgroundSpriteSheet.crop(2,1306,512,432);
			backgrounds[7] = backgroundSpriteSheet.crop(516,1306,512,432);
			backgrounds[8] = backgroundSpriteSheet.crop(2,1740,512,432);

			backgrounds2[0] = ImageIO.read(getClass().getResourceAsStream("/Sheets/GrasslandBackground.png"));
			backgrounds2[1] = ImageIO.read(getClass().getResourceAsStream("/Sheets/DarkBackground.png"));
			backgrounds2[2] = ImageIO.read(getClass().getResourceAsStream("/Sheets/CanyonBackground.png"));
			backgrounds2[3] = ImageIO.read(getClass().getResourceAsStream("/Sheets/DesertBackground.png"));
			backgrounds2[4] = ImageIO.read(getClass().getResourceAsStream("/Sheets/Snow1Background.png"));
			backgrounds2[5] = ImageIO.read(getClass().getResourceAsStream("/Sheets/Snow2Background.png"));


			//Player 1 (Mario) Sprites
			//Small
			marioSmallWalkLeft[0] = player1SpriteSheet.crop(169,0,14,20);
			marioSmallWalkLeft[1] = player1SpriteSheet.crop(49,0,15,19);

			marioSmallWalkRight[0] = player1SpriteSheet.crop(209,0,14,20);
			marioSmallWalkRight[1] = player1SpriteSheet.crop(328,0,15,19);

			marioSmallJumpLeft[0] = player1SpriteSheet.crop(168,39,16,22);
			marioSmallJumpLeft[1] = player1SpriteSheet.crop(128,40,16,20);
			marioSmallJumpLeft[2] = player1SpriteSheet.crop(49,39,15,21);//up
			marioSmallJumpLeft[3] = player1SpriteSheet.crop(89,43,15,14);//down

			marioSmallJumpRight[0] = player1SpriteSheet.crop(208,39,16,22);
			marioSmallJumpRight[1] = player1SpriteSheet.crop(248,40,16,20);
			marioSmallJumpRight[2] = player1SpriteSheet.crop(328,39,15,21);//up
			marioSmallJumpRight[3] = player1SpriteSheet.crop(288,43,15,14);//down

			// Big
			marioBigWalkLeft[0] = player1SpriteSheet.crop(169,76,15,28);
			marioBigWalkLeft[1] = player1SpriteSheet.crop(8,76,16,28);

			marioBigWalkRight[0] = player1SpriteSheet.crop(208,76,15,28);
			marioBigWalkRight[1] = player1SpriteSheet.crop(368,76,16,28);

			marioBigRunLeft[0] = player1SpriteSheet.crop(169,76,15,28);
			marioBigRunLeft[1] = player1SpriteSheet.crop(48,76,16,27);

			marioBigRunRight[0] = player1SpriteSheet.crop(208,76,15,28);
			marioBigRunRight[1] = player1SpriteSheet.crop(328,76,16,27);

			marioBigJumpLeft[0] = player1SpriteSheet.crop(168,114,16,31);
			marioBigJumpLeft[1] = player1SpriteSheet.crop(128,115,16,29);
			marioBigJumpLeft[2] = player1SpriteSheet.crop(129,196,15,27);//up
			marioBigJumpLeft[3] = player1SpriteSheet.crop(88,122,16,15);//down
			marioBigJumpLeft[4] = player1SpriteSheet.crop(8,115,16,29);//change

			marioBigJumpRight[0] = player1SpriteSheet.crop(208,114,16,31);
			marioBigJumpRight[1] = player1SpriteSheet.crop(248,115,16,29);
			marioBigJumpRight[2] = player1SpriteSheet.crop(248,196,15,27);//up
			marioBigJumpRight[3] = player1SpriteSheet.crop(287,122,16,15);//down
			marioBigJumpRight[4] = player1SpriteSheet.crop(368,115,16,29);//Change

			//Player 2 (Luigi) Sprites
			//Luigi Small 

			luigiSmallWalkLeft[0] = player2SpriteSheet.crop(199,0,16,23);
			luigiSmallWalkLeft[1] = player2SpriteSheet.crop(169,0,16,22);

			luigiSmallWalkRight[0] = player2SpriteSheet.crop(234, 0, 16, 24);
			luigiSmallWalkRight[1] = player2SpriteSheet.crop(263,0,17,22);

			luigiSmallJumpLeft[0] = player2SpriteSheet.crop(198,30,17,22);
			luigiSmallJumpLeft[1] = player2SpriteSheet.crop(167,30,18,22);
			luigiSmallJumpLeft[2] = player2SpriteSheet.crop(85,30,20,23);//up
			luigiSmallJumpLeft[3] = player2SpriteSheet.crop(111,33,16,16);//down

			luigiSmallJumpRight[0] = player2SpriteSheet.crop(233,30,17,22);
			luigiSmallJumpRight[1] = player2SpriteSheet.crop(264,29,17,24);
			luigiSmallJumpRight[2] = player2SpriteSheet.crop(347,29,17,24);//up
			luigiSmallJumpRight[3] = player2SpriteSheet.crop(321,34,16,16);//down

			// Luigi Big
			luigiBigWalkLeft[0] = player2SpriteSheet.crop(199,116,17,31);
			luigiBigWalkLeft[1] = player2SpriteSheet.crop(167,115,18,32);

			luigiBigWalkRight[0] = player2SpriteSheet.crop(233,116,17,31);
			luigiBigWalkRight[1] = player2SpriteSheet.crop(263,115,18,32);

			luigiBigRunLeft[0] = player2SpriteSheet.crop(199,116,17,31);
			luigiBigRunLeft[1] = player2SpriteSheet.crop(137,115,19,32);

			luigiBigRunRight[0] = player2SpriteSheet.crop(233,116,17,31);
			luigiBigRunRight[1] = player2SpriteSheet.crop(293,115,19,30);

			luigiBigJumpLeft[0] = player2SpriteSheet.crop(197,156,18,30);
			luigiBigJumpLeft[1] = player2SpriteSheet.crop(167,156,18,30);
			luigiBigJumpLeft[2] = player2SpriteSheet.crop(85,155,16,31);//up
			luigiBigJumpLeft[3] = player2SpriteSheet.crop(110,162,18,17);//down
			luigiBigJumpLeft[4] = player2SpriteSheet.crop(53,155,18,32);//change

			luigiBigJumpRight[0] = player2SpriteSheet.crop(233,155,17,31);
			luigiBigJumpRight[1] = player2SpriteSheet.crop(263,157,19,30);
			luigiBigJumpRight[2] = player2SpriteSheet.crop(348,155,16,31);//up
			luigiBigJumpRight[3] = player2SpriteSheet.crop(321,162, 16,16);//down
			luigiBigJumpRight[4] = player2SpriteSheet.crop(378,154,18,33);//Change

			//Blue Brudda Animations //DONE ALL

			//JUMP
			blueBruddaJumpRight[0] = bruddaSpriteSheet.crop(235,171,44,44); 
			blueBruddaJumpRight[1] = bruddaSpriteSheet.crop(192,178,43,38);
			blueBruddaJumpRight[2] = bruddaSpriteSheet.crop(381,94,39,38);//up
			blueBruddaJumpRight[3] = bruddaSpriteSheet.crop(152,185,40,30);//down
			blueBruddaJumpRight[4] = bruddaSpriteSheet.crop(358,174,31,40);//change

			blueBruddaJumpLeft[0] = bruddaSpriteSheet.crop(745,167,44,44);
			blueBruddaJumpLeft[1] = bruddaSpriteSheet.crop(789,173,44,38); 
			blueBruddaJumpLeft[2] = bruddaSpriteSheet.crop(605,89,40,39);//up
			blueBruddaJumpLeft[3] = bruddaSpriteSheet.crop(832,181,41,30);//down
			blueBruddaJumpLeft[4] = bruddaSpriteSheet.crop(636,170,32,40);//Change

			//RUN
			blueBruddaRunLeft[0] = bruddaSpriteSheet.crop(989,130,35,37); 
			blueBruddaRunLeft[1] = bruddaSpriteSheet.crop(953,129,35,37);



			blueBruddaRunRight[0] = bruddaSpriteSheet.crop(0,134,38,34);
			blueBruddaRunRight[1] = bruddaSpriteSheet.crop(39,134,35,37);
		

			//WALK
			blueBruddaWalkLeft[0] = bruddaSpriteSheet.crop(986,91,38,39);
			blueBruddaWalkLeft[1] = bruddaSpriteSheet.crop(954,90,33,40); 

			blueBruddaWalkRight[0] = bruddaSpriteSheet.crop(0,94,37,40); 
			blueBruddaWalkRight[1] = bruddaSpriteSheet.crop(71,94,32,40);


			//Red Brudda Animations //DONE

			//JUMP
			redBruddaJumpLeft[0] = bruddaSpriteSheet.crop(677,382,44,42);
			redBruddaJumpLeft[1] = bruddaSpriteSheet.crop(787,382,44,37);
			redBruddaJumpLeft[2] = bruddaSpriteSheet.crop(946,296,40,39);//up
			redBruddaJumpLeft[3] = bruddaSpriteSheet.crop(759,437,39,29);//down
			redBruddaJumpLeft[4] = bruddaSpriteSheet.crop(947,425,35,41);//change

			redBruddaJumpRight[0] = bruddaSpriteSheet.crop(304,386,45,43);
			redBruddaJumpRight[1] = bruddaSpriteSheet.crop(194,386,45,38); 
			redBruddaJumpRight[2] = bruddaSpriteSheet.crop(40,301,39,40); //up
			redBruddaJumpRight[3] = bruddaSpriteSheet.crop(230,443, 37,28);//down
			redBruddaJumpRight[4] = bruddaSpriteSheet.crop(46,430,32,42);//Change

			//RUN 
			redBruddaRunLeft[0] = bruddaSpriteSheet.crop(911,298,35,38);
			redBruddaRunLeft[1] = bruddaSpriteSheet.crop(874,299,35,36);
		 
			redBruddaRunRight[0] = bruddaSpriteSheet.crop(80,304,35,38);
			redBruddaRunRight[1] = bruddaSpriteSheet.crop(116,303,35,38);


			//WALK
			redBruddaWalkLeft[0] = bruddaSpriteSheet.crop(987,343,37,39); 
			redBruddaWalkLeft[1] = bruddaSpriteSheet.crop(955,342,33,40);

			redBruddaWalkRight[0] = bruddaSpriteSheet.crop(0,347,37,39); 
			redBruddaWalkRight[1] = bruddaSpriteSheet.crop(38,347,32,40);


			
			marioFlag[0] = flagSheet.crop(58, 211, 30, 39);
			marioFlag[1] = flagSheet.crop(91, 211, 31, 39);
			marioFlag[2] = flagSheet.crop(125, 212, 30, 36);
			
			luigiFlag[0] = flagSheet.crop(207, 211, 30, 41);
			luigiFlag[1] = flagSheet.crop(237,213,28,36);
			luigiFlag[2] = flagSheet.crop(267,214,29,36);
			
			
			
			item[0] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL1.png"));
			item[1] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL2.png"));
			item[2] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL3.png"));
			item[3] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL4.png"));
			item[4] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL5.png"));
			item[5] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL6.png"));
			item[6] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL7.png"));
			item[7] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL8.png"));
			item[8] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL9.png"));
			item[9] =  ImageIO.read(getClass().getResourceAsStream("/Sheets/item/SL10.png"));
			item[10] =  item[9];
			item[11] =  item[8];
			item[12] =  item[7];
			item[13] =  item[6];
			item[14] =  item[5];
			item[15] =  item[4];
			item[16] =  item[3];
			item[17] =  item[2];
			item[18] =  item[1];
			
			coinitem[0] = coinitemSheet.crop(13, 13, 14, 17);
			coinitem[1] = coinitemSheet.crop(29, 13, 12, 18);
			coinitem[2] = coinitemSheet.crop(40, 13, 10, 18);
			coinitem[3] = coinitemSheet.crop(28, 13, 13, 18);
			coinitem[4] = coinitemSheet.crop(50, 13, 10, 18);
			
			
			coinitemcollected[0] = coinitemSheet.crop(59, 14, 13, 18);
			
			hitWall[0] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO1.png"));
			hitWall[1] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO2.png"));
			hitWall[2] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO3.png"));
			hitWall[3] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO4.png"));
			hitWall[4] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO5.png"));
			hitWall[5] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO6.png"));
			hitWall[6] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO7.png"));
			hitWall[7] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO8.png"));
			hitWall[8] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO9.png"));
			hitWall[9] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO10.png"));
			hitWall[10] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO11.png"));
			hitWall[11] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO12.png"));
			hitWall[12] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO13.png"));
			hitWall[13] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO14.png"));
			hitWall[14] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO15.png"));
			hitWall[15] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO17.png"));
			hitWall[16] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO18.png"));
			hitWall[17] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO19.png"));
			hitWall[18] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO20.png"));
			hitWall[19] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO21.png"));
			hitWall[20] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO22.png"));
			hitWall[21] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO23.png"));
			hitWall[22] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO24.png"));
			hitWall[23] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO25.png"));
			hitWall[24] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO26.png"));
			hitWall[25] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO27.png"));
			hitWall[26] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO28.png"));
			hitWall[27] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO29.png"));
			hitWall[28] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO30.png"));
			hitWall[29] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO31.png"));
			hitWall[30] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO32.png"));
			hitWall[31] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO33.png"));
			hitWall[32] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO35.png"));
			hitWall[33] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO36.png"));
			hitWall[34] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO37.png"));
			hitWall[35] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO38.png"));
			hitWall[36] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO39.png"));
			hitWall[37] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO40.png"));
			hitWall[38] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO41.png"));
			hitWall[39] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO42.png"));
			hitWall[40] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO43.png"));
			hitWall[41] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO44.png"));
			hitWall[42] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO45.png"));
			hitWall[43] = ImageIO.read(getClass().getResourceAsStream("/Sheets/KO/KO46.png"));

			enemy[0] = SSpriteSheet.crop(8,14,64,45);
			enemy[1] = SSpriteSheet.crop(88,21,60,38);
			enemy[2] = SSpriteSheet.crop(165,25,55,37);
			enemy[3] = SSpriteSheet.crop(242,28,50,35);
			enemy[4] = SSpriteSheet.crop(315,28,48,34);
			enemy[5] = SSpriteSheet.crop(381,27,57,35);
			enemy[6] = SSpriteSheet.crop(454,25,61,35);
			enemy[7] = SSpriteSheet.crop(525,19,62,37);
			enemy[8] = SSpriteSheet.crop(75,89,51,41);

			enemyFG[0] = SAttackSpriteSheet.crop(3,115,66,45);
			enemyFG[1] = SAttackSpriteSheet.crop(72,103,71,55);
			enemyFG[2] =  SAttackSpriteSheet.crop(149,101,64,62);
			enemyFG[3] =  SAttackSpriteSheet.crop(215,102,80,62);
			enemyFG[4] =  SAttackSpriteSheet.crop(5,167,79,62);
			enemyFG[5] =  SAttackSpriteSheet.crop(90,167,93,62);
			enemyFG[6] = SAttackSpriteSheet.crop(188,167,99,72);
			enemyFG[7] =  enemyFG[1];
			enemyFG[8] =  enemyFG[0];

			enemyGB1[0] = SAttackSpriteSheet.crop(3,248,53,48);
			enemyGB1[1] = SAttackSpriteSheet.crop(68,247,61,46);
			enemyGB1[2] =  SAttackSpriteSheet.crop(147,252,48,40);


			enemyGB2[0] = SAttackSpriteSheet.crop(205,250,34,41);
			enemyGB2[1] = SAttackSpriteSheet.crop(252,251,45,44);


			enemyGB3[0] = enemyGB1[2];
			enemyGB3[1] = enemyGB1[1];
			enemyGB3[2] =  enemyGB1[0];

			enemyPS= SAttackSpriteSheet.crop(68,358,29,64);
			enemyHT= SAttackSpriteSheet.crop(178,357,52,62);
			enemySmash= SAttackSpriteSheet.crop(9,302,51,41);
			enemyBL= SAttackSpriteSheet.crop(188,184,41,55);

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

			//Enemy Pihranna Plant
			piranhaPlant[0] = piranhaPlantSpriteSheet.crop(7, 19, 32, 50);
			piranhaPlant[1] = piranhaPlantSpriteSheet.crop(40, 22, 29, 48);
			piranhaPlant[2] = piranhaPlantSpriteSheet.crop(73, 25, 32, 45);
			piranhaPlant[3] = piranhaPlantSpriteSheet.crop(107, 28, 31, 42);
			piranhaPlant[4] = piranhaPlantSpriteSheet.crop(141, 27, 30, 42);
			piranhaPlant[5] = piranhaPlantSpriteSheet.crop(172, 24, 33, 47);
			piranhaPlant[6] = piranhaPlantSpriteSheet.crop(172, 24, 33, 47);
			piranhaPlant[7] = piranhaPlantSpriteSheet.crop(141, 27, 30, 42);
			piranhaPlant[8] = piranhaPlantSpriteSheet.crop(107, 28, 31, 42);
			piranhaPlant[9] = piranhaPlantSpriteSheet.crop(73, 25, 32, 45);
			piranhaPlant[10] = piranhaPlantSpriteSheet.crop(40, 22, 29, 48);
			piranhaPlant[11] = piranhaPlantSpriteSheet.crop(7, 19, 32, 50);
			piranhaPlantDies = piranhaPlantSpriteSheet.crop(172, 24, 33, 47);


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
	public static  void makeMap(int i, int j, int k, int z,  Map map, Handler h) {
		for(int x = i; x < k; x++) {
			map.addBlock(new BreakBlock( x * j, z * j, j, j, h));
		}	
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
