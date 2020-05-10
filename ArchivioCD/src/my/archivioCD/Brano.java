package my.archivioCD;

import java.sql.Time;

/** {@linkplain Class} che rappresenta un <b>brano musicale</b> */
public class Brano {
	private static final String IMPOSSIBILE_INSERIRE_UN_BRANO_CON_DURATA_0 = "Impossibile inserire un brano con durata 0";
	private static int progressivo = 0;
	private String codice;
	private String titolo;
	private String cantante;
	private Time durataBranoSecondi;// in millisecondi

	public Brano(String _titoloBrano, String _cantante, int _durataBranoSecondi) {
		if (_durataBranoSecondi <= 0)
			setDurataBrano(_durataBranoSecondi+1);
		setTitolo(_titoloBrano);
		setDurataBrano(_durataBranoSecondi);
		setCantante(_cantante);
		setCodice();
		progressivo++;
	}

	public Brano(String titoloBrano, String cantante, int minuti, int secondi) {
		this(titoloBrano, cantante, minuti * 60 + secondi);
	}

	/**
	 * <h1>metodo per test JUnit</h1>
	 * 
	 * @deprecated MANCA ATTRIBUTO {@linkplain #cantante}
	 */
	public Brano(String titoloBrano, int minuti, int secondi) {
		this(titoloBrano, "", minuti, secondi);
	}

	public String getCodice() {
		return codice;
	}

	/**
	 * Rappresenta il <tt>formato orario </tt>della <b>durata</b> del
	 * {@linkplain Brano}
	 * 
	 * @return una {@linkplain String} tipo <tt>hh:mm:ss</tt>
	 */
	public String getDurataBranoString() {
		byte ora = (byte) (durataBranoSecondi.getHours() - 1);
		return String.format("%02d:%02d:%02d", ora, durataBranoSecondi.getMinutes(), durataBranoSecondi.getSeconds());
	}

	/**
	 * @return un <tt>long</tt> rappresentante i secondi dell'attributo
	 *         {@linkplain #durataBrano}
	 */
	public long getDurataBranoSecondi() {
		return durataBranoSecondi.getTime() / 1000;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titoloBrano) {
		this.titolo = titoloBrano;
	}

	public void setDurataBrano(int durataBranoSecondi) {

		this.durataBranoSecondi = new Time(durataBranoSecondi * 1000);
	}

	public String getCantante() {
		return cantante;
	}

	public void setCantante(String cantante) {
		this.cantante = cantante;
	}

	private void setCodice() {
		this.codice = Brano.class.getSimpleName() + progressivo;
	}

	public String belToString() {
		return String.format("Il Brano %s, interpretato da %s, che dura %s. Il suo codiceUnivoco e' %s",
				titolo.toUpperCase(), cantante, getDurataBranoString(), codice);
	}

	/**
	 * <h5>UNUSED</h5>
	 * <h1>metodo per test JUnit</h1>
	 * 
	 * @deprecated usare {@linkplain #belToString()}
	 */

	public String toString() {

		return String.format("%s [%02d:%02d] ", titolo, durataBranoSecondi.getMinutes(),
				durataBranoSecondi.getSeconds());
	}

	public boolean equals(Brano brano) {
		return this.titolo.equalsIgnoreCase(brano.titolo) && this.cantante.equalsIgnoreCase(brano.getCantante());

	}

}
