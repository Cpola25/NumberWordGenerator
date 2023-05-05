# NumberWordGenerator
Software Engineering Project: NumberPhrase

### Team:

Role  | Name
------------- | -------------
Full-Stack:   | Lizbeth Trujillo
Front-end:  | Jessica Rodriguez
Database:  | Victoria Kitchen
Test Automation: | Nicolas Victoria

### Description:

This project aims to design and implement a software solution that finds several English words that best represent a company, given a phone number. For instance, 409-880-7011 could correspond to any word from the cartesian product {g, h, i} ⨯ {} ⨯ {w, x, y, z} ⨯ {t, u, v} ⨯ {t, u, v} ⨯ {} ⨯ {p, q, r, s} ⨯ {_} ⨯ {/, ., @} ⨯ {/, ., @}.
 The deliverables for this project will include a use-case diagram, class diagram, interaction diagram, state chart, and activity diagram. 

### Features:

- Interactive UI
- Result storage
- Help Screen
- Result Storage
- Extensive Database

### Technologies Used: 

- Implemented using Java
- UI implemented using JavaFx
- (whatever test automation software we use)

### Getting Started:


To get started with our project NumberPhrase, please follow these steps:

#### Installation

- Clone the project from the GitHub repository
- Install Java and any necessary dependencies

#### Usage

- Run the application using your preferred Java IDE or through the command line
- Follow the menu-driven interface to enter a phone number and generate a list of possible English words to represent the number
  - Phone Number Entry requirements:
    1. Must contain a 10 digit phone number {e.g. 8552671029}
    2. Must not contain letters
    3. Must not contain special characters {e.g. %, ^, -, =,...}
    4. Must not contain spaces, commas, or periods
- If you get more than 10 results, you will be prompted to select your favorite and narrow down your top choices. But don't worry all your results will be saved so you can go back later!
- After each number you submit your results should be logged in a unique text file in the Results folder along with a date and timestamp
- For an example output you can find results for entering the number 4093392255 under the Results folder in the Number subFolder
- Note as we are still expanding our database, your number may not have any words. See Project report for more information on limitations. 

#### Bulkloading
- If you want to parse more than one input at a time go to the BulkLoading Folder and modify the BulkLoadingDocument.txt to include all of your desired numbers. 
- Numbers should only be separated by a space and follow the Phone Number Entry requirements listed above. 
- Next, run the main class located in AutomatedTesting.java instead of the Application Manager
- Results will be appended to the testingLog.txt located in Results folder under Tests. 
#### Documentation

- The project includes a use-case diagram, class diagram, interaction diagram, state chart, and activity diagram to help understand its design and functionality.
- Any assumptions or limitations made while developing the models are documented in the report.
- Black-box testing has been performed to ensure the software is functioning as expected

#### Data Persistence

Data persistence has been achieved using the Java file system and object serialization, ensuring that data is saved and can be retrieved even after the program is closed.
We hope that this software will be useful for anyone looking to find eye-catching words to represent their phone numbers. If you have any questions or feedback, please feel free to contact us.
