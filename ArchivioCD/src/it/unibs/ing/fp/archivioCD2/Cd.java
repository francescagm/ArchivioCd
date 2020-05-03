package it.unibs.ing.fp.archivioCD2;

import java.sql.Time;
import java.util.ArrayList;



public class Cd {
    
	private String autore;
	private static Time durataCdSecondi ;
	private String titolo_Cd;
	private ArrayList<Brano> compilation;

	private String codiceCd;
	private int progressivo = 0;
	private int _durataCdSecondi;
	
	public Cd(String _titoloCd, String _autore) {
		compilation = new ArrayList<Brano>();
		this.titolo_Cd = _titoloCd;
		this.autore = _autore;
		this.progressivo++;

		this.codiceCd = Cd.class.getSimpleName() + progressivo;
		this.durataCdSecondi =new Time(_durataCdSecondi*1000) ;	

	}
	
	
public String getCodiceCd() {
		return codiceCd;
	}


	


public void aggiuntaBrano(Brano Brano) {
     compilation.add(Brano);
		
	}

public void eliminaBrano(Brano titolo) {
	if (compilation!=null) {
	for (int i=0;i<compilation.size();i++) {
		if(compilation.get(i).getTitoloBrano().equals(titolo))
			compilation.remove(i);
		    i--;
		    System.out.println("il brano è stato rimosso dalla compilation");
		}
	
	}else {System.out.println("non sono presenti Brani nella compilation");	
	}
	}
	
 public void visualizzaBrano(Brano titolo) {
	 if (compilation!=null) {
		 for (int i=0;i<compilation.size();i++) {
				if(compilation.get(i).getTitoloBrano().equals(titolo))
				System.out.println(""+compilation.get(i));}

		 }	 else {System.out.println("brano non trovato");
	 }
 
 }

 public void cercaBrano(Brano codice) {
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


	@Override
	public String toString() {
		return "Cd [autore=" + autore + ", titolo_Cd=" + titolo_Cd + ", compilation=" + compilation + ", codiceCd="
				+ codiceCd + ", progressivo=" + progressivo + ", _durataCdSecondi=" + _durataCdSecondi + "]";
	}
   
	
	
	


}