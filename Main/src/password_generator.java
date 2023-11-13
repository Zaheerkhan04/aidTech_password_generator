import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.awt.Color;
import java.awt.Font;

public class PasswordGenerator {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Password Generator");
        frame.getContentPane().setBackground(new Color(64, 128, 128));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(449, 356);
        frame.getContentPane().setLayout(null);

        JLabel lengthLabel = new JLabel("Enter the length of the password:");
        lengthLabel.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 15));
        lengthLabel.setForeground(new Color(255, 255, 255));
        lengthLabel.setBounds(20, 72, 225, 20);
        frame.getContentPane().add(lengthLabel);

        JTextField lengthField = new JTextField();
        lengthField.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 16));
        lengthField.setBounds(255, 74, 96, 20);
        frame.getContentPane().add(lengthField);

        JLabel characterSetLabel = new JLabel("Choose a character set:");
        characterSetLabel.setForeground(new Color(255, 255, 255));
        characterSetLabel.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 15));
        characterSetLabel.setBounds(20, 118, 200, 20);
        frame.getContentPane().add(characterSetLabel);

        JComboBox<String> characterSetComboBox = new JComboBox<>(new String[]{"Uppercase letters (A-Z)",
                "Lowercase letters (a-z)", "Numbers (0-9)", "Special characters (!@#$%^&*)", "All of the above"});
        characterSetComboBox.setForeground(new Color(0, 128, 128));
        characterSetComboBox.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 14));
        characterSetComboBox.setBounds(255, 117, 165, 20);
        frame.getContentPane().add(characterSetComboBox);

        JButton generateButton = new JButton("Generate Password");
        generateButton.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 15));
        generateButton.setForeground(new Color(0, 128, 128));
        generateButton.setBounds(132, 176, 200, 30);
        frame.getContentPane().add(generateButton);

        JTextArea resultArea = new JTextArea();
        resultArea.setFont(new Font("Arial Narrow", Font.ITALIC, 17));
        resultArea.setForeground(new Color(0, 128, 128));
        resultArea.setEditable(false);
        resultArea.setBounds(81, 235, 298, 30);
        frame.getContentPane().add(resultArea);

        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int passwordLength;
                try {
                    passwordLength = Integer.parseInt(lengthField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input for password length. Please enter a number.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String characterSet = "";
                int choice = characterSetComboBox.getSelectedIndex();

                switch (choice) {
                    case 0:
                        characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                        break;
                    case 1:
                        characterSet = "abcdefghijklmnopqrstuvwxyz";
                        break;
                    case 2:
                        characterSet = "0123456789";
                        break;
                    case 3:
                        characterSet = "!@#$%^&*";
                        break;
                    case 4:
                        characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
                        break;
                }

                String password = generateRandomPassword(passwordLength, characterSet);
                resultArea.setText("Generated Password: " + password);
            }
        });

        frame.setVisible(true);
    }

    public static String generateRandomPassword(int length, String characterSet) {
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(characterSet.length());
            char randomChar = characterSet.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }
}
