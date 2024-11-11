package main;

import entity.Entity;

public class CollitionChecker {

    GamePanel gp;
    public CollitionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkTile(Entity entity){
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.tileSize;
        int entityRightCol = entityRightWorldX/gp.tileSize;
        int entityTopRow = entityTopWorldY/gp.tileSize;
        int entityBottomRow = entityBottomWorldY/gp.tileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTilenum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTilenum[entityRightCol][entityTopRow];
                if(gp.tileM.tile[tileNum1].collition == true || gp.tileM.tile[tileNum2].collition == true){
                    entity.collitionOn = true;
            }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTilenum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileM.mapTilenum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collition == true || gp.tileM.tile[tileNum2].collition == true){
                    entity.collitionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTilenum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileM.mapTilenum[entityLeftCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collition == true || gp.tileM.tile[tileNum2].collition == true){
                    entity.collitionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/gp.tileSize;
                tileNum1 = gp.tileM.mapTilenum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileM.mapTilenum[entityRightCol][entityBottomRow];
                if(gp.tileM.tile[tileNum1].collition == true || gp.tileM.tile[tileNum2].collition == true){
                    entity.collitionOn = true;
                }
                break;
        }
    }
}
