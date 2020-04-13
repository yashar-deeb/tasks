package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class Controller {

    @FXML
    private TextField textField;

    @FXML
    private Button btn;

    @FXML
    private Text text;

    public void initialize() {
        btn.setOnMouseClicked((event -> {
            String[] s = textField.getText().split(" ");
            MyLinkedList<String> linkedList = new MyLinkedList<>();

            for (String item : s) {
                linkedList.addLast(item);
            }

            linkedList = linkedList.process();
            StringBuilder textStr = new StringBuilder();

            for (int i = 0; i < linkedList.size(); i++) {
                try {
                    textStr.append(linkedList.getValue(i));
                    if (i != linkedList.size() - 1)
                        textStr.append(", ");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            text.setText(textStr.toString());
        }));
    }
}
