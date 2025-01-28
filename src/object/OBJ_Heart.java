package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Heart extends Entity {
    GamePanel gp;
    public OBJ_Heart(GamePanel gp){
        super(gp);
        this.gp = gp;

        type = type_pickUpOnly;
        value = 2;
        down1 = setup("/res/objects/heart_full", gp.tileSize, gp.tileSize);
        name = "Heart";
        image = setup("/res/objects/heart_full", gp.tileSize, gp.tileSize);
        image2 = setup("/res/objects/heart_half", gp.tileSize, gp.tileSize);
        image3 = setup("/res/objects/heart_blank", gp.tileSize, gp.tileSize);
    }

    public void use(Entity entity){
        gp.playSoundEff(3);
        gp.ui.addMessage("Vida +" + value);
        entity.life += value;
    }
}
