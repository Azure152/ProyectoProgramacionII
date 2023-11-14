package co.edu.uniquindio.poo.torneodeportivo.persona;

public record Representante(
    String nombre,
    String apellido,
    String email,
    String celular
) {
    public Representante 
    {
        assert nombre != null;
        assert apellido != null;
        assert email != null;
        assert celular != null;
    }
}
