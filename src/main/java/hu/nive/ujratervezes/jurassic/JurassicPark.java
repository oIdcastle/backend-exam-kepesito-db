package hu.nive.ujratervezes.jurassic;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JurassicPark {

    private String dbUrl;
    private String dbUser;
    private String dbPassword;

    public JurassicPark(String dbUrl, String dbUser, String dbPassword) {
        this.dbUrl = dbUrl;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
    }

    List<String> checkOverpopulation() {
        List<String> dinoNames = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(this.dbUrl, this.dbUser, this.dbPassword)) {
            String sql = "SELECT breed FROM dinosaur WHERE actual > expected ORDER BY breed";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while(rs.next()) {
                dinoNames.add(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println("Database error: " + e.getMessage());
        }
        return dinoNames;
    }


}
