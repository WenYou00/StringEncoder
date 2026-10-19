// =====================================================================
// Encoded Class
// =====================================================================

public class Encoded {

    // ===========================================================
    // Fields & Constructors 
    // ===========================================================
    private String inputText;      // Stores the original user input string
    private int charCount;         // Stores the number of non-space characters
    private String resultText;     // Store encoded result text
    private final String groupID;  // final keyword ensures it cannot be modified

    // Default Constructors
    public Encoded() {
        this.inputText = "";
        this.charCount = 0;
        this.resultText = "";
        this.groupID = "G07/MC-G12";
    }

    // Constructor with inputText parameter
    public Encoded(String inputText) {
        this();    // Call default constructor - groupID is assigned here
        this.inputText = inputText;
        this.charCount = countCharacters(inputText); // Automatically count characters
    }


    // ===========================================================
    // Input Validation 
    // ===========================================================
    public boolean checkStringValidity(String inputText) {
        // Check null or empty input
        if (inputText == null || inputText.isEmpty()) {
            return false;
        }

        // Check each character one by one
        for (int i = 0; i < inputText.length(); i++) {

            char c = inputText.charAt(i);

            // Accept lowercase letters
            if (c >= 'a' && c <= 'z') {
                continue;
            }

            // Accept digits
            else if (c >= '0' && c <= '9') {
                continue;
            }

            // Accept spaces
            else if (c == ' ') {
                continue;
            }

            // Any other character is invalid
            else {
                return false;
            }
        }

        return true;  // All characters are valid
    }


    // ===========================================================
    // Character Counting 
    // ===========================================================
    public int countCharacters(String inputText) {

        int count = 0;

        // Loop through each character
        for (int i = 0; i < inputText.length(); i++) {
            // Only count if character is not a space
            if (inputText.charAt(i) != ' ') {
                count++;
            }
        }

        return count;
    }


    // ===========================================================
    // Generate Shift 
    // ===========================================================
    public int generateShift() {
        // Get hash code of the secret group ID
        int hash = this.groupID.hashCode();

        // Ensure positive value, then map to range 1-10
        int groupShift = Math.abs(hash) % 10 + 1;

        return groupShift;
    }


    // ===========================================================
    // Apply Cipher 
    // ===========================================================
    public String applyCipher(String inputText, int shift) {
        // Handle null or empty input
        if (inputText == null || inputText.isEmpty()) {
            return "";
        }
        
        StringBuilder encoded = new StringBuilder();
        
        // Process each character one by one
        for (int i = 0; i < inputText.length(); i++) {
            char c = inputText.charAt(i);
            
            // Shift lowercase letters
            if (c >= 'a' && c <= 'z') {
                char shifted = (char) ((c - 'a' + shift) % 26 + 'a');
                encoded.append(shifted);
            } 

            // Shift digits
            else if (c >= '0' && c <= '9') {
                char shifted = (char) ((c - '0' + shift) % 10 + '0');
                encoded.append(shifted);
            } 

            // Preserve spaces unchanged
            else if (c == ' ') {
                encoded.append(' ');
            }
        }

        // Store the encoded result in the class field
        this.resultText = encoded.toString();

        return encoded.toString();
    }
    
    // ===========================================================
    // Getter and Setter Methods 
    // ===========================================================
    // Getter method for inputText
    public String getInputText() { 
        return inputText; 
    }

    // Setter method for inputText
    public void setInputText(String inputText) { 
        this.inputText = inputText; 
        this.charCount = countCharacters(inputText);
    }

    // Getter method for charCount
    public int getCharCount() { 
        return charCount; 
    }

    // Getter method for resultText
    public String getResultText() { 
        return resultText; 
    }

    // Setter method for resultText
    public void setResultText(String resultText) { 
        this.resultText = resultText; 
    }
}