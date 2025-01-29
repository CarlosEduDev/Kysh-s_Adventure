package main;

import entity.Entity;
import object.OBJ_Heart;
import object.OBJ_Mana;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UI {
    GamePanel gp;
    Font arial_24, arial_48B;

    Graphics2D g2;
    public boolean messageOn = false;
    BufferedImage heart_full, hear_half, heart_blank, mana_full, mana_blank;

    ArrayList<String> message = new ArrayList<>();
    ArrayList<Integer> messageCounter = new ArrayList<>();

    ArrayList<String> expMess = new ArrayList<>();
    ArrayList<Integer> expMessCounter = new ArrayList<>();

    public boolean gameFinished = false;
    public String currentDialogue = "";
    public int commandNum = 0;
    public int slotCol = 0;
    public int slotRow = 0;


    public UI(GamePanel gp){
        this.gp = gp;

        arial_24 = new Font("Cambria", Font.PLAIN, 24);
        arial_48B = new Font("Arial", Font.BOLD, 48);

        Entity heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        hear_half = heart.image2;
        heart_blank = heart.image3;

        Entity mana = new OBJ_Mana(gp);
        mana_full = mana.image;
        mana_blank = mana.image2;
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
            drawMessage();
            drawExpMessage();
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

        // CHARACTER STATE
        if(gp.gameState == gp.characterState){
            drawCharacterScreen();
            drawInventory();
        }

    }

    private void drawInventory() {
        int frameX = gp.tileSize*12;
        int frameY = gp.tileSize;
        int frameWidth = gp.tileSize*6;
        int frameHeight = gp.tileSize*5;
        drawWindow(frameX, frameY, frameWidth, frameHeight);

        // SLOT
        final int slotXstart = frameX + 20;
        final int slotYstart = frameY + 20;
        int slotX = slotXstart;
        int slotY = slotYstart;
        int slotSize = gp.tileSize+3;

        // DESENHAR ITENS DO JOGADOR
        for(int i = 0; i < gp.player.inventory.size(); i++){

            // EQUIPAR
            if(gp.player.inventory.get(i) == gp.player.currentweapon ||
               gp.player.inventory.get(i) == gp.player.currentShield){
                g2.setColor(new Color(240, 190, 90));
                g2.fillRoundRect(slotX, slotY, gp.tileSize, gp.tileSize, 10, 10);
            }

            g2.drawImage(gp.player.inventory.get(i).down1, slotX, slotY, null);
            slotX += slotSize;

            if(i == 4 || i == 9 || i == 14){
                slotX = slotXstart;
                slotY += slotSize;
            }
        }


        // CURSOR
        int CursorX = slotXstart + (slotSize * slotCol);
        int CursorY = slotYstart + (slotSize * slotRow);
        int CursorWidth = gp.tileSize;
        int CursorHeight = gp.tileSize;

        // DESENHAR CURSOR
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(CursorX, CursorY,CursorWidth, CursorHeight, 10, 10);

        // FRAME DA DESCRIÇÃO DOS ITENS(CAIXA)
        int dFrameX = frameX;
        int dFrameY = frameY + frameHeight;
        int dFrameWidth = frameWidth;
        int dFrameHeight = gp.tileSize*3;

        // TEXTO DESCRIÇÃO DOS ITENS
        int textX = dFrameX + 20;
        int textY = dFrameY + gp.tileSize;
        g2.setFont(g2.getFont().deriveFont(20F));

        int itemIndex = getItemIndexOnSlot();

        if(itemIndex < gp.player.inventory.size()){

            drawWindow(dFrameX, dFrameY, dFrameWidth, dFrameHeight);

            for(String line: gp.player.inventory.get(itemIndex).descripton.split("\n")){
                g2.drawString(line, textX, textY);
                textY += 32;
            }
        }
    }

    public int getItemIndexOnSlot(){
        int itemIndex = slotCol + (slotRow*5);

        return itemIndex;
    }

    private void drawMessage() {
        int messageX = gp.tileSize;
        int messageY = gp.tileSize*4;
        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 24F));

        for(int i = 0; i < message.size(); i++){
            if(message.get(i) != null){
                g2.setColor(Color.black);
                g2.drawString(message.get(i), messageX+2, messageY+2);

                g2.setColor(Color.white);
                g2.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1; //messageCounter++;
                messageCounter.set(i, counter);
                messageY += 50;

                if(messageCounter.get(i) > 180){
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawExpMessage(){
        int expMessageX = (gp.tileSize+3)*7;
        int expMessageY = gp.tileSize*5;

        g2.setFont(g2.getFont().deriveFont(Font.BOLD, 23F));

        for(int i = 0; i < expMess.size(); i++){
            if(expMess.get(i) != null){
                g2.setColor(Color.yellow);
                g2.drawString(expMess.get(i), expMessageX, expMessageY);

                int counter = expMessCounter.get(i) + 1;
                expMessCounter.set(i, counter);
                expMessageY +=50;

                if(expMessCounter.get(i) > 186){
                    expMess.remove(i);
                    expMessCounter.remove(i);
                }

            }
        }
    }

    private void drawCharacterScreen() {
        final int frameX = gp.tileSize *2;
        final int frameY = gp.tileSize;
        final int frameWidth = gp.tileSize*5;
        final int frameHeight = gp.tileSize*10;
        drawWindow(frameX, frameY, frameWidth, frameHeight);

        // TEXT
        g2.setColor(Color.white);
        g2.setFont(g2.getFont().deriveFont(24F));

        int textX = frameX + 20;
        int textY = frameY + gp.tileSize;
        final int lineHeight = 35;

        // nomes
        g2.drawString("Nível", textX, textY);
        textY += lineHeight;

        g2.drawString("Vida", textX, textY);
        textY += lineHeight;
        g2.drawString("Mana", textX, textY);
        textY += lineHeight;

        g2.drawString("Força", textX, textY);
        textY += lineHeight;

        g2.drawString("Destreza", textX, textY);
        textY += lineHeight;

        g2.drawString("Ataque", textX, textY);
        textY += lineHeight;

        g2.drawString("Defesa", textX, textY);
        textY += lineHeight;

        g2.drawString("Exp", textX, textY);
        textY += lineHeight;

        g2.drawString("Próximo nível", textX, textY);
        textY += lineHeight;

        g2.drawString("Moedas", textX, textY);
        textY += lineHeight + 10;

        g2.drawString("Espada", textX, textY);
        textY += lineHeight + 14;

        g2.drawString("Escudo", textX, textY);
        textY += lineHeight;

        // VALORES
        int tailX = (frameX + frameWidth) - 30;

        textY = frameY + gp.tileSize;
        String value;

        value = String.valueOf(gp.player.level);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.life + "/" + gp.player.maxLife);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.mana + "/" + gp.player.maxMana);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.strenght);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.dexterity);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.attack);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.defense);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.exp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.nextLevelExp);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        value = String.valueOf(gp.player.coin);
        textX = getXforAlignToRightText(value, tailX);
        g2.drawString(value, textX, textY);
        textY += lineHeight;

        g2.drawImage(gp.player.currentweapon.down1, tailX - gp.tileSize, textY-24, null);
        textY += gp.tileSize;
        g2.drawImage(gp.player.currentShield.down1, tailX - gp.tileSize, textY-24, null);
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

        // DESENHA MANA MAXIMA
        x = (gp.tileSize/2) - 5;
        y = (int)(gp.tileSize*1.5);
        i = 0;

        while(i < gp.player.maxMana){
            g2.drawImage(mana_blank,x, y, null);
            i++;
            x += 35;
        }

        // DESENHA MANA
        x = (gp.tileSize/2) - 5;
        y = (int)(gp.tileSize*1.5);
        i = 0;
        while(i < gp.player.mana){
            g2.drawImage(mana_full, x, y, null);
            i++;
            x += 35;
        }

    }

    public void addMessage(String text){
        message.add(text);
        messageCounter.add(0);
    }

    public void expMessage(String text){
        expMess.add(text);
        expMessCounter.add(0);
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

    public int getXforAlignToRightText(String text, int tailX){
        int lenght = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        int x = tailX - lenght;

        return x;
    }
}
