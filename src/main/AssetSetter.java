package main;

import Monster.GreenSlime;
import entity.NPC_Merchant;
import entity.NPC_OldMan;
import entity.Plate;
import object.*;
import tile_interactive.IT_DryTree;

public class AssetSetter {
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject(){
        int i = 0, mapNum = 0;
        gp.obj[mapNum][i] = new OBJ_Door(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*12;
        gp.obj[mapNum][i].worldY = gp.tileSize*12;
        i++;
        gp.obj[mapNum][i] = new OBJ_Chest(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*11;
        gp.obj[mapNum][i].worldY = gp.tileSize*8;
        i++;
        gp.obj[mapNum][i] = new OBJ_Key(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*26;
        gp.obj[mapNum][i].worldY = gp.tileSize*21;
        i++;
        gp.obj[mapNum][i] = new OBJ_Axe(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*33;
        gp.obj[mapNum][i].worldY = gp.tileSize*7;
        i++;
        gp.obj[mapNum][i] = new OBJ_Blue_Shield(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*35;
        gp.obj[mapNum][i].worldY = gp.tileSize*21;
        i++;
        gp.obj[mapNum][i] = new OBJ_Potion_Red(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*22;
        gp.obj[mapNum][i].worldY = gp.tileSize*27;
        i++;

        gp.obj[mapNum][i] = new OBJ_Heart(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*22;
        gp.obj[mapNum][i].worldY = gp.tileSize*28;
        i++;


        gp.obj[mapNum][i] = new OBJ_Mana(gp);
        gp.obj[mapNum][i].worldX = gp.tileSize*22;
        gp.obj[mapNum][i].worldY = gp.tileSize*30;
        i++;

    }

    public void setInteractiveTile(){
        int i = 0, mapNum = 0;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 14, 26);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 14, 27);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 14, 28);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 27, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 28, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 29, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 30, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 31, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 32, 12);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 33, 12);
        i++;

        gp.iTile[mapNum][i] = new IT_DryTree(gp, 10, 41);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 11, 41);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 12, 41);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 13, 41);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 13, 40);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 14, 40);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 15, 40);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 16, 40);
        i++;
        gp.iTile[mapNum][i] = new IT_DryTree(gp, 17, 40);
        i++;
    }

    public void setNPC(){ // posição
        int mapNum = 0, i = 0;

        // MAPA 0
        gp.npc[mapNum][i] = new NPC_OldMan(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*21;
        gp.npc[mapNum][i].worldY = gp.tileSize*21;

        // MAPA 1
        mapNum = 1;
        i = 0;
        gp.npc[mapNum][i] = new NPC_Merchant(gp);
        gp.npc[mapNum][i].worldX = gp.tileSize*12;
        gp.npc[mapNum][i].worldY = gp.tileSize*7;

    }

    public void setPlate(){
        int mapNum = 0;
        gp.plate[mapNum][0] = new Plate(gp);
        gp.plate[mapNum][0].worldX = gp.tileSize*29;
        gp.plate[mapNum][0].worldY = gp.tileSize*22;
    }

    public void setMonster(){
        int i = 0, mapNum = 0;
        gp.monster[mapNum][i] = new GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*23;
        gp.monster[mapNum][i].worldY = gp.tileSize*36;
        i++;
        gp.monster[mapNum][i] = new GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*23;
        gp.monster[mapNum][i].worldY = gp.tileSize*37;
        i++;
        gp.monster[mapNum][i] = new GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*22;
        gp.monster[mapNum][i].worldY = gp.tileSize*36;
        i++;
        gp.monster[mapNum][i] = new GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*34;
        gp.monster[mapNum][i].worldY = gp.tileSize*42;
        i++;
        gp.monster[mapNum][i] = new GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*38;
        gp.monster[mapNum][i].worldY = gp.tileSize*42;
        i++;

        gp.monster[mapNum][i] = new GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*14;
        gp.monster[mapNum][i].worldY = gp.tileSize*31;
        i++;
        gp.monster[mapNum][i] = new GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*10;
        gp.monster[mapNum][i].worldY = gp.tileSize*33;
        i++;
        gp.monster[mapNum][i] = new GreenSlime(gp);
        gp.monster[mapNum][i].worldX = gp.tileSize*14;
        gp.monster[mapNum][i].worldY = gp.tileSize*30;
        i++;

//        mapNum = 1;
//        gp.monster[mapNum][i] = new GreenSlime(gp);
//        gp.monster[mapNum][i].worldX = gp.tileSize*38;
//        gp.monster[mapNum][i].worldY = gp.tileSize*42;
//        i++;

    }
}
