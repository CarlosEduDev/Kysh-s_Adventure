package object;

import entity.Entity;
import main.GamePanel;

public class OBJ_Coin_Bronze extends Entity {
    GamePanel gp;
    public OBJ_Coin_Bronze(GamePanel gp) {
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        name = "Moeda de bronze";
        value = 1;
        down1 = setup("/res/objects/coin_bronze", gp.tileSize, gp.tileSize);
    }

    public void use(Entity entity){
        gp.playSoundEff(3);
        gp.ui.addMessage("Moeda +" + value);
        gp.player.coin += value;
    }
}
