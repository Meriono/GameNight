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

    Game(){
        for (int i = 1; i < 16; i++) {
            JButton button = new JButton(String.valueOf(i));
            gameList.add(button);
            gridPanel.add(button);
            button.setBackground(Color.gray);
            button.setForeground(Color.darkGray);
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

        setVisible(true);
        setSize(350,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    MouseAdapter ma = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            super.mouseClicked(e);
            for (JButton jb : gameList) {
                if(e.getSource() == jb){
                    System.out.println("Du klickade på knapp: " + (gameList.indexOf(jb)+1));
                }
            }
        }
    };

    public static void main(String[] args) {
        Game start = new Game();
    }
}
