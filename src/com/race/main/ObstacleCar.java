package com.race.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

/**
 * @brief Kuriami kitu automobiliu (kliuciu kuriu reikia vengti) objektai
 */
public class ObstacleCar extends GameObject {

    Random random;
    private Image image;

    /**
     * @brief Valdomam objektui priskiriamos pradines koordinates, ID. Uzklausiama paveikslelio is isores
     */
    public ObstacleCar(int x, int y, ID id) {
        super(x, y, id);
        random = new Random();
        velY = (x / 60); //speed
        try {
            image = ImageIO.read(new File("src/NpcCar.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @brief Objektui suteikiamas kolisijos zonos dydis ir koordinates
     */
    public Rectangle getBounds() {
        return new Rectangle((int) x, (int) y, 35, 60);
    }


    /**
     * @brief Keiciama objekto pozicija, priskiriamas atsitiktis koordinaciu kitimas
     */
    @Override
    public void tick() {
        x = x + velX;
        y = y + velY;
        if (y >= 650) {
            random = new Random();
            int random4 = random.nextInt(4);
            x = (random4 + 1) * 60 + 42;
            y = random.nextInt(2000) - 2000;
            velY = random4 + 1; //speed
        }
    }

    /**
     * @brief Prisikiriamas ir atnaujinamas objekto grafinis atvaizdavimas naudojant biblioteka is isores
     */
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(image, (int) x, (int) y, null);
    }
}
