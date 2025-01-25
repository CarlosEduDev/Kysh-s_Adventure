package main;

import Monster.GreenSlime;
import entity.NPM_OldMan;
import object.*;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){}

    public void setNPC(){ // posição
        gp.npc[0] = new NPM_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize*21;
        gp.npc[0].worldY = gp.tileSize*21;

    }

    public void setMonster(){
        int i = 0;
        gp.monster[i] = new GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*23;
        gp.monster[i].worldY = gp.tileSize*36;
        i++;
        gp.monster[i] = new GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*23;
        gp.monster[i].worldY = gp.tileSize*37;
        i++;
        gp.monster[i] = new GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*22;
        gp.monster[i].worldY = gp.tileSize*36;
        i++;
        gp.monster[i] = new GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*34;
        gp.monster[i].worldY = gp.tileSize*42;
        i++;
        gp.monster[i] = new GreenSlime(gp);
        gp.monster[i].worldX = gp.tileSize*38;
        gp.monster[i].worldY = gp.tileSize*42;
        i++;
    }
}
