package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Axe extends Entity {
    public OBJ_Axe(GamePanel gp) {
        super(gp);

        name = "Machado";
        type = type_axe;
        down1 = setup("/res/objects/axe", gp.tileSize, gp.tileSize);
        attackValue = 2;
        descripton = "[ " + name + " ]\nServe para cortar árvores\nAtaque: " + attackValue;
        attackArea.width = 30;
        attackArea.height = 30;
    }
}
