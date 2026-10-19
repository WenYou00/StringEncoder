// =====================================================================
// Main Application
// =====================================================================

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Assignment extends JFrame {

    // =====================================================================
    // GUI Components Declaration
    // =====================================================================
    private JTextField inputField = new JTextField(20);          // Text field for user input
    private JTextArea outputArea = new JTextArea(10, 30);  // Text area to display results
    private JButton encodeButton = new JButton("Encode Text");     // Button to start encoding process
    private JButton clearButton = new JButton("Clear");            // Button to clear encode
    
    
    // =====================================================================
    // GUI Layout 
    // =====================================================================
    // Constructor for Assignment GUI
    public Assignment() {
        // JFrame Setup
        setTitle("Cipher Encoder - G07/MC-G12\""); // Window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Exit application when closed
        setLayout(new FlowLayout());                     // Simple layout manager

        // Create Input Panel
        JPanel panel = new JPanel();  // Setup Panel
        panel.add(new JLabel("Enter Text:")); // Label for input field
        panel.add(inputField);                     // Add text field into panel
        panel.add(encodeButton);                   // Add encode button into panel
        panel.add(clearButton);                    // Add clear button into panel

        // Setup Output Area
        outputArea.setEditable(false);  // Prevent user editing output
        add(panel);                       // Add components into JFrame
        add(new JScrollPane(outputArea)); // Add scroll pane for output

        // Finalize window setup
        pack();                         // Auto-size window
        setLocationRelativeTo(null); // Display window at center
        setVisible(true);            // Show window


        // =====================================================================
        // Button Listeners, Workflow, Error Handling 
        // =====================================================================
        // Workflow 
        // Button Listener: Encode
        encodeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                // Step 1: Get user input from text field
                String input = inputField.getText();

                // Step 2: Create Encoded object with input
                Encoded encoder = new Encoded(input);

                // Error Handling: empty input 
                if (input.isEmpty()) {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Input cannot be empty!", 
                        "Error", 
                        JOptionPane.ERROR_MESSAGE
                );

                    return;
                }
 
                // Error Handling: invalid characters
                if (!encoder.checkStringValidity(input)) {
                    JOptionPane.showMessageDialog(
                        null, 
                        "Invalid input!", 
                        "Validation Error", 
                        JOptionPane.ERROR_MESSAGE
                );

                    return;
                }

                // Step 3: Count non-space characters
                int charCount = encoder.countCharacters(input);

                // Step 4: Generate group shift
                int groupShift = encoder.generateShift();

                // Step 5: Calculate final shift
                int finalShift = groupShift + charCount;

                 // Step 6: Apply cipher to encode the string
                String encodedResult = encoder.applyCipher(input, finalShift);

                // Step 7: Display results in output area
                outputArea.setText(
                        "Non-space Characters : " + charCount
                        + "\n\nFinal Shift : " + finalShift
                        + "\n\nEncoded Result : " + encodedResult
                        + "\n\nEncoding completed successfully.");
            }
        });

        // Button Listener: Clear
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Clear input text field
                inputField.setText("");
                
                // Clear output text area
                outputArea.setText("");
                
                // Set focus back to input field for next entry
                inputField.requestFocus();
            }
        });
    }


    // =====================================================================
    // Main Method 
    // =====================================================================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Assignment());
    }
}