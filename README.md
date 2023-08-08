# team-61-03 - Object-Oriented Design

1 Problem
Writing "personal journal" is one of the oldest habits that many people, not necessarily writers, have had.
People usually write their journal at any moment of day for any occasion, and they enjoy reviewing them later.
Some people like to write their journal in "journal notebooks" but experiences show that paper is susceptible to damage by light and air.
Therefore, a software system that persists the data in database (or in files) can keep our journal forever and our descendants can enjoy reviewing their ancestors' thoughts.
There is no open-source and free personal desktop application for writing personal journals and the free web-based ones are full of annoying ads that may spy on you.
2 Vision
An open-source "personal journal" software system can solve this problem. Using Java and JAVA_FX
This software has the following features:
2.1 Authentication
1. The application shall be single user, but password protected. The user must provide their (= her/his) password to enter the application.
2. The user shall provide a default password (see the technical notes) when the application is launched for the first time, but the application shall force the user to change their password.
3. The user shall change their password by:
3.1. Providing the old password (password field)
3.2. Entering the new password (password field)
3.3. Reentering the new password for confirmation (password field)
3.4. Entering a security question (could be a text field for typing the question, or a dropdown for choosing the predefined ones)
3.5. Entering the answer to the security question
4. The user shall reset their password (forgot password) by:
4.1. Providing the answer to the security question (text field)
4.2. Entering the new password (password field)
4.3. Reentering the new password for confirmation (password field)
5. The password shall be stored in a file (or database table) as plain text. For simplicity, no encryption is required.
6. The user shall logout after successful login. The system returns to the "enter password" page/window (aka home page).
2.2 Application's Features
7. The user shall create new journal entries.
8. The user shall enter the following information to create a new journal entry:
8.1. An optional title (text field)
8.2. Current date-time (date-picker that picks the current date-time by default, but it shall be editable)
8.3. The context (main text area for writing the journal)
9. The user shall Save the journal entry or click on Cancel button to return to the home page.
10. The user shall search for a journal entry based on a substring in the context or in the title.
11. The user shall edit an old journal entry after searching for it.
12. The user shall delete an old journal entry after searching for it.
