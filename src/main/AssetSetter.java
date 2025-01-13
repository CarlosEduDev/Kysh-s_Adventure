package main;

import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = 23 * gp.tileSize;
        gp.obj[0].worldY = 7 * gp.tileSize;

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 40 * gp.tileSize; //Kysh-s_Adventure

        gp.obj[2] = new OBJ_Chest(gp);
        gp.obj[2].worldX = 24 * gp.tileSize;
        gp.obj[2].worldY = 40 * gp.tileSize;

//        gp.obj[3] = new OBJ_Door();
//        gp.obj[3].worldX = 15 * gp.tileSize;
//        gp.obj[3].worldY = 21 * gp.tileSize;

        gp.obj[4] = new OBJ_Boots(gp);
        gp.obj[4].worldX = 37 * gp.tileSize;
        gp.obj[4].worldY = 40 * gp.tileSize;

        gp.obj[5] = new OBJ_Door_iron(gp);
        gp.obj[5].worldX = 10 * gp.tileSize;
        gp.obj[5].worldY = 11 * gp.tileSize;


        gp.obj[6] = new OBJ_Chest(gp);
        gp.obj[6].worldX = 10 * gp.tileSize;
        gp.obj[6].worldY = 7 * gp.tileSize;

    }
}
