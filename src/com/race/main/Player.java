package com.race.main;


import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * @brief Kuriamas zaidejo objektas, kuri mes valdome
 */
public class Player extends GameObject {

    Handler handler;
    private Image image;
    private Score score;

    /**
     * @brief Valdomam objektui priskiriamos pradines koordinates, ID, handler objektas. Sukuriamas naujas tasku skaiciavimo objektas. Uzklausiama paveikslelio
     */
    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        score = new Score(0, 15, ID.Score);
        this.handler = handler;
        try {
            image = ImageIO.read(new File("src/Player.png"));
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
     * @brief Tikrinamos visos zaidimo objektu koliziju zonu koordinates, tikrinama ar nesutampa, jei sutampa naikinamas zaidejo objektas, kvieciamas GameOver objektas, rezultato atvaizdavimui
     */
    private void collision() {
        for (int i = 0; i < handler.gameObjects.size(); i++) {
            GameObject tempObject = handler.gameObjects.get(i);
            if (tempObject.getId() == ID.ObstacleCar || tempObject.getId() == ID.EnemyCar) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    handler.removeObject(this);
                    handler.removeObject(score);
                    GameOver gameOver = new GameOver(10, 10, ID.GameOver);
                    gameOver.setScore(score.getScore());
                    handler.addObject(gameOver);

                }
            }
        }
    }

    /**
     * @brief Keiciama objekto pozicija, suteikiamos koordinaciu ribos
     */
    public void tick() {
        x = x + velX;
        y = y + velY;
        x = border((int)x, 90, 295);
        y = border((int)y, 100, 500);

        collision();
    }

    /**
     * @brief Prisikiriamas ir atnaujinamas objekto grafinis atvaizdavimas naudojant biblioteka is isores
     */
    public void render(Graphics graphics) {
        graphics.drawImage(image, (int)x, (int)y, null);
    }

    /**
     * @brief Nustatomos koordinaciu plokstumos ribos
     */
    private int border(int position, int min, int max) {
        if (position >= max) {
            return max;
        }
        if (position <= min) {
            return min;
        }
        return position;
    }

    /**
     * @brief Uzklausiamas tasku rezultatas is Score  tasku skaiciavimo objekto
     */
    public Score getScore() {
        return score;
    }


}

