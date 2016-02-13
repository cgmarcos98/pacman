package es.cgmarcos.pacman.persistence;

import es.cgmarcos.pacman.beans.Medico;
import es.cgmarcos.pacman.persistence.dao.MedicoRemitenteDAO;
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
public class MedicoRemitenteDAOTest {

    private static final Logger logger = LoggerFactory.getLogger(MedicoRemitenteDAOTest.class);
    @Autowired
    private MedicoRemitenteDAO medicoRemitenteDAOTested;

    public static Medico getMedicoRemitenteTest() {
        Medico medicoRemitente = new Medico();
        medicoRemitente.setNombre("MedicoRemitente");
        medicoRemitente.setApellido1("de");
        medicoRemitente.setApellido2("Prueba");
        medicoRemitente.setNif("00000000H");

        return medicoRemitente;
    }

    private int insertaMedicoRemitenteTest() {
        try {
            Medico medico = getMedicoRemitenteTest();
            medicoRemitenteDAOTested.insertMedicoRemitente(medico);
            logger.info("Medico Remitente de Test Insertado");
            return medico.getCodMedico();
        } catch (Exception ex) {
            logger.info("Medico Remitente de Test ya existente");
        }
        return 0;
    }

    @Test
    public void testCrearyEliminarMedicoRemitente() {
        Medico medicoTest = getMedicoRemitenteTest();
        medicoRemitenteDAOTested.insertMedicoRemitente(medicoTest);
        logger.info("** Medico creado correctamente");
        int result = medicoRemitenteDAOTested.deleteMedicoRemitente(medicoTest.getCodMedico());
        assertThat(result, is(1));
    }

    @Test
    public void testGetMedicoByCod() {
        // Creamos medico y lo buscamos:
        int codMedico = insertaMedicoRemitenteTest();
        Medico medico = medicoRemitenteDAOTested.selectMedicoRemitenteByCod(codMedico);
        logger.info("** Resultado Query: " + medico);
        assertThat(medico, is(notNullValue(Medico.class)));
        assertThat(medico.getCodMedico(), is(codMedico));
        logger.info("Medico recuperado: " + medico.toString());
    }

    @Test
    public void testAllMedicos() {
        // Insertamos al menos un medico.
        insertaMedicoRemitenteTest();
        List<Medico> medicos = medicoRemitenteDAOTested.selectAllMedicosRemitentes();
        assertThat(medicos.size(), is(greaterThan(0)));
        logger.info("Recuperados " + medicos.size() + " medicos Remitente. Datos del primer medico: " + medicos.get(0).toString());
    }

    @Test
    public void testUpdateMedico() {
        int codMedico = insertaMedicoRemitenteTest();
        Medico medico = medicoRemitenteDAOTested.selectMedicoRemitenteByCod(codMedico);
        assertThat(medico, is(not(nullValue(Medico.class))));
        medico.setApellido1("Remitenteellido Modificado");
        medicoRemitenteDAOTested.updateMedicoRemitente(medico);
        medico = medicoRemitenteDAOTested.selectMedicoRemitenteByCod(codMedico);
        assertThat(medico.getApellido1(), is("Remitenteellido Modificado"));
    }


}

















