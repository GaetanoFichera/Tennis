package JPartitadiTennis;
import java.util.*;

import javax.swing.JOptionPane;


public class Partita {
	
	// Attributi
	
	private Tennista Giocatore1;
	
	private Tennista Giocatore2;
	
	private Pallina PallinaTennis;
	
	private int CampoDimx;
	
	private int CampoDimy;
	
	// Metodi

	public int getCampoDimy() {
		return CampoDimy;
	}

	public void setCampoDimy(int campoDimy) {
		CampoDimy = campoDimy;
	}

	public int getCampoDimx() {
		return CampoDimx;
	}

	public void setCampoDimx(int campoDimx) {
		CampoDimx = campoDimx;
	}

	public Partita (int x, int y){
		
		CampoDimx = x;
		CampoDimy = y;
		
		Scanner in = new Scanner(System.in);
		int n;
		do{
			System.out.print("Quanti utenti siete? ");
			n = in.nextInt();
			switch (n){
				case 0: {Tennista u1 = new TennistaArtificiale("Computer 1");
						 this.Giocatore1 = u1;
						 Tennista u2 = new TennistaArtificiale("Computer 2");
						 this.Giocatore2 = u2;
						}
						break;
				case 1:	{String n1 = JOptionPane.showInputDialog("Utente 1 inserisci il tuo nickname: ");
				 		 Tennista u1 = new TennistaReale(n1);
				 		 this.Giocatore1 = u1;
				 		 Tennista u2 = new TennistaArtificiale("Computer 2");
				 		 this.Giocatore2 = u2;}
						break;
				case 2: {String n1 = JOptionPane.showInputDialog("Utente 1 inserisci il tuo nickname: ");
				 		 Tennista u1 = new TennistaReale(n1);
				 		 this.Giocatore1 = u1;
				 		 String n2 = JOptionPane.showInputDialog("Utente 2 inserisci il tuo nickname: ");
				 		 Tennista u2 = new TennistaReale(n2);
				 		 this.Giocatore2 = u2;}
						break;
				default: System.out.print("ERRORE: Il numero di utenti deve essere incluso tra 0 e 2" + '\n');
						 break;
			}
		}while(n > 2);
		PallinaTennis = new Pallina(); 
	}
	
	public void Gioco (){

		int numeroset = 1;
		while (this.Giocatore1.getSet()!=this.Giocatore2.getSet()+2 && this.Giocatore1.getSet()+2!=this.Giocatore2.getSet()){
			
			Set(numeroset);
			numeroset++;
		}
		
		if(this.Giocatore1.getSet() == this.Giocatore2.getSet()+2){
			
			System.out.print("Il Giocatore: "  + this.Giocatore1.getNome() + " ha vinto la partita! Complimenti." + '\n');
		}
		else {
			System.out.print("Il Giocatore: "  + this.Giocatore2.getNome() + " ha vinto la partita! Complimenti" + '\n');
		}
	}

	private void Set (int n){
		
		System.out.print("Inizio " + n + "° set" + '\n');
		
		this.Giocatore1.setPosx(this.CampoDimx/2);
		this.Giocatore1.setPosy(0);
		this.Giocatore2.setPosx(this.CampoDimx/2);
		this.Giocatore2.setPosy(this.CampoDimy-1);
		
		Tennista gprimo, gsecondo;
		if (n%2==1){ //scelta di chi deve battere ogni set e per chi riceve il servizio
			
			gprimo = this.Giocatore1;
			gsecondo = this.Giocatore2;
			
			}
		else {
			
			gprimo = this.Giocatore2;
			gsecondo = this.Giocatore1;
			
		}
		
		System.out.print("Battuta iniziale assegnata a: " + gprimo.getNome() + '\n');
		
		while (!((this.Giocatore1.getPunteggio() > 3 && this.Giocatore1.getPunteggio() >= this.Giocatore2.getPunteggio()+2) || 
				 (this.Giocatore2.getPunteggio() > 3 && this.Giocatore2.getPunteggio() >= this.Giocatore1.getPunteggio()+2))){
			
			System.out.print("I punti dei giocatori sono: " + '\n' + 
					 this.Giocatore1.getNome() + ": " + this.Giocatore1.getPunteggioTennis() + '\n' + 
					 this.Giocatore2.getNome() + ": " + this.Giocatore2.getPunteggioTennis() + '\n' + 
					 "Palla assegnata a " + gprimo.getNome() + " per la battuta" + '\n');
			Turno(gprimo, gsecondo);
		}
		
		if(this.Giocatore1.getPunteggio() > this.Giocatore2.getPunteggio()){
			
			this.Giocatore1.setSet(this.Giocatore1.getSet()+1);
			System.out.print("Il Giocatore: "  + this.Giocatore1.getNome() + " ha vinto il " + n + "° set" + '\n');
		}else {
			this.Giocatore2.setSet(this.Giocatore2.getSet()+1);
			System.out.print("Il Giocatore: "  + this.Giocatore2.getNome() + " ha vinto il " + n + "° set" + '\n');
		}
		
		this.Giocatore1.setPunteggio(0);
		this.Giocatore2.setPunteggio(0);
	}
	
	private void Turno (Tennista g1, Tennista g2){

		this.PallinaTennis.spostaPallina(g1.getPosx(), g1.getPosy());
		boolean puntoassegnato = false;
		boolean azione = false;
		while (!puntoassegnato){
			StampaCampo();
			System.out.print("Tocca al giocatore G1: " + g1.getNome() + '\n');
			azione = g1.Mossa(this.PallinaTennis,this.CampoDimx, this.CampoDimy);
			if (azione){
				StampaCampo();
				System.out.print("Tocca al giocatore G2: " + g2.getNome() + '\n');
				azione = g2.Mossa(this.PallinaTennis,this.CampoDimx, this.CampoDimy);
				if (!azione){
					g1.setPunteggio(g1.getPunteggio()+1);
					System.out.print("Punto assegnato al giocatore: " + g1.getNome() + '\n');
					puntoassegnato = true;
				}
			}else{
				g2.setPunteggio(g2.getPunteggio()+1);
				System.out.print("Punto assegnato al giocatore: " + g2.getNome() + '\n');
				puntoassegnato = true;
			}
		}
	}
	
	private void StampaCampo(){
		
		System.out.print("   ");
		for(int m = 0; m < this.CampoDimx; m++){
			System.out.print("  " + m + "  ");
		}
		System.out.print('\n' + "     ");
		
		for(int q = 0; q < this.CampoDimx-1; q++){
			System.out.print("_____");
		}
		System.out.print("____" + '\n');
		
		for(int j = 0; j < this.CampoDimy; j++){
			if(j == this.CampoDimy/2) {
				System.out.print("    ");
				for(int n = 0; n < this.CampoDimx; n++){
					System.out.print("-----");
				}
				System.out.print("-" + '\n');
			}
			System.out.print(j + "   ");
			for(int i = 0; i < this.CampoDimx; i++){
				if(i == this.Giocatore1.getPosx() && j == this.Giocatore1.getPosy() && 
				   i == this.PallinaTennis.getPosx() && j == this.PallinaTennis.getPosy()){
				   System.out.print("|G1-P");
				}else if(i == this.Giocatore2.getPosx() && j == this.Giocatore2.getPosy() &&
						 i == this.PallinaTennis.getPosx() && j == this.PallinaTennis.getPosy()){
						 System.out.print("|G2-P");
					  }else if(i == this.Giocatore1.getPosx() && j == this.Giocatore1.getPosy()){
							   System.out.print("| G1 ");
							}else if(i == this.Giocatore2.getPosx() && j == this.Giocatore2.getPosy()){
									 System.out.print("| G2 ");
								  }else if(i == this.PallinaTennis.getPosx() && j == this.PallinaTennis.getPosy()){
									  	   System.out.print("| P  ");
								  		}else {
								  			System.out.print("|____");
								  		}
			}	
			System.out.print("|" + '\n');
		}
		System.out.print('\n');
	}
}

/*
     0    1    2    3    4
    ________________________
0  |____|____| T1 |____|____|
1  |____|____|____|____|____|
2  |____|____|____|____|____|
   --------------------------
3  |____|____|____|____|____|
4  |____|____|____| P  |____|
5  |____|____| T2 |____|____|

*/