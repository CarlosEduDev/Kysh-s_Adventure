package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {
    GamePanel gp;
    int value = 5;
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Porção de vida";
        down1 = setup("/res/objects/potion_red", gp.tileSize, gp.tileSize);
        descripton = "[ " + name + " ]\nRecupera " + value + " de vida.";
    }

    public void use(Entity entity){
//        gp.gameState = gp.dialogueState;
//        gp.ui.currentDialogue = "Você bebeu " + name + "\nVocê recuperou " + value + " de vida.";
        entity.life += value;
        if(gp.player.life > gp.player.maxLife){
            gp.player.life = gp.player.maxLife;
        }
        gp.playSoundEff(3);
    }
}
