package Model;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cards;

    public Hand() {
        cards = new ArrayList<>();
    }

    public void addCard(Card card) {
        getCards().add(card);
    }

    public List<Card> getCards() {
        return cards;
    }

    public int calculateScore() {
        int score = 0, aces = 0;
        for (Card card : getCards()) {
            String rank = card.getRank();
            if ("JQK".contains(rank)) score += 10;
            else if (rank.equals("A")) { aces++; score += 11; }
            else score += Integer.parseInt(rank);
        }
        while (score > 21 && aces > 0) { score -= 10; aces--; }
        return score;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }
}