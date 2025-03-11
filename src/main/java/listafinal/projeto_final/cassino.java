package listafinal.projeto_final;

import Control.BlackjackController;
import Model.BlackjackModel;
import View.BlackjackView;
import Control.FortuneTigerController;
import Model.FortuneTigerModel;
import View.FortuneTigerView;
import Control.FoguetinhoController;
import Model.FoguetinhoModel;
import View.FoguetinhoView;

import javax.swing.*;
import java.awt.*;

public class cassino {
    public static void main(String[] args) {
        // Cria a janela principal
        JFrame frame = new JFrame("Menu do Cassino");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout(10, 10)); 

        // Cria o título
        JLabel titulo = new JLabel("Bem-vindo ao Cassino!", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 22));
        titulo.setForeground(new Color(0, 102, 204)); 
        frame.add(titulo, BorderLayout.NORTH);

        // Painel de botões
        JPanel painelBotoes = new JPanel();
        painelBotoes.setLayout(new GridLayout(4, 1, 10, 10)); 
        painelBotoes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 

        // Criação dos botões
        JButton botaoJogo1 = new JButton("Jogo 1 - Fortune Tiger");
        JButton botaoJogo2 = new JButton("Jogo 2 - BlackJack");
        JButton botaoJogo3 = new JButton("Jogo 3 - Foguetinho");
        JButton botaoSair = new JButton("Sair");

        // Estiliza os botões
        estilizarBotao(botaoJogo1);
        estilizarBotao(botaoJogo2);
        estilizarBotao(botaoJogo3);
        estilizarBotao(botaoSair);

        // Adiciona os botões ao painel
        painelBotoes.add(botaoJogo1);
        painelBotoes.add(botaoJogo2);
        painelBotoes.add(botaoJogo3);
        painelBotoes.add(botaoSair);

        frame.add(painelBotoes, BorderLayout.CENTER);

        // Adiciona ação ao botão "Jogo 1" (inicia o Fortune Tiger)
        botaoJogo1.addActionListener(e -> {
            FortuneTigerModel model = new FortuneTigerModel();
            FortuneTigerView view = new FortuneTigerView(null);
            FortuneTigerController controller = new FortuneTigerController(model, view);
            view.controller = controller;
            view.setVisible(true);
        });

        // Adiciona ação ao botão "Jogo 2" (inicia o Blackjack)
        botaoJogo2.addActionListener(e -> {
            BlackjackModel model = new BlackjackModel();
            BlackjackView view = new BlackjackView(null);
            BlackjackController controller = new BlackjackController(model, view); 
            // NÃO chame view.createGUI() aqui, pois já foi chamado no construtor do controller
        });

        // Adiciona ação ao botão "Jogo 3" (inicia o Foguetinho)
        botaoJogo3.addActionListener(e -> {
            FoguetinhoModel model = new FoguetinhoModel();
            FoguetinhoView view = new FoguetinhoView();
            FoguetinhoController controller = new FoguetinhoController(model, view);
            view.addIniciarListener(controller);
            view.addPararListener(controller);
            view.setVisible(true);
        });

        // Adiciona ação ao botão "Sair"
        botaoSair.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(frame, "Deseja realmente sair?", "Confirmação", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        // Exibe a janela
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }

    // Método para estilizar os botões
    private static void estilizarBotao(JButton botao) {
        botao.setFont(new Font("Arial", Font.BOLD, 14));
        botao.setBackground(new Color(0, 153, 76)); 
        botao.setForeground(Color.WHITE);
        botao.setFocusPainted(false); 
        botao.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        botao.setCursor(new Cursor(Cursor.HAND_CURSOR)); 
    }
}