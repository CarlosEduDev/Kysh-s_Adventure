package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font arial_24, arial_48B;

    BufferedImage keyImage;

    public boolean messageOn = false;
    public String message = "";
    int menssageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp){
        this.gp = gp;

        arial_24 = new Font("Arial", Font.PLAIN, 24);
        arial_48B = new Font("Arial", Font.BOLD, 48);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
    }

    public void ShowMessage(String text){
        message = text;
        messageOn = true;
    }


    public void draw(Graphics2D g2){

        if(gameFinished == true){
            g2.setFont(arial_24);
            g2.setColor(Color.white);

            String text;
            int textLength;
            int x;
            int y;

            text = "Você achou um tesouro!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            g2.setFont(arial_48B);
            g2.setColor(Color.yellow);
            text = "Parabéns!";
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*2);
            g2.drawString(text, x, y);

            g2.setFont(arial_24);
            g2.setColor(Color.white);
            text = "Seu tempo de jogo: " + dFormat.format(playTime);
            textLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();

            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*4);
            g2.drawString(text, x, y);

            gp.gameThread = null;

        }
        else{
            g2.setFont(arial_24);
            g2.setColor(Color.white);
            g2.drawImage(keyImage, gp.tileSize/2, gp.tileSize/2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey,74, 50);

            // TEMPO DE JOGO
            playTime += (double) 1/60;
            g2.drawString("Tempo: " + dFormat.format(playTime),gp.tileSize*12, 28);

            // Mensagem
            if(messageOn == true){
                g2.setFont(g2.getFont().deriveFont(30F));
                g2.drawString(message, gp.tileSize*2, gp.tileSize*5);

                menssageCounter++;

                if(menssageCounter > 120){
                    menssageCounter = 0;
                    messageOn = false;
                }
            }
        }

    }
}
