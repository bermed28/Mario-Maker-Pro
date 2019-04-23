package Game.GameStates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Display.UI.UIManager;
import Display.UI.UIStringButton;
import Main.Handler;
import Resources.Images;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class WinState extends State {

	private UIManager uiManager;
	public static boolean luigiWon = false;
	public static boolean marioWon = false;
	public WinState(Handler handler) {
		super(handler);
		uiManager = new UIManager(handler);
		handler.getMouseManager().setUimanager(uiManager);
	
		uiManager.addObjects(new UIStringButton(308, 330, 128, 64, "Quit", () -> {
			handler.getMouseManager().setUimanager(null);
			handler.setIsInMap(false);
			System.exit(0);
		},handler,Color.WHITE));

	}

	@Override
	public void tick() {
		handler.getMouseManager().setUimanager(uiManager);
		uiManager.tick();

	}

	@Override
	public void render(Graphics g) {
		if(handler.getMario().hit && !(handler.getMario().isBig)){
			luigiWon = true;
			g.drawImage(Images.luigiWinState,-20,0,handler.getWidth()+20,handler.getHeight(),null);
		}
		else if(handler.getLuigi().hit && !(handler.getLuigi().isBig)){
			marioWon = true;
			g.drawImage(Images.marioWinState,-20,0,handler.getWidth()+20,handler.getHeight(),null);
		}
		uiManager.Render(g);
	}
}
