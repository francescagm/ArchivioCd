package test;

import static org.junit.Assert.*;

import org.junit.Test;

import my.archivioCD.ArchivioCd;
import my.archivioCD.Cd;

public class ArchivioCdTest {
	@Test
	public void testAggiuntaCd() throws Exception {
		final ArchivioCd archivio = new ArchivioCd();
		archivio.aggiuntaCd(new Cd("Anime salve", "Fabrizio De Andrè"));
		archivio.aggiuntaCd(new Cd("Storia di un impiegato", "Fabrizio De Andrè"));
		assertEquals(2, archivio.getNumeroCD());
	}
	
	@Test
	public void testRicercaTitoloPresente() throws Exception {
		final ArchivioCd archivio = new ArchivioCd();
		archivio.aggiuntaCd(new Cd("Anime salve", "Fabrizio De Andrè"));
		archivio.aggiuntaCd(new Cd("Storia di un impiegato", "Fabrizio De Andrè"));
		assertFalse(archivio.cercaCDPerTitolo("Anime salve").isEmpty());
	}
	
	@Test
	public void testRicercaTitoloNonPresente() throws Exception {
		final ArchivioCd archivio = new ArchivioCd();
		archivio.aggiuntaCd(new Cd("Anime salve", "Fabrizio De Andrè"));
		archivio.aggiuntaCd(new Cd("Storia di un impiegato", "Fabrizio De Andrè"));
		assertTrue(archivio.cercaCDPerTitolo("La buona novella").isEmpty());
	}
	
	@Test
	public void testEliminazioneCdPerTitolo() throws Exception {
		final ArchivioCd archivio = new ArchivioCd();
		archivio.aggiuntaCd(new Cd("Anime salve", "Fabrizio De Andrè"));
		archivio.aggiuntaCd(new Cd("Storia di un impiegato", "Fabrizio De Andrè"));
		assertEquals(2, archivio.getNumeroCD());
		archivio.eliminaCDPerTitolo("Anime salve");
		assertEquals(1, archivio.getNumeroCD());
		assertTrue(archivio.cercaCDPerTitolo("Anime salve").isEmpty());
	}
}
