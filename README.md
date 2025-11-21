QuickChat Application (JAVA,NetBeans,ANT)

QuickChat is a messaging system that allows users to register, log in, and send messages with various functionalities such as storing, disregarding, and displaying sent messages.
It also includes a message manager that handles storing messages in a JSON file, displaying message details, and performing various search and report operations.


Part 1: Registration and Login Feature

In this section, I implemented a user registration and login system for the application. The key features include:

User Registration:

1. Users are required to input a username, password, and South African cell phone number.

The system checks if:

1. The username contains an underscore (_) and is no longer than five characters.

2. The password meets the required complexity (at least 8 characters, one capital letter, one number, and one special character).

3.The cell phone number contains a valid South African international code (e.g., +27 followed by 9 digits).

4.Appropriate feedback is provided for each step of the registration process, whether successful or erroneous.

Login Feature:

1.Users can log in using the credentials they registered with. The system verifies that the username and password match the stored values and provides a success or failure message accordingly.

2.This task was designed to introduce core concepts such as string manipulation, user input validation, and basic decision structures in Java.

Part 2: Sending Messages

3.In this section, I added functionality to send and manage messages after users have successfully logged in:

Main Menu:

1.Once logged in, users are greeted with a Welcome message and presented with a numeric menu to:

2.Send Messages.

3.Show Recently Sent Messages (still under development).

4.Quit the application.

Message Entry:

1.Users can input the number of messages they want to send, up to a limit of 10.

Each message includes:

1. A unique message ID.

2.Recipient's cell number.

3. Message content, with a check for messages exceeding 250 characters.

4.A message hash generated from the message details (ID, number, first and last word).

Message Actions:

1. After entering message details, the user can choose whether to send, discard, or store the message for later.

2. The system accumulates the total number of messages sent and displays this total once the task is complete.

3. This part introduced working with loops, arrays, and file handling (storing messages as JSON).

Part 3: Storing Data and Displaying Reports

1. In this final part, I expanded the functionality to store, manipulate, and display detailed message data:

Message Storage:

1. I created arrays to store different types of messages: sent, disregarded, and stored.

I implemented features to:

1.Display the sender and recipient of all sent messages.

2.Find and display the longest sent message.

3.Search messages by message ID or by recipient.

4.Delete a message using its message hash.

5.Generate a full report listing all sent messages with their details (Message ID, Hash, Recipient, Message).

Data File Handling:

1.I utilized JSON to store and load message data. The messages are serialized into JSON format and saved, which can be read back into the application as needed.

Unit Testing:

1.Comprehensive unit tests were written to verify the correctness of each method, particularly those handling message generation, validation, and storage
