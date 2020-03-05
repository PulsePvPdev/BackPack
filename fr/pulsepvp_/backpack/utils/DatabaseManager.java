package pulsepvp_.backpack.utils;

import pulsepvp_.backpack.BackPack;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseManager {
   private BackPack BackPack;
   private DatabaseConnect databaseConnect;
   public Connection database;

   public DatabaseManager(BackPack BackPack) {
      this.BackPack = BackPack;
   }

   public void connect() {
      String host = this.BackPack.getConfig().getString("host");
      String databaseString = this.BackPack.getConfig().getString("database");
      String username = this.BackPack.getConfig().getString("username");
      String password = this.BackPack.getConfig().getString("password");
      this.databaseConnect = new DatabaseConnect(host, databaseString, username, password);

      try {
         this.databaseConnect.connectDatabase();
      } catch (SQLException var6) {
         var6.printStackTrace();
      }

      this.database = this.databaseConnect.connection();
   }

   public void disconnect() {
      try {
         this.databaseConnect.disconnectDatabase();
      } catch (SQLException var2) {
         var2.printStackTrace();
      }

   }

   public Connection getDatabase() {
      return this.database;
   }
}