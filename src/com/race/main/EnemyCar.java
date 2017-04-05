package com.race.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @brief Kuriami kitu automobiliu (kliuciu kuriu reikia vengti) objektai
 */
public class EnemyCar extends GameObject {

    private Image image;
    private Handler handler;
    private GameObject player;

    private AISight aiRightSight;
    private AISight aiLeftSight;
    private AISight aiFrontSight;
    private AISight aiBackSight;
    private AISight aiBackRightSight;
    private AISight aiBackLeftSight;
    private AISight aiFrontRightSight;
    private AISight aiFrontLeftSight;




    /**
     * @brief Valdomam objektui priskiriamos pradines koordinates, ID. Uzklausiama paveikslelio is isores
     */
    public EnemyCar(int x, int y, ID id, Player player, Handler handler) {
        super(x, y, id);
        this.player = player;
        this.handler = handler;
        velY = (x / 60); //speed
        try {
            image = ImageIO.read(new File("src/EnemyCar.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        aiFrontSight = new AISight(x, y, ID.AISight, handler, this, 0, -60);
        aiBackSight = new AISight(x, y, ID.AISight, handler, this, 0, 60);
        aiRightSight = new AISight(x, y, ID.AISight, handler, this, -35, 0);
        aiLeftSight = new AISight(x, y, ID.AISight, handler, this,35 , 0);
        aiFrontRightSight = new AISight(x, y, ID.AISight, handler, this, 35, -60);
        aiFrontLeftSight = new AISight(x, y, ID.AISight, handler, this, -35, -60);

        aiBackRightSight = new AISight(x, y, ID.AISight, handler, this, 35, 60);
        aiBackLeftSight = new AISight(x, y, ID.AISight, handler, this, -35, 60);

        handler.addObject(aiFrontSight);
        handler.addObject(aiRightSight);
        handler.addObject(aiLeftSight);
        handler.addObject(aiBackSight);
        handler.addObject(aiBackRightSight);
        handler.addObject(aiBackLeftSight);
        handler.addObject(aiFrontRightSight);
        handler.addObject(aiFrontLeftSight);
}

    /**
     * @brief Objektui suteikiamas kolisijos zonos dydis ir koordinates
     */
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y, 35, 60);
    }


    /**
     * @brief Keiciama objekto pozicija, priskiriamas atsitiktis koordinaciu kitimas
     */
    @Override
    public void tick() {

        x = x + velX;
        y = y + velY;

        boolean right = aiRightSight.getIntercepts();
        boolean left = aiLeftSight.getIntercepts();
        boolean front = aiFrontSight.getIntercepts();
        boolean frontLeft = aiFrontLeftSight.getIntercepts();
        boolean frontRight = aiFrontRightSight.getIntercepts();

//        boolean back = aiBackSight.getIntercepts();
//        boolean backLeft = aiBackLeftSight.getIntercepts();
//        boolean backRight = aiBackLeftSight.getIntercepts();
        float speed = 0.5f;
        float breakSpeed = 0.6f;

        if(front)
        {
            velY+=breakSpeed;
        }
        else if(right && !(left))
        {
            velX+=speed;
        }
        else if(left && !(right))
        {
            velX-=speed;
        }

        else if(frontRight)
        {
            velX-=speed;
            System.out.println("PK");
        }
        else if (frontLeft)
        {
            velX+=speed;
            System.out.println("PD");
        }

        else
        {
            chasePlayer();
        }

        collision();
    }

    private void collision() {
        for (int i = 0; i < handler.gameObjects.size(); i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            if (tempObject.getId() == ID.ObstacleCar) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(this);
                }
            }
        }
    }
    private void chasePlayer()
    {
        int speed = 1;
        float playerX = player.getX();
        float playerY = player.getY();

        float dir_x = playerX - x;
        float dir_y = playerY - y;

        float hyp = (float) Math.sqrt(dir_x*dir_x + dir_y*dir_y);

        dir_x /= hyp;
        dir_y /= hyp;

        velX = dir_x*speed;
        velY = dir_y*speed;
    }

    /**
     * @brief Prisikiriamas ir atnaujinamas objekto grafinis atvaizdavimas naudojant biblioteka is isores
     */
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(image, (int)x, (int)y, null);
    }
}
