import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;


public class FractalExplorer {
    private int size;
    int rowsRemain;
    private JFrame frame;
    public JImageDisplay img;
    private JButton resetButton;
    private JButton saveButton;
    private JComboBox combobox;
    public FractalExplorer(int s) {
        size = s;
        generator.getInitialRange(range);
    }
    FractalGenerator generator = new Mandelbrot();
    Rectangle2D.Double range = new Rectangle2D.Double();
    public void createAndShowGUI() {
        JPanel Npanel = new JPanel();
        JPanel Spanel = new JPanel();
        frame = new JFrame();
        img = new JImageDisplay(size, size);
        resetButton = new JButton("Reset");
        saveButton = new JButton("Save");
        combobox = new JComboBox();
        combobox.addItem(new Mandelbrot());
        combobox.addItem(new Tricorn());
        combobox.addItem(new BurningShip());
        frame.getContentPane().add(Npanel, BorderLayout.NORTH);
        frame.getContentPane().add(Spanel, BorderLayout.SOUTH);
        JLabel label = new JLabel("Fractal type:");
        Npanel.add(label);
        Npanel.add(combobox);
        Spanel.add(resetButton);
        Spanel.add(saveButton);
        frame.getContentPane().add(img, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        combobox.addActionListener(new actionListener());
        resetButton.addActionListener(new actionListener());  //Сброс
        saveButton.addActionListener(new actionListener());
        img.addMouseListener(new KJIUK());      //Клик

        frame.pack();
        frame.setVisible(true);
        frame.setResizable(true);
    }
    private void drawFractal() {
        rowsRemain = size;
            for (int y = 0; y < size; y++)
            {
                FractalWorker drawer = new FractalWorker(y);
                drawer.execute();
            }
    }

    private class FractalWorker extends SwingWorker<Object ,Object> {
        int Ycoord;
        int[] RGBarr;

        FractalWorker(int y){Ycoord = y;}

        @Override
        protected Object doInBackground() throws Exception {
            RGBarr = new int[size];
            double yCoord = FractalGenerator.getCoord
                    (range.y, range.y + range.height, size, Ycoord);
            for (int x = 0; x < size; x++) {
                double xCoord = FractalGenerator.getCoord
                        (range.x, range.x + range.width, size, x);
                int IterNum = generator.numIterations(xCoord, yCoord);
                if (IterNum == -1) RGBarr[x] = 0;
                else {
                    float hue = 0.7f + (float) IterNum / 200f;
                    RGBarr[x] = Color.HSBtoRGB(hue, 1f, 1f);
                }
            }
            return null;
        }

        @Override
        protected void done() {
            for (int x=0; x<size; x++) {
                img.drawPixel(x, Ycoord, RGBarr[x]);
            }
            img.repaint(0,Ycoord, size, 1);
            rowsRemain--;
            if (rowsRemain==0) enableUI(true);
            else enableUI(false);
        }


    }

    private class actionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if(actionEvent.getSource() == combobox) {
                generator = (FractalGenerator) combobox.getSelectedItem();
                generator.getInitialRange(range);
                drawFractal();
            }
            else if (actionEvent.getSource() == resetButton) {
                generator.getInitialRange(range);
                drawFractal();
            }
            else if (actionEvent.getSource() == saveButton) {
                JFileChooser chooser = new JFileChooser();
                FileFilter filter = new FileNameExtensionFilter("PNG Images", "png");
                chooser.setFileFilter(filter);
                chooser.setAcceptAllFileFilterUsed(false);
                if (chooser.showSaveDialog(img) == JFileChooser.APPROVE_OPTION) {
                    try {
                        ImageIO.write(img.img, "png", chooser.getSelectedFile());
                    } catch (IOException exc) {
                        JOptionPane.showMessageDialog(frame, exc.getMessage(), "Cannot Save Image", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }
    }

    private class KJIUK extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (rowsRemain != 0) return;
            int x = e.getX();
            int y = e.getY();

            double xCoord = generator.getCoord(range.x,range.x+range.width, size, x);
            double yCoord = generator.getCoord(range.y,range.y+range.height, size, y);
            generator.recenterAndZoomRange(range, xCoord, yCoord, 0.5);
            drawFractal();
        }
    }

    public static void main(String[] args) {
        FractalExplorer fractalItself = new FractalExplorer(800);
        fractalItself.createAndShowGUI();
        fractalItself.drawFractal();
    }

    public void enableUI(boolean val) {
        resetButton.setEnabled(val);
        saveButton.setEnabled(val);
        combobox.setEnabled(val);
    }

}
