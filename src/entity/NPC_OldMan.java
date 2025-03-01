package entity;

import main.GamePanel;

import java.util.Random;

public class NPC_OldMan extends Entity{

    public NPC_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
//        speak();
    }
    public void getImage() {
        up1 = setup("/res/npc/oldman_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/npc/oldman_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/npc/oldman_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/npc/oldman_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/npc/oldman_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/npc/oldman_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/npc/oldman_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/npc/oldman_right_2", gp.tileSize, gp.tileSize);
    }

    public String setDialogue(){
        int i = 0;
        dialogues[i] = "Boas vindas ao Reino de Varrock, viajante!";
        i++;
        dialogues[i] = "Suponho que esteja aqui pelo tesouro sagrado.";
        i++;
        dialogues[i] = "Quase me esqueci de me apresentar! Meu nome \né Dan, o guardião mago desse reino, prazer!";
        i++;
        dialogues[i] = "Seja bem-vindo e boa viagem";
        i++;

        return dialogues[i];
    }
    public void setAction(){

        actionLockCounter++;

        if(actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100)+1; // escolhe de 1 a 100;

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    public void speak(){
        super.speak();
    }
}
/*
dialogues[4] = "Você parece que veio de muito longe, cavalheiro!";
        dialogues[5] = "Se você encontrar o tesouro sagrado, irá realizar todas as suas ambições, mas tenha cuidado...";
        dialogues[6] = "Muitos guerreiros como você morreram tentando por as mãos no tesouro sagrado, mostre que você é diferente deles!";
* */