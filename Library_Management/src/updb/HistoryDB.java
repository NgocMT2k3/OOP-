package updb;

import util.DBConnection;
import model.*;
import java.util.*;
import java.util.Date;
import java.sql.*;

public class HistoryDB 
{
    public static void showHistory() throws SQLException
    {
        List<StringBuilder> history = new ArrayList<>();
        String sql = "SELECT acoounts.name FROM history, books, accounts";
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            
        }
    }
}
