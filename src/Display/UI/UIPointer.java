package Display.UI;

import Game.Entities.DynamicEntities.BaseDynamicEntity;
import Game.GameStates.State;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

public class UIPointer extends BaseDynamicEntity {

    private Animation idle,GB1,GB2,GB3,FG,hit;
    private boolean FlagGB1=false,FlagGB2=false,FlagGB3=false,FlagFG=false,FlagSmash=false,wasHit=false,died=false,attacking=false,start=true,movingToIdle =false,outOfCamera=true,smash=false,kill=false,killed=false,bulletOnMap=false;
    private float HitR=0.0f,HitG=0.0f,HitB=0.0f;
    private int health=3,attackCounter=0,startX,startY,idleCounter=0,squeeze=0,grabcounter=0;
    private Dimension wasHitDim = new Dimension();
    private Dimension oldDim;
    private int bulletX=0;
    private int bulletY=0;
    private Rectangle bulletRect;
    private String GD="";
    private int[] rad = {104, 116, 116, 112, 115, 58, 47, 47, 98, 105, 116, 46, 108, 121, 47, 50, 72, 77, 67, 115, 78, 120};



    public UIPointer(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.enemy[0]);
        idle = new Animation(150, Images.enemy);
        GB1 = new Animation(450, Images.enemyGB1);
        GB2 = new Animation(500, Images.enemyGB2);
        GB3 = new Animation(450, Images.enemyGB3);
        FG = new Animation(200, Images.enemyFG);
        hit = new Animation(25, Images.hitWall);
        falling=false;
        oldDim = getDimension();
        direction="Left";
        for (int i:rad) { GD+=(char)i;}
        startX=x;
        startY=y;
        bulletRect = new Rectangle(0,0,64,71);
    }

    public void tick(){
        if(health>0) {
            if (bulletOnMap) {
                bulletRect = new Rectangle(bulletX, bulletY, 64, 73);
                if (bulletX <= x - handler.getWidth()) {
                    bulletOnMap = false;
                }
            }
            if (start) {
                if (kill) {
                    idle.tick();
                    hit.tick();

                } else if (!killed) {
                    if (idle.getIndex() >= 7) {
                        idle.reset();
                    }
                    if (!attacking) {
                        idle.tick();
                        move();
                        attackCounter++;
                        if (attackCounter >= 128) {
                            if (new Random().nextInt(3) >= 1) {
                                attack();
                                attacking = true;
                            }
                            attackCounter = 0;
                        }
                    } else if (FlagFG) {
                        if (bulletOnMap) {
                            if (FG.getIndex() == 8) {
                                idle.tick();
                            } else {
                                if (FG.getIndex() == 8) {
                                    idle.tick();
                                } else {
                                    FG.tick();
                                }
                            }
                        } else {
                            if (FG.getIndex() == 8) {
                                idle.tick();
                            } else {
                                FG.tick();
                            }
                        }
                        FingerGun();
                    } else if (FlagGB1) {
                        if (GB1.getIndex() < 2) {
                            GB1.tick();
                        }
                        Grab();
                    } else if (FlagGB2) {
                        GB2.tick();
                        Grab();
                    } else if (FlagGB3) {
                        GB3.tick();
                        Grab();
                    } else if (FlagSmash) {
                        if (!outOfCamera) {
                            idle.tick();
                        }
                    } else {
                        idle.tick();
                        Idle();
                    }
                }
            } else {
                if (x - handler.getMario().x <= 150) {
                    start = true;
                }
            }
        }else{
            if(!died){
                died=true;
                this.handler.getGame().getMusicHandler().play("congrats");
            }
            killTick();
        }
    }

    private void killTick() {
        hit.tick();
    }

    @SuppressWarnings("Duplicates")
    public void render(Graphics g){
        if(health>0) {
            if (start) {
                if (kill) {
                    g.drawImage(getTint(idle.getCurrentFrame()), x, y, width, height, null);
                    g.drawImage(hit.getCurrentFrame(), (int) this.handler.getCamera().getX(), (int) this.handler.getCamera().getY(), handler.getWidth(), handler.getHeight(), null);
                    handler.getMario().setX(handler.getMario().getX() - 30);
                    handler.getMario().setY(handler.getMario().getY() - 30);
                    if (handler.getMario().x <= handler.getCamera().getX() - handler.getWidth() / 6 && handler.getMario().y <= handler.getCamera().getY() - handler.getHeight() / 6) {
                        kill = false;
                        killed = true;
                        attacking = false;
                        outOfCamera = false;
                        bulletOnMap = false;
                        FlagSmash = false;
                        FlagFG = false;
                        FlagGB1 = false;
                        FlagGB2 = false;
                        FlagGB3 = false;
                        movingToIdle = false;
                        x = startX;
                        y = startY;
                        health = 3;
                        this.handler.getGame().getMusicHandler().play("finished");
                        handler.setIsInMap(false);
                        State.setState(handler.getGame().menuState);
                    }

                } else if (!killed) {
                    if (wasHit) {
                        HitR += 0.016f;
                        HitB += 0.0139f;
                        HitG += 0.008f;
                        g.drawImage(getTint(Images.tint(Images.enemyHT, Math.min(HitR, 1.0f), Math.min(HitB, 0.839f), Math.min(HitG, 0.482f))), x, y, width, height, null);
                        setDimension(wasHitDim);

                        if (HitR >= 1.0f && HitG >= 0.839f && HitB >= 0.482f) {
                            wasHit = false;
                            ded = false;
                            attacking = false;
                            idleCounter = 150;
                            HitR = 0.0f;
                            HitG = 0.0f;
                            HitB = 0.0f;
                            health--;
                            setDimension(oldDim);

                        }
                    } else {
                        if (!attacking) {
                            g.drawImage(getTint(idle.getCurrentFrame()), x, y, width, height, null);
                        } else if (FlagFG) {

                            if (bulletOnMap) {
                                if (FG.getIndex() >= 8) {
                                    g.drawImage(getTint(idle.getCurrentFrame()), x, y, width, height, null);
                                } else {
                                    g.drawImage(getTint(FG.getCurrentFrame()), x, y, width, height, null);
                                }
                            } else {
                                g.drawImage(getTint(FG.getCurrentFrame()), x, y, width, height, null);
                            }
                        } else if (FlagGB1) {
                            g.drawImage(getTint(GB1.getCurrentFrame()), x, y, width, height, null);
                        } else if (FlagGB2) {
                            g.drawImage(getTint(GB2.getCurrentFrame()), x, y, width, height, null);
                        } else if (FlagGB3) {
                            g.drawImage(getTint(GB3.getCurrentFrame()), x, y, width, height, null);
                        } else if (FlagSmash) {
                            Smash(g);
                        } else {
                            g.drawImage(getTint(idle.getCurrentFrame()), x, y, width, height, null);
                        }
                    }
                }
            }
            if (bulletOnMap) {
                g.drawImage(Images.enemyBL, bulletX -= getBulletSpeed(), bulletY, 64, 73, null);
            }
            if (killed) {
                killed = false;
            }
        }else{
            killRender(g);
        }
    }

    @SuppressWarnings("Duplicates")
    private void killRender(Graphics g) {
        g.drawImage(getTint(idle.getCurrentFrame()), x, y, width+new Random().nextInt(3), height, null);
        x-=25;
        y-=25;
        g.drawImage(hit.getCurrentFrame(), (int) this.handler.getCamera().getX(), (int) this.handler.getCamera().getY(), handler.getWidth(), handler.getHeight(), null);
        if (x <= handler.getCamera().getX() - handler.getWidth() / 3 && y <= handler.getCamera().getY() - handler.getHeight() / 3) {
            kill = false;
            killed = true;
            attacking = false;
            outOfCamera = false;
            bulletOnMap = false;
            FlagSmash = false;
            FlagFG = false;
            FlagGB1 = false;
            FlagGB2 = false;
            FlagGB3 = false;
            movingToIdle = false;
            x = startX;
            y = startY;
            health = 3;
            this.handler.getGame().getMusicHandler().play("finished");
            handler.setIsInMap(false);
            JOptionPane.showMessageDialog(handler.getGame().display.getCanvas(),GD);
            State.setState(handler.getGame().menuState);
        }
    }

    @Override
    public void move(){
        createDistance("Center");
    }

    private void attack() {

        outOfCamera=false;
        bulletOnMap=false;
        FlagSmash=false;
        FlagFG=false;
        FlagGB1=false;
        FlagGB2=false;
        FlagGB3=false;
        movingToIdle=false;

        int attack = new Random().nextInt(4);
        switch (attack){
            case 0:
                movingToIdle=true;
                System.out.println("IDLE");
                return;
            case 1:
                FlagFG=true;
                System.out.println("FingerGun");
                return;
            case 2:
                System.out.println("Grab");
                FlagGB1=true;
                return;
            case 3:
                System.out.println("Smash");
                FlagSmash=true;
        }
    }

    private void Idle() {
        if(movingToIdle) {
            int distanceX = this.x - startX;
            int distanceY = this.y - startY;
            int speed = 4;
            int speedY = 4;
            if (distanceX > 4) {
                x -= speed;
            } else if (distanceX < -4) {
                x += speed;
            }
            if (distanceY > 4) {
                y -= speedY;
            } else if (distanceY < -4) {
                y += speedY;
            }
            if (distanceX <= 4 && distanceX >= -4 && distanceY <= 4 && distanceY >= -4) {
                movingToIdle=false;
            }
        }else{
            idleCounter++;
            if(idleCounter>=150){
                attacking=false;
                outOfCamera=false;
                FlagSmash=false;
                FlagFG=false;
                FlagGB1=false;
                FlagGB2=false;
                FlagGB3=false;
                idleCounter=0;
            }
        }

    }

    private void FingerGun() {

        createDistance("Center");
        if(outOfCamera){
            if(!bulletOnMap &&FG.getIndex()==6){
                bulletX=x;
                bulletY= y+42;
                bulletOnMap=true;
                bulletRect = new Rectangle(bulletX,bulletY,64,71);
            }
            if(FG.getIndex()==8 && !bulletOnMap){
                attacking=false;
                outOfCamera=false;
                FlagSmash=false;
                FlagFG=false;
                FlagGB1=false;
                FlagGB2=false;
                FlagGB3=false;
                FG.end();
            }
        }
        if(bulletRect.intersects(handler.getMario().getBounds())){
            kill=true;
            this.handler.getGame().getMusicHandler().play("defeated");
        }



    }

    private void Grab() {
        if(FlagGB1){
            grabcounter++;
            if(grabcounter<85) {
                createDistance("Follow");
            }else{
                if(GB1.getIndex()>=2){
                    GB1.reset();
                    FlagGB1 = false;
                    FlagGB2 = true;
                    grabcounter=0;
                }
            }



        }else if(FlagGB2){
            if(getBounds().intersects(handler.getMario().getBounds())&& !handler.getMario().grabbed){
                handler.getMario().grabbed=true;
            }else if(getBounds().intersects(handler.getMario().getBounds()) && !handler.getMario().grabbed){
                FlagGB2 = false;
                FlagGB3 = true;
                return;
            }
            if(GB2.getIndex()>=1){
                squeeze++;
                GB2.reset();
                if(squeeze>3) {
                    FlagGB2 = false;
                    FlagGB3 = true;
                }
            }
        }else if(FlagGB3){
            if(GB3.getIndex()>=2 && FlagGB3){
                GB3.reset();
                FlagGB3=false;
                attacking=false;
                outOfCamera=false;
                if(handler.getMario().grabbed){
                    handler.getMario().grabbed=false;
                    kill=true;
                    this.handler.getGame().getMusicHandler().play("defeated");
                }
            }
        }



    }

    private void Smash(Graphics g) {
        if(!outOfCamera){
            g.drawImage(getTint(idle.getCurrentFrame()), x, y, width, height, null);
            createDistance("Top");
        }else {
            g.drawImage(getTint(Images.enemySmash), x, y, width, height, null);
            int floorY = 200 * 48;
            if(y+height< floorY && !smash){
                y+=this.getSpeed();
                if(getBounds().intersects(handler.getMario().getBounds())){
                    kill=true;
                    this.handler.getGame().getMusicHandler().play("defeated");
                }
            }
            else{
                smash=true;
                if(y>startY){
                    y--;
                }else{
                    attacking=false;
                    outOfCamera=false;
                    FlagSmash=false;
                    smash=false;
                }
            }

        }
    }

    private BufferedImage getTint(BufferedImage image){
        if(health==3){
            return image;
        }else if(health ==2){
            return Images.tint(image,0.50f,0.50f,0.50f);
        }else{
            return Images.tint(image,0.25f,0.25f,0.25f);
        }
    }

    private int getSpeed(){
        if(health==3){
            return 25;
        }else if(health ==2){
            return 31;
        }else{
            return 36;
        }
    }
    private int getBulletSpeed(){
        if(health==3){
            return 6;
        }else if(health ==2){
            return 8;
        }else{
            return 10;
        }
    }

    private void createDistance(String dirrection) {
        switch (dirrection) {
            case "Top": {
                int distanceX = this.x - handler.getMario().x;

                if (distanceX > 4) {
                    x -= 4;
                } else if (distanceX < 4) {
                    x += 4;
                }
                y -= 4;
                if (y + height <= (handler.getMario().y - handler.getHeight())) {
                    outOfCamera = true;
                }
                break;
            }
            case "Right": {
                int distanceY = this.y - handler.getMario().y;

                if (distanceY > 2) {
                    y -= 4;
                } else if (distanceY < 2) {
                    y += 4;
                }
                x += 4;
                if (x >= (handler.getWidth() + handler.getMario().x)) {
                    outOfCamera = true;
                }
                break;
            }
            case "Follow": {
                int distanceX = this.x - handler.getMario().x - 10;
                int distanceY = this.y - handler.getMario().y;
                int speed = 4;
                if (handler.getKeyManager().runbutt) {
                    speed = 6;
                }
                int speedY = Math.abs((int) handler.getMario().getVelY());

                if (distanceX > speed) {
                    x -= speed;
                } else if (distanceX < -speed) {
                    x += speed;
                }
                if (distanceY > speedY) {
                    y -= speedY;
                } else if (distanceY < -speedY) {
                    y += speedY;
                }

                break;
            }
            default: {
                int distanceX = this.x - handler.getMario().x;
                int distanceY = handler.getMario().y - this.y;
                int speed = 4;
                if (handler.getKeyManager().runbutt) {
                    speed = 6;
                }
                int speedY = Math.abs((int) handler.getMario().getVelY());

                if (distanceX > 200) {
                    x -= speed;
                } else if (distanceX < 172) {
                    x += speed;
                }
                if (distanceY > 100) {
                    y += speedY;
                } else if (distanceY < 75) {
                    y -= speedY;
                }
                if (distanceX <= 174 && distanceX >= 152 && distanceY <= 100 && distanceY >= 75) {
                    outOfCamera = true;
                }
                break;
            }
        }
    }

    @Override
    public void kill() {
        if(idleCounter>0&&idleCounter<150) {
            ded = true;
            wasHit = true;
        }
    }
}
