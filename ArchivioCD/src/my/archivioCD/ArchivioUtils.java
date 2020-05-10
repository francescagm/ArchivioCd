package my.archivioCD;

import java.util.ArrayList;

import util.mylib.InputDati;

public class ArchivioUtils {
	
	private final static String frame = "********************************************************";
	private final static String MESS_GOODBYE = "\n ARRIVEDERCI \n  ";
	private final static String MESS_WELCOME = "        BENVENUTO IN ARCHIVIO MUSICALE\n      organizza e gestisci le tue raccolte";

	public static Brano creaBrano() {
		return new Brano(InputDati.leggiStringaNonVuota("Inserisci il titolo del Brano "),
				InputDati.leggiStringaNonVuota("Inserisci il cantante "),
				InputDati.leggiIntero("Inserisci i minuti della durata del Brano ", 0, 59),
				InputDati.leggiIntero("Inserisci i secondi della durata del Brano ", 1, 59));
	}

	public static Cd creaCD() {
		return new Cd(InputDati.leggiStringaNonVuota("Inserisci il titolo del CD "),
				InputDati.leggiStringaNonVuota("Inserisci il nome dell'autore "));
	}

	public static String[] visualizzaUnArrayListBrani(ArrayList<Brano> arrayDaVisuliazzare) {
		String[] fine=new String[arrayDaVisuliazzare.size()];
		for (int i=0;i<arrayDaVisuliazzare.size();i++) {
			fine[i]=arrayDaVisuliazzare.get(i).belToString();
		}
		return fine;
	}
	public static String[] visualizzaUnArrayListCd(ArrayList<Cd> arrayDaVisuliazzare) {
		String[] fine=new String[arrayDaVisuliazzare.size()];
		for (int i=0;i<arrayDaVisuliazzare.size();i++) {
			fine[i]=arrayDaVisuliazzare.get(i).belToString();
		}
		return fine;
	}
	/**
	* Prints welcome message
	*/
	public static void printWelcome() {
		System.out.println(frame);
		System.out.println(MESS_WELCOME);
		System.out.println(frame);
	}

	/**
	 * Prints a goodbye message
	 */
	public static void printGoodbye() {
		System.out.println(frame);
		System.out.println(MESS_GOODBYE);
		System.out.println(frame);
}

}
