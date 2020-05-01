package it.unibs.ing.fp.archiviocd;

public class Brano {
	
	private String nomeBrano;
    private int durata_min;
    private int durata_sec;
	private int n_minuti ;
    private int n_secondi;
    private final String  MINUTI  = "MM";
    private final String  SECONDI = "ss";
	private String durata_TOT;
    
	
	public Brano(String _titolo, String _autore,String _nomeBrano,String _durataTot ) {
		
		this.nomeBrano = _nomeBrano;
		this.durata_min= durata_min;
		this.durata_sec= durata_sec;
	    this.durata_TOT = durata_min + MINUTI + durata_sec + SECONDI;
    
}
//	public Brano(String _nomeBrano,int durata_min,int durata_sec) {
//		super();
//		this.nomeBrano = getNomeBrano();
//		this.durata_min =getN_minuti();
//		this.durata_sec= getN_secondi();
//	}
	public String getNomeBrano() {
		return nomeBrano;
	}
	public void setNomeBrano(String nomeBrano) {
		this.nomeBrano = nomeBrano;
	}
	public int getN_minuti() {
		return n_minuti;
	}
	public void setN_minuti(int n_minuti) {
		this.n_minuti = n_minuti;
	}
	public int getN_secondi() {
		return n_secondi;
	}
	public void setN_secondi(int n_secondi) {
		this.n_secondi = n_secondi;
	}
}