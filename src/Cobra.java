import java.awt. image.BufferedImage;
import javax.imageio.ImageIO;

/* Representa o personagem do jogo */
public class Cobra {
	public BufferedImage cobra_baixo, cobra_cima, cobra_direita, cobra_esquerda, cauda_cima, cauda_baixo, cauda_esquerda, cauda_direita, corpo_cobra;
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
			cauda_cima = ImageIO.read(getClass().getResource("imgs/cauda_cima.png"));
			cauda_baixo = ImageIO.read(getClass().getResource("imgs/cauda_baixo.png"));
			cauda_direita = ImageIO.read(getClass().getResource("imgs/cauda_direita.png"));
			cauda_esquerda = ImageIO.read(getClass().getResource("imgs/cauda_esquerda.png"));
			corpo_cobra = ImageIO.read(getClass().getResource("imgs/corpo_cobra.png"));
			
		} catch (Exception e) {
			System.out.println("Erro ao carregar a imagem!");
		}

	}

	public BufferedImage obterImg() {
		if (velY < 0) { // move-se para a cima
			return cobra_cima;
		} else if (velY > 0) { //move-se para a baixo
			return cobra_baixo;
		}
		if (velX < 0) { // move-se para a esquerda
			return cobra_esquerda;
		} else {
			return cobra_direita;
		}

	}
	
	
	public BufferedImage obterImgCauda() {
		if (velY < 0) { // move-se para a cima
			return cauda_cima;
		} else if (velY > 0) { //move-se para a baixo
			return cauda_baixo;
		}
		if (velX < 0) { // move-se para a esquerda
			return cauda_esquerda;
		} else {
			return cauda_direita;
		}

	}
}
