package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Mana extends Entity {
    GamePanel gp;
    public OBJ_Mana(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        value = 1;
        down1 = setup("/res/objects/manacrystal_full", gp.tileSize, gp.tileSize);
        name = "Mana";
        image = setup("/res/objects/manacrystal_full", gp.tileSize, gp.tileSize);
        image2 = setup("/res/objects/manacrystal_blank", gp.tileSize, gp.tileSize);

    }

    public void use(Entity entity){
        gp.playSoundEff(3);
        gp.ui.addMessage("Mana +" + value);
        entity.mana += value;
    }
}
