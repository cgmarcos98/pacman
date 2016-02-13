package es.cgmarcos.pacman.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Locale;

@Component
public class Clinica implements Serializable {

    private static final long serialVersionUID = -6159053853178194127L;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    private int codClinica;
    private String nombre;
    private String descripcion;

    public int getCodClinica() {
        return codClinica;
    }

    public void setCodClinica(int codClinica) {
        this.codClinica = codClinica;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void validate() {
        validate(false);
    }

    public void validate(boolean validateAutogenerates) {
        if (validateAutogenerates) {
            if (codClinica == 0) {
                throw new IllegalArgumentException(messageSource.getMessage("error.clinica.codClinica.nulo", null, Locale.getDefault()));
            }
        }
        if (nombre == null) {
            throw new IllegalArgumentException(messageSource.getMessage("error.clinica.nombre.nulo", null, Locale.getDefault()));
        }


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Clinica)) return false;

        Clinica clinica = (Clinica) o;

        if (codClinica != clinica.codClinica) return false;
        if (!nombre.equals(clinica.nombre)) return false;
        return !(descripcion != null ? !descripcion.equals(clinica.descripcion) : clinica.descripcion != null);

    }

    @Override
    public int hashCode() {
        int result = codClinica;
        result = 31 * result + nombre.hashCode();
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Clinica{" +
                "codClinica='" + codClinica + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
