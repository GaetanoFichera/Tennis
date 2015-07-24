package JPartitadiTennis;
import java.util.Scanner;


public class TennistaReale extends Tennista{

	public TennistaReale (String s){
			super(s);
		}
	
	public boolean Mossa(Pallina p, int dimx, int dimy){
		boolean mossa = false;
		boolean risultatotiro = this.tiro(p, dimx, dimy);
		if (risultatotiro){
			mossa = true;
			System.out.println("In attesa della ricezione, dove vuoi spostarti? " + '\n');
			Scanner in = new Scanner(System.in);
			int x, y;
			boolean errore = false;
			do{
				errore = false;
				try{
					System.out.println("Inserisci la coordinata x: " + '\n');
					x = in.nextInt();
					System.out.println("Inserisci la coordinata y: " + '\n');
					y = in.nextInt();
					if((x>dimx-1 || y>dimy-1) || 
					   (this.posy<dimy/2 && y>=dimy/2) || 
					   (this.posy>=dimy/2 && y<dimy/2)){
							throw new PosizioneTennistaSbagliata();
					}else{
						this.Muovi(x, y);
					}
				}
				catch(PosizioneTennistaSbagliata e){
					System.out.print("ERRORE: " + e + '\n');
					errore = true;
				}
			}while(errore); 
		}
		else {
			System.out.println("Peccato, punto all'avversario" + '\n');
		}
		return mossa;
	}

}
