import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/* Representa ch�o do jogo */
public class Solo { 
	public BufferedImage imgSolo;
	int posX, posY;
	
	public Solo() {
		//determina posi��o
		posX=0;
		posY=0;
		
		
		// faz o carregamento do sprite
		try {
			imgSolo = ImageIO.read(getClass().getResource("/imgs/ground.png"));
		}catch(Exception e) {
			e.printStackTrace();
		}
	
	}
}