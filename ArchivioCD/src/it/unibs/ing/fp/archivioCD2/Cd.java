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
	//TODO
		compilation.add(Brano);

	}

	public boolean eliminaBrano(String titolo) {
		if (!compilation.isEmpty()) {
			for (int i = 0; i < compilation.size(); i++) {
				if (compilation.get(i).getTitoloBrano().equals(titolo)) {
					compilation.remove(i);
					return true;
				}
			}
		}
		return false;

	}
	//TODO
	public Brano cercaBrano(Brano brano) {
		for (Brano br : compilation) {
			br.equals(brano);
			return null;
		}
		return null;
		
	}
	
	public void visualizzaBrano(Brano titolo) {
		if (compilation != null) {
			for (int i = 0; i < compilation.size(); i++) {
				if (compilation.get(i).getTitoloBrano().equals(titolo))
					System.out.println("" + compilation.get(i));
			}

		} else {
			System.out.println("brano non trovato");
		}

	}

	public void cercaBrano(String codice) {
		if (compilation != null) {
			for (int i = 0; i < compilation.size(); i++) {
				if (compilation.get(i).getCodiceBrano().equals(codice))
					System.out.println("" + compilation.get(i).getTitoloBrano());
			}

		} else {
			System.out.println("brano non trovato");

		}
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
//TODO JAVADOC
	public Time getDurataCd() {
		return durataCd;
	}
	
	public String getDurataCdString() {
		byte ora = (byte) (durataCd.getHours() - 1);
		return String.format("%02d:%02d:%02d", ora, durataCd.getMinutes(), durataCd.getSeconds());
	}

	public void setDurataCd(Time durataCdSecondi) {
		this.durataCd = durataCdSecondi;
	}

	
	public void setDurataCdSecondi(int durataCdSecondi) {
		setDurataCd(new Time(durataCdSecondi * 1000));
	}

}