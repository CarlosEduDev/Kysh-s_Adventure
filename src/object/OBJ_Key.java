package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Key extends Entity {

    public OBJ_Key(GamePanel gp) {
        super(gp);
        name = "Chave";
        down1 = setup("/res/objects/key", gp.tileSize, gp.tileSize);
        descripton = "[ " + name + " ]\nUma chave para abrir\numa porta.";
        collition = true;
        price = 15;
    }
}
