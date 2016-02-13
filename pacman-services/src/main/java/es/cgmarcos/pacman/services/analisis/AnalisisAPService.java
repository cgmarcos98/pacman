package es.cgmarcos.pacman.services.analisis;

import es.cgmarcos.pacman.beans.AnalisisAP;
import org.springframework.stereotype.Service;

@Service
public interface AnalisisAPService {

    AnalisisAP crearAnalisisAP(AnalisisAP analisis);

}
