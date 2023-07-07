package rev.pe.graph.ui;

import lombok.Getter;
import lombok.Setter;
import rev.pe.graph.canvas.Canvas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GraphFrame extends JFrame
{
    @Getter @Setter
    private int width = 3200;
    @Getter @Setter
    private int height = 1600;

    private JPanel contentPane;

    private final rev.pe.graph.canvas.Canvas canvas;

    public GraphFrame(Canvas canvas) {
        this.canvas = canvas;

        contentPane = new JPanel(getContentPane().getLayout());
        setContentPane(contentPane);
        setSize(width, height);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter()
        {
            public void windowClosing(WindowEvent e) {
                onClose();
            }
        });

        contentPane.registerKeyboardAction(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                onClose();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onClose() {
        dispose();
    }

    @Override
    public void paint(Graphics g) {
        canvas.paint((Graphics2D)g);
    }

}
