package entity;

import main.GamePanel;
import main.KeyHandler;
import object.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Player extends Entity {
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    int standCounter = 0;
    public boolean attackCanceled = false;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int maxInventorySize = 20;
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

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackIMG();
        setItems();
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
        maxMana = 4;
        mana = maxMana;
        ammo = 10;
        level = 1;
        strenght = 1;
        dexterity = 1;
        exp = 0;
        nextLevelExp = 5;
        coin = 0;
        currentweapon = new OBJ_Sword_Normal(gp);
//        currentweapon = new OBJ_Axe(gp);
        currentShield = new OBJ_Shield_Wood(gp);
        attack = getAttack();
        defense = getDefense();
        projectTile = new OBJ_fireball(gp);
    }

    public void setDefaultConditions(){
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        direction = "down";
    }

    public void restoreLifeAndMana(){
        life = maxLife;
        mana = maxMana;
        invincible = false;
    }

    public void setItems(){
        inventory.clear();
        inventory.add(currentweapon);
        inventory.add(currentShield);
        inventory.add(new OBJ_Key(gp));
    }

    private int getAttack() {
        attackArea = currentweapon.attackArea;

        return attack = strenght * currentweapon.attackValue;
    }

    private int getDefense() {return defense = dexterity * currentShield.defenseValue;
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

        if(currentweapon.type == type_sword){
            attackUp1 = setup("/res/player/boy_attack_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/res/player/boy_attack_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/res/player/boy_attack_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/res/player/boy_attack_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/res/player/boy_attack_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/res/player/boy_attack_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/res/player/boy_attack_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/res/player/boy_attack_right_2", gp.tileSize*2, gp.tileSize);

        }

        if(currentweapon.type == type_axe){
            attackUp1 = setup("/res/player/boy_axe_up_1", gp.tileSize, gp.tileSize*2);
            attackUp2 = setup("/res/player/boy_axe_up_2", gp.tileSize, gp.tileSize*2);
            attackDown1 = setup("/res/player/boy_axe_down_1", gp.tileSize, gp.tileSize*2);
            attackDown2 = setup("/res/player/boy_axe_down_2", gp.tileSize, gp.tileSize*2);
            attackLeft1 = setup("/res/player/boy_axe_left_1", gp.tileSize*2, gp.tileSize);
            attackLeft2 = setup("/res/player/boy_axe_left_2", gp.tileSize*2, gp.tileSize);
            attackRight1 = setup("/res/player/boy_axe_right_1", gp.tileSize*2, gp.tileSize);
            attackRight2 = setup("/res/player/boy_axe_right_2", gp.tileSize*2, gp.tileSize);

        }
    }

    // Atualizar posição e direção do jogador
    public void update() {

        if(attacking == true){
            attacking();
        }
        else if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true || keyH.enterPressed == true){

            if (keyH.upPressed) {direction = "up";}
            else if (keyH.downPressed) {direction = "down";}
            else if (keyH.leftPressed) {direction = "left";}
            else if (keyH.rightPressed) {direction = "right";}

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

            // checa colião dos tiles interativos
            gp.collitionCh.checkEntity(this,gp.iTile);

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

            if(keyH.enterPressed == true && attackCanceled == false){
                attacking = true;
                spriteCounter = 0;
            }

            gp.keyHandler.enterPressed = false;
            attackCanceled = false;

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

        if(gp.keyHandler.shotKeyPressed == true && projectTile.alive == false
                && shotAvailableCounter == 30 && projectTile.haveResource(this) == true){
            // configura coordenadas, direções e usuário padrões
            projectTile.set(worldX, worldY, direction, true, this);

            // SUBTRAI O CUSTO DE MANA
            projectTile.subtractResource(this);

            // adicionar a lista
            gp.projectileList.add(projectTile);

            shotAvailableCounter = 0;
            gp.playSoundEff(11);
        }

        if(invincible == true){
            invincibleCounter++;

            if(invincibleCounter > 60){
                invincible = false;
                invincibleCounter = 0;
            }
        }

        if(shotAvailableCounter < 30){
            shotAvailableCounter++;
        }

        if(life > maxLife){
            life = maxLife;
        }

        if(mana > maxMana){
            mana = maxMana;
        }
        if(life <= 0){
            gp.gameState = gp.gameOverState;
            gp.ui.commandNum = -1;
            gp.stopMusic();
            gp.playSoundEff(13);
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
            damageMonster(monsterIndex, attack);

            int iTileIndex = gp.collitionCh.checkEntity(this, gp.iTile);
            damageInteractive(iTileIndex);

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

    public void damageInteractive(int i) {
        if(i != 999 && gp.iTile[i].destructible == true
                && gp.iTile[i].isCorrectItem(this) == true && gp.iTile[i].invincible == false){
            gp.iTile[i].playSE();
            gp.iTile[i].life--;
            gp.iTile[i].invincible = true;

            // GERAR PARTICULAS
            generateParticle(gp.iTile[i], gp.iTile[i]);

            if(gp.iTile[i].life == 0){
                gp.iTile[i] = gp.iTile[i].getDestroyedForm();
            }
        }
    }

    public void damageMonster(int i, int attack) {

        if(i != 999){
            if(gp.monster[i].invincible == false){
                gp.playSoundEff(6);

                int damage = attack - gp.monster[i].defense;
                if(damage < 0){
                    damage = 0;
                }

                gp.monster[i].life -= damage;
                gp.ui.addMessage(damage + " de dano");
                gp.monster[i].invincible = true;
                gp.monster[i].damageReaction();

                if(gp.monster[i].life <= 0){
                    gp.monster[i].dying = true;
//                    gp.ui.addMessage("você matou " + gp.monster[i].name + "!");
                    gp.ui.expMessage("EXP + " + gp.monster[i].exp);
                    exp += gp.monster[i].exp;
                    checkLevelUp();
                }
            }
        }
    }

    public void checkLevelUp() {
        if(exp >= nextLevelExp){
            level++;
            nextLevelExp = nextLevelExp*2;
            maxLife += 2;
            strenght++;
            dexterity++;
            attack = getAttack();
            defense = getDefense();
            gp.playSoundEff(9);
            gp.gameState = gp.dialogueState;
            gp.ui.currentDialogue = "Você aumentou seu nível.\nSeu nível atual: " + level;
        }
    }

    public void pickUpObject(int i){ // pegar um objeto
        if(i != 999){
            // pegar apenas itens
            if(gp.obj[i].type == type_pickUpOnly){
                gp.obj[i].use(this);
                gp.obj[i] = null;
            }
            // itens do inventário
            else{
                String text = "";
                if(inventory.size() != maxInventorySize){
                    inventory.add(gp.obj[i]);
                    gp.playSoundEff(2);
                }
                else{
                    text = "Seu inventário está cheio.";
                }
                gp.ui.addMessage(text);
                gp.obj[i] = null;
            }
        }

    }

    public void interactNPC(int i){
        if(gp.keyHandler.enterPressed == true){
            if(i != 999){
                attackCanceled = true;
                gp.playSoundEff(8);
                gp.gameState = gp.dialogueState;
                gp.npc[i].speak();
            }
        }
    }

    public void contactMonster(int index){
        if(index != 999){

            if(invincible == false && gp.monster[index].dying == false){
                gp.playSoundEff(7);

                int damage = gp.monster[index].attack - defense;
                if(damage < 0){
                    damage = 0;
                }

                life -= damage;
                invincible = true;
            }
        }
    }

    public void selectItem(){
        int itemIndex = gp.ui.getItemIndexOnSlot();

        if(itemIndex < inventory.size()){
            Entity selectedItem = inventory.get(itemIndex);

            if(selectedItem.type == type_sword || selectedItem.type == type_axe){
                currentweapon = selectedItem;
                attack = getAttack();
                getPlayerAttackIMG();
            }

            if(selectedItem.type == type_shield){
                currentShield = selectedItem;
                defense = getDefense();
            }

            if(selectedItem.type == type_consumable){
                selectedItem.use(this);
                inventory.remove(itemIndex);
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