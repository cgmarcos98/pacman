package es.cgmarcos.pacman.services.analisis;

import es.cgmarcos.pacman.beans.AnalisisAP;
import es.cgmarcos.pacman.beans.Clinica;
import es.cgmarcos.pacman.beans.Medico;
import es.cgmarcos.pacman.beans.Paciente;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context-services.xml")
public class AnalisisAPServiceTest {

    @Autowired
    @Qualifier(value = "analisisAPServiceImpl")
    private AnalisisAPService analisisAPServiceTested;


    private AnalisisAP getAnalisisNuevoOK() {
        AnalisisAP analisis = new AnalisisAP();
        Paciente paciente = new Paciente();
        paciente.setCodPaciente(1);

        paciente.setNombre("Paciente 1");
        paciente.setApellido1("Apellido1");
        paciente.setApellido2("Apellido2");
        analisis.setPaciente(paciente);

        Clinica clinicaRemitente = new Clinica();
        clinicaRemitente.setCodClinica(1);
        clinicaRemitente.setNombre("Clinica Remitente");
        clinicaRemitente.setDescripcion("Detalles de la clinica Remitente");
        analisis.setClinicaRemitente(clinicaRemitente);

        Medico medRemitente = new Medico();
        medRemitente.setCodMedico(1);
        medRemitente.setNombre("Medico Remitente 1");
        medRemitente.setApellido1("Apellido1");
        medRemitente.setApellido2("Apellido2");
        medRemitente.setNif("11111111H");
        analisis.setMedicoRemitente(medRemitente);

        Clinica clinicaAP = new Clinica();
        clinicaAP.setCodClinica(1);
        clinicaAP.setNombre("Clinica AP");
        clinicaAP.setDescripcion("Detalles de la clinica AP");
        analisis.setClinicaAP(clinicaAP);

        Medico medicoAP = new Medico();
        medicoAP.setCodMedico(1);
        medicoAP.setNombre("MedicoAP");
        medicoAP.setApellido1("Apellido1");
        medicoAP.setApellido2("Apellido2");
        medicoAP.setNif("0000000X");
        analisis.setMedicoAP(medicoAP);

        return analisis;
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowIllegalArgumentExceptionWhenAnalisisIsNull() {
        AnalisisAP analisis = null;
        analisisAPServiceTested.crearAnalisisAP(analisis);
    }

    @Test
    public void shouldReturnAnalisisWithCodAnalisis() {
        AnalisisAP analisis = getAnalisisNuevoOK();

        analisis = analisisAPServiceTested.crearAnalisisAP(analisis);

        assertThat(analisis.getCodAnalisis(), not(is(0)));
    }

    @Test
    public void shouldReturnTodayDateWhenOriginalDateIsNull() {
        SimpleDateFormat sdf = new SimpleDateFormat("DD/MM/YYYY");
        AnalisisAP analisis = getAnalisisNuevoOK();

        analisis = analisisAPServiceTested.crearAnalisisAP(analisis);

        assertThat(analisis.getFechaRegistro(), not(nullValue()));
        assertThat(analisis.getFechaRegistro(), equalToIgnoringCase(sdf.format(new Date())));

    }


}