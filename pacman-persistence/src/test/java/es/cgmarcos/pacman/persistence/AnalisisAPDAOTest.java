package es.cgmarcos.pacman.persistence;

import es.cgmarcos.pacman.beans.AnalisisAP;
import es.cgmarcos.pacman.beans.Clinica;
import es.cgmarcos.pacman.beans.Medico;
import es.cgmarcos.pacman.beans.Paciente;
import es.cgmarcos.pacman.persistence.dao.*;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-persistence.xml")
@Transactional
public class AnalisisAPDAOTest {
    private static final String FECHA_REGISTRO_TEST = (new SimpleDateFormat("DD/MM/YYYY")).format(new GregorianCalendar().getTime());
    private static final Logger logger = LoggerFactory.getLogger(AnalisisAPDAOTest.class);
    @Autowired
    private AnalisisAPDAO analisisDAOTested;
    @Autowired
    private PacienteDAO pacienteDAO;
    @Autowired
    private ClinicaAPDAO clinicaAPDAO;
    @Autowired
    private MedicoAPDAO medicoAPDAO;
    @Autowired
    private ClinicaRemitenteDAO clinicaRemitenteDAO;
    @Autowired
    private MedicoRemitenteDAO medicoRemitenteDAO;
    private Paciente pacienteTest;
    private Medico medicoAPTest;
    private Clinica clinicaAPTest;
    private Medico medicoRemitenteTest;
    private Clinica clinicaRemitenteTest;

    public AnalisisAP getAnalisisTest() {
        AnalisisAP analisis = new AnalisisAP();
        analisis.setFechaRegistro(FECHA_REGISTRO_TEST);
        analisis.setPaciente(pacienteTest);
        analisis.setMedicoAP(medicoAPTest);
        analisis.setClinicaAP(clinicaAPTest);
        analisis.setMedicoRemitente(medicoRemitenteTest);
        analisis.setClinicaRemitente(clinicaRemitenteTest);

        return analisis;
    }

    private int insertAnalisisTest() {
        AnalisisAP analisis = getAnalisisTest();
        insertElementosTest(analisis);
        analisisDAOTested.insertAnalisisAP(analisis);
        return analisis.getCodAnalisis();
    }

    private void insertElementosTest(AnalisisAP analisis) {
        pacienteTest = PacienteDAOTest.getPacienteTest();
        pacienteDAO.insertPaciente(pacienteTest);
        analisis.setPaciente(pacienteTest);
        logger.info("Paciente de Test insertado: " + pacienteTest.getCodPaciente());

        clinicaAPTest = ClinicaAPDAOTest.getClinicaAPTest();
        clinicaAPDAO.insertClinicaAP(clinicaAPTest);
        analisis.setClinicaAP(clinicaAPTest);
        logger.info("Clinica AP de Test insertada: " + clinicaAPTest.getCodClinica());

        medicoAPTest = MedicoAPDAOTest.getMedicoAPTest();
        medicoAPDAO.insertMedicoAP(medicoAPTest);
        analisis.setMedicoAP(medicoAPTest);
        logger.info("Medico AP de Test insertado: " + medicoAPTest.getCodMedico());

        clinicaRemitenteTest = ClinicaRemitenteDAOTest.getClinicaRemitenteTest();
        clinicaRemitenteDAO.insertClinicaRemitente(clinicaRemitenteTest);
        analisis.setClinicaRemitente(clinicaRemitenteTest);
        logger.info("Clinica Remitente de Test insertada: " + clinicaRemitenteTest.getCodClinica());

        medicoRemitenteTest = MedicoRemitenteDAOTest.getMedicoRemitenteTest();
        medicoRemitenteDAO.insertMedicoRemitente(medicoRemitenteTest);
        analisis.setMedicoRemitente(medicoRemitenteTest);
        logger.info("Medico Remitente de Test insertado: " + medicoRemitenteTest.getCodMedico());

    }


    @Test
    public void testCrearYEliminarAnalisis() {

        AnalisisAP analisisAP = getAnalisisTest();
        insertElementosTest(analisisAP);

        analisisDAOTested.insertAnalisisAP(analisisAP);
        logger.info("** Creado Analisis: " + analisisAP.getCodAnalisis());

        int result = analisisDAOTested.deleteAnalisisAP(analisisAP.getCodAnalisis());
        assertThat(result, is(1));

    }

    @Test
    public void testSelectAnalisisByCod() {
        int codAnalisis = insertAnalisisTest();

        AnalisisAP analisis = analisisDAOTested.selectAnalisisAPByCod(codAnalisis);
        assertThat(analisis.getCodAnalisis(), is(codAnalisis));
        assertThat(analisis.getPaciente(), is(not(nullValue())));
        assertThat(analisis.getClinicaAP(), is(not(nullValue())));
        assertThat(analisis.getMedicoAP(), is(not(nullValue())));
        assertThat(analisis.getClinicaRemitente(), is(not(nullValue())));
        assertThat(analisis.getMedicoAP(), is(not(nullValue())));


    }

    @Test
    public void testSelectAllAnalisis() {
        // Insertamos al menos una clinica.
        insertAnalisisTest();

        List<AnalisisAP> listAnalisis = analisisDAOTested.selectAllAnalisisAP();
        assertThat(listAnalisis.size(), Matchers.is(greaterThan(0)));
        logger.info("Recuperadas " + listAnalisis.size() + " analisis. Datos del primer analisis: " + listAnalisis.get(0).toString());

    }

    @Test
    public void testUpdateAnalisis() {

        int codAnalisis = insertAnalisisTest();

        AnalisisAP analisis = analisisDAOTested.selectAnalisisAPByCod(codAnalisis);
        assertThat(analisis, Matchers.is(Matchers.not(Matchers.nullValue(AnalisisAP.class))));
        String fecha = new SimpleDateFormat("DD/MM/YYYY").format(new Date());
        analisis.setFechaRegistro(fecha);
        analisisDAOTested.updateAnalisisAP(analisis);
        analisis = analisisDAOTested.selectAnalisisAPByCod(codAnalisis);
        assertThat(analisis.getFechaRegistro(), Matchers.is(fecha));

    }


}
