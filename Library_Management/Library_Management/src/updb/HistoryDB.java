package updb;

import util.DBConnection;
import model.*;
import java.util.*;
import java.util.Date;
import java.sql.*;

public class HistoryDB 
{
    public static void showHistory(String username) throws SQLException
    {
        String sql = "SELECT * FROM history " +  (!username.isEmpty() ? " WHERE username = ?" : "");
        List<History> historylist = new ArrayList<>();
        try(Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql))
        {
            if(!username.isEmpty()) stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next())
            {
                History history = new History();
                history.setUsername(rs.getString("username"));
                history.setBookId(rs.getInt("bookid"));
                history.setRequestDate(rs.getTimestamp("request_date"));
                history.setBorrowDate(rs.getTimestamp("borrow_date"));
                history.setReturnDate(rs.getTimestamp("return_date"));
                history.setStatus(rs.getString("status"));
                historylist.add(history);
            }
            System.out.printf("%-20s | %-6s | %-25s |%-25s | %-25s | %-10s\n","UserName", "BookID", "Request_Date","Borrow_Date", "Return_Date", "Status" );
            for(History h : historylist)
            {
                System.out.printf("%-20s | %-6d | %-25s |%-25s | %-25s | %-10s\n ",h.getUsername(), h.getBookId(), h.getRequestDate(), h.getBorrowDate(), h.getReturnDate(), h.getStatus());
            }
        }
    }
}
