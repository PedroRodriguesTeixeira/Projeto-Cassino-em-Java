package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import Control.FortuneTigerController;

public class FortuneTigerView extends JFrame {
    private JLabel[][] simbolosLabels;
    private JLabel saldoLabel;
    private JTextField valorApostaField;
    private JButton girarButton;
    private ImageIcon[] emojis;
    public FortuneTigerController controller; 

    public FortuneTigerView(FortuneTigerController controller) {
        this.controller = controller;

        setTitle("Fortune Tiger");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }

        emojis = new ImageIcon[11];
        emojis[0] = new ImageIcon(getClass().getResource("/imagens/icons8-abacaxi-100.png"));
        emojis[1] = new ImageIcon(getClass().getResource("/imagens/icons8-ameixa-100.png"));
        emojis[2] = new ImageIcon(getClass().getResource("/imagens/icons8-cogumelo-100.png"));
        emojis[3] = new ImageIcon(getClass().getResource("/imagens/icons8-copas-100.png"));
        emojis[4] = new ImageIcon(getClass().getResource("/imagens/icons8-estrela-100.png"));
        emojis[5] = new ImageIcon(getClass().getResource("/imagens/icons8-folha-de-carvalho-100.png"));
        emojis[6] = new ImageIcon(getClass().getResource("/imagens/icons8-laranja-100.png"));
        emojis[7] = new ImageIcon(getClass().getResource("/imagens/icons8-melancia-100.png"));
        emojis[8] = new ImageIcon(getClass().getResource("/imagens/icons8-morango-100.png"));
        emojis[9] = new ImageIcon(getClass().getResource("/imagens/icons8-sunflower-100.png"));
        emojis[10] = new ImageIcon(getClass().getResource("/imagens/icons8-tiger-100.png"));

        JPanel painelPrincipal = new JPanel(new BorderLayout(20, 20));
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel painelSimbolos = new JPanel(new GridLayout(3, 3, 10, 10));
        simbolosLabels = new JLabel[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                simbolosLabels[i][j] = new JLabel(emojis[0]);
                simbolosLabels[i][j].setBorder(BorderFactory.createLineBorder(Color.RED));
                painelSimbolos.add(simbolosLabels[i][j]);
            }
        }
        painelPrincipal.add(painelSimbolos, BorderLayout.CENTER);

        JPanel painelInfo = new JPanel(new FlowLayout());
        saldoLabel = new JLabel("Saldo: 1000");
        saldoLabel.setFont(new Font("Arial", Font.BOLD, 16));

        valorApostaField = new JTextField(10);
        girarButton = new JButton("Girar");
        girarButton.setFont(new Font("Arial", Font.BOLD, 14));

        // ActionListener com a correção
        girarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int valorAposta = Integer.parseInt(valorApostaField.getText());
                    FortuneTigerView.this.controller.girar(valorAposta); 
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor de aposta inválido! Digite um número inteiro.");
                }
            }
        });

        painelInfo.add(saldoLabel);
        painelInfo.add(valorApostaField);
        painelInfo.add(girarButton);
        painelPrincipal.add(painelInfo, BorderLayout.SOUTH);

        add(painelPrincipal);

        getContentPane().setBackground(Color.RED);

        setVisible(true);
    }

    public void atualizarSimbolos(int[][] simbolos) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                simbolosLabels[i][j].setIcon(emojis[simbolos[i][j]]);
            }
        }
    }

    public void atualizarSaldo(int saldo) {
        saldoLabel.setText("Saldo: " + saldo);
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(null, mensagem);
    }

    public JButton getGirarButton() {
        return girarButton;
    }
}