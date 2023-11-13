package co.edu.uniquindio.poo.torneodeportivo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

public class TorneoTest
{
    /**
     * instancia para el manejo de logs
     */
    private static final Logger LOG = Logger.getLogger(TorneoTest.class.getName());

    /**
     * verificar que la clase torneo almacene y recupere datos
     */
    @Test
    public void datosCompletos()
    {
        LOG.info("inicio de prueba datos completos...");

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

        assertEquals("Copa Mundo", torneo.getNombre());
        assertEquals(LocalDate.now().plusMonths(5), torneo.getFechaInicioTorneo());
        assertEquals(LocalDate.now().plusMonths(1), torneo.getFechaInicioInscripciones());
        assertEquals(LocalDate.now().plusMonths(3), torneo.getFechaCierreInscripciones());
        assertEquals((byte) 24, torneo.getNumeroMaximoParticipantes());
        assertEquals((byte) 0, torneo.getLimiteEdad());
        assertEquals((byte) 0, torneo.getValorInscripcion());
        assertEquals(TipoTorneo.LOCAL, torneo.getTipoTorneo());

        LOG.info("Fin prueba de datos completos...");
    }

    /**
     * verificar que la clase Torneo valide que se ingresen los datos
     */
    @Test
    public void datosNulos()
    {
        LOG.info("inicio de prueba datos nulos...");

        assertThrows(Throwable.class, () -> new Torneo(null, null, null, null, (byte) 24, (byte) 0, (byte) 0, TipoTorneo.LOCAL));

        LOG.info("Fin prueba de datos nulos...");
    }

    /**
     * verificar que la clase Torneo valide que el numero de participantes no sea negativo
     */
    @Test
    public void participantesNegativos()
    {
        LOG.info("inicio de prueba numero maximo de participantes negativos...");

        assertThrows(Throwable.class, () -> {
            new Torneo(
                "Copa Mundo", 
                LocalDate.now().plusMonths(5),
                LocalDate.now().plusMonths(1),
                LocalDate.now().plusMonths(3),
                (byte) -24,
                (byte) 0,
                (byte) 0,
                TipoTorneo.LOCAL
            );
        });

        LOG.info("Fin prueba de numero maximo de participantes negativos...");
    }

    /**
     * verificar que la clase Torneo valide que el limite de edad no sea negativo
     */
    @Test
    public void limiteEdadNegativo()
    {
        LOG.info("inicio de prueba limite de edad negativo...");

        assertThrows(Throwable.class, () -> {
            new Torneo(
                "Copa Mundo", 
                LocalDate.now().plusMonths(5),
                LocalDate.now().plusMonths(1),
                LocalDate.now().plusMonths(3),
                (byte) 24,
                (byte) -1,
                (byte) 0,
                TipoTorneo.LOCAL
            );
        });

        LOG.info("Fin prueba de limite de edad negativo...");
    }

    /**
     * verificar que la clase Torneo valide que el valor de la inscripcion no sea negativo
     */
    @Test
    public void valorInscripcionNegativo()
    {
        LOG.info("inicio de prueba valor de inscripcion negativo...");

        assertThrows(Throwable.class, () -> {
            new Torneo(
                "Copa Mundo", 
                LocalDate.now().plusMonths(5),
                LocalDate.now().plusMonths(1),
                LocalDate.now().plusMonths(3),
                (byte) 24,
                (byte) 0,
                (byte) -1,
                TipoTorneo.LOCAL
            );
        });

        LOG.info("Fin prueba de valor de inscripcion negativo...");
    }

    /**
     * verificar que la clase Torneo valide que la fecha de inscripcion no sea posterior a la de inicio del torneo
     */
    @Test
    public void fechaInscripcionTardia()
    {
        LOG.info("inicio de prueba: fecha de inscripcion tardia...");

        assertThrows(Throwable.class, () -> {
            new Torneo(
                "Copa Mundo", 
                LocalDate.now().plusMonths(5),
                LocalDate.now().plusMonths(6),
                LocalDate.now().plusMonths(7),
                (byte) 24,
                (byte) 0,
                (byte) 0,
                TipoTorneo.LOCAL
            );
        });

        LOG.info("Fin de prueba: fecha de inscripcion tardia...");
    }

    /**
     * verificar que la clase Torneo valide que la fecha de cierre de inscripciones  sea posterior a la de inicio de inscripciones
     */
    @Test
    public void fechaCierreInscripcionPrevia()
    {
        LOG.info("inicio de prueba: fecha de cierre de inscripciones previa...");

        assertThrows(Throwable.class, () -> {
            new Torneo(
                "Copa Mundo", 
                LocalDate.now().plusMonths(5),
                LocalDate.now().plusMonths(1),
                LocalDate.now().plusDays(15),
                (byte) 24,
                (byte) 0,
                (byte) 0,
                TipoTorneo.LOCAL
            );
        });

        LOG.info("Fin de prueba: fecha de cierre de inscripciones previa...");
    }

    /**
     * verificar que la clase Torneo no permita tipos de torneo nulos
     */
    @Test
    public void tipoTorneoNulo()
    {
        LOG.info("inicio de prueba: tipo de torneo nulo...");

        assertThrows(Throwable.class, () -> {
            new Torneo(
                "Copa Mundo", 
                LocalDate.now().plusMonths(5),
                LocalDate.now().plusMonths(1),
                LocalDate.now().plusDays(15),
                (byte) 24,
                (byte) 0,
                (byte) 0,
                null
            );
        });

        LOG.info("Fin de prueba: tipo de torneo nulo...");
    }
}