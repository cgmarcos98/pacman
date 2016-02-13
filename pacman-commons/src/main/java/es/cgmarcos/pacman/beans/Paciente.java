package es.cgmarcos.pacman.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Locale;

@Component
public class Paciente implements Serializable {

    private static final long serialVersionUID = -8390195815628595071L;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    private int codPaciente;
    private String nombre;
    private String apellido1;
    private String apellido2;

    public int getCodPaciente() {
        return codPaciente;
    }

    public void setCodPaciente(int codPaciente) {
        this.codPaciente = codPaciente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public void validate() {
        validate(false);
    }

    public void validate(boolean validateAutogenerates) {
        if (validateAutogenerates) {
            if (codPaciente == 0) {
                throw new IllegalArgumentException(messageSource.getMessage("error.paciente.codPaciente.nulo", null, Locale.getDefault()));
            }
        }
        if (nombre == null) {
            throw new IllegalArgumentException(messageSource.getMessage("error.paciente.nombre.nulo", null, Locale.getDefault()));
        }
        if (apellido1 == null) {
            throw new IllegalArgumentException(messageSource.getMessage("error.paciente.apellido1.nulo", null, Locale.getDefault()));
        }

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Paciente)) return false;

        Paciente paciente = (Paciente) o;

        if (codPaciente != paciente.codPaciente) return false;
        if (!nombre.equals(paciente.nombre)) return false;
        if (!apellido1.equals(paciente.apellido1)) return false;
        return !(apellido2 != null ? !apellido2.equals(paciente.apellido2) : paciente.apellido2 != null);

    }

    @Override
    public int hashCode() {
        int result = codPaciente;
        result = 31 * result + nombre.hashCode();
        result = 31 * result + apellido1.hashCode();
        result = 31 * result + (apellido2 != null ? apellido2.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Paciente{" +
                "codPaciente='" + codPaciente + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                '}';
    }
}
