package my.archivioCD;

import java.util.ArrayList;
import java.util.Arrays;

import util.mylib.EstrazioniCasuali;

public class ArchivioCd {

	private static final String CD_ESISTENTE = "Nome del CD già esistente! Impossibile immeterlo";
	/**
	 * <b>Attributo</b> che identifica l' {@linkplain ArrayList} di {@linkplain Cd}
	 */
	private ArrayList<Cd> tuttiMieiCd;

	public ArchivioCd() {
		tuttiMieiCd = new ArrayList<>();
		tuttiMieiCd.clear();
	}

	/** <b>Aggiunge </b>un {@linkplain Cd} a {@link #tuttiMieiCd} */
	public void aggiungiCd(Cd cdDaInserire) {
		if (contiene(cdDaInserire.getTitolo()))
			throw new IllegalArgumentException(CD_ESISTENTE);
		tuttiMieiCd.add(cdDaInserire);
	}

	/**
	 * <b>Elimina</b> un {@linkplain Cd} dai {@linkplain #tuttiMieiCd}
	 * 
	 * @return <tt>true</tt> se la eliminazione e' andata a buon fine,
	 *         <tt>false</tt> altrimenti
	 * @param cdDaEliminare
	 *            e' il {@linkplain Cd} da eliminare
	 * @author Simone
	 */
	public boolean eliminaCd(Cd cdDaEliminare) {
		int cdAllInizio = tuttiMieiCd.size();
		tuttiMieiCd.remove(cdDaEliminare);
		return (cdAllInizio - 1 == tuttiMieiCd.size());
	}

	/**
	 * <b>Elimina</b> dei {@linkplain Cd}s
	 * 
	 * @return <tt>true</tt> se la eliminazione e' andata a buon fine,
	 *         <tt>false</tt> altrimenti
	 * @param cdDaEliminare
	 *            e' l'{@linkplain ArrayList} di {@linkplain Cd} da eliminare
	 * @author Simone
	 */
	private boolean eliminaCds(ArrayList<Cd> cdDaEliminare) {// ma qui non ho capito li elimina tutti? non capisco in
																// (cercaCd(cd)) che ricerca fa per brano
		int cdAllInizio = tuttiMieiCd.size();
		if (cdDaEliminare.isEmpty())
			return false;
		for (Cd cd : cdDaEliminare) {
			tuttiMieiCd.remove(cd);
		}
		if (cdAllInizio - cdDaEliminare.size() == tuttiMieiCd.size())
			return true;
		return false;
	}

	/**
	 * <b>Elimina</b> un {@linkplain Cd} cercandolo per <b>titolo</b>
	 * 
	 * @return <tt>true</tt> se la eliminazione e' andata a buon fine,
	 *         <tt>false</tt> altrimenti
	 * @param titolo
	 *            e' il <b>titolo</b> da ricercare
	 * @author Simone
	 */

	public boolean eliminaCd(String titolo) {
		return eliminaCds(cercaCDPerTitolo(titolo));
	}

	/**
	 * <b>Elimina</b> un {@linkplain Cd} cercandolo per <b>autore</b>
	 * 
	 * @return <tt>true</tt> se la eliminazione e' andata a buon fine,
	 *         <tt>false</tt> altrimenti
	 * @param autore
	 *            e' il <b>autore</b> da ricercare
	 * @author Simone
	 */
	public boolean eliminaCDPerAutore(String autore) { // perchè qui passa un cantante qui è autore giusto ?
		return eliminaCds(cercaCDPerAutore(autore));
	}

	/**
	 * <b>Elimina</b> tutti i {@linkplain Cd} presenti in {@link #tuttiMieiCd}
	 * 
	 * @return <tt>true</tt> se la eliminazione e' andata a buon fine,
	 *         <tt>false</tt> altrimenti
	 * @author Simone
	 */
	public boolean eliminaTuttiCd() {
		return tuttiMieiCd.removeAll(tuttiMieiCd);
	}

	/**
	 * <b>Cerca </b>un {@linkplain Cd} tra {@linkplain #tuttiMieiCd}
	 * 
	 * @return la <b>posizione </b>del {@linkplain Cd} cercato
	 * @param brano
	 *            e' il {@linkplain Brano} da cercare
	 * @author Simone
	 */

	public int cercaPosizioneCd(Cd cdDaCercare) {
		for (Cd cd : tuttiMieiCd) {
			if (cd.equals(cdDaCercare)) {
				return tuttiMieiCd.indexOf(cd);
			}
		}
		return -1;
	}

	/**
	 * <b>Cerca </b>un {@linkplain Cd} tra {@linkplain #tuttiMieiCd} per
	 * <tt>titolo</tt>
	 * 
	 * @return un {@linkplain ArrayList} con all'interno tutti i {@linkplain Cd}
	 *         trovati
	 * @param titolo
	 *            e' il <b>titolo</b> da ricercare
	 * @author Simone
	 */
	public ArrayList<Cd> cercaCDPerTitolo(String titolo) { // qui però in teoria dovrebbe essere una stringa nel senso
															// che non ho mai due titoli uguali ..
		ArrayList<Cd> cdTrovati = new ArrayList<Cd>();

		for (int i = 0; i < tuttiMieiCd.size(); i++) {
			if (tuttiMieiCd.get(i).getTitolo().equalsIgnoreCase(titolo)) {
				cdTrovati.add(tuttiMieiCd.get(i));
			}

		}
		return cdTrovati;
	}

	/**
	 * <b>Cerca </b>un {@linkplain Cd} tra {@linkplain #tuttiMieiCd} per
	 * <tt>autore</tt>
	 * 
	 * @return un {@linkplain ArrayList} con all'interno tutti i {@linkplain Cd}
	 *         trovati
	 * @param autore
	 *            e' il <b>titolo</b> da ricercare
	 * @author Simone
	 */
	public ArrayList<Cd> cercaCDPerAutore(String autore) {
		ArrayList<Cd> cdTrovati = new ArrayList<Cd>();

		for (int i = 0; i < tuttiMieiCd.size(); i++) {
			if (tuttiMieiCd.get(i).getTitolo().equalsIgnoreCase(autore)) {
				cdTrovati.add(tuttiMieiCd.get(i));
			}

		}
		return cdTrovati;
	}

	/**
	 * <b>Visualizzazione </b>dei {@linkplain Cd} cercati per titolo.
	 * 
	 * @return un <tt>array</tt> di {@linkplain Strig}, contenente le specifiche del
	 *         {@linkplain Cd}
	 * @param titoloCd
	 *            e' il titolo del {@linkplain Cd} da visualizzare
	 * @author Simone
	 */
	public String[] visualizzaCdPerTitolo(String titoloCd) {
		ArrayList<Cd> cdsTrovati = cercaCDPerTitolo(titoloCd);
		ArrayList<String> stringaCdTrovato = new ArrayList<>();
		for (Cd cds : cdsTrovati) {
			stringaCdTrovato.add(cds.belToString());
		}
		return stringaCdTrovato.toArray(new String[stringaCdTrovato.size()]);
	}

	/**
	 * <b>Visualizzazione </b>dei {@linkplain Cd} cercati per autore.
	 * 
	 * @return un <tt>array</tt> di {@linkplain Strig}, contenente le specifiche del
	 *         {@linkplain Cd}
	 * @param autoreCd
	 *            e' il titolo del {@linkplain Cd} da visualizzare
	 * @author Simone
	 */
	public String[] visualizzaCdPerAutore(String autoreCd) {
		ArrayList<Cd> cdsTrovati = cercaCDPerAutore(autoreCd);
		ArrayList<String> stringaCdTrovato = new ArrayList<>();
		for (Cd cds : cdsTrovati) {
			stringaCdTrovato.add(cds.belToString());
		}
		return stringaCdTrovato.toArray(new String[stringaCdTrovato.size()]);
	}

	/**
	 * <b>Visualizzazione </b>dell {@linkplain ArchivioCd} .contiene collezione di
	 * tutti i {@linkplain Cd}in archivio
	 * 
	 * @return un <tt>array</tt> di {@linkplain String}, contenente le specifiche
	 *         del {@linkplain Cd}
	 * 
	 * @author Simone
	 */

	public String[] visualizzaInteraCollezioneCD() {

		ArrayList<String> daVisualizzare = new ArrayList<>();
		for (Cd cd : tuttiMieiCd) {
			daVisualizzare.add(cd.belToString());
		}
		return daVisualizzare.toArray(new String[daVisualizzare.size()]);
	}

	/**
	 * <b>Visualizzazione </b>dell {@linkplain ArchivioCd} .contiene collezione di
	 * tutti i {@linkplain Cd}in archivio
	 * 
	 * @return un <tt>array</tt> di {@linkplain String}, contenente le specifiche
	 *         del {@linkplain Cd} e tutti i suoi {@linkplain Brano} numerati
	 * 
	 * @author Simone
	 */
	public String[] visualizzaInteraCollezioneCdBrani() {
		ArrayList<String> daVisualizzare = new ArrayList<>();
		for (Cd cd : tuttiMieiCd) {
			daVisualizzare.add(cd.toStringCollection());
		}
		return daVisualizzare.toArray(new String[daVisualizzare.size()]);
	}

	/** @return il {@linkplain Cd} in quella posizione */
	public Cd getCd(int index) {
		return tuttiMieiCd.get(index);
	}

	/**
	 * @return un <tt>int</tt> con la quantita' di {@linkplain Cd} in
	 *         {@link #tuttiMieiCd}
	 */
	public int getNumeroCd() {
		return tuttiMieiCd.size();
	}

	public ArrayList<Cd> getArchivio() {
		return tuttiMieiCd;
	}

	private void setArchivio(ArrayList<Cd> tuttiCdNuovi) {
		this.tuttiMieiCd = tuttiCdNuovi;
	}

	/**
	 * Sono tutti i check che si devono superare pe aggiungere un nuovo
	 * {@linkplain Cd}
	 * 
	 * @return true se tutti i check sono positivi, falso altrimenti
	 * @see {@linkplain ArchivioCd#aggiungiCd(Cd)}
	 */
	public boolean contiene(String titolo) {
		return !cercaCDPerTitolo(titolo).isEmpty();

	}

	/**
	 * @return un {@linkplain String} con le specifiche di
	 *         {@linkplain ArchivioCd} @author Simone
	 */
	public String toString() {
		return String.format("Dentro in ArchivioCD sono presenti %d Dischi", getArchivio().size());

	}

	/**
	 * @return un {@linkplain String} con all' interno le specifiche di tutti i
	 *         {@linkplain Cd} numerati, contenuti in {@link #tuttiMieiCd}, e tutti
	 *         i suoi {@linkplain Brano} numerati,<b> se vuoto</b> <tt>null<tt>
	 * @author Simone
	 * @see #visualizzaInteraCollezioneCdBrani()
	 */
	public String toStringCdBraniColletion() {
		StringBuilder fine = new StringBuilder();

		fine.append(toString());
		fine.append(System.lineSeparator() + "Contiene:" + System.lineSeparator());
		String[] cdString = visualizzaInteraCollezioneCdBrani();
		if (cdString != null&&cdString.length>0) {
			for (int i = 0; i < cdString.length; i++) {
				fine.append(i);
				fine.append("--> " + cdString[i]);
				fine.append(System.lineSeparator());
			}
			return fine.toString();
		}
		return null;
	}

	/**
	 * @return un {@linkplain String} con all' interno le specifiche di tutti i
	 *         {@linkplain Cd} numerati, contenuti in {@link #tuttiMieiCd},<b> se
	 *         vuoto</b> <tt>null<tt>
	 * @author Simone
	 * @see #visualizzaInteraCollezioneCD()
	 */
	public String toStringCdColletion() {
		StringBuilder fine = new StringBuilder();

		fine.append(toString());
		fine.append(System.lineSeparator() + "Contiene:" + System.lineSeparator());
		String[] cdString = visualizzaInteraCollezioneCD();
		if (cdString != null&&cdString.length>0) {
			for (int i = 0; i < cdString.length; i++) {
				fine.append(i);
				fine.append("--> " + cdString[i]);
				fine.append(System.lineSeparator());
			}
			return fine.toString();
		}
		return null;
	}

	/**
	 * Estrae un {@linkplain Cd} dall'{@linkplain ArchivioCd#tuttiMieiCd}
	 * <b>CASUALMENTE</b>
	 * 
	 * @return un {@linkplain Cd}, null se {@link #tuttiMieiCd} e' vuoto
	 */
	public Cd cdCasuale() {
		if(tuttiMieiCd.isEmpty())
			return null;
		return tuttiMieiCd.get(EstrazioniCasuali.estraiIntero(0, tuttiMieiCd.size() - 1));
	}

}