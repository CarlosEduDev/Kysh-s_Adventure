package entity;

import main.GamePanel;

import java.util.Random;

public class NPM_OldMan extends Entity{

    public NPM_OldMan(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 1;

        getImage();
        setDialogue();
        speak();
    }
    public void getImage() {
        up1 = setup("/res/npc/oldman_up_1");
        up2 = setup("/res/npc/oldman_up_2");
        down1 = setup("/res/npc/oldman_down_1");
        down2 = setup("/res/npc/oldman_down_2");
        left1 = setup("/res/npc/oldman_left_1");
        left2 = setup("/res/npc/oldman_left_2");
        right1 = setup("/res/npc/oldman_right_1");
        right2 = setup("/res/npc/oldman_right_2");
    }

    public void setDialogue(){
        dialogues[0] = "Boa viagem, paladino!";
        dialogues[1] = "Boas vindas ao Reino de Varrock, viajante!";
        dialogues[2] = "Suponho que esteja aqui pelo tesouro sagrado.";
        dialogues[3] = "Quase me esqueci de me apresentar! Meu nome \né Kyllert, o guardião mago desse reino, prazer!";
        dialogues[4] = "Infelizmente estou velho demais para me \naventurar em busca do tesouro sagrado, logo\nirei me aposentar haha!";
    }
    public void setAction(){

        actionLockCounter++;

        if(actionLockCounter == 140){

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