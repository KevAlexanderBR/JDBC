import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;
import db.DbIntegrityException;

public class AppDelete {
  public static void main(String[] args) throws InterruptedException, IOException {

    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    Connection conn = null;
    PreparedStatement st = null;
    try {
      conn = DB.getConnection();

      st = conn.prepareStatement(
          "DELETE FROM department "
          + "WHERE "
          + "Id = ?");
              
      st.setInt(1, 2);

      int rowsAffected = st.executeUpdate();

      if (rowsAffected > 0) {
        System.out.println("Done! Rows affected: " + rowsAffected);     
      } else {
        System.out.println("No rown affected!");
      }

    } catch (SQLException e) {
      e.printStackTrace();
      throw new DbIntegrityException(e.getMessage());
    } finally {
      DB.closeStatement(st);
      DB.closeConnection();
    }
  }
}
