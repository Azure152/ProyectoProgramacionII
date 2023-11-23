package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import co.edu.uniquindio.poo.torneodeportivo.participante.Equipo;
import co.edu.uniquindio.poo.torneodeportivo.participante.Jugador;
import co.edu.uniquindio.poo.torneodeportivo.persona.Persona;

public class RegistroJugadorTest
{
    /**
     * instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(RegistroJugadorTest.class.getName());

    /**
     * registrar un jugador desde equipo
     */
    @Test
    public void registrarJugadorEnEquipoDesdeEquipo()
    {
        LOG.info("Inicio prueba: registrar un jugador desde un equipo...");

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234", LocalDate.now().minusYears(15));

        var resultado = new LinkedList<>();
        resultado.add(jugador);

        assertDoesNotThrow(() -> equipo.registrarJugador(jugador));
        assertEquals(resultado, equipo.getJugadores());

        LOG.info("Fin prueba: registrar un jugador desde un equipo...");
    }

    /**
     * registrar un jugador desde equipo
     */
    @Test
    public void registrarJugadorEnEquipoDesdeTorneo()
    {
        LOG.info("Inicio prueba: registrar un jugador desde un torneo...");

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234", LocalDate.now().minusYears(15));

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(1),
            LocalDate.now().minusDays(15),
            LocalDate.now().plusDays(15),
            (byte) 24,
            (byte) 18,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.GRUPAL
        );

        torneo.registrarParticipante(equipo);

        var resultado = new LinkedList<>();
        resultado.add(jugador);

        assertDoesNotThrow(() -> torneo.registrarJugador(equipo, jugador));
        assertEquals(resultado, equipo.getJugadores());

        LOG.info("Fin prueba: registrar un jugador desde un torneo...");
    }

    /**
     * verificar que un Torneo sin limite de edad permita registrar jugadores
     */
    @Test
    public void registrarJugadorTorneoSinLimiteEdad()
    {
        LOG.info("Inicio prueba: registrar un jugador en un torneo sin limite de edad...");

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234", LocalDate.now().minusYears(21));

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(1),
            LocalDate.now().minusDays(15),
            LocalDate.now().plusDays(15),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.GRUPAL
        );

        torneo.registrarParticipante(equipo);

        var resultado = new LinkedList<>();
        resultado.add(jugador);

        assertDoesNotThrow(() -> torneo.registrarJugador(equipo, jugador));
        assertEquals(resultado, equipo.getJugadores());

        LOG.info("Fin prueba: registrar un jugador en un torneo sin limite de edad...");
    }

    /**
     * verifica que no se permita registrar un jugador que tenga una edad mayor a la permitida en el torneo
     */
    @Test
    public void registrarJugadorMayorLimiteEdadDesdeTorneo()
    {
        LOG.info("Inicio prueba: registrar un jugador con edad mayor al limite...");

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234", LocalDate.now().minusYears(21));

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(1),
            LocalDate.now().minusDays(15),
            LocalDate.now().plusDays(15),
            (byte) 24,
            (byte) 18,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.GRUPAL
        );

        torneo.registrarParticipante(equipo);

        assertThrows(Throwable.class, () -> torneo.registrarJugador(equipo, jugador));

        LOG.info("Fin prueba: registrar un jugador con edad mayor al limite...");
    }

    /**
     * verificar que Torneo no perimita la inscripcion de jugadores cuando las inscripciones hallan cerrado
     */
    @Test
    public void registrarJugadorInscripcionesCerradas()
    {
        LOG.info("Inicio prueba: registrar un jugador con inscripciones cerradas...");

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234", LocalDate.now().minusYears(15));

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(1),
            LocalDate.now().minusDays(15),
            LocalDate.now().plusDays(15),
            (byte) 24,
            (byte) 18,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.GRUPAL
        );

        torneo.registrarParticipante(equipo);
        torneo.setFechaCierreInscripciones(LocalDate.now().minusDays(1));

        assertThrows(Throwable.class, () -> torneo.registrarJugador(equipo, jugador));

        LOG.info("Fin prueba: registrar un jugador con inscripciones cerradas...");
    }

    /**
     * verificar que Equipo no perimita la inscripcion de jugadores con nombres y apellidos iguales 
     */
    @Test
    public void registrarJugadorNombreRepetidoDesdeEquipo()
    {
        LOG.info("Inicio prueba: registrar jugadores con nombres y apellidos iguales desde equipo...");

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234", LocalDate.now().minusYears(15));
        var jugador2 = new Jugador("Christian", "Candela", "ccandela@email.com", "6067431235", LocalDate.now().minusYears(15));

        equipo.registrarJugador(jugador);
        assertThrows(Throwable.class, () -> equipo.registrarJugador(jugador2));

        LOG.info("Fin prueba: registrar jugadores con nombres y apellidos iguales desde equipo...");
    }

    /**
     * verificar que Torneo no perimita la inscripcion de jugadores con nombres y apellidos iguales 
     */
    @Test
    public void registrarJugadorNombreRepetidoDesdeTorneo()
    {
        LOG.info("Inicio prueba: registrar jugadores con nombres y apellidos iguales desde torneo...");

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234", LocalDate.now().minusYears(15));
        var jugador2 = new Jugador("Christian", "Candela", "ccandela@email.com", "6067431235", LocalDate.now().minusYears(15));

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(1),
            LocalDate.now().minusDays(15),
            LocalDate.now().plusDays(15),
            (byte) 24,
            (byte) 18,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.GRUPAL
        );

        torneo.registrarParticipante(equipo);
        torneo.registrarJugador(equipo, jugador);

        assertThrows(Throwable.class, () -> torneo.registrarJugador(equipo, jugador2));

        LOG.info("Inicio prueba: registrar jugadores con nombres y apellidos iguales desde torneo...");
    }

    /**
     * verificar que Torneo no perimita la inscripcion de jugadores con nombres y apellidos iguales en diferentes equipos
     */
    @Test
    public void registrarJugadorNombreRepetidoDiferentesEquipos()
    {
        LOG.info("Inicio prueba: registrar jugadores con nombres y apellidos iguales desde torneo...");

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var equipo2 = new Equipo("Quindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234", LocalDate.now().minusYears(15));
        var jugador2 = new Jugador("Christian", "Candela", "ccandela@email.com", "6067431235", LocalDate.now().minusYears(15));

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(1),
            LocalDate.now().minusDays(15),
            LocalDate.now().plusDays(15),
            (byte) 24,
            (byte) 18,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.GRUPAL
        );

        torneo.registrarParticipante(equipo);
        torneo.registrarParticipante(equipo2);
        torneo.registrarJugador(equipo, jugador);

        assertThrows(Throwable.class, () -> torneo.registrarJugador(equipo2, jugador2));

        LOG.info("Fin prueba: registrar jugadores con nombres y apellidos iguales desde torneo...");
    }

    /**
     * verifica que Equipo no permita registrar jugadores con el mismo correo
     */
    @Test
    public void registrarJugadorMismoEmailDesdeEquipo()
    {
        LOG.info("Inicio prueba: registrar jugadores con el mismo correo desde equipo...");

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234", LocalDate.now().minusYears(15));
        var jugador2 = new Jugador("Cristian", "Vermudez", "chrcandela@email.com", "6067431235", LocalDate.now().minusYears(15));

        equipo.registrarJugador(jugador);
        assertThrows(Throwable.class, () -> equipo.registrarJugador(jugador2));

        LOG.info("Fin prueba: registrar jugadores con el mismo correo desde equipo...");
    }

    /**
     * verifica que Torneo no permita registrar jugadores con el mismo correo
     */
    @Test
    public void registrarJugadorMismoEmailDesdeTorneo()
    {
        LOG.info("Inicio prueba: registrar jugadores con el mismo correo desde torneo...");

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var equipo2 = new Equipo("Quindio", representante);
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234", LocalDate.now().minusYears(15));
        var jugador2 = new Jugador("Cristian", "Vermudez", "chrcandela@email.com", "6067431235", LocalDate.now().minusYears(15));

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(1),
            LocalDate.now().minusDays(15),
            LocalDate.now().plusDays(15),
            (byte) 24,
            (byte) 18,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.GRUPAL
        );

        torneo.registrarParticipante(equipo);
        torneo.registrarParticipante(equipo2);
        torneo.registrarJugador(equipo, jugador);

        assertThrows(Throwable.class, () -> torneo.registrarJugador(equipo2, jugador2));

        LOG.info("Fin prueba: registrar jugadores con el mismo correo desde torneo...");
    }

    /**
     * verificar que Torneo no permita registrar jugadores "null"
     */
    @Test
    public void registrarJugadorNuloTorneo()
    {
        LOG.info("Inicio prueba: registrar jugador nulo desde torneo...");

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(1),
            LocalDate.now().minusDays(15),
            LocalDate.now().plusDays(15),
            (byte) 24,
            (byte) 18,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.GRUPAL
        );

        torneo.registrarParticipante(equipo);

        assertThrows(Throwable.class, () -> torneo.registrarJugador(equipo, null));

        LOG.info("Fin prueba: registrar jugador nulo desde torneo...");
    }

    /**
     * verificar que Torneo no permita registrar jugadores desde un equipo con nombre en blanco
     */
    @Test
    public void registrarJugadorEquipoEnBlancoDesdeTorneo()
    {
        LOG.info("Inicio prueba: registrar jugador nulo desde torneo...");

        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234", LocalDate.now().minusYears(15));
        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(1),
            LocalDate.now().minusDays(15),
            LocalDate.now().plusDays(15),
            (byte) 24,
            (byte) 18,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.GRUPAL
        );

        assertThrows(Throwable.class, () -> torneo.registrarJugador("", jugador));

        LOG.info("Fin prueba: registrar jugador nulo desde torneo...");
    }
}