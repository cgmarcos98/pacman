package es.cgmarcos.pacman.persistence.dao;

import es.cgmarcos.pacman.beans.Medico;

import java.util.List;

public interface MedicoRemitenteDAO {

    void insertMedicoRemitente(Medico medico);

    Medico selectMedicoRemitenteByCod(int codMedico);

    List<Medico> selectAllMedicosRemitentes();

    int updateMedicoRemitente(Medico medico);

    int deleteMedicoRemitente(int codMedico);
}
