package JPartitadiTennis;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.print("Benvenuti al Roland Garros, qui potete giocare a tennis, da solo o in compagnia" + '\n');
		Partita partitaditennis = new Partita(5,6);
		partitaditennis.Gioco();
		System.out.print("Partita terminata, Arrivederci" + '\n');
		}
	}

