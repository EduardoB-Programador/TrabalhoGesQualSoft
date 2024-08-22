package controller;

import model.Pessoa;
import util.DefinicaoImpossivelException;
import view.Janelas;

import java.util.ArrayList;

public class Cadastro {

    private static ArrayList<Pessoa> pessoas = new ArrayList<>();

    public static Pessoa acessar() {

        String acesso = Janelas.telaInput("""
                O que deseja realizar?
                
                1 - Cadastro
                2 - Login
                """);

        try {
            if (acesso.equals("1") || acesso.equalsIgnoreCase("cadastrar") || acesso.equalsIgnoreCase("1 - cadastro")) {
                cadastro();

            } else if (acesso.equals("2") || acesso.equalsIgnoreCase("login") || acesso.equalsIgnoreCase("2 - login")) {
                return login();

            } else {
                throw new DefinicaoImpossivelException("A opção " + acesso + " não é uma opção.");

            }

        } catch (DefinicaoImpossivelException e) {
            return acessar();

        }

        return null;
    }

    private static void cadastro() {
        String nome = Janelas.telaInput("Escreva o seu nome:");
        String endereco = Janelas.telaInput("Escreva o seu endereço:");

        Pessoa pessoa = new Pessoa(nome, endereco);

        pessoas.add(pessoa);
    }

    private static Pessoa login() {
        String user = Janelas.telaInput("Insira o seu CPF seguindo a estrutura 000.000.000-00:");

        try {
            if (user.charAt(3) != '.' || user.charAt(7) != '.' || user.charAt(11) != '-' || user.length() != 14) {
                throw new DefinicaoImpossivelException("Digite o CPF seguindo a estrutura: 000.000.000-00");
            }

        } catch (DefinicaoImpossivelException e) {
            return login();
        }

        String senha = Janelas.telaInput("Digite sua senha:");

        for (Pessoa pessoa : pessoas) {

            if (pessoa.getCPF().equals(user) && pessoa.getSenha().equals(senha)) {
                return pessoa;
            }

        }

        boolean reiniciar = Janelas.telaSN("""
                CPF ou senha incorretos, deseja realizar o login novamente?
                """);

        if (reiniciar) {
            return login();
        } else {
            return null;
        }
    }

    public static ArrayList<Pessoa> getPessoas() {
        return pessoas;
    }
}
