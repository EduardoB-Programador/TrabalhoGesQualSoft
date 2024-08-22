package util;

import view.Janelas;

public class DefinicaoImpossivelException extends RuntimeException {

    public DefinicaoImpossivelException(String mensagem) {
        super(mensagem);

        Janelas.telaErro(mensagem);
    }

}