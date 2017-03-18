package com.race.main;

import java.awt.*;
import java.util.LinkedList;
/**
 * @brief Objektu suvedimas i bendra sarasa ir trinimas is ju
 */
public class Handler {


    LinkedList<GameObject> gameObjects = new LinkedList<GameObject>();
    /**
     * @brief Isrenkami objektai is saraso ir atnaujinamos ju koordinates
     */
    public void tick(){
        for (int i =0; i < gameObjects.size();i++){
            GameObject tempObject = gameObjects.get(i);
            tempObject.tick();
        }
    }
    /**
     * @brief Isrenkami objektai is saraso ir atnaujinamos ju grafinis vaizdas naudojant biblioteka is isores
     */
    public void render(Graphics graphics){
        for (int i =0; i < gameObjects.size();i++){
            GameObject tempObject = gameObjects.get(i);
            tempObject.render(graphics);
        }
    }
    /**
     * @brief Metodas skirtas prideti zaidimo naujas objekta
     */
    public void addObject(GameObject gameObject){
        this.gameObjects.add(gameObject);
    }
    /**
     * @brief Metodas skirtas naikinti zaidimo objekta
     */
    public void removeObject(GameObject gameObject){
        this.gameObjects.remove(gameObject);

    }

}
