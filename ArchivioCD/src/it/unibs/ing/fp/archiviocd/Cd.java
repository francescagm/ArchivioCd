package it.unibs.ing.fp.archiviocd;

import java.util.ArrayList;

public class Cd {
	 
	private ArrayList<Brano>compilation;
	private String titolo;
	private String autore;
	


   public Cd(String _titolo,String _autore) {
	  
	   this.titolo = _titolo;
	   this.autore = _autore;
	   compilation=	new ArrayList<Brano>();
	   
     }


//
//public Cd() {
//	// TODO Auto-generated constructor stub
//}



public ArrayList<Brano> getCompilation() {
	return compilation;
}



public void setCompilation(ArrayList<Brano> compilation) {
	this.compilation = compilation;
}



public String getTitolo() {
	return titolo;
}



public void setTitolo(String titolo) {
	this.titolo = titolo;
}



public String getAutore() {
	return autore;
}



public void setAutore(String autore) {
	this.autore = autore;
}
public void aggiungiBrano() {
	
	
	
}
public void visualizzaBrano() {
	
}
public void rimuoviBrano() {
	
}
public void   shuffleBrano() {
	
}
}




   