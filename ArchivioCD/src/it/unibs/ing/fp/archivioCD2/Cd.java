package it.unibs.ing.fp.archivioCD2;

import java.util.ArrayList;

import compilation.Brano;

public class Cd {
//	private static final String TITOLO = "";
//	private static final String AUTORE = "";
	private String autore;
	private static int durataCdSecondi = 4440;
	private String titolo_Cd;
	private ArrayList<Brano> compilation;
	private static final int MAX_BRANI = 12;// massimo 74 minuti quindi massimo SECONDI 4440 usa ensure capatici
	private String codiceCd;
	private int progressivo = 0;

	public Cd(String _titoloCd, String _autore) {
		compilation = new ArrayList<Brano>(MAX_BRANI);
		this.titolo_Cd = _titoloCd;
		this.autore = _autore;
		this.progressivo++;

		this.codiceCd = Cd.class.getSimpleName() + progressivo;
		this.durataCdSecondi = durataCdSecondi;
	}

	public void aggiungiBrano(TipoLinkContiene t) {
		if (t != null && t.getBrano() == this)
			ManagerContiene.inserisci(t);
	}

public int aggiungtaBrano ( Brano brano ) {
	  
for ( int i = 0; i < compilation.size(); i++  ) { 
		if ( compilation.get(i)!= null&& compilation.size())) { 
			;
			posizione = i;
			i = compilation.length;
		}

	public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getTitolo_Cd() {
		return titolo_Cd;
	}

	public void setTitolo_Cd(String titolo_Cd) {
		this.titolo_Cd = titolo_Cd;
	}

	public ArrayList<Brano> getCompilation() {
		return compilation;
	}

	public void setCompilation(ArrayList<Brano> compilation) {
		this.compilation = compilation;
	}

	public static int getDurataCdSecondi() {
		return durataCdSecondi;
	}

	public PrefissiArchivio getPrefissiAchivio() {
		return PrefissiArchivio.CD;
	}

}