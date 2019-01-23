/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author user
 */
public class NotePad extends Application {

    //FileWriter fileWriter = null;
    //PrintWriter printWriter = null;
    //BufferedReader bufferedReader = null;
    MenuBar menuBar;
    Menu menu1;
    Menu menu2;
    Menu menu3;
    MenuItem newItem;
    MenuItem openItem;
    MenuItem saveItem;
    MenuItem exitItem;
    MenuItem undoItem;
    MenuItem copyItem;
    MenuItem cutItem;
    MenuItem pastItem;
    MenuItem deleteItem;
    MenuItem selectAllItem;
    MenuItem aboutItem;

    String selected;
    int position;

    @Override
    public void start(Stage primaryStage) {

        menu1 = new Menu("file");
        menu2 = new Menu("Edit");
        menu3 = new Menu("help");

        newItem = new MenuItem("New");
        saveItem = new MenuItem("Save");
        openItem = new MenuItem("Open");
        exitItem = new MenuItem("Exit");

        undoItem = new MenuItem("Undo");
        copyItem = new MenuItem("Copy");
        cutItem = new MenuItem("Cut");
        pastItem = new MenuItem("Paste");
        deleteItem = new MenuItem("Delete");
        selectAllItem = new MenuItem("SelectAll");

        saveItem.setAccelerator(KeyCombination.keyCombination("ctrl+n"));
        copyItem.setAccelerator(KeyCombination.keyCombination("ctrl+m"));
        cutItem.setAccelerator(KeyCombination.keyCombination("ctrl+r"));
        pastItem.setAccelerator(KeyCombination.keyCombination("ctrl+f"));
        undoItem.setAccelerator(KeyCombination.keyCombination("ctrl+o"));

        aboutItem = new MenuItem("About");

        menu1.getItems().addAll(newItem, saveItem, exitItem, openItem);
        menu2.getItems().addAll(undoItem, copyItem, cutItem, pastItem, deleteItem, selectAllItem);
        menu3.getItems().addAll(aboutItem);

        menuBar = new MenuBar(menu1, menu2, menu3);

        TextArea txtArea = new TextArea();

        newItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                txtArea.setText(" ");

            }

        });
        cutItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                txtArea.cut();

            }
        });

        copyItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                txtArea.copy();

            }
        });
        pastItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                txtArea.paste();

            }

        });
        undoItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                txtArea.undo();
            }
        });
        selectAllItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                txtArea.selectAll();
                // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        }
        );
        deleteItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                txtArea.setText(txtArea.getText().replace(txtArea.getSelectedText(), ""));
            }
        });
        exitItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        saveItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                FileChooser directoryChooser = new FileChooser();
                File file2 = directoryChooser.showOpenDialog(primaryStage);

                try {

                    FileOutputStream fop = null;
                    File file;
                    String content = txtArea.getText();
                    file = new File(file2.getAbsolutePath());
                    fop = new FileOutputStream(file);
                    byte[] contentInBytes = content.getBytes();
                    fop.write(contentInBytes);
                    fop.flush();
                    fop.close();
                } catch (IOException ex) {
                    Logger.getLogger(NotePad.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        });
        openItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                FileChooser directoryChooser = new FileChooser();
                File file2 = directoryChooser.showOpenDialog(primaryStage);

                try {

                    FileInputStream fis = new FileInputStream(file2.getAbsolutePath());
                    int size = fis.available();
                    byte[] b = new byte[size];
                    fis.read(b);
                    txtArea.setText(new String(b));
                    //System.out.println(new String(b));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(NotePad.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(NotePad.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        aboutItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setTitle("About");
                alert.setHeaderText(null);
                alert.setContentText("This Project by:Yasmin Qasem ");

                alert.showAndWait();

                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
        BorderPane brdrPane = new BorderPane(txtArea);
        brdrPane.setTop(menuBar);

        Scene scene = new Scene(brdrPane, 300, 250);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
