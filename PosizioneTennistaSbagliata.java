package JPartitadiTennis;

public class PosizioneTennistaSbagliata extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	PosizioneTennistaSbagliata()
	  {
	    super("Hai inserito delle coordinate sbagliate! Riprova");
	  }

}
