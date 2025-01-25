package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Blue_Shield extends Entity {
    public OBJ_Blue_Shield(GamePanel gp) {
        super(gp);

        name = "Escudo azul";
        type = type_shield;
        down1 = setup("/res/objects/shield_blue", gp.tileSize, gp.tileSize);
        defenseValue = 2;
        descripton = "[ " + name + " ]\nUm escudo cintilante\nDefesa: " + defenseValue;
        attackArea.width = 30;
        attackArea.height = 30;
    }
}
