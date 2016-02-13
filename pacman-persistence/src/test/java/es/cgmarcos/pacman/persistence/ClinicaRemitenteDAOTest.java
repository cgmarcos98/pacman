package es.cgmarcos.pacman.persistence;

import es.cgmarcos.pacman.beans.Clinica;
import es.cgmarcos.pacman.persistence.dao.ClinicaRemitenteDAO;
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
public class ClinicaRemitenteDAOTest {
    private static Logger logger = LoggerFactory.getLogger(ClinicaRemitenteDAOTest.class);
    @Autowired
    private ClinicaRemitenteDAO clinicaDAOTested;

    public static Clinica getClinicaRemitenteTest() {
        Clinica clinica = new Clinica();
        clinica.setNombre("CLINICA RMTE 01");
        clinica.setDescripcion("Detalles clinica");

        return clinica;
    }

    private int insertaClinicaRemitenteTest() {
        try {
            Clinica clinica = getClinicaRemitenteTest();
            clinicaDAOTested.insertClinicaRemitente(clinica);
            logger.info("Clinica de test insertado");
            return clinica.getCodClinica();
        } catch (Exception ex) {
            logger.info("Clinica de test ya existente");
        }
        return 0;
    }

    @Test
    public void testCrearyEliminarClinicaRemitente() {
        Clinica clinica = getClinicaRemitenteTest();
        clinicaDAOTested.insertClinicaRemitente(clinica);
        logger.info("** Clinica Remitente creada correctamente");
        int result = clinicaDAOTested.deleteClinicaRemitente(clinica.getCodClinica());
        assertThat(result, is(1));
    }

    @Test
    public void testGetClinicaRemitenteByCod() {
        // Creamos clinica y la buscamos:
        int codClinica = insertaClinicaRemitenteTest();

        Clinica clinica = clinicaDAOTested.selectClinicaRemitenteByCod(codClinica);
        logger.info("** Resultado Query: " + clinica);
        assertThat(clinica, is(notNullValue(Clinica.class)));
        assertThat(clinica.getCodClinica(), is(codClinica));
        logger.info("Clinica recuperada: " + clinica.toString());
    }

    @Test
    public void testAllClinicas() {
        insertaClinicaRemitenteTest();

        List<Clinica> clinicas = clinicaDAOTested.selectAllClinicasRemitentes();
        assertThat(clinicas.size(), is(greaterThan(0)));
        logger.info("Recuperadas " + clinicas.size() + " clinicas. Datos de la primera clinica: " + clinicas.get(0).toString());

    }

    @Test
    public void testUpdateClinica() {

        int codClinica = insertaClinicaRemitenteTest();
        Clinica clinica = clinicaDAOTested.selectClinicaRemitenteByCod(codClinica);
        assertThat(clinica, is(not(nullValue(Clinica.class))));
        clinica.setNombre("Clinica Remitente Modificada");
        clinicaDAOTested.updateClinicaRemitente(clinica);
        clinica = clinicaDAOTested.selectClinicaRemitenteByCod(codClinica);
        assertThat(clinica.getNombre(), is("Clinica Remitente Modificada"));

    }


}