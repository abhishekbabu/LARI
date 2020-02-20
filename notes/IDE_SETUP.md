# Setting up the development environment

### 1. Download IntelliJ IDEA (Community / Ultimate edition)
We are developing LARI in IntelliJ IDEA as it is an industry standard IDE that is also used by many CSE classes at UW.
Feel free to use a different IDE if you prefer.  
As students at UW, we have access to the Ultimate edition [here](https://www.jetbrains.com/student/). The Community
edition is still fine.

### 2. Create a GitHub account / get yourself added to the repo as a collaborator
You will need a GitHub account to work on the application and make changes. Once you have created your account, let the
lead software engineer of the lab know so that they can add you to the repo as a collaborator.

### 3. Clone the LARI repo from GitHub
- When you open IntelliJ for the first time, you will have to go through a setup process. Feel free to leave everything
in the default settings.
- Then, you will be greeted by a "Welcome" page that will allow you to create/clone/open projects. Click the "Get from
Version Control" button.
- On the next page, you will find a text field where you can enter a repository URL and select a directory for the repo
to be stored in. On the GitHub page of the repo, click the green button that says "Clone or download" and copy the link
shown.
- Paste this link in the field, select the directory you want to store this repo in and click "Clone".
- When IntelliJ asks if you would like to create an IntelliJ IDEA project, click "Yes".
- This will bring you to a series of windows titled "Import Project". Leave everything as is and click "Next" on every
window and then finally, click "Finish".

Now, IntelliJ will open up the project and you should be able to view all the files in the repo.

### 4. Build and run the application
Now, you can click the green play button on the top menu bar to run the application.  
If it asks you to select a configuration, click "Cancel". Then, navigate to
`\LARI\src\main\java\views\Tracker\Tracker.java`. In this file, you should see a green play button next to the line
number of the class declaration i.e. the line that says `public class Tracker ...`. Click the green play button to run
the application. This will also set the configuration for the green play button in the top menu bar for future use.  
Perfect! Now you should be able to modify code and see your changes in action in the application.

### Written by
Author: Abhishek Babu