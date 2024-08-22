package controller;

import model.Pessoa;
import model.Paciente;
import model.SistemaConsulta;
import view.Janelas;

import java.util.ArrayList;

public class Consulta {
    private static ArrayList<Paciente> pacientes = new ArrayList<>();

    //Sistema pra marcar consulta
    public static Paciente consulta(Pessoa pessoa) {

        SistemaConsulta consulta = new SistemaConsulta(pessoa);
        Paciente paciente = new Paciente(pessoa);

        while (true) {
            boolean sair = false;

            switch (menuPaciente()) {
                case 1 -> {

                    if (!(consulta.getDia() == null || consulta.getHora() == null || consulta.getSala() == null)) {
                        pacientes.add(resultadoConsulta(paciente));
                        return paciente;

                    } else {
                        Janelas.telaErro("Não é possível realizar a consulta visto que não há consulta marcada.");
                    }
                }

                case 2 -> consulta.remarcarConsulta();

                case 3 -> consulta.cancelarConsulta();

                case 4 -> {

                    if (!(consulta.getDia() == null || consulta.getHora() == null || consulta.getSala() == null)) {
                        consulta.avisoConsultaMarcada();

                    } else {
                        Janelas.telaErro("Não é possível realizar a consulta visto que não há consulta marcada.");
                    }
                }

                case 5 -> sair = true;
            }

            if (sair) {
                break;
            }
        }

        return paciente;
    }

    private static int menuPaciente() {
        String mensagem = """
                O que deseja fazer?
                
                1 - Consultar
                2 - Remarcar consulta
                3 - Cancelar consulta
                4 - Ver detalhes do aviso
                5 - Sair do menu
                """;

        String escolha = Janelas.telaInput(mensagem).toLowerCase();

        switch (escolha) {

            case "1", "consultar", "1 - consultar" -> {
                return 1;
            }

            case "2", "remarcar consulta", "remarcar", "2 - remarcar consulta" -> {
                return 2;
            }

            case "3", "cancelar consulta", "cancelar", "2 - cancelar consulta" -> {
                return 3;
            }

            case "4", "ver detalhes do aviso", "ver detalhes", "4 - ver detalhes do aviso" -> {
                return 4;
            }

            case "5", "sair do menu", "sair", "5 - sair do menu" -> {
                return 5;
            }

            default -> {
                Janelas.telaErro("Opção não encontrada.");
                return menuPaciente();
            }
        }
    }

    //Um outro ator (um possível médico) seria o responsável por acrescentar essas informações no paciente
    private static Paciente resultadoConsulta(Paciente paciente) {

        while (true) {
            int escolha = menuConsulta();

            boolean sair = false;

            switch (escolha) {
                case 1 -> paciente.adicionarEnfermidade();

                case 2 -> Janelas.telaMensagem(String.valueOf(paciente.getEnfermidades()));

                case 3 -> paciente.removerEnfermidade();

                case 4 -> paciente.adicionarMedicacao();

                case 5 -> Janelas.telaMensagem(String.valueOf(paciente.getMedicacoes()));

                case 6 -> paciente.removerMedicacao();

                case 7 -> sair = true;
            }

            if (sair) {
                break;
            }

        }

        return paciente;
    }

    public static int menuConsulta() {
        String mensagem = """
                O que deseja realizar?
                
                1 - Adicionar enfermidade
                2 - Mostrar enfermidades
                3 - Remover enfermidade
                4 - Adicionar medicação
                5 - Mostrar medicações
                6 - Remover medicação
                7 - Sair do menu
                """;

        String escolha = Janelas.telaInput(mensagem).toLowerCase();
        switch (escolha) {
            case "1", "adicionar enfermidade",  "1 - adicionar enfermidade" -> {
                return 1;
            }

            case "2", "mostrar enfermidades", "2 - mostrar enfermidades" -> {
                return 2;
            }

            case "3", "remover enfermidade", "3 - remover enfermidade" -> {
                return 3;
            }

            case "4", "adicionar medicação", "4 - adicionar medicação" -> {
                return 4;
            }

            case "5", "mostrar medicações", "5 - motrar medicações" -> {
                return 5;
            }

            case "6", "remover medicação", "6 - remover medicação" -> {
                return 6;
            }

            case "7", "sair do menu", "sair", "7 - sair do menu" -> {
                return 7;
            }

            default -> {
                Janelas.telaErro("Opção não encontrada.");
                return menuConsulta();
            }
        }
    }

    public ArrayList<Paciente> getPacientes() {
        return pacientes;
    }
}
