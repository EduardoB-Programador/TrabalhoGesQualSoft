package view;

import javax.swing.JOptionPane;

public class Janelas extends JOptionPane {


    public static void telaMensagem(String mensagem) {
        showMessageDialog(null, mensagem);

    }

    public static String telaInput(String mensagem) {
        return showInputDialog(null, mensagem);

    }

    public static void telaErro(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem, "ERRO", ERROR_MESSAGE);

    }

    public static boolean telaSN(String mensagem) {
        int num = JOptionPane.showConfirmDialog(null, mensagem, null, YES_NO_OPTION);
        //System.out.println(num);// checar o output
        return num == 0;
    }

}
