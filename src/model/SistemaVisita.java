package model;

import util.DefinicaoImpossivelException;
import view.Janelas;

public class SistemaVisita extends Sistema {
    private String sala, dia, hora;
    private Visitante visitante;

    public SistemaVisita(Pessoa pessoa) {
        super(pessoa);

        disponibilidade();
        this.dia = definirDia();
        this.hora = definirHora(this.dia);
        this.sala = super.definirSala();
        
    }

    //Disponibilidade para visitas dos pacientes no hospital
    public void disponibilidade() {
        String mensagem = """
                A disponibilidade do hospital atualmente para visitas é a seguinte:

                Dias de semana - 14:00 até 15:00
                Finais de semana - 13:00 até 15:00

                Após os horários escritos acima o período de visitas termina, e não será mais permitido a visita nesse dia.""";

        Janelas.telaMensagem(mensagem);
    }

    public void cancelarVisita() {

    }

    //Define o dia da visita
    public String definirDia() {
        String diaFormat, dia = Janelas.telaInput("Qual o dia da semana?");

        //Formata o dia para realizar a checagem de forma mais simples
        diaFormat = super.formatarDia(dia);

        //Checa pra ver se o dia é um dia válido
        try {

            //Dia valido = retorna dia
            switch (diaFormat) {
                case "segunda", "terça", "quarta", "quinta", "sexta", "sábado", "domingo" -> {
                    return dia;
                }

                default -> throw new DefinicaoImpossivelException("\nO dia \"" + dia + "\" não é uma opção.\n");

            }

        } catch (DefinicaoImpossivelException e) {
            definirDia();
        }

        //Retorna dia para definir o dia da visita
        return dia;
    }

    //Define hora para a visita
    public String definirHora(String dia) {
        String diaFormat = super.formatarDia(dia);

        String hora = Janelas.telaInput("Qual a hora que deseja visitar?");

        try {
            String horaFormat = super.formatarHoraH(hora);

            try {
                int numHora = Integer.parseInt(horaFormat);

                if (!(diaFormat.equals("sábado") || diaFormat.equals("domingo")) && (numHora != 14)) {
                    throw new DefinicaoImpossivelException("Não é possível marcar uma visita às " + hora + " horas");

                } else if (numHora != 13 && numHora != 14) {
                    throw new DefinicaoImpossivelException("Não é possível marcar uma visita às " + hora + " horas");
                }

            } catch (NumberFormatException e) {
                Janelas.telaErro("Nenhum caractere que não seja numérico será aceito.");
                definirHora(dia);
            }


            String minFormat = super.formatarHoraM(hora);

            try {
                int numMin = Integer.parseInt(minFormat);

                if (numMin < 0 || numMin > 60) {
                    throw new DefinicaoImpossivelException("Não é possível marcar uma visita às " + hora + " horas");
                }

            } catch (NumberFormatException e) {
                Janelas.telaErro("Nenhum caractere que não seja numérico será aceito.");
                definirHora(dia);
            }

        } catch (DefinicaoImpossivelException e) {
            definirHora(dia);
        }

        return hora;
    }

    public void avisoVisitaMarcada() {
        String mensagem = "Sua visita foi marcada! Abaixo estão as informações:\n" +
                "\nNome: " + super.getPessoa().getNome() +
                "\nDia da visita: " + this.dia +
                "\nHorário da visita: " + this.hora +
                "\nSala para visitar: " + this.sala;
    }
}
