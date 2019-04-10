package Main;

import Display.DisplayScreen;
import Display.UI.UIPointer;
import Game.Entities.DynamicEntities.Luigi;
import Game.Entities.DynamicEntities.Mario;
import Game.Entities.DynamicEntities.Player;
import Game.Entities.StaticEntities.BreakBlock;
import Game.GameStates.GameOverState;
import Game.GameStates.GameState;
import Game.GameStates.MenuState;
//import Game.GameStates.MultiPlayerState;
import Game.GameStates.PauseState;
import Game.GameStates.Player_Selection;
import Game.GameStates.State;
import Game.GameStates.WinState;
import Game.World.Map;
import Game.World.MapBuilder;
import Input.Camera;
import Input.KeyManager;
import Input.MouseManager;
import Resources.Images;
import Resources.MusicHandler;

import java.awt.*;
import java.awt.image.BufferStrategy;


/**
 * Created by AlexVR on 7/1/2018.
 */

public class GameSetUp implements Runnable {
	public DisplayScreen display;
	public DisplayScreen luigi_display;
	public String title;

	private boolean running = false;
	private Thread thread;
	public static boolean threadB;

	private BufferStrategy bs;
	private Graphics g;
	private BufferStrategy bsluigi;
	private Graphics gluigi;

	public UIPointer pointer;

	//Input
	public KeyManager keyManager;
	public MouseManager mouseManager;
	public MouseManager initialmouseManager;

	//Handler
	private Handler handler;

	//States
	public State gameState;
	public State menuState;
	public State pauseState;
	public State playerSelectionState;
	public State gameoverState;
	public State multiPlayerState;
	public State winState;
	
	//Res.music
	private MusicHandler musicHandler;


	public GameSetUp(String title,Handler handler) {
		this.handler = handler;
		this.title = title;
		threadB=false;

		keyManager = new KeyManager();
		mouseManager = new MouseManager();
		initialmouseManager = mouseManager;
		musicHandler = new MusicHandler(handler);
		handler.setCamera(new Camera());
		handler.setLuigiCamera(new Camera());
	}

	private void init(){
		display = new DisplayScreen(title, handler.width, handler.height);
		display.getFrame().addKeyListener(keyManager);
		display.getFrame().addMouseListener(mouseManager);
		display.getFrame().addMouseMotionListener(mouseManager);
		display.getCanvas().addMouseListener(mouseManager);
		display.getCanvas().addMouseMotionListener(mouseManager);

		Images img = new Images();

		musicHandler.restartBackground();

		gameState = new GameState(handler);
		menuState = new MenuState(handler);
		pauseState = new PauseState(handler);
		gameoverState = new GameOverState(handler);
		winState = new WinState(handler);
		playerSelectionState = new Player_Selection(handler);


		State.setState(playerSelectionState);
	}

	public void reStart(){
		gameState = new GameState(handler);
	}

	public synchronized void start(){
		if(running)
			return;
		running = true;
		//this runs the run method in this  class
		thread = new Thread(this);
		thread.start();
	}

	public void run(){

		//initiallizes everything in order to run without breaking
		init();

		int fps = 60;
		double timePerTick = 1000000000 / fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;

		while(running){
			//makes sure the games runs smoothly at 60 FPS
			now = System.nanoTime();
			delta += (now - lastTime) / timePerTick;
			timer += now - lastTime;
			lastTime = now;

			if(delta >= 1){
				//re-renders and ticks the game around 60 times per second
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000){
				ticks = 0;
				timer = 0;
			}
		}

		stop();

	}

	private void tick(){
		//checks for key types and manages them
		keyManager.tick();

		if(musicHandler.ended()){
			musicHandler.restartBackground();
		}

		if(Player_Selection.MultiPlayer && Player_Selection.Screenmove) {
			luigi_display = new DisplayScreen(title, handler.width, handler.height);
			luigi_display.getFrame().setLocation(display.getFrame().getX()+display.getFrame().getWidth()/2, display.getFrame().getY());
			display.getFrame().setLocation(display.getFrame().getX()-display.getFrame().getWidth()/2, display.getFrame().getY());
			Player_Selection.Screenmove = false;
		}
		//game states are the menus
		if(State.getState() != null)
			State.getState().tick();
		if (handler.isInMap()) {
			if(!Player_Selection.MultiPlayer) {
				updateCamera();
			}
			else {
				updateCamera();
				updateLuigiCamera();

			}

		}

	}
	// COPY PASTE THIS FOR LUIGIS CAMERA
	private void updateCamera() {
		Player mario = handler.getMario();
		double marioVelocityX = mario.getVelX();
		double marioVelocityY = mario.getVelY();
		double shiftAmount = 0;
		double shiftAmountY = 0;

		if (marioVelocityX > 0 && mario.getX() - 2*(handler.getWidth()/3) > handler.getCamera().getX()) {
			shiftAmount = marioVelocityX;
		}
		if (marioVelocityX < 0 && mario.getX() +  2*(handler.getWidth()/3) < handler.getCamera().getX()+handler.width) {
			shiftAmount = marioVelocityX;
		}
		if (marioVelocityY > 0 && mario.getY() - 2*(handler.getHeight()/3) > handler.getCamera().getY()) {
			shiftAmountY = marioVelocityY;
		}
		if (marioVelocityX < 0 && mario.getY() +  2*(handler.getHeight()/3) < handler.getCamera().getY()+handler.height) {
			shiftAmountY = -marioVelocityY;
		}
		handler.getCamera().moveCam(shiftAmount,shiftAmountY);
	}

	private void updateLuigiCamera() {
		Player luigi = handler.getLuigi();
		double luigiVelocityX = luigi.getVelX();
		double luigiVelocityY = luigi.getVelY();
		double shiftAmount = 0;
		double shiftAmountY = 0;

		if (luigiVelocityX > 0 && luigi.getX() - 2*(handler.getWidth()/3) > handler.getLuigiCamera().getX()) {
			shiftAmount = luigiVelocityX;
		}
		if (luigiVelocityX < 0 && luigi.getX() +  2*(handler.getWidth()/3) < handler.getLuigiCamera().getX()+handler.width) {
			shiftAmount = luigiVelocityX;
		}
		if (luigiVelocityY > 0 && luigi.getY() - 2*(handler.getHeight()/3) > handler.getLuigiCamera().getY()) {
			shiftAmountY = luigiVelocityY;
		}
		if (luigiVelocityX < 0 && luigi.getY() +  2*(handler.getHeight()/3) < handler.getLuigiCamera().getY()+handler.height) {
			shiftAmountY = -luigiVelocityY;
		}
		handler.getLuigiCamera().moveCam(shiftAmount,shiftAmountY);
	}

	private void render(){
		if(!Player_Selection.MultiPlayer) {
			bs = display.getCanvas().getBufferStrategy();

			if(bs == null){
				display.getCanvas().createBufferStrategy(3);
				return;
			}
			g = bs.getDrawGraphics();
			//Clear Screen
			g.clearRect(0, 0,  handler.width, handler.height);

			//Draw Here!
			Graphics2D g2 = (Graphics2D) g.create();

			if(State.getState() != null)
				State.getState().render(g);

			//End Drawing!
			bs.show();
			g.dispose();
		}
		else {
			bs = display.getCanvas().getBufferStrategy();
			bsluigi = luigi_display.getCanvas().getBufferStrategy();

			if(bs == null || bsluigi == null){
				display.getCanvas().createBufferStrategy(3);
				luigi_display.getCanvas().createBufferStrategy(3);
				return;
			}

			g = bs.getDrawGraphics();
			gluigi= bsluigi.getDrawGraphics();

			//Clear Screen
			gluigi.clearRect(0, 0,  handler.width, handler.height);
			g.clearRect(0, 0,  handler.width, handler.height);

			//Draw Here!
			Graphics2D luigig2 = (Graphics2D) gluigi.create();
			Graphics2D g2 = (Graphics2D) g.create();

			if(State.getState() != null) {
				State.getState().render(g);
				if(State.getState() instanceof GameState && Player_Selection.MultiPlayer) {
					handler.getMap().drawMap2(luigig2);

				}
			}
			if (State.getState() instanceof MenuState) {
				gluigi.drawImage(Images.luigiLoading, 0, 0, luigi_display.getCanvas().getWidth(), luigi_display.getCanvas().getHeight(), null);
			}



			//End Drawing!
			bs.show();
			g.dispose();
			bsluigi.show();
			gluigi.dispose();
		}
	}
	public Map getMap() {
		Map map = new Map(this.handler);
		Images.makeMap(0, MapBuilder.pixelMultiplier, 31, 200, map, this.handler);
		for(int i = 195; i < 200; i++) {
			map.addBlock(new BreakBlock(0, i*MapBuilder.pixelMultiplier, 48,48, this.handler));
			map.addBlock(new BreakBlock(30*MapBuilder.pixelMultiplier, i*MapBuilder.pixelMultiplier, 48,48, this.handler));
		}
		Mario mario = new Mario(24 * MapBuilder.pixelMultiplier, 196 * MapBuilder.pixelMultiplier, 48,48, this.handler);

		map.addEnemy(mario);

		map.addEnemy(pointer);
		threadB=true;
		return map;
	}

	public synchronized void stop(){
		if(!running)
			return;
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public KeyManager getKeyManager(){
		return keyManager;
	}

	public MusicHandler getMusicHandler() {
		return musicHandler;
	}


	public MouseManager getMouseManager(){
		return mouseManager;
	}

}

