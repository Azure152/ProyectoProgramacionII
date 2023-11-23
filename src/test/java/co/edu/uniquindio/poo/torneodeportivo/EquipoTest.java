package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import co.edu.uniquindio.poo.torneodeportivo.participante.Equipo;
import co.edu.uniquindio.poo.torneodeportivo.persona.Persona;

public class EquipoTest
{
    /**
     * instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(EquipoTest.class.getName());

    /**
     * verificar que Torneo permita registrar un equipo
     */
    @Test
    public void registrarEquipo()
    {
        LOG.info("Inicio de prueba registrarEquipo...");

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(5),
            LocalDate.now().minusDays(1),
            LocalDate.now().plusMonths(3),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.GRUPAL
        );

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);

        torneo.registrarParticipante(equipo);

        var resultado = new LinkedList<Equipo>();
        resultado.add(equipo);

        assertEquals(resultado, torneo.getParticipantes());

        LOG.info("Fin de prueba registrarEquipo...");
    }

    /**
     * verificar que Torneo no permita registrar un equipo con nombre repetido
     */
    @Test
    public void registrarEquipoNombreRepetido()
    {
        LOG.info("Inicio de prueba: registrar un equipo con nombre repetido...");

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(5),
            LocalDate.now().minusDays(1),
            LocalDate.now().plusMonths(3),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.GRUPAL
        );

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var equipo2 = new Equipo("Uniquindio", representante);

        torneo.registrarParticipante(equipo);

        assertThrows(Throwable.class, () -> torneo.registrarParticipante(equipo2));

        LOG.info("Find de prueba: registrar un equipo con nombre repetido...");
    }

    /**
     * verificar que no se permita el registro de un equipo cuando las inscripciones
     * ya hallan cerrado
     */
    @Test
    public void registarEquipoInscripcionesCerradas()
    {
        LOG.info("Inicio de prueba: registrar equipo con inscripciones cerradas...");

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(5),
            LocalDate.now().minusMonths(1),
            LocalDate.now().minusDays(3),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.GRUPAL
        );

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);

        assertThrows(Throwable.class, () -> torneo.registrarParticipante(equipo));

        LOG.info("Fin de prueba: registrar equipo con inscripciones cerradas...");
    }

    /**
     * verificar que no se permita el registro de un equipo cuando las inscripciones
     * no hallan iniciado aun
     */
    @Test
    public void registarEquipoInscripcionesSinIniciar()
    {
        LOG.info("Inicio de prueba: registrar equipo con inscripciones sin iniciar...");

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(5),
            LocalDate.now().plusMonths(1),
            LocalDate.now().plusMonths(3),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.GRUPAL
        );

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);

        assertThrows(Throwable.class, () -> torneo.registrarParticipante(equipo));

        LOG.info("Fin de prueba: registrar equipo con inscripciones sin iniciar...");
    }

    /**
     * verificar que no se permitan registrar mas equipos del numero maximo permitidos en Torneo
     */
    @Test
    public void registarEquipoExcedente()
    {
        LOG.info("Inicio de prueba: registrar equipo excedente...");

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(5),
            LocalDate.now().minusDays(1),
            LocalDate.now().plusMonths(3),
            (byte) 1,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL,
            CaracterTorneo.GRUPAL
        );

        var representante = new Persona("Robinson", "Pulgarin", "rpulgarin@email.com", "6067359300");
        var equipo = new Equipo("Uniquindio", representante);
        var equipo2 = new Equipo("NuevoEquipo", representante);

        torneo.registrarParticipante(equipo);

        assertThrows(Throwable.class, () -> torneo.registrarParticipante(equipo2));

        LOG.info("Fin de prueba: registrar equipo excedente...");
    }
}
