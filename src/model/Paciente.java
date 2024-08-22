package model;

import view.Janelas;

import java.util.ArrayList;

public class Paciente extends Pessoa {
    private double peso, altura;
    private boolean temPlano;
    private ArrayList<String> medicamentos = new ArrayList<>(), enfermidades = new ArrayList<>();


    public Paciente(String nome, String endereco) {
        super(nome, endereco);

        this.peso = setPeso();
        this.altura = setAltura();
        this.temPlano = setTemPlano();
    }

    public Paciente(Pessoa pessoa) {
        super(pessoa.getCPF(), pessoa.getNome(), pessoa.getSenha(), pessoa.getEndereco(), pessoa.getIdade());

        this.peso = setPeso();
        this.altura = setAltura();
        this.temPlano = setTemPlano();
    }

    public double setPeso() throws NumberFormatException {
        double peso;

        try {
            peso = Double.parseDouble(Janelas.telaInput("Qual o seu peso?"));

        } catch (NumberFormatException e) {
            Janelas.telaErro("Qualquer valor que não seja numérico será aceito.");
            return setPeso();

        }

        return peso;
    }

    public double setAltura() throws NumberFormatException {
        double altura;

        try {
            altura = Double.parseDouble(Janelas.telaInput("Qual a sua altura?"));

        } catch (NumberFormatException e) {
            Janelas.telaErro("Qualquer valor que não seja numérico será aceito.");
            return setAltura();

        }

        return altura;
    }

    public boolean setTemPlano() {
        return Janelas.telaSN("Você possui plano?");
    }

    public double getPeso() {return this.peso;}

    public double getAltura() {return this.altura;}

    public boolean isTemPlano() {
        return this.temPlano;
    }

    public void adicionarMedicacao() throws NumberFormatException {
        String medicacao = Janelas.telaInput("Insira o nome da medicação que o paciente deve tomar:");
        int dosagem = 0;

        try {
            dosagem = Integer.parseInt(Janelas.telaInput("Insira a dosagem dessa medicação em mg (miligrama):"));

        } catch (NumberFormatException e) {
            Janelas.telaErro("Insira um valor numérico.");
            adicionarMedicacao();

        }

        String medDose = medicacao + " " + dosagem + "mg";

        this.medicamentos.add(medDose);
        Janelas.telaMensagem("A medicação \"" + medicacao + "\" foi adicionada ao paciente.");
    }

    public void removerMedicacao() throws NumberFormatException {
        StringBuilder mensagem = getMedicacoes();
        mensagem.append("\n\nEscreva o número que representa uma das medicações para ser removida:");

        try {
            int remover = Integer.parseInt(Janelas.telaInput(String.valueOf(mensagem)));

            this.medicamentos.remove(remover - 1);
        } catch (NumberFormatException e) {
            Janelas.telaErro("Insira o número que representa a medicação. Valores não numéricos ou diferentes dos mencionados não serão aceitos.");
            removerMedicacao();

        }
    }

    public StringBuilder getMedicacoes() {

        StringBuilder mensagem = new StringBuilder("A(s) medicação(ões) abaixo é(são) referente(s) ao paciente " + super.getNome() + ":\n");
        int id = 1;

        for (String medicacao : this.medicamentos) {

            mensagem.append(id).append(" - ").append(medicacao).append("\n");
            ++id;
        }

        return mensagem;
    }

    public void adicionarEnfermidade() {
        String enfermidade = Janelas.telaInput("Insira o nome da enfermidade que o paciente possui:");

        this.enfermidades.add(enfermidade);
        Janelas.telaMensagem("O paciente " + super.getNome() + " foi diagnosticado com a enfermidade " + enfermidade + ".");
    }

    public void removerEnfermidade() throws NumberFormatException {
        StringBuilder mensagem = getEnfermidades();
        mensagem.append("\n\nEscreva o número que representa uma das enfermidades para ser removida:");
        String enfermidade = Janelas.telaInput(String.valueOf(mensagem));

        try {
            int remover = Integer.parseInt(enfermidade);

            this.enfermidades.remove(remover - 1);

        } catch (NumberFormatException e) {
            Janelas.telaErro("Insira o número que representa a enfermidade. Valores não numéricos não serão aceitos.");
            removerEnfermidade();
        }

    }

    public StringBuilder getEnfermidades() {
        StringBuilder mensagem = new StringBuilder("A(s) enfermidade(s) abaixo é(são) referente(s) ao paciente " + super.getNome() + ":\n");
        int id = 1;

        for (String enfermidade : this.enfermidades) {

            mensagem.append(id).append(" - ").append(enfermidade).append("\n");
            ++id;
        }

        return mensagem;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nPeso: " + this.peso +
                "\nAltura: " + this.altura +
                "\nPossui plano: " + (this.temPlano ? "sim" : "não");
    }
}