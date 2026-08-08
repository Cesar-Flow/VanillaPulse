package br.whoslv.vanillapulse.database.repository;

import br.whoslv.vanillapulse.database.entity.PlayerStatistics;
import br.whoslv.vanillapulse.statistic.StatisticType;

import java.sql.*;
import java.util.*;

public class PlayerStatisticsRepository {
    private final Connection connection;

    public PlayerStatisticsRepository(Connection connection){
        this.connection = connection;
    }


    public void saveStatistics(PlayerStatistics stats) {
        String sql = """
        INSERT INTO player_statistics
        (
            player_uuid,
            blocks_broken,
            player_deaths,
            player_kills,
            mob_kills,
            distance_traveled,
            play_time_hours,
            updated_at
        )
    
        VALUES (?, ?, ?, ?, ?, ?, ?, datetime('now', 'localtime'))
    
        ON CONFLICT(player_uuid)
    
        DO UPDATE SET
            blocks_broken = player_statistics.blocks_broken + excluded.blocks_broken,
            player_deaths = player_statistics.player_deaths + excluded.player_deaths,
            player_kills = player_statistics.player_kills + excluded.player_kills,
            mob_kills = player_statistics.mob_kills + excluded.mob_kills,
            distance_traveled = excluded.distance_traveled,
            play_time_hours = excluded.play_time_hours,
            
            updated_at = datetime('now', 'localtime')
        """;

        try (PreparedStatement ps = connection.prepareStatement(sql)){

            ps.setString(1, stats.getUuid().toString());
            ps.setInt(2, stats.getBlocksBroken());
            ps.setInt(3,stats.getPlayerDeaths());
            ps.setInt(4,stats.getPlayerKills());
            ps.setInt(5, stats.getMobKills());
            ps.setDouble(6, stats.getDistanceTraveled());
            ps.setInt(7, stats.getPlayTimeHours());

            ps.executeUpdate();

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public PlayerStatistics find(UUID uuid) {
        String sql = """
        SELECT *
        FROM player_statistics
        WHERE player_uuid = ?
        """;

        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(
                    1,
                    uuid.toString()
            );

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                PlayerStatistics stats = new PlayerStatistics(uuid);

                stats.setBlocksBroken(rs.getInt("blocks_broken"));
                stats.setPlayerDeaths(rs.getInt("player_deaths"));
                stats.setPlayerKills(rs.getInt("player_kills"));
                stats.setMobKills(rs.getInt("mob_kills"));
                stats.setDistanceTraveled(rs.getDouble("distance_traveled"));
                stats.setPlayTimeHours(rs.getInt("play_time_hours"));

                return stats;
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

        return new PlayerStatistics(uuid);
    }

    public Map<UUID, Map<StatisticType, Integer>> getAllStatistics() {
        Map<UUID, Map<StatisticType, Integer>> result = new HashMap<>();

        String sql = """
        SELECT
            player_uuid,
            blocks_broken,
            player_deaths,
            player_kills,
            mob_kills
        FROM player_statistics
    """;

        try (PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                UUID uuid = UUID.fromString(rs.getString("player_uuid"));
                Map<StatisticType, Integer> playerStats = new HashMap<>();

                playerStats.put(StatisticType.BLOCK_BROKEN, rs.getInt("blocks_broken"));
                playerStats.put(StatisticType.PLAYER_DEATH, rs.getInt("player_deaths"));
                playerStats.put(StatisticType.PLAYER_KILLED,rs.getInt("player_kills"));
                playerStats.put(StatisticType.MOB_KILLED,rs.getInt("mob_kills"));

                result.put(uuid, playerStats);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

        return result;
    }
}