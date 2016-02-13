package es.cgmarcos.pacman.persistence;

import es.cgmarcos.pacman.beans.Medico;
import es.cgmarcos.pacman.persistence.dao.MedicoAPDAO;
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
public class MedicoAPDAOTest {

    private static final Logger logger = LoggerFactory.getLogger(MedicoAPDAOTest.class);
    @Autowired
    private MedicoAPDAO medicoAPDAOTested;

    public static Medico getMedicoAPTest() {
        Medico medicoAP = new Medico();
        medicoAP.setNombre("MedicoAP");
        medicoAP.setApellido1("de");
        medicoAP.setApellido2("Prueba");
        medicoAP.setNif("00000000H");

        return medicoAP;
    }

    private int insertaMedicoAPTest() {
        try {
            Medico medico = getMedicoAPTest();
            medicoAPDAOTested.insertMedicoAP(medico);
            logger.info("Medico AP de test insertado");
            return medico.getCodMedico();
        } catch (Exception ex) {
            logger.info("Medico AP de test ya existente");
        }
        return 0;
    }

    @Test
    public void testCrearyEliminarMedicoAP() {
        Medico medicoTest = getMedicoAPTest();
        medicoAPDAOTested.insertMedicoAP(medicoTest);
        logger.info("** Medico creado correctamente");
        int result = medicoAPDAOTested.deleteMedicoAP(medicoTest.getCodMedico());
        assertThat(result, is(1));
    }

    @Test
    public void testGetMedicoByCod() {
        // Creamos medico y lo buscamos:
        int codMedico = insertaMedicoAPTest();

        Medico medico = medicoAPDAOTested.selectMedicoAPByCod(codMedico);
        logger.info("** Resultado Query: " + medico);
        assertThat(medico, is(notNullValue(Medico.class)));
        assertThat(medico.getCodMedico(), is(codMedico));
        logger.info("Medico recuperado: " + medico.toString());
    }

    @Test
    public void testAllMedicos() {
        // Insertamos al menos un medico.
        insertaMedicoAPTest();

        List<Medico> medicos = medicoAPDAOTested.selectAllMedicosAP();
        assertThat(medicos.size(), is(greaterThan(0)));
        logger.info("Recuperados " + medicos.size() + " medicos AP. Datos del primer medico: " + medicos.get(0).toString());
    }

    @Test
    public void testUpdateMedico() {
        int codMedico = insertaMedicoAPTest();

        Medico medico = medicoAPDAOTested.selectMedicoAPByCod(codMedico);
        assertThat(medico, is(not(nullValue(Medico.class))));
        medico.setApellido1("Apellido Modificado");
        medicoAPDAOTested.updateMedicoAP(medico);
        medico = medicoAPDAOTested.selectMedicoAPByCod(codMedico);
        assertThat(medico.getApellido1(), is("Apellido Modificado"));

    }


}

















