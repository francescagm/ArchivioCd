package it.unibs.ing.fp.archivioCD2;

import java.sql.Time;

public class Brano {
//	private static final String TITOLO = "";
//	private static final int MM =0;
//	private static final int SS =0;
//	private final String TITOLO_BRANO="";
	private static int progressivo=0;
	private String codiceBrano;
	private String titoloBrano;
	private String interprete;
	private Time  durataBranoSecondi;// in millisecondi 
	
	
	
	public Brano(String _titoloBrano, int _durataBranoSecondi) {
		this.titoloBrano = _titoloBrano;
		this.durataBranoSecondi = new Time(_durataBranoSecondi*1000) ;	
		this.interprete = interprete;
		this.progressivo++;
		this.codiceBrano=Brano.class.getSimpleName()+ progressivo ;
	
	}


//	public void setDurataBrano(String durataBrano) {
//		// da totale a stringa 
//		this.durataBrano = durataBrano;
//	}


//	public String getInterprete() {
//		return interprete;
//	}
//
//
//	public String getDurataBrano() {
//		return durataBrano;
//	}


	public int getDurataBranoSecondi() {
		// ArchivioUtils da inserimento minuti secondi a secondi 
		return durataBranoSecondi;
	}


	public String getTitoloBrano() {
		return titoloBrano;
	}


	public void setTitoloBrano(String titoloBrano) {
		this.titoloBrano = titoloBrano;
	}


	public void setDurataBranoSecondi(int durataBranoSecondi) {
	
		this.durataBranoSecondi = durataBranoSecondi;
	}




	
}
