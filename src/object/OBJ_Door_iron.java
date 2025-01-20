package object;

import entity.Entity;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door_iron extends Entity {

    GamePanel gp;
    public OBJ_Door_iron(GamePanel gp){
        super(gp);
        name = "Door_iron";
        down1 = setup("/res/objects/door_iron");

        collition = true;
    }
}
