package Control;

import javax.swing.*;

import Model.FortuneTigerModel;
import View.FortuneTigerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FortuneTigerController {
    private final FortuneTigerModel model;
    public FortuneTigerView view; 
    private Timer timer;

    public FortuneTigerController(FortuneTigerModel model, FortuneTigerView view) {
        this.model = model;
        this.view = view;
    }

    public void girar(int valorAposta) {
        System.out.println("Início do método girar");
        if (valorAposta > 0 && valorAposta <= model.getSaldo()) {
            model.setValorAposta(valorAposta);
            model.atualizarSaldo(-valorAposta);
            view.atualizarSaldo(model.getSaldo());

            view.getGirarButton().setEnabled(false);

            timer = new Timer(100, new ActionListener() {
                int contador = 0;
                @Override
                public void actionPerformed(ActionEvent e) {
                    model.girar();
                    view.atualizarSimbolos(model.getSimbolos());
                    contador++;
                    if (contador >= 20) {
                        timer.stop();
                        verificarCombinacao();
                        view.getGirarButton().setEnabled(true);
                    }
                }
            });
            timer.start();
        } else {
            view.exibirMensagem("Valor de aposta inválido! Digite um valor entre 1 e " + model.getSaldo());
        }
        System.out.println("Fim do método girar");
    }

    private void verificarCombinacao() {
        if (model.verificarCombinacao()) {
            int simboloVencedor = model.getSimbolos()[model.getCombinacaoVencedora()[0] / 3][model.getCombinacaoVencedora()[0] % 3];
            int ganho = model.calcularGanho(simboloVencedor);
            model.atualizarSaldo(ganho);
            view.atualizarSaldo(model.getSaldo());
            view.exibirMensagem("Você ganhou! Multiplicador: " + (ganho / model.getValorAposta()));
        } else {
            view.exibirMensagem("Você perdeu!");
        }
    }

    public static void main(String[] args) {
        FortuneTigerModel model = new FortuneTigerModel();
        FortuneTigerView view = new FortuneTigerView(null); 
        FortuneTigerController controller = new FortuneTigerController(model, view); 
        view.controller = controller; 
    }
}