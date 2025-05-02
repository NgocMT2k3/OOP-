import java.sql.*;
import java.text.ParseException;
import util.DBConnection;
import model.*;
import updb.*;
import java.util.*;
import java.sql.Date;


public class Main 
{
    private static Scanner sc = new Scanner(System.in);
    private static StringBuilder saveUserName = new StringBuilder(); 
    
    public static void LoginMenu() throws SQLException
    {
        System.out.println("\n ==== LIBRARY MANAGEMNET SYSTEM ====");
        System.out.println("1. Signin");
        System.out.println("2. Login");
        System.out.println("3. Admin");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
        
        int choice = sc.nextInt(); sc.nextLine();
        switch (choice)
        {
            case 1:
                Signin();
                break;
            case 2:
                Login();
                break;
            case 3:
                AdminLogin();
                break;
            case 4:
                System.out.println("Goodbye! See you later. :3");
               break;
        }
    }
    
    public static void AdminMenu() throws SQLException
    {
        System.out.println("\n ==== LIBRARY MANAGEMNET SYSTEM ====");
        System.out.println("==== ADMIN SYSTEM ====");
        System.out.println("1. Admins Management");
        System.out.println("2. Users Managemant");
        System.out.println("3. Books Management"); 
        System.out.println("4. add Books"); 
        System.out.println("5. Category Management"); 
        System.out.println("6. Promotion Management");
        System.out.println("7. RevenueStatistics");
        System.out.println("8. Exit");
        System.out.print("Choose an option: ");
        
        int choice = sc.nextInt(); sc.nextLine();
        switch (choice)
        {
            case 1:
                adminsManagement();
                break;
            case 2:
                usersManagement();
                break;
            case 3:
                booksManagement();
                break;
            case 4: 
                addBooks();
                break;
            case 5:
                categoryManagement();
                break;
            case 6:
                promotionManagement();
               break;
            case 7:
                revenueStatistics();
                break;
            case 8:
               LoginMenu();
        }
    }
    
    public static void UserMenu() throws SQLException
    {
        System.out.println("\n==== LIBRARY MANAGEMENT SYSTEM ====");
        System.out.println("1. Borrow Books");
        System.out.println("2. Renew Books");
        System.out.println("3. History");
        System.out.println("4. Information");//mk, email, phone
        System.out.println("5. Logout");
        System.out.print("Choose an option: ");
        
        int choice = sc.nextInt(); sc.nextLine();
        
        switch (choice)
        {
            case 1:
                requestBooks();
                break;
            case 2:
                System.out.print("Enter BookID your want to Confirm Renew: ");
                String input = sc.nextLine().trim();
                if(input.isEmpty()) break;
                int bookid = Integer.parseInt(input);
                if(BooksDB.checkRenewDate(bookid) == true)
                {
                    System.out.println("You have renewed this book once. Please go to the library to renew it again.");
                    UserMenu();
                }
                Date renew_date = new Date(System.currentTimeMillis());
                BooksDB.renewBooks( bookid, renew_date);
                UserMenu();
                break;
            case 3:
                history();
                break;
            case 4:
                information();
                break;
            case 5:
                LoginMenu();
                saveUserName.setLength(0);
                break;
        }
    }
    public static void main(String[] args) throws SQLException
    {
        LoginMenu();
    }
    
    public static void Signin() throws SQLException
    {
        System.out.print("\nEnter Username: ");
        String username = sc.next(); sc.nextLine();
        if(LoginDB.checkUserName(username) == true)
        {
            System.out.print("Username Invalid! Please Enter another Username.");
            Signin();
        }
        else 
        {
            System.out.print("Enter Password: ");
            String password = sc.next(); sc.nextLine();
            System.out.print("Enter Email: ");
            String email = sc.next(); sc.nextLine();
            System.out.print("Enter Phonenumber: ");
            String phonenumber = sc.next(); sc.nextLine();
            Login account = new Login(username, password, email, phonenumber);
            LoginDB.addAccount(account);
            System.out.println("Signined Successfully!");
            LoginMenu();
        }
    }
    
    public static void Login() throws SQLException
    {
        System.out.print("Enter Username: ");
        String username = sc.next(); sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.next(); sc.nextLine();
        
        Login account = new Login(username, password, "", "");
        if(LoginDB.checkLog(account) == false)
        {
            System.out.println("Username or Password Incorrect! Please try again.");
            Login();
        }
        else 
        {
            System.out.println("Login Sccessfully!");
            saveUserName.append(username);
            UserMenu();
        }
    }
    
    public static void AdminLogin() throws SQLException
    {
        System.out.print("Enter Adminname: ");
        String adminname = sc.next(); sc.nextLine();
        System.out.print("Enter Password: ");
        String password = sc.next(); sc.nextLine();
        Admins admin = new Admins(adminname, password);
        if(AdminsDB.checkLog(admin) == false)
        {
            System.out.println("Adminname or Password Incorect? Please try again.");
            AdminLogin();
        }
        else AdminMenu();
    }
    
    public static void adminsManagement() throws SQLException
    {
        System.out.println("\n ==== LIBRARY MANAGEMNET SYSTEM ====");
        System.out.println("==== ADMIN SYSTEM ====");
        System.out.println("1. Add Admins");
        System.out.println("2. Show Admins");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
        
        int choice = sc.nextInt(); sc.nextLine();
        
        switch (choice)
        {
            case 1:
                int ck = 0;
                while(ck==0)
                {
                    System.out.print("Enter Adminname: ");
                    String adminname = sc.next(); sc.nextLine();
                    if(AdminsDB.checkAdd(adminname) == true)
                    {
                        System.out.println("Adminname Invalid! Please Enter another Adminname.");
                    }
                    else 
                    {
                        ck = 1;
                        System.out.print("Enter Password: ");
                        String password = sc.next(); sc.nextLine();
                        Admins admin = new Admins(adminname, password);
                        AdminsDB.addAmin(admin);
                        System.out.println("Added Admin Successfully!");
                        adminsManagement();
                    }
                }
                
                break;
            case 2: 
                AdminsDB.showAdmin();
                System.out.print("Enter Enything to Exit ");
                sc.nextLine();
                adminsManagement();
            case 3:
                AdminMenu();
        }
        
    }
    
    public static void usersManagement() throws SQLException
    {
        System.out.println("\n ==== LIBRARY MANAGEMNET SYSTEM ====");
        System.out.println("==== ADMIN SYSTEM ====");
        System.out.println("1. Show User");
        System.out.println("2. Show History User");
        System.out.println("3. Confirm Request");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
        
        int choice = sc.nextInt(); sc.nextLine();
        switch (choice)
        {
            case 1: 
                AdminsDB.showUser("");
                usersManagement();
            case 2:
                System.out.print("Enter Username your want to show History (Enter 'All' to show all): ");
                String choice2 = sc.next(); sc.nextLine();
                if(choice2.equals("All"))  
                {
                    HistoryDB.showHistory("");
                    System.out.print("Enter Anything to Exit ");
                    sc.nextLine();
                    usersManagement();
                }
                else 
                {
                    HistoryDB.showHistory(choice2);
                    System.out.print("Enter Anything to Exit ");
                    sc.nextLine();
                    usersManagement();
                }    
            case 3:
                confirmRequest();
                break;
            case 4:
                AdminMenu();
        }
    }
    public static void booksManagement() throws SQLException
    {
        System.out.println("\n ==== LIBRARY MANAGEMNET SYSTEM ====");
        System.out.println("==== ADMIN SYSTEM ====");
        System.out.println("1. Show Books");
        System.out.println("2. Update Books");
        System.out.println("3. Add Books");
        System.out.println("4. Exit");
        System.out.print("Choose an option: ");
        
        int choice = sc.nextInt(); sc.nextLine();
        
        switch (choice)
        {
            case 1:
                BooksDB.showBooks();
                booksManagement();
            case 2:
                System.out.print("Enter BookId: ");
                int bookid = sc.nextInt(); sc.nextLine();
                System.out.print("Enter Qunatity your want to add: ");
                int quantity = sc.nextInt(); sc.nextLine();
                BooksDB.updateBooks(bookid, quantity);
                System.out.println("Updated Book  Successfully!");
                booksManagement();
            case 3:
                addBooks();
                break;
            case 4:
                AdminMenu();
                break;
        }
    }
    
    public static void addBooks() throws SQLException
    {
        System.out.print("Enter BookId: ");
        int bookid = sc.nextInt(); sc.nextLine();
        if(BooksDB.checkBookId(bookid) == true)
        {
            System.out.println("The Book is in the Library! Please Enter anthor BookId ");
            addBooks();
        }
        else
        {
            System.out.print("Enter Title: ");
            String title = sc.nextLine();
            System.out.print("Enter PublishYear: ");
            int publishyear = sc.nextInt(); sc.nextLine();
            System.out.print("Enter Author: ");
            String author = sc.nextLine();
            System.out.print("Enter Category: ");
            String category = sc.nextLine(); 
            if (!CategoryDB.checkCategoryName(category)) 
            {
            System.out.println(" Category does not exist! Please add the category first");
            addBooks(); 
            return;
            }
            System.out.print("Enter Quantity: ");
            int quantity = sc.nextInt(); sc.nextLine();
            System.out.print("Enter Price: ");
            int price = sc.nextInt(); sc.nextLine();
            Books book = new Books(bookid, title, publishyear, author, category, quantity, price);
            BooksDB.addBooks(book);
            System.out.println("Added Book Access!");
            AdminMenu();
        }                 
    }
    
    public static void requestBooks() throws SQLException
    {
        System.out.print("Enter BookId: ");
        int bookid = sc.nextInt(); sc.nextLine();
        if(BooksDB.checkBookId(bookid) !=  true)
        {
            System.out.println("This book is not in the library yet! Please Enter another BookId.");
            requestBooks();
        }
        else if(BooksDB.checkQuantity(bookid) == false)
        {
            System.out.println("This book is out of stock! Please Enter another BookId.");
            requestBooks();
        }
        else 
        {
            BooksDB.updateBooks(bookid, -1);
            Date request_date = new Date(System.currentTimeMillis());
            BooksDB.requestBooks(saveUserName.toString(), bookid, request_date);
            System.out.println("Book Borrow Request Successfully Created!");
            System.out.println("Choose option pay: ");
            UserMenu();
        }
    }
        
    public static void borrowBooks() throws SQLException
    {
        System.out.print("Enter Uername your want to Confirm: ");
        String username = sc.next(); sc.nextLine(); 
        if(LoginDB.checkUserName(username) == false ) 
        {
            System.out.println("Username Invalid! Please Enter another Username.");
            borrowBooks();
        }
        HistoryDB.showHistory(username); 
        while(true)
        {   
            System.out.print("Enter BookID your want to Confirm Borrow: ");
            String input = sc.nextLine().trim();
            if(input.isEmpty()) break;
            int bookid = Integer.parseInt(input);
            Date borrow_date = new Date(System.currentTimeMillis());
            BooksDB.borrowBooks( bookid, borrow_date);
            HistoryDB.showHistory(username); 
        }
    }
    public static void returnBooks() throws SQLException
    {
        System.out.print("Enter Uername your want to Confirm: ");
        String username = sc.next(); sc.nextLine(); 
        if(LoginDB.checkUserName(username) == false ) 
        {
            System.out.println("Username Invalid! Please Enter another Username.");
            returnBooks();
        }
        HistoryDB.showHistory(username); 
        while(true)
        {                    
            System.out.print("Enter BookID your want to Confirm Return: ");
            String input = sc.nextLine().trim();
            if(input.isEmpty()) break;
            int bookid = Integer.parseInt(input);
            Date return_date = new Date(System.currentTimeMillis());
            BooksDB.returnBooks( bookid, return_date);
            HistoryDB.showHistory(username); 
        }
    }
    
    public static void renewBooks() throws SQLException
    {
        System.out.print("Enter Uername your want to Confirm: ");
        String username = sc.next(); sc.nextLine(); 
        if(LoginDB.checkUserName(username) == false ) 
        {
            System.out.println("Username Invalid! Please Enter another Username.");
            renewBooks();
        }
        HistoryDB.showHistory(username); 
        while(true)
        {                    
            System.out.print("Enter BookID your want to Confirm Renew: ");
            String input = sc.nextLine().trim();
            if(input.isEmpty()) break;
            int bookid = Integer.parseInt(input);
            Date renew_date = new Date(System.currentTimeMillis());
            BooksDB.renewBooks( bookid, renew_date);
            HistoryDB.showHistory(username); 
        }
    }
    
    public static void history() throws SQLException
    {
        HistoryDB.showHistory(saveUserName.toString());
        UserMenu();
    }
    
    public static void information() throws SQLException
    {
        AdminsDB.showUser(saveUserName.toString());
        System.out.println("1. Change Information");
        System.out.println("2. Exit");
        System.out.print("Choose an option: ");
        
        int choice = sc.nextInt(); sc.nextLine();
        
        switch (choice)
        {
            case 1:
                System.out.print("Enter New Password (Enter 0 to Skip this Step): ");
                String password = sc.next(); sc.nextLine();
                System.out.print("Enter New Email (Enter 0 to Skip this Step): ");
                String email = sc.next(); sc.nextLine();
                System.out.print("Enter New Phonenumber (Enter 0 to Skip this Step): ");
                String phonenumber = sc.next(); sc.nextLine();
                
                Login account = new Login(saveUserName.toString(), password, email, phonenumber);
                LoginDB.chanceInformation(account);
                System.out.println("Changed Successfully!");
            case 2:
                UserMenu();
        }
    }
    
    public static void confirmRequest() throws SQLException
    {
        System.out.println("1. Borrow Book");
        System.out.println("2. Return Book");
        System.out.println("3. Renew Book");
        System.out.println("4. Exit");
            
        System.out.print("Choose an option: ");
        int choice = sc.nextInt(); sc.nextLine();
        if(choice == 4) usersManagement();

        switch (choice)
        {
            case 1:
                borrowBooks();
                confirmRequest();
                break;
            case 2:
                returnBooks();
                confirmRequest();
                break;
            case 3:
                renewBooks();
                confirmRequest();
                break;
        }
    }
    public static void categoryManagement() throws SQLException 
    {
    while (true) {
        System.out.println("\n==== CATEGORY MANAGEMENT ====");
        System.out.println("1. Show Categories");
        System.out.println("2. Add Category");
        System.out.println("3. Update Category");
        System.out.println("4. Delete Category");
        System.out.println("5. Back to Admin Menu");
        System.out.print("Choose an option: ");
        int choice = sc.nextInt(); sc.nextLine();

        switch (choice) {
            case 1:
                List<Category> list = CategoryDB.getAllCategories();
                System.out.printf("%-15s | %-20s\n", "Category ID", "Category Name");
                for (Category c : list) {
                    System.out.printf("%-15d | %-20s\n", c.getCategoryId(), c.getCategoryName());
                }
                break;

            case 2:
                System.out.print("Enter new Category ID: ");
                int id = sc.nextInt(); sc.nextLine();
                System.out.print("Enter new Category Name: ");
                String name = sc.nextLine();
                CategoryDB.addCategory(new Category(id, name));
                System.out.println("Category added successfully!");
                break;

            case 3:
                System.out.print("Enter Category ID to update: ");
                int updateId = sc.nextInt(); sc.nextLine();
                System.out.print("Enter new Category Name: ");
                String newName = sc.nextLine();
                CategoryDB.updateCategory(new Category(updateId, newName));
                System.out.println("Category updated successfully!");
                break;

            case 4:
                System.out.print("Enter Category ID to delete: ");
                int deleteId = sc.nextInt(); sc.nextLine();
                CategoryDB.deleteCategory(deleteId);
                System.out.println("Category deleted successfully!");
                break;
                
            case 5:
                AdminMenu();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static void promotionManagement() throws SQLException {
        while (true) {
            System.out.println("\n==== PROMOTION MANAGEMENT ====");
            System.out.println("1. Show Promotions");
            System.out.println("2. Add Promotion");
            System.out.println("3. Update Promotion");
            System.out.println("4. Delete Promotion");
            System.out.println("5. Back to Admin Menu");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1:
                    PromotionDB.showPromotions();
                    break;

                case 2:
                    System.out.print("Enter Book ID: ");
                    int bookId = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Discount Percent: ");
                    int discount = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter Start Date (yyyy-mm-dd): ");
                    Date start = Date.valueOf(sc.nextLine());
                    System.out.print("Enter End Date (yyyy-mm-dd): ");
                    Date end = Date.valueOf(sc.nextLine());

                    if (end.before(start)) {
                        System.out.println("End date must be after start date!");
                        break;
                    }

                    Promotion newPromo = new Promotion(bookId, discount, start, end);
                    PromotionDB.addPromotion(newPromo);
                    break;

                case 3:
                    System.out.print("Enter Book ID to update promotion: ");
                    int updateBookId = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter new Discount Percent: ");
                    int newDiscount = sc.nextInt(); sc.nextLine();
                    System.out.print("Enter new Start Date (yyyy-mm-dd): ");
                    Date newStart = Date.valueOf(sc.nextLine());
                    System.out.print("Enter new End Date (yyyy-mm-dd): ");
                    Date newEnd = Date.valueOf(sc.nextLine());

                    if (newEnd.before(newStart)) {
                        System.out.println("End date must be after start date!");
                        break;
                    }
                    Promotion updatedPromo = new Promotion(updateBookId, newDiscount, newStart, newEnd);
                    PromotionDB.updatePromotion(updatedPromo);
                    break;
                case 4:
                    System.out.print("Enter Book ID to delete promotion: ");
                    int deleteBookId = sc.nextInt(); sc.nextLine();
                    PromotionDB.deletePromotion(deleteBookId);
                    System.out.println("Promotion deleted successfully!");
                    break;

                case 5:
                    AdminMenu();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    public static void revenueStatistics() throws SQLException {
    while (true) {
        System.out.println("\n==== REVENUE STATISTICS ====");
        System.out.println("1. Statistics by Day");
        System.out.println("2. Statistics by Month");
        System.out.println("3. Statistics by Year");
        System.out.println("4. Back to Admin Menu");
        System.out.print("Choose an option: ");
        int choice = sc.nextInt(); sc.nextLine();

        switch (choice) {
            case 1:
                String sqlDay = "SELECT transaction_date, SUM(amount) AS total " +
                                "FROM Transactions GROUP BY transaction_date ORDER BY transaction_date";
                try (Connection conn = DBConnection.getConnection();PreparedStatement ps = conn.prepareStatement(sqlDay);
                     ResultSet rs = ps.executeQuery()) {

                    System.out.printf("%-15s | %-15s\n", "Date", "Total Revenue");
                    while (rs.next()) {
                        System.out.printf("%-15s | %.2f\n", rs.getDate("transaction_date"), rs.getDouble("total"));
                    }
                }
                break;

            case 2:
                String sqlMonth = "SELECT YEAR(transaction_date) AS year, MONTH(transaction_date) AS month, SUM(amount) AS total " +
                                  "FROM Transactions GROUP BY year, month ORDER BY year, month";
                try (Connection conn = DBConnection.getConnection();PreparedStatement ps = conn.prepareStatement(sqlMonth);
                     ResultSet rs = ps.executeQuery()) {

                    System.out.printf("%-10s | %-10s | %-15s\n", "Year", "Month", "Total Revenue");
                    while (rs.next()) {
                        System.out.printf("%-10d | %-10d | %.2f\n",
                                rs.getInt("year"), rs.getInt("month"), rs.getDouble("total"));
                    }
                }
                break;

            case 3:
                String sqlYear = "SELECT YEAR(transaction_date) AS year, SUM(amount) AS total " +
                                 "FROM Transactions GROUP BY year ORDER BY year";
                try (Connection conn = DBConnection.getConnection();PreparedStatement ps = conn.prepareStatement(sqlYear);
                     ResultSet rs = ps.executeQuery()) {

                    System.out.printf("%-10s | %-15s\n", "Year", "Total Revenue");
                    while (rs.next()) {
                        System.out.printf("%-10d | %.2f\n", rs.getInt("year"), rs.getDouble("total"));
                    }
                }
                break;

            case 4:
                AdminMenu();
                return;

            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}

}
