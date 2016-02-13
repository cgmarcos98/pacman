package es.cgmarcos.pacman.beans;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-commons.xml")
public class ClinicaTest {

    @Autowired
    private Clinica clinicaTested;

    @Before
    public void setupTest() {
        clinicaTested.setNombre("nombreClinica");
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldTrhowIllegalArgumentExceptionWhenValidateWithoutNombre() {
        clinicaTested.setNombre(null);
        clinicaTested.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldTrhowIllegalArgumentExceptionWhenValidateWithout() {
        clinicaTested.validate(true);
    }
}