package my.archivioCD;

import java.util.ArrayList;

import util.mylib.EstrazioniCasuali;

 public class ArchivioCd {
   
	private ArrayList<Cd> tuttiMieiCd;
		
		public ArchivioCd() {
			tuttiMieiCd=new ArrayList<Cd>();
		}

			public void aggiuntaCd(Cd cdDaInserire) {
			checkCdEsistente(cdDaInserire.getTitoloCd());
			tuttiMieiCd.add(cdDaInserire);
		}

		public boolean eliminaCds(ArrayList<Cd> cdDaEliminare) {
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
		 *            @author Simone
		 */
		
		private boolean checkCdEsistente(String titolo) {
			if(cercaCDPerTitolo(titolo).isEmpty())
				return true;
			return false;
		}
		public boolean eliminaCDPerTitolo(String titolo) {
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
		public boolean eliminaCDPerAutore(String cantante) {
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

			for (Cd _brano : tuttiMieiCd) {
				if (_brano.equals(cdDaCercare)) {
					return tuttiMieiCd.indexOf(_brano);
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
		public ArrayList<Cd> cercaCDPerTitolo(String titolo) {
			ArrayList<Cd> cdTrovati = new ArrayList<Cd>();
			
				for (int i = 0; i < tuttiMieiCd.size(); i++) {
					if (tuttiMieiCd.get(i).getTitoloCd().equals(titolo)) {
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
					if (tuttiMieiCd.get(i).getTitoloCd().equals(autore)) {
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
			
			public String[] visualizzaInteraCollezione() {
				
				ArrayList<String> daVisualizzare=new ArrayList<>();
				for (Cd cd : tuttiMieiCd) {
					daVisualizzare.add(cd.toString());
				}
				return daVisualizzare.toArray(new String[daVisualizzare.size()]);
			}
						
			
		
		public ArrayList<Cd> getArchivio() {
			return tuttiMieiCd;
		}

		public void setArchivio(ArrayList<Cd> tuttiCdNuovi) {
			this.tuttiMieiCd = tuttiCdNuovi;
		}

		@Override
		public String toString() {
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