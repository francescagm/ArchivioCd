package my.archivioCD;

import util.mylib.InputDati;
import util.mylib.MyMenu;

public class ManageArchivio {
	private static final String ARCHIVIO_VUOTO = "Archivio vuoto";
	/** {@value} */
	private static final String ELIMINA_TUTTI = "elimina tutti";
	/** {@value} */
	private static final String FRECCIETTA = "-->";
	/** {@value} */
	private static final String PROBLEMI_DI_ELIMINAZIONE = "problemi in fase di eliminazione, probabile perdita di dati";
	/** {@value} */
	private static final String ELIMINAZIONE_AVVENUTA = "eliminazione avvenuta ";
	/** {@value} */
	private static final String NO_CD = "spiacente nessun cd all'interno dell'archivio musicale";
	/** {@value} */
	private static final String IMPOSSIBILE_INSERIRE_BRANO = NO_CD
			+ "\n impossibile inserire un brano, inserire prima un cd ";
	/** {@value} */
	private final static String[] VOCI = { "Aggiungi CD", "Aggiungi un Brano", "Rimuovi un CD", "Rimuovi un Brano",
			"Visualizza Brani", "Visualizza Cd", "Visualizza Tutto", "Estrai un Cd Casualmente",
			"Estrai un Brano Casualmente", "Estrai tutto Casualmente" };
	/** {@value} */
	private final static String TITOLO = "ARCHIVIO CD";
	/** {@value} */
	private static final String REINSERIRE = "voi tentare di nuovo l'inserimento ?";
	/** {@value} */
	private static final String ELIMINA = "1-->elimina per titolo\n2-->elimina per autore\n3-->Elimina tutto";

	private static ArchivioCd archivioMusicale = new ArchivioCd();

	MyMenu menu = new MyMenu(TITOLO, VOCI);

	public final void MainMenu() {
		boolean finito = false;
		do {
			int scelta = menu.scegli();

			switch (scelta) {
			case 1: {
				inserisciCD();
				break;
			}
			case 2: {
				inserisciBrano();
				break;
			}
			case 3: {
				eliminaCd();
				break;
			}
			case 4: {
				this.eliminaBrano();
				break;
			}
			case 5: {
				visualizzaBrani();
				break;
			}
			case 6: {
				visualizzaCd();
				break;
			}
			case 7: {
				visualizzaTutto();
				break;
			}
			case 8: {
				estraiCd();
				break;
			}
			case 9: {
				estraiBrano();
				break;
			}
			case 10: {
				estraiTutto();
				break;
			}
			default:
				finito = true;
				break;
			}
		} while (!finito);
	}

	private void inserisciCD() {
		
		do {
			Cd nuovoCd = ArchivioUtils.creaCD();
			try {
				archivioMusicale.aggiungiCd(nuovoCd);
				break;
			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				if (!InputDati.yesOrNo(REINSERIRE))
					break;
			}
		} while (true);
	}

	private void eliminaCd() {
		System.out.println("ELIMINA CD");
		System.out.println(ELIMINA);
		switch (InputDati.leggiIntero("Scelgi dunque > ", 1, 3)) {
		case 1: {
			String titoloDaEliminare = InputDati.leggiStringaNonVuota("inserisci il titolo del CD da eliminare: ");

			boolean haEliminato = archivioMusicale.eliminaCd(titoloDaEliminare);
			if (haEliminato) {
				System.out.println("cd eliminato con successo ");
			} else {
				System.out.println("titolo non trovato ");
			}

			break;
		}
		case 2: {
			String autoreDaEliminare = InputDati.leggiStringaNonVuota("inserisci nome autore cd da eliminare: ");
			String[] cdPerAutore = archivioMusicale.visualizzaCdPerAutore(autoreDaEliminare);
			if (cdPerAutore != null && cdPerAutore.length > 0) {
				for (int i = 0; i < cdPerAutore.length; i++) {
					System.out.println(i + FRECCIETTA + cdPerAutore[i]);
				}
				System.out.println(cdPerAutore.length + FRECCIETTA + ELIMINA_TUTTI);
				int sceltaEliminazione = InputDati.leggiIntero("scelta opzione", 0, cdPerAutore.length);

				if (sceltaEliminazione == cdPerAutore.length) {
					if (archivioMusicale.eliminaCDPerAutore(autoreDaEliminare))
						System.out.println(ELIMINAZIONE_AVVENUTA);
					else
						System.out.println(PROBLEMI_DI_ELIMINAZIONE);
				} else {
					if (archivioMusicale
							.eliminaCd(archivioMusicale.cercaCDPerAutore(autoreDaEliminare).get(sceltaEliminazione)))
						System.out.println(ELIMINAZIONE_AVVENUTA);
					else
						System.out.println(PROBLEMI_DI_ELIMINAZIONE);
				}

			} else {
				System.out.println(NO_CD);
			}
			break;
		}
		case 3: {
			if (InputDati.yesOrNo("Sei sicuro di Eliminare tutti i Cd presenti nell'Archivio?"))
				if (archivioMusicale.eliminaTuttiCd())
					System.out.println(ELIMINAZIONE_AVVENUTA);
				else
					System.out.println(PROBLEMI_DI_ELIMINAZIONE);
			break;

		}

		default:
			break;
		}
	}

	private void eliminaBrano() {
		System.out.println("ELIMINA BRANO");
		int sceltaCd = scegliCd();
		if (sceltaCd != -1) {
			System.out.println("Hai scelto " + archivioMusicale.getCd(sceltaCd).belToString());
			System.out.println(ELIMINA);
			switch (InputDati.leggiIntero("Scegli dunque > ", 1, 3)) {
			case 1: {
				eliminaBranoCdTitolo(sceltaCd);
				break;
			}
			case 2: {
				eliminaBranoCdCantante(sceltaCd);
				break;
			}
			case 3: {
				if (InputDati.yesOrNo("Sei sicuro di Eliminare tutti i brani presenti in questo Cd?"))
					if (archivioMusicale.getCd(sceltaCd).eliminaTuttiBrani())
						System.out.println(ELIMINAZIONE_AVVENUTA);
					else
						System.out.println(PROBLEMI_DI_ELIMINAZIONE);
				break;
			}
			default:
				break;
			}

		}

	}

	/***/
	private int scegliCd() {
		String tuttiCD = archivioMusicale.toStringCdColletion();
		if (tuttiCD != null&&tuttiCD.length()>0) {
			System.out.println(tuttiCD);
			return InputDati.leggiIntero("Scegli il CD> ", 0, archivioMusicale.getArchivio().size() - 1);
		}
		return -1;

	}

	private void eliminaBranoCdTitolo(int sceltaCd) {
		String titoloDaEliminare = InputDati.leggiStringaNonVuota("scegli il titolo");
		String[] braniTrovati = archivioMusicale.getCd(sceltaCd).visualizzaBranoPerTitolo(titoloDaEliminare);
		if (braniTrovati != null&&braniTrovati.length>0) {
			for (int i = 0; i < braniTrovati.length; i++) {
				System.out.println(i + FRECCIETTA + braniTrovati[i]);
			}
			System.out.println(braniTrovati.length + FRECCIETTA + ELIMINA_TUTTI);
			int sceltaEliminazione = InputDati.leggiIntero("scegli dunque-> ", 0, braniTrovati.length);
			if (sceltaEliminazione == braniTrovati.length) {
				if (archivioMusicale.getCd(sceltaCd).eliminaBranoPerTitolo(titoloDaEliminare))
					System.out.println(ELIMINAZIONE_AVVENUTA);

				else
					System.out.println(PROBLEMI_DI_ELIMINAZIONE);

			} else {
				if (archivioMusicale.getCd(sceltaCd).eliminaBrano(archivioMusicale.getCd(sceltaCd)
						.cercaBranoPerTitolo(titoloDaEliminare).get(sceltaEliminazione)))
					System.out.println(ELIMINAZIONE_AVVENUTA);
				else
					System.out.println(PROBLEMI_DI_ELIMINAZIONE);
			}
		} else
			System.out.println("Nessun brano trovato con titolo \"" + titoloDaEliminare + "\"\nOPERAZIONE ANNULLATA");

	}

	private void eliminaBranoCdCantante(int sceltaCd) {
		String cantanteDaEliminare = InputDati.leggiStringaNonVuota("scegli il titolo");
		String[] braniTrovati = archivioMusicale.getCd(sceltaCd).visualizzaBranoPerCantante(cantanteDaEliminare);
		if (braniTrovati != null&&braniTrovati.length>0) {
			for (int i = 0; i < braniTrovati.length; i++) {
				System.out.println(i + FRECCIETTA + braniTrovati[i]);
			}
			System.out.println(braniTrovati.length + FRECCIETTA + ELIMINA_TUTTI);
			int sceltaEliminazione = InputDati.leggiIntero("scegli dunque-> ", 0, braniTrovati.length);
			if (sceltaEliminazione == braniTrovati.length) {
				if (archivioMusicale.getCd(sceltaCd).eliminaBranoPerCantante(cantanteDaEliminare))
					System.out.println(ELIMINAZIONE_AVVENUTA);

				else
					System.out.println(PROBLEMI_DI_ELIMINAZIONE);

			} else {
				if (archivioMusicale.getCd(sceltaCd).eliminaBrano(archivioMusicale.getCd(sceltaCd)
						.cercaBranoPerCantante(cantanteDaEliminare).get(sceltaEliminazione)))
					System.out.println(ELIMINAZIONE_AVVENUTA);
				else
					System.out.println(PROBLEMI_DI_ELIMINAZIONE);
			}
		} else
			System.out
					.println("Nessun brano trovato con cantante \"" + cantanteDaEliminare + "\"\nOPERAZIONE ANNULLATA");

	}

	private void inserisciBrano() {
		int sceltaCd = scegliCd();
		if (sceltaCd != -1) {
			do {
				Brano branoDaInserire = ArchivioUtils.creaBrano();
				try {
					archivioMusicale.getCd(sceltaCd).aggiungiBrano(branoDaInserire);
					break;
				} catch (IllegalArgumentException e) {
					System.out.println(e.getMessage());
					if (!InputDati.yesOrNo("Riprovare ad inserire un nuovo Brano in Questo Cd?"))
						break;
				}
			} while (true);

		}
		else System.out.println(ARCHIVIO_VUOTO);
	}

	private void visualizzaTutto() {
		String tutto = archivioMusicale.toStringCdBraniColletion();
		if (tutto != null&&tutto.length()>0) {
			System.out.println(tutto);
		} else
			System.out.println(ARCHIVIO_VUOTO);
	}

	private void visualizzaCd() {
		String tutto = archivioMusicale.toStringCdColletion();
		if (tutto != null&&tutto.length()>0) {
			System.out.println(tutto);
		} else
			System.out.println(ARCHIVIO_VUOTO);

	}

	private void visualizzaBrani() {
		int scelta = scegliCd();
		if (scelta != -1) {
			String output=archivioMusicale.getCd(scelta).toStringCollection();
			if(output==null)
			System.out.println("vuoto");
			else System.out.println(output);
		} else
			System.out.println(ARCHIVIO_VUOTO);
	}

	private void estraiCd() {
		Cd estratto = archivioMusicale.cdCasuale();
		if (estratto != null) {

			System.out.println(estratto.toStringCollection());
		} else
			System.out.println(ARCHIVIO_VUOTO);
	}

	private void estraiBrano() {
		int scelta = scegliCd();
		if (scelta != -1) {
			Brano estratto = archivioMusicale.getCd(scelta).branoCasuale();
			if (estratto != null) {
				System.out.println(estratto.belToString());
			} else
				System.out.println("Cd vuoto");
		} else
			System.out.println(ARCHIVIO_VUOTO);
	}

	public void estraiTutto() {
		Cd cdEstratto = null;
		boolean finito = false;
		byte count=0;
		do {
			cdEstratto = archivioMusicale.cdCasuale();
			if (cdEstratto == null)
				break;
			if (cdEstratto.getDurataCd() != 0)
				finito = true;
			count++;
		} while (!finito&&count<3);

		if (cdEstratto != null) {
			Brano estratto = cdEstratto.branoCasuale();
			if (estratto != null) {
				System.out.println(cdEstratto.belToString());
				System.out.println(estratto.belToString());

			} else
				System.out.println("Tutti i Cd sono vuoti");
		} else
			System.out.println(ARCHIVIO_VUOTO);
	}

}