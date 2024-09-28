package main;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    // config da peças
    final int originalTileSize = 16; // 16x16 - tela
    final int scale = 3;
    final int tileSize = originalTileSize * scale; // 48x48 peças
    final int maxScreenColm = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenColm; // 769 pixels
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels

    Thread gameThread;
    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);

    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    @Override
    public void run() {
        while(gameThread != null){

        }
    }
}
