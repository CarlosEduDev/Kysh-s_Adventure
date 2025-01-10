package object;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_Door_iron extends SuperObject{
    public OBJ_Door_iron(){
        name = "Door_iron";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/door_iron.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        collition = true;
    }
}
