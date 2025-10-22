-- Create Database
CREATE DATABASE IF NOT EXISTS micro_investment_planner;
USE micro_investment_planner;

-- Users Table
CREATE TABLE IF NOT EXISTS Users (
    user_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('user','admin') DEFAULT 'user',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Schemes Table
CREATE TABLE IF NOT EXISTS Scheme (
    scheme_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    interest_rate DECIMAL(5,2),
    duration_months INT,
    risk_level ENUM('Low','Medium','High'),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Investments Table
CREATE TABLE IF NOT EXISTS Investment (
    investment_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    scheme_id INT NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    status ENUM('Active','Completed') DEFAULT 'Active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (scheme_id) REFERENCES Scheme(scheme_id)
);

-- Transactions Table
CREATE TABLE IF NOT EXISTS Transaction (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    investment_id INT NOT NULL,
    date DATE NOT NULL,
    type ENUM('Deposit','Withdrawal') NOT NULL,
    amount DECIMAL(10,2) NOT NULL,
    FOREIGN KEY (investment_id) REFERENCES Investment(investment_id)
);

-- Goals Table
CREATE TABLE IF NOT EXISTS Goal (
    goal_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    target_amount DECIMAL(10,2) NOT NULL,
    saved_amount DECIMAL(10,2) DEFAULT 0,
    target_date DATE NOT NULL,
    status ENUM('Active','Achieved') DEFAULT 'Active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
