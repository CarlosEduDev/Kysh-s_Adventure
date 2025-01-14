package main;

import object.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

public class UI {
    GamePanel gp;
    Font arial_24, arial_48B;

    Graphics2D g2;


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

    }

    public void ShowMessage(String text){
        message = text;
        messageOn = true;
    }


    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_48B);
        g2.setColor(Color.white);

        if(gp.gameState == gp.playState){
            // fazer playState dps
            }

        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
        }

    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
        String text = "Jogo pausado";
        int x = getXforCenteredText(text), y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public int getXforCenteredText(String text){
        int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - lenght/2;

        return x;
    }
}
