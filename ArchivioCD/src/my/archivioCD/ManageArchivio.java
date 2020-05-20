package my.archivioCD;

import java.util.ArrayList;

import util.mylib.InputDati;
import util.mylib.MyMenu;

public class ManageArchivio {

	private static final String INSERISCI_ALLORA_UN_NUOVO_CD = "\nInserisci allora un nuovo Cd";
	private static final String CONTENUTI_ESIGUI = "\nPer eseguire lo shuffle devi arricchire la collezione"
			.toUpperCase();
	private static final String SHUFFLE = "Shuffle";
	private static final String ARCHIVIO_VUOTO = "\nArchivio vuoto ".toUpperCase();
	private static final String BRANI_TROVATI = "Brani trovati ";
	private static final String CD_IN_ARCHIVIO = "Cd in Archivio ";
	private static final String ELIMINARE_TUTTI_I_BRANI = "Eliminare tutti i brani ";
	private static final String ELIMINAZIONE_ANNULLATA = "Eliminazione annullata ";
	private static final String ELIMINAZIONE_AVVENUTA = "ELIMINAZIONE AVVENUTA ";
	private static final String HAI_ELIMINATO = "Hai eliminato ";
	private static final String INSERIMENTO_BRANO_CD_NON_PRESENTE = "Cd su cui inserire il brano non e' presente";
	private static final String SEI_SICURO_DI = "Sei sicuro di ";
	private static final String PROBLEMI_NELL_ELIMINAZIONE = "PROBLEMI NELL'ELIMINAZIONE ";
	private static final String VISUALIZZAZIONE_ANNULLATA = "Visualizzazione annullata ";
	private static final String IMPOSSIBILE_ELIMINARE_BRANI = "Impossibile eliminare brani";
	private static final String RICERCA_ANNULLATA = "Ricerca annullata ";
	private static final String VUOI_RIPROVARE = "Vuoi riprovare? ";
	private static final String INSERISCI_AUTORE = "Inserisci autore da cercare ";
	private static final String INSERISCI_TITOLO = "Inserisci titolo da cercare ";
	private static final String INSERISCI_CANTANTE = "Inserisci cantante da cercare ";

	private final static String[] voci = { "Gestisci Archivio", "Visualizza contenuti", "Shuffle", };

	private final static MyMenu menu = new MyMenu("ARCHIVIO CD", voci);
	private final static String[] estrai = { "Estrai casualmente un brano da ogni Cd",
			"Estrai un brano casualmente da un cd casuale", "Estrai un brano da un Cd scelto" };
	private final static String[] cerca = { "Visualizza archivio", "Visualizza solo i Cd",
			"Visualizza i brani di un Cd" };
	private final static ArchivioCd archivioMusicale = new ArchivioCd();

	private final static String[] eliminaCD = { "Elimina per scelta", "Elimina per titolo", "Elimina per autore", };
	private final static String[] eliminaBrano = { "Elimina per scelta", "Elimina per titolo", "Elimina per cantante",
			"Elimina tutti i Brani sul Cd" };

	private final static String[] aggiungiInArchivio = { "Gestisci Cd", "Gestisci Brani", "FORMATTA ARCHIVIO" };
	private final static String[] gestisciBrani = { "Inserisci Brano", "Elimina Brano" };
	private final static String[] gestisciCd = { "Inserisci Cd", "Elimina Cd", };

	/** No costruttore */
	private ManageArchivio() {
	}

	/** Metodo per usare il Manager */
	public static void startManager() {
		ManageArchivio.mainMenu();
	}

	/**
	 * Menu' principale
	 * 
	 * @see #aggiornaArchivio()
	 */
	private static void mainMenu() {
		boolean finito = false;
		do {
			switch (menu.scegli()) {
			case 1:
				ManageArchivio.aggiornaArchivio();
				break;

			case 2:
				ManageArchivio.visualizza();
				break;
			case 3:
				estrazioneCasuale();
				break;

			case 0:
				finito = true;
				break;
			default:
				System.out.println("ERRORE");
				break;
			}

		} while (!finito);
	}

	/** menu aggiunta ed eliminazione di cd/brani */
	private static void aggiornaArchivio() {
		final MyMenu menu = new MyMenu("AGGIUNGI IN ARCHIVIO", aggiungiInArchivio);
		switch (menu.scegli()) {
		case 1:
			ManageArchivio.gestisciCd();
			break;
		case 2:
			ManageArchivio.gesticiBrani();
			break;
		case 3:
			if (InputDati.yesOrNo("Sei sicuro di eliminare tutti i Cd dall'archivio?"))
				if (archivioMusicale.eliminaTuttiCd())
					System.out.println("Hai formattato l'archivio");
				else
					System.out.println(ARCHIVIO_VUOTO + " impossibile eliminare Cd");

			break;
		default:
			break;
		}
	}

	/** menu' gestione CD */
	private static void gestisciCd() {
		final MyMenu menu = new MyMenu("GESTISCI CD", ManageArchivio.gestisciCd);
		switch (menu.scegli()) {
		case 1:
			ManageArchivio.inserisciCd();
			break;
		case 2: {
			ManageArchivio.eliminaCD();
		}
		default:
			break;
		}
	}

	/** menu' gestione brani */
	private static void gesticiBrani() {
		final MyMenu menu = new MyMenu("GESTISCI BRANI", ManageArchivio.gestisciBrani);
		switch (menu.scegli()) {
		case 1:
			ManageArchivio.inserimentoBrano();
			break;
		case 2:
			int scelta = ManageArchivio.sceltaCd("Su quale Cd?", IMPOSSIBILE_ELIMINARE_BRANI);
			if (scelta < 0)
				return;
			else
				eliminaBrano(scelta);
			break;
		default:
			break;
		}
	}

	/** Inserimento di un nuovo brano */
	private static void inserimentoBrano() {
		if (archivioMusicale.getNumeroCd() != 0) {
			String[] cdInArchivio = archivioMusicale.visualizzaInteraCollezioneCD();
			String[] strDaVisu = new String[cdInArchivio.length + 1];
			for (int i = 0; i < cdInArchivio.length; i++) {
				strDaVisu[i] = cdInArchivio[i];
			}
			strDaVisu[cdInArchivio.length] = INSERIMENTO_BRANO_CD_NON_PRESENTE;
			MyMenu menu = new MyMenu(CD_IN_ARCHIVIO, strDaVisu);
			int scelta = menu.scegli();
			if (scelta == strDaVisu.length) {
				System.out.println(INSERISCI_ALLORA_UN_NUOVO_CD);
				inserisciCd();
				return;
			}
			switch (scelta) {
			case 0:
				System.out.println(RICERCA_ANNULLATA);
				return;

			default:
				ManageArchivio.inserisciBrano(scelta - 1);
				break;
			}
		} else {
			System.out.println(ARCHIVIO_VUOTO);
			if (InputDati.yesOrNo("Vuoi inserire un Cd?"))
				inserisciCd();
		}
	}

	/** inserisce un nuovo brano nel Cd in quella posizione in archivio */
	private static void inserisciBrano(int posCd) {
		do {
			try {
				Brano daInserire = ArchivioUtils.creaBrano();
				archivioMusicale.getCd(posCd).aggiungiBrano(daInserire);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println("\n"+e.getMessage());
				if (!InputDati.yesOrNo(VUOI_RIPROVARE))
					break;
			}
		} while (true);
	}

	/** Inserisce un nuovo Cd in Archivio */
	private static void inserisciCd() {
		Cd daInserire = null;
		do {
			daInserire = ArchivioUtils.creaCD();
			try {
				archivioMusicale.aggiungiCd(daInserire);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println("\n"+e.getMessage());
				if (!InputDati.yesOrNo(VUOI_RIPROVARE))
					return;
				System.out.println();
			}
		} while (true);

		int pos = archivioMusicale.cercaPosizioneCd(daInserire);
		while (InputDati.yesOrNo("\nVuoi inserire un nuovo brano?")) {
			ManageArchivio.inserisciBrano(pos);
		}
		System.out.println("Hai inserito\n" + archivioMusicale.getCd(pos).toStringCollection());
	}

	/** Menu di eliminazione CD */
	private static void eliminaCD() {
		if (archivioMusicale.getNumeroCd() != 0) {
			MyMenu menu = new MyMenu("ELIMINAZIONE CD", ManageArchivio.eliminaCD);
			switch (menu.scegli()) {
			case 1: {
				ManageArchivio.eliminaCdScelta();
				break;
			}
			case 2: {
				String titoloCdDaEliminare = InputDati.leggiStringaNonVuota(INSERISCI_TITOLO);
				Cd cdDaEliminare = archivioMusicale.cercaCDPerTitolo(titoloCdDaEliminare);
				if (archivioMusicale.eliminaCd(cdDaEliminare))
					System.out.println(HAI_ELIMINATO + "\n->" + cdDaEliminare.toStringCollection());
				else {
					System.out.println("Nessun Cd con titolo " + titoloCdDaEliminare + " trovato");
				}
				break;
			}
			case 3: {
				ManageArchivio.eliminaCdPerAutore();
				break;
			}
			default:
				System.out.println(ELIMINAZIONE_ANNULLATA);
				break;
			}
		} else
			System.out.println(ARCHIVIO_VUOTO);
	}

	/** ELimina un CD da uno scelti */
	private static void eliminaCdScelta() {
		MyMenu sottomenu = new MyMenu(ManageArchivio.eliminaCD[0],
				archivioMusicale.visualizzaInteraCollezioneCdBrani());
		int scelta = sottomenu.scegli();
		switch (scelta) {
		case 0:
			System.out.println(ELIMINAZIONE_ANNULLATA);
			return;
		default:
			if (archivioMusicale.eliminaCd(archivioMusicale.getCd(scelta - 1)))
				System.out.println(ELIMINAZIONE_AVVENUTA);
			else
				System.out.println(PROBLEMI_NELL_ELIMINAZIONE);
			break;
		}
	}

	/** Elimina un Cd per un autore inserito in console */
	private static void eliminaCdPerAutore() {

		Cd cdcercato = null;
		String autoreDaCercare = InputDati.leggiStringaNonVuota(INSERISCI_AUTORE);
		ArrayList<Cd> cdTrovati = archivioMusicale.cercaCDPerAutore(autoreDaCercare);
		if (cdTrovati.isEmpty())
			System.out.println("Nessun Cd di " + autoreDaCercare + " trovato");
		else {
			String[] cdStringTrovati = ArchivioUtils.visualizzaUnArrayListCd(cdTrovati);
			String[] stringDaVisu = new String[cdStringTrovati.length + 1];
			for (int i = 0; i < cdStringTrovati.length; i++) {
				stringDaVisu[i] = cdStringTrovati[i];
			}
			stringDaVisu[cdStringTrovati.length] = "Eliminare tutti i cd trovati";
			MyMenu sottoMenu = new MyMenu("Cd trovati", stringDaVisu);
			int scelta = sottoMenu.scegli();
			if (scelta == stringDaVisu.length) {
				if (InputDati.yesOrNo(SEI_SICURO_DI + stringDaVisu[cdStringTrovati.length] + "?")) {
					archivioMusicale.eliminaCDPerAutore(autoreDaCercare);
					return;
				} else
					scelta = 0;
			}
			switch (scelta) {
			case 0:
				System.out.println(ELIMINAZIONE_ANNULLATA);
				break;

			default:
				cdcercato = cdTrovati.get(scelta - 1);
				break;
			}
			if (archivioMusicale.eliminaCd(cdcercato))
				System.out.println(HAI_ELIMINATO + cdcercato.toStringCollection());
			else {
				System.out.println(PROBLEMI_NELL_ELIMINAZIONE + " di " + cdcercato.belToString());
			}
		}
	}

	/**
	 * menu con la possibilita' di scegliere un cd tra quelli elencati
	 * 
	 * @return la posizione nell'arraylist di {@link #archivioMusicale}, -1 o -2 se
	 *         impossibile scegliere o scelta annullata
	 */
	private static int sceltaCd(String titoloMenu, String impossibile) {
		if (archivioMusicale.getNumeroCd() != 0) {
			MyMenu scegliCd = new MyMenu(titoloMenu, archivioMusicale.visualizzaInteraCollezioneCD());
			int scelta = scegliCd.scegli();
			switch (scelta) {
			case 0:
				System.out.println(RICERCA_ANNULLATA);
				return -1;

			default:
				if (archivioMusicale.getCd(scelta - 1).getNumeroBrani() != 0)
					return scelta - 1;
				else
					System.out.println("Cd vuoto," + impossibile);
				return -2;
			}
		} else
			System.out.println(ARCHIVIO_VUOTO + impossibile);
		return -2;
	}

	/** menu che gestisce l'eliminazione di un o piu' brano */
	private static void eliminaBrano(int cdDoveEliminare) {
		MyMenu comeEliminare = new MyMenu(
				"Come eliminare Brano in " + archivioMusicale.getCd(cdDoveEliminare).getTitolo(),
				ManageArchivio.eliminaBrano);
		switch (comeEliminare.scegli()) {
		case 1:
			eliminaBranoPerScelta(cdDoveEliminare);
			break;
		case 2:
			eliminaBranoPerTitolo(cdDoveEliminare);
			break;
		case 3:
			eliminaBranoPerCantante(cdDoveEliminare);
			break;

		case 4:
			ManageArchivio.eliminaTuttiBrani(cdDoveEliminare);
			break;
		default:
			System.out.println(ELIMINAZIONE_ANNULLATA);
			break;
		}
	}

	/** elimina un brano per scelta */
	private static void eliminaBranoPerScelta(int cdDoveEliminare) {
		MyMenu braniInCd = new MyMenu("Scegli il brano",
				archivioMusicale.getCd(cdDoveEliminare).visualizzaInteraCollezione());
		int scelta = braniInCd.scegli();
		switch (scelta) {
		case 0:
			System.out.println(ManageArchivio.ELIMINAZIONE_ANNULLATA);
			break;
		default:
			Brano branoDaEliminare = archivioMusicale.getCd(cdDoveEliminare).getBrano(scelta - 1);
			if (archivioMusicale.getCd(cdDoveEliminare).eliminaBrano(branoDaEliminare))
				System.out.println(HAI_ELIMINATO + branoDaEliminare.belToString());
			else
				System.out.println(PROBLEMI_NELL_ELIMINAZIONE + " di " + branoDaEliminare.belToString());
			break;
		}
	}

	/** Elimina un brano di un titolo */
	private static void eliminaBranoPerTitolo(int cdDoveEliminare) {
		Brano branoDaEliminare = null;
		String titoloDaEliminare = InputDati.leggiStringaNonVuota(INSERISCI_TITOLO + " del brano da cercare");
		ArrayList<Brano> braniTrovati = archivioMusicale.getCd(cdDoveEliminare).cercaBranoPerTitolo(titoloDaEliminare);
		if (braniTrovati.isEmpty()) {
			System.out.println("Nessun brano con titolo " + titoloDaEliminare + " trovato");
		} else {
			String[] braniStringTrovati = ArchivioUtils.visualizzaUnArrayListBrani(braniTrovati);
			String[] stringDaVisu = new String[braniStringTrovati.length + 1];
			for (int i = 0; i < braniStringTrovati.length; i++) {
				stringDaVisu[i] = braniStringTrovati[i];
			}
			stringDaVisu[braniStringTrovati.length] = ELIMINARE_TUTTI_I_BRANI + "trovati";
			MyMenu sottoMenu = new MyMenu(BRANI_TROVATI, stringDaVisu);
			int scelta = sottoMenu.scegli();
			if (scelta == stringDaVisu.length) {
				if (InputDati.yesOrNo(SEI_SICURO_DI + stringDaVisu[braniStringTrovati.length] + "?")) {
					archivioMusicale.getCd(cdDoveEliminare).eliminaBranoPerTitolo(titoloDaEliminare);
					return;
				} else
					scelta = 0;
			}
			switch (scelta) {
			case 0:
				System.out.println(ELIMINAZIONE_ANNULLATA);
				break;

			default:
				branoDaEliminare = braniTrovati.get(scelta - 1);
				break;
			}
			if (archivioMusicale.getCd(cdDoveEliminare).eliminaBrano(branoDaEliminare))
				System.out.println(HAI_ELIMINATO + branoDaEliminare.belToString());
			else {
				System.out.println(PROBLEMI_NELL_ELIMINAZIONE + " di " + branoDaEliminare.belToString());
			}
		}
	}

	/** Elimina i brani di un cantante */
	private static void eliminaBranoPerCantante(int cdDoveEliminare) {
		Brano branoDaEliminare = null;
		String cantanteDaEliminare = InputDati.leggiStringaNonVuota(INSERISCI_CANTANTE + " del brano da cercare");
		ArrayList<Brano> braniTrovati = archivioMusicale.getCd(cdDoveEliminare)
				.cercaBranoPerCantante(cantanteDaEliminare);
		if (braniTrovati.isEmpty()) {
			System.out.println("Nessun brano con cantante " + cantanteDaEliminare + " trovato");
		} else {
			String[] braniStringTrovati = ArchivioUtils.visualizzaUnArrayListBrani(braniTrovati);
			String[] stringDaVisu = new String[braniStringTrovati.length + 1];
			for (int i = 0; i < braniStringTrovati.length; i++) {
				stringDaVisu[i] = braniStringTrovati[i];
			}
			stringDaVisu[braniStringTrovati.length] = ELIMINARE_TUTTI_I_BRANI + "trovati";
			MyMenu sottoMenu = new MyMenu(BRANI_TROVATI, stringDaVisu);
			int scelta = sottoMenu.scegli();
			if (scelta == stringDaVisu.length) {
				if (InputDati.yesOrNo(SEI_SICURO_DI + stringDaVisu[braniStringTrovati.length] + "?")) {
					archivioMusicale.getCd(cdDoveEliminare).eliminaBranoPerCantante(cantanteDaEliminare);
					return;
				} else
					scelta = 0;
			}
			switch (scelta) {
			case 0:
				System.out.println(ELIMINAZIONE_ANNULLATA);
				break;

			default:
				branoDaEliminare = braniTrovati.get(scelta - 1);
				break;
			}
			if (archivioMusicale.getCd(cdDoveEliminare).eliminaBrano(branoDaEliminare))
				System.out.println(HAI_ELIMINATO + branoDaEliminare.belToString());
			else {
				System.out.println(PROBLEMI_NELL_ELIMINAZIONE + " di " + branoDaEliminare.belToString());
			}
		}
	}

	/** Eliminazione di tutti i brani */
	private static void eliminaTuttiBrani(int cdDoveEliminare) {
		String cd = archivioMusicale.getCd(cdDoveEliminare).getCodice();
		if (InputDati.yesOrNo(SEI_SICURO_DI + ELIMINARE_TUTTI_I_BRANI + " in " + cd + "?")) {
			if (archivioMusicale.getCd(cdDoveEliminare).eliminaTuttiBrani())
				System.out.println(HAI_ELIMINATO + " tutti i brani in " + cd);
			else
				System.out.println(PROBLEMI_NELL_ELIMINAZIONE);
		} else
			System.out.println(ManageArchivio.ELIMINAZIONE_ANNULLATA);
	}

	/** Menu visualizza */
	private static void visualizza() {
		MyMenu menu = new MyMenu("VISUALIZZA ARCHIVIO", cerca);
		switch (menu.scegli()) {
		case 0:
			System.out.println(VISUALIZZAZIONE_ANNULLATA);
			return;
		case 1:
			System.out.println(archivioMusicale.toStringCdBraniColletion());
			break;
		case 2:
			System.out.println(archivioMusicale.toStringCdColletion());
			break;
		case 3:
			int scelta = sceltaCd("Scegli il CD", "impossibile visualizzare alcun brano");
			if (scelta < 0)
				return;
			else {
				System.out.println(archivioMusicale.getCd(scelta).toStringCollection());
			}
			break;
		default:
			break;

		}
	}

	/** Menu estrazioni casuali */

	private static void estrazioneCasuale() {

		MyMenu menu = new MyMenu(SHUFFLE, estrai);
		switch (menu.scegli()) {
		case 1:

			estraiBrani();
			break;
		case 2:
			estraiBrano();
			break;
		case 3:
			estraiCdeBrano();

			break;
		default:
			break;

		}
	}

	/** estrae un brano da una compilation da un cd casualmente */
	private static void estraiCdeBrano() {
		Cd cdEstratto = null;
		boolean finito = false;
		byte count = 0;
		do {
			cdEstratto = archivioMusicale.cdCasuale();
			if (cdEstratto == null)
				break;
			if (cdEstratto.getDurataCd() != 0)
				finito = true;
			count++;
		} while (!finito && count < 3);

		if (cdEstratto != null) {
			Brano estratto = cdEstratto.branoCasuale();
			if (estratto != null) {
				System.out.println(cdEstratto.belToString());

			} else
				System.out.println("Tutti i Cd sono vuoti");
		} else
			System.out.println(ARCHIVIO_VUOTO);
	}

	/** estrae un brano da un cd scelto */
	private static void estraiBrano() {
		int scelta = sceltaCd(CD_IN_ARCHIVIO, "");
		if (scelta >= 0) {
			Brano estratto = archivioMusicale.getCd(scelta).branoCasuale();
			System.out.println(estratto.belToString());
		}
	}

	/** estrae un brano casualmente da ogni cd presente nella collezione */
	private static void estraiBrani() {
		StringBuilder shuffle = new StringBuilder();
		int numCD = archivioMusicale.getNumeroCd();
		if (numCD != 0) {
			for (int i = 0; i < numCD; i++) {
				Cd cdEstratto = archivioMusicale.getCd(i);
				if (cdEstratto.getNumeroBrani() != 0)
					shuffle.append(
							"In " + cdEstratto.getTitolo() + " ho estratto " + cdEstratto.branoCasuale().belToString());
				else
					shuffle.append("In " + cdEstratto.getTitolo() + " nessun brano presente\n");

			}
			System.out.println(shuffle);
		} else {
			System.out.println(CONTENUTI_ESIGUI);

		}
	}
}
