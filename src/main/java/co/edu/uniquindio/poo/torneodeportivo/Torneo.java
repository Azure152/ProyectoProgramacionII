package co.edu.uniquindio.poo.torneodeportivo;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Optional;

import co.edu.uniquindio.poo.torneodeportivo.participante.Equipo;
import co.edu.uniquindio.poo.torneodeportivo.participante.Jugador;
import co.edu.uniquindio.poo.torneodeportivo.participante.Participante;

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
     * caracter del torneo
     */
    private final CaracterTorneo caracter;

    /**
     * equipos
     */
    private Collection<Participante> participantes;

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
        TipoTorneo tipoTorneo,
        CaracterTorneo caracter
    ) {
        assert nombre != null;
        assert fechaInicioTorneo != null;
        assert fechaInicioInscripciones != null;
        assert fechaCierreInscripciones != null;
        assert numeroMaximoParticipantes > 0;
        assert limiteEdad >= 0;
        assert valorInscripcion >= 0;
        assert tipoTorneo != null;
        assert caracter != null;
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
        this.caracter = caracter;
        this.participantes = new LinkedList<>();
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
     * registra un participante en el torneo
     * 
     * @param equipo 
     */
    public void registrarParticipante(Participante participante)
    {
        assert this.caracter.participanteValido(participante);
        assert this.participanteInexistente(participante.getNombreCompleto());
        assert this.getNumeroPartcipantes() < this.numeroMaximoParticipantes;

        if (this.caracter.equals(CaracterTorneo.INDIVIDUAL)) {
            Jugador jugador = (Jugador) participante;
            assert this.limiteEdad == 0 || jugador.calcularEdad() < this.limiteEdad;
            assert this.encontrarJugadorParticipantePorCorreo(jugador.getEmail()).isEmpty();
        }

        this.validarInscripcionesAbiertas();

        this.participantes.add(participante);
    }

    /**
     * comprueba si un participante ya se encuentra registrado
     * 
     * @param nombre nombre completo del participante
     * 
     * @return un booleano que representa si el participante no se encuentra registrado
     */
    private boolean participanteInexistente(String nombre)
    {
        return this.participantes.stream().filter(p -> p.getNombreCompleto().equals(nombre)).findAny().isEmpty();
    }

    /**
     * registra un jugador en un equipo
     * 
     * @param equipo instancia de equipo en la que registrar
     * @param jugador instancia de jugador a registrar
     */
    public void registrarJugador(Equipo equipo, Jugador jugador)
    {
        assert this.caracter.equals(CaracterTorneo.GRUPAL);

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
        assert this.caracter.equals(CaracterTorneo.GRUPAL);

        var equipo = this.participantes.stream().filter((p) -> {
            return p.getNombreCompleto().equals(nombreEquipo) && p instanceof Equipo;
        }).map(p -> (Equipo) p).findAny();

        assert equipo.isPresent();

        this.registrarJugador(equipo.get(), jugador);
    }

    /**
     * encuentra un jugador registrado usando el correo (caracter INDIVIDUAL)
     * 
     * @param email direccion de correo electronico
     */
    private Optional<Jugador> encontrarJugadorParticipantePorCorreo(String email)
    {
        assert this.caracter.equals(CaracterTorneo.INDIVIDUAL);

        return this.participantes.stream()
            .filter(p -> p instanceof Jugador j && j.getEmail().equalsIgnoreCase(email))
            .map(p -> (Jugador) p)
            .findAny();
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
     * 
     * @param jugador jugador a comprobar
     */
    private void validarJugadorInexistente(Jugador jugador)
    {
        this.participantes.stream()
            .filter(p -> p instanceof Equipo)
            .map(p -> (Equipo) p)
            .iterator().forEachRemaining((e) -> {
                assert ! e.jugadorExistente(jugador);
                assert ! e.jugadorExistentePorCorreo(jugador.getEmail());
            });
    }

    /**
     * obtiene la cantidad de participantes
     * 
     * @return cantidad de participantes registrados
     */
    public int getNumeroPartcipantes()
    {
        return this.participantes.size();
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
    public Collection<Participante> getParticipantes()
    {
        return this.participantes.stream().sorted(Comparator.comparing(Participante::getNombreCompleto)).toList();
    }
}
