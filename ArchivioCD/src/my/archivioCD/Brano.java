package my.archivioCD;

import java.sql.Time;



/** {@linkplain Class} che rappresenta un <b>brano musicale</b> */
public class Brano {
	private static int progressivo = 0;
	private String codiceBrano;
	private String titoloBrano;
	private String cantante;
	private Time durataBranoSecondi;// in millisecondi

	public Brano(String _titoloBrano, String _cantante, int _durataBranoSecondi) {
		setTitoloBrano(_titoloBrano);
		setDurataBranoSecondi(_durataBranoSecondi);
		setCantante(_cantante);
		setCodiceBrano();
		progressivo++;
	}

	public Brano(String titoloBrano, String cantante, int minuti, int secondi) {
		this(titoloBrano, cantante, minuti * 60 + secondi);
	}

	public Brano(String titoloBrano, int minuti, int secondi) {
		this(titoloBrano, "", minuti, secondi);
	}

	public String getCodice() {
		return codiceBrano;
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
	 *         {@linkplain #durataBranoSecondi}
	 */
	public long getDurataBrano() {
		return durataBranoSecondi.getTime() / 1000;
	}

	public String getTitolo() {
		return titoloBrano;
	}

	public void setTitoloBrano(String titoloBrano) {
		this.titoloBrano = titoloBrano;
	}

	public void setDurataBranoSecondi(int durataBranoSecondi) {

		this.durataBranoSecondi = new Time(durataBranoSecondi * 1000);
	}

	public String getCantante() {
		return cantante;
	}

	public void setCantante(String cantante) {
		this.cantante = cantante;
	}

	private void setCodiceBrano() {
		this.codiceBrano = Brano.class.getSimpleName() + progressivo;
	}

	public String belToString() {
		return String.format("Il Brano %s, interpretato da %s, che dura %s. Il suo codiceUnivoco e' %s",
				titoloBrano.toUpperCase(), cantante, getDurataBranoString(), codiceBrano);
	}

	/**<h5>UNUSED</h5><h1> metodo per test JUnit </h1>
	 * @deprecated usare {@linkplain #belToString()}*/
	
	public String toString() {
		
		return String.format("%s [%02d:%02d] ",titoloBrano,durataBranoSecondi.getMinutes(),durataBranoSecondi.getSeconds());
	}

	public boolean equals(Brano brano) {
		return this.titoloBrano.compareTo(brano.titoloBrano) == 0
				&& this.codiceBrano.compareTo(brano.getCodice()) == 0
				&& this.cantante.compareTo(brano.getCantante()) == 0 && this.getDurataBrano() == brano.getDurataBrano();
			
	}

}
