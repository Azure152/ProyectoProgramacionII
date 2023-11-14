package co.edu.uniquindio.poo.torneodeportivo.persona;

public class Persona
{
    /**
     * nombre(s)
     */
    private final String nombre;

    /**
     * apellido(s)
     */
    private final String apellido;

    /**
     * direccion de correo electronico
     */
    private final String email;

    /**
     * numero de telefono/celular
     */
    private final String celular;

    /**
     * construye una instancia de Persona
     * 
     * @param nombre nombre(s) de la persona
     * @param apellido apellidos(s) de la persona
     * @param email direccion de correo electronico
     * @param celular numero de telefono/celular
     */
    public Persona(
        String nombre,
        String apellido,
        String email,
        String celular
    ) {
        assert nombre != null && ! nombre.isBlank();
        assert apellido != null && ! apellido.isBlank();
        assert email != null;
        assert celular != null;

        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.celular = celular;
    }

    /**
     * obtiene los nombre(s)
     * 
     * @return nombre(s)
     */
    public String getNombre()
    {
        return this.nombre;
    }

    /**
     * obtiene los apellidos(s)
     * 
     * @return apellido(s)
     */
    public String getApellido()
    {
        return this.apellido;
    }

    /**
     * obtiene la direccion de correo electronico
     * 
     * @return direccion de correo electronico
     */
    public String getEmail()
    {
        return this.email;
    }

    /**
     * obitiene el numero de telefono/celular
     * 
     * @return numero de telefono/celular
     */
    public String getCelular()
    {
        return this.celular;
    }
}
