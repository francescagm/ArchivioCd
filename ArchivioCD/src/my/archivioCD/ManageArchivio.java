package my.archivioCD;

import util.mylib.InputDati;
import util.mylib.MyMenu;

public class ManageArchivio {
	private static final String IMPOSSIBILE_INSERIRE_BRANO = "spiacente nessun cd all'interno dell'archivio musicale\n impossibile inserire un brano \n inserire prima un cd ";
	private final static String[] VOCI = { "Aggiungi CD", "Aggiungi un Brano", "Rimuovi un CD", "Rimuovi un Brano",
			"Visualizza Brani", "Visualizza Cd", "Visualizza Tutto" };
	private final static String TITOLO = "ARCHIVIO CD";
	private static final String REINSERIRE = "voi tentare di nuovo l'inserimento ?";
	private static final String ELIMINA ="1--> elimina per titolo\n2-->elimina per autore" ;

	ArchivioCd archivioMusicale = new ArchivioCd();

	MyMenu menu = new MyMenu(TITOLO, VOCI);
	{

		int scelta = 0;
		scelta = menu.scegli();
		
		

		switch (scelta) {
		case 1:

			Cd nuovoCd = ArchvioUtils.creaCD();
			try {
				archivioMusicale.aggiungiCd(nuovoCd);

			} catch (IllegalArgumentException e) {
				System.out.println(e.getMessage());
				while (InputDati.yesOrNo(REINSERIRE)) {

				}

			}

			break;
		case 2:
			String[] intera_collezione = archivioMusicale.visualizzaInteraCollezione();
			if (intera_collezione != null || intera_collezione.length > 0) {
				for (int i = 0; i < intera_collezione.length; i++) {
					System.out.println(i + "-->" + intera_collezione[i]);
				}
				int sceltaCollezione = InputDati.leggiIntero("scegli il cd dove inserire il brano ", 0,
						intera_collezione.length - 1);
				archivioMusicale.getArchivio().get(sceltaCollezione).aggiungiBrano(ArchvioUtils.creaBrano());
			}else {System.out.println(IMPOSSIBILE_INSERIRE_BRANO);}
 
			break;

		case 3:
			
			break;
		case 4:

			System.out.println("riproduzione di un brano shuffe");
			break;
		default:
			break;
		}
	}
		public void eliminaCd() {
			switch (InputDati.leggiIntero(ELIMINA, 1, 2)) {
			case 1 : {
				String titoloDaEliminare = InputDati.leggiStringaNonVuota("inserisci il titolo del CD da eliminare: ");
			
				boolean haEliminato =archivioMusicale.eliminaCd(titoloDaEliminare);
				if(haEliminato) {System.out.println("cd eliminato con successo ");}
				else {System.out.println("titolo non trovato ");}
				
				break;}
			case 2 :  
			    String autoreDaEliminare = InputDati.leggiStringaNonVuota("inserisci nome autore cd da eliminare: ");
			    String [] cdPerAutore = archivioMusicale.visualizzaCdPerAutore(autoreDaEliminare);
			    if (cdPerAutore!=null||cdPerAutore.length>0) {
			    	for (int i = 0; i < cdPerAutore.length; i++) {
			    		System.out.println(i+"-->"+cdPerAutore[i]);
						}
			     System.out.println(cdPerAutore.length+"-->"+"elimina tutti");
			     int sceltaEliminazione = InputDati.leggiIntero("scelta opzione", 0, cdPerAutore.length);
			     if(sceltaEliminazione == cdPerAutore.length)
			    	 archivioMusicale.eliminaCDPerAutore(autoreDaEliminare);
			     else archivioMusicale.getArchivio().remove(sceltaEliminazione);
			     
			     
			     
			     
			    }
			    
			    
			    
//			    boolean haEliminato =archivioMusicale.eliminaCd(autoreDaEliminare);
//			    if(haEliminato) {System.out.println("cd eliminato con successo ");}
//			    else {System.out.println("titolo non trovato ");}
			
				
				
			break;

			default:
				break;
			}
		}
	
	

}