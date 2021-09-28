import javax.imageio.ImageIO;
import javax.swing.*;

public class Data {
    private int height;
    private int width;
    private int p;
    private double error;
    private int L;
    BufferedImage image;
    BufferedImage restoredImage;
    Model model;

    public Data() {
        inputImage();
        inputParameters();
        setParametersToModel();
        model.start();
        saveImage();
        printParameters();
    }

    private void inputImage() {
        JFrame frame = new JFrame();
        JFileChooser chosenFile = new JFileChooser("images");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.png", new String[]{"png"});
        chosenFile.setFileFilter(filter);
        int ret = chosenFile.showOpenDialog(frame);
        if (ret == 0) {
            File file = chosenFile.getSelectedFile();
            try {
                image = ImageIO.read(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void inputParameters() {
        Scanner scanner = new Scanner(System.in, "UTF-8");
        System.out.println("Rectangle's height (m) :");
        this.height = scanner.nextInt();
        System.out.println("Rectangle's width (n) :");
        this.width = scanner.nextInt();
        System.out.println("Number of neurons on the second layer (p) :");
        this.p = scanner.nextInt();
        System.out.println("Max error value (e) :");
        this.error = scanner.nextInt();
    }

    public void printParameters() {
        int N = width * height * 3;
        L = model.getNumOfRectangles();
        double Z = (N * this.L) / ((N + this.L) * this.p + 2.0);
        System.out.println("n = " + this.width);
        System.out.println("m = " + this.height);
        System.out.println("p = " + this.p);
//        System.out.println("N = " + N);
        System.out.println("Z = " + Z);
        System.out.println("L = " + this.L);
    }

    private void setParametersToModel(){
        this.model = new Model();
        model.setImage(image);
        model.setError(error);
        model.setP(p);
        model.setRectangleHeight(height);
        model.setRectangleWidth(width);
    }

    private void saveImage(){
        restoredImage = model.getRestoredImage();
        File file = new File("images/output.png");
        try{
            ImageIO.write(restoredImage,"png", file);
        } catch (IOException e){
            e.printStackTrace();
        }
        System.out.println("Image saved.");
    }

}
