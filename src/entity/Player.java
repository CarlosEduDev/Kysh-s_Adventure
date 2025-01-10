package entity;

import main.GamePanel;
import main.KeyHandler;
import main.Sound;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;
    int hasKey = 0;

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

        solidArea.width = 32;
        solidArea.height = 32;

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
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Walking_sprites/boy_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Walking_sprites/boy_up_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Walking_sprites/boy_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Walking_sprites/boy_down_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Walking_sprites/boy_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Walking_sprites/boy_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Walking_sprites/boy_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Walking_sprites/boy_right_2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    }

    public void pickUpObject(int i){ // pegar um objeto
        if(i != 999){
            String objectName = gp.obj[i].name;

            switch (objectName){
                case "Key":
                    gp.playSoundEff(2);
                    gp.obj[i] = null;
                    hasKey++;
                    System.out.println("Você obteve " + hasKey + " chave(s)\nProcure um baú para abri-lo!");
                    break;
                case "Chest":
                    if(hasKey > 0){
                        gp.obj[i] = null;
                        hasKey--;
                        System.out.println("Você abriu o baú!\nvocê obteve o seguinte item: espada");
                        System.out.println("Você tem " + hasKey + " chave(s)");
                    }
                    break;
                case "Door", "Door_iron":
                    if(hasKey > 0){
                        gp.playSoundEff(4);
                        gp.obj[i] = null;
                        hasKey--;
                        System.out.println("Você tem " + hasKey + " chave(s)");
                    }
                    break;

                case "Boots":
                    gp.obj[i] = null;
                    gp.playSoundEff(3);
                    speed +=1.4;
                    System.out.println("Você pegou a bota!");
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

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
