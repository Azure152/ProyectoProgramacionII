package co.edu.uniquindio.poo.torneodeportivo;

import java.time.LocalDate;

public class Torneo
{
    /**
     * nombre del torneo
     */
    private final String nombre;

    /**
     * fecha de inicio del torneo
     */
    private final LocalDate fechaInicioTorneo;

    /**
     * fecha de inicio de las incripciones
     */
    private final LocalDate fechaInicioInscripciones;

    /**
     * fecha de cierre de las incripciones
     */
    private final LocalDate fechaCierreInscripciones;

    /**
     * numero maximo de participantes
     */
    private final byte numeroMaximoParticipantes;

    /**
     * limite maximo de edad para participantes
     */
    private final byte limiteEdad;

    /**
     * valor de la inscripcion
     */
    private final int valorInscripcion;

    /**
     * crea una instancia de Torneo
     * 
     * @param nombre nombre del torneo
     * @param fechaInicioTorneo fecha de inicio del torneo
     * @param fechaInicioInscripciones fecha de inicio de las incripciones
     * @param fechaCierreInscripciones fecha de cierre de las inscripciones
     * @param numeroMaximoParticipantes numero maximo de participantes
     * @param limiteEdad limite maximo de edad
     * @param valorInscripcion valor de la inscripcion
     */
    public Torneo(
        String nombre,
        LocalDate fechaInicioTorneo,
        LocalDate fechaInicioInscripciones,
        LocalDate fechaCierreInscripciones,
        byte numeroMaximoParticipantes,
        byte limiteEdad,
        int valorInscripcion
    ) {
        assert nombre != null;
        assert fechaInicioTorneo != null;
        assert fechaInicioInscripciones != null;
        assert fechaCierreInscripciones != null;
        assert numeroMaximoParticipantes > 0;
        assert limiteEdad >= 0;
        assert valorInscripcion >= 0;
        assert fechaInicioTorneo.isAfter(LocalDate.now());
        assert fechaInicioInscripciones.isBefore(fechaInicioTorneo);
        assert fechaCierreInscripciones.isAfter(fechaInicioInscripciones) && fechaCierreInscripciones.isBefore(fechaInicioTorneo);

        this.nombre = nombre;
        this.fechaInicioTorneo = fechaInicioTorneo;
        this.fechaInicioInscripciones = fechaInicioInscripciones;
        this.fechaCierreInscripciones = fechaCierreInscripciones;
        this.numeroMaximoParticipantes = numeroMaximoParticipantes;
        this.limiteEdad = limiteEdad;
        this.valorInscripcion = valorInscripcion;
    }

    /**
     * obtiene el nombre del torneo
     * 
     * @return nombre del torneo
     */
    public String getNombre()
    {
        return nombre;
    }

    /**
     * obtiene la fecha de inicio del torneo
     * 
     * @return fecha de inicio del torneo
     */
    public LocalDate getFechaInicioTorneo()
    {
        return fechaInicioTorneo;
    }

    /**
     * obtiene la fecha de inicio de inscripciones
     * 
     * @return fecha de inicio para inscripciones
     */
    public LocalDate getFechaInicioInscripciones()
    {
        return fechaInicioInscripciones;
    }

    /**
     * obtiene la fecha de cierre de inscripciones
     * 
     * @return fecha de cierre para inscripciones
     */
    public LocalDate getFechaCierreInscripciones()
    {
        return fechaCierreInscripciones;
    }

    /**
     * obtiene el numero maximo de participantes permitidos
     * 
     * @return numero maximo de participantes
     */
    public byte getNumeroMaximoParticipantes()
    {
        return numeroMaximoParticipantes;
    }

    /**
     * obtiene el limite de edad para jugadores
     * 
     * @return limite de edad maxima para los participantes
     */
    public byte getLimiteEdad()
    {
        return limiteEdad;
    }

    /**
     * obtiene el valor de la inscripcion para el torneo
     * 
     * @return valor de la inscripcion
     */
    public int getValorInscripcion()
    {
        return valorInscripcion;
    }
}
