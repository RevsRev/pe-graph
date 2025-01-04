package github.com.rev.plot.ui;

import github.com.rev.plot.canvas.Canvas;
import lombok.Getter;
import lombok.Setter;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GraphFrame extends JFrame implements RefreshListener
{
    @Getter @Setter
    private int width = 1600;
    @Getter @Setter
    private int height = 800;

    private final JPanel contentPane;

    private final Canvas canvas;

    public GraphFrame(int width, int height, Canvas canvas) {
        this.width = width;
        this.height = height;
        this.canvas = canvas;

        canvas.rescale(width, height);

        contentPane = new GraphContent(getContentPane().getLayout(), width, height, canvas);
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

    private void scheduleRepaint(RefreshParms parms) {
        canvas.getGraphicsT().setErase(parms.erase);
        contentPane.paintImmediately(0,0,width, height);
        canvas.getGraphicsT().setErase(false);
    }

    @Override
    public void refreshFired(RefreshParms parms)
    {
        scheduleRepaint(parms);
    }
}
