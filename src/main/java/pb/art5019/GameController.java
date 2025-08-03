package pb.art5019;

import java.util.ArrayList;
import java.util.Random;

public class GameController {
    private int[] usedTips;
    private Player[] players;
    private int guesserId;
    private int remainingTime;
    private ArrayList<Integer> usedCardsIds = new ArrayList<>();
    private int matchNumber = 1;
    private boolean isRunning = true;
    private ArrayList<Card> cards = new ArrayList<>();
    private Card currentCard;
    private int stackPosition;
    private Random randomizer = new Random();

    public void gameStartPoint() {
        shuffleCards();
        pickACard();




    }

    private void shuffleCards() {
        cards.forEach(card -> card.randomizedId = card.id * randomizer.nextInt());
        cards = new ArrayList<>(cards.stream().sorted().toList());
    }


    private void pickACard() {
        currentCard = cards.get(stackPosition);
    }

    private void shufflePlayers() {

    }


    public int[] getUsedTips() {
        return usedTips;
    }

    public void setUsedTips(int[] usedTips) {
        this.usedTips = usedTips;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public int getGuesserId() {
        return guesserId;
    }

    public void setGuesserId(int guesserId) {
        this.guesserId = guesserId;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }


}
