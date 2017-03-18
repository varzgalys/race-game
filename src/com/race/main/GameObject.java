package com.race.main;

import java.awt.*;
/**
 * @brief Sablonas visoms kitoms zaidimo objektu klasems kurti kaip Player, ObstacleCar,GameOver,Score,GameBackground
 */
public abstract class GameObject {

    protected float x, y;
    protected ID id;
    protected  float velX, velY;
    /**
     * @brief Zaidimo obejktams suteikiamos kordinates ir ID
     */
    public  GameObject(float x, float y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    /**
     * @brief Metodas skirtas keisti objektu koordinatems (greiciui ir krypciai)
     */
    public abstract void tick();
    /**
     * @brief  Metodas skirtas sukurti ir atnaujinti objektu grafini vaizda
     */
    public abstract  void render(Graphics graphics);
    /**
     * @brief Metodas skirtas suteikti objektui kolisijos zona naudojant biblioteka is isores
     */
    public abstract Rectangle getBounds();
    /**
     * @brief Metodas skirtas gauti sukuro zaidimo objekto ID
     */
    public ID getId(){
        return id;
    }
    /**
     * @brief Metodas nustatyti x asies kitima (greiti)
     */
    public void setVelX(int velX){
        this.velX = velX;
    }
    /**
     * @brief Metodas nustatyti y asies kitima (greiti)
     */
    public void setVelY(int velY){
        this.velY = velY;
    }

    public float getX() {return this.x;}

    public float getY() {return this.y;}
}
