package com.example.gamesystemmanagement.controllers;

import com.example.gamesystemmanagement.models.Game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.*;



public class GameController {

    @FXML
    private TextField gameName;

    @FXML
    private TextField gameGenre;

    @FXML
    private TextField gamePrice;

    @FXML
    private TextField gameRating;

    @FXML
    private TextField machineDevelopedFor; // Added machineDevelopedFor field

    @FXML
    private TextField releaseYear; // Changed from Char to TextField

    @FXML
    private TextField coverImageURL;

    @FXML
    private ListView<String> gameList;

    private final List<Game> games = new LinkedList<>();
    private GameNode head;
    private Game pickedGame;
    private final String fileName = "Game.xml";
    private HashMap<String, Game> gameHashMap;

    public GameController() {
        gameHashMap = new HashMap<>();
    }

    @FXML
    protected void onRemoveButtonClick() {
        deleteGame(gameName.getText());
    }

    private void deleteGame(String text) {
        GameNode current = head;
        GameNode previous = null;

        while (current != null) {
            if (current.getGame().getName().equals(text)) {
                if (previous == null) {
                    head = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                break;
            }
            previous = current;
            current = current.getNext();
        }
        gameList.getItems().clear();
        listAllGames();

    }
public void editGame() {
        GameNode current = head;
        GameNode previous = null;

        while (current != null) {
            if (current.getGame().getName().equals(head)) {
                if (previous == null) {
                    head = current.getNext();
                } else {
                    previous.setNext(current.getNext());
                }
                break;
            }
            previous = current;
            current = current.getNext();
        }
        gameList.getItems().clear();
        listAllGames();

    }

    @FXML
    protected void onEditButtonClick() {
        editGame();
    }

    @FXML
    protected void onAddButtonClick() {
        addGame(gameName.getText(), gameInputByUser());
    }

    private void addGame(String text, Game gameInputByUser) {
        GameNode newNode = new GameNode();
        newNode.setGame(gameInputByUser);
        newNode.setNext(head);
        head = newNode;
        gameList.getItems().clear();
        listAllGames();
    }

    @FXML
    protected void onSearchButtonClick() {
        searchGame(gameName.getText());
    }

    private void searchGame(String text) {
        GameNode current = head;
        while (current != null) {
            if (current.getGame().getName().equals(text)) {
                pickedGame = current.getGame();
                break;
            }
            current = current.getNext();
        }
        System.out.println(pickedGame);
    }

    @FXML
    protected void onClearButtonClick() {
        gameName.clear();
        gameGenre.clear();
        gamePrice.clear();
        gameRating.clear();
        machineDevelopedFor.clear();
        releaseYear.clear();
        coverImageURL.clear();
    }

    public void listAllGames() {
        games.forEach(game -> gameList.getItems().add(game.toString()));
    }

    public void searchPartialGameInfo(String partName, String partPublisher, String partDesc, String partDeveloper, String partMDF, Integer partRelYear) {
        for (Map.Entry<String, Game> entry : gameHashMap.entrySet()) {
            String name = entry.getKey();
            Game game = entry.getValue();

            if (name.contains(partName) &&
                    game.getPublisher().contains(partPublisher) &&
                    game.getDescription().contains(partDesc) &&
                    game.getDeveloper().contains(partDeveloper) &&
                    game.getMachineDevelopedFor().contains(partMDF) &&
                    game.getReleaseYear() == partRelYear ) {

                System.out.println("The matching game is: ");
                System.out.println("Game name: " + game.getName());
                System.out.println("Published by: " + game.getPublisher());
                System.out.println("Description: " + game.getDescription());
                System.out.println("Machine developed for: " + game.getMachineDevelopedFor());
                System.out.println("Game release Year: " + game.getReleaseYear());
            }
        }
    }

    // Other methods and event handlers go here

    private Game gameInputByUser() {
        return new Game(
                gameName.getText(),
                gameGenre.getText(),
                gamePrice.getText(),
                gameRating.getText(),
                machineDevelopedFor.getText(),
                Integer.parseInt(releaseYear.getText()), // Assuming releaseYear is an integer
                coverImageURL.getText()
        );
    }



    @FXML
    public void navigateToMainMenu(ActionEvent actionEvent) {
        if (actionEvent.getSource() == gameList) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("path/to/anotherFXML.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private class GameNode {
        private GameNode next;
        private Game game;
        public Game getGame() {
            return game;
        }

        public void setGame(Game game) {
            this.game = game;
        }

        public void setNext(GameNode head) {
            return;
        }

        public GameNode getNext() {
            return next;
        }
    }



}
