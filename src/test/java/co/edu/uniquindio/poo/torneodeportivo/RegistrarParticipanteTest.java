package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import co.edu.uniquindio.poo.torneodeportivo.participante.Equipo;
import co.edu.uniquindio.poo.torneodeportivo.participante.Jugador;
import co.edu.uniquindio.poo.torneodeportivo.persona.Persona;

public class RegistrarParticipanteTest {
    /**
     * instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(RegistrarParticipanteTest.class.getName());

    /**
     * verifica que un Torneo de caracter INDIVIDUAL permita el registro de un Jugador
     */
    @Test
    public void registrarParticipanteTorneo() {
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234", LocalDate.now().minusYears(15));
        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(1),
            LocalDate.now().minusDays(15),
            LocalDate.now().plusDays(15),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.INDIVIDUAL
        );

        assertDoesNotThrow(() -> torneo.registrarParticipante(jugador));

        assertEquals(torneo.getParticipantes(), List.of(jugador));
    }

    /**
     * verifica que Torneo no permita el registro de un particpante con el mismo nombre.
     * con caracter de torneo INDIVIDUAL
     */
    @Test
    public void registrarParticipanteIndividualNombreRepetido() {
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234", LocalDate.now().minusYears(15));
        var jugador2 = new Jugador("Christian", "Candela", "ccandela@email.com", "6067431235", LocalDate.now().minusYears(15));
        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(1),
            LocalDate.now().minusDays(15),
            LocalDate.now().plusDays(15),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.INDIVIDUAL
        );

        torneo.registrarParticipante(jugador);

        assertThrows(Throwable.class, () -> torneo.registrarParticipante(jugador2));
    }

    /**
     * verifica que torneo no permita registrar jugadores en torneos de caracter GRUPAL
     */
    @Test
    public void registrarParticipanteIndividualTorneoGrupal() {
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234", LocalDate.now().minusYears(15));
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

        assertThrows(Throwable.class, () -> torneo.registrarParticipante(jugador));
    }

    /**
     * verifica que torneo no permita registrar equipos en torneos de caracter INDIVIDUAL
     */
    @Test
    public void registrarEquipoTorneoIndividual() {
        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(1),
            LocalDate.now().minusDays(15),
            LocalDate.now().plusDays(15),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.INDIVIDUAL
        );

        assertThrows(Throwable.class, () -> torneo.registrarParticipante(equipo));
    }

    /**
     * verifica que torneo no permita registrar jugadores que sobrepasen el limite de edad
     * cuando el torneo es de caracter INDIVIDUAL 
     */
    @Test
    public void registrarParticipanteLimiteEdadSuperadoTorneoIndividual() {
        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234", LocalDate.now().minusYears(15));
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

        assertThrows(Throwable.class, () -> torneo.registrarParticipante(jugador));
    }

    /**
     * verifica que Torneo no permita registrar jugadores con el mismo correo en un
     * torneo de caracter individual
     */
    @Test
    public void registrarParticipanteCorreoRepetidoTorneoIndividual()
    {
        LOG.info("Inicio prueba: registrar jugadores con el mismo correo en un torneo individual...");

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
            CaracterTorneo.INDIVIDUAL
        );

        torneo.registrarParticipante(jugador);

        assertThrows(Throwable.class, () -> torneo.registrarParticipante(jugador2));

        LOG.info("Fin prueba: registrar jugadores con el mismo correo en un torneo individual...");
    }

    /**
     * verifica que torneo devuelta una lista de participantes en orden alfabetico (nombre)
     */
    @Test
    public void listadoParticipantesOrdenAlfabetico() {
        LOG.info("Inicio prueba: listado de participantes en orden alfabetico...");

        var jugador = new Jugador("Christian", "Candela", "chrcandela@email.com", "6067431234", LocalDate.now().minusYears(15));
        var jugador2 = new Jugador("Andres", "Vermudez", "andverz@email.com", "6067431235", LocalDate.now().minusYears(15));

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(1),
            LocalDate.now().minusDays(15),
            LocalDate.now().plusDays(15),
            (byte) 24,
            (byte) 18,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.INDIVIDUAL
        );

        torneo.registrarParticipante(jugador);
        torneo.registrarParticipante(jugador2);

        var resultadoEsperado = List.of(jugador2, jugador);

        assertEquals(resultadoEsperado, torneo.getParticipantes());

        LOG.info("Fin prueba: listado de participantes en orden alfabetico...");
    }
}
