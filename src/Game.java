import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

public class Game extends JPanel {

	// ATRIBUTOS ---------------------------------------------------------
	public Cobra[] cobra;
	public Comida comida;
	public Solo solo;
	public int tamanhoCobra = 2;
	public int tamanhoMax = (Principal.ALTURA_TELA * Principal.LARGURA_TELA) / (20 * 20);
	public boolean rodando = true;
	// os estados de pressionamento das teclas direcionais
	public boolean k_cima = false;
	public boolean k_baixo = false;
	public boolean k_direita = false;
	public boolean k_esquerda = false;

	// CONSTRUTOR ---------------------------------------------------------
	public Game() {
		// adiciona o escutador de eventos de pressionamento do teclado

		addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) { // escutador de pressionamento de tecla
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP: // tecla cima
					k_cima = true;
					break;
				case KeyEvent.VK_DOWN: // tecla baixo
					k_baixo = true;
					break;
				case KeyEvent.VK_LEFT: // tecla esquerda
					k_esquerda = true;
					break;
				case KeyEvent.VK_RIGHT: // tecla direita
					k_direita = true;
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) { // escutados da soltura de tecla
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP: // tecla cima
					k_cima = false;
					break;
				case KeyEvent.VK_DOWN: // tecla baixo
					k_baixo = false;
					break;
				case KeyEvent.VK_LEFT: // tecla esquerda
					k_esquerda = false;
					break;
				case KeyEvent.VK_RIGHT: // tecla direita
					k_direita = false;
					break;
				}
			}

		});

		cobra = new Cobra[tamanhoMax];
		comida = new Comida();
		comida.gerarComida();
		solo = new Solo();

		for (int i = 0; i < tamanhoCobra; i++) {
			cobra[i] = new Cobra();
		}
		cobra[0].posX = 20;
		cobra[0].velX = 20;

		setFocusable(true); // para poder tratar eventos
		setLayout(null);

		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				gameloop(); // dispara o gameloop do jogo
			}
		});
		thread.start();
	}

	// GAMELOOP ---------------------------------------------------------
	public void gameloop() {
		while (rodando) { // cada repeti��o � um quadro do jogo
			handlerEvent();
			update();
			render();

			try {// dando uma pause de 17 milisegundos (60FPS)
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public void handlerEvent() {
		// checar (a cada quadro) os pressionamentos das teclas
		// e, com base nisso, modificar as vari�veis de velocidade

		if (k_cima == true) { // quando a tecla
			if (cobra[0].velY > 0) {
			} else {
				cobra[0].velY = -20;
				cobra[0].velX = 0;
			}

		} else if (k_baixo == true) { // tecla para baixo pressionada
			if (cobra[0].velY < 0) {
			} else {
				cobra[0].velY = 20;
				cobra[0].velX = 0;
			}

		} else if (k_esquerda == true) { // somente a tecla para esquerda pressionada
			if (cobra[0].velX > 0) {
			} else {
				cobra[0].velX = -20;
				cobra[0].velY = 0;
			}

		} else if (k_direita == true) { // somente a tecla para direita pressionada
			if (cobra[0].velX < 0) {
			} else {
				cobra[0].velX = 20;
				cobra[0].velY = 0;
			}
		}

	}

	public void update() {
		// movimenta o corpo da cobra
		if (cobra[0].velX == 0 && cobra[0].velY == 0) {
		} else {
			for (int i = tamanhoCobra - 1; i > 0; i--) {
				cobra[i].posX = cobra[i - 1].posX;
				cobra[i].posY = cobra[i - 1].posY;

			}
		}
		// movimenta a cabe�a da cobra
		cobra[0].posX = cobra[0].posX + cobra[0].velX;
		cobra[0].posY = cobra[0].posY + cobra[0].velY;

		testeColisao();
	}

	public void render() {
		repaint(); // for�ar o redesenho da tela
	}

	// M�TODOS ---------------------------------------------------------
	public void testeColisao() {

		// colis�o com a comida
		if (cobra[0].posX == comida.posX && cobra[0].posY == comida.posY) {

			tamanhoCobra++;
			// instancia a nova parte da cobra;
			cobra[tamanhoCobra - 1] = new Cobra();
			// passa posi��o da penultima parte da cobra para a ultima
			cobra[tamanhoCobra - 1].posX = cobra[tamanhoCobra - 2].posX;
			cobra[tamanhoCobra - 1].posY = cobra[tamanhoCobra - 2].posY;

			comida.gerarComida(); // chama fun��o para gerar a comida aleatoria

			// impedir que a comida surja na posi��o da cobra
			for (int i = 0; i < tamanhoCobra; i++) {
				if (cobra[i].posX == comida.posX && cobra[i].posY == comida.posY) {
					comida.gerarComida();
				}
			}

		}

		// colis�o da bola com o lado direito da tela
		if (cobra[0].posX + (cobra[0].tamanho) > Principal.LARGURA_TELA) {
			cobra[0].posX = cobra[1].posX;
			cobra[1].posX = cobra[1].posX - cobra[1].tamanho;// desfaz o movimento horizontal
			rodando = false;
		}
		// colis�o da bola com o lado esquerdo da tela
		if (cobra[0].posX < 0) {
			cobra[0].posX = 0;// desfaz o movimento horizontal
			cobra[1].posX = cobra[1].tamanho;
			rodando = false;
		}

		// colis�o da bola com o lado inferior da tela
		if (cobra[0].posY + (cobra[0].tamanho) > Principal.ALTURA_TELA) {
			cobra[0].posY = cobra[1].posY;
			cobra[1].posY = cobra[1].posY - cobra[1].tamanho;// desfaz o movimento vertical
			rodando = false;
		}
		// colis�o da bola com o lado superior da tela
		if (cobra[0].posY < 0) {
			cobra[0].posY = 0; // desfaz o movimento vertical
			cobra[1].posY = cobra[1].tamanho;
			rodando = false;

		}

		// colis�o da cobra com o corpo
		for (int i = tamanhoCobra - 1; i > 0; i--) {
			if (cobra[0].posX == cobra[i].posX && cobra[0].posY == cobra[i].posY) {
				cobra[0].posY = cobra[1].posY;
				cobra[0].posX = cobra[1].posX;
				cobra[1].posY = cobra[2].posY;
				cobra[1].posX = cobra[2].posX;
				rodando = false;
			}
		}
	}

	// M�TODOS ESPECIAIS ---------------------------------------------------------

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		// desenhar os elementos na tela
		setBackground(Color.LIGHT_GRAY);
		g.drawImage(solo.imgSolo, solo.posX, solo.posY, null);
		g.drawImage(comida.maca, comida.posX, comida.posY, null);

		// desenha o personagem na tela

		for (int i = 0; i < tamanhoCobra; i++) {
			if (i == 0) {
				g.drawImage(cobra[0].obterImg(), cobra[0].posX, cobra[0].posY, null);
			} else {
				g.drawImage(cobra[i].cobra_corpo, cobra[i].posX, cobra[i].posY, null);
			}

		}

	}
}