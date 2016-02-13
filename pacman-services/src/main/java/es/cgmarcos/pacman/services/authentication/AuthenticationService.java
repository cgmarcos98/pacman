package es.cgmarcos.pacman.services.authentication;

import es.cgmarcos.pacman.beans.core.Usuario;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    boolean authenticateUser(Usuario usuario);
}
