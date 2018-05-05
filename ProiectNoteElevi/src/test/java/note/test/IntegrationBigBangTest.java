package note.test;

import static org.junit.Assert.*;

import java.util.List;

import note.model.Corigent;
import note.model.Elev;
import note.model.Medie;
import note.model.Nota;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import note.utils.ClasaException;
import note.utils.Constants;

import note.controller.NoteController;

public class IntegrationBigBangTest {

    NoteController ctrl;

    @Before
    public void setUp() throws Exception {
        ctrl = new NoteController();
    }

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Test
    public void testA() throws ClasaException {
        //P->A
        Nota nota = new Nota(1000, "Mate", 4);
        ctrl.addNota(nota);
        assertEquals(1, ctrl.getNote().size());
        assertEquals(4, ctrl.getNote().get(0).getNota(),0.0001);
        assertEquals("Mate", ctrl.getNote().get(0).getMaterie());
    }

    @Test
    public void testB() throws ClasaException {
        //P->B
        Elev e1 = new Elev(1, "Elev1");
        ctrl.addElev(e1);
        Nota n1 = new Nota(1, "Mate", 4);
        ctrl.addNota(n1);
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
        List<Medie> rezultate = ctrl.calculeazaMedii();
        for (Medie m : rezultate) {
            if (m.getElev().getNrmatricol() == 1)
                assertEquals(m.getMedie(), 4, 0.0001);
        }
    }

    @Test
    public void testC() throws ClasaException {
        //P->C
        Elev e1 = new Elev(1, "Elev1");
        ctrl.addElev(e1);
        Nota n1 = new Nota(1, "Mate", 4);
        ctrl.addNota(n1);
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
        List<Corigent> corigenti = ctrl.getCorigenti();
        assertEquals(1, corigenti.get(0).getNrMaterii());
        assertEquals(1, corigenti.size());
        assertEquals("Elev1", corigenti.get(0).getNumeElev());
    }


    @Test
    public void testIntegation() throws ClasaException {
        //P->B->A->C B-valid A-valid C-valid
        Elev e1 = new Elev(1, "Elev1");
        ctrl.addElev(e1);
        Nota nota = new Nota(1, "Mate", 4);
        ctrl.addNota(nota);
        assertEquals(1, ctrl.getNote().size());
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
        List<Medie> rezultate = ctrl.calculeazaMedii();
        assertEquals(1, rezultate.size());
        assertEquals(4, rezultate.get(0).getMedie(),0.00001);
        List<Corigent> corigenti = ctrl.getCorigenti();
        assertEquals(corigenti.size(), 1);

    }

}
	/*@Test
	public void test10() {
		//P->B->A->C B-valid A-valid C-invalid
	}
*/

