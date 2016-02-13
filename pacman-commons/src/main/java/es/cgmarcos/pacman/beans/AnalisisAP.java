package es.cgmarcos.pacman.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Locale;

@Component
public class AnalisisAP implements Serializable {


    private static final long serialVersionUID = 8313801154594465765L;

    private int codAnalisis;
    private String fechaRegistro;
    /**
     * Formato DD/MM/YYYY
     */

    private Paciente paciente;
    private Medico medicoRemitente;
    private Clinica clinicaRemitente;

    private Medico medicoAP;
    private Clinica clinicaAP;

    @Autowired
    private ResourceBundleMessageSource messageSource;

    public int getCodAnalisis() {
        return codAnalisis;
    }

    public void setCodAnalisis(int codAnalisis) {
        this.codAnalisis = codAnalisis;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medico getMedicoRemitente() {
        return medicoRemitente;
    }

    public void setMedicoRemitente(Medico medicoRemitente) {
        this.medicoRemitente = medicoRemitente;
    }

    public Clinica getClinicaRemitente() {
        return clinicaRemitente;
    }

    public void setClinicaRemitente(Clinica clinicaRemitente) {
        this.clinicaRemitente = clinicaRemitente;
    }

    public Medico getMedicoAP() {
        return medicoAP;
    }

    public void setMedicoAP(Medico medicoAP) {
        this.medicoAP = medicoAP;
    }

    public Clinica getClinicaAP() {
        return clinicaAP;
    }

    public void setClinicaAP(Clinica clinicaAP) {
        this.clinicaAP = clinicaAP;
    }

    /**
     * Validación general de los valores del objeto AnalisisAP
     */
    public void validate() {
        validate(false);
    }

    public void validate(boolean validateAutogenerates) {

        if (validateAutogenerates) {
            if (codAnalisis == 0) {
                throw new IllegalArgumentException(messageSource.getMessage("error.analisisap.codAnalisis.nulo", null, Locale.getDefault()));
            }
            if (fechaRegistro == null) {
                throw new IllegalArgumentException(messageSource.getMessage("error.analisisap.fechaRegistro.nulo", null, Locale.getDefault()));
            }
        }

        if (paciente == null) {
            throw new IllegalArgumentException(messageSource.getMessage("error.analisisap.paciente.nulo", null, Locale.getDefault()));
        }
        if (medicoRemitente == null) {
            throw new IllegalArgumentException(messageSource.getMessage("error.analisisap.medicoRemitente.nulo", null, Locale.getDefault()));
        }
        if (clinicaRemitente == null) {
            throw new IllegalArgumentException(messageSource.getMessage("error.analisisap.clinicaRemitente.nulo", null, Locale.getDefault()));
        }
        if (medicoAP == null) {
            throw new IllegalArgumentException(messageSource.getMessage("error.analisisap.medicoAP.nulo", null, Locale.getDefault()));
        }
        if (clinicaAP == null) {
            throw new IllegalArgumentException(messageSource.getMessage("error.analisisap.clinicaAP.nulo", null, Locale.getDefault()));
        }

        paciente.validate();
        medicoRemitente.validate();
        clinicaRemitente.validate();
        medicoAP.validate();
        clinicaAP.validate();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnalisisAP)) return false;

        AnalisisAP that = (AnalisisAP) o;

        if (codAnalisis != that.codAnalisis) return false;
        if (!fechaRegistro.equals(that.fechaRegistro)) return false;
        return paciente.equals(that.paciente);

    }

    @Override
    public int hashCode() {
        int result = codAnalisis;
        result = 31 * result + (fechaRegistro != null ? fechaRegistro.hashCode() : 0);
        result = 31 * result + paciente.hashCode();
        result = 31 * result + medicoRemitente.hashCode();
        result = 31 * result + clinicaRemitente.hashCode();
        result = 31 * result + medicoAP.hashCode();
        result = 31 * result + clinicaAP.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "AnalisisAP{" +
                "codAnalisis=" + codAnalisis +
                ", fechaRegistro=" + fechaRegistro +
                ", paciente=" + paciente +
                ", medicoRemitente=" + medicoRemitente +
                ", clinicaRemitente=" + clinicaRemitente +
                ", medicoAP=" + medicoAP +
                ", clinicaAP=" + clinicaAP +
                '}';
    }
}
