package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int[][] mapTilenum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];

        // Inicializando o mapa com base nas dimensões da tela
        mapTilenum = new int[gp.maxScreenColm][gp.maxScreenRow];

        getTileImage();
        loadMap();
    }

    // Carregar imagens dos tiles
    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/Background/tiles/grass01.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/Background/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/Background/tiles/water01.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Carregar o mapa a partir de um arquivo de texto
    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/res/maps/map01.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < gp.maxScreenColm && row < gp.maxScreenRow) {
                String line = br.readLine();

                while (col < gp.maxScreenColm) {
                    String[] numbers = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTilenum[col][row] = num;
                    col++;
                }

                if (col == gp.maxScreenColm) {
                    col = 0;
                    row++;
                }
            }
            br.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.out.println("Erro: o arquivo de mapa não foi encontrado.");
        }
    }

    // Desenhar o mapa na tela
    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.maxScreenColm && row < gp.maxScreenRow) {
            int tileNum = mapTilenum[col][row];

            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;

            if (col == gp.maxScreenColm) {
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
