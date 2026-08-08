package br.whoslv.vanillapulse;

import br.whoslv.vanillapulse.database.DatabaseManager;
import br.whoslv.vanillapulse.database.repository.PlayerRepository;
import br.whoslv.vanillapulse.database.repository.PlayerStatisticsRepository;
import br.whoslv.vanillapulse.event.listener.BlockBreakListener;
import br.whoslv.vanillapulse.event.listener.EntityDeathListener;
import br.whoslv.vanillapulse.event.listener.PlayerJoin;
import br.whoslv.vanillapulse.statistic.DistanceService;
import br.whoslv.vanillapulse.statistic.PlayTimeService;
import br.whoslv.vanillapulse.statistic.StatisticService;
import br.whoslv.vanillapulse.sync.SyncManager;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.resources.Identifier;

import net.minecraft.server.MinecraftServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VanillaPulse implements ModInitializer {
	public static final String MOD_ID = "vanillapulse";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	public static PlayerStatisticsRepository playerStatisticsRepository;
	public static PlayerRepository playerRepository;
	public static StatisticService statisticService;
	public static DatabaseManager database;
	public static MinecraftServer server;
	public static DistanceService distanceService;
	public static PlayTimeService playTimeService;

	@Override
	public void onInitialize() {
		database = new DatabaseManager();
		database.connect();

		LOGGER.info("Database connection: " + database.getConnection());

		playerStatisticsRepository = new PlayerStatisticsRepository(database.getConnection());
		playerRepository = new PlayerRepository(database.getConnection());
		statisticService = new StatisticService();
		distanceService = new DistanceService();
		playTimeService = new PlayTimeService();

		ServerTickEvents.END_SERVER_TICK.register(SyncManager::tick);
		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			VanillaPulse.server = server;
			SyncManager.scheduleSync(server);
		});

		ServerLifecycleEvents.SERVER_STARTED.register(server -> {
			VanillaPulse.server = server;
		});

		BlockBreakListener.register();
		EntityDeathListener.register();
		PlayerJoin.register();
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
