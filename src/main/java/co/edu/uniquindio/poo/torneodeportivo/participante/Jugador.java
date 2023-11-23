package co.edu.uniquindio.poo.torneodeportivo.participante;

import java.time.LocalDate;
import java.time.Period;

import co.edu.uniquindio.poo.torneodeportivo.persona.Persona;

public class Jugador extends Persona implements Participante
{
    /**
     * fecha de nacimiento
     */
    private LocalDate fechaNacimiento;

    /**
     * construye una instancia de Jugador
     * 
     * @param nombre nombre(s) del jugador
     * @param apellido apellidos(s) del jugador
     * @param email direccion de correo electronico
     * @param celular numero de telefono/celular
     * @param fechaNacimiento fecha de nacimiento
     */
    public Jugador(String nombre, String apellido, String email, String celular, LocalDate fechaNacimiento)
    {
        super(nombre, apellido, email, celular);

        assert fechaNacimiento != null;
        assert fechaNacimiento.isBefore(LocalDate.now());

        this.fechaNacimiento = fechaNacimiento;
    }

    /**
     * calcula la edad
     * 
     * @return la edad
     */
    public int calcularEdad()
    {
        return Period.between(this.fechaNacimiento, LocalDate.now()).getYears();
    }

    /**
     * {@inheritDoc}
     */
    public String getNombreCompleto()
    {
        return this.getNombre() + ' ' + this.getApellido();
    }

    /**
     * obtiene la fecha de nacimiento
     * 
     * @return la fecha de nacimiento
     */
    public LocalDate getFechaNacimiento()
    {
        return this.fechaNacimiento;
    }
}
