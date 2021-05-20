import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FlashcardCreate extends JFrame {
    // GUI Components
    private JPanel outerDisplay;
    private JTextField frontText;
    private JTextField backText;
    private JButton createButton;
    private JLabel front;
    private JLabel back;
    private JPanel entryLayoutDisplay;
    private JLabel cardTitle;
    private JTextField cardFront;
    private JTextField cardBack;
    private JPanel cardDisplay;
    private JButton backButton;
    private JLabel confirmationText;

    int TEXT_DISPLAY_LENGTH = 500;
    int INITIAL_COUNT = 2;
    int MAX_CHARACTERS = 30;
    int countdown = INITIAL_COUNT;
    Timer timer;
    private Main main;

    public FlashcardCreate() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(outerDisplay);
        this.setSize(650,450);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transitionWindowToMain();
            }
        });
        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Steps to presenting the flashcard and GUI.
                String front = frontText.getText();
                String back = backText.getText();
                writeToFile();
                frontText.setText("");
                backText.setText("");
                cardBack.setText(back);
                cardFront.setText(front);
                ActionListener SuccessTextTimer = new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (countdown == 0) {
                            timer.stop();
                            confirmationText.setVisible(false);
                            countdown = INITIAL_COUNT;
                        } else if(countdown > 0) {
                            confirmationText.setVisible(true);
                            countdown--;
                            System.out.println(countdown);
                        }
                    }
                };

                timer = new Timer(TEXT_DISPLAY_LENGTH,SuccessTextTimer);
                timer.start();
            }
        });
        frontText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (frontText.getText().length() > MAX_CHARACTERS) {
                    e.consume();
                }
            }
        });
        backText.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (backText.getText().length() > MAX_CHARACTERS) {
                    e.consume();
                }
            }
        });
    }
    private void transitionWindowToMain() {
        main = new Main();
        this.setVisible(false);
        main.setVisible(true);
    }
    private void writeToFile(){
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter("Flashcard.txt",true));
            out.write(frontText.getText()+ ":"+backText.getText()+"\n");
            System.out.println("File created with front/back text.");
            out.close();
        } catch (IOException e) {

        }
    }
}
