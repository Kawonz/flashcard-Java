import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FlashcardLoad extends JFrame {
    private JPanel outerDisplay;
    private JTextField flashcardText;
    private JButton goForwardButton;
    private JButton goBackButton;
    private JPanel entryLayout;
    private JButton backButton;
    private JButton playButton;
    private JButton flipButton;
    private Main main;
    
    String frontText;
    String backText;
    int currentIndex = 0;
    Boolean onFrontSide = true;

    public FlashcardLoad() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(outerDisplay);
        this.setSize(650,450);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        setFrontCard();
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transitionWindowToMain();
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        goForwardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onFrontSide = true; // Set true to be sure card always faces the front when card goes to previous or next card.
                try {
                    int amountOfFlashcards = getWordsFromFile().size() -1; // Max size of the list of flashcards at that moment.
                    if (currentIndex == amountOfFlashcards) {
                        currentIndex = 0;
                        setFrontCard();
                    } else {
                        currentIndex ++;
                        setFrontCard();
                    }
                } catch (IOException ioException) {
                    System.out.println("File not found :(");
                }

            }
        });
        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onFrontSide = true;
               if (currentIndex > 0) {
                   currentIndex --;
                   setFrontCard();
               } else {
                   try {
                       int amountOfFlashcards = getWordsFromFile().size() - 1;
                       currentIndex = amountOfFlashcards;
                       setFrontCard();
                   } catch (IOException ioException) {
                       System.out.println("File not found :(");
                   }
               }
            }
        });
        flipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (onFrontSide) {
                    System.out.println("To back side");
                    flashcardText.setText(backText);
                    onFrontSide = false;
                } else {
                    System.out.println("To front side");
                    flashcardText.setText(frontText);
                    onFrontSide = true;
                }
            }
        });
    }

    private void transitionWindowToMain() {
        main = new Main();
        this.setVisible(false);
        main.setVisible(true);
    }

    private List getWordsFromFile() throws IOException {
        List<String> words = new ArrayList<>();
        File file = new File("Flashcard.txt"); // [!] Keep pathname file closed when program is running.
        for (String line : Files.readAllLines(Paths.get("Flashcard.txt"))) {
            words.add(line);
        }
        return words;
    }
        private void setFrontCard () {
            try {
                String currentIndexText = (String) getWordsFromFile().get(currentIndex);
                String[] splitText = currentIndexText.split(":");
                frontText = splitText[0];
                backText = splitText[1];
                flashcardText.setText(frontText);
                System.out.println(getWordsFromFile());
            } catch(IOException ex) {
                System.out.println("File not found :(");
            }
    }
}
