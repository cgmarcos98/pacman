package es.cgmarcos.pacman.beans;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-commons.xml")
public class AnalisisAPTest {

    @Autowired
    private AnalisisAP analisisTested;

    @Before
    public void setUp() throws Exception {
        Paciente paciente = new Paciente();
        paciente.setCodPaciente(1);

        paciente.setNombre("Paciente 1");
        paciente.setApellido1("Apellido1");
        paciente.setApellido2("Apellido2");
        analisisTested.setPaciente(paciente);

        Clinica clinicaRemitente = new Clinica();
        clinicaRemitente.setCodClinica(1);
        clinicaRemitente.setNombre("Clinica Remitente");
        clinicaRemitente.setDescripcion("Detalles de la clinica Remitente");
        analisisTested.setClinicaRemitente(clinicaRemitente);

        Medico medRemitente = new Medico();
        medRemitente.setCodMedico(1);
        medRemitente.setNombre("Medico Remitente 1");
        medRemitente.setApellido1("Apellido1");
        medRemitente.setApellido2("Apellido2");
        medRemitente.setNif("11111111H");
        analisisTested.setMedicoRemitente(medRemitente);

        Clinica clinicaAP = new Clinica();
        clinicaAP.setCodClinica(1);
        clinicaAP.setNombre("Clinica AP");
        clinicaAP.setDescripcion("Detalles de la clinica AP");
        analisisTested.setClinicaAP(clinicaAP);

        Medico medicoAP = new Medico();
        medicoAP.setCodMedico(1);
        medicoAP.setNombre("MedicoAP");
        medicoAP.setApellido1("Apellido1");
        medicoAP.setApellido2("Apellido2");
        medicoAP.setNif("0000000X");
        analisisTested.setMedicoAP(medicoAP);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenValidateWithoutPaciente() {
        analisisTested.setPaciente(null);
        analisisTested.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenValidateWithoutClinicaRemitente() {
        analisisTested.setClinicaRemitente(null);
        analisisTested.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenValidateWithoutMedicoRemitente() {
        analisisTested.setMedicoRemitente(null);
        analisisTested.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenValidateWithoutClinicaAP() {
        analisisTested.setClinicaAP(null);
        analisisTested.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenValidateWithoutMedicoAP() {
        analisisTested.setMedicoAP(null);
        analisisTested.validate();
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenValidateAutogeneratesWithoutCodAnalisis() {
        SimpleDateFormat sdf = new SimpleDateFormat("DDMM/YYYY");
        analisisTested.setCodAnalisis(0);
        analisisTested.setFechaRegistro(sdf.format(new GregorianCalendar()));
        analisisTested.validate(true);
    }


    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenValidateAutogeneratesWithoutFechaRegistro() {
        analisisTested.setCodAnalisis(123);
        analisisTested.setFechaRegistro(null);
        analisisTested.validate(true);
    }


}