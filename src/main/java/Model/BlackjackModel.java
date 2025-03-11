package Model;

public class BlackjackModel {
    private Deck deck;
    private Hand playerHand;
    private Hand dealerHand;
    private boolean gameOver;

    public BlackjackModel() {
        startGame();
    }

    public void startGame() {
        setDeck(new Deck());
        getDeck().shuffle();
        setPlayerHand(new Hand());
        setDealerHand(new Hand());
        getPlayerHand().addCard(getDeck().dealCard());
        getPlayerHand().addCard(getDeck().dealCard());
        getDealerHand().addCard(getDeck().dealCard());
        getDealerHand().addCard(getDeck().dealCard());
        setGameOver(false);
    }

    public Deck getDeck() { return deck; }
    public Hand getPlayerHand() { return playerHand; }
    public Hand getDealerHand() { return dealerHand; }
    public boolean isGameOver() { return gameOver; }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void playerHit() {
        if (!isGameOver()) {
            getPlayerHand().addCard(getDeck().dealCard());
            if (getPlayerHand().calculateScore() > 21) {
                setGameOver(true);
            }
        }
    }

    public void dealerTurn() {
        while (getDealerHand().calculateScore() < 17) {
            getDealerHand().addCard(getDeck().dealCard());
        }
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void setPlayerHand(Hand playerHand) {
        this.playerHand = playerHand;
    }

    public void setDealerHand(Hand dealerHand) {
        this.dealerHand = dealerHand;
    }
}