package com.race.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @brief Kuriami kitu automobiliu (kliuciu kuriu reikia vengti) objektai
 */
public class AISight extends GameObject {

    private Image image;
    private Handler handler;
    private boolean intercepts;
    private EnemyCar enemyCar;
    private int x_range;
    private int y_range;


    /**
     * @brief Valdomam objektui priskiriamos pradines koordinates, ID. Uzklausiama paveikslelio is isores
     */
    public AISight(int x, int y, ID id, Handler handler, EnemyCar enemyCar, int x_range, int y_range) {
        super(x, y, id);
        this.handler = handler;
        this.x_range = x_range;
        this.y_range = y_range;
        this.enemyCar = enemyCar;

        try {
            this.image = ImageIO.read(new File("src/EnemyCar.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }


        velY = (x / 60); //speed
    }

    /**
     * @brief Objektui suteikiamas kolisijos zonos dydis ir koordinates
     */
    public Rectangle getBounds() {
        return new Rectangle((int)x,(int)y, 30, 40);
    }


    /**
     * @brief Keiciama objekto pozicija, priskiriamas atsitiktis koordinaciu kitimas
     */
    @Override
    public void tick() {

        x = enemyCar.getX() + x_range;
        y = enemyCar.getY() + y_range;


        collision();
    }

    private void collision() {
        intercepts = false;


        for (int i = 0; i < handler.gameObjects.size(); i++)
        {
            GameObject tempObject = handler.gameObjects.get(i);
            if (tempObject.getId() == ID.ObstacleCar)
            {
//                if (getBounds().intersects(tempObject.getBounds()))
//                {
//                    intercepts = true;
//                }
                if (tempObject.getBounds().intersects(this.getBounds())) //this.getBounds
                {
                    intercepts = true;
                }

            }
        }

    }
    private void collision2()
    {
        intercepts = false;
        for (int i = 0; i < handler.gameObjects.size(); i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            if (tempObject.getId() == ID.ObstacleCar)
            {


            }
        }
    }

    public boolean getIntercepts(){
        return intercepts;
    }

    /**
     * @brief Prisikiriamas ir atnaujinamas objekto grafinis atvaizdavimas naudojant biblioteka is isores
     */
    @Override
    public void render(Graphics graphics)
    {
//        graphics.drawImage(image, (int)x, (int)y, null);
        graphics.drawRect((int)x,(int)y,30,40);
    }
}

