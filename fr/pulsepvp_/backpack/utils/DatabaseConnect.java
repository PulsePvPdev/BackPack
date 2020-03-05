package pulsepvp_.backpack.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import org.bukkit.Bukkit;

public class DatabaseConnect {
   private Connection connection;
   private String host;
   private String database;
   private String username;
   private String password;

   public DatabaseConnect(String host, String database, String username, String password) {
      this.host = host;
      this.database = database;
      this.username = username;
      this.password = password;
   }

   synchronized void connectDatabase() throws SQLException {
      if (this.connection == null || this.connection.isClosed()) {
         Bukkit.getLogger().log(Level.WARNING, "Database connected");
         this.connection = DriverManager.getConnection("jdbc:mysql://" + this.host + "/" + this.database, this.username, this.password);
      }
   }

   synchronized void disconnectDatabase() throws SQLException {
      this.connection.close();
      Bukkit.getLogger().log(Level.WARNING, "Database disconnected");
   }

   Connection connection() {
      return this.connection;
   }
}