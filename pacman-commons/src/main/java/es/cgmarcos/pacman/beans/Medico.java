package es.cgmarcos.pacman.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Locale;

@Component
public class Medico implements Serializable {

    private static final long serialVersionUID = 8166424153092333360L;

    @Autowired
    private ResourceBundleMessageSource messageSource;


    private int codMedico;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String nif;

    public int getCodMedico() {
        return codMedico;
    }

    public void setCodMedico(int codMedico) {
        this.codMedico = codMedico;
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

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medico)) return false;

        Medico medico = (Medico) o;

        if (codMedico != medico.codMedico) return false;
        if (!nombre.equals(medico.nombre)) return false;
        if (!apellido1.equals(medico.apellido1)) return false;
        if (apellido2 != null ? !apellido2.equals(medico.apellido2) : medico.apellido2 != null) return false;
        return nif.equals(medico.nif);

    }

    public void validate() {
        validate(false);
    }

    public void validate(boolean validateAutogenerates) {
        if (validateAutogenerates) {
            if (codMedico == 0) {
                throw new IllegalArgumentException(messageSource.getMessage("error.medico.codMedico.nulo", null, Locale.getDefault()));
            }
        }
        if (nombre == null) {
            throw new IllegalArgumentException(messageSource.getMessage("error.medico.nombre.nulo", null, Locale.getDefault()));
        }
        if (apellido1 == null) {
            throw new IllegalArgumentException(messageSource.getMessage("error.medico.apellido1.nulo", null, Locale.getDefault()));
        }
        if (nif == null) {
            throw new IllegalArgumentException(messageSource.getMessage("error.medico.nif.nulo", null, Locale.getDefault()));
        }

    }


    @Override
    public int hashCode() {
        int result = codMedico;
        result = 31 * result + nombre.hashCode();
        result = 31 * result + apellido1.hashCode();
        result = 31 * result + (apellido2 != null ? apellido2.hashCode() : 0);
        result = 31 * result + nif.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Medico{" +
                "codMedico='" + codMedico + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", nif='" + nif + '\'' +
                '}';
    }
}
