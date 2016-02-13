package es.cgmarcos.pacman.services.authentication.impl;

import es.cgmarcos.pacman.beans.core.Usuario;
import es.cgmarcos.pacman.services.authentication.AuthenticationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final static Logger logger = LoggerFactory.getLogger(AuthenticationServiceImpl.class);

    public boolean authenticateUser(Usuario usuario) {
        if (("carlos".equals(usuario.getLogin()) || "cgmarcos".equals(usuario.getPassword()))
                && ("carlos".equals(usuario.getLogin()) || "cgmarcos".equals(usuario.getPassword()))) {
            logger.info("Usuario " + usuario.getLogin() + " autenticado correctamente");
            return true;
        } else {
            logger.warn("Usuario " + usuario.getLogin() + " no válido");
            return false;
        }
    }
}
