package es.cgmarcos.pacman.beans;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-commons.xml")
public class PacienteTest {

    @Autowired
    private Paciente pacienteTest;

    @Before
    public void setUpTest() {
        pacienteTest.setNombre("Nombre_Paciente");
        pacienteTest.setApellido1("Apellido1");
        pacienteTest.setApellido2("Apellido2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenValidateWithoutNombre() {
        pacienteTest.setNombre(null);
        pacienteTest.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenValidateWithoutApellido1() {
        pacienteTest.setApellido1(null);
        pacienteTest.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenValidateAutogeneratesWithoutCodPaciente() {
        pacienteTest.setCodPaciente(0);
        pacienteTest.validate(true);
    }

}