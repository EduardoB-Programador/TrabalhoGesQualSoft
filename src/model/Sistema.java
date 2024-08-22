package model;

import util.DefinicaoImpossivelException;
import view.Janelas;
import java.util.Random;

public abstract class Sistema {
    private Pessoa pessoa;

    public Sistema(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public String formatarDia(String dia) {
        String diaFormat;

        if (dia.charAt(dia.length() - 1) == 's') {

            String diaFormat1 = dia.substring(0, dia.length() - 1); //Checar e retirar o "s" caso o input termine em "s"
            diaFormat = diaFormat1.toLowerCase(); //Deixar tudo em caixa baixa

        } else {
            diaFormat = dia.toLowerCase(); //Deixar tudo em caixa baixa
        }

        return diaFormat;
    }

    public String formatarHoraH(String hora) {
        String horaFormat;

            //System.out.println(hora.charAt(0) + " " + hora.charAt(1) + " " + hora.charAt(2) + " " + hora.charAt(3));
        if (hora.charAt(2) == ':') {
            horaFormat = hora.substring(0,2);

        } else if (hora.charAt(1) == ':') {
            horaFormat = hora.substring(0,1);

        } else {
            throw new DefinicaoImpossivelException("Defina uma hora válida.");

        }

        return horaFormat;
    }

    public String formatarHoraM(String hora) {
        String horaFormat;

        if (hora.charAt(2) == ':') {
            horaFormat = hora.substring(3);

        } else if (hora.charAt(1) == ':') {
            horaFormat = hora.substring(2);

        } else {
            throw new DefinicaoImpossivelException("Defina uma hora válida.");

        }

        return horaFormat;
    }

    public String definirSala() {
        Random random = new Random();

        return "Sala " + random.nextInt(1, 10) + "C";
    }

}