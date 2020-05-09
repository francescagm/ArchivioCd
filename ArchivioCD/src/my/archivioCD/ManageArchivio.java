package my.archivioCD;

import util.mylib.InputDati;
import util.mylib.MyMenu;

public class ManageArchivio {
	private static final String ESTRAZIONE_CASUALE = "estrazione casuale dei contenuti  ";
	private static final String VISUALIZZA = "visualizzazione e ricerca dei contenuti";
	private static final String LIBERA_SPAZIO = "fai spazio in archivio";
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
	private final static String[] VOCI = { "Aggiorna contenuti Archivio", "ottimizza lo spazio in archivio", "visualizza contenuti musicali", "riproduzione casuale"};
			
			
	/** {@value} */
	private final static String TITOLO = "    LA MIA MUSICA :   ";
	/** {@value} */
	private static final String REINSERIRE = "voi tentare di nuovo l'inserimento ?";
	/** {@value} */
	private static final String ELIMINA = "1-->elimina per titolo\n2-->elimina per autore\n3-->Elimina tutto";
	private static final String AGGIORNA = "Aggiornamento contenuti in archivio musicale";
	private static final String[] TIPO_AGGIORNAMENTO = {"inserisci nuovo cd vuoto","inserisci nuova compilation completa","inserisci nuovo brano "};
	private static final String[] COSA_CANCELLO = {"elimina un cd ","elimina brani ","RESETTA INTERO ARCHIVIO"};
	private static final String[] MOSRA_PER = {"visualizza tutti i brani","visualizza tutti i cd","visualizza intera collezione","cerca"};  
	private static final String[] ESTRAI_PER = {"estrai un cd a caso ","estrai un brano casualmente","estrai tutto ","shuffle brani"};    
	final private static String MESS_USCITA = "Vuoi veramente uscire ?";
	private static ArchivioCd archivioMusicale = new ArchivioCd();

	MyMenu menu = new MyMenu(TITOLO, VOCI);

	public final void MainMenu() {
		boolean finito = false;
		do {
			int scelta = menu.scegli();

			switch (scelta) {
			case 1: {
				aggiornaArchivio();
				
				break;
			}
			case 2: {
				cleanArchivio();
				
				break;
			}
			case 3: {
				
				VisualizzaArchvio();
				
				
				break;
			}
			
			case 4: {
				riproduzioneCasuale();
				
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
	
	public void aggiornaArchivio() {
	MyMenu aggiornaArchvio = new MyMenu(AGGIORNA, TIPO_AGGIORNAMENTO);
    int selezione=0;
	do {
	 selezione = aggiornaArchvio.scegli();
	 switch (selezione) {
	 case 0: 
		 InputDati.yesOrNo(MESS_USCITA);
		 break;
	 case 1:
		inserisciCD();
		break;
		
	case 2:
		inserisciCdCompleto(); 
		
	case 3:
		System.out.println("inserisci un nuovo brano per aggiornare playlist esistente");
		System.out.println("scelgi un cd dalla lista:");
		inserisciBrano();
		default:
		 
		break;
	}
	   
	} while (selezione!=0);
	System.out.println("fine_aggiornamento");
	}
	
	

	public void cleanArchivio() {
		MyMenu liberaArchivio = new MyMenu(LIBERA_SPAZIO, COSA_CANCELLO);
		int scelta = 0;
		do {
	    scelta = liberaArchivio.scegli();
	    switch (scelta) {
		case 0:
			InputDati.yesOrNo("hai finito di fare spazio?");
			
			break;
		case 1:
			eliminaCd();
			
			break;
			
			
		case 2:
			eliminaBrano();
			
			
			break;
			
		case 3:{
			System.out.println("CANCELLAZIONE COMPLETA DELL ARCHVIO");
			InputDati.yesOrNo("WARNING !!! sei sicuro di voler cancellare completamente il tuo Archivio");
		    ArchivioCd sceltaDefinitiva = new ArchivioCd();
		    sceltaDefinitiva.eliminaTuttiCd();
			System.out.println("ARCHIVIO FORMATTATO");
			
			break;
		}
		default:
			
			break;
	    }
		} while (scelta !=0);
		System.out.println("fine");
		}
		
	
	
	
	
	
	public void VisualizzaArchvio() {
		MyMenu contenuti = new MyMenu(VISUALIZZA,MOSRA_PER);
		int scelta = 0;
		do {
	    scelta = contenuti.scegli();
	    switch (scelta) {
		case 0:
			InputDati.yesOrNo("hai consultato abbastanza");
			
			break;
		case 1:
			System.out.println("la tua collezione si compone dei seguenti cd:");
			visualizzaCd();
			
			break;
			
			
		case 2:
			System.out.println(" ecco la lista dei brani musicali presenti nella collezione:");
			visualizzaBrani();
			
			break;
			
		case 3:
			System.out.println("tutte le playlist:");
			visualizzaTutto();
			
			break;
		case 4:
           
			
			cerca();
			// cerca cd per titolo .... per autore brano per titolo brano per autore 
			
			break;
		
		default:
			
			break;
	    }
		} while (scelta !=0);
		System.out.println("fine");
		}
	
	
	
	public void riproduzioneCasuale() {
		MyMenu shuffle = new MyMenu(ESTRAZIONE_CASUALE,ESTRAI_PER);
		int scelta = 0;
		do {
	    scelta = shuffle.scegli();
	    switch (scelta) {
		case 0:
			InputDati.yesOrNo("hai ascoltato abbastanza");
			
			break;
		case 1:{
			System.out.println("ascolta un cd a caso");
			estraiCd();
			break;
		}
			
			
		case 2:{
			System.out.println("hai poco tempo ascolta un brano a caso");
			estraiBrano();
			
			break;
		}
		case 3:{
			System.out.println("shuffle dalla collezione");
			estraiTutto();
			
			break;
		}
//		case 4:
//           
//          // estrai piu brani da diversi cd 
//			
//			break;
//		
		default:
			
			break;
	    }
		} while (scelta !=0);
		System.out.println("fine");
		}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	private void cerca() {
		// TODO metodi di ricerca per 
		
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
		if(archivioMusicale.getArchivio()== null ||archivioMusicale.getTuttiMieiCd()==null)
			System.out.println("archivio vuoto brani non presenti impossibile procedere");
			System.out.println(" archvio vuoto non sono presenti brani da eliminare");
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
		if (braniTrovati != null &&braniTrovati.length>0) {
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
		// qui bisogna  iserire qualcosa per dire prima se non c'è il cd di inserire prima un cd 
		if (sceltaCd != -1) {
			do {
				Brano branoDaInserire = ArchivioUtils.creaBrano();
				try {
					archivioMusicale.getCd(sceltaCd).aggiungiBrano(branoDaInserire);
					System.out.println("hai inserito %s "+ branoDaInserire.belToString()+"nella playlist cd: "+archivioMusicale.getCd(sceltaCd).getTitolo());			
					InputDati.yesOrNo("inserire un nuovo brano in questa playlist?");
					while(!false) {
					archivioMusicale.getCd(sceltaCd).aggiungiBrano(ArchivioUtils.creaBrano());
					System.out.println("hai inserito : "+ branoDaInserire.belToString()+"  nella playlist cd: " +archivioMusicale.getCd(sceltaCd).getTitolo());
				}
					
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
	private void inserisciCdCompleto() {
		// TODO 
	}

}