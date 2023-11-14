package co.edu.uniquindio.poo.torneodeportivo.participante;

import co.edu.uniquindio.poo.torneodeportivo.persona.Representante;

public record Equipo(
    String nombre,
    Representante representante
) {
    public Equipo
    {
        assert nombre != null;
        assert representante != null;
    }
}
