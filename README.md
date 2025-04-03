# Automated CV Analyzer

## Description:
The **Automated CV Analyzer** is a Java application designed to help recruiters and applicants streamline the job application process by automating the review of CVs. It eliminates the risk of human error, such as missing key requirements or failing to identify critical information in CVs. The program allows recruiters to create, edit, delete job listings, and view the applicants who have applied. Applicants can upload their CVs, receive direct feedback about their suitability for a job, and view detailed job listings.

## Features:
- **Sign Up**: Allows users to create a personal account as either an applicant or a recruiter.
- **Log In**: Enables users to log in and save their state.
- **Add Jobs**: Recruiters can create job listings.
- **Edit Jobs**: Recruiters can modify existing job listings.
- **Delete Jobs**: Recruiters can remove job listings.
- **View CVs**: Recruiters can view applicants who have applied for their posted jobs.
- **More Details**: Applicants can view additional details about the jobs they are interested in.
- **Upload CV**: Applicants can upload their CV to apply for a specific job.

## Installation:

### 1. Clone the Repository:
'''bash
git clone https://github.com/DjDesh123/Automated-CV-analyser.git
'''
### 2. install stanfordNLP and Apache Commons Text 
   https://nlp.stanford.edu/software/lex-parser.html#Download

   https://commons.apache.org/proper/commons-text/download_text.cgi

### 3. compile the program
   javac main

### 4. run the program
   java Main
   
### File Breakdown:

- **ApplicantDashboard.java** - Displays the dashboard for applicants and allows the user to decide if they want to upload their CV, see more details, or exit to the main menu.
- **CVData.java** - Stores the object that will be stored in the CVDatabase and also has the getter methods for the CV's data.
- **CVDatabase.java** - Handles everything related to the CV and the database.
- **CVDatabaseStorage.java** - Saves and loads the CV database because I used LinkedHashMap, which is volatile.
- **CVRating.java** - Rates the applicant's CV via an equation.
- **DashboardRedirector.java** - Redirects the user to the correct dashboard when they log in.
- **IntValidation.java** - Validates and checks if the user input is an integer.
- **JobData.java** - Stores the object that will be used to be stored in the JobDatabase.
- **JobDatabase.java** - Used to store the jobs uploaded by the recruiter and to handle any method that affects the job database.
- **JobDatabaseStorage.java** - Loads and saves the JobDatabase's LinkedHashMap.
- **LogInDatabase.java** - Used to store the user's login credentials with their salted password.
- **LogInPage.java** - Used to display the login screen and handle incorrect or correct credentials.
- **Main.java** - Start of the program to run everything.
- **MenuScheduler.java** - Displays the main menu while handling the redirection to the correct page.
- **NLPProcessor.java** - Uses the Stanford NLP and Apache Commons Text to check and analyze the CV and send back the information to a different method.
- **NotificationSystem.java** - Creates a push notification to be sent to the recruiter while also telling the applicant that they sent their CV.
- **NotificationSystemStorage.java** - Temporarily stores the notifications.
- **PasswordHashing.java** - Handles password hashing while also salting the password.
- **RecruiterDashboard.java** - Displays the recruiter dashboard and allows them to select either add job, edit job, delete job, view CV, or exit to the main menu.
- **ScreenManager.java** - Clears the screen for the user to provide a better UI experience.
- **SignUpPage.java** - Allows the user to sign up to the program and also handles edge cases to ensure the user enters valid information.
- **StringValidation.java** - Handles and checks if the string that the user enters is valid.
- **UserInfo.java** - Stores the object that holds the information of the user's account that is used in the LogInDatabase.




