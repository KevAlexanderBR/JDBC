import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import db.DB;

public class AppUpdate {
  public static void main(String[] args) throws InterruptedException, IOException {

    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    Connection conn = null;
    PreparedStatement st = null;
    try {
      conn = DB.getConnection();

      st = conn.prepareStatement(
          "UPDATE seller "
              + "SET BaseSalary = BaseSalary + ? "
              + "WHERE "
              + "(DepartmentId = ?)");
              
      st.setDouble(1, 200.0);
      st.setInt(2, 2);

      int rowsAffected = st.executeUpdate();

      if (rowsAffected > 0) {
        System.out.println("Done! Rows affected: " + rowsAffected);     
      } else {
        System.out.println("No rown affected!");
      }

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      DB.closeStatement(st);
      DB.closeConnection();
    }
  }
}
