import java.util.Random;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/* Representa o personagem do jogo */
public class Comida {
	public BufferedImage maca;
	public int posX;
	public int posY;
	public int tamanho;

	public Comida() {
		//criada fun��o a qual far� carregamento da img
		try {
			maca = ImageIO.read(getClass().getResource("imgs/maca.png"));
		} catch (Exception e) { //caso haja algum erro ao carregar imagem
			System.out.println("Erro ao carregar imagem da ma�a");
		}

		tamanho = 20;

	}
	
	//fun��o para gerar posi��o aleat�ria para a ma�a 
	public void gerarComida() {
		Random random = new Random();
		posX = (random.nextInt(Principal.LARGURA_TELA / tamanho) * tamanho);
		posY = (random.nextInt(Principal.ALTURA_TELA / tamanho) * tamanho);
	}

}
