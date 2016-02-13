package es.cgmarcos.pacman.persistence.dao;

import es.cgmarcos.pacman.beans.Clinica;

import java.util.List;

public interface ClinicaRemitenteDAO {


    void insertClinicaRemitente(Clinica clinica);

    Clinica selectClinicaRemitenteByCod(int codClinica);

    List<Clinica> selectAllClinicasRemitentes();

    int updateClinicaRemitente(Clinica clinica);

    int deleteClinicaRemitente(int codClinica);
}
