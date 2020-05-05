package test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Ignore;
import org.junit.Test;

import my.archivioCD.Brano;
import my.archivioCD.Cd;

public class CdTest {
	private static final String TITOLO = "Anime salve";
	private static final String AUTORE = "Fabrizio De Andrè";

	@Test
	public void testToStringSenzaBrani() throws Exception {
		assertEquals(new Cd(TITOLO, AUTORE).toString(),
				"Il CD ANIME SALVE, il cui autore e' Fabrizio De Andrè, di durata totale 00:00:00 e contiene 0 brani. Il suo codiceUnivoco e' Cd4");
	}

	@Test
	public void testToStringConBrani() throws Exception {
		final Cd cd = new Cd(TITOLO, AUTORE);
		cd.aggiungiBrano(new Brano("Anime salve", "", 5, 52));
		cd.aggiungiBrano(new Brano("Le acciughe fanno il pallone", "", 4, 47));
		cd.aggiungiBrano(new Brano("Smisurata preghiera", "", 7, 8));
		// TODO questo modo di controllare � sbagliato, BISOGNA SCRVERe UNA STRINGA PER
		//LUNGO( A MANO)
		assertEquals(cd.toStringCollection(), cd.toStringCollection());
	}

	@Test
	public void testHaTitoloTrue() throws Exception {
		final Cd cd = new Cd(TITOLO, AUTORE);
		assertTrue(cd.getTitoloCd().equals(TITOLO));
	}

	@Test
	public void testHaTitoloFalse() throws Exception {
		final Cd cd = new Cd(TITOLO, AUTORE);
		assertFalse(cd.getTitoloCd().equals("La buona novella"));
	}

	@Test
	public void testEstrazioneCasualeBrano() throws Exception {
		final Cd cd = new Cd("_A Title_", "_An Author_");
		final int numeroBrani = 10;
		for (int i = 0; i < numeroBrani; i++) {
			Brano br = new Brano("Brano " + i, "", 0, i + 1);
			cd.aggiungiBrano(br);
		}

		final int numeroEstrazioni = 100;
		Set<String> titoliBraniEstratti = new HashSet<String>();
		for (int i = 0; i < numeroEstrazioni; i++) {
			Brano b = cd.branoCasuale();
			titoliBraniEstratti.add(b.getTitoloBrano());
			b = null;
		}
		assertTrue(titoliBraniEstratti.size() >= 7);
	}
}
