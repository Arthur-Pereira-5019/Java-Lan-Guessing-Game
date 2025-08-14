package pb.art5019;


import java.io.IOException;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {
        System.out.println("Guessing Game by Art5019 v0.0.1 type your option: (J)oin game, (C)reate game");
Scanner scanner = new Scanner(System.in);
        if(scanner.nextLine().equals("C")) {
            GameController gm = new GameController();
            gm.gameStartPoint();
        }
        Client client = new Client();
        client.setupClient();



    }


}