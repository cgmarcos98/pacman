package es.cgmarcos.pacman.persistence;

import es.cgmarcos.pacman.beans.Clinica;
import es.cgmarcos.pacman.persistence.dao.ClinicaAPDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context-persistence.xml")
@Transactional
public class ClinicaAPDAOTest {
    private static final Logger logger = LoggerFactory.getLogger(ClinicaAPDAOTest.class);
    @Autowired
    private ClinicaAPDAO clinicaDAOTested;

    public static Clinica getClinicaAPTest() {
        Clinica clinica = new Clinica();
        clinica.setNombre("CLINICA AP 01");
        clinica.setDescripcion("Detalles clinica");

        return clinica;
    }

    private int insertaClinicaTest() {
        try {
            Clinica clinica = getClinicaAPTest();
            clinicaDAOTested.insertClinicaAP(clinica);
            logger.info("Clinica de test insertado");
            return clinica.getCodClinica();
        } catch (Exception ex) {
            logger.info("Clinica de test ya existente");
        }
        return 0;
    }

    @Test
    public void testCrearyEliminarClinicaAP() {
        Clinica clinica = getClinicaAPTest();
        clinicaDAOTested.insertClinicaAP(clinica);
        logger.info("** Clinica AP creada correctamente");
        int result = clinicaDAOTested.deleteClinicaAP(clinica.getCodClinica());
        assertThat(result, is(1));
    }

    @Test
    public void testGetClinicaAPByCod() {
        // Creamos clinica y la buscamos:
        int codClinica = insertaClinicaTest();

        Clinica clinica = clinicaDAOTested.selectClinicaAPByCod(codClinica);
        logger.info("** Resultado Query: " + clinica);
        assertThat(clinica, is(notNullValue(Clinica.class)));
        assertThat(clinica.getCodClinica(), is(codClinica));
        logger.info("Clinica recuperada: " + clinica.toString());

    }

    @Test
    public void testAllClinicas() {
        // Insertamos al menos una clinica.
        insertaClinicaTest();

        List<Clinica> clinicas = clinicaDAOTested.selectAllClinicasAP();
        assertThat(clinicas.size(), is(greaterThan(0)));
        logger.info("Recuperadas " + clinicas.size() + " clinicas. Datos de la primera clinica: " + clinicas.get(0).toString());

    }

    @Test
    public void testUpdateClinica() {

        int codClinica = insertaClinicaTest();

        Clinica clinica = clinicaDAOTested.selectClinicaAPByCod(codClinica);
        assertThat(clinica, is(not(nullValue(Clinica.class))));
        clinica.setNombre("Clinica AP Modificada");
        clinicaDAOTested.updateClinicaAP(clinica);
        clinica = clinicaDAOTested.selectClinicaAPByCod(codClinica);
        assertThat(clinica.getNombre(), is("Clinica AP Modificada"));

    }


}