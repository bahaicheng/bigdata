package org.apache.ignitetest

import java.sql.{Connection, DriverManager, Statement}

object IgniteJdbc {
  def main(args: Array[String]): Unit = {
    val driver = "org.apache.ignite.IgniteJdbcThinDriver"
    val url = "jdbc:ignite:thin://192.168.121.66/"

    val conn: Connection = DriverManager.getConnection(url)

    try {
      val stmt: Statement = conn.createStatement()
      stmt.executeUpdate("CREATE TABLE City (" +
        " id LONG PRIMARY KEY, name VARCHAR) " +
        " WITH \"template=replicated\"");

      stmt.executeUpdate("CREATE TABLE Person (" +
        " id LONG, name VARCHAR, city_id LONG, " +
        " PRIMARY KEY (id, city_id)) " +
        " WITH \"backups=1, affinityKey=city_id\"");

      stmt.executeUpdate("CREATE INDEX idx_city_name ON City (name)");
      stmt.executeUpdate("CREATE INDEX idx_person_name ON Person (name)")
    } catch {
      case ex: Exception => println("")
    } finally {


    }
  }
}
