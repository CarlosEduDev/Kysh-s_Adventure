package entity;

import main.GamePanel;
import object.*;

public class NPC_Merchant extends Entity{

    public NPC_Merchant(GamePanel gp) {
        super(gp);

        direction = "down";
        speed = 0;

        getImage();
        setDialogue();
        setItems();
    }
    public void getImage() {
        up1 = setup("/res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        up2 = setup("/res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        down1 = setup("/res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("/res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("/res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        left2 = setup("/res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
        right1 = setup("/res/npc/merchant_down_1", gp.tileSize, gp.tileSize);
        right2 = setup("/res/npc/merchant_down_2", gp.tileSize, gp.tileSize);
    }

    public void setDialogue(){
        dialogues[0] = "Seja bem-vindo(a) à loja do krung\nDeseja alguma coisa?";
    }

    public void setItems(){
        inventory.add(new OBJ_Potion_Red(gp));
        inventory.add(new OBJ_Sword_Wood(gp));
        inventory.add(new OBJ_Sword_Normal(gp));
        inventory.add(new OBJ_Axe(gp));
        inventory.add(new OBJ_Shield_Wood(gp));
        inventory.add(new OBJ_Blue_Shield(gp));
    }

    public void speak(){
        super.speak();
        gp.gameState = gp.tradeState;
        gp.ui.npc = this;
    }
}
