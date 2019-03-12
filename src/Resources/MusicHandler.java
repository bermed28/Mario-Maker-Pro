package Resources;


import Main.Handler;

import javax.sound.sampled.*;
import java.io.File;

public class MusicHandler {

    //Res.music
    private File audioFile;
    private AudioInputStream audioStream;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip audioClip;


    //Inspired By ahmetcandiroglu' Music Handler

    private Handler handler;
    private Clip background;
    private long clipTime = 0;

    public MusicHandler(Handler handler){
        this.handler = handler;
        background = getClip(loadAudio("background"));
    }

    private AudioInputStream loadAudio(String url) {
        try {
            audioFile = new File("res/music/" + url + ".wav");
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            format = audioStream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);


            return audioStream;

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        return null;
    }

    private Clip getClip(AudioInputStream stream) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(stream);
            return clip;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void resumeBackground(){
        background.setMicrosecondPosition(clipTime);
        background.start();
    }

    public void pauseBackground(){
        clipTime = background.getMicrosecondPosition();
        background.stop();
    }

    public void restartBackground() {
        clipTime = 0;
        resumeBackground();
    }

    ///Example to play a special effect
    public void playJump() {
        Clip clip = getClip(loadAudio("jump"));
        clip.start();
    }





}
