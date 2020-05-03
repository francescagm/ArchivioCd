package it.unibs.ing.fp.archivioCD2;

public class ArchvioUtils {
	
	public final static char CD = 'c';
	public final static char BRANO_SINGOLO = 'b'; 
	
	public static boolean CDoBrano(String messaggio) {
		String mioMessaggio = messaggio + "(" + BRANO_SINGOLO  + "/" + CD + ")";
		char valoreLetto = leggiUpperChar(mioMessaggio, String.valueOf(BRANO_SINGOLO) + String.valueOf(CD));
 /// che caovlo succede 
		if (valoreLetto == BRANO_SINGOLO)
			return true;
		else
			return false;
		}

}
