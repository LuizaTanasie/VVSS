package note.test;

import note.controller.NoteController;
import note.model.Corigent;
import note.model.Elev;
import note.model.Medie;
import note.model.Nota;
import note.utils.ClasaException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class IntegrationTopDownTest {
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
        Nota nota = new Nota(1000, "Istorie", 9);
        ctrl.addNota(nota);
        assertEquals(1, ctrl.getNote().size());
        assertEquals(9, ctrl.getNote().get(0).getNota(),0.0001);
        assertEquals("Istorie", ctrl.getNote().get(0).getMaterie());
    }

    @Test
    public void testAB() throws ClasaException {
        //P->B->A
        Elev elev = new Elev(1000,"Elev");
        ctrl.addElev(elev);
        Nota nota = new Nota(1000, "Istorie", 9);
        ctrl.addNota(nota);
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
        List<Medie> rezultate = ctrl.calculeazaMedii();
        assertEquals(1, ctrl.getNote().size());
        assertEquals(1, rezultate.size());
        assertEquals(9, rezultate.get(0).getMedie(),0.0001);
        assertEquals(1000, rezultate.get(0).getElev().getNrmatricol());
    }

    @Test
    public void testABC() throws ClasaException {
        //P->B->A->C
        Elev elev = new Elev(1000,"Elev");
        ctrl.addElev(elev);
        Nota nota = new Nota(1000, "Istorie", 9);
        ctrl.addNota(nota);
        ctrl.creeazaClasa(ctrl.getElevi(), ctrl.getNote());
        List<Medie> rezultate = ctrl.calculeazaMedii();
        List<Corigent> corigenti = ctrl.getCorigenti();
        assertEquals(1, ctrl.getNote().size());
        assertEquals(1, rezultate.size());
        assertEquals(9, rezultate.get(0).getMedie(),0.0001);
        assertEquals(1000, rezultate.get(0).getElev().getNrmatricol());
        assertEquals(0, corigenti.size());
    }


}
