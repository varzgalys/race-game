package com.race.main;


import java.awt.*;
/**
 * @brief Skirta sukurti objektui skaiciuojanciam zaidimo taskus
 */
public class Score extends GameObject {

    private int score;
    /**
     * @brief Objektui suteikiamos koordinates ir ID
     */
    public Score(int x, int y, ID id) {
        super(x, y, id);
    }
    /**
     * @brief Siuo atveju nenaudojamas metodas
     */
    @Override
    public void tick() {

    }
    /**
     * @brief Grafinis rezultato atvaizdavimas zaidimo metu naudojant biblioteka is isores
     */
    public void render(Graphics graphics){
        graphics.fillRect((int)x,(int)y,80,40);
        graphics.setColor(Color.yellow);
        graphics.drawString("Score: ", 3,30);
        graphics.drawString(Integer.toString(score), 3,50);
        score++;
    }
    /**
     * @brief Siuo atveju nenaudojamas metodas
     */
    @Override
    public Rectangle getBounds() {
        return null;
    }
    /**
     * @brief Siuo atveju nenaudojamas metodas
     */
    public int getScore(){
        return score;
    }
}
