package es.cgmarcos.pacman.persistence.dao;

import es.cgmarcos.pacman.beans.AnalisisAP;

import java.util.List;

public interface AnalisisAPDAO {

    void insertAnalisisAP(AnalisisAP analisisAP);

    AnalisisAP selectAnalisisAPByCod(int cod);

    List<AnalisisAP> selectAllAnalisisAP();

    int updateAnalisisAP(AnalisisAP analisisAP);

    int deleteAnalisisAP(int codAnalisisAP);
}
