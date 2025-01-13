package object;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door_iron extends SuperObject{

    GamePanel gp;
    public OBJ_Door_iron(GamePanel gp){

        this.gp = gp;
        name = "Door_iron";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door_iron.png"));
            uTool.scaleImage(image, gp.tileSize, gp.tileSize);
        }catch (IOException e){
            e.printStackTrace();
        }

        collition = true;
    }
}
