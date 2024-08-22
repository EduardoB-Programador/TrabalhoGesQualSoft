package controller;

import model.Pessoa;
import model.Visitante;
import model.SistemaVisita;

import java.util.ArrayList;

public class Visita {
    ArrayList<Visitante> visitas = new ArrayList<>();

    public static Visitante visitar(Pessoa pessoa) {

        SistemaVisita visita = new SistemaVisita(pessoa);
        Visitante visitante = new Visitante(pessoa);

        return null;
    }
}
