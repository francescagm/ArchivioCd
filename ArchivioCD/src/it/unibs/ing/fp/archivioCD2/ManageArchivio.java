package it.unibs.ing.fp.archivioCD2;

import java.sql.Time;
import java.util.*;

import util.mylib.InputDati;
import util.mylib.MyMenu;


public class ManageArchivio {
	private final static String[] VOCI = {  };
	private final static String TITOLO = " ";
	
	ArchivioCd archivioMusicale = new ArchivioCd(null);
	  
	MyMenu menu = new MyMenu(TITOLO,VOCI);{
	 
		
		 
			int scelta = 0;
			scelta = menu.scegli();
			switch (scelta) {
			case 1:
			    System.out.println("visualizza la mia collezione musicale");
				archivioMusicale.visualizzaCd(null);
				System.out.println("vuoi ordinare le collezzione per titolo o per codice ");
				// uso delle due opzioni 
				InputDati.yesOrNo("vuoi ordinare la collezzione per titolo Cd");
			    
				break;
			case 2:
				System.out.println("vuoi aggiornare le tua collezione");
				System.out.println("vuoi aggiungere un brano ad un cd esistente oppure vuoi agigungere nuovo cd");
				// uso delle due opzioni
				break;
			
			case 3: 
				System.out.println("vuoi rimuovere un brano o un cd");
			     // uso delle due opzioni // uso del si o no per conferma ricerca per titolo o per autore 
				break;
			case 4:
				 
				 System.out.println("riproduzione di un brano shuffe");
				 break;
			default:
				break;
			
			
		  	
	    
       
		
		
		public static Brano creaBrano() {
		
		String titoloBrano = InputDati.leggiStringaNonVuota("inserisci titolo del brano musicale");
	    String cantante = InputDati.leggiStringaNonVuota("inserisci cantanti ");
	    int  durataBranoSecondi = InputDati.leggiInteroPositivo("inserisci durata del brano");
		return new Brano(titoloBrano,cantante,durataBranoSecondi);
	}
	
	
	    public static Cd creaCd() {
		String titolo_Cd =InputDati.leggiStringaNonVuota("inserisci titolo del Cd "); ;
		String autore = InputDati.leggiStringaNonVuota("inserisci nome Editore ");;
         return new Cd(titolo_Cd,autore);
	}
	
	
			}

	

}
