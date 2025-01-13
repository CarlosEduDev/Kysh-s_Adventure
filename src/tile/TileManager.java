package tile;

import main.GamePanel;
import main.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    public Tile[] tile;
    public int[][] mapTilenum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[20];

        // Inicializando o mapa com base nas dimensões do mundo
        mapTilenum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/res/maps/world01.txt");
    }

    // Carregar imagens dos tiles
    public void getTileImage() {
            setup(0, "grass01",false);
            setup(1, "wall",true);
            setup(2, "water01",true);
            setup(3, "earth",false);
            setup(4, "tree",true);
            setup(5, "road00",false);

    }

    public void setup(int index, String imageName, boolean collition){
        UtilityTool uTool = new UtilityTool();

        try{
            tile[index] = new Tile();
            tile[index].image = ImageIO.read(getClass().getResourceAsStream("/res/Background/tiles/" + imageName + ".png"));
            tile[index].image = uTool.scaleImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collition = collition;

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    // Carregar o mapa a partir de um arquivo de texto
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int worldCol = 0;
            int worldRow = 0;

            while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
                String line = br.readLine();

                while (worldCol < gp.maxWorldCol) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[worldCol]);
                    mapTilenum[worldCol][worldRow] = num;
                    worldCol++;
                }

                if (worldCol == gp.maxWorldCol) {
                    worldCol = 0;
                    worldRow++;
                }
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Erro: o arquivo de mapa não foi encontrado.");
        }
    }

    // Desenhar apenas os tiles visíveis na tela
    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTilenum[worldCol][worldRow];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;

            // Desenhar apenas se o tile estiver visível na tela
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {

                g2.drawImage(tile[tileNum].image, screenX, screenY, null);
            }

            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
