package model;

import util.DefinicaoImpossivelException;
import view.Janelas;

public class SistemaConsulta extends Sistema {
    private String sala, dia, hora;

    public SistemaConsulta(Pessoa pessoa) {
        super(pessoa);

        this.dia = definirDia();
        this.hora = definirHora(this.dia);
        this.sala = super.definirSala();

        avisoConsultaMarcada();
    }

    //Texto de disponibilidade das consultas
    public void disponibilidade() {
        String mensagem = """
                A disponibilidade do hospital atualmente para consultas é a seguinte:

                Segundas - 8:00 até 17:00
                Terças - 8:00 até 17:00
                Quartas - 8:00 até 17:00
                Quintas - 8:00 até 17:00
                Sextas - 8:00 até 15:30
                Sábados - 8:00 até 11:30
                Domingos - Não há atendimento
                """;

        Janelas.telaMensagem(mensagem);
    }

    //Reseta todos os valores da consulta
    public void cancelarConsulta() {

        boolean check = Janelas.telaSN("Você tem certeza que deseja cancelar a consulta?");
        if (check) {
            this.dia = null;
            this.hora = null;
            this.sala = null;
        }

    }

    //Realiza o procedimento de marcar consulta novamente
    public void remarcarConsulta() {

       boolean check = Janelas.telaSN("Você tem certeza que deseja remarcar a consulta?");
       if (check) {
           this.dia = definirDia();
           this.hora = definirHora(this.dia);
           this.sala = definirSala();
       }

    }

    //Método para verificar o input do dia da consulta
    public String definirDia() {
        disponibilidade();

        //Input do dia
        String dia = Janelas.telaInput("Qual o dia da semana?");

        //Dia com formatação para facilitar a verificação
        String diaFormat = super.formatarDia(dia);

        try {
            //Verificar se o dia é válido ou está disponível
            switch (diaFormat) {
                case "domingo" ->
                        throw new DefinicaoImpossivelException("\nNão é possível definir uma consulta para " + diaFormat + ".\n");

                case "segunda", "terça", "quarta", "quinta", "sexta", "sábado" -> {
                    return dia;
                }

                default -> throw new DefinicaoImpossivelException("\nO dia \"" + dia + "\" não é uma opção.\n");
            }

        } catch (DefinicaoImpossivelException e) {
            return definirDia();
        }
    }

    //Método para verificar o input da hora da consulta
    public String definirHora(String dia) {
        disponibilidade();

        String diaFormat = super.formatarDia(dia);

        //Input do horário
        String hora = Janelas.telaInput("Qual o horário que deseja consultar?");

        //Parte 1 horas
        //Verificar se o input da parte do horário correspondente às horas é válido
        try {
            //Hora formatada para facilitar a verificação
            String horaFormat = super.formatarHoraH(hora);

            try {
                //Hora
                int numHora = Integer.parseInt(horaFormat);

                //Verificação dos limites das horas
                if (diaFormat.equals("sábado") && (numHora < 8 || numHora > 11) ) {
                    throw new DefinicaoImpossivelException("Não é possível marcar uma consulta às " + hora + " horas.");

                } else if (diaFormat.equals("sexta") && (numHora < 8 || numHora > 15)) {
                    throw new DefinicaoImpossivelException("Não é possível marcar uma consulta às " + hora + " horas.");

                } else if (numHora < 8 || numHora > 17) {
                    throw new DefinicaoImpossivelException("Não é possível marcar uma consulta às " + hora + " horas.");

                }

                //Parte 2 Minutos
                //Verificar se o input da parte do horário correspondente aos minutos é válido
                //Hora formatada para facilitar a verificação
                String minFormat = super.formatarHoraM(hora);

                //Minutos
                int numMin = Integer.parseInt(minFormat);

                System.out.println(numMin);
                //Verificação dos limites dos minutos
                if ((diaFormat.equals("sábado") || diaFormat.equals("sexta")) && (numMin < 0 || numMin > 30) ) {
                    throw new DefinicaoImpossivelException("Não é possível marcar uma consulta às " + hora + " horas.");

                } else if (numHora == 17 && numMin > 0) {
                    throw new DefinicaoImpossivelException("Não é possível marcar uma consulta às " + hora + " horas.");

                } else if (numMin < 0 || numMin > 60) {
                    throw new DefinicaoImpossivelException("Não é possível marcar uma consulta às " + hora + " horas.");

                }

                return hora;

            } catch (NumberFormatException e) {
                Janelas.telaErro("Nenhum caractere que não seja numérico será aceito.");
                return definirHora(dia);

            }

        } catch (DefinicaoImpossivelException e) {
            return definirHora(dia);

        }
    }

    public String getDia() {
        return dia;
    }

    public String getHora() {
        return hora;
    }

    public String getSala() {
        return sala;
    }

    public void avisoConsultaMarcada() {
        String mensagem = "Sua consulta foi marcada! Abaixo estão as informações da consulta:\n" +
                "\nNome: " + super.getPessoa().getNome() +
                "\nDia da consulta: " + this.dia +
                "\nHorário da Consulta: " + this.hora +
                "\nSala onde será realizada a consulta: " + this.sala;

        Janelas.telaMensagem(mensagem);
    }
}
