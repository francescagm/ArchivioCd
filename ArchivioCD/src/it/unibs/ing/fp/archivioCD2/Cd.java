package it.unibs.ing.fp.archivioCD2;

import java.sql.Time;
import java.util.ArrayList;
 
public class Cd {
    
	private String autore;
	private  Time durataCdSecondi ;
	private String titolo_Cd;
	private ArrayList<Brano> compilation;
   
	private String codiceCd;
	private static int progressivo = 0;
	
	
	public Cd(String _titoloCd, String _autore) {
		compilation = new ArrayList<Brano>();
		setTitolo_Cd(_titoloCd);
		setAutore(_autore);
		progressivo++;

		setCodiceCd();
		setDurataCd(durataCdSecondi);	
	}
	
	
public String getCodiceCd() {
		return codiceCd;
	}

private void setCodiceCd() {
	this.codiceCd = Cd.class.getSimpleName() + progressivo;
}
	


public void aggiuntaBrano(Brano Brano) {
     compilation.add(Brano);
		
	}

public boolean eliminaBrano(String titolo) {
	if (compilation!=null) {
	for (int i=0;i<compilation.size();i++) {
		if(compilation.get(i).getTitoloBrano().equals(titolo))
			compilation.remove(i);
		    return true;
		}
	return false;
	
	}
	
 public void visualizzaBrano(Brano titolo) {
	 if (compilation!=null) {
		 for (int i=0;i<compilation.size();i++) {
				if(compilation.get(i).getTitoloBrano().equals(titolo))
				System.out.println(""+compilation.get(i));}

		 }	 else {System.out.println("brano non trovato");
	 }
 
 }

 public void cercaBrano(String codice) {
	 if (compilation!=null) {
		 for (int i=0;i<compilation.size();i++) {
				if(compilation.get(i).getCodiceBrano().equals(codice))
				System.out.println(""+compilation.get(i).getTitoloBrano());}

		 }	 else {System.out.println("brano non trovato");
	 
	 
		 }
 }
 

public String getAutore() {
		return autore;
	}

	public void setAutore(String autore) {
		this.autore = autore;
	}

	public String getTitoloCd() {
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


	@Override
	public String toString() {
		return String.format("Cd [codiceCd=%s,  titolo_Cd= %s, autore= %s, durata del CD= %s, numero brani all'interno del CD= %d", getCodiceCd(),getTitoloCd(),getAutore(),getDurataCdSecondi(),getCompilation().size());
				
	}


	public Time getDurataCdSecondi() {
		return durataCdSecondi;
	}


	public void setDurataCd(Time durataCdSecondi) {
		this.durataCdSecondi = durataCdSecondi;
	}
	@Deprecated
	public void setDurataCdSecondi(int durataCdSecondi) {
		setDurataCd(new Time(durataCdSecondi*1000)) ;
	}
   
	
	
	


}