package edu.bothell.multi_ui.core;

import java.util.ArrayList;

public class Game {
    private final int MAX_PLAYERS = 2; // Nim is typically played with 2 players
    private final ArrayList<Player> players;
    private final State state;
    private int turn;
    private Player active;
    private int buttonsRemaining; // Number of buttons remaining

    public Game(Control c) {
        this.turn = 0;
        this.state = new World(); // Assuming World is your game state representation
        this.players = new ArrayList<>();
        this.buttonsRemaining = 16; // Start with 16 buttons
    }

    public Player addPlayer(Player p) {
        if (players.size() < MAX_PLAYERS) {
            this.players.add(p);
            if (this.active == null) active = p;
        }
        return p;
    }

    public Player addPlayer(char c, String sId) {
        Player p = new Player(c);
        p.setSId(sId);
        return addPlayer(p);
    }

    public char[] getPlayersChar() {
        char[] pcs = new char[players.size()];
        for (int i = 0; i < pcs.length; i++)
            pcs[i] = players.get(i).getChar();
        return pcs;
    }

    public boolean isValid(int buttonsToRemove, String sId) {
        // Check if the move is valid
        return (buttonsToRemove == 1 || buttonsToRemove == 2) && 
               buttonsToRemove <= buttonsRemaining && 
               active.getSId().equals(sId);
    }

    public char play(int buttonsToRemove, String sId) {
        if (!isValid(buttonsToRemove, sId)) return ' ';
        
        // Update the number of buttons remaining
        buttonsRemaining -= buttonsToRemove;

        // Check if the game is over
        if (buttonsRemaining == 0) {
            // The current player loses
            System.out.println(active.getChar() + " loses!");
            return 'L'; // Indicate loss
        }

        // Switch to the next player
        turn++;
        this.active = players.get(turn % players.size());

        return active.getChar();
    }

    public Player getActive() {
        return this.active;
    }

    public State getState() {
        return this.state;
    }

    public int getMaxPlayers() {
        return MAX_PLAYERS;
    }

    public int getPlayerCount() {
        return players.size();
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public int getTurn() {
        return this.turn;
    }

    public int getButtonsRemaining() {
        return buttonsRemaining;
    }
}
