package note.test;

import static org.junit.Assert.*;

import note.model.Nota;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import note.utils.ClasaException;
import note.utils.Constants;

import note.controller.NoteController;

public class AddNotaTest {

	private NoteController ctrl;

	@Before
	public void init(){
		ctrl = new NoteController();
	}

	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void TC01_ECP() throws ClasaException {
		Nota nota = new Nota(1, "materie", 8);
		ctrl.addNota(nota);
		assertEquals(1, ctrl.getNote().size());
		assertEquals(1, ctrl.getNote().get(0).getNrmatricol(),0.00001);
		assertEquals(8, ctrl.getNote().get(0).getNota(),0.00001);
		assertEquals("materie", ctrl.getNote().get(0).getMaterie());
	}

	@Test
	public void TC06_ECP() throws ClasaException {
		expectedEx.expect(ClasaException.class);
		expectedEx.expectMessage(Constants.invalidNrmatricol);
		Nota nota = new Nota(-1, "materie", 8);
		ctrl.addNota(nota);
	}

	@Test
	public void TC07_ECP() throws ClasaException {
		expectedEx.expect(ClasaException.class);
		expectedEx.expectMessage(Constants.invalidNrmatricol);
		Nota nota = new Nota(1.5, "materie", 8);
		ctrl.addNota(nota);
	}

	@Test
	public void TC04_ECP() throws ClasaException {
		expectedEx.expect(ClasaException.class);
		expectedEx.expectMessage(Constants.invalidNota);
		Nota nota = new Nota(1, "materie", 0);
		ctrl.addNota(nota);
	}

	@Test
	public void TC03_ECP() throws ClasaException {
		expectedEx.expect(ClasaException.class);
		expectedEx.expectMessage(Constants.invalidNota);
		Nota nota = new Nota(1, "materie", 4.5);
		ctrl.addNota(nota);
	}

	@Test
	public void TC05_ECP() throws ClasaException {
		expectedEx.expect(ClasaException.class);
		expectedEx.expectMessage(Constants.invalidNota);
		Nota nota = new Nota(1, "materie", 11);
		ctrl.addNota(nota);
	}

	@Test
	public void TC11_BV() throws ClasaException {
		Nota nota = new Nota(4, "materie", 10);
		ctrl.addNota(nota);
		assertEquals(1, ctrl.getNote().size());
		assertEquals(4, ctrl.getNote().get(0).getNrmatricol(),0.00001);
		assertEquals(10, ctrl.getNote().get(0).getNota(),0.00001);
		assertEquals("materie", ctrl.getNote().get(0).getMaterie());
	}

	@Test
	public void TC09_BV() throws ClasaException {
		expectedEx.expect(ClasaException.class);
		expectedEx.expectMessage(Constants.invalidNota);
		Nota nota = new Nota(1, "materie", -1);
		ctrl.addNota(nota);
	}

	@Test
	public void TC16_BV() throws ClasaException {
		Nota nota = new Nota(Integer.MAX_VALUE, "materie", 5);
		ctrl.addNota(nota);
		assertEquals(1, ctrl.getNote().size());
		assertEquals(Integer.MAX_VALUE, ctrl.getNote().get(0).getNrmatricol(),0.00001);
		assertEquals(5, ctrl.getNote().get(0).getNota(),0.00001);
		assertEquals("materie", ctrl.getNote().get(0).getMaterie());
	}

	@Test
	public void TC17_BV() throws ClasaException {
		Nota nota = new Nota(Integer.MAX_VALUE-1, "materie", 5);
		ctrl.addNota(nota);
		assertEquals(1, ctrl.getNote().size());
		assertEquals(Integer.MAX_VALUE-1, ctrl.getNote().get(0).getNrmatricol(),0.00001);
		assertEquals(5, ctrl.getNote().get(0).getNota(),0.00001);
		assertEquals("materie", ctrl.getNote().get(0).getMaterie());
	}

	@Test
	public void TC01_BV() throws ClasaException {
		expectedEx.expect(ClasaException.class);
		expectedEx.expectMessage(Constants.invalidMateria);
		Nota nota = new Nota(1, "", 5);
		ctrl.addNota(nota);
	}

	@Test
	public void TC04_BV() throws ClasaException {
		Nota nota = new Nota(1, "Materieeeeeeeeeee123", 5);
		ctrl.addNota(nota);
		assertEquals(1, ctrl.getNote().size());
		assertEquals(1, ctrl.getNote().get(0).getNrmatricol(),0.00001);
		assertEquals(5, ctrl.getNote().get(0).getNota(),0.00001);
		assertEquals("Materieeeeeeeeeee123", ctrl.getNote().get(0).getMaterie());
	}


}
