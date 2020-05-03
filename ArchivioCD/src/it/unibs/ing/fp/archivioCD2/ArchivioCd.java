package it.unibs.ing.fp.archivioCD2;

import java.util.ArrayList;
import java.util.Arrays;

public class ArchivioCd {
   
    
	private ArrayList<Cd> tuttiMieiCd;

	public ArchivioCd(ArrayList<Cd> tuttiCd) {
		
		tuttiMieiCd = new ArrayList<Cd>(); ;
		}
	
	
	    public void aggiuntaCd(Cd cd) {
	     tuttiMieiCd.add(cd);
			
		}

	public void eliminaCd(Cd titoloCd) {
		if (tuttiMieiCd!=null) {
		for (int i=0;i<tuttiMieiCd.size();i++) {
			if(tuttiMieiCd.get(i).getTitolo_Cd().equals(titoloCd))
				tuttiMieiCd.remove(i);
			    System.out.println("il cd è stato rimosso da archivio");
			}
		
		}else {System.out.println("non sono presenti cd in archivio");	
		}
		}
		
	 public void visualizzaCd(Cd titolo ) {
		 if (tuttiMieiCd!=null) {
			 for (int i=0;i<tuttiMieiCd.size();i++) {
				if(tuttiMieiCd.get(i).getTitolo_Cd().equals(titolo))
					    tuttiMieiCd.get(i).getTitolo_Cd();
						System.out.println(tuttiMieiCd.get(i).getTitolo_Cd());
					    //if(tuttiMieiCd.get(i).getCompilation()!=null) // no no non stampare tutto qui facciamo una stampa del contenuto di un cd a parte 
					   
					}

			 }	 else {System.out.println("cd non presente in archivio");
		 }
	 
	 }

	 @Override
	public String toString() {
		return "ArchivioCd [tuttiMieiCd=" + tuttiMieiCd + "]";
	}





	public void cercaCd(Cd codice) {
		 if (tuttiMieiCd!=null) {
			 for (int i=0;i<tuttiMieiCd.size();i++) {
					if(tuttiMieiCd.get(i).getCodiceCd().equals(codice))
					System.out.println(""+tuttiMieiCd.get(i).getTitolo_Cd());}

			 }	 else {System.out.println("codice brano inesistente");}
	 }
	
	public void contentutoCd(Cd titolo) {
	  visualizzaCd(titolo);
	  for(int i =0; i<tuttiMieiCd.get(i).getCompilation().size();i++)
		  if(tuttiMieiCd.get(i).getCompilation()!=null)
		  System.out.println(tuttiMieiCd.get(i).getCompilation().get(i).getTitoloBrano());
		  else System.out.println("il cd  è vuoto");
	}
	  
     public void formattaCd(Brano playlist)
     {
    	     for(int i =0; i<tuttiMieiCd.get(i).getCompilation().size();i++) {
    		 playlist =tuttiMieiCd.get(i).getCompilation().get(i);
    	     if (playlist!=null) {
    	     playlist=null;
    	     System.out.println("cd foramattato");}
    	     else { System.out.println("la playlist è vuota");
    	     }
     }
		
	}
}