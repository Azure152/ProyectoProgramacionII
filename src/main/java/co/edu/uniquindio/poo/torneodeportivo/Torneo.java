package co.edu.uniquindio.poo.torneodeportivo;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

import co.edu.uniquindio.poo.torneodeportivo.participante.Equipo;
import co.edu.uniquindio.poo.torneodeportivo.participante.Jugador;

public class Torneo
{
    /**
     * nombre del torneo
     */
    private final String nombre;

    /**
     * fecha de inicio del torneo
     */
    private LocalDate fechaInicioTorneo;

    /**
     * fecha de inicio de las incripciones
     */
    private LocalDate fechaInicioInscripciones;

    /**
     * fecha de cierre de las incripciones
     */
    private LocalDate fechaCierreInscripciones;

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
     * tipo del torneo
     */
    private final TipoTorneo tipoTorneo;

    /**
     * equipos participantes/inscritos
     */
    private Collection<Equipo> equipos;

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
        int valorInscripcion,
        TipoTorneo tipoTorneo
    ) {
        assert nombre != null;
        assert fechaInicioTorneo != null;
        assert fechaInicioInscripciones != null;
        assert fechaCierreInscripciones != null;
        assert numeroMaximoParticipantes > 0;
        assert limiteEdad >= 0;
        assert valorInscripcion >= 0;
        assert tipoTorneo != null;
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
        this.tipoTorneo = tipoTorneo;
        this.equipos = new LinkedList<>();
    }

    /**
     * establece la fecha de inicio del torneo
     * 
     * @param inicioTorneo la nueva fecha de inicio del torneo
     */
    public void setFechaInicioTorneo(LocalDate inicioTorneo)
    {
        assert inicioTorneo != null;
        assert inicioTorneo.isAfter(LocalDate.now());
        assert inicioTorneo.isAfter(this.fechaCierreInscripciones);

        this.fechaInicioTorneo = inicioTorneo;
    }

    /**
     * establece la fecha de inicio para las incripciones
     * 
     * @param inicioInscripciones nueva fecha de inicio de inscripciones
     */
    public void setFechaInicioInscripciones(LocalDate inicioInscripciones)
    {
        assert inicioInscripciones != null;
        assert inicioInscripciones.isBefore(this.fechaInicioTorneo);
        assert inicioInscripciones.isBefore(this.fechaCierreInscripciones);

        this.fechaInicioInscripciones = inicioInscripciones;
    }

    /**
     * establece la fecha de cierre para las incripciones
     * 
     * @param cierreInscripciones nueva fecha de cierre de inscripciones
     */
    public void setFechaCierreInscripciones(LocalDate cierreInscripciones)
    {
        assert cierreInscripciones != null;
        assert cierreInscripciones.isAfter(this.fechaInicioInscripciones);
        assert cierreInscripciones.isBefore(this.fechaInicioTorneo);

        this.fechaCierreInscripciones = cierreInscripciones;
    }

    /**
     * registra un equipo al torneo
     * 
     * @param equipo equipo a registrar
     */
    public void registrarEquipo(Equipo equipo)
    {
        assert this.buscarEquipo(equipo.getNombre()).isEmpty();
        assert this.equipos.size() < this.numeroMaximoParticipantes;
        this.validarInscripcionesAbiertas();
        
        this.equipos.add(equipo);
    }

    /**
     * busca un equipo usando el nombre
     * 
     * @param nombre nombre del equipo
     * 
     * @return un equipo coincidente
     */
    public Optional<Equipo> buscarEquipo(String nombre)
    {
        return this.equipos.stream().filter(e -> e.getNombre().equalsIgnoreCase(nombre)).findAny();
    }

    /**
     * registra un jugador en un equipo
     * 
     * @param equipo instancia de equipo en la que registrar
     * @param jugador instancia de jugador a registrar
     */
    public void registrarJugador(Equipo equipo, Jugador jugador)
    {
        this.validarJugadorInexistente(jugador);
        assert this.limiteEdad == 0 || jugador.calcularEdad() <= this.limiteEdad;
        this.validarInscripcionesAbiertas();

        equipo.registrarJugador(jugador);
    }

    /**
     * registra un jugador en un equipo registrado usando el nombre
     * 
     * @param nombreEquipo nombre del equipo
     * @param jugador instancia de jugador a registrar
     */
    public void registrarJugador(String nombreEquipo, Jugador jugador)
    {
        var equipo = this.buscarEquipo(nombreEquipo);

        assert equipo.isPresent();

        this.registrarJugador(equipo.get(), jugador);
    }

    /**
     * valida que las inscripciones se encuentren abiertas
     */
    private void validarInscripcionesAbiertas()
    {
        assert LocalDate.now().isEqual(this.fechaInicioInscripciones) || LocalDate.now().isAfter(this.fechaInicioInscripciones);
        assert LocalDate.now().isBefore(this.fechaCierreInscripciones);
    }

    /**
     * valida que un jugador no se encuentre registrado
     */
    private void validarJugadorInexistente(Jugador jugador)
    {
        this.equipos.stream().iterator().forEachRemaining((e) -> {
            assert ! e.jugadorExistente(jugador);
            assert ! e.jugadorExistentePorCorreo(jugador.getEmail());
        });
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

    /**
     * obtiene el tipo de torneo
     * 
     * @return tipo de torneo
     */
    public TipoTorneo getTipoTorneo()
    {
        return tipoTorneo;
    }

    /**
     * obtiene los equipos inscritos al torneo
     * 
     * @return equipos inscriptos
     */
    public Collection<Equipo> getEquipos()
    {
        return equipos;
    }
}
