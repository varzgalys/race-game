package com.race.main;


import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
/**
 * @brief Zaidimo objektu, klavisu pagalba, valdymo objektas
 */
public class KeyInput extends KeyAdapter{

    private boolean[] keyDown = new boolean[4];
    private Handler handler;
    /**
     * @brief Kiekvienam klavisui priskiriamas kintamasis
     */
    public KeyInput(Handler handler){
        this.handler = handler;
        keyDown[0]=false;
        keyDown[1]=false;
        keyDown[2]=false;
        keyDown[3]=false;
    }
    /**
     * @brief Nuspaudus tam tikrus klavisus priskiriamas veiksmas keisti objekto koordinaciu kitima (greiti ir krypti), bei anksciau priskirtu kintamuju reiksme
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0; i  < handler.gameObjects.size(); i++){
            GameObject tempObject = handler.gameObjects.get(i);
            if(tempObject.getId() == ID.Player){
                if(key == KeyEvent.VK_RIGHT){
                    tempObject.setVelX(+2);
                    keyDown[0]=true;
                }
                if (key == KeyEvent.VK_LEFT){
                    tempObject.setVelX(-2);
                    keyDown[1]=true;
                }
                if (key == KeyEvent.VK_UP){
                    tempObject.setVelY(-2);
                    keyDown[2]=true;
                }
                if (key == KeyEvent.VK_DOWN){
                    tempObject.setVelY(+5);
                    keyDown[3]=true;
                }
            }
        }
    }
    /**
     * @brief Atleidus tam tikrus klavisus priskiriamas veiksmas keisti objekto koordinaciu kitima (greiti ir krypti), bei anksciau priskirtu kintamuju reiksme
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0; i  < handler.gameObjects.size(); i++){
            GameObject tempObject = handler.gameObjects.get(i);
            if(tempObject.getId() == ID.Player){
                if(key == KeyEvent.VK_RIGHT){
                    keyDown[0]=false;
                }
                if (key == KeyEvent.VK_LEFT){
                    keyDown[1]=false;
                }
                if (key == KeyEvent.VK_UP){
                    keyDown[2]=false;
                }
                if (key == KeyEvent.VK_DOWN){
                    keyDown[3]=false;
                }
                if(!keyDown[0] && !keyDown[1]){
                    tempObject.setVelX(0);
                }
                if(!keyDown[2] && !keyDown[3]){
                    tempObject.setVelY(0);
                }

            }
        }
    }
}
