package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gp;

    public boolean  upPressed, downPressed, leftPressed, rightPressed, enterPressed, shotKeyPressed;
    //DEBUG
    boolean checkDebug = false;

    public KeyHandler(GamePanel gp){
        this.gp = gp;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // TITLE STATE
        if(gp.gameState == gp.titleState){
            titleState(code);
        }
        // PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            pauseState(code);
        }

        // PLAY STATE
        else if(gp.gameState == gp.playState){
            playState(code);
            }
        // CHARACTER STATE
        else if(gp.gameState == gp.characterState){
            characterState(code);
        }
        // DIALOGUE STATE
        else if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }
        // OPTIONS STATE
        else if(gp.gameState == gp.optionsState){
            optionsState(code);
        }

        // GAME OVER STATE
        else if(gp.gameState == gp.gameOverState){
            gameOverState(code);
        }

        // TRADE STATE
        else if(gp.gameState == gp.tradeState){
            tradeState(code);
        }
    }

    private void tradeState(int code) {
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        if(gp.ui.subState == 0){
            if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
                gp.ui.commandNum--;
                if(gp.ui.commandNum < 0){
                    gp.ui.commandNum = 2;
                }
                gp.playSoundEff(10);
            }
            if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
                gp.ui.commandNum++;
                if(gp.ui.commandNum > 2){
                    gp.ui.commandNum = 0;
                }
                gp.playSoundEff(10);
            }
        }

        if(gp.ui.subState == 1){
            npcInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }

        if(gp.ui.subState == 2){
            playerInventory(code);
            if(code == KeyEvent.VK_ESCAPE){
                gp.ui.subState = 0;
            }
        }
    }

    private void gameOverState(int code) {
        if(code == KeyEvent.VK_W){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = 1;
            }
            gp.playSoundEff(10);
        }
        if(code == KeyEvent.VK_S){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 1){
                gp.ui.commandNum = 0;
            }
            gp.playSoundEff(10);
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.retry();
                gp.playMusic(1);
            }
            else if(gp.ui.commandNum == 1){
                gp.gameState = gp.titleState;
                gp.restart();
            }
        }
    }

    public void titleState(int code){
        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            gp.ui.commandNum--;
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = 2;
            }
        }

        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            gp.ui.commandNum++;
            if(gp.ui.commandNum > 2){
                gp.ui.commandNum = 0;
            }
        }

        if(code == KeyEvent.VK_ENTER){
            if(gp.ui.commandNum == 0){
                gp.gameState = gp.playState;
                gp.playMusic(1);
            }
            if(gp.ui.commandNum == 1){
                // add dps
            }
            if(gp.ui.commandNum == 2){
                System.exit(0);
            }
        }
    }

    public void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }

        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }

        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }

        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState;
            gp.stopMusic();
        }
        if (code == KeyEvent.VK_I) {
            gp.gameState = gp.characterState;
        }
        if (code == KeyEvent.VK_ENTER) {
            enterPressed = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            gp.gameState = gp.optionsState;
//            gp.stopMusic();
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = true;
        }

        // DEBUG
        if (code == KeyEvent.VK_T) {
            if (checkDebug == false) {
                checkDebug = true;
            } else if (checkDebug == true) {
                checkDebug = false;
            }
        }
        if (code == KeyEvent.VK_R){
            switch (gp.currentMap){
                case 0: gp.tileM.loadMap("/res/maps/world02.txt", 0); break;
                case 1: gp.tileM.loadMap("/res/maps/interior.txt",1); break;
            }
            System.out.println("mudar mapa");
        }
    }

    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }

    public void dialogueState(int code){
        if(code == KeyEvent.VK_ENTER){
            gp.gameState = gp.playState;
        }
    }

    public void characterState(int code){
        if(code == KeyEvent.VK_I || code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }

        if(code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }
        playerInventory(code);
    }

    public void playerInventory(int code){
        if(code == KeyEvent.VK_W){
            if(gp.ui.playerSlotRow != 0){
                gp.ui.playerSlotRow--;
                gp.playSoundEff(10);

            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.playerSlotCol != 0){
                gp.ui.playerSlotCol--;
                gp.playSoundEff(10);
            }
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.playerSlotRow != 3){
                gp.ui.playerSlotRow++;
                gp.playSoundEff(10);
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.playerSlotCol != 4){
                gp.ui.playerSlotCol++;
                gp.playSoundEff(10);
            }
        }
    }

    public void npcInventory(int code){
        if(code == KeyEvent.VK_W){
            if(gp.ui.npcSlotRow != 0){
                gp.ui.npcSlotRow--;
                gp.playSoundEff(10);

            }
        }
        if(code == KeyEvent.VK_A){
            if(gp.ui.npcSlotCol != 0){
                gp.ui.npcSlotCol--;
                gp.playSoundEff(10);
            }
        }
        if(code == KeyEvent.VK_S){
            if(gp.ui.npcSlotRow != 3){
                gp.ui.npcSlotRow++;
                gp.playSoundEff(10);
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.ui.npcSlotCol != 4){
                gp.ui.npcSlotCol++;
                gp.playSoundEff(10);
            }
        }
    }

    public void optionsState(int code) {
        if(code == KeyEvent.VK_ESCAPE){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_ENTER){
            enterPressed = true;
        }

        int maxCommandNum = 0;
        switch (gp.ui.subState){
            case 0: maxCommandNum = 5; break;
            case 3: maxCommandNum = 1; break;
        }

        if(code == KeyEvent.VK_W || code == KeyEvent.VK_UP){
            gp.ui.commandNum--;
            gp.playSoundEff(10);
            if(gp.ui.commandNum < 0){
                gp.ui.commandNum = maxCommandNum;
            }
        }
        if(code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN){
            gp.ui.commandNum++;
            gp.playSoundEff(10);
            if(gp.ui.commandNum > maxCommandNum){
                gp.ui.commandNum = 0;
            }
        }

        if(code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT){
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 1 && gp.music.volumeScale > 0){
                    gp.music.volumeScale--;
                    gp.music.checkVolume();
                    gp.playSoundEff(10);
                }
                if(gp.ui.commandNum == 2 && gp.soundEf.volumeScale > 0){
                    gp.soundEf.volumeScale--;
                    gp.playSoundEff(10);
                }
            }
        }

        if(code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT){
            if(gp.ui.subState == 0){
                if(gp.ui.commandNum == 1 && gp.music.volumeScale < 5){
                    gp.music.volumeScale++;
                    gp.music.checkVolume();
                    gp.playSoundEff(10);
                }
                if(gp.ui.commandNum == 2 && gp.soundEf.volumeScale < 5){
                    gp.soundEf.volumeScale++;
                    gp.playSoundEff(10);
                }
            }
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPressed = false;
        }

        if(code == KeyEvent.VK_S){
            downPressed = false;
        }

        if(code == KeyEvent.VK_A){
            leftPressed = false;
        }

        if(code == KeyEvent.VK_D){
            rightPressed = false;
        }
        if (code == KeyEvent.VK_F) {
            shotKeyPressed = false;
        }
    }
}
