package entity;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public Player(GamePanel gp, KeyHandler keyH) {
        super(gp);
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

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackIMG();
    }

    // Definir valores padrões
    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down";

        // PLAYER STATUS
        maxLife = 6;
        life = maxLife;
    }


    // Carregar imagens do jogador
    public void getPlayerImage() {
        up1 = setup("/res/player/boy_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/player/boy_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/player/boy_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/player/boy_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/player/boy_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/player/boy_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/player/boy_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/player/boy_right_2", gp.tileSize, gp.tileSize);
    }

    public void getPlayerAttackIMG(){
        attackUp1 = setup("/res/player/boy_attack_up_1", gp.tileSize, gp.tileSize*2);
        attackUp2 = setup("/res/player/boy_attack_up_2", gp.tileSize, gp.tileSize*2);
        attackDown1 = setup("/res/player/boy_attack_down_1", gp.tileSize, gp.tileSize*2);
        attackDown2 = setup("/res/player/boy_attack_down_2", gp.tileSize, gp.tileSize*2);
        attackLeft1 = setup("/res/player/boy_attack_left_1", gp.tileSize*2, gp.tileSize);
        attackLeft2 = setup("/res/player/boy_attack_left_2", gp.tileSize*2, gp.tileSize);
        attackRight1 = setup("/res/player/boy_attack_right_1", gp.tileSize*2, gp.tileSize);
        attackRight2 = setup("/res/player/boy_attack_right_2", gp.tileSize*2, gp.tileSize);
    }

    // Atualizar posição e direção do jogador
    public void update() {

        if(attacking == true){
            attacking();
        }
        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true){

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

            // checa a colisão com NPC
            int npcIndex = gp.collitionCh.checkEntity(this, gp.npc);
            interactNPC(npcIndex);

            // checa a colisão com monstros
            int monsterIndex = gp.collitionCh.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            // checa evento
            gp.eHandler.checkEvent();

            // se a colisão for false, o jogador pode andar
            if(collitionOn == false && keyH.enterPressed == false){
                switch(direction){
                    case "up": worldY -= speed;break;
                    case "down": worldY += speed;break;
                    case "left": worldX -= speed;break;
                    case "right": worldX += speed;break;
                }
            }

            gp.keyHandler.enterPressed = false;

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

        if(invincible == true){
            invincibleCounter++;

            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }

    }

    private void attacking() {
        spriteCounter++;

        if(spriteCounter <= 5){
            spriteNum = 1;
        }
        if(spriteCounter > 5 && spriteCounter <= 25){
            spriteNum = 2;

            // salva a posição atual do ataque
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Ajusta a posição de ataque do player
            switch(direction){
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "left": worldX -= attackArea.width; break;
                case "right": worldX += attackArea.width; break;
            }

            solidArea.width = attackArea.width;
            solidArea.height = attackArea.height;

            int monsterIndex = gp.collitionCh.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;


        }
        if(spriteCounter > 25){
            spriteNum = 1;
            spriteCounter = 0;
            attacking = false;
        }
    }

    private void damageMonster(int i) {

        if(i != 999){
            if(gp.monster[i].invincible == false){
                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;

                if(gp.monster[i].life <= 0){
                    gp.monster[i] = null;
                }
            }
        }
    }

    public void pickUpObject(int i){ // pegar um objeto
        if(i != 999){
        }
    }

    public void interactNPC(int i){
        if(gp.keyHandler.enterPressed == true){
            if(i != 999){
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
            else{
                attacking = true;
            }

        }
    }

    public void contactMonster(int index){
        if(index != 999){

            if(invincible == false){
                life -= 1;
                invincible = true;
            }
        }
    }

    // Desenhar o jogador na tela
    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if(attacking == false){
                    if(spriteNum == 1){image = up1;}
                    if(spriteNum == 2){image = up2;}
                }
                if(attacking == true){
                    tempScreenY = screenY - gp.tileSize;
                    if(spriteNum == 1){image = attackUp1;}
                    if(spriteNum == 2){image = attackUp2;}
                }
                break;
            case "down":
                if(attacking == false){
                    if(spriteNum == 1){image = down1;}
                    if(spriteNum == 2){image = down2;}
                }
                if(attacking == true){
                    if(spriteNum == 1){image = attackDown1;}
                    if(spriteNum == 2){image = attackDown2;}
                }
                break;
            case "left":
                if(attacking == false){
                    if(spriteNum == 1){image = left1;}
                    if(spriteNum == 2){image = left2;}
                }
                if(attacking == true){
                    tempScreenX = screenX - gp.tileSize;
                    if(spriteNum == 1){image = attackLeft1;}
                    if(spriteNum == 2){image = attackLeft2;}
                }
                break;
            case "right":
                if(attacking == false){
                    if(spriteNum == 1){image = right1;}
                    if(spriteNum == 2){image = right2;}
                }
                if(attacking == true){
                    if(spriteNum == 1){image = attackRight1;}
                    if(spriteNum == 2){image = attackRight2;}
                }
                break;
        }

        if(invincible == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }
        g2.drawImage(image, tempScreenX, tempScreenY,null);

        // resetar alpha
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}