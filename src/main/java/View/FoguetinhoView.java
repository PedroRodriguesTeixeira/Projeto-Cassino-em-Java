package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class FoguetinhoView extends JFrame {
    private JLabel labelMultiplicador;
    private JLabel labelSaldo;
    private JTextField campoAposta;
    public JButton botaoIniciar;  
    public JButton botaoParar;  
    private JLabel labelImagem;

    public FoguetinhoView() {
        setTitle("Jogo do Foguetinho");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel labelAposta = new JLabel("Valor da Aposta:");
        labelAposta.setBounds(50, 20, 300, 20);
        add(labelAposta);

        campoAposta = new JTextField();
        campoAposta.setBounds(50, 50, 300, 30);
        add(campoAposta);

        labelSaldo = new JLabel("Saldo: R$ 1000.00", SwingConstants.CENTER);
        labelSaldo.setBounds(50, 90, 300, 20);
        add(labelSaldo);

        labelMultiplicador = new JLabel("Multiplicador: 1.0x", SwingConstants.CENTER);
        labelMultiplicador.setBounds(50, 120, 300, 20);
        add(labelMultiplicador);

        botaoIniciar = new JButton("Iniciar");
        botaoIniciar.setBounds(50, 150, 100, 30);
        add(botaoIniciar);

        botaoParar = new JButton("Parar");
        botaoParar.setBounds(250, 150, 100, 30);
        add(botaoParar);
        botaoParar.setEnabled(false);

        ImageIcon imagemOrig = new ImageIcon("src/images/foguete.gif");
        Image instancImagem = imagemOrig.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon imagem = new ImageIcon(instancImagem);
        labelImagem = new JLabel(imagem);
        labelImagem.setBounds(100, 200, 200, 200);
        add(labelImagem);
    }

    public String getValorAposta() {
        return campoAposta.getText();
    }

    public void setMultiplicador(String valor) {
        labelMultiplicador.setText("Multiplicador: " + valor + "x");
    }

    public void setSaldo(String valor) {
        labelSaldo.setText("Saldo: R$ " + valor);
    }

    public void addIniciarListener(ActionListener listener) {
        botaoIniciar.addActionListener(listener);
    }

    public void addPararListener(ActionListener listener) {
        botaoParar.addActionListener(listener);
    }

    public void habilitarJogo(boolean iniciar) {
        botaoIniciar.setEnabled(!iniciar);
        botaoParar.setEnabled(iniciar);
    }

    public void exibirMensagem(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem);
    }

    public void resetarAposta() {
        campoAposta.setText("");
    }
}