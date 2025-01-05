package github.com.rev.plot.ui;

import github.com.rev.plot.canvas.Canvas;
import lombok.Getter;
import lombok.Setter;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public final class GraphFrame extends JFrame {
    @Getter @Setter
    private int width;
    @Getter @Setter
    private int height;

    private final JPanel contentPane;

    private final Canvas canvas;

    public GraphFrame(final int width, final int height, final Canvas canvas) {
        this.width = width;
        this.height = height;
        this.canvas = canvas;

        canvas.rescale(width, height);

        contentPane = new GraphContent(getContentPane().getLayout(), width, height, canvas);
        setContentPane(contentPane);
        setSize(width, height);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(final WindowEvent e) {
                onClose();
            }
        });

        contentPane.registerKeyboardAction(
                e -> onClose(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }

    private void onClose() {
        dispose();
    }
}
