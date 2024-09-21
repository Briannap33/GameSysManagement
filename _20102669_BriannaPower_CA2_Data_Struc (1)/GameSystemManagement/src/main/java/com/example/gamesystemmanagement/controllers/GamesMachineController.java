package com.example.gamesystemmanagement.controllers;

import com.example.gamesystemmanagement.models.GamesMachine;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.ListView;
import java.util.List;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class GamesMachineController {

    private MachineNode head;
    private MachineNode headMachine;
    public GamesMachine pickedMachine;

    @FXML
    private ListView<String> gamesListView; // ListView for games

    @FXML
    private TextField searchField;

    @FXML
    private List<Object> gamesMachines;

    @FXML
    private TextField name;

    @FXML
    private TextField manufacturer;

    @FXML
    private TextField description;

    @FXML
    private TextField type;

    @FXML
    private TextField media;

    @FXML
    private TextField launchYear;

    @FXML
    private TextField initialPrice;

    @FXML
    private TextField imageURL;

    @FXML
    private void initialize() {
        gamesMachines = new LinkedList<>();
        gamesMachines.add(new GamesMachine("Atari 2600", "Atari", "The Atari 2600, originally sold as the Atari Video Computer System or Atari VCS until November 1982, is a home video game console from Atari, Inc. Released on September 11, 1977, it is credited with popularizing the use of microprocessor-based hardware and games stored on ROM cartridges (a format first used with the Fairchild Channel F in 1976) instead of dedicated hardware with games physically built into the unit.", "Home video game console", "ROM cartridge", 1977, 199.99, "https://upload.wikimedia.org/wikipedia/commons/thumb/4/43/Atari-2600-Wood-4Sw-Set.jpg/1200px-Atari-2600-Wood-4Sw-Set.jpg"));
        gamesMachines.add(new GamesMachine("Atari 5200", "Atari", "The Atari 5200 SuperSystem, commonly known as the Atari 5200, is a home video game console that was introduced in 1982 by Atari Inc. as a higher-end complementary console for the popular Atari 2600. The 5200 was created to compete with the Intellivision, but wound up more directly competing with the ColecoVision shortly after its release.", "Home video game console", "ROM cartridge", 1982, 269.99, "https://upload.wikimedia.org/wikipedia/commons/thumb/9/9e/Atari-5200-4-Port-wController-L.jpg/1200px-Atari-5200-4-Port-wController-L.jpg"));
        gamesMachines.add(new GamesMachine("Atari 7800", "Atari", "The Atari 7800 ProSystem, or simply the Atari 7800, is a home video game console officially released by Atari Corporation in 1986. It is almost fully backward-compatible with the Atari 2600, the first console to have backward compatibility without the use of additional modules. It was considered affordable at a price of US$140.", "Home video game console", "ROM cartridge", 1986, 139.99, "https://upload.wikimedia.org/wikipedia/commons/thumb/0/0e/Atari-7800-Console-Set.jpg/ 1200px-Atari-7800-Console-Set.jpg"));
    }


    // Other methods and event handlers go here
    @FXML
    private void handleSearch() {
        String searchTerm = searchField.getText().toLowerCase().trim();

        if (searchTerm.isEmpty()) {
            updateGamesListView(gamesMachines);
        } else {
            // Filter machines based on search term
            List<Object> filteredMachines = gamesMachines.stream()
                    .filter(gameMachine -> GamesMachine.getName().toLowerCase().contains(searchTerm)
                            || GamesMachine.getManufacturer().toLowerCase().contains(searchTerm))
                    .collect(Collectors.toList());

            updateGamesListView(filteredMachines);
        }
    }

    private void updateGamesListView(List<Object> gamesMachines) {
        gamesListView.getItems().clear();
        for (Object machine : gamesMachines) {
            gamesListView.getItems().add(GamesMachine.getName());
        }
    }



    @FXML
    private void showAllGamesMachinesAdded() {
        MachineNode now = head;
        while (now != null) {
            System.out.println("The machine last added was:" + GamesMachine.getName());
            now = now.next;
        }
    }



    private GamesMachine createdMachineFromUser() {
        return new GamesMachine(
                name.getText(),
                manufacturer.getText(),
                description.getText(),
                type.getText(),
                media.getText(),
                Integer.parseInt(launchYear.getText()), // Assuming launchYear is an integer
                Double.parseDouble(initialPrice.getText()),
                imageURL.getText());
    }

    private void clearInfoGiven() {
        name.clear();
        manufacturer.clear();
        description.clear();
        type.clear();
        media.clear();
        launchYear.clear();
        initialPrice.clear();
        imageURL.clear();
    }

    private void sortInvalidInput() {
        if (name.getText().isEmpty() ||
                manufacturer.getText().isEmpty() ||
                description.getText().isEmpty() ||
                type.getText().isEmpty() ||
                media.getText().isEmpty() ||
                launchYear.getText().isEmpty() ||
                initialPrice.getText().isEmpty() ||
                imageURL.getText().isEmpty()) {
            System.out.println("Invalid!!");
        }
    }

    private void MachineNode(GamesMachine newMachine) {

    }

    @FXML
    private void addGamesMachine() {
        GamesMachine newMachine = null;
        MachineNode newNode = new MachineNode(newMachine);
        if (head == null) {
            head = newNode;
        } else {
            MachineNode now = head;
            while (now.next != null) {
                now = now.next;
            }
            now.next = newNode;
        }
    }
    private void addToLast(GamesMachine newMachine) {
        MachineNode newNode = new MachineNode(newMachine);
        if (head == null) {
            head = newNode;
            return;
        }
        MachineNode now = head;
        while (now.next != null) {
            now = now.next;
        }
        now.next = newNode;

    }

    public static class MachineNode {
        GamesMachine machine;
        MachineNode next;

        MachineNode(GamesMachine machine) {
            this.machine = machine;
            this.next = null;
        }

    }

    private void editGamesMachine() {
        if (pickedMachine == null) {
            System.out.println("Invalid selection");
        } else {
            pickedMachine.setName(name.getText());
            pickedMachine.setManufacturer(manufacturer.getText());
            pickedMachine.setDescription(description.getText());
            pickedMachine.setType(type.getText());
            pickedMachine.setMedia(media.getText());
            pickedMachine.setLaunchYear(Integer.parseInt(launchYear.getText()));
            pickedMachine.setInitialPrice(Double.parseDouble(initialPrice.getText()));
            pickedMachine.setImageURL(imageURL.getText());
        }
    }


    @FXML
    private void deleteGamesMachine() {
        if (pickedMachine == null) {
            System.out.println("Invalid selection");
        } else {
            MachineNode now = head;
            MachineNode previous = null;
            while (now != null && !now.machine.equals(pickedMachine)) {
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
}


