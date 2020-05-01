package it.unibs.ing.fp.archivioCD2;

public enum PrefissiArchivio {
	CD("CD",1),
	BRANO_SINGOLO ("B",2);
	private String Simbolo;
	private int codicetipo;
	private PrefissiArchivio(String simbolo, int codicetipo) {
		Simbolo = simbolo;
		this.codicetipo = codicetipo;
	}
	public int getCodicetipo() {
		return codicetipo;
	}
	
}
