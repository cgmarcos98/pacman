package es.cgmarcos.pacman.persistence.dao;

import es.cgmarcos.pacman.beans.Clinica;

import java.util.List;

public interface ClinicaAPDAO {


    void insertClinicaAP(Clinica clinica);

    Clinica selectClinicaAPByCod(int codClinica);

    List<Clinica> selectAllClinicasAP();

    int updateClinicaAP(Clinica clinica);

    int deleteClinicaAP(int codClinica);
}
