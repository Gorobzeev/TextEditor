package sample.Controller;



import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.control.TextArea;
import javafx.fxml.FXML;
import javafx.event.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class Controller implements Serializable {

    File file;
    File saveFile;
    int fromIndex=0;
    @FXML
    public TextArea mainTextArea;

    @FXML
    private TextField searchTextField;

    @FXML
    public void initialize(URL location, ResourceBundle resources) {

}

    public void handleOpenAction(ActionEvent actionEvent){
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open text file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt", "*.txt"));
        file = fileChooser.showOpenDialog(stage);

        try {
            BufferedReader bf = new BufferedReader(new FileReader(file));
            String s;
            while ((s = bf.readLine()) != null) {
                mainTextArea.setText(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleSaveAction(ActionEvent actionEvent){

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.append(mainTextArea.getText());
            bw.flush();
            bw.close();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleSaveAsAction(ActionEvent actionEvent){
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save text file");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("txt", "*.txt"));
        saveFile = fileChooser.showSaveDialog(stage);


        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(saveFile));
            bw.append(mainTextArea.getText());
            bw.flush();
            bw.close();

        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleSearchText(ActionEvent actionEvent){
        String textToFind = searchTextField.getText();
        String whereToFind = mainTextArea.getText();
        int currentStringPlace = whereToFind.indexOf(textToFind, fromIndex);
        if (currentStringPlace != -1) {
            mainTextArea.selectRange(textToFind.length() + currentStringPlace,
                    currentStringPlace);
            fromIndex = textToFind.length() + currentStringPlace;
        } else {
            searchTextField.setText("");
            fromIndex = 0;

        }
    }
}
