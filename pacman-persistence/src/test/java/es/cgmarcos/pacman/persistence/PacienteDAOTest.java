package es.cgmarcos.pacman.persistence;

import es.cgmarcos.pacman.beans.Paciente;
import es.cgmarcos.pacman.persistence.dao.PacienteDAO;
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
public class PacienteDAOTest {
    private static final Logger logger = LoggerFactory.getLogger(MedicoAPDAOTest.class);
    @Autowired
    private PacienteDAO pacienteDAOTested;

    public static Paciente getPacienteTest() {
        Paciente paciente = new Paciente();
        paciente.setNombre("Segundo");
        paciente.setApellido1("Paciente");
        paciente.setApellido2("de Prueba");

        return paciente;
    }

    private int insertaPacienteTest() {
        try {
            Paciente paciente = getPacienteTest();
            pacienteDAOTested.insertPaciente(paciente);
            logger.info("Paciente de Test insertado: " + paciente.getCodPaciente());
            return paciente.getCodPaciente();
        } catch (Exception ex) {
            logger.info("Paciente de Test ya existente");
        }
        return 0;
    }

    @Test
    public void testCrearyEliminarPaciente() {
        Paciente pacienteTest = getPacienteTest();
        pacienteDAOTested.insertPaciente(pacienteTest);
        System.out.println("************************* ID: " + pacienteTest.getCodPaciente());
        logger.info("** Paciente creado correctamente. Lo eliminamos...");
        int i = pacienteDAOTested.deletePaciente(pacienteTest.getCodPaciente());
        assertThat(i, is(1));

    }

    @Test
    public void testGetPacienteByCod() {
        // Creamos paciente y lo buscamos:
        int codPaciente = insertaPacienteTest();

        Paciente paciente = pacienteDAOTested.selectPacienteByCod(codPaciente);
        logger.info("** Resultado Query: " + paciente);
        assertThat(paciente, is(notNullValue(Paciente.class)));
        assertThat(paciente.getCodPaciente(), is(codPaciente));
        logger.info("Paciente recuperado: " + paciente.toString());
    }

    @Test
    public void testAllPacientes() {
        // Insertamos al menos un paciente.
        insertaPacienteTest();

        List<Paciente> pacientes = pacienteDAOTested.selectAllPacientes();
        assertThat(pacientes.size(), is(greaterThan(0)));
        logger.info("Recuperados " + pacientes.size() + " pacientes. Datos del primer paciente: " + pacientes.get(0).toString());

    }

    @Test
    public void testUpdatePaciente() {
        int codPaciente = insertaPacienteTest();

        Paciente paciente = pacienteDAOTested.selectPacienteByCod(codPaciente);
        assertThat(paciente, is(not(nullValue(Paciente.class))));
        paciente.setApellido1("Apellido Modificado");
        pacienteDAOTested.updatePaciente(paciente);
        paciente = pacienteDAOTested.selectPacienteByCod(codPaciente);
        assertThat(paciente.getApellido1(), is("Apellido Modificado"));

    }


}