package com.handball.manager.service;

import com.handball.manager.model.Player;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final Map<Long, Player> players = new ConcurrentHashMap<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    public List<Player> getAllPlayers() {
        return new ArrayList<>(players.values());
    }

    public Optional<Player> getPlayerById(Long id) {
        return Optional.ofNullable(players.get(id));
    }

    public Player createPlayer(Player player) {
        validateShirtNumberUnique(player.getShirtNumber(), null);
        
        Long id = idGenerator.getAndIncrement();
        player.setId(id);
        players.put(id, player);
        return player;
    }

    public Player updatePlayer(Long id, Player player) {
        if (!players.containsKey(id)) {
            throw new IllegalArgumentException("Player not found with id: " + id);
        }
        
        validateShirtNumberUnique(player.getShirtNumber(), id);
        
        player.setId(id);
        players.put(id, player);
        return player;
    }

    public Player deactivatePlayer(Long id) {
        Player player = players.get(id);
        if (player == null) {
            throw new IllegalArgumentException("Player not found with id: " + id);
        }
        player.setActive(false);
        return player;
    }

    public void deletePlayer(Long id) {
        players.remove(id);
    }

    private void validateShirtNumberUnique(Integer shirtNumber, Long currentPlayerId) {
        boolean exists = players.values().stream()
                .filter(p -> !p.getId().equals(currentPlayerId))
                .anyMatch(p -> p.getShirtNumber().equals(shirtNumber));
        
        if (exists) {
            throw new IllegalArgumentException(
                "Shirt number " + shirtNumber + " is already taken");
        }
    }

    // Initialize with sample data
    public void initSampleData() {
        createPlayer(new Player(null, "John", "Doe", Player.Position.GOALKEEPER, 1, true));
        createPlayer(new Player(null, "Jane", "Smith", Player.Position.LEFT_WING, 7, true));
        createPlayer(new Player(null, "Mike", "Johnson", Player.Position.CENTER_BACK, 10, true));
    }
}