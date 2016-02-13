package es.cgmarcos.pacman.beans;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-commons.xml")
public class MedicoTest {

    @Autowired
    private Medico medicoTested;

    @Before
    public void setUpTest() {
        medicoTested.setNombre("Nombre_Medico");
        medicoTested.setApellido1("Apellido1");
        medicoTested.setNif("00000000H");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldTrowIllegalArgumentExceptionWhenValidateWithoutNombre() {
        medicoTested.setNombre(null);
        medicoTested.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldTrowIllegalArgumentExceptionWhenValidateWithoutApellido1() {
        medicoTested.setApellido1(null);
        medicoTested.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldTrowIllegalArgumentExceptionWhenValidateWithoutNIF() {
        medicoTested.setNif(null);
        medicoTested.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldTrowIllegalArgumentExceptionWhenValidateAutogeneratesWithoutCodMedico() {
        medicoTested.validate(true);
    }
}