package co.edu.uniquindio.poo.torneodeportivo.participante;

import java.util.Collection;
import java.util.LinkedList;

import co.edu.uniquindio.poo.torneodeportivo.persona.Persona;

public class Equipo implements Participante
{
    /**
     * nombre del equipo
     */
    private final String nombre;

    /**
     * representante del equipo
     */
    private final Persona representante;

    /**
     * jugadores del equipo
     */
    private Collection<Jugador> jugadores;

    /**
     * contruir una instancia de Equipo
     * 
     * @param nombre nombre del equipo
     * @param representante representante del equipo
     */
    public Equipo(String nombre, Persona representante)
    {
        assert nombre != null;
        assert representante != null;

        this.nombre = nombre;
        this.representante = representante;
        this.jugadores = new LinkedList<>();
    }

    /**
     * registra un jugador en el equipo
     */
    public void registrarJugador(Jugador jugador)
    {
        assert ! this.jugadorExistente(jugador);
        assert ! this.jugadorExistentePorCorreo(jugador.getEmail());

        this.jugadores.add(jugador);
    }

    /**
     * comprueba si el jugador ya se encuentra registrado en el equipo
     * 
     * @return un booleano que representa si el jugador se encuentra registrado
     */
    public boolean jugadorExistente(Jugador jugador)
    {
        return this.jugadores.stream().filter((j) -> {
            return jugador.equals(j)
                || jugador.getNombre().equalsIgnoreCase(j.getNombre()) && jugador.getApellido().equalsIgnoreCase(j.getApellido());
        }).findAny().isPresent();
    }

    /**
     * comprueba si el jugador se encuentra registrado usando el correo
     * 
     * @param correo direccion de correo electronico
     * 
     * @return un booleano que representa si el jugador se encuentra registrado
     */
    public boolean jugadorExistentePorCorreo(String correo)
    {
        return this.jugadores.stream().filter((j) -> {
            return j.getEmail().equalsIgnoreCase(correo);
        }).findAny().isPresent();
    }

    /**
     * obtiene el nombre del equipo
     * 
     * @return el nombre del equipo
     */
    public String getNombre()
    {
        return this.nombre;
    }

    /**
     * {@inheritDoc}
     */
    public String getNombreCompleto()
    {
        return this.getNombre();
    }

    /**
     * obtiene el representante del equipo
     * 
     * @return representante del equipo
     */
    public Persona getRepresentante()
    {
        return this.representante;
    }

    /**
     * obtiene los jugadores que conforman el equipo
     * 
     * @return jugadores del equipo
     */
    public Collection<Jugador> getJugadores()
    {
        return this.jugadores;
    }
}
