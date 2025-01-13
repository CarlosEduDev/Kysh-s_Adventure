package entity;

import main.GamePanel;
import main.KeyHandler;
import main.Sound;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    public int hasKey = 0;
    int standCounter = 0;
    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 16;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        solidArea.width = 30;
        solidArea.height = 28;

        setDefaultValues();
        getPlayerImage();
    }

    // Definir valores padrões
    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";
    } //java -jar caminho\para\seuArquivo.jar


    // Carregar imagens do jogador
    public void getPlayerImage() {
        up1 = setup("boy_up_1");
        up2 = setup("boy_up_2");
        down1 = setup("boy_down_1");
        down2 = setup("boy_down_2");
        left1 = setup("boy_left_1");
        left2 = setup("boy_left_2");
        right1 = setup("boy_right_1");
        right2 = setup("boy_right_2");
    }

    public BufferedImage setup(String ImageName){
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/player/Walking_sprites/" + ImageName + ".png"));
            image = uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }

        return image;
    }

    // Atualizar posição e direção do jogador
    public void update() {

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true){

            if (keyH.upPressed) {
                direction = "up";
            }
            else if (keyH.downPressed) {
                direction = "down";
            }
            else if (keyH.leftPressed) {
                direction = "left";
            }
            else if (keyH.rightPressed) {
                direction = "right";
            }

            //checa a colisão dos tile
            collitionOn = false;
            gp.collitionCh.checkTile(this);

            // checar a colisão dos objetos
            int objIndex = gp.collitionCh.checkObject(this, true);
            pickUpObject(objIndex);
            // se a colisão for false, o jogador pode andar
            if(collitionOn == false){
                switch(direction){
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }

            spriteCounter++;
            if(spriteCounter > 12){
                if(spriteNum == 1){
                    spriteNum = 2;
                }
                else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
        else{
            standCounter++;
            if(standCounter == 20){
                spriteNum = 1;
                standCounter = 0;

            }
        }

    }

    public void pickUpObject(int i){ // pegar um objeto
        if(i != 999){
            String objectName = gp.obj[i].name;

            switch (objectName){
                case "Key":
                    gp.playSoundEff(2);
                    gp.obj[i] = null;
                    hasKey++;
                    gp.ui.ShowMessage("Você pegou uma chave");
                    break;
                case "Chest":
                    if(hasKey > 0){
                        gp.obj[i] = null;
                        hasKey--;
                        gp.ui.ShowMessage("Você abriu um baú!");
                    }
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSoundEff(5);
                    break;
                case "Door", "Door_iron":
                    if(hasKey > 0){
                        gp.playSoundEff(4);
                        gp.obj[i] = null;
                        hasKey--;
                    }else{
                        gp.ui.ShowMessage("Você precisa de uma chave para abrir!");
                    }
                    break;

                case "Boots":
                    gp.obj[i] = null;
                    gp.playSoundEff(3);
                    speed +=1.4;
                    gp.ui.ShowMessage("Você pegou uma bota de velocidade!");
                    break;
            }
        }
    }

    // Desenhar o jogador na tela
    public void draw(Graphics g2) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if(spriteNum == 1){
                image = up1;
                }
                if(spriteNum == 2){
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1){
                image = down1;
                }
                if(spriteNum == 2){
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1){
                image = left1;
                }
                if(spriteNum == 2){
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1){
                image = right1;
                }
                if(spriteNum == 2){
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY,null);
    }
}
