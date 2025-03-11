package Control;

import Model.BlackjackModel;
import View.BlackjackView;

public class BlackjackController {
    private final BlackjackModel model;
    private final BlackjackView view;

    public BlackjackController(BlackjackModel model, BlackjackView view) {
        this.model = model;
        this.view = view;
        view.createGUI(this); 
        view.updateCards(model);  
    }

    public void playerHit() {
        model.playerHit();
        view.updateCards(model);
        if (model.isGameOver()) {
            checkBust();
        }
    }

    public void playerStand() {
        model.setGameOver(true);
        model.dealerTurn();
        view.updateCards(model);
        determineWinner();
    }

    public void restartGame() {
        model.startGame();
        view.updateCards(model);
        view.enableButtons();
        view.hideRestartButton();
        model.setGameOver(false);
    }

    private void checkBust() {
        if (model.getPlayerHand().calculateScore() > 21) {
            view.showMessage("Você estourou! Pontuação acima de 21.");
            view.disableButtons();
            view.showRestartButton();
        }
    }

    private void determineWinner() {
        int playerScore = model.getPlayerHand().calculateScore();
        int dealerScore = model.getDealerHand().calculateScore();

        if (dealerScore > 21 || playerScore > dealerScore) {
            view.showMessage("Você venceu!");
        } else if (playerScore < dealerScore) {
            view.showMessage("Você perdeu.");
        } else {
            view.showMessage("Empate!");
        }
        view.disableButtons();
        view.showRestartButton();
    }

    public void updateInitialCards() {
        view.updateCards(model);
    }
}