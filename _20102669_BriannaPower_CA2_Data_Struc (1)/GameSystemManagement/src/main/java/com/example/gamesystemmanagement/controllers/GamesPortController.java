package com.example.gamesystemmanagement.controllers;

import com.example.gamesystemmanagement.models.GamesPort;
import javafx.fxml.FXML;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;


public class GamesPortController {

    @FXML
    private TextField originalGame;

    @FXML
    private TextField newMachine;

    @FXML
    private TextField portDeveloper;

    @FXML
    private TextField portReleaseYear;

    @FXML
    private TextField coverImageURL;

    @FXML
    private ListView<String> gamePortList;

    private final List<GamesPort> gamesPorts = new LinkedList<>();


    private PortNode head;
    public GamesPort pickedPort;

    private final String fileName = "GamesPort.xml";
    private HashMap<String, GamesPort> portHashMap;

    public GamesPortController(){
        portHashMap = new HashMap<>();
    }
    public void addGamesPort(String originalGame, GamesPort port) {
        try {
            GamesPort newPort = createdPortFromUser();
            addToEnd(newPort);
            showAllGamesPortsAdded();
            clearInfoGiven();
        } catch (NumberFormatException e) {
            sortInvalidInput();
        }
        portHashMap.put(originalGame, port);

    }

    private GamesPort createdPortFromUser() {
        // needs fxml fields
        return new GamesPort(
                originalGame.getText(),
                newMachine.getText(),
                portDeveloper.getText(),
                Integer.parseInt(portReleaseYear.getText()),
                coverImageURL.getText());

    }

    private void clearInfoGiven() {
        if(originalGame.getText().isEmpty() ||
                newMachine.getText().isEmpty() ||
                portDeveloper.getText().isEmpty() ||
                portReleaseYear.getText().isEmpty() ||
                coverImageURL.getText().isEmpty()){
            System.out.println("Invalid!!");
        }
    }

    private void sortInvalidInput() {
        System.out.println("Invalid!!");
    }


    private void showAllGamesPortsAdded() {
        PortNode now = head;
        while (now != null) {
            System.out.println("The port last added was:" + now.port.getNewMachine());
            now = now.next;
        }
    }

    private void addToEnd(GamesPort newPort) {
        PortNode newNode = new PortNode(newPort);
        if (head == null) {
            head = newNode;
            return;
        }
        PortNode now = head;
        while (now.next != null) {
            now = now.next;
        }
        now.next = newNode;
    }

    public static class PortNode {
        GamesPort port;
        PortNode next;

        PortNode(GamesPort port) {
            this.port = port;
            this.next = null;
        }

    }
    private void editGamesPort() {
        if (pickedPort == null) {
            System.out.println("Invalid selection");
            return;
        }
//need fxml
        originalGame.setText(String.valueOf(pickedPort.getOriginalGame()));
        newMachine.setText(pickedPort.getNewMachine());
        portDeveloper.setText(pickedPort.getPortDeveloper());
        portReleaseYear.setText(String.valueOf(pickedPort.getPortReleaseYear()));
        coverImageURL.setText(pickedPort.getCoverImageURL());


        updateUserSelectedPort();
    }

    private void updateUserSelectedPort() {
        pickedPort.setOriginalGame(originalGame.getText());
        pickedPort.setNewMachine(newMachine.getText());
        pickedPort.setPortDeveloper(portDeveloper.getText());
        pickedPort.setPortReleaseYear(Integer.parseInt(portReleaseYear.getText()));
        pickedPort.setCoverImageURL(coverImageURL.getText());

    }

    private void deleteGamePort(String name) {

        if (pickedPort == null) {
            System.out.println("Invalid selection");
        } else {
            PortNode now = head;
            PortNode previous = null;
            while (now != null && !now.port.equals(pickedPort)) {
                previous = now;
                now = now.next;
            }
            if (now != null) {
                if (previous == null) {
                    head = head.next;
                } else {
                    previous.next = now.next;
                }

            }
        }
    }
    public void searchPartialPortInfo(String ogGamePart, String partMachine,String partDeveloper, Integer partRelYear){
        for (Map.Entry<String, GamesPort> entry : portHashMap.entrySet()) {
            String originalGame = entry.getKey();

            GamesPort port = entry.getValue();

            if (originalGame.contains(ogGamePart) && port.getNewMachine().contains(partMachine) && port.getPortDeveloper().contains(partDeveloper)) {
                port.getPortReleaseYear();
            }
        }
    }

    public void load() throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            portHashMap = (HashMap<String, GamesPort>) in.readObject();
        }
    }

    public void save() throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(portHashMap);
        }
    }

    public String fileName() {
        return fileName;
    }


}
