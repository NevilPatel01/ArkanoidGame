package ProjectArkanoid;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import java.util.*;


/**
 * @author Nevil Dineshkumar Patel_000892482
 */
public class ProjectArkanoid extends Application {

    private int Rows;
    private int Columns;
    private int currentScore;
    private int highScore;
    private int currentLevel;
    private double ballX;
    private double ballY;
    private double paddle;

    @Override
    public void start(Stage stage) throws Exception {
        primaryInput();
        Group root = new Group();
        Scene scene = new Scene(root);
        // Screen Size in Pixels
        Canvas canvas = new Canvas(800, 650);
        // Title of Screen
        stage.setTitle("Project Arkanoid");
        root.getChildren().add(canvas);
        stage.setScene(scene);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Drawing of game start from here.
        drawGame(gc);
        stage.show();
    }

    // User Input is taken from below.
    private void primaryInput() {
        Scanner scanner = new Scanner(System.in);

        // Input of Rows for Bricks
        System.out.print("Enter the number of Rows for bricks upto 15: ");
        Rows = allInput(scanner, 1, 15);

        // Input of Columns for Bricks
        System.out.print("Enter the number of Columns for bricks upto 15: ");
        Columns = allInput(scanner, 1, 15);

        // Input of Current Score
        System.out.print("Enter the current score: ");
        currentScore = allInput(scanner, 0, Integer.MAX_VALUE);

        // Input of High Score
        System.out.print("Enter the High Score: ");
        highScore = allInput(scanner, 0, Integer.MAX_VALUE);

        // If the Current Score is higher than High Score it will update the High Score
        if (currentScore > highScore) {
            highScore = currentScore;
        }

        // Input for Current Level(Wave)
        System.out.print("Enter the Current level: ");
        currentLevel = allInput(scanner, 1, Integer.MAX_VALUE);

        // Input for Ball X and Y position
        System.out.print("Enter the ball x and y position: ");
        ballX = doubleInput(scanner, 0, 800);
        ballY = doubleInput(scanner, 0, 600);

        // Input for Paddle X position
        System.out.print("Enter the paddle x position: ");
        paddle = doubleInput(scanner, 0, 800);

        scanner.close();
    }

    // Logic behind Current Score and High Score and inappropriate Integer Input
    private int allInput(Scanner scanner, int minimumRange, int maximumRange) {
        int input;
        while (true) {
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= minimumRange && input <= maximumRange) {
                    break;
                }
            }
            System.out.print("Invalid input!! Please enter a number between " + minimumRange + " and " + maximumRange + ": ");
            scanner.nextLine();
        }
        return input;
    }

    // logic Behind Minimum and Maximum Double Value of Input
    private double doubleInput(Scanner scanner, double minimumRange, double maximumRange) {
        double input;
        while (true) {
            if (scanner.hasNextDouble()) {
                input = scanner.nextDouble();
                if (input >= minimumRange && input <= maximumRange) {
                    break;
                }
            }
            System.out.print("Invalid input!! Please enter a number between " + minimumRange + " and " + maximumRange + ": ");
            scanner.nextLine();
        }
        return input;
    }

    private void drawGame(GraphicsContext gc) {
        double brickWidth = 60;
        double brickHeight = 20;
        double brickTop = 100;
        double padding = 10;
        double ballRadius = 10;
        double paddleWidth = 100;
        double paddleHeight = 15;

        // Clear the Screen
        gc.clearRect(0, 0, 800, 600);

        // Drawing of the bricks
        double brickX = padding;
        double brickY = brickTop;
        for (int row = 0; row < Rows; row++) {
            for (int col = 0; col < Columns; col++) {
                gc.fillRect(brickX, brickY, brickWidth, brickHeight);
                gc.strokeRect(brickX, brickY, brickWidth, brickHeight);
                brickX = brickX + brickWidth + padding;
            }
            brickX = padding;
            brickY = brickY + brickHeight + padding;
        }

        // Drawing of the ball
        gc.fillOval(ballX - ballRadius, ballY - ballRadius, ballRadius * 2, ballRadius * 2);

        // Drawing of the paddle
        gc.fillRect(paddle, 550, paddleWidth, paddleHeight);

        // Draw the separator between game and display regions
        gc.setLineWidth(2);
        gc.strokeLine(0, 90, 800, 90);

        // Draw the current score, high score, and current level
        gc.fillText("Current Score: " + currentScore, 10, 40);
        gc.fillText("High Score: " + highScore, 10, 60);
        gc.fillText("Current Level: " + currentLevel, 10, 80);
    }

    // Default code
    public static void main(String[] args) {
        launch(args);
    }
}
