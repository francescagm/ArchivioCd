package my.archivioCD;

public class MainArchivio_CD {
	private static final String TITOLO = "Il pescatore";
	private static final String CANTANTE = "SanPei";
	private static final String AUTORE = "Fabrizio De Andrè";

	public static void main(String[] args) {
		ManageArchivio usaArchivio=new ManageArchivio();
		usaArchivio.MainMenu();
	}

}
