package my.archivioCD;

public class MainArchivio_CD {
	private static final String TITOLO = "Il pescatore";
	private static final String CANTANTE="SanPei";
	private static final String AUTORE = "Fabrizio De Andrè";

	public static void main(String[] args) {
		Brano br =new Brano(TITOLO, CANTANTE,4440);
//		Brano br1 =new Brano("Baby","Bimbo spastico",600);
//		//System.out.println(br.getDurataBrano());
//		Cd cdUno= new Cd("CD1", "tizioCaio");
//		Cd cdDue =new Cd("CD2", "tizioCaio");
//		final Cd cd = new Cd(TITOLO, AUTORE);
//		cd.aggiungiBrano(new Brano("Anime salve", "", 5, 52));
//		cd.aggiungiBrano(new Brano("Le acciughe fanno il pallone", "", 4, 47));
//		cd.aggiungiBrano(new Brano("Smisurata preghiera", "", 7, 8));
//			
//		cdUno.aggiungiBrano(br1);
		System.out.println(br.belToString());

	}

}
   