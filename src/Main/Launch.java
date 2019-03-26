package Main;


/**
 * Created by AlexVR on 7/1/2018.
 */

public class Launch {

    public static void main(String[] args) {
        Handler handler = new Handler();
        GameSetUp game = new GameSetUp("Mario Maker Pro TM. (CR Nintendo)",handler);
        handler.setGame(game);
        game.start();
    }
}
