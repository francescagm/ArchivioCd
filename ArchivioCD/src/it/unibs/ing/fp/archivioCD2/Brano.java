package it.unibs.ing.fp.archivioCD2;

import java.sql.Time;

/** {@linkplain Class} che rappresenta un <b>brano musicale</b> */
public class Brano {
	private static int progressivo = 0;
	private String codiceBrano;
	private String titoloBrano;
	private String cantante;
	private Time durataBranoSecondi;// in millisecondi

	@Deprecated
	public Brano(String _titoloBrano, String _cantante, Time _durataBranoSecondi) {
		// TODO da pensare bene
	}

	public Brano(String _titoloBrano, String _cantante, int _durataBranoSecondi) {
		setTitoloBrano(_titoloBrano);
		setDurataBranoSecondi(_durataBranoSecondi);
		setCantante(_cantante);
		progressivo++;
		setCodiceBrano();
	}

	public String getCodiceBrano() {
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

	public String getTitoloBrano() {
		return titoloBrano;
	}

	public void setTitoloBrano(String titoloBrano) {
		this.titoloBrano = titoloBrano;
	}

	public void setDurataBranoSecondi(int durataBranoSecondi) {

		this.durataBranoSecondi = new Time(durataBranoSecondi * 1000);
	}

	@Deprecated
	public void setDurataBranoSecondi(Time durataBranoSecondi) {
		// TODO da pensare bene
		this.durataBranoSecondi = durataBranoSecondi;
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

	@Override
	public String toString() {
		return String.format("Brano [codiceBrano= %s, titoloBrano= %s, cantante= %s, durataBranoSecondi= %s]",
				codiceBrano, titoloBrano, cantante, getDurataBranoString());
	}

	public boolean equals(Brano brano) {
		return this.titoloBrano.compareTo(brano.titoloBrano) == 0
				&& this.codiceBrano.compareTo(brano.getCodiceBrano()) == 0
				&& this.cantante.compareTo(brano.getCantante()) == 0 && 
				this.getDurataBrano() == brano.getDurataBrano();
	}

}
