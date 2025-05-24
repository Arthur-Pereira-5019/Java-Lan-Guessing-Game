package pb.art5019;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) throws IOException {
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


}