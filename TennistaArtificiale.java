package JPartitadiTennis;
import java.util.Random;
import java.util.Scanner;

public class TennistaArtificiale extends Tennista{

	public TennistaArtificiale (String s){
		super(s);
	}

	public boolean Mossa(Pallina p, int dimx, int dimy){
		boolean mossa = false;
		boolean risultatotiro = this.tiro(p, dimx, dimy);
		if (risultatotiro){
			mossa = true;
			int x, y;
			Random r = new Random();
			x = r.nextInt(dimx);
			y = r.nextInt(dimy/2);
			if(this.posy >= dimy/2){
				y = y + dimy/2;
			}
			this.Muovi(x, y);
		}
		else {
			System.out.println("Peccato, punto all'avversario" + '\n');
		}
		return mossa;
	}

}