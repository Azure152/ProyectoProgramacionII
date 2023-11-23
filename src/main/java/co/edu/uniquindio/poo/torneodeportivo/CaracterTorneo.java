package co.edu.uniquindio.poo.torneodeportivo;

import co.edu.uniquindio.poo.torneodeportivo.participante.Equipo;
import co.edu.uniquindio.poo.torneodeportivo.participante.Jugador;
import co.edu.uniquindio.poo.torneodeportivo.participante.Participante;

public enum CaracterTorneo {
    INDIVIDUAL {
        public boolean participanteValido(Participante participante) {
            return participante instanceof Jugador;
        };
    },

    GRUPAL {
        public boolean participanteValido(Participante participante) {
            return participante instanceof Equipo;
        };
    };

    public abstract boolean participanteValido(Participante participante);
}