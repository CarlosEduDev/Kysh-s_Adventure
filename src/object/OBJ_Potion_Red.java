package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Potion_Red extends Entity {
    GamePanel gp;
    public OBJ_Potion_Red(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_consumable;
        name = "Porção de vida";
        value = 5;
        down1 = setup("/res/objects/potion_red", gp.tileSize, gp.tileSize);
        descripton = "[ " + name + " ]\nRecupera " + value + " de vida.";
    }

    public void use(Entity entity){
        entity.life += value;
        gp.playSoundEff(3);
    }
}
