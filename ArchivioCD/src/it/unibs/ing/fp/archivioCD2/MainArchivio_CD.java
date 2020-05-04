package it.unibs.ing.fp.archivioCD2;

public class MainArchivio_CD {

	public static void main(String[] args) {
		Brano br =new Brano("Baby","Bimbo spastico",600);
		Brano br1 =new Brano("Baby","Bimbo spastico",600);
		//System.out.println(br.getDurataBrano());
		Cd cdUno= new Cd("CD1", "tizioCaio");
		cdUno.aggiuntaBrano(br);
		cdUno.aggiuntaBrano(br1);
		String[] ciao=cdUno.visualizzaBrani("Baby");
		for (int i = 0; i < ciao.length; i++) {
			System.out.println(ciao[i]);
		}
	
	
	}

}
   