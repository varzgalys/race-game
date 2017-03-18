package com.race.main;

import javax.swing.*;
import java.awt.*;

/**
 * @brief Kuriamas pagrinidis langas kuriama vyksta programa
 */
public class Window extends Canvas {
    /**
     * @brief Langui priskiriamas dydis, pavadinimas, pagrindis programos objektas
     */
    public Window(int width, int height, String title, Game game){
        JFrame frame = new JFrame(title);

        frame.setPreferredSize(new Dimension(width,height));
        frame.setMaximumSize(new Dimension(width,height));
        frame.setMinimumSize(new Dimension(width,height));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.add(game);
        frame.setVisible(true);
        game.start();

    }


}
