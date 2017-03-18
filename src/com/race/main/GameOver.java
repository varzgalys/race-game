package com.race.main;

import java.awt.*;

/**
 * @brief Pralaimejimo atveju (kolisijos) pasiektu tasku atvaizdavimo objekto kurimas
 */
public class GameOver extends GameObject {
    int score;

    public GameOver(int x, int y, ID id) {
        super(x, y, id);
    }

    /**
     * @brief Siuo atveju nenaudojama klase
     */
    @Override
    public void tick() {
    }

    /**
     * @brief Nustatomas rezultatas atsiustas is aukstesnes klases
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @brief Grafinis lenteles ir rezultato atvaizdavimas naudojant biblioteka is isores
     */
    @Override
    public void render(Graphics graphics) {
        graphics.fillRect((int) x, (int) y, 400, 200);
        graphics.setColor(Color.YELLOW);
        graphics.drawString("Game over! Your score: " + score, (int) x + 100, (int) y + 100);
        graphics.setColor(Color.yellow);
    }

    /**
     * @brief Siuo atveju nenaudojama klase
     */
    @Override
    public Rectangle getBounds() {
        return null;
    }
}
