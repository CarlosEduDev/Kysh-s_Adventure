package main;

import javax.swing.*;

public class Main {

    public static JFrame window;

    public static void main(String[] args) {

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Kysh's Adventure");
//        window.setUndecorated(true);

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        System.out.println("funcionando na outra branch");
        window.pack();

        window.setLocationRelativeTo(null); // janela vai ser exibida no centro da tela
        window.setVisible(true);

        gamePanel.setupGame();

        gamePanel.startGameThread();
    }
}
