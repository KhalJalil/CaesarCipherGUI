import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class CaesarCipherGUI {

    public static final String charpool ="abcdefghijklmnopqrstuvwxyz";
    private JLabel displayEncrypted, displayDecrypted, enkeyLabel,dekeyLabel;
    private JLabel plaintextLabel, cipherTextLabel;
    private JTextField enterPlain, enterCipher, keyEn, keyDe;
    private JButton encryptButton, decryptButton;

    private JFrame myFrame;
    private JPanel myPanel;

    public CaesarCipherGUI() {

        myPanel =new JPanel();
        myFrame = new JFrame();
        myFrame.add(myPanel);

        myPanel.setLayout(null);
        myPanel.setBackground(Color.GRAY);

        enterCipher = new JTextField(" ");
        enterPlain = new JTextField("  ");
        enterPlain.getInsets(new Insets(10,10,2,2) );


        keyDe = new JTextField("    ");
        keyEn = new JTextField("    ");

        enterPlain.setBounds(20,40,200,30);
        enterCipher.setBounds(20,100,200,30);
        myPanel.add(enterCipher);
        myPanel.add(enterPlain);

        keyEn.setBounds(250,40,50,30);
        keyDe.setBounds(250,100,50,30);
        myPanel.add(keyEn);
        myPanel.add(keyDe);

        encryptButton = new JButton("Encrypt");
        decryptButton = new JButton("Decrypt");

        encryptButton.setBounds(350,40,80,30);
        decryptButton.setBounds(350,100,80,30);
        myPanel.add(encryptButton);
        myPanel.add(decryptButton);

        displayEncrypted =new JLabel("Your cipher Text : ");
        displayDecrypted = new JLabel("Your plaintext is: ");

        displayEncrypted.setBounds(450,40,400,30);
        displayDecrypted.setBounds(450,100,400,30);
        myPanel.add(displayEncrypted);
        myPanel.add(displayDecrypted);

        enkeyLabel = new JLabel("Encryption Key ");
        dekeyLabel = new JLabel("Decryption Key");

        enkeyLabel.setBounds(230,20,100,30);
        dekeyLabel.setBounds(230,80,100,30);
        myPanel.add(enkeyLabel);
        myPanel.add(dekeyLabel);

        plaintextLabel= new JLabel("Plain Text ");
        cipherTextLabel = new JLabel("Cipher Text");

        plaintextLabel.setBounds(21,20,150,30);
        cipherTextLabel.setBounds(21,80,150,30);
        myPanel.add(plaintextLabel);
        myPanel.add(cipherTextLabel);

        encryptButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String myKey = keyEn.getText().replaceAll(" ","");
                        int key = Integer.parseInt(myKey);
                        String plaintext = enterPlain.getText().toLowerCase().replaceAll(" ","")
                                .replaceAll("\\W","");
                        displayEncrypted.setText("Your ciphertext is: "+encryption(key,plaintext));

                    }
                }
        );
        decryptButton.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String myKey = keyDe.getText().replaceAll(" ","");
                        int key = Integer.parseInt(myKey);
                        String cipherText = enterCipher.getText().toLowerCase().replaceAll(" ","");
                        displayDecrypted.setText("Your plaintext is: "+decryption(key,cipherText));
                    }
                }
        );
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setLocationRelativeTo(null);
        myFrame.setTitle("Caesars Cipher");
        myFrame.setSize(800,400);
        myFrame.setVisible(true);
    }
    public static void main  (String []args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        new CaesarCipherGUI();
    }
    public  static String encryption(int  key, String character){

        char cTextChar;
        String cipherText="";
        char plainChar[]= new char[character.length()];
        for (int i = 0; i<character.length();i++){

            plainChar[i] = character.charAt(i);
            int ptextIntPosition = charpool.indexOf(plainChar[i]);

            int cTextInt = (ptextIntPosition + key)%26;

            cTextChar = charpool.charAt(cTextInt);
            cipherText+=cTextChar;
        }

        return cipherText;
    }

    public static String decryption(int  key, String character) {

        char pTextChar;
        String plainText="";
        int pTextInt;
        char cipherChar[]= new char[character.length()];
        for (int i = 0; i<character.length();i++){

            cipherChar[i] = character.charAt(i);
            int ctextIntPosition = charpool.indexOf(cipherChar[i]);

            pTextInt = (ctextIntPosition - key);
            if (pTextInt<0){
                while (pTextInt<0)
                    pTextInt= pTextInt+ 26;
                pTextInt =(pTextInt%26);
            }
            else {
                pTextInt = pTextInt%26;
            }
            pTextChar = charpool.charAt(pTextInt);
            plainText+=pTextChar;
        }
        return plainText;
    }

}

