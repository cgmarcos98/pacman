package es.cgmarcos.pacman.persistence.dao;

import es.cgmarcos.pacman.beans.Medico;

import java.util.List;

public interface MedicoAPDAO {

    void insertMedicoAP(Medico medico);

    Medico selectMedicoAPByCod(int codMedico);

    List<Medico> selectAllMedicosAP();

    void updateMedicoAP(Medico medico);

    int deleteMedicoAP(int codMedico);
}
