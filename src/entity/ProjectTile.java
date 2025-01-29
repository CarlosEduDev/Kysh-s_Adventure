package entity;

import main.GamePanel;

public class ProjectTile extends Entity{

    Entity user;

    public ProjectTile(GamePanel gp) {
        super(gp);
    }

    public void set(int worldX, int worldY, String direction, boolean alive, Entity user){
        this.worldX = worldX;
        this.worldY = worldY;
        this.alive = alive;
        this.direction = direction;
        this.user = user;
        this.life = this.maxLife;
    }
    public void update(){

            if(user == gp.player){
                int monsterIndex = gp.collitionCh.checkEntity(this, gp.monster);
                if(monsterIndex != 999){
                    gp.player.damageMonster(monsterIndex, attack);
                    generateParticle(user.projectTile, gp.monster[monsterIndex]);
                    alive = false;
                }
            }
            if(user != gp.player){
                boolean contactPlayer = gp.collitionCh.checkPlayer(this);

                if(gp.player.invincible == false && contactPlayer == true){
                    damagePlayer(attack);
                    generateParticle(user.projectTile, gp.player);
                    alive = false;
                }
            }

        switch (direction){
            case "up": worldY -= speed; break;
            case "down": worldY += speed; break;
            case "left": worldX -= speed; break;
            case "right": worldX += speed; break;
        }

        life--;
        if(life <= 0){
            alive = false;
        }

        spriteCounter++;
        if(spriteCounter > 12){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }

    public boolean haveResource(Entity user){
        boolean haveResource = false;

        return haveResource;
    }

    public void subtractResource(Entity user){
    }
}
