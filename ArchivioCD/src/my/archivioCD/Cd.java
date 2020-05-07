package my.archivioCD;

import java.sql.Time;
import java.util.ArrayList;

import util.mylib.EstrazioniCasuali;

public class Cd {

	private static final String SPAZIO_IN_CD_ESAURITO = "Brano troppo lungo, spazio in esaurimento; capaci\u00e0 disponibile=";
	private String autore;
	private Time durataCd;
	private String titolo;
	private ArrayList<Brano> compilation;
	private String codice;
	private static int progressivo = 0;
	/** {@value} */
	public final static int DURATA_MASSIMA_CD_SECONDI = 4440;

	public Cd(String _titoloCd, String _autore) {
		compilation = new ArrayList<Brano>();
		compilation.clear();
		setTitolo(_titoloCd);
		setAutore(_autore);
		setCodice();
		durataCd = new Time(0);
		progressivo++;
	}
	/**
	 * costruttore di copia 
	 * @param cdDacopiare
	 */
	public Cd (Cd cdDacopiare) {
		setTitolo(cdDacopiare.getTitolo());
		setAutore(cdDacopiare.getAutore());
		setCodice();
		progressivo++;
		durataCd = cdDacopiare.durataCd;
		compilation = cdDacopiare.compilation;
	}
	

	public String getCodice() {
		return codice;
	}

	private void setCodice() {
		this.codice = Cd.class.getSimpleName() + progressivo;
	}

	public void aggiungiBrano(Brano brano) {
		if (cercaPosBrano(brano) != -1)
			throw new IllegalArgumentException("BRANO GIA' ESISTENTE IN QUESTO Cd");
		compilation.add(brano);
		setDurataCdSecondi((getDurataCd() + brano.getDurataBranoSecondi()));
	}

	/**
	 * <b>Elimina </b>tutti i {@linkplain Brano} passati nell'{@linkplain ArrayList}
	 * 
	 * @return <b>true</b> se tutti i {@linkplain Brano} nell'
	 *         {@linkplain ArrayList} sono stati <b>eliminati</b><br>
	 *         <b> false </b> altrimenti
	 * @param braniDaEliminare
	 *            e' l' {@linkplain ArrayList} da <b>eliminare</b>
	 */
	private boolean eliminaBrani(ArrayList<Brano> braniDaEliminare) {
		int braniAllInizio = compilation.size();
		if (braniDaEliminare.isEmpty())
			return false;
		for (Brano brano : braniDaEliminare) {
			if (compilation.remove(brano))
				setDurataCdSecondi(getDurataCd() - brano.getDurataBranoSecondi());
		}
		return (braniAllInizio - braniDaEliminare.size() == compilation.size());
	}

	/**
	 * <b>Elimina</b> un {@linkplain Brano} cercandolo per <b>titolo</b>
	 * 
	 * @return <tt>true</tt> se la eliminazione e' andata a buon fine,
	 *         <tt>false</tt> altrimenti
	 * @param titolo
	 *            e' il <b>titolo</b> da ricercare
	 * @author Simone
	 * @see <tt>funziona usando</tt>: {@linkplain #eliminaBrani},
	 *      {@linkplain #cercaBranoPerTitolo}
	 */
	public boolean eliminaBranoPerTitolo(String titolo) {
		return eliminaBrani(cercaBranoPerTitolo(titolo));
	}

	/**
	 * <b>Elimina</b> un {@linkplain Brano} cercandolo per <b>cantante</b>
	 * 
	 * @return <tt>true</tt> se la eliminazione e' andata a buon fine,
	 *         <tt>false</tt> altrimenti
	 * @param cantante
	 *            e' il <b>cantante</b> da ricercare
	 * @author Simone
	 * @see <tt>funziona usando</tt>: {@linkplain #eliminaBrani},
	 *      {@linkplain #cercaBranoPerCantante}
	 */
	public boolean eliminaBranoPerCantante(String cantante) {
		return eliminaBrani(cercaBranoPerCantante(cantante));
	}

	/***
	 * <b>Elimina </b>il {@linkplain Brano} passato
	 * 
	 * @return <b>true</b> se il {@linkplain Brano} e' stato <b>eliminato</b><br>
	 *         <b> false </b> altrimenti
	 * @param branoDaEliminare
	 *            e' il {@linkplain Brano} da <b>eliminare</b>
	 */
	public boolean eliminaBrano(Brano branoDaEliminare) {
		int braniAllInizio = compilation.size();
		compilation.remove(branoDaEliminare);
		return (braniAllInizio - 1 == compilation.size());
	}

	/**
	 * <b>Cerca </b>un {@linkplain Brano} nella {@linkplain #compilation}
	 * 
	 * @return la <b>posizione </b>del {@linkplain Brano} cercato, -1 se non trovato
	 * @param brano
	 *            e' il {@linkplain Brano} da cercare
	 * @author Simone
	 */

	public int cercaPosBrano(Brano brano) { // questo chiaro

		for (Brano _brano : compilation) {
			if (_brano.equals(brano)) {
				return compilation.indexOf(_brano);
			}
		}
		return -1;
	}

	/**
	 * <b>Cerca </b>un {@linkplain Brano} nella {@linkplain #compilation} per
	 * <tt>titolo</tt>
	 * 
	 * @return un {@linkplain ArrayList} con all'interno tutti i {@linkplain Brano}
	 *         trovati
	 * @param titolo
	 *            e' il <b>titolo</b> da ricercare
	 * @author Simone
	 */
	public ArrayList<Brano> cercaBranoPerTitolo(String titolo) {
		ArrayList<Brano> braniTrovati = new ArrayList<Brano>();

		for (int i = 0; i < compilation.size(); i++) {
			if (compilation.get(i).getTitolo().equalsIgnoreCase(titolo)) {
				braniTrovati.add(compilation.get(i));
			}

		}
		return braniTrovati;
	}

	/**
	 * 
	 * <b>Cerca </b>un {@linkplain Brano} nella {@linkplain #compilation} per
	 * <tt>cantante</tt>
	 * 
	 * @return un {@linkplain ArrayList} con all'interno tutti i {@linkplain Brano}
	 *         trovati
	 * @param cantante
	 *            e' il <b>cantante</b> da ricercare
	 * @author Simone
	 */
	public ArrayList<Brano> cercaBranoPerCantante(String cantante) {
		ArrayList<Brano> braniTrovati = new ArrayList<Brano>();
		for (int i = 0; i < compilation.size(); i++) {
			if (compilation.get(i).getCantante().equalsIgnoreCase(cantante)) {
				braniTrovati.add(compilation.get(i));
			}
		}
		return braniTrovati;
	}

	/**
	 * <b>Visualizzazione </b>dei {@linkplain Brano} cercati per titolo.
	 * 
	 * @return un <tt>array</tt> di {@linkplain Strig}, contenente le specifiche del
	 *         {@linkplain Brano}
	 * @param titoloBrano
	 *            e' il titolo del {@linkplain Brano} da visualizzare
	 * @author Simone
	 */
	public String[] visualizzaBranoPerTitolo(String titoloBrano) {
		ArrayList<Brano> braniTrovati = cercaBranoPerTitolo(titoloBrano);
		ArrayList<String> stringaBranoTrovato = new ArrayList<>();
		for (Brano brano : braniTrovati) {
			stringaBranoTrovato.add(brano.belToString());
		}
		return stringaBranoTrovato.toArray(new String[stringaBranoTrovato.size()]);
	}

	/**
	 * <b>Visualizzazione </b>dei {@linkplain Brano} cercati per <tt>cantante</tt>.
	 * 
	 * @return un <tt>array</tt> di {@linkplain Strig}, contenente le specifiche dei
	 *         singoli {@linkplain Brano}
	 * @param cantanteBrano
	 *            e' il cantante del {@linkplain Brano} da visualizzare
	 * @author Simone
	 */
	public String[] visualizzaBranoPerCantante(String cantanteBrano) {
		ArrayList<Brano> braniTrovati = cercaBranoPerCantante(cantanteBrano);
		ArrayList<String> stringaBranoTrovato = new ArrayList<>();
		for (Brano brano : braniTrovati) {
			stringaBranoTrovato.add(brano.belToString());
		}
		return stringaBranoTrovato.toArray(new String[stringaBranoTrovato.size()]);
	}

	/**
	 * <b>Visualizza</b> l'intera {@linkplain #compilation} del {@linkplain Cd}
	 * 
	 * @return un <tt>array</tt> di {@linkplain Strig}
	 * @author Simone
	 */
	public String[] visualizzaInteraCollezione() {

		ArrayList<String> daVisualizzare = new ArrayList<>();
		for (Brano brano : compilation) {
			daVisualizzare.add(brano.belToString());
		}
		return daVisualizzare.toArray(new String[daVisualizzare.size()]);
	}

	/** @return l' {@linkplain #autore} del {@linkplain Cd} */
	public String getAutore() {
		return autore;
	}

	private void setAutore(String autore) {
		this.autore = autore;
	}

	/** @return l' {@linkplain #titolo} del {@linkplain Cd} */
	public String getTitolo() {
		return titolo;
	}

	private void setTitolo(String titolo_Cd) {
		this.titolo = titolo_Cd;
	}

	private ArrayList<Brano> getCompilation() {
		return compilation;
	}

	private void setCompilation(ArrayList<Brano> compilation) {
		this.compilation = compilation;
	}

	/**
	 * @return una {@linkplain String} con le specifiche di {@linkplain Cd}
	 * @see #toStringCollection()
	 */
	public String belToString() {
		return String.format(
				"Il CD %s, il cui autore e' %s, di durata totale %s e contiene %d brani. Il suo codiceUnivoco e' %s",
				titolo.toUpperCase(), autore, getDurataCdString(), compilation.size(), codice);
	}

	/**
	 * <h5>UNUSED</h5>
	 * <h1>metodo fatto solo per JUnit</h1>
	 * 
	 * @deprecated usare {@linkplain #belToString()}
	 */
	public String toString() {
		StringBuilder fine = new StringBuilder();
		fine.append(String.format("Titolo: %s, Autore: %s, Lista dei brani: ", getTitolo(), getAutore()));
		for (int i = 0; i < compilation.size(); i++) {
			fine.append(compilation.get(i).toString());
		}
		return fine.toString();

	}

	/**
	 * @return una {@linkplain String} con le specifiche di {@linkplain Cd} e con
	 *         tutti i {@linkplain Brano} presenti
	 */
	public String toStringCollection() {
		StringBuilder fine = new StringBuilder();
		fine.append(belToString());
		fine.append(System.lineSeparator() + "Contiene:" + System.lineSeparator());
		String[] collezione = visualizzaInteraCollezione();
		for (String string : collezione) {
			fine.append(string);
			fine.append(System.lineSeparator());
		}
		return fine.toString();
	}

	public boolean equals(Cd cd_da_confrontare) {
		return titolo.compareTo(cd_da_confrontare.getTitolo()) == 0
				&& autore.compareTo(cd_da_confrontare.getAutore()) == 0
				&& codice.compareTo(cd_da_confrontare.getCodice()) == 0
				&& compilation == cd_da_confrontare.getCompilation();
	}

	/**
	 * @return un <tt>long</tt> rappresentante i secondi dell'attributo
	 *         {@linkplain #durataCd}
	 */
	public long getDurataCd() {
		return durataCd.getTime() / 1000;
	}

	/**
	 * Rappresenta il <tt>formato orario </tt>della <b>durata</b> del
	 * {@linkplain Cd}
	 * 
	 * @return una {@linkplain String} tipo <tt>hh:mm:ss</tt>
	 */
	public String getDurataCdString() {
		byte ora = (byte) (durataCd.getHours() - 1);
		return String.format("%02d:%02d:%02d", ora, durataCd.getMinutes(), durataCd.getSeconds());
	}

	/**
	 * Estrae un {@linkplain Brano} dall'{@linkplain Cd#compilation}
	 * <b>CASUALMENTE</b>
	 * 
	 * @return un {@linkplain Brano}
	 */
	public Brano branoCasuale() {
		return compilation.get(EstrazioniCasuali.estraiIntero(0, compilation.size() - 1));
	}

//	public int getSecondiRimasti() {
//		return (int) (DURATA_MASSIMA_CD_SECONDI - getDurataCd());
//	}

	private void setDurataCdSecondi(long durataCdSecondi) {
		// if (getDurataCd() + durataCdSecondi > Cd.DURATA_MASSIMA_CD_SECONDI)
		// throw new IllegalArgumentException(
		// SPAZIO_IN_CD_ESAURITO + getSecondiRimasti());
		this.durataCd = new Time(durataCdSecondi * 1000);
	}

	/**
	 * @return <tt>true</tt> se il {@linkplain #titolo} del {@linkplain Cd} e'
	 *         uguale alla {@linkplain String} passata per parametro
	 * @param cercato,
	 *            e' la {@linkplain String} da comparare
	 */
	public boolean haTitolo(String cercato) {
		return titolo.equalsIgnoreCase(cercato);
	}

}