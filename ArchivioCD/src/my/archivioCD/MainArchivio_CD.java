package my.archivioCD;

public class MainArchivio_CD {
	

	public static void main(String[] args) {
		
		ManageArchivio usaArchivio=new ManageArchivio();
		ArchivioUtils.printWelcome();
		usaArchivio.MainMenu();
		ArchivioUtils.printGoodbye();
	}

}
