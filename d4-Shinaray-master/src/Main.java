import controllers.BookController;
import controllers.CartController;
import controllers.UserController;
import controllers.PaymentController;
import controllers.ReviewController;
import controllers.interfaces.IBookController;
import controllers.interfaces.ICartController;
import data.PostgresDB;
import data.interfaces.IDB;
import RoleManagement.Role;
import repositories.BookRepository;
import repositories.CartRepository;
import repositories.UserRepository;
import repositories.PaymentRepository;
import repositories.ReviewRepository;
import repositories.interfaces.IBookRepository;
import repositories.interfaces.ICartRepository;
import repositories.interfaces.IUserRepository;
import repositories.interfaces.IPaymentRepository;
import repositories.interfaces.IReviewRepository;
import start.*;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/bookstore";
        String user = "postgres";
        String password = "700235";
        IDB db = new PostgresDB(url, user, password);
        Role roll;
        boolean enabled;

        IUserRepository userRepo = new UserRepository(db);
        UserController userController = new UserController(userRepo);

        IBookRepository bookRepo = new BookRepository(db);
        IBookController bookController = new BookController(bookRepo);

        ICartRepository cartRepo = new CartRepository(db);
        ICartController cartController = new CartController(cartRepo);

        IPaymentRepository paymentRepo = new PaymentRepository(db);
        PaymentController paymentController = new PaymentController(paymentRepo);

        IReviewRepository reviewRepo = new ReviewRepository(db);
        ReviewController reviewController = new ReviewController(reviewRepo);
        ReviewApplication reviewApp = new ReviewApplication(reviewController);

        MyApplication app = new MyApplication(userController);
        BookApplication bookApp = new BookApplication(bookController);
        CartApplication cartApplication = new CartApplication(cartController);
        PaymentApplication paymentApp = new PaymentApplication(paymentController);

        Start starty = new Start(db);
        starty.start();
        roll = starty.role;
        enabled = starty.enter;

        app.start(roll);
        bookApp.start(roll);
        cartApplication.start(roll);
        paymentApp.start(roll);
        reviewApp.start(roll);

        db.close();
    }
}
