# Steganography
Steganography is the practice of concealing messages or information within other non-secret text or data. This program utilizes bitwise operations to conceal text messages in a JPG/PNG image without visually altering the image itself.

## Usage
Run the `main` method on `Controller.java`. This will produce a GUI with which `encode` and `decode` functions can be utilized.

To encode, click on the *Encode* tab and write the message in the text area given. Then press *Encode Now* button on the bottom. This brings up a File Chooser with which you can select either a JPG or PNG image file (only these two types are supported). If the encoding was successful, a Message Dialog will appear with the Success message along with a pop-up with the encoded image (there should be no visual changes to the image).

To decode, click on the *Decode* tab. On the bottom left-hand side, click on the *Choose PNG File* button. A File Chooser will then appear. You can only select PNG files for the decoder to work. After choosing a PNG file, the image will appear above the buttons. Pressing *Decode Now* will decode the image and a pop-up will appear with the message if the decoding was successful.

## How It Works
#### Steganography.java
This is the Model file with the functionalities of `encode` and `decode` defined. Using a *BufferedImage* version of the given JPG/PNG image, the program first creates a User Space which is a copy of the image that can be played around with. With this User Space, the program then selects bytes of the image and modifies the least significant bits in accordance to the byte data of the message.

Decode works similarly in that it looks at the changed bytes of the image and collects all of the least significant bits. Putting these together results in the message.

#### View.java
This is the View Controller file that contains the code for the GUI implemented in this program. The GUI uses *JTabbedPane* with `encode` and `decode` on their respective tabs. Within each tab, *GridBagLayout* is used to organize the layout.

In the `encode` tab, there is a *JTextArea* where the message is written. Pressing the *Encode Now* button will bring up a *JFileChooser* with which a JPG/PNG can be chosen for the message to be implemented. A pop-up will come up after a successful encoding that displays the encoded image.

The `decode` tab contains a *JLabel* to display the image file, *Choose PNG File* button to choose image to decode, and *Decode Now* to run the `decode` function on the image. A pop-will will come up after a successful decoding that displays the message.

#### FileChecker.java
Checks the file type for JPG/PNG

#### Controller.java
The Controller implements all of the classes above to create the program.

## Acknowledgement
This project is inspired by the Steganography Tutorial written and coded by [William Wilson](http://www.dreamincode.net/forums/topic/27950-steganography/).
