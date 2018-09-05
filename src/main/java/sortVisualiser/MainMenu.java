package sortVisualiser;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import static sortVisualiser.Main.WIN_HEIGHT;
import static sortVisualiser.Main.WIN_WIDTH;
import sortVisualiser.algorithms.BubbleSort;
import sortVisualiser.algorithms.GnomeSort;
import sortVisualiser.algorithms.ISortAlgorithm;
import sortVisualiser.algorithms.InsertionSort;
import sortVisualiser.algorithms.MergeSort;
import sortVisualiser.algorithms.QuickSort;
import sortVisualiser.algorithms.SelectionSort;

/**
 *
 * @author Matthew Hopson
 */
public class MainMenu extends JPanel {
    
    private static final Color BACKGROUND_COLOUR = Color.darkGray;
    private ArrayList<AlgorithmCheckBox> checkBoxes;
    
    public MainMenu() {
        checkBoxes = new ArrayList<>();
        setUpGUI();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIN_WIDTH, WIN_HEIGHT);
    }
    
    private void addCheckBox(ISortAlgorithm algorithm, JPanel panel) {
        JCheckBox box = new JCheckBox("", true);
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.setBackground(BACKGROUND_COLOUR);
        box.setForeground(Color.WHITE);
        checkBoxes.add(new AlgorithmCheckBox(algorithm, box));
        panel.add(box);
    }
    
    public void setUpGUI() {
        JPanel container = new JPanel();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
   
        setBackground(BACKGROUND_COLOUR);
        container.setBackground(BACKGROUND_COLOUR);
        
        try {
            ClassLoader loader = getClass().getClassLoader();
            BufferedImage image = ImageIO.read(new File(loader.getResource("logo.png").getFile()));
            JLabel label = new JLabel(new ImageIcon(image));
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            add(label);
        } catch (IOException e) {
            System.out.println("Unable to load logo");
        }
        
        container.setAlignmentX(Component.CENTER_ALIGNMENT);
        addCheckBox(new SelectionSort(),    container);
        addCheckBox(new QuickSort(),        container);
        addCheckBox(new MergeSort(),        container);
        addCheckBox(new InsertionSort(),    container);
        addCheckBox(new GnomeSort(),        container);
        addCheckBox(new BubbleSort(),      container);
        
        JButton startButton = new JButton("Begin Visual Sorter");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        add(container);
        add(startButton);
    }
    
    private class AlgorithmCheckBox {
        private final ISortAlgorithm algorithm;
        private JCheckBox box;
        
        public AlgorithmCheckBox(ISortAlgorithm algorithm, JCheckBox box) {
            this.algorithm = algorithm;
            this.box = box;
            this.box.setText(algorithm.getName());
        }
        
        public boolean isSelected() {
            return box.isSelected();
        }
        
        public ISortAlgorithm getAlgorithm() {
            return algorithm;
        }
    }
    
}