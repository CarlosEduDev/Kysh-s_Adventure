package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Normal extends Entity {

    public OBJ_Sword_Normal(GamePanel gp) {
        super(gp);

        name = "Espada normal";
        down1 = setup("/res/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;
        descripton = "[" + name + "]\nUma espada velha.";
    }
}
