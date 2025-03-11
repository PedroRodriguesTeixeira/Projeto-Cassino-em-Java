package Control;

import Model.FoguetinhoModel;
import View.FoguetinhoView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FoguetinhoController implements ActionListener {
    private FoguetinhoModel model;
    private FoguetinhoView view;
    private Timer timer;

    public FoguetinhoController(FoguetinhoModel model, FoguetinhoView view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == view.botaoIniciar) {
            try {
                double aposta = Double.parseDouble(view.getValorAposta());
                if (model.saldoSuficiente(aposta)) {
                    model.iniciarJogo(aposta);
                    view.habilitarJogo(true);
                    iniciarJogo();
                } else {
                    view.exibirMensagem("Saldo insuficiente!");
                }
            } catch (NumberFormatException ex) {
                view.exibirMensagem("Valor de aposta inválido!");
            }
        } else if (e.getSource() == view.botaoParar) {
            pararJogo();
        }
    }

    private void iniciarJogo() {
        timer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (model.isEmAndamento()) {
                    model.aumentarMultiplicador();
                    view.setMultiplicador(String.format("%.2f", model.getMultiplicador()));
                    if (model.verificarCrash()) {
                        encerrarJogo(false);
                    }
                }
            }
        });
        timer.start();
    }

    private void pararJogo() {
        if (model.isEmAndamento()) {
            encerrarJogo(true);
        }
    }

    private void encerrarJogo(boolean ganhou) {
        model.encerrarJogo();
        view.habilitarJogo(false);
        timer.stop();

        if (ganhou) {
            double ganho = model.calcularGanho();
            model.adicionarGanho(ganho);
            view.exibirMensagem("Você ganhou R$ " + String.format("%.2f", ganho));
        } else {
            view.exibirMensagem("Crash! Você perdeu!");
        }

        // Correção: atualizar saldo aqui, fora do if
        view.setSaldo(String.format("%.2f", model.getSaldo())); 

        view.resetarAposta();
        view.setMultiplicador("1.00");
    }
}