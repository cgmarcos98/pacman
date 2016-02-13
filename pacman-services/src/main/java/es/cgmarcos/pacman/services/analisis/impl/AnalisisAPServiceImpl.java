package es.cgmarcos.pacman.services.analisis.impl;

import es.cgmarcos.pacman.beans.AnalisisAP;
import es.cgmarcos.pacman.persistence.dao.AnalisisAPDAO;
import es.cgmarcos.pacman.services.analisis.AnalisisAPService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Locale;


@Service
public class AnalisisAPServiceImpl implements AnalisisAPService {

    private static final Logger logger = LoggerFactory.getLogger(AnalisisAPService.class);
    private static final ResourceBundleMessageSource messageSource;

    static {
        messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("locale/services-messages");

    }

    @Autowired
    private AnalisisAPDAO analisisAPDAO;


    @Transactional
    public AnalisisAP crearAnalisisAP(AnalisisAP analisis) {

        if (analisis == null) {
            throw new IllegalArgumentException(messageSource.getMessage("error.analisisService.analisis.nulo", null, Locale.getDefault()));
        }

        analisis.validate();

        if (analisis.getFechaRegistro() == null) {
            analisis.setFechaRegistro(new SimpleDateFormat("DD/MM/YYYY").format(new GregorianCalendar().getTime()));
        }

        analisisAPDAO.insertAnalisisAP(analisis);

        logger.info("Creado Nuevo Analisis AP: " + analisis.getCodAnalisis());

        return analisis;
    }


}
