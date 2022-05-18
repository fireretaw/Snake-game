import java.awt.Color;
import java.util.Random;


/* Representa o personagem do jogo */
public class Comida { 
	public int posX;
	public int posY;
	public int tamanho;

	
	
	
	public Comida() {
		
		Random random = new Random();
		tamanho=20;
		posX = (random.nextInt(Principal.LARGURA_TELA / tamanho) * tamanho);
		posY=  (random.nextInt(Principal.ALTURA_TELA / tamanho) * tamanho);
		
		
	}
	
	
}
