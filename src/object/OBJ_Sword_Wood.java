package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Sword_Wood extends Entity {
    public OBJ_Sword_Wood(GamePanel gp) {
        super(gp);

        name = "Espada de madeira";
        type = type_sword;
        down1 = setup("/res/objects/sword_normal", gp.tileSize, gp.tileSize);
        attackValue = 1;
        attackArea.width = 36;
        attackArea.height = 36;
        descripton = "[ " + name + " ]\nUma espada velha.\nAtaque: " + attackValue;
        price = 3;
        knockBackPower = 4;
    }
}
