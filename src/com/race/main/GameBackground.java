package com.race.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @brief Judincio fono objektas
 */
public class GameBackground extends GameObject{

    private Image image;
    /**
     * @brief Fonui suteikiamos koordinates, id. Uzprasomas grafinis paveikslelis is isores
     */
    public GameBackground(int x, int y, ID id) {
        super(x, y, id);
        try {
            image = ImageIO.read(new File("src/background.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * @brief Nustatomas fono koordinaciu kitimas (greitis ir kriptis)
     */
    @Override
    public void tick() {
        y = y + 5;
        if(y >= 0){
            y = -1400;
        }
    }
    /**
     * @brief Anksciau gautas paveiksleis nustatomas kaip fono grafinis vaizdas naudojant biblioteka is isores
     */
    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(image,(int)x,(int)y,null);

    }
    /**
     * @brief Siuo atveju nenaudojamas metodas
     */
    @Override
    public Rectangle getBounds() {
        return null;
    }
}
