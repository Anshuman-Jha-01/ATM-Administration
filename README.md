# ATM Administration - Java-Based ATM Simulation System

**ATM Administration** is a Java-based simulation of a basic ATM system that allows users to perform common banking operations such as withdrawals, deposits, balance inquiries, PIN updates, and more. It uses file-based storage (`Accounts.txt`, `Statement.txt`, `Warnings.txt`) to maintain user data and transaction logs.

---

## Table of Contents

- [Project Overview](#project-overview)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Project Structure](#project-structure)

---

## Project Overview

This **ATM Administration System** provides a console-based interface to simulate ATM transactions securely. The system supports multiple functionalities, such as managing user balances, logging statements, validating PINs, and recording warnings.

The application is ideal for beginners looking to explore file handling, input validation, and class-based design in Java.

---

## Features

- **Withdraw and Deposit Money**
- **Check Balance**
- **Update Card PIN**
- **View Mini Statement**
- **Log Inaccurate PIN Attempts as Warnings**
- **Administer Accounts via Text Files**

Each transaction is validated through user card ID and PIN and updates are written directly to `Accounts.txt` and `Statement.txt`.

---

## Technologies Used

- **Java SE** (Java 8+)
- **Standard Java Libraries**:
  - `java.util` (for input and collections)
  - `java.io` (for file reading/writing)
  - `java.time` (for date management)
- **File System**:
  - `Accounts.txt` – Stores user card data
  - `Statement.txt` – Logs transaction history
  - `Warnings.txt` – Logs failed PIN attempts

---

## Installation

### Prerequisites

- **Java JDK** (Version 8 or higher)
- A code editor or IDE (VS Code, IntelliJ, Eclipse)
- Terminal or command line access

### Steps to Run the Project

1. **Clone or Download the Repository**
   ```bash
   git clone https://github.com/Anshuman-Jha-01/ATM-Administration.git
   cd ATM-Administration


2. **Compile the Java Files:**
    ```bash 
    javac ATM.java Transactions.java
    

3. **Run the program:**
    ```bash
    java ATM

---

## Usage

1. **Launch Application:**
    - The console displays an interactive menu.

2. **Choose Operations:**
    - Withdraw, Deposit, Check Balance
    - Update PIN, View Mini Statement, Check Warnings

3. **Data Validation:**
    - Each action requires correct card ID and PIN.
    - Failed PIN attempts are logged in Warnings.txt.

4. **Exit:**
    - Select option 7 from the menu to exit the program.

---


## Project Structure

```bash
   /ATM Administration
    ├── ATM.java             # Main entry point and transaction menu
    ├── Transactions.java    # Handles all banking logic and file operations
    ├── Accounts.txt         # Stores card ID, PIN, and balance
    ├── Statement.txt        # Logs transaction history per user
    ├── Warnings.txt         # Logs failed login attempts or suspicious behavior
    └── README.md            # Project documentation

  ```
