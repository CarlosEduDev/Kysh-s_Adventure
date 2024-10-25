package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TileManager {
    GamePanel gp;
    Tile[] tile;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];

        getTileImage();
    }

    // Carregar imagens dos tiles
    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/res/Background/tiles/grass00.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/res/Background/tiles/wall.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/res/Background/tiles/water00.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Desenhar um tile na tela
    public void draw(Graphics2D g2) {
        if (tile[0] != null) { // Certifique-se de que a imagem foi carregada
            g2.drawImage(tile[0].image, 0, 0, gp.tileSize, gp.tileSize, null);
        }
    }
}
