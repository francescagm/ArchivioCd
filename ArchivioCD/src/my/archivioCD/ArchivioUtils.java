package my.archivioCD;

import util.mylib.InputDati;

public class ArchivioUtils {

	public static Brano creaBrano() {
		return new Brano(InputDati.leggiStringaNonVuota("Inserisci il titolo del Brano "),
				InputDati.leggiStringaNonVuota("Inserisci il cantante "),
				InputDati.leggiIntero("Inserisci i minuti della durata del Brano ", 0, 59),
				InputDati.leggiIntero("Inserisci i secondi della durata del Brano ", 0, 59));
	}

	public static Cd creaCD() {
		return new Cd(InputDati.leggiStringaNonVuota("Inserisci il titolo del CD "),
				InputDati.leggiStringaNonVuota("Inserisci il nome dell'autore"));
	}
}
