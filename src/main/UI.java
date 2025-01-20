package main;

import entity.Entity;
import object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gp;
    Font arial_24, arial_48B;

    Graphics2D g2;
    public boolean messageOn = false;
    BufferedImage heart_full, hear_half, heart_blank;
    public String message = "";
    int menssageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;

    public UI(GamePanel gp){
        this.gp = gp;

        arial_24 = new Font("Cambria", Font.PLAIN, 24);
        arial_48B = new Font("Arial", Font.BOLD, 48);

        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        hear_half = heart.image2;
        heart_blank = heart.image3;
    }

    public void ShowMessage(String text){
        message = text;
        messageOn = true;
    }


    public void draw(Graphics2D g2){
        this.g2 = g2;

        g2.setFont(arial_48B);
        g2.setColor(Color.white);

        // TITLE STATE
        if(gp.gameState == gp.titleState){
            drawTitleScreen();
        }

        // PLAY STATE
        if(gp.gameState == gp.playState){
            drawPlayerLife();
            }

        // PAUSE STATE
        if(gp.gameState == gp.pauseState){
            drawPauseScreen();
            drawPlayerLife();
        }

        // DIALOGUE STATE
        if(gp.gameState == gp.dialogueState){
            drawDialogueScreen();
        }

    }

    public void drawPlayerLife(){
        int x = gp.tileSize/2;
        int y = gp.tileSize/2;
        int i = 0;

        // DESENHA CORAÇÕES CHEIOS
        while(i < gp.player.maxLife/2){
            g2.drawImage(heart_blank, x, y, null);
            i++;
            x += gp.tileSize;
        }

        // RESETA
         x = gp.tileSize/2;
         y = gp.tileSize/2;
         i = 0;

         // DESENHA VIDA PELA METADE
        while(i < gp.player.life){
            g2.drawImage(hear_half, x, y, null);
            i++;
            if(i < gp.player.life){
                g2.drawImage(heart_full, x, y, null);
            }
            i++;
            x+= gp.tileSize;
        }
    }

    public void drawTitleScreen(){
        g2.setColor(new Color(12, 12, 12));
        g2.fillRect(0,0,gp.screenWidth, gp.screenHeight);
        // NOME DO TITULO
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 75F));
        String text = "Kysh's Adventure";
        int x = getXforCenteredText(text), y = gp.tileSize*3;

        // SHADOW
        g2.setColor(Color.gray);
        g2.drawString(text, x+5, y+5);

        // MAIN COLOR
        g2.setColor(Color.white);
        g2.drawString(text, x, y);

        // IMAGEM DO PERSONAGEM
        x = gp.screenWidth/2 - (gp.tileSize*2)/2;
        y += gp.tileSize*2;
        g2.drawImage(gp.player.down1, x, y, gp.tileSize*2, gp.tileSize*2, null);

        // MENU
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 37F));
        text = "NOVO JOGO";
        x = getXforCenteredText(text);
        y += gp.tileSize*4;
        g2.drawString(text, x, y);
        if(commandNum == 0){
            g2.drawString(">", x-(gp.tileSize-15), y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 37F));
        text = "CONTINUAR";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 1){
            g2.drawString(">", x-(gp.tileSize-15), y);
        }

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 37F));
        text = "SAIR";
        x = getXforCenteredText(text);
        y += gp.tileSize;
        g2.drawString(text, x, y);
        if(commandNum == 2){
            g2.drawString(">", x-(gp.tileSize-15), y);
        }
    }

    public void drawPauseScreen(){
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,50F));
        String text = "Jogo pausado";
        int x = getXforCenteredText(text), y = gp.screenHeight/2;

        g2.drawString(text, x, y);
    }

    public void drawDialogueScreen(){
        // WINDOW
        int x = gp.tileSize*2, y = gp.tileSize/2;
        int width = gp.screenWidth - (gp.tileSize*4), height = gp.tileSize*4;
        drawWindow(x, y, width, height);

        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 25));
        x += (gp.tileSize-25);
        y += (gp.tileSize);

        for(String line : currentDialogue.split("\n")){
            g2.drawString(line, x , y);
            y += 40;
        }
    }

    public void drawWindow(int x, int y, int width, int height){
        Color c = new Color(0, 0, 0, 200);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5 ,y+5 ,width-10 ,height-10, 25, 25);
    }

    public int getXforCenteredText(String text){
        int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = gp.screenWidth/2 - lenght/2;

        return x;
    }
}
