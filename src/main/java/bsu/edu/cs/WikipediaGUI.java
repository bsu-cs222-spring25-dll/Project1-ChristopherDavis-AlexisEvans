package bsu.edu.cs;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

//Modified code that was built off of from Nolan Meyer and Dakota Coughlin
public class WikipediaGUI extends Application{
    private final WikipediaConnection wikipediaConnection = new WikipediaConnection();
    private final RevisionFormatter revisionFormatter = new RevisionFormatter();
    //private final List<RevisionParts> revisionList;

    @Override
    public void start(Stage stage) throws Exception {

        VBox parent = new VBox();
        parent.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: white;" + "-fx-border-color: gray; " + "-fx-border-width: 4px; ");

        createTitle(parent,80);

        createArticleLabel(parent,stage);

        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.setWidth(600);
        stage.setHeight(550);
        stage.show();
    }

    private void switchScene(Stage stage,String searchInput) throws FileNotFoundException {
        String output = "";
        VBox parent = new VBox();
        parent.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: white;");
        parent.setAlignment(Pos.CENTER);
        Alert errorPopUp = new Alert(Alert.AlertType.ERROR);
        errorPopUp.setTitle("Error!");
        errorPopUp.getDialogPane().setStyle("-fx-background-color: gray;");
        errorPopUp.getDialogPane().setStyle("-fx-font-size: 20px;");
        //Stage alertStage = (Stage) errorPopUp.getDialogPane().getScene().getWindow();



        //Basically the big meat and potatoes of where everything is running like the out put of the data
        if (searchInput.isEmpty()) {
            errorPopUp.setContentText("Please enter an article!");
            errorPopUp.showAndWait();
        } else {

            try {
                InputStream wikiResponse = wikipediaConnection.searchWikipediaUrl(searchInput);
                RevisionParser revisionParser = new RevisionParser(wikiResponse);
                RevisionParser parser = new RevisionParser(wikiResponse);
                output = revisionFormatter.printRevisionList(revisionParser.parseWikipediaResponse());

                Label redirectLabel = new Label(parser.retrieveDestination());
                redirectLabel.setPadding(new Insets(0, 0, 10, 0));
                parent.getChildren().add(redirectLabel);

            } catch (Exception e) {
                errorPopUp.setContentText(e.getMessage());
                errorPopUp.showAndWait();
            }
        }

        createOutputLabel(parent, output);

        Region spacer = new Region();
        VBox.setVgrow(spacer, javafx.scene.layout.Priority.ALWAYS);
        parent.getChildren().add(spacer);

        HBox backButtonContainer = new HBox();
        Button backButton = new Button("<-Back");
        backButton.setOnAction(_ -> {
            try {
                start(stage);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
        backButtonContainer.getChildren().add(backButton);
        backButtonContainer.setAlignment(Pos.BOTTOM_LEFT);
        parent.getChildren().add(backButtonContainer);

        if(!output.isEmpty()) {
            stage.setScene(new Scene(parent));
        }
    }


    //The methods creating the actual buttons can be greatly modified for our liking
    public void createTitle(VBox parent,int padding) throws FileNotFoundException {
        HBox titleContainer = new HBox();
        Label title = new Label("Wikipedia Revisions");
        title.setFont(new Font("Arial",35));
        titleContainer.getChildren().add(title);
        titleContainer.setAlignment(Pos.CENTER);
        titleContainer.setPadding(new Insets(0,0,padding,0));
        parent.getChildren().add(titleContainer);
    }
    public void createOutputLabel(VBox parent, String output){
        Label outputLabel = new Label(output);
        outputLabel.setMaxWidth(600);
        outputLabel.setMinWidth(400);
        outputLabel.setWrapText(true);
        outputLabel.setPadding(new Insets(0, 0, 10, 20));
        outputLabel.setAlignment(Pos.CENTER_LEFT);
        ScrollPane scrollPane = new ScrollPane(outputLabel);
        scrollPane.setFitToWidth(true);
        scrollPane.setMaxHeight(380);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setStyle("-fx-background-color: gray; " + "-fx-border-color: #3498db; " + "-fx-border-width: 4px; " + "-fx-padding: 10px;");
        parent.getChildren().add(scrollPane);
        parent.getChildren().add(outputLabel);
    }
    private Button createGetRevisionsButton(Stage stage, TextField textField) {
        Button getRevisionsButton = new Button("Get revisions!");
        getRevisionsButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 8px 16px;");

        getRevisionsButton.setOnMouseEntered(_ -> getRevisionsButton.setStyle("-fx-background-color: #2980b9; -fx-text-fill: white;"));

        getRevisionsButton.setOnMouseExited(_ -> getRevisionsButton.setStyle("-fx-background-color: #3498db; -fx-text-fill: white;"));

        getRevisionsButton.setOnAction(_ -> {
            try {
                switchScene(stage, textField.getText());
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        return getRevisionsButton;
    }

    public void createArticleLabel(VBox parent, Stage stage) throws FileNotFoundException {
        Label articleLabel = new Label("Enter Article Title: ");
        HBox articleTitleInput = new HBox(articleLabel);
        TextField textField = new TextField();
        articleTitleInput.getChildren().add(textField);
        articleTitleInput.setAlignment(Pos.CENTER);
        articleTitleInput.setPadding(new Insets(0,0,20,0));
        parent.getChildren().add(articleTitleInput);
        stage.setTitle("Wikipedia Revisions Search");
        VBox buttonContainer = new VBox();
        Button getRevisionsButton = createGetRevisionsButton(stage, textField);
        buttonContainer.getChildren().add(getRevisionsButton);
        buttonContainer.setAlignment(Pos.CENTER);
        parent.getChildren().add(buttonContainer);
    }
}