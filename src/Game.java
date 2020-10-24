import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

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
    JButton winTheGameButton = new JButton("Vinn spelet");
    List<JButton> gameList = new ArrayList<>();
    JButton blank = new JButton();
    List<Integer> listOfNumbers = new ArrayList<>(15);

    public void createButtons(){
        for (int i = 1; i < 16; i++) {
            JButton button = new JButton();

            gameList.add(randomButton(button));
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
    }

    public JButton randomButton(JButton button){
        Random r = new Random();

        while (true){
            int number = r.nextInt(15)+1;
            if(!listOfNumbers.contains(number)){
                button.setText(String.valueOf(number));
                listOfNumbers.add(number);
                break;
            }
        }
        return button;
    }

    Game(){
        createButtons();
        gameList.add(blank);
        blank.addMouseListener(ma);

        setTitle("GameNight!");
        gridPanel.setLayout(new GridLayout(4,4));
        panel.setLayout(new BorderLayout());
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridLayout(2,1));
        add(panel);
        panel.add(gridPanel, BorderLayout.WEST);
        rightPanel.add(newGameButton);
        rightPanel.add(winTheGameButton);
        panel.add(rightPanel, BorderLayout.EAST);

        newGameButton.addMouseListener(ma);
        winTheGameButton.addMouseListener(ma);

        gridPanel.add(blank);

        setVisible(true);
        setSize(400,200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    final MouseAdapter ma = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {

            //TODO: Snygga till?
            boolean test = false;
            for (JButton jb : gameList) {
                if (e.getSource() == jb) {
                    for (int i = 0; i < gameList.size(); i++) {

                        if (jb == gameList.get(i) && jb != blank) {
                            int left = gameList.indexOf(blank)-1;
                            int right = gameList.indexOf(blank)+1;
                            int top = gameList.indexOf(blank)-4;
                            int bottom = gameList.indexOf(blank)+4;

                            if(i == left || i == right || i == top || i==bottom){
                                /*
                                Källa för att byta plats på två objekt i en lista:
                                https://howtodoinjava.com/java/collections/arraylist/swap-two-elements-arraylist/
                                 */
                                //Byter plats på två knappar i listan
                                Collections.swap(gameList, gameList.indexOf(blank), gameList.indexOf(jb));

                                //Byter plats på tryckt knapp med blank i spelet
                                Point p = new Point(jb.getLocation());
                                jb.setLocation(blank.getLocation());
                                blank.setLocation(p);

                                test = true;
                            }
                        }
                        if (test)
                            break;
                    }
                }
                if (test)
                    break;
            }

            if (e.getSource() == newGameButton) {
                Game newStart = new Game();
            }

            if (e.getSource() == winTheGameButton) {
                int last = gameList.size()-1;

                if(gameList.indexOf(blank) != last){
                    Collections.swap(gameList, gameList.indexOf(blank), gameList.indexOf(gameList.get(last)));

                    Point p = new Point(gameList.get(last).getLocation());
                    gameList.get(last).setLocation(blank.getLocation());
                    blank.setLocation(p);
                }

                for (int j = 0; j < gameList.size(); j++) {
                    for (int i = 0; i < 14; i++) {
                        int y = i+1;
                        int firstNumber = Integer.parseInt(gameList.get(i).getText());
                        int secondNumber = Integer.parseInt(gameList.get((y)).getText());

                        if (firstNumber > secondNumber){
                            Collections.swap(gameList, i, y);
                            Point first = new Point(gameList.get(i).getLocation());
                            gameList.get(i).setLocation(gameList.get(y).getLocation());
                            gameList.get(y).setLocation(first);
                        }
                    }
                }
            }
        }
    };

    public static void main(String[] args) {
        Game start = new Game();
    }
}