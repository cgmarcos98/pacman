package es.cgmarcos.pacman.persistence.dao;

import es.cgmarcos.pacman.beans.Paciente;

import java.util.List;

public interface PacienteDAO {

    void insertPaciente(Paciente paciente);

    Paciente selectPacienteByCod(int cod);

    List<Paciente> selectAllPacientes();

    int updatePaciente(Paciente paciente);

    int deletePaciente(int codPaciente);
}
