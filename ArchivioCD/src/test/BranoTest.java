package test;

import static org.junit.Assert.*;

import org.junit.Test;

import my.archivioCD.Brano;

public class BranoTest {
	private static final String TITOLO = "Il pescatore";
	private static final String CANTANTE = "SanPei";
	private static final int MM = 4;
	private static final int SS = 46;

	@Test
	public void testToString() throws Exception {
		assertEquals("Il Brano IL PESCATORE, interpretato da SanPei, che dura 00:04:46. Il suo codiceUnivoco e' Brano0",
				new Brano(TITOLO, CANTANTE, MM, SS).toString());
	}

}
