package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

    @FXML
    private TextField textField;

    @FXML
    private Button btn;

    @FXML
    private Text text1;

    @FXML
    private Text text2;

    @FXML
    private Button setFileBtn;

    private Stage stage;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void initialize() {
        btn.setOnMouseClicked((event -> {
            MyStack<String> stack = new MyStack<>();
            String[] s = textField.getText().split(" ");
            for (String value : s) {
                stack.push(value);
            }
            StringBuilder stringBuilder1 = new StringBuilder();
            stringBuilder1.append("Orig stack ");
            StringBuilder stringBuilder2 = new StringBuilder();
            stringBuilder2.append("Reversed stack ");
            try {
                MyStack ans = stack.getReversedStack();
                appendToStringFromStack(stack, stringBuilder1);
                appendToStringFromStack(ans, stringBuilder2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            text1.setText(stringBuilder1.toString());
            text2.setText(stringBuilder2.toString());
        }));

        setFileBtn.setOnMouseClicked((event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Document");
            FileChooser.ExtensionFilter extFilter =
                    new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");//Расширение
            fileChooser.getExtensionFilters().add(extFilter);
            File file = fileChooser.showOpenDialog(stage);//Указываем текущую сцену

            if (file != null) {
                setTextField(file.getPath());
            }
        }));
    }

    private void setTextField(String inputPath) {
        StringBuilder stringBuilder = new StringBuilder();
        try (Scanner input = new Scanner(new File(inputPath))) {
            while (input.hasNextLine()) {
                String[] str = input.nextLine().split(" ");
                for (String s : str) {
                    stringBuilder.append(s);
                    stringBuilder.append(" ");
                }
            }
        } catch (IOException ex) {
            System.err.println("Invalid Path");
        }
        textField.setText(stringBuilder.toString().trim());
    }

    private void appendToStringFromStack(MyStack stack, StringBuilder string) throws Exception {
        while (!stack.isEmpty()) {
            string.append(stack.pop().toString());
            if (!stack.isEmpty()) {
                string.append(", ");
            }
        }
    }
}
