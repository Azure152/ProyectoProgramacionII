package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class TorneoModificarFechasTest
{
    /**
     * instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(TorneoModificarFechasTest.class.getName());

    /**
     * verificar que la clase Torneo permita modificar la fecha de inicio del torneo
     */
    @Test
    public void modificarFechaInico()
    {
        LOG.info("inicio prueba: modificar la fecha de inicio del torneo...");

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(5),
            LocalDate.now().plusMonths(1),
            LocalDate.now().plusMonths(3),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL
        );

        assertDoesNotThrow(() -> torneo.setFechaInicioTorneo(LocalDate.now().plusMonths(6)));
        assertEquals(LocalDate.now().plusMonths(6), torneo.getFechaInicioTorneo());

        LOG.info("Fin prueba: modificar la fecha de inicio del torneo...");
    }

    /**
     * verficar que en la clase torneo al modificar la fecha de inicio de torneo no acepte nulos
     */
    @Test
    public void modificarFechaInicoNull()
    {
        LOG.info("inicio prueba: modificar la fecha de inicio del torneo por nulo...");

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(5),
            LocalDate.now().plusMonths(1),
            LocalDate.now().plusMonths(3),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL
        );

        assertThrows(Throwable.class, () -> torneo.setFechaInicioTorneo(null));

        LOG.info("Fin prueba: modificar la fecha de inicio del torneo por nulo...");
    }

    /**
     * verificar que la clase torneo no permita la modificacion de la fecha de inicio del torneo
     * por una anterior a la fecha de inicio de inscripciones
     */
    @Test
    public void modificarFechaInicoAnteriorInscripcion()
    {
        LOG.info("inicio prueba: modificar la fecha de inicio del torneo por una previa a la de inscripciones...");

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(5),
            LocalDate.now().plusMonths(1),
            LocalDate.now().plusMonths(3),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL
        );

        assertThrows(
            Throwable.class,
            () -> torneo.setFechaInicioTorneo(LocalDate.now().plusDays(12))
        );

        LOG.info("Fin prueba: modificar la fecha de inicio del torneo por una previa a la de inscripciones...");
    }

    /**
     * verificar que la clase torneo permita la modificacion de la fecha de inicio para las inscripciones
     */
    @Test
    public void modificarFechaInicioInscripciones()
    {
        LOG.info("inicio prueba: modificar la fecha de inicio de inscripciones...");

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(5),
            LocalDate.now().plusMonths(1),
            LocalDate.now().plusMonths(3),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL
        );

        assertDoesNotThrow(() -> torneo.setFechaInicioInscripciones(LocalDate.now().plusMonths(2)));
        assertEquals(LocalDate.now().plusMonths(2), torneo.getFechaInicioInscripciones());

        LOG.info("Fin prueba: modificar la fecha de inicio de inscripciones...");
    }

    /**
     * verficar que en la clase torneo al modificar la fecha de inicio para inscripciones al torneo no acepte nulos
     */
    @Test
    public void modificarFechaInicoInscripcionesNull()
    {
        LOG.info("inicio prueba: modificar la fecha de inicio de inscripciones por un nulo...");

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(5),
            LocalDate.now().plusMonths(1),
            LocalDate.now().plusMonths(3),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL
        );

        assertThrows(Throwable.class, () -> torneo.setFechaInicioInscripciones(null));

        LOG.info("Fin prueba: modificar la fecha de inicio de inscripciones por un nulo...");
    }

    /**
     * verificar que la clase Torneo no permita modificar la fecha de inicio de inscripciones por una posterior
     * a la fecha de cierre de inscripciones
     */
    @Test
    public void modificarFechaInicioInscripcionesPosteriorCierre()
    {
        LOG.info("inicio prueba: modificar la fecha de inicio de inscripciones por una posterior al cierre...");

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(5),
            LocalDate.now().plusMonths(1),
            LocalDate.now().plusMonths(3),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL
        );

        assertThrows(
            Throwable.class, 
            () -> torneo.setFechaInicioInscripciones(LocalDate.now().plusMonths(4))
        );

        LOG.info("Fin prueba: modificar la fecha de inicio de inscripciones por una posterior al cierre...");
    }

    /**
     * verificar que la clase Torneo permita modificar la fecha de cierre de inscripciones
     */
    @Test
    public void modificarFechaCierreDeinscripciones()
    {
        LOG.info("inicio prueba: modificar la fecha de cierre de inscripciones...");

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(5),
            LocalDate.now().plusMonths(1),
            LocalDate.now().plusMonths(3),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL
        );

        assertDoesNotThrow(() -> torneo.setFechaCierreInscripciones(LocalDate.now().plusMonths(4)));
        assertEquals(LocalDate.now().plusMonths(4), torneo.getFechaCierreInscripciones());

        LOG.info("Fin prueba: modificar la fecha de cierre de inscripciones...");
    }

    /**
     * verficar que en la clase torneo al modificar la fecha de cierre para inscripciones al torneo no acepte nulos
     */
    @Test
    public void modificarFechaCierreInscripcionesNull()
    {
        LOG.info("inicio prueba: modificar la fecha de cierre de inscripciones por un nulo...");

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(5),
            LocalDate.now().plusMonths(1),
            LocalDate.now().plusMonths(3),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL
        );

        assertThrows(Throwable.class, () -> torneo.setFechaCierreInscripciones(null));

        LOG.info("Fin prueba: modificar la fecha de cierre de inscripciones por un nulo...");
    }

    /**
     * verificar que la clase Torneo no permita modificar la fecha de cierre de inscripciones por una posterior
     * a la fecha de inicio del torneo
     */
    @Test
    public void modificarFechaInicioInscripcionesPosteriorInicioTorneo()
    {
        LOG.info("inicio prueba: modificar la fecha de cierre de inscripciones por una posterior al inicio del torneo...");

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(5),
            LocalDate.now().plusMonths(1),
            LocalDate.now().plusMonths(3),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL
        );

        assertThrows(
            Throwable.class, 
            () -> torneo.setFechaCierreInscripciones(LocalDate.now().plusMonths(6))
        );

        LOG.info("Fin prueba: modificar la fecha de cierre de inscripciones por una posterior al inicio del torneo...");
    }

    /**
     * verificar que la clase Torneo no permita modificar la fecha de cierre de inscripciones por una previa
     * a la fecha de inicio para las incripciones
     */
    @Test
    public void modificarFechaInicioInscripcionesPreviaInicioInscripciones()
    {
        LOG.info("inicio prueba: modificar la fecha de cierre de inscripciones por una previa al inicio de inscripciones...");

        var torneo = new Torneo(
            "Copa Mundo", 
            LocalDate.now().plusMonths(5),
            LocalDate.now().plusMonths(1),
            LocalDate.now().plusMonths(3),
            (byte) 24,
            (byte) 0,
            (byte) 0,
            TipoTorneo.LOCAL
        );

        assertThrows(
            Throwable.class, 
            () -> torneo.setFechaCierreInscripciones(LocalDate.now().plusDays(12))
        );

        LOG.info("Fin prueba: modificar la fecha de cierre de inscripciones por una previa al inicio de inscripciones...");
    }
}