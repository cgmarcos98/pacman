package es.cgmarcos.pacman.web.models;

import es.cgmarcos.pacman.beans.core.Usuario;
import es.cgmarcos.pacman.services.authentication.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.Locale;

@Component("loginBean")
@ManagedBean(name = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    @Autowired
    private ResourceBundleMessageSource messageSourceWeb;

    @Qualifier("authenticationServiceImpl")
    @Autowired
    private AuthenticationService authService;

    private static final long serialVersionUID = 1L;

    private Usuario usuario;

    private Usuario getUsuario() {
        if (usuario == null) {
            usuario = new Usuario();
        }
        return usuario;
    }

    public String getLogin() {
        return getUsuario().getLogin();
    }

    public void setLogin(String login) {
        getUsuario().setLogin(login);
    }

    public String getPassword() {
        return getUsuario().getPassword();
    }

    public void setPassword(String password) {
        getUsuario().setPassword(password);
    }

    public String validateLogin() {
        if (authService.authenticateUser(getUsuario())) {
            return messageSourceWeb.getMessage("login.usuario.ok", null, Locale.getDefault());
        } else {
            return messageSourceWeb.getMessage("login.usuario.error", null, Locale.getDefault());
        }
    }


}