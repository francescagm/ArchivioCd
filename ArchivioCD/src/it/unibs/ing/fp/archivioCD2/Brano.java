package it.unibs.ing.fp.archivioCD2;

import java.sql.Time;

public class Brano {
private static int progressivo=0;
	private String codiceBrano;
	private String titoloBrano;
	private String cantante;
	private Time  durataBranoSecondi;// in millisecondi 
	
	
	
	public Brano(String _titoloBrano,String _cantante, int _durataBranoSecondi) {
		this.titoloBrano = _titoloBrano;
		this.durataBranoSecondi = new Time(_durataBranoSecondi*1000) ;	
		this.cantante = cantante;
		this.progressivo++;
		this.codiceBrano=Brano.class.getSimpleName()+ progressivo ;
	
	}
	

   


	public String getCodiceBrano() {
		return codiceBrano;
	}





	public void setCodiceBrano(String codiceBrano) {
		this.codiceBrano = codiceBrano;
	}





	public Time getDurataBranoSecondi() {
		
		return durataBranoSecondi;
	}


	public String getTitoloBrano() {
		return titoloBrano;
	}


	public void setTitoloBrano(String titoloBrano) {
		this.titoloBrano = titoloBrano;
	}


	public void setDurataBranoSecondi(Time durataBranoSecondi) {
	
		this.durataBranoSecondi = durataBranoSecondi;
	}
   
       
   


	@Override
	public String toString() {
		return "Brano [codiceBrano=" + codiceBrano + ", titoloBrano=" + titoloBrano + ", cantante=" + cantante
				+ ", durataBranoSecondi=" + durataBranoSecondi + "]";
	}

        
   

	   
}
