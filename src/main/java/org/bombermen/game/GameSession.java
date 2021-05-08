package org.bombermen.game;

import org.bombermen.gameMechanics.GameMechanics;
import org.bombermen.message.Message;
import org.bombermen.services.GameService;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameSession {
    private CopyOnWriteArrayList<Message> inputQueue;

    private ArrayList<Player> players;

    private GameMechanics gameMechanics;

    private final int MAX_N_OF_PLAYERS;
    private boolean gameReady;
    private String gameID;

    public GameSession(String gameID, int numOfPlayers) {
        this.gameID = gameID;
        inputQueue = new CopyOnWriteArrayList<>();
        players = new ArrayList<>();
        MAX_N_OF_PLAYERS = numOfPlayers;
    }

    public void createPlayer(String name){
        players.add(new Player(name));
        if(players.size() == MAX_N_OF_PLAYERS) {
            gameReady = true;
        }
    }

    public void pushMessage(Message message) {
        inputQueue.add(message);
    }

    public boolean isGameReady() {
        return gameReady;
    }

    public ArrayList<Message> getInputQueue() {
        ArrayList<Message> copy = new ArrayList(inputQueue);
        inputQueue.clear();
        return copy;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public int getMAX_N_OF_PLAYERS() {
        return MAX_N_OF_PLAYERS;
    }

    public void deleteSession() {
        GameService.getInstance().getGames().remove(gameID);
    }
}
