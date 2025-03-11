package View;

import Control.BlackjackController;
import Model.BlackjackModel;
import Model.Card;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BlackjackView {
    private JFrame frame;
    private JPanel playerPanel, dealerPanel;
    private JButton hitButton, standButton, restartButton;

    public BlackjackView(BlackjackController controller) {
  
    }

    public void createGUI(BlackjackController controller) {
        frame = new JFrame("Blackjack");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        dealerPanel = new JPanel();
        dealerPanel.setBorder(BorderFactory.createTitledBorder("Cartas do Dealer"));
        dealerPanel.setLayout(new FlowLayout());

        playerPanel = new JPanel();
        playerPanel.setBorder(BorderFactory.createTitledBorder("Suas Cartas"));
        playerPanel.setLayout(new FlowLayout());

        hitButton = new JButton("Pedir Carta");
        standButton = new JButton("Parar");
        restartButton = new JButton("Recomeçar");

        hitButton.addActionListener(e -> controller.playerHit());
        standButton.addActionListener(e -> controller.playerStand());
        restartButton.addActionListener(e -> controller.restartGame());

        restartButton.setVisible(false);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(hitButton);
        buttonPanel.add(standButton);
        buttonPanel.add(restartButton);

        frame.add(dealerPanel, BorderLayout.NORTH);
        frame.add(playerPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void updateCards(BlackjackModel model) {
        playerPanel.removeAll();
        for (Card card : model.getPlayerHand().getCards()) {
            JLabel cardLabel = createCardLabel(card.toString());
            playerPanel.add(cardLabel);
        }

        dealerPanel.removeAll();
        List<Card> dealerCards = model.getDealerHand().getCards();
        for (int i = 0; i < dealerCards.size(); i++) {
            JLabel cardLabel;
            if (model.isGameOver() || i == 0) {
                cardLabel = createCardLabel(dealerCards.get(i).toString());
            } else {
                cardLabel = createCardLabel("??");
            }
            dealerPanel.add(cardLabel);
        }

        
        try {
            Thread.sleep(500); 
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        playerPanel.revalidate();
        playerPanel.repaint();
        dealerPanel.revalidate();
        dealerPanel.repaint();
    }

    private JLabel createCardLabel(String card) {
        JLabel label = new JLabel(card, SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(60, 90));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setOpaque(true);
        label.setBackground(Color.WHITE);
        label.setForeground(Color.BLACK);
        return label;
    }

    public void showMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }

    public void disableButtons() {
        hitButton.setEnabled(false);
        standButton.setEnabled(false);
    }

    public void enableButtons() {
        hitButton.setEnabled(true);
        standButton.setEnabled(true);
    }

    public void showRestartButton() {
        restartButton.setVisible(true);
    }

    public void hideRestartButton() {
        restartButton.setVisible(false);
    }
}