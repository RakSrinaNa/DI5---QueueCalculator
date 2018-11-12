package fr.mrcraftcod.queue;

import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.awt.Taskbar;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Optional;
import java.util.function.Consumer;

/**
 * Created by Thomas Couchoud (MrCraftCod - zerderr@gmail.com) on 2018-11-12.
 *
 * @author Thomas Couchoud
 * @since 2018-11-12
 */
public class Main extends Application{
	private final static NumberFormat RESULT_FORMAT = new DecimalFormat("##.#####");
	private Stage stage;
	
	public static void main(String[] args){
		Application.launch(args);
	}
	
	public void start(Stage stage) throws Exception{
		this.stage = stage;
		preInit();
		Scene scene = buildScene(stage);
		stage.setTitle(this.getFrameTitle());
		stage.setScene(scene);
		stage.sizeToScene();
		//stage.setResizable(false);
		if(getIcon() != null){
			setIcon(getIcon());
		}
		if(getStageHandler() != null){
			this.getStageHandler().accept(stage);
		}
		if(shouldDisplayAtStart()){
			stage.show();
			if(getOnStageDisplayed() != null){
				this.getOnStageDisplayed().accept(stage);
			}
		}
	}
	
	@SuppressWarnings("RedundantThrows")
	public void preInit() throws Exception{}
	
	public Scene buildScene(Stage stage){
		return new Scene(createContent(stage));
	}
	
	public String getFrameTitle(){
		return "Queues";
	}
	
	public Image getIcon(){
		return null;
	}
	
	private void setIcon(Image icon){
		this.stage.getIcons().clear();
		this.stage.getIcons().add(icon);
		Taskbar.getTaskbar().setIconImage(SwingFXUtils.fromFXImage(icon, null));
	}
	
	public Consumer<Stage> getStageHandler(){
		return stage -> {
			stage.getScene().getStylesheets().add(Main.class.getResource("/jfx/base.css").toExternalForm());
		};
	}
	
	public boolean shouldDisplayAtStart(){
		return true;
	}
	
	public Consumer<Stage> getOnStageDisplayed() throws Exception{
		return stage -> {};
	}
	
	@SuppressWarnings("Duplicates")
	public Parent createContent(Stage stage){
		var lineIndex = -1;
		final var s = new Text("Server count: ");
		final var sInput = new NumberField<>(1);
		sInput.getStylesheets().add(Main.class.getResource("/jfx/base.css").toExternalForm());
		sInput.setMaxWidth(Double.MAX_VALUE);
		GridPane.setConstraints(s, 0, ++lineIndex);
		GridPane.setConstraints(sInput, 1, lineIndex);
		GridPane.setFillWidth(sInput, true);
		GridPane.setHgrow(sInput, Priority.ALWAYS);
		
		final var queueLimit = new Text("Queue limit: ");
		final var queueLimitInput = new NumberField<Double>(null);
		queueLimitInput.setMaxWidth(Double.MAX_VALUE);
		GridPane.setConstraints(queueLimit, 0, ++lineIndex);
		GridPane.setConstraints(queueLimitInput, 1, lineIndex);
		GridPane.setFillWidth(queueLimitInput, true);
		GridPane.setHgrow(queueLimitInput, Priority.ALWAYS);
		
		final var lambda = new Text("Lambda: ");
		final var lambdaInput = new DecimalNumberField(0.9D);
		lambdaInput.setMaxWidth(Double.MAX_VALUE);
		GridPane.setConstraints(lambda, 0, ++lineIndex);
		GridPane.setConstraints(lambdaInput, 1, lineIndex);
		GridPane.setFillWidth(lambdaInput, true);
		GridPane.setHgrow(lambdaInput, Priority.ALWAYS);
		
		final var mu = new Text("Mu: ");
		final var muInput = new DecimalNumberField(1D);
		muInput.setMaxWidth(Double.MAX_VALUE);
		GridPane.setConstraints(mu, 0, ++lineIndex);
		GridPane.setConstraints(muInput, 1, lineIndex);
		GridPane.setFillWidth(muInput, true);
		GridPane.setHgrow(muInput, Priority.ALWAYS);
		
		final var validButton = new Button("Run");
		validButton.setMaxWidth(Double.MAX_VALUE);
		validButton.setDefaultButton(true);
		GridPane.setConstraints(validButton, 0, ++lineIndex, 2, 1);
		GridPane.setFillWidth(validButton, true);
		GridPane.setHgrow(validButton, Priority.ALWAYS);
		
		final var l = new Text("Average system size: ");
		final var lOutput = new Text();
		final var lProgress = new CircleProgress();
		GridPane.setConstraints(l, 0, ++lineIndex);
		GridPane.setConstraints(lOutput, 1, lineIndex);
		GridPane.setConstraints(lProgress, 0, ++lineIndex, 2, 1);
		GridPane.setFillWidth(lProgress, true);
		GridPane.setHgrow(lProgress, Priority.ALWAYS);
		
		final var lq = new Text("Average queue size: ");
		final var lqOutput = new Text();
		final var lqProgress = new CircleProgress();
		GridPane.setConstraints(lq, 0, ++lineIndex);
		GridPane.setConstraints(lqOutput, 1, lineIndex);
		GridPane.setConstraints(lqProgress, 0, ++lineIndex, 2, 1);
		GridPane.setFillWidth(lqProgress, true);
		GridPane.setHgrow(lqProgress, Priority.ALWAYS);
		
		final var w = new Text("Average wait time in system: ");
		final var wOutput = new Text();
		GridPane.setConstraints(w, 0, ++lineIndex);
		GridPane.setConstraints(wOutput, 1, lineIndex);
		
		final var wq = new Text("Average wait time in queue: ");
		final var wqOutput = new Text();
		GridPane.setConstraints(wq, 0, ++lineIndex);
		GridPane.setConstraints(wqOutput, 1, lineIndex);
		
		final var ref = new Text("Average refused: ");
		final var refOutput = new Text();
		final var refProgress = new CircleProgress();
		GridPane.setConstraints(ref, 0, ++lineIndex);
		GridPane.setConstraints(refOutput, 1, lineIndex);
		GridPane.setConstraints(refProgress, 0, ++lineIndex, 2, 1);
		GridPane.setFillWidth(refProgress, true);
		GridPane.setHgrow(refProgress, Priority.ALWAYS);
		
		validButton.setOnAction(e -> {
			sInput.getStyleClass().remove("invalidState");
			queueLimitInput.getStyleClass().remove("invalidState");
			lambdaInput.getStyleClass().remove("invalidState");
			muInput.getStyleClass().remove("invalidState");
			lOutput.setText("");
			lProgress.clear();
			lqOutput.setText("");
			lqProgress.clear();
			wOutput.setText("");
			wqOutput.setText("");
			refOutput.setText("");
			refProgress.clear();
			try{
				final var output = Computation.compute(new QueueInput(ProbabilityLaw.POISSON, ProbabilityLaw.EXP, sInput.getInt(), lambdaInput.getDouble(), muInput.getDouble(), queueLimitInput.getInt()));
				lOutput.setText(RESULT_FORMAT.format(output.getL()));
				lProgress.setCount(output.getL());
				lqOutput.setText(RESULT_FORMAT.format(output.getLq()));
				lqProgress.setCount(output.getLq());
				wOutput.setText(RESULT_FORMAT.format(output.getW()));
				wqOutput.setText(RESULT_FORMAT.format(output.getWq()));
				refOutput.setText(Optional.ofNullable(output.getRef()).map(ee -> {
					refProgress.setCount(ee);
					return RESULT_FORMAT.format(ee);
				}).orElse("Undefined"));
			}
			catch(BadInputException e2){
				System.err.println(e2.getMessage());
				for(var i : e2.getFields()){
					switch(i){
						case S:
							sInput.getStyleClass().add("invalidState");
							break;
						case MU:
							muInput.getStyleClass().add("invalidState");
							break;
						case LAMBDA:
							lambdaInput.getStyleClass().add("invalidState");
							break;
						case QUEUE_LIMIT:
							queueLimitInput.getStyleClass().add("invalidState");
							break;
						default:
					}
				}
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Computation error");
				alert.setHeaderText("Some fields are not completed correctly or lead to an invalid state");
				alert.setContentText(e2.getMessage());
				alert.showAndWait();
			}
			catch(Exception e2){
				e2.printStackTrace();
			}
		});
		
		GridPane inputs = new GridPane();
		inputs.getChildren().addAll(s, sInput, queueLimit, queueLimitInput, lambda, lambdaInput, mu, muInput, validButton, l, lOutput, lProgress, lq, lqOutput, lqProgress, w, wOutput, wq, wqOutput, ref, refOutput, refProgress);
		inputs.setMaxWidth(Double.MAX_VALUE);
		
		VBox root = new VBox(10);
		root.getChildren().add(inputs);
		
		VBox.setVgrow(inputs, Priority.ALWAYS);
		HBox.setHgrow(inputs, Priority.ALWAYS);
		return root;
	}
	
	public Stage getStage(){
		return stage;
	}
}
