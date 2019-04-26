package Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * Created by AlexVR on 7/1/2018.
 */

public class KeyManager implements KeyListener {

	private boolean[] keys,justPressed,cantPress;
	public boolean up_mario=false, down_mario=false, left_mario=false, right_mario=false,up_luigi=false, down_luigi=false, left_luigi=false, right_luigi=false,pbutt=false,runbutt=false, ebutt=false,luigiRun = false;
	


	public KeyManager(){

		keys = new boolean[256];
		justPressed = new boolean[keys.length];
		cantPress = new boolean[keys.length];

	}

	public void tick(){
		for(int i =0; i < keys.length;i++){
			if(cantPress[i] && !keys[i]){
				cantPress[i]=false;

			}else if(justPressed[i]){
				cantPress[i]=true;
				justPressed[i] =false;
			}
			if(!cantPress[i] && keys[i]){
				justPressed[i]=true;
			}
		}

		up_mario= keys[KeyEvent.VK_W];
		down_mario = keys[KeyEvent.VK_S];  
		left_mario = keys[KeyEvent.VK_A];  
		right_mario = keys[KeyEvent.VK_D];  


		up_luigi = keys[KeyEvent.VK_UP];
		down_luigi = keys[KeyEvent.VK_DOWN];
		left_luigi = keys[KeyEvent.VK_LEFT];
		right_luigi = keys[KeyEvent.VK_RIGHT];
		luigiRun  = keys[KeyEvent.VK_SLASH];




		pbutt = keys[KeyEvent.VK_ESCAPE];
		runbutt = keys[KeyEvent.VK_SHIFT];
		ebutt = keys[KeyEvent.VK_E];

	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = true;
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() < 0 || e.getKeyCode() >= keys.length)
			return;
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public boolean keyJustPressed(int keyCode){
		if(keyCode < 0 || keyCode >= keys.length)
			return false;
		return justPressed[keyCode];
	}

}
