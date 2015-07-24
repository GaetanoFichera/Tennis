package JPartitadiTennis;
import java.util.Random;

public abstract class Tennista {
	
	protected String nome;
	protected int punteggio;
	protected int set;
	protected int posx;
	protected int posy;

	public Tennista (String s){
		
		nome = s;
		punteggio = 0;
		set = 0;
	}
	
	public String getNome (){
		
		return nome;
	}
	
	public void setNome (String s){
		
		this.nome = s;
	}
	
	public int getPunteggio (){
		
		return punteggio;
	}
	
	public void setPunteggio (int i){
		
		this.punteggio = i;
	}
	
	public int getSet(){
		
		return set;
	}
	
	public void setSet (int i){
		
		this.set = i;
	}
	
	public int getPosx(){
		
		return this.posx;
	}
	
	public void setPosx (int i){
		
		this.posx = i;
	} 
	
	public int getPosy(){
		
		return this.posy;
	}
	
	public void setPosy (int i){
		
		this.posy = i;
	}
	
	public String getPunteggioTennis (){
		
		String punteggioreale;
		
		switch (this.punteggio){
		case 0: punteggioreale = "0";
				break;
		case 1: punteggioreale = "15";
				break;
		case 2: punteggioreale = "30";
				break;
		case 3: punteggioreale = "40";
				break;
		default:
				Integer punto = this.punteggio-3; 
				punteggioreale = punto.toString() + "° Vantaggio";
		}
		
		return punteggioreale;
		
	}
	
	protected void Muovi (int x, int y){
		
			this.setPosx(x);
			this.setPosy(y);
	}
	
	private boolean raggiungibilita(Pallina p){
		
		boolean ragg = false;
		if((p.getPosy() == this.posy && Math.abs(p.getPosx()-this.posx) <= 1) || 
				(p.getPosx() == this.posx && Math.abs(p.getPosy()-this.posy) <= 1)){
			ragg = true;
		}
		return ragg;
		
	}
	
	protected boolean tiro(Pallina p, int dimx, int dimy){
		
		boolean risultatotiro = false;
		if (this.raggiungibilita(p)){
			System.out.println("Il tuo giocatore ha effettuato il tiro!" + '\n');
			Random r= new Random();
			int possfav = dimx * dimy/2 + 1;
			int possfuori = dimx/2 + dimy/2;
			int possrete = dimx/2 + 1;
			int q = r.nextInt(possfav + possrete + possfuori);
			if (q < possfav){ //tiri buoni
				
				System.out.println("Palla nel campo avversario");
				Random r1 = new Random();
				int x = r1.nextInt(dimx); 
				Random r2 = new Random();
				int y = r2.nextInt(dimy/2);
				if (p.getPosy() < dimy/2){
					y = y + dimy/2;
				}
				p.spostaPallina(x, y);
				risultatotiro = true;
			}
			else if (q < possfav + possfuori){ //tiri fuori
				System.out.println("Palla fuori dal campo");
			}
			else { //tiri sulla rete
				System.out.println("Palla sulla rete");
				}
		}
		else { //palla non raggiungibile
			System.out.println("Palla non raggiungibile");
		}
		return risultatotiro;
	}

	/* Tabella equivalenza punteggi: 0 = 0
	 * 								 1 = 15
	 * 								 2 = 30 
	 * 								 3 = 40
	 *                     			 4 = Vittoria oppure 1° Vantaggio
	 *                    			 5 = 2° Vantaggio
	 * 								 etc...
	 */
	
	public abstract boolean Mossa(Pallina p, int dimx, int dimy);
}
