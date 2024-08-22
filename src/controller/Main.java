package controller;

import model.Pessoa;
import model.Paciente;
import util.DefinicaoImpossivelException;
import view.Janelas;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Pessoa> pessoas;

        Pessoa pessoa = null;

        while (pessoa == null) {
            pessoa = Cadastro.acessar();
            pessoas = Cadastro.getPessoas();
        }

        while (true) {

            String escolha = Janelas.telaInput("""
                O que deseja fazer?
                
                1 - Marcar Consulta
                2 - Marcar Visita (Função não desenvolvida)
                3 - Sair do sistema
                """);

            if (escolha.equals("1") || escolha.equalsIgnoreCase("marcar consulta") || escolha.equalsIgnoreCase("consulta")) {
                Paciente paciente = Consulta.consulta(pessoa);
                acaoPaciente(paciente);

            } else if (escolha.equals("2") || escolha.equalsIgnoreCase("marcar visita") || escolha.equalsIgnoreCase("visita")) {
                /*Visita.visita();*/

            } else if (escolha.equals("3") || escolha.equalsIgnoreCase("sair do sistema") || escolha.equalsIgnoreCase("sair")) {
                break;

            } else {
                Janelas.telaErro("A opção " + escolha + " não é uma opção válida.");
            }
        }

    }

    private static void acaoPaciente(Paciente paciente) {

        while (true) {
            boolean sair = false;

            switch (menuPaciente()) {

                case 1 -> Janelas.telaMensagem(paciente.toString());

                case 2 -> Janelas.telaMensagem(String.valueOf(paciente.getMedicacoes()));

                case 3 -> Janelas.telaMensagem(String.valueOf(paciente.getEnfermidades()));

                case 4 -> sair = true;
            }

            if (sair) {
                break;
            }

        }

    }

    public static int menuPaciente() {

        try {
            String escolha = Janelas.telaInput("""
                    O que deseja fazer?
                    
                    1 - Verificar informações pessoais
                    2 - Verificar medicações
                    3 - Verificar enfermidades
                    4 - Sair do menu
                    """).toLowerCase();

            switch (escolha) {
                case "1", "verificar informações pessoais", "informações pessoais", "1 - verificar informações pessoais" -> {
                    return 1;
                }

                case "2", "verificar medicações", "medicações", "2 - verificar medicações" -> {
                    return 2;
                }

                case "3", "verificar enfermidades", "enfermidades", "3 - verificar enfermidades" -> {
                    return 3;
                }

                case "4", "sair do menu", "sair", "4 - sair do menu" -> {
                    return 4;
                }

                default -> throw new DefinicaoImpossivelException("A opção fornecida não é uma opção.");
            }

        } catch (DefinicaoImpossivelException e) {
            return menuPaciente();
        }
    }
}
