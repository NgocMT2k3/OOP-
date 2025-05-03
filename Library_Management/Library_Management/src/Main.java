import java.sql.*;
import util.DBConnection;
import model.*;
import updb.*;
import java.util.*;
import java.util.Date;

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
        System.out.println("4. Category Management");
        System.out.println("5. Exit");
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
                categoryManagement();
                break;
            case 5:
               LoginMenu();
        }
    }
    
    public static void UserMenu() throws SQLException
    {
        System.out.println("\n==== LIBRARY MANAGEMENT SYSTEM ====");
        System.out.println("1. Borrow Books");
        System.out.println("2. Renew Books");
        System.out.println("3. History");
        System.out.println("4. Book Reviews");
        System.out.println("5. Information");
        System.out.println("6. Logout");
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
                Date renew_date = new Date();
                BooksDB.renewBooks( bookid, renew_date);
                UserMenu();
                break;
            case 3:
                history();
                break;
            case 4:
                System.out.println("\n--- Book Reviews ---");
                System.out.println("1. Add Review");
                System.out.println("2. View Reviews");
                System.out.println("3. Edit Review");
                System.out.println("4. Delete Review");
                System.out.println("5. Back");
                System.out.print("Choose: ");
                int rv = sc.nextInt(); sc.nextLine();

                switch (rv) {
                    case 1:
                        addReview();
                        break;
                        
                    case 2:                   
                        BookReviewDB.getAllReviews();
                        break;

                    case 3:
                        editReview();
                        break;

                    case 4:
                        deleteReview();
                        break;

                    case 5:
                        UserMenu();
                        break;

                    default:
                        System.out.println("Invalid option. Please try again.");
                        break;
                    }
                UserMenu();
                break;
            case 5:
                information();
                break;
            case 6:
                saveUserName.setLength(0);
                LoginMenu();
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
        System.out.println("4. ManagePromotion");
        System.out.println("5. Exit");
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
                ManagePromotion();
                break;
            case 5:
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
    
    public static void ManagePromotion() throws SQLException 
    {
        System.out.println("\n==== PROMOTION MANAGEMENT ====");
        System.out.println("1. Show Promotions");
        System.out.println("2. Add Promotion");
        System.out.println("3. Update Promotion");
        System.out.println("4. Delete Promotion");
        System.out.println("5. Back");
        System.out.print("Choose: ");

        int choice = sc.nextInt(); sc.nextLine();
        switch (choice) 
        {
            case 1:
                PromotionDB.getAllPromotions();
                break;

            case 2:
                System.out.print("Enter Book ID: ");
                int bookId = sc.nextInt(); sc.nextLine();
                System.out.print("Enter Discount Percent (e.g., 20): ");
                int discount = sc.nextInt(); sc.nextLine();
                Promotion newPromo = new Promotion(bookId, discount);
                PromotionDB.addPromotion(newPromo);
                System.out.println("✅ Promotion added.");
                break;

            case 3:
                System.out.print("Enter Book ID to update promotion: ");
                int updateId = sc.nextInt(); sc.nextLine();
                System.out.print("Enter New Discount Percent: ");
                int newDiscount = sc.nextInt(); sc.nextLine();
                Promotion updatePromo = new Promotion(updateId, newDiscount);
                PromotionDB.updatePromotion(updatePromo);
                System.out.println("✅ Promotion updated.");
                break;

            case 4:
                System.out.print("Enter Book ID to delete promotion: ");
                int delId = sc.nextInt(); sc.nextLine();
                PromotionDB.deletePromotion(delId);
                System.out.println("✅ Promotion deleted.");
                break;

            case 5:
                AdminMenu();
                return;

            default:
                System.out.println("❌ Invalid option.");
        }

        ManagePromotion();
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
            Date request_date = new Date();
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
            Date borrow_date = new Date();
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
            Date return_date = new Date();
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
            Date renew_date = new Date();
            BooksDB.renewBooks( bookid, renew_date);
            HistoryDB.showHistory(username); 
        }
    }
    
    public static void history() throws SQLException
    {
        HistoryDB.showHistory(saveUserName.toString());
        UserMenu();
    }
    
    public static void addReview() throws SQLException
    {
        System.out.print("BookID: ");
        int bookid = sc.nextInt(); sc.nextLine();
        if(BooksDB.checkBookId(bookid) == true)
        {
            System.out.print("Rating (1-5): ");
            int rating = sc.nextInt(); sc.nextLine();
            System.out.print("Comment: ");
            String comment = sc.nextLine();
            BookReview newReview = new BookReview(bookid, saveUserName.toString(), rating, comment);
            BookReviewDB.addReview(newReview);
            System.out.println("✅ Review added.");
            UserMenu();
        }
        else 
        {
            System.out.println("This Book is Invalid! Please Enter another BookID.");
            addReview();
        }
    }
    
    public static void editReview() throws SQLException
    {
        System.out.print("Enter review BookID to edit: ");
        int bookid = sc.nextInt(); sc.nextLine();
        if(BookReviewDB.checkBookId(bookid) == true)
        {
            System.out.print("New Rating (1-5): ");
            int newRating = sc.nextInt(); sc.nextLine();
            System.out.print("New Comment: ");
            String newComment = sc.nextLine();
            BookReviewDB.updateReview(bookid, newRating, newComment);
            System.out.println("✅ Review updated.");
            UserMenu();
        }
        else 
        {
           System.out.println("This Book is Invalid! Please Enter another BookID.");
           editReview(); 
        }
        
    }
    
    public static void deleteReview() throws SQLException
    {
        System.out.print("Enter review BookID to delete: ");
        int bookid = sc.nextInt(); sc.nextLine();
        if(BookReviewDB.checkBookId(bookid) == true)
        {
            BookReviewDB.deleteReview(bookid);
            UserMenu();
        }
        else 
        {
           System.out.println("This Book is Invalid! Please Enter another BookID.");
           deleteReview(); 
        }
        System.out.println("✅ Review deleted.");
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
        System.out.println("\n==== CATEGORY MANAGEMENT ====");
        System.out.println("1. Show Categories");
        System.out.println("2. Add Category");
        System.out.println("3. Delete Category");
        System.out.println("4. Back to Admin Menu");
        System.out.print("Choose an option: ");
        int choice = sc.nextInt(); sc.nextLine();

        switch (choice) 
        {
            case 1:
                List<Category> list = CategoryDB.getAllCategories();
                System.out.printf("%-15s | %-20s\n", "Category ID", "Category Name");
                for (Category c : list) {
                    System.out.printf("%-15d | %-20s\n", c.getCategoryId(), c.getCategoryName());
                }
                showBookofCategory();
                break;

            case 2:
                addCategory();
                break;

            case 3:
                deleteCategory();
                break;
                
            case 4:
                AdminMenu();
                break;
                
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
     
    public static void showBookofCategory() throws SQLException
    {
        System.out.println("1. Show Books");
        System.out.println("2. Exit");
        System.out.print("Choose an option: ");
        
        int choice = sc.nextInt(); sc.nextLine();
        switch (choice)
        {
            case 1:
                System.out.print("Enter Category : ");
                String category = sc.nextLine();
                if(CategoryDB.checkCategoryName(category) == true)
                {
                    CategoryDB.showBookofCategory(category);
                }
                else 
                {
                    System.out.println("Category Invalid! Please Enter another CategoryID.");
                    showBookofCategory();   
                }
            case 2:
                categoryManagement();
                break;
        }
    }
    
    public static void addCategory() throws SQLException
    {
        System.out.print("Enter new Category ID: ");
        int id = sc.nextInt(); sc.nextLine();
        if(CategoryDB.checkCategoryID(id) == false)
        {
            System.out.print("Enter new Category Name: ");
            String name = sc.nextLine();
            CategoryDB.addCategory(new Category(id, name));
            System.out.println("Category added successfully!");
            categoryManagement();
        }
        else 
        {
            System.out.println("Category ID has existed! Please Enter anthor Category ID");
            addCategory();
        }
    }
    
    
    public static void deleteCategory() throws SQLException
    {
        System.out.print("Enter Category ID to delete: ");
        int cid = sc.nextInt(); sc.nextLine();
        if(CategoryDB.checkCategoryID(cid) == true)
        {
            CategoryDB.deleteCategory(cid);
            System.out.println("Category deleted successfully!");
            categoryManagement();
        }
        else
        {
            System.out.println("Category ID Invalid! Please Enter anthor Category ID");
            deleteCategory();
        }
        
    }
}
