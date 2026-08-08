package br.whoslv.vanillapulse.database.repository;

import net.minecraft.server.level.ServerPlayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class PlayerRepository {
    private final Connection connection;

    public PlayerRepository(Connection connection) {
        this.connection = connection;
    }

    public void registerPlayer(ServerPlayer player) {
        if (searchPlayer(player) == null) {
            String sql = """
                    INSERT INTO players(uuid, username)
                    VALUES (?, ?)
                    """;

            try (PreparedStatement ps = connection.prepareStatement(sql)) {
                ps.setString(1, player.getUUID().toString());
                ps.setString(2, player.getName().getString());

                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public String searchPlayer(ServerPlayer player) {
        String sql = """
        SELECT * FROM players WHERE uuid = ?
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, player.getUUID().toString());

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String playerUUID = rs.getString("uuid");

                return playerUUID;
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Set<UUID> getAllPlayers() {
        String sql = """
        SELECT uuid
        FROM players
        ORDER BY uuid
        """;

        Set<UUID> players = new HashSet<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                UUID uuid = UUID.fromString(resultSet.getString("uuid"));
                players.add(uuid);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return players;
    }
}
