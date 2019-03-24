package Display.UI;

import Game.Entities.DynamicEntities.BaseDynamicEntity;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;
import java.util.Random;

public class UIPointer extends BaseDynamicEntity {

    Animation idle,GB1,GB2,GB3,FG;
    boolean FlagGB1=false,FlagGB2=false,FlagGB3=false,FlagFG=false,FlagSmash=false,wasHit=false,died=false,attacking=false,start=true,movingToIdle =false,outOfCamera=true,smash=false;
    float HitR=0.0f,HitG=0.0f,HitB=0.0f;
    int health=3,attackCounter=0,startX,startY,idleCounter=0,squeeze=0;
    Rectangle wasHitBound = new Rectangle();
    Dimension wasHitDim = new Dimension();
    Dimension oldDim;
    int floorY = 200*48;


    public UIPointer(int x, int y, int width, int height, Handler handler) {
        super(x, y, width, height, handler, Images.enemy[0]);
        idle = new Animation(150, Images.enemy);
        GB1 = new Animation(450, Images.enemyGB1);
        GB2 = new Animation(500, Images.enemyGB2);
        GB3 = new Animation(450, Images.enemyGB3);
        FG = new Animation(200, Images.enemyFG);
        falling=false;
        oldDim = getDimension();
        direction="Left";
        startX=x;
        startY=y;
    }

    public void tick(){

        if(start) {
            if(idle.getIndex()>=7){
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
                if(!outOfCamera){
                    idle.tick();
                }else {
                    FG.tick();
                }
                FingerGun();
            } else if (FlagGB1) {
                GB1.tick();
                Grab();
            } else if (FlagGB2) {
                GB2.tick();
                Grab();
            } else if (FlagGB3) {
                GB3.tick();
                Grab();
            } else if (FlagSmash) {
                if(!outOfCamera){
                    idle.tick();
                }else {
                    return;
                }
            } else {
                idle.tick();
                Idle();
            }
        }else{
            if(x-handler.getMario().x<=150){
                start=true;
            }
        }
    }

    public void render(Graphics g){
        if(start) {
            if (wasHit) {
                g.drawImage(Images.tint(Images.enemyHT, Math.min(HitR += 0.016f, 1.0f), Math.min(HitR += 0.0139f, 0.839f), Math.min(HitR += 0.008, 0.482f)), x, y, width, height, null);
                setDimension(wasHitDim);

                if (HitR >= 1.0f && HitG >= 0.839f && HitB >= 0.482f) {
                    wasHit = false;
                    HitR = 0.0f;
                    HitG = 0.0f;
                    HitB = 0.0f;
                    health--;
                    setDimension(oldDim);

                }
            } else {
                if (!attacking) {
                    g.drawImage(idle.getCurrentFrame(), x, y, width, height, null);
                } else if (FlagFG) {
                    if(!outOfCamera){
                        g.drawImage(idle.getCurrentFrame(), x, y, width, height, null);
                    }else {
                        g.drawImage(FG.getCurrentFrame(), x, y, width, height, null);
                    }
                } else if (FlagGB1) {
                    g.drawImage(GB1.getCurrentFrame(), x, y, width, height, null);
                } else if (FlagGB2) {
                    g.drawImage(GB2.getCurrentFrame(), x, y, width, height, null);
                } else if (FlagGB3) {
                    g.drawImage(GB3.getCurrentFrame(), x, y, width, height, null);
                } else if (FlagSmash) {
                    Smash(g);
                }else{
                    g.drawImage(idle.getCurrentFrame(), x, y, width, height, null);
                }
            }
        }
    }

    @Override
    public void move(){
        createDistance("Center");
    }

    private void attack() {

        outOfCamera=false;
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
                return;
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
            if(idleCounter>=300){
                attacking=false;
                outOfCamera=false;
                idleCounter=0;
            }
        }

    }

    private void FingerGun() {

        createDistance("Center");
        if(outOfCamera){
            if(FG.getIndex()>=8){
                attacking = false;
                outOfCamera=false;
                FlagFG=false;
            }
        }



    }

    private void Grab() {
        if(GB1.getIndex()>=2 && FlagGB1){
            FlagGB1=false;
            FlagGB2=true;
        }else if(GB2.getIndex()>=1 && FlagGB2){
            squeeze++;
            if(squeeze>3) {
                FlagGB2 = false;
                FlagGB3 = true;
            }
        }
        else if(GB3.getIndex()>=2 && FlagGB3){
            FlagGB3=false;
            attacking=false;
            outOfCamera=false;
        }

    }

    private void Smash(Graphics g) {
        if(!outOfCamera){
            g.drawImage(idle.getCurrentFrame(), x, y, width, height, null);
            createDistance("Top");
        }else {
            g.drawImage(Images.enemySmash, x, y, width, height, null);
            if(y+height<floorY && !smash){
                y+=22;
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

    private void createDistance(String dirrection) {
        if(dirrection.equals("Top")){
            int distanceX = this.x-handler.getMario().x;

            if(distanceX>4){
                x-=4;
            }else if(distanceX<4){
                x+=4;
            }
            y-=4;
            if(y+height<=(handler.getMario().y-handler.getHeight())){
                outOfCamera=true;
                System.out.println("on Top");
            }
        }else if(dirrection.equals("Right")){
            int distanceY = this.y-handler.getMario().y;

            if(distanceY>2){
                y-=4;
            }else if(distanceY<2){
                y+=4;
            }
            x+=4;
            if(x>=(handler.getWidth()+handler.getMario().x)){
                outOfCamera=true;
            }
        }
        else{
            int distanceX = this.x-handler.getMario().x;
            int distanceY = handler.getMario().y-this.y;
            int speed = 4;
            if(handler.getKeyManager().runbutt){speed=6;}
            int speedY = Math.abs((int) handler.getMario().getVelY());

            if(distanceX>174){
                x-=speed;
            }else if(distanceX<152){
                x+=speed;
            }
            if(distanceY>100){
                y+=speedY;
            }else if(distanceY<75){
                y-=speedY;
            }
            if(distanceX<=174 && distanceX>=152&&distanceY<=100 &&distanceY>=75){
                outOfCamera=true;
            }
        }
    }




}
