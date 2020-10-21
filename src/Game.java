import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hanna Edlund
 * Date: 2020-10-21
 * Time: 19:45
 * Project: InlämningSprint3
 */
public class Game extends JFrame {
    JPanel panel = new JPanel();
    JPanel gridPanel = new JPanel();
    JButton newGameButton = new JButton("Nytt spel");
    List<JButton> gameList = new ArrayList<>();
    JLabel blank = new JLabel("BLANK");

    Game(){
        for (int i = 1; i < 16; i++) {
            JButton button = new JButton(String.valueOf(i));
            gameList.add(button);
            gridPanel.add(button);
            button.setBackground(Color.gray);
            button.setForeground(Color.darkGray);
            /*
            Källa till ändringen av border färgen:
            https://stackoverflow.com/questions/56157666/change-border-color-of-a-jbutton-in-java-swing-preserving-the-insets
             */
            button.setBorder(BorderFactory.createCompoundBorder(
                    BorderFactory.createLineBorder(Color.black, 1),
                    BorderFactory.createEmptyBorder(
                            button.getBorder().getBorderInsets(button).top,
                            button.getBorder().getBorderInsets(button).left,
                            button.getBorder().getBorderInsets(button).bottom,
                            button.getBorder().getBorderInsets(button).right)));
            button.setSize(10,10);
            button.addMouseListener(ma);
        }
        setTitle("GameNight!");
        gridPanel.setLayout(new GridLayout(4,4));
        panel.setLayout(new BorderLayout());
        add(panel);
        panel.add(gridPanel, BorderLayout.WEST);
        panel.add(newGameButton, BorderLayout.EAST);
        newGameButton.addMouseListener(ma);
        gridPanel.add(blank);

        setVisible(true);
        setSize(350,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    MouseAdapter ma = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            for (JButton jb : gameList) {
                if(e.getSource() == jb){
                    Point p = new Point(jb.getLocation());
                    jb.setLocation(blank.getLocation());
                    blank.setLocation(p);
                }
            }
            if(e.getSource() == newGameButton){
                Game newStart = new Game();
            }
        }
    };

    public static void main(String[] args) {
        Game start = new Game();
    }
}


/* Location på brickorna
0-1	    49-1	98-1 	147-1
0-41	49-41	98-41	147-41
0-81	49-81	98-81	147-81
0-121	49-121	98-121	147-121
 */