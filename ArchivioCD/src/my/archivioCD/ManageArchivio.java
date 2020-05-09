package my.archivioCD;

import java.util.ArrayList;

import util.mylib.InputDati;
import util.mylib.MyMenu;

public class ManageArchivio {

	private static final String CD_IN_ARCHIVIO = "Cd in Archivio";
	private static final String RICERCA_ANNULLATA = "Ricerca annullata";
	private static final String VUOI_RIPROVARE = "Vuoi riprovare?";
	private static final String INSERISCI_AUTORE = "Inserisci autore da cercare";
	private static final String INSERISCI_TITOLO = "Inserisci titolo da cercare";
	private static final String ARCHIVIO_VUOTO = "Archivio vuoto";
	private static final String INSERISCI_CANTANTE = "Inserisci cantante da cercare";

	private final static String[] voci = { "Gestisci Archivio", "Visualizza contenuti", "Shuffle", };

	private final static MyMenu menu = new MyMenu("ARCHIVIO CD", voci);

	private final static ArchivioCd archivioMusicale = new ArchivioCd();

	private final static String[] eliminaCD = { "Elimina per scelta", "Elimina per titolo", "Elimina per autore",
			"Elimina tutto" };
	private final static String[] eliminaBrano = { "Elimina per scelta", "Elimina per titolo", "Elimina per cantante",
			"Elimina tutto" };
	private final static String[] aggiungiInArchivio = { "Gestisci Cd", "Gestisci Brani","FORMATTA ARCHIVIO" };
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
				// TODO
				break;
			case 3:

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
			//TODO
			break;
		default:
			break;
		}
	}

	/** Inserimento di un nuovo brano */
	private static void inserimentoBrano() {
		if (archivioMusicale.getNumeroCd() != 0) {
			String[] cdInArchivio = archivioMusicale.visualizzaInteraCollezioneCD();
			final int sceltaCdNP = cdInArchivio.length;
			String[] strDaVisu = new String[sceltaCdNP + 1];
			for (int i = 0; i < cdInArchivio.length; i++) {
				strDaVisu[i] = cdInArchivio[i];
			}
			strDaVisu[sceltaCdNP] = "Cd non presente";
			MyMenu menu = new MyMenu(CD_IN_ARCHIVIO, strDaVisu);
			int scelta = menu.scegli();
			if (scelta == sceltaCdNP + 1) {
				System.out.println("Inserisci allora un nuovo Cd");
				inserisciCd();
				return;
			}
			switch (scelta) {
			case 0:
				System.out.println(ManageArchivio.RICERCA_ANNULLATA);
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
			Brano daInserire = ArchivioUtils.creaBrano();
			try {
				archivioMusicale.getCd(posCd).aggiungiBrano(daInserire);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				if (!InputDati.yesOrNo(VUOI_RIPROVARE))
					break;
			}
		} while (true);
	}

	/** Cerca un Cd per un autore inserito in console */
	private static Cd cercaCdPerAutore() {

		Cd cdcercato = null;
		String autoreDaCercare = InputDati.leggiStringaNonVuota(INSERISCI_AUTORE);
		ArrayList<Cd> cdTrovati = archivioMusicale.cercaCDPerAutore(autoreDaCercare);
		if (cdTrovati.isEmpty())
			System.out.println("Nessun Cd di " + autoreDaCercare + " trovato");
		else {
			MyMenu sottoMenu = new MyMenu("Cd trovati", ArchivioUtils.visualizzaUnArrayListCd(cdTrovati));
			int scelta = sottoMenu.scegli();
			switch (scelta) {
			case 0:
				System.out.println(RICERCA_ANNULLATA);
				break;

			default:
				cdcercato = cdTrovati.get(scelta - 1);
				break;
			}
		}
		return cdcercato;

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
				System.out.println(e.getMessage());
				if (!InputDati.yesOrNo(VUOI_RIPROVARE))
					return;
			}
		} while (true);

		int pos = archivioMusicale.cercaPosizioneCd(daInserire);
		while (InputDati.yesOrNo("Vuoi inserire un nuovo brano?")) {
			ManageArchivio.inserisciBrano(pos);
		}
		System.out.println("Hai inserito\n" + archivioMusicale.getCd(pos).toStringCollection());
	}

	private static void eliminaCD() {
		if (archivioMusicale.getNumeroCd() != 0) {
			MyMenu menu = new MyMenu("ELIMINAZIONE CD", ManageArchivio.eliminaCD);
			switch (menu.scegli()) {
			case 1: {
				eliminaCdScelta();
				break;
			}
			case 2: {
				String titoloCdDaEliminare = InputDati.leggiStringaNonVuota(INSERISCI_TITOLO);
				Cd cdDaEliminare = archivioMusicale.cercaCDPerTitolo(titoloCdDaEliminare);
				if (archivioMusicale.eliminaCd(cdDaEliminare))
					System.out.println("Hai eliminato\n->" + cdDaEliminare.toStringCollection());
				else {
					System.out.println("Nessun Cd con titolo " + titoloCdDaEliminare + " trovato");
				}
			}
			case 3://TODO
			default:
				break;
			}
		} else
			System.out.println(ARCHIVIO_VUOTO);
	}

	private static void eliminaCdScelta() {
		MyMenu sottomenu = new MyMenu(ManageArchivio.eliminaCD[0],
				archivioMusicale.visualizzaInteraCollezioneCdBrani());
		int scelta = sottomenu.scegli();
		switch (scelta) {
		case 0:
			return;
		default:
			archivioMusicale.eliminaCd(archivioMusicale.getCd(scelta - 1));
			break;
		}
	}
}