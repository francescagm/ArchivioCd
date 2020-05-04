package it.unibs.ing.fp.archivioCD2;


import java.sql.Time;
import java.util.ArrayList;


public class Cd {

	private String autore;
	private Time durataCd;
	private String titoloCd;
	private ArrayList<Brano> compilation;
	private String codiceCd;
	private static int progressivo = 0;

	public Cd(String _titoloCd, String _autore) {
		compilation = new ArrayList<Brano>();
		setTitoloCd(_titoloCd);
		setAutore(_autore);
		progressivo++;
		setCodiceCd();
		setDurataCdSecondi(0);
	}

	public String getCodiceCd() {
		return codiceCd;
	}

	private void setCodiceCd() {
		this.codiceCd = Cd.class.getSimpleName() + progressivo;
	}

	public void aggiuntaBrano(Brano Brano) {
		
		compilation.add(Brano);
		setDurataCdSecondi((getDurataCd() + Brano.getDurataBrano()));
	}

	/**
	 * <b>Elimina</b> un {@linkplain Brano} cercandolo per <b>titolo</b>
	 * 
	 * @return <tt>true</tt> se la eliminazione e' andata a buon fine,
	 *         <tt>false</tt> altrimenti
	 * @param titolo
	 *            e' il <b>titolo</b> da ricercare
	 *            @author Simone
	 */
	//TODO da ripensare
	public boolean eliminaBranoPerTitolo(String titolo) {
		byte quanteCanzoni = (byte) compilation.size();
		if (quanteCanzoni != 0) {
			for (int i = 0; i < compilation.size(); i++) {
				if (compilation.get(i).getTitoloBrano().equals(titolo)) {
					compilation.remove(i);
					setDurataCdSecondi(getDurataCd() - compilation.get(i).getDurataBrano());
					i--;
				}
			}
			return (quanteCanzoni != compilation.size());
		}
		return false;
	}

	/**
	 * <b>Cerca </b>un {@linkplain Brano} nella {@linkplain #compilation}
	 * 
	 * @return la <b>posizione </b>del {@linkplain Brano} cercato
	 * @param brano
	 *            e' il {@linkplain Brano} da cercare
	 *            @author Simone
	 */
	@Deprecated
	public int cercaBrano(Brano brano) {

		for (Brano _brano : compilation) {
			if (_brano.equals(brano)) {
				return compilation.indexOf(_brano);
			}
		}
		return -1;
	}

	/**
	 * <b>Cerca </b>un {@linkplain Brano} nella {@linkplain #compilation} per <tt>titolo</tt>
	 * 
	 * @return un {@linkplain ArrayList} con all'interno tutti i {@linkplain Brano}
	 *         trovati
	 * @param titolo
	 *            e' il <b>titolo</b> da ricercare
	 *            @author Simone
	 */
	public ArrayList<Brano> cercaBranoPerTitolo(String titolo) {
		ArrayList<Brano> braniTrovati = new ArrayList<Brano>();
		if (compilation.size() != 0) {
			for (int i = 0; i < compilation.size(); i++) {
				if (compilation.get(i).getTitoloBrano().equals(titolo)) {
					braniTrovati.add(compilation.get(i));
				}
			}

		}
		return braniTrovati;
	}
	/**
	 * 
	 * <b>Cerca </b>un {@linkplain Brano} nella {@linkplain #compilation} per <tt>cantante</tt>
	 * 
	 * @return un {@linkplain ArrayList} con all'interno tutti i {@linkplain Brano}
	 *         trovati
	 * @param cantante
	 *            e' il <b>cantante</b> da ricercare
	 *            @author Simone
	 */
	public ArrayList<Brano> cercaBranoPerCantante(String cantante) {
		ArrayList<Brano> braniTrovati = new ArrayList<Brano>();
		if (compilation.size() != 0) {
			for (int i = 0; i < compilation.size(); i++) {
				if (compilation.get(i).getCantante().equals(cantante)) {
					braniTrovati.add(compilation.get(i));
				}
			}

		}
		return braniTrovati;
	}
/**<b>Visualizzazione </b>dei {@linkplain Brano} cercati per titolo.
 * @return un <tt>array</tt> di {@linkplain Strig}, contenente le specifiche del {@linkplain Brano}
 * @param titoloBrano e' il titolo del {@linkplain Brano} da visualizzare
 * @author Simone*/
	public String[] visualizzaBranoPerTitolo(String titoloBrano) {
			ArrayList<Brano> braniTrovati=cercaBranoPerTitolo(titoloBrano);
			ArrayList<String> stringaBranoTrovato=new ArrayList<>();
			for (Brano brano : braniTrovati) {
			stringaBranoTrovato.add(brano.toString());
			}
			return stringaBranoTrovato.toArray(new String[stringaBranoTrovato.size()]);
	}

	/**<b>Visualizzazione </b>dei {@linkplain Brano} cercati per <tt>cantante</tt>.
	 * @return un <tt>array</tt> di {@linkplain Strig}, contenente le specifiche del {@linkplain Brano}
	 * @param cantanteBrano e' il cantante del {@linkplain Brano} da visualizzare
	 * @author Simone*/
		public String[] visualizzaBranoPerCantante(String cantanteBrano) {
				ArrayList<Brano> braniTrovati=cercaBranoPerCantante(cantanteBrano);
				ArrayList<String> stringaBranoTrovato=new ArrayList<>();
				for (Brano brano : braniTrovati) {
				stringaBranoTrovato.add(brano.toString());
				}
				return stringaBranoTrovato.toArray(new String[stringaBranoTrovato.size()]);
		}
	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getTitoloCd() {
		return titoloCd;
	}

	public void setTitoloCd(String titolo_Cd) {
		this.titoloCd = titolo_Cd;
	}

	public ArrayList<Brano> getCompilation() {
		return compilation;
	}

	public void setCompilation(ArrayList<Brano> compilation) {
		this.compilation = compilation;
	}

	@Override
	public String toString() {
		return String.format(
				"Cd [codiceCd=%s,  titolo_Cd= %s, autore= %s, durata del CD= %s, numero brani all'interno del CD= %d",
				getCodiceCd(), getTitoloCd(), getAutore(), getDurataCdString(), getCompilation().size());

	}

	/**
	 * @return un <tt>long</tt> rappresentante i secondi dell'attributo
	 *         {@linkplain #durataCd}
	 */
	public long getDurataCd() {
		return durataCd.getTime() / 1000;
	}

	public String getDurataCdString() {
		byte ora = (byte) (durataCd.getHours() - 1);
		return String.format("%02d:%02d:%02d", ora, durataCd.getMinutes(), durataCd.getSeconds());
	}

	public void setDurataCdSecondi(long durataCdSecondi) {
		this.durataCd = new Time(durataCdSecondi*1000);
	}

}