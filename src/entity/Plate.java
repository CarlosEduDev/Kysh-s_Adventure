package entity;

import main.GamePanel;

public class Plate extends Entity{
    GamePanel gp;
    public Plate(GamePanel gp) {
        super(gp);
        this.gp = gp;

        direction = "down";

        getImage();
        setDialogue();
//        speak();
    }

    private void getImage(){
        up1 = setup("/res/interactive/plate", gp.tileSize, gp.tileSize);
        up2 = setup("/res/interactive/plate", gp.tileSize, gp.tileSize);
        down1 = setup("/res/interactive/plate", gp.tileSize, gp.tileSize);
        down2 = setup("/res/interactive/plate", gp.tileSize, gp.tileSize);
        left1 = setup("/res/interactive/plate", gp.tileSize, gp.tileSize);
        left2 = setup("/res/interactive/plate", gp.tileSize, gp.tileSize);
        right1 = setup("/res/interactive/plate", gp.tileSize, gp.tileSize);
        right2 = setup("/res/interactive/plate", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){
        dialogues[0] = "Bem vindo ao Kysh's Adventure";
        dialogues[1] = "Para andar, use as teclas W, A, S e D";
        dialogues[2] = "Para ver seu inventário, pressione a tecla I do teclado";
        dialogues[3] = "Para usar sua magia, pressione a tecla F do teclado";
        dialogues[4] = "Para pausar o jogo, pressione a tecla P";
        dialogues[5] = "Para interagir com npc's, cortar árvores e usar sua espada,\npressione a tecla Enter";
        dialogues[6] = "Pressione a tecla ESC para acessar as opções";
        dialogues[7] = "Boa sorte!";
    }
    public void speak(){
        super.speak();
    }
}
