package it.unibs.ing.fp.archivioCD2;

import java.util.ArrayList;

public class ArchivioCd {
    private static final int  MAX_ARCHIVIO= 50000;// metto anche qui in secondi 
	public ArrayList<Cd> getTuttiCd() {
		return TuttiCd;
	}

	public void setTuttiCd(ArrayList<Cd> tuttiCd) {
		TuttiCd = tuttiCd;
	}
	private ArrayList<Cd> TuttiCd;

	public ArchivioCd(ArrayList<Cd> tuttiCd) {
		
		TuttiCd = new ArrayList<Cd>(); ;
		
	}
	
	public void aggiungiCd() {
		
		
		
	}
	
	public void eliminaCd() {
}
    public void cercaCD() {
}
}