package JPartitadiTennis;


public class Pallina {
	
	//Attributi
	
	private int posx; 
	
	private int posy; 
	
	//Metodi
	
	public Pallina (){ 
	}

	public int getPosx() { //restituisce posx
		return posx;
	}



	public void setPosx(int posx) { //imposta posx
		this.posx = posx;
	}



	public int getPosy() { //restituisce posy
		return posy;
	}



	public void setPosy(int posy) { //imposta posy
		this.posy = posy;
	}
	
	
	public void spostaPallina(int x, int y) { //imposta sia la posx che la posy
		this.setPosx(x);
		this.setPosy(y);
	}

}
