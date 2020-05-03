package it.unibs.ing.fp.archivioCD2;

import java.util.ArrayList;
import java.util.Arrays;

import javax.management.InstanceNotFoundException;

public class ArchivioCd {
   
    
	private ArrayList<Cd> tuttiMieiCd;

	public ArchivioCd(ArrayList<Cd> tuttiCd) {
		
		tuttiMieiCd = new ArrayList<Cd>(); ;
		}
	
	
	    public void aggiuntaCd(Cd cd) {
	     tuttiMieiCd.add(cd);
			
		}

	public boolean eliminaCd(Cd cd) {
		//TODO
	}
		

	 @Override
	public String toString() {
		return "ArchivioCd [tuttiMieiCd=" + tuttiMieiCd + "]";
	}





	public Cd cercaCdTitolo(String titolo) {
		 if (tuttiMieiCd!=null) {
			 for (int i=0;i<tuttiMieiCd.size();i++) {
					if(tuttiMieiCd.get(i).getTitoloCd().equals(titolo))
					return tuttiMieiCd.get(i);}

			 }	
		return null;
	 }  
	
	public int cercaCd (Cd cd) throws InstanceNotFoundException {
		if (tuttiMieiCd!=null) {
			 for (int i=0;i<tuttiMieiCd.size();i++) {
					if(tuttiMieiCd.get(i).equals(cd))
					return i;}

			 }	
		throw new InstanceNotFoundException("CD NOT FOUND");
	}
	
	public Cd cercaCdCodice(String codice) {
		 if (tuttiMieiCd!=null) {
			 for (int i=0;i<tuttiMieiCd.size();i++) {
					if(tuttiMieiCd.get(i).getCodiceCd().equals(codice))
					return tuttiMieiCd.get(i);}

			 }	
		return null;
	 }  
	
	public String visualizzaCd(Cd cercato) {
		Cd cd1 =cercaCdCodice(cercato.getCodiceCd());
		if(cd1!=null) {
			return cd1.toString();
		}
		return null;
//	  visualizzaCd(titolo);
//	  for(int i =0; i<tuttiMieiCd.get(i).getCompilation().size();i++)
//		  if(tuttiMieiCd.get(i).getCompilation()!=null)
//		  System.out.println(tuttiMieiCd.get(i).getCompilation().get(i).getTitoloBrano());
//		  else System.out.println("il cd  è vuoto");
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