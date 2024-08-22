package model;

import util.DefinicaoImpossivelException;
import view.Janelas;

public class Pessoa {
    private final String cpf;
    private String nome, endereco, senha;
    private int idade;

    public Pessoa(String nome, String endereco) {
        this.cpf = setCpf();
        this.nome = nome;
        this.endereco = endereco;
        this.senha = setSenha();
        this.idade = setIdade();
    }

    public Pessoa(String cpf, String nome, String senha, String endereco, int idade) {
        this.cpf = cpf;
        this.nome = nome;
        this.senha = senha;
        this.endereco = endereco;
        this.idade = idade;
    }

    public String setCpf() {
        String cpf = Janelas.telaInput("Insira o CPF: (Siga a estrutura: 000.000.000-00)");

        try {
            if (cpf.charAt(3) != '.' || cpf.charAt(7) != '.' || cpf.charAt(11) != '-' || cpf.length() != 14) {
                throw new DefinicaoImpossivelException("Digite o CPF seguindo a estrutura: 000.000.000-00");
            }

        } catch (DefinicaoImpossivelException e) {
            return setCpf();
        }

        if (Janelas.telaSN("O CPF " + cpf + " está correto?")) {
            return cpf;
        } else {
            return setCpf();
        }

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String setSenha() {
        String senha1, senha2;

        try {
            senha1 = Janelas.telaInput("Digite sua senha:");
            senha2 = Janelas.telaInput("Confirme sua senha:");

            if (senha1.equals(senha2)) {
                return senha1;
            } else {
                throw new DefinicaoImpossivelException("Não foi possível definir a senha, tente novamente. Você deve repetir a senha 2 vezes.");
            }

        } catch (DefinicaoImpossivelException e) {
            return setSenha();
        }
    }

    public int setIdade() throws NumberFormatException {
        int idade;

        try {
            idade = Integer.parseInt(Janelas.telaInput("Qual a sua idade?"));

        } catch (NumberFormatException e) {
            Janelas.telaErro("Qualquer valor que não seja numérico será aceito.");
            return setIdade();
        }

        return idade;
    }

    public String getCPF() {
        return this.cpf;
    }

    public String getNome() {
        return this.nome;
    }

    public String getEndereco() {
        return this.endereco;
    }

    public String getSenha() {
        return this.senha;
    }

    public int getIdade() {
        return this.idade;
    }

    //Retorna todos os valores da classe
    public String toString() {
        return "Essas são todas as informações pessoais de " + this.nome +
                "\nNome: " + this.nome +
                "\nIdade: " + this.idade +
                "\nEndereço: " + this.endereco +
                "\nCPF: " + this.cpf +
                "\nSenha: " + this.senha;
    }
}
