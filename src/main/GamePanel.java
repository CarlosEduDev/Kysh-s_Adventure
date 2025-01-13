package main;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // config da peças
    final int originalTileSize = 16; // 16x16 - tela
    final int scale = 3;
    public final int tileSize = originalTileSize * scale; // 48x48 peças
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
     public final int screenWidth = tileSize * maxScreenCol; // 769 pixels
    public final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    // config do mundo
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;

    int FPS = 60;

    // Sistema
    public UI ui = new UI(this);
    TileManager tileM = new TileManager(this);
    KeyHandler keyHandler = new KeyHandler();
    Sound music = new Sound();
    Sound soundEf = new Sound();
    Thread gameThread;
    public CollitionChecker collitionCh = new CollitionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    // Entidade e Objetos
    public Player player = new Player(this, keyHandler);
    public SuperObject obj[] = new SuperObject[10];


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    public void setupGame(){

        aSetter.setObject();

        playMusic(1);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // 0.016666 seg
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;

        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer+= (currentTime - lastTime);
            lastTime = currentTime;

                if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                drawCount = 0;
                timer = 0;
            }
        }
    }
    public void update(){
        player.update();

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);


        Graphics2D g2 = (Graphics2D)g;

        // DEBUG
        long drawStart = 0;
        if(keyHandler.checkDrawTime == true){
            drawStart = System.nanoTime();

        }

        // tile
        tileM.draw(g2);

        // OBJECT
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){ // checa se o slot não ta vazia
                obj[i].draw(g2, this);
            }
        }

        // PLAYER
        player.draw(g2);

        // UI
        ui.draw(g2);
        // DEBUG
        if(keyHandler.checkDrawTime == true){
            long drawEnd = System.nanoTime();
            long passed = drawEnd - drawStart;
            g2.setColor(Color.white);
            g2.drawString("Tempo de desenho: " + passed, 10, 400);
            System.out.println("Tempo de desenho: " + passed);

        }

        g2.dispose();
    }

    public void playMusic(int i){
        music.setFile(i);
        music.playSong();
        music.loopSong();
    }

    public void stopMusic(){
        music.stopSong();
    }

    public void playSoundEff(int i){
        soundEf.setFile(i);
        soundEf.playSong();
    }
}
