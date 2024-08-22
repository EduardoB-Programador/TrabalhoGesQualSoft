package model;

import view.Janelas;

public class Visitante extends Pessoa {
    private String parentesco, nomePaciente, cpfPaciente;

    public Visitante(String nome, String endereco) {
        super(nome, endereco);

        this.nomePaciente = Janelas.telaInput("Qual o nome do paciente que deseja visitar?");
        this.parentesco = setParentesco();
        this.cpfPaciente = super.setCpf();
    }

    public Visitante(Pessoa pessoa) {
        super(pessoa.getCPF(), pessoa.getNome(), pessoa.getSenha(), pessoa.getEndereco(), pessoa.getIdade());
    }

    public String setParentesco() {

        if (Janelas.telaSN("Você é um familiar do paciente?")) {
            return "familiar";

        } else if (Janelas.telaSN("Você é o cônjuge do paciente?")) {
            return "cônjuge";

        } else {
            return "amigo";

        }
    }

}
