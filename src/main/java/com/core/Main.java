package com.core;

import com.core.UI.Screens.StartScreenFiller;
import com.github.sarxos.webcam.Webcam;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * Created by siefker on 04.11.2015.
 */
public class Main extends Application {

    public static void main(String args[]) {

        launch(args);
        //Initiator.initiateProgram();
    }


    @Override
    public void start(Stage primaryStage) throws IOException, InterruptedException {
        primaryStage.setTitle("Test");
        BorderPane root = new BorderPane();
        //Pane root = new Pane();
        StartScreenFiller.fillPaneWithStartScreenComponents(root);
        primaryStage.setScene(new Scene(root));
        primaryStage.setFullScreen(true);
        primaryStage.show();


        long start = System.currentTimeMillis();
        int rows = 2;
        int cols = 2;
        int chunks = rows * cols;

        Webcam webcam = Webcam.getDefault();
        webcam.open();

        ImageIO.write(webcam.getImage(), "PNG", new File("C://Users//Anwender//Pictures//Saved Pictures//1.png"));
        File file1 = new File("C://Users//Anwender//Pictures//Saved Pictures//1.png");
        FileInputStream fis1 = new FileInputStream(file1);
        BufferedImage image1 = ImageIO.read(fis1);

        int width1 = image1.getWidth() / cols;
        int height1 = image1.getHeight() / rows;
        int count1 = 0;
        int count2 = 0;
        BufferedImage img1[] = new BufferedImage[chunks];

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                img1[count1] = new BufferedImage(width1, height1, image1.getType());
                Graphics2D gr1 = img1[count1++].createGraphics();
                gr1.drawImage(image1, 0, 0, width1, height1, width1 * y, height1 * x, width1 * y + width1, height1 * x + height1, null);
                gr1.dispose();
            }


        }
        System.out.println("Splitting1 done");
        for (int i = 0; i < img1.length; i++) {
            ImageIO.write(img1[i], "PNG", new File("C://Users//Anwender//Pictures//Saved Pictures//Klein1." + i + ".png"));
        }
        System.out.println("Mini images1 created");

        Thread.sleep(3000);
        ImageIO.write(webcam.getImage(), "PNG", new File("C://Users//Anwender//Pictures//Saved Pictures//2.png"));
        File file2 = new File("C://Users//Anwender//Pictures//Saved Pictures//2.png");
        FileInputStream fis2 = new FileInputStream(file2);
        BufferedImage image2 = ImageIO.read(fis2);



        int width2 = image2.getWidth() / cols;
        int height2 = image2.getHeight() / rows;
        BufferedImage img2[] = new BufferedImage[chunks];

        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                img2[count2] = new BufferedImage(width2, height2, image2.getType());
                Graphics2D gra2 = img2[count2++].createGraphics();
                gra2.drawImage(image2, 0, 0, width2, height2, width2 * y, height2 * x, width2 * y + width2, height2 * x + height2, null);
                gra2.dispose();
            }


        }
        System.out.println("Splitting2 done");
        for (int i = 0; i < img2.length; i++) {
            ImageIO.write(img2[i], "PNG", new File("C://Users//Anwender//Pictures//Saved Pictures//Klein2." + i + ".png"));
        }
        System.out.println("Mini images2 created");


        int p=0;
        int q=0;

        FileWriter fw = new FileWriter(file1.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        for(int a=0;a<width1;a++)
        {
            for(int b=0;b<height1;b++)
            {


                int clr1=  image1.getRGB(a,b);
                int  red1   = (clr1 & 0x00ff0000) >> 16;
                int  green1 = (clr1 & 0x0000ff00) >> 8;
                int  blue1  =  clr1 & 0x000000ff;

                int clr2=  image2.getRGB(a,b);
                int  red2   = (clr2 & 0x00ff0000) >> 16;
                int  green2 = (clr2 & 0x0000ff00) >> 8;
                int  blue2  =  clr2 & 0x000000ff;

                if(((red1 - red2) + (green1 - green2) + (blue1 - blue2))<50)
                {
                    p=p+1;
                    bw.write("\t");
                    bw.write(Integer.toString(height1));
                    bw.write("\t");
                    bw.write(Integer.toString(b));
                    bw.write("\n");
                }
                else
                    q=q+1;
            }
        }


        float s = (height1*width1);
        float x =(100*p)/s;

        System.out.println("AEHNLICHKEIT ="+x+"%");
        long stop = System.currentTimeMillis();
        System.out.println("ZEIT ="+(stop-start)/1000);
        System.out.println("PIXEL VERAENDERT:="+q);
        System.out.println("PIXEL GLEICH:="+p);



        //   int totalDiff = Math.abs(Color.red(p1) - Color.red(p2)) + Math.abs(Color.green(p1) - Color.green(p2)) + Math.abs(Color.blue(p1) - Color.blue(p2));






    }

    /*private class WebCamInfo {
        private String webCamName;
        private int webCamIndex;
        public String getWebCamName() {
            return webCamName;
        }
        public void setWebCamName(String webCamName) {
            this.webCamName = webCamName;
        }
        public int getWebCamIndex() {
            return webCamIndex;
        }
        public void setWebCamIndex(int webCamIndex) {
            this.webCamIndex = webCamIndex;
        }
        @Override
        public String toString() {
            return webCamName;
        }
    }
    private FlowPane bottomCameraControlPane;
    private FlowPane topPane;
    private BorderPane root;
    private String cameraListPromptText = "Choose Camera";
    private ImageView imgWebCamCapturedImage;
    private Webcam webCam = null;
    private boolean stopCamera = false;
    private BufferedImage grabbedImage;
    private ObjectProperty<Image> imageProperty = new SimpleObjectProperty<Image>();
    private BorderPane webCamPane;
    private Button btnCamreaStop;
    private Button btnCamreaStart;
    private Button btnCameraDispose;
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Connecting Camera Device Using Webcam Capture API");
        root = new BorderPane();
        topPane = new FlowPane();
        topPane.setAlignment(Pos.CENTER);
        topPane.setHgap(20);
        topPane.setOrientation(Orientation.HORIZONTAL);
        topPane.setPrefHeight(40);
        root.setTop(topPane);
        webCamPane = new BorderPane();
        webCamPane.setStyle("-fx-background-color: #ccc;");
        imgWebCamCapturedImage = new ImageView();
        webCamPane.setCenter(imgWebCamCapturedImage);
        root.setCenter(webCamPane);
        createTopPanel();
        bottomCameraControlPane = new FlowPane();
        bottomCameraControlPane.setOrientation(Orientation.HORIZONTAL);
        bottomCameraControlPane.setAlignment(Pos.CENTER);
        bottomCameraControlPane.setHgap(20);
        bottomCameraControlPane.setVgap(10);
        bottomCameraControlPane.setPrefHeight(40);
        bottomCameraControlPane.setDisable(true);
        createCameraControls();
        root.setBottom(bottomCameraControlPane);
        primaryStage.setScene(new Scene(root));
        primaryStage.setHeight(700);
        primaryStage.setWidth(600);
        primaryStage.centerOnScreen();
        primaryStage.show();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setImageViewSize();
            }
        });
    }
    protected void setImageViewSize() {
        double height = webCamPane.getHeight();
        double width = webCamPane.getWidth();
        imgWebCamCapturedImage.setFitHeight(height);
        imgWebCamCapturedImage.setFitWidth(width);
        imgWebCamCapturedImage.prefHeight(height);
        imgWebCamCapturedImage.prefWidth(width);
        imgWebCamCapturedImage.setPreserveRatio(true);
    }
    private void createTopPanel() {
        int webCamCounter = 0;
        Label lbInfoLabel = new Label("Select Your WebCam Camera");
        ObservableList<WebCamInfo> options = FXCollections.observableArrayList();
        topPane.getChildren().add(lbInfoLabel);
        for (Webcam webcam : Webcam.getWebcams()) {
            WebCamInfo webCamInfo = new WebCamInfo();
            webCamInfo.setWebCamIndex(webCamCounter);
            webCamInfo.setWebCamName(webcam.getName());
            options.add(webCamInfo);
            webCamCounter++;
        }
        ComboBox<WebCamInfo> cameraOptions = new ComboBox<WebCamInfo>();
        cameraOptions.setItems(options);
        cameraOptions.setPromptText(cameraListPromptText);
        cameraOptions.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<WebCamInfo>() {
            @Override
            public void changed(ObservableValue<? extends WebCamInfo> arg0, WebCamInfo arg1, WebCamInfo arg2) {
                if (arg2 != null) {
                    System.out.println("WebCam Index: " + arg2.getWebCamIndex() + ": WebCam Name:" + arg2.getWebCamName());
                    initializeWebCam(arg2.getWebCamIndex());
                }
            }
        });
        topPane.getChildren().add(cameraOptions);
    }
    protected void initializeWebCam(final int webCamIndex) {
        Task<Void> webCamTask = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                if (webCam != null) {
                    disposeWebCamCamera();
                }
                webCam = Webcam.getWebcams().get(webCamIndex);
                webCam.open();
                startWebCamStream();
                return null;
            }
        };
        Thread webCamThread = new Thread(webCamTask);
        webCamThread.setDaemon(true);
        webCamThread.start();
        bottomCameraControlPane.setDisable(false);
        btnCamreaStart.setDisable(true);
    }
    protected void startWebCamStream() {
        stopCamera = false;
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                while (!stopCamera) {
                    try {
                        if ((grabbedImage = webCam.getImage()) != null) {
                            Platform.runLater(new Runnable() {
                                @Override
                                public void run() {
                                    Image mainiamge = SwingFXUtils.toFXImage(grabbedImage, null);
                                    imageProperty.set(mainiamge);
                                }
                            });
                            grabbedImage.
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                return null;
            }
        };
        Thread th = new Thread(task);
        th.setDaemon(true);
        th.start();
        imgWebCamCapturedImage.imageProperty().bind(imageProperty);
    }
    private void createCameraControls() {
        btnCamreaStop = new Button();
        btnCamreaStop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                stopWebCamCamera();
            }
        });
        btnCamreaStop.setText("Stop Camera");
        btnCamreaStart = new Button();
        btnCamreaStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                startWebCamCamera();
            }
        });
        btnCamreaStart.setText("Start Camera");
        btnCameraDispose = new Button();
        btnCameraDispose.setText("Dispose Camera");
        btnCameraDispose.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
                disposeWebCamCamera();
            }
        });
        bottomCameraControlPane.getChildren().add(btnCamreaStart);
        bottomCameraControlPane.getChildren().add(btnCamreaStop);
        bottomCameraControlPane.getChildren().add(btnCameraDispose);
    }
    protected void disposeWebCamCamera() {
        stopCamera = true;
        webCam.close();
        btnCamreaStart.setDisable(true);
        btnCamreaStop.setDisable(true);
    }
    protected void startWebCamCamera() {
        stopCamera = false;
        startWebCamStream();
        btnCamreaStop.setDisable(false);
        btnCamreaStart.setDisable(true);
    }
    protected void stopWebCamCamera() {
        stopCamera = true;
        btnCamreaStart.setDisable(false);
        btnCamreaStop.setDisable(true);
    }
    public static void main(String[] args) {
        launch(args);
    }*/

}