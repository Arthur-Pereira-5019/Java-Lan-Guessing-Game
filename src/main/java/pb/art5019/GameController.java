package pb.art5019;

import java.util.ArrayList;
import java.util.Random;

public class GameController {
    private int[] usedTips;
    private int guesserId;
    private int remainingTime;
    private ArrayList<Integer> usedCardsIds = new ArrayList<>();
    private ArrayList<Player> players = new ArrayList<>();
    private int matchNumber = 1;
    private boolean isRunning = true;
    private ArrayList<Card> cards = new ArrayList<>();
    private Card currentCard;
    private int stackPosition;
    private Random randomizer = new Random();
    private int currentPrize = 0;
    private int maxPrize = 0;
    private int currentPlayerId;

    public void gameStartPoint() {

        shuffleCards();
        shufflePlayers();
        pickACard();

        maxPrize = currentCard.tips.length;
        currentPrize = maxPrize;


        gameLoop();

    }

    public void gameLoop() {

    }

    public void makeAGuess(String guess) {
        if(guess.toLowerCase().equals(currentCard.name.toLowerCase())) {
           defaultPonctuation();
           return;
        }

    }

    private void rotate() {
        currentPlayerId++;
        if(currentPlayerId == getConnectedPlayers()) {
            currentPlayerId = 0;
        }
    }


    private void defaultPonctuation() {
        ponctuate(currentPrize, currentPlayer());
    }

    private void ponctuate(int points, Player player) {
        player.setPoints(player.getPoints() + points);
    }

    private void shuffleCards() {
        cards.forEach(card -> card.randomizedId = card.id * randomizer.nextInt());
        cards = new ArrayList<>(cards.stream().sorted().toList());
    }

    private Player currentPlayer() {
        return players.get(currentPlayerId);
    }


    private void pickACard() {
        stackPosition++;
        currentCard = cards.get(stackPosition);
    }

    private void shufflePlayers() {
        players.forEach(players -> players.setRandomizedOrder(randomizer.nextInt()));
        players = new ArrayList<>(players.stream().sorted().toList());
    }

    private int getConnectedPlayers() {
        return players.size();
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
