# 🏪 My Shop – Java Swing + MySQL Demo App

This is a simple **Java Swing desktop application** with **MySQL integration** for demonstration purposes.  
It includes:
- A **Main Page** (`App.java`) with navigation.
- A **Register Page** (`RegisterPage.java`) where users can sign up.
- A **Login Page** (`LoginPage.java`) with authentication.
- A **Landing Page** (`LandingPage.java`) that greets logged-in users.

> ⚠️ Note: For demo purposes, passwords are stored in **plain text** (not secure).  
In a real project, always hash passwords using libraries such as BCrypt or Argon2.

---

## 📂 Project Structure

quiz/
├── lib/ # External libraries (MySQL Connector JAR)
├── src/
│ ├── db/
│ │ └── db.java # Database connection helper
│ └── ui/
│ ├── App.java # Main entry point
│ ├── LoginPage.java # Login screen
│ ├── RegisterPage.java # Registration screen
│ └── LandingPage.java # Greeting screen after login
├── .env.local # Environment file for DB credentials
└── README.md

yaml
Copy code

---

## ⚙️ Requirements

- **Java 17+** (JDK installed and added to PATH)
- **MySQL** (running locally)
- **MySQL Connector/J** (e.g., `mysql-connector-j-8.4.0.jar` in `lib/` folder)

---

## 🛠️ Setup

### 1. Create Database + Table
```sql
CREATE DATABASE myshop;
USE myshop;

CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    gmail VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
2. Configure Environment File
Create a .env.local file in project root:

ini
Copy code
DB_NAME=myshop
DB_USER=root
DB_PASS=yourpassword
3. Add MySQL Connector/J
Place the mysql-connector-j-8.x.x.jar file inside the lib/ folder.

▶️ Compile & Run
From the project root:

bash
Copy code
# Compile all sources into /out
javac -cp ".;lib/*" -d out src/db/db.java src/ui/*.java

# Run the app
java -cp ".;lib/*;out" ui.App
💻 Features
Main App Window

Shows "WELCOME TO MY SHOP!"

Buttons: Login, Register

Register Page

Fields: First Name, Last Name, Gmail, Password

Stores user data in MySQL table

Login Page

Fields: Gmail, Password

Verifies against DB

If valid → Opens LandingPage

Landing Page

Displays personalized greeting:

"Hello, Keith Mark!"

📸 Screenshots (placeholders)
App Window

Register Page

Login Page

Landing Page

🚧 Roadmap / To-Do
 Replace plain-text passwords with hashed storage (BCrypt/Argon2)

 Add session management (keep track of logged-in user)

 Improve UI with icons & styling

 Package into .jar for easy distribution

🤝 Contributing
Fork the repo

Create your feature branch (git checkout -b feature/new-feature)

Commit changes (git commit -m 'Add feature')

Push to branch (git push origin feature/new-feature)

Create a Pull Request

📜 License
This project is for educational/demo purposes only.
You are free to use, modify, and learn from it. 🚀