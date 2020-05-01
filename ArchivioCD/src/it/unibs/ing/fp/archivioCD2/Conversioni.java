package it.unibs.ing.fp.archivioCD2;

public class Conversioni {

	  public static final int ORE = 0;
	  public static final int MINUTI = 1;

	  

	
	  // Metodi di conversione (ore:min -> min e min -> ore:min)
	  public int inMinuti() {
	    return (ore * 60 + minuti);
	  }
	  public String daSecondiaMinuti(int secondi) {
	   int minuti= secondi/ 60;
	   secondi = secondi % 60;
	   return 
	  }

	  // Somma un tempo
	  public void somma(Tempo t)
	  {
	    int min = inMinuti() + t.inMinuti();
	    daMinuti(min);
	  }
	  // Sottrae un tempo
	  public Tempo sottrai(Tempo t)
	  {
	    int sec = this.inMinuti() - t.inMinuti();
	    this.daMinuti(sec);
	    return this;
	  }

	  // Confronto fra tempi
	  public boolean maggioreDi(Tempo t) {
	    return (this.inMinuti() > t.inMinuti());
	  }
	  public boolean minoreDi(Tempo t) {
	    return (this.inMinuti() < t.inMinuti());
	  }
	}


	

}
