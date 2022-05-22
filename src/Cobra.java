import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/* Representa o personagem do jogo */
public class Cobra {
	public BufferedImage cobra_baixo, cobra_cima, cobra_direita, cobra_esquerda, cobra_corpo;
	public int posX;
	public int posY;
	public int tamanho;
	public int velX;
	public int velY;

	public Cobra() {

		// determina a dimensão e posição da cabeça
		tamanho = 20;
		try {
			cobra_baixo = ImageIO.read(getClass().getResource("imgs/cobra_baixo.png"));
			cobra_cima = ImageIO.read(getClass().getResource("imgs/cobra_cima.png"));
			cobra_direita = ImageIO.read(getClass().getResource("imgs/cobra_direita.png"));
			cobra_esquerda = ImageIO.read(getClass().getResource("imgs/cobra_esquerda.png"));
			cobra_corpo = ImageIO.read(getClass().getResource("imgs/cobra_corpo.png"));
		} catch (Exception e) {
			System.out.println("Erro ao carregar a imagem!");
		}

	}

}
