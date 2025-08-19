package pb.art5019;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class GameController {
    //Managing variables
    private boolean isRunning = true;
    private Random randomizer = new Random();
    private int matchNumber = 1;
    private int remainingTime;

    //Prize related variables
    private int maxPrize = 0;
    private int currentPrize = 0;

    //Tips related variables
    private int[] usedTips;

    //Player related variables
    private int currentPlayerId;
    private ArrayList<Player> players = new ArrayList<>();

    //Cards related variables
    private int stackPosition;
    private Card currentCard;
    private ArrayList<Card> cards = new ArrayList<>();

    //Conection methods
    ServerSocket serverSocket;
    Socket socket;
    InputStream input;
    OutputStream output;


    public void gameStartPoint() throws IOException {
        startConnection();
        solveCards();

        shuffleCards();
        shufflePlayers();
        maxPrize = currentCard.tips.length;
        currentPrize = maxPrize;

        gameLoop();
    }

    public void solveCards() throws IOException {
        File sourceFolder = new File("target");
        ArrayList<File> JSONFiles = new ArrayList<>();
        ArrayList<Card> Cards = new ArrayList<>();
        JSON jsonManipulator = new JSON();
        FilenameFilter acceptJSON = new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.contains(".json");
            }
        };

        JSONFiles.addAll(List.of(sourceFolder.listFiles(acceptJSON)));
        for(File thisFile: JSONFiles) {
            Cards.add(jsonManipulator.readValue(thisFile, Card.class));
        }

        for(Card card: Cards) {
            System.out.println(card.name);
        }
    }

    public void startConnection() throws java.io.IOException {
        serverSocket = new ServerSocket(5019);
        //TODO: Multithreading
        System.out.println("Connected at IP: " + serverSocket.getInetAddress() + "and port " + serverSocket.getLocalPort());
        socket = serverSocket.accept();
        input = socket.getInputStream();
        output = socket.getOutputStream();
    }

    public String getIp() {
        try(final DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"), 10002);
            return socket.getLocalAddress().getHostAddress();
        } catch (SocketException | UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    public void gameLoop() {
        while(isRunning) {
            pickACard();
            if(getConnectedPlayers() == 0 || stackPosition == cards.size()) {
                isRunning = false;
            }
        }
        endGame();
    }

    private void endGame() {
        matchNumber++;
    }

    public void makeAGuess(String guess) {
        if(guess.equalsIgnoreCase(currentCard.name)) {
           rightGuess();
           return;
        }
        wrongGuess();
    }

    private void rotate() {
        currentPlayerId++;
        if(currentPlayerId == getConnectedPlayers()) {
            currentPlayerId = 0;
        }
    }

    private void rightGuess() {
        defaultPonctuation();
        rotate();
    }

    private void wrongGuess() {
        rotate();
        nextPrize();
    }


    private void nextPrize() {
        currentPrize--;
        if(currentPrize==0) {

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

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }


}
