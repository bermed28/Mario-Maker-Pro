package Game.GameStates;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Display.UI.UIPointer;
import Game.Entities.DynamicEntities.BaseDynamicEntity;
import Game.Entities.DynamicEntities.Coin;
import Game.World.MapBuilder;
import Main.Handler;

/**
 * Created by AlexVR on 7/1/2018.
 */
public class GameState extends State {


	public GameState(Handler handler){
		super(handler);
		handler.getGame().pointer = new UIPointer(28 * MapBuilder.pixelMultiplier,197 * MapBuilder.pixelMultiplier,128,128,handler);


	}

	@Override
	public void tick() {
		
		if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_ESCAPE)){
			handler.getGame().getMusicHandler().pauseBackground();
			State.setState(handler.getGame().pauseState);
		}
		

		if(Player_Selection.MultiPlayer) {
			if((handler.getMario().getHit() && !(handler.getMario().isBig))){
				WinState.luigiWon = true;
				State.setState(handler.getGame().winState);
			}
			if(handler.getLuigi().getHit() && !(handler.getLuigi().isBig)){
				WinState.marioWon = true;
				State.setState(handler.getGame().winState);
			}
		}

		handler.getMario().tick();
		if(Player_Selection.MultiPlayer) {
			handler.getLuigi().tick();
		}
		if(handler.getMap().getListener() != null && MapBuilder.mapDone) {
			handler.getMap().getListener().tick();
			handler.getMap().getHand().tick();
			handler.getMap().getWalls().tick();
		}
		for (BaseDynamicEntity entity:handler.getMap().getEnemiesOnMap()) {
			entity.tick();
			if(entity instanceof Coin) {
				if(entity.getBottomBounds().intersects(handler.getMario().getTopBounds()) || 
						entity.getLeftBounds().intersects(handler.getMario().getRightBounds()) || 
						entity.getRightBounds().intersects(handler.getMario().getLeftBounds())|| 
						entity.getTopBounds().intersects(handler.getMario().getBottomBounds())){
					entity.x = -10000;
					handler.getGame().getMusicHandler().playCoin();

				}
			}
			
		
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2 = (Graphics2D) g.create();
		handler.getMap().drawMap(g2);
	}

}
