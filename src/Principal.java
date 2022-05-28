import java.awt.Dimension;

import javax.swing.JFrame;

public class Principal {

	public static final int LARGURA_TELA = 640;
	public static final int ALTURA_TELA = 480;
	
	// construtor
	public Principal() {

		// Cria��o do objeto que representa a janela da aplica��o
		JFrame janela = new JFrame("Snake Game640");
		Game game = new Game();
		// configurar os diversos aspectos da janela
		game.setPreferredSize(new Dimension(LARGURA_TELA, ALTURA_TELA));
		janela.setResizable(false); // evita o redimensionamento da janela
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		janela.setLocation(100,100); // define a posi��o de ancoragem da janela
		janela.setVisible(true); // torna a janela vis�vel na tela
		janela.getContentPane().add(game);
		janela.pack();
	}
	
	public static void main(String[] args) {
		new Principal();
	}
}
