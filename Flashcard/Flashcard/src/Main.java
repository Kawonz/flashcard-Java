import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    private JPanel outerDisplay;
    private JPanel buttonPanel;
    private JButton create;
    private JButton review;
    private JLabel titleDisplay;

    private FlashcardCreate flashcardCreate;
    private FlashcardLoad flashcardLoad;

    public Main() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(outerDisplay);
        this.setSize(300,200);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        create.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createTransitionWindow();
            }
        });
        review.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadTransitionWindow();
            }
        });
    }
    public void createTransitionWindow() {
        flashcardCreate = new FlashcardCreate();
        this.setVisible(false);
        flashcardCreate.setVisible(true);
    }
    public void loadTransitionWindow() {
        flashcardLoad = new FlashcardLoad();
        this.setVisible(false);
        flashcardLoad.setVisible(true);
    }
    public static void main(String[] args) {
        Main main = new Main();
    }
}
