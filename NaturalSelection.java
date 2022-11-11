import java.awt.*;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

public class NaturalSelection extends JFrame
{
  public NaturalSelection()
  {
    super("Natural Selection");

    NaturalSelectionPanel game = new NaturalSelectionPanel();
    
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setBorder(new EmptyBorder(0, 0, 0, 0));
    panel.add((JPanel)game, BorderLayout.CENTER);

    Container c = getContentPane();
    c.add(panel, BorderLayout.CENTER);
  }

  public static void main(String[] args)
  {
    NaturalSelection window = new NaturalSelection();
    window.setBounds(0, 0, 800, 800);
    window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    window.setVisible(true);
  }
}
