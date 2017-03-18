package com.race.main;


import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.Random;

/**
 * @brief Pagrindine klase, kurioje yra kuriami esminiai objektai
 */

public class Game extends Canvas implements Runnable{


    private static final int width = 420, height = 600;
    private Random random;
    private Thread thread;
    private boolean runnning = false;
    private Handler handler;
    private Window window;
    private Score score;
    private int numberOfCars;

    /**
     * @brief Kuriamas pagrinidis langas, handler objektas, bei zaidimo objektai, kurie yra siunciami handler objektui
     */
    public Game(){

        this.requestFocus();
        random = new Random();
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        window = new Window(width,height,"RACE",this);
        handler.addObject(new GameBackground(0,-1400,ID.GameBackground));
        Player player = new Player(190,350, ID.Player,handler);
        numberOfCars = 11;

        handler.addObject(player);
        for (int i = 0; i < numberOfCars; i++){
            int random4 = random.nextInt(4);
            handler.addObject(new ObstacleCar(((random4 + 1)*60) + 42,random.nextInt(2000)-2000,ID.ObstacleCar));
        }
        handler.addObject(player.getScore());
        handler.addObject(new EnemyCar(280,500, ID.EnemyCar, player, handler));
    }
    /**
     * @brief Kuriami objektai is isoriniu biblioteku, pastoviam programos atnaujinimui, bei pradedamas pastovus atnaujinimas
     */

    public synchronized void start(){
        thread = new Thread(this);
        thread.start();
        runnning = true;
    }
    /**
     * @brief Stabdomas pastovus programos atnaujinimas
     */
    public synchronized void stop(){
        try{
            thread.join();
            runnning = false;

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    /**
     * @brief Pagrindis zaidimo ciklo metodas, atnaujinantis visus objektus zaidime
     */

    public void run(){
        long lastTime = System.nanoTime();
        double amountoOfTicks = 60.0;
        double ns = 1000000000 / amountoOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (runnning){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if (runnning){
                render();
            }
            frames++;

            if (System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                //System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    /**
     * @brief Kvieciamas metodas is handler klases
     */
    private void tick(){
        handler.tick();
    }
    /**
     * @brief Kuriamas objektas is isoriniu biblioteku zaidimo objektu grafiniam atnaujinimui naudojant biblioteka is isores
     */
    private void render(){
        BufferStrategy bufferStrategy = this.getBufferStrategy();
        if(bufferStrategy == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics graphics = bufferStrategy.getDrawGraphics();
        graphics.setColor(Color.darkGray);
        graphics.fillRect(0,0,width,height);

        handler.render(graphics);

        graphics.dispose();
        bufferStrategy.show();
    }

    /**
     * @brief Kuriamas naujas zaidimas
     */
    public static void main(String args[]){
        new Game();
    }
}


