package main;

import entity.Entity;
import entity.Player;
import tile.TileManager;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
    public KeyHandler keyHandler = new KeyHandler(this);
    Sound music = new Sound();
    Sound soundEf = new Sound();
    public EventHandler eHandler = new EventHandler(this );
    Thread gameThread;
    public CollitionChecker collitionCh = new CollitionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);

    // Entidade e Objetos
    public Player player = new Player(this, keyHandler);
    public Entity obj[] = new Entity[10];
    public Entity npc[] = new Entity[10];
    public Entity monster[] = new Entity[20];
    ArrayList<Entity> entityList = new ArrayList<>();

    // GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    public void setupGame(){

        aSetter.setObject();
        aSetter.setNPC();
        aSetter.setMonster();
//        playMusic(1);
//        stopMusic();
        gameState = titleState;
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
        if(gameState == playState){
            // jogador
            player.update();

            // NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    npc[i].update();
                }
            }

            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    monster[i].update();
                }
            }
        }
        if(gameState == pauseState){
            // nada
        }

    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);


        Graphics2D g2 = (Graphics2D)g;

        // DEBUG
        long drawStart = 0;
        if(keyHandler.checkDrawTime == true){
            drawStart = System.nanoTime();

        }

        // TITLE SCREEN
        if(gameState == titleState){
            ui.draw(g2);
        }
         //outros
        else{
            // tile
            tileM.draw(g2);

            entityList.add(player);

            // NPC
            for(int i = 0; i < npc.length; i++){
                if(npc[i] != null){
                    entityList.add(npc[i]);
                }
            }

            // OBJETOS
            for(int i = 0; i < obj.length; i++){
                if(obj[i] != null){
                    entityList.add(obj[i]);
                }
            }

            // Monstros
            for(int i = 0; i < monster.length; i++){
                if(monster[i] != null){
                    entityList.add(monster[i]);
                }
            }

            // SORT
            Collections.sort(entityList, new Comparator<Entity>() {
                @Override
                public int compare(Entity e1, Entity e2) {
                    int result = Integer.compare(e1.worldY, e2.worldY);
                    return result;
                }
            });

            // DESENHAR ENTIDADES
            for(int i = 0; i < entityList.size(); i++){
                entityList.get(i).draw(g2);
            }
            // LISTA DE ENTIDADES VAZIA
            for(int i = 0; i < entityList.size(); i++){
                entityList.remove(i).draw(g2);
            }

            // UI
            ui.draw(g2);
        }
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
