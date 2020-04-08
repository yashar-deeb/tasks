package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Controller controller = new Controller();
        controller.setStage(primaryStage);

        primaryStage.setTitle("task 3");
        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }

    public static List<String> getReversedStack(Stack stack) throws Exception {
        Stack supStack = new Stack();
        List<String> ans = new ArrayList<>();

        while (!stack.isEmpty()) {
            supStack.push(stack.pop());
            ans.add(supStack.peek().toString());
        }
        while (!supStack.isEmpty()) {
            stack.push(supStack.pop());
        }

        return ans;
    }


    public static void main(String[] args) {
        launch(args);
    }
}
