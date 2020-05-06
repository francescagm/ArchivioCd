package my.archivioCD;

import java.util.ArrayList;

import util.mylib.EstrazioniCasuali;

 public class ArchivioCd {
   
	private ArrayList<Cd> tuttiMieiCd;
		public ArchivioCd () {
			tuttiMieiCd=new ArrayList<>();
			tuttiMieiCd.clear();
		}
			public void aggiungiCd(Cd cdDaInserire) {
			if(contiene(cdDaInserire.getTitolo()))
				throw new IllegalArgumentException("Nome del CD già esistente! Impossibile immeterlo");
			tuttiMieiCd.add(cdDaInserire);
		}
			/**
			 * <b>Elimina</b> un {@linkplain Cd} cercandolo per <b>titolo</b>
			 * 
			 * @return <tt>true</tt> se la eliminazione e' andata a buon fine,
			 *         <tt>false</tt> altrimenti
			 * @param titolo
			 *            e' il <b>titolo</b> da ricercare
			 *            @author Simone
			 */
		public boolean eliminaCds(ArrayList<Cd> cdDaEliminare) {// ma qui non ho capito li elimina tutti? non capisco in (cercaCd(cd)) che ricerca fa per brano
			int cdAllInizio= tuttiMieiCd.size();
			for (Cd cd: cdDaEliminare) {
				tuttiMieiCd.remove(cercaCd(cd));				
			}
			return (cdAllInizio-cdDaEliminare.size()==tuttiMieiCd.size());
		}
	
		
		
			/**
			 * <b>Elimina</b> un {@linkplain Cd} cercandolo per <b>titolo</b>
			 * 
			 * @return <tt>true</tt> se la eliminazione e' andata a buon fine,
			 *         <tt>false</tt> altrimenti
			 * @param titolo
			 *            e' il <b>titolo</b> da ricercare
			 * @author Simone			 */	
			
		
		public boolean eliminaCd(String titolo) {
			return eliminaCds(cercaCDPerTitolo(titolo));
		}

		/**
		 * <b>Elimina</b> un {@linkplain Cd} cercandolo per <b>cantante</b>
		 * 
		 * @return <tt>true</tt> se la eliminazione e' andata a buon fine,
		 *         <tt>false</tt> altrimenti
		 * @param cantante
		 *            e' il <b>cantante</b> da ricercare
		 *            @author Simone
		 */
		public boolean eliminaCDPerAutore(String cantante) {  // perchè qui passa un cantante  qui è autore giusto ? 
			return eliminaCds(cercaCDPerAutore(cantante));
		}
		/**
		 * <b>Cerca </b>un {@linkplain Cd} tra {@linkplain #tuttiMieiCd}
		 * 
		 * @return la <b>posizione </b>del {@linkplain Cd} cercato
		 * @param brano
		 *            e' il {@linkplain Brano} da cercare
		 *            @author Simone
		 */
		
		public int cercaCd(Cd cdDaCercare) {
			for (Cd cd : tuttiMieiCd) {
				if (cd.equals(cdDaCercare)) {
					return tuttiMieiCd.indexOf(cd);
				}
			}
			return -1;
		}

		/**
		 * <b>Cerca </b>un {@linkplain Cd} tra {@linkplain #tuttiMieiCd} per <tt>titolo</tt>
		 * 
		 * @return un {@linkplain ArrayList} con all'interno tutti i {@linkplain Cd}
		 *         trovati
		 * @param titolo
		 *            e' il <b>titolo</b> da ricercare
		 *            @author Simone
		 */
		public ArrayList<Cd> cercaCDPerTitolo(String titolo) {  // qui però in teoria dovrebbe essere una stringa nel senso che non ho mai due titoli uguali ..
			ArrayList<Cd> cdTrovati = new ArrayList<Cd>();
			
				for (int i = 0; i < tuttiMieiCd.size(); i++) {
					if (tuttiMieiCd.get(i).getTitolo().equals(titolo)) {
						cdTrovati.add(tuttiMieiCd.get(i));
					}
			
			}
			return cdTrovati;
		}
		/**
		 * <b>Cerca </b>un {@linkplain Cd} tra {@linkplain #tuttiMieiCd} per <tt>autore</tt>
		 * 
		 * @return un {@linkplain ArrayList} con all'interno tutti i {@linkplain Cd}
		 *         trovati
		 * @param autore
		 *            e' il <b>titolo</b> da ricercare
		 *            @author Simone
		 */
		public ArrayList<Cd> cercaCDPerAutore(String autore) {
			ArrayList<Cd> cdTrovati = new ArrayList<Cd>();
			
				for (int i = 0; i < tuttiMieiCd.size(); i++) {
					if (tuttiMieiCd.get(i).getTitolo().equals(autore)) {
						cdTrovati.add(tuttiMieiCd.get(i));
					}
			
			}
			return cdTrovati;
		}
	/**<b>Visualizzazione </b>dei {@linkplain Cd} cercati per titolo.
	 * @return un <tt>array</tt> di {@linkplain Strig}, contenente le specifiche del {@linkplain Cd}
	 * @param titoloCd e' il titolo del {@linkplain Cd} da visualizzare
	 * @author Simone*/
		public String[] visualizzaCdPerTitolo(String titoloCd) {
				ArrayList<Cd> cdsTrovati=cercaCDPerTitolo(titoloCd);
				ArrayList<String> stringaCdTrovato=new ArrayList<>();
				for (Cd cds: cdsTrovati) {
				stringaCdTrovato.add(cds.toString());
				}
				return stringaCdTrovato.toArray(new String[stringaCdTrovato.size()]);
		}

		/**<b>Visualizzazione </b>dei {@linkplain Cd} cercati per autore.
		 * @return un <tt>array</tt> di {@linkplain Strig}, contenente le specifiche del {@linkplain Cd}
		 * @param autoreCd e' il titolo del {@linkplain Cd} da visualizzare
		 * @author Simone*/
			public String[] visualizzaCdPerAutore(String autoreCd) {
					ArrayList<Cd> cdsTrovati=cercaCDPerAutore(autoreCd);
					ArrayList<String> stringaCdTrovato=new ArrayList<>();
					for (Cd cds: cdsTrovati) {
					stringaCdTrovato.add(cds.toString());
					}
					return stringaCdTrovato.toArray(new String[stringaCdTrovato.size()]);
			}
			
			
			/**<b>Visualizzazione </b>dell {@linkplain Archivio} .contiene collezione di tutti i {@linkplain Cd}in archivio
			 * @return un <tt>array</tt> di {@linkplain Cd}, contenente le specifiche del {@linkplain Cd}
			 * 
			 * @author Simone*/
			
			
			public String[] visualizzaInteraCollezione() {
				
				ArrayList<String> daVisualizzare=new ArrayList<>();
				for (Cd cd : tuttiMieiCd) {
					daVisualizzare.add(cd.toString());
				}
				return daVisualizzare.toArray(new String[daVisualizzare.size()]);
			}
						
		public int getNumeroCd() {
			return tuttiMieiCd.size();
		}
		
		public ArrayList<Cd> getArchivio() {
			return tuttiMieiCd;
		}

		public void setArchivio(ArrayList<Cd> tuttiCdNuovi) {
			this.tuttiMieiCd = tuttiCdNuovi;
		}
		/**Sono tutti i check che si devono superare pe aggiungere un nuovo {@linkplain Cd} 
		 * @return true se tutti i check sono positivi, falso altrimenti
		 * @see {@linkplain ArchivioCd#aggiungiCd(Cd)}*/
		public boolean contiene(String titolo) {
			return !cercaCDPerTitolo(titolo).isEmpty();
			
		}
		@Override
		public String toString() { // toStringColletion ?
			return String.format(
					"Dentro in ArchioCD sono presenti %d Dischi",
					getArchivio().size());

		}
		/**Estrae un {@linkplain Cd} dall'{@linkplain ArchivioCd#tuttiMieiCd} <b>CASUALMENTE</b>
		 * @return un {@linkplain Cd}*/ 
		public Cd cdCasuale() {
			return tuttiMieiCd.get(EstrazioniCasuali.estraiIntero(0, tuttiMieiCd.size()-1));
			}
		
		public int getNumeroCD() {
			return tuttiMieiCd.size();
		}
	
}