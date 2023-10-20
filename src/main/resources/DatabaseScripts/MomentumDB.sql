CREATE DATABASE MomentumDB;
GO

USE MomentumDB;
GO

CREATE TABLE EQUITY_SYMBOL (
	EQ_SYMBOL_ID INT PRIMARY KEY,
	EQ_SYMBOL VARCHAR(100),
	EQ_NAME VARCHAR(100),
);
GO

CREATE TABLE DAILY_PRICE(
	DAILY_PRICE_ID INT PRIMARY KEY,
	EQ_SYMBOL_ID INT,
	CUR_DATE DATE,
	PREVIOUS_CLOSE_PRICE FLOAT,
	OPEN_PRICE FLOAT,
	HIGH_PRICE FLOAT,
	LOW_PRICE FLOAT,
	CLOSE_PRICE FLOAT,
	TRADED_VALUE FLOAT,
	TRADED_VOLUME FLOAT,
	FOREIGN KEY (EQ_SYMBOL_ID) REFERENCES EQUITY_SYMBOL(EQ_SYMBOL_ID)
);
GO

CREATE TABLE HIGH_LOW_PRICE(
	HIGH_LOW_PRICE_ID INT PRIMARY KEY,
	EQ_SYMBOL_ID INT,
	WEEK_52_HIGH FLOAT,
	WEEK_52_LOW FLOAT,
	CUR_DATE DATE,
	FOREIGN KEY (EQ_SYMBOL_ID) REFERENCES EQUITY_SYMBOL(EQ_SYMBOL_ID)
);

USE MomentumDB;
GO

CREATE TABLE NIFTY_50 (
	NIFTY_50_ID INT PRIMARY KEY,
	EQ_SYMBOL_ID INT,
	FOREIGN KEY (EQ_SYMBOL_ID) REFERENCES EQUITY_SYMBOL(EQ_SYMBOL_ID)
);
GO

CREATE TABLE NIFTY_NEXT_50 (
	NIFTY_50_ID INT PRIMARY KEY,
	EQ_SYMBOL_ID INT,
	FOREIGN KEY (EQ_SYMBOL_ID) REFERENCES EQUITY_SYMBOL(EQ_SYMBOL_ID)
);
GO

CREATE TABLE NIFTY_100 (
	NIFTY_50_ID INT PRIMARY KEY,
	EQ_SYMBOL_ID INT,
	FOREIGN KEY (EQ_SYMBOL_ID) REFERENCES EQUITY_SYMBOL(EQ_SYMBOL_ID)
);
GO

CREATE TABLE NIFTY_200 (
	NIFTY_50_ID INT PRIMARY KEY,
	EQ_SYMBOL_ID INT,
	FOREIGN KEY (EQ_SYMBOL_ID) REFERENCES EQUITY_SYMBOL(EQ_SYMBOL_ID)
);
GO

CREATE TABLE NIFTY_500 (
	NIFTY_50_ID INT PRIMARY KEY,
	EQ_SYMBOL_ID INT,
	FOREIGN KEY (EQ_SYMBOL_ID) REFERENCES EQUITY_SYMBOL(EQ_SYMBOL_ID)
);
GO

CREATE TABLE NIFTY_MIDCAP_50 (
	NIFTY_50_ID INT PRIMARY KEY,
	EQ_SYMBOL_ID INT,
	FOREIGN KEY (EQ_SYMBOL_ID) REFERENCES EQUITY_SYMBOL(EQ_SYMBOL_ID)
);
GO

CREATE TABLE NIFTY_MIDCAP_100 (
	NIFTY_50_ID INT PRIMARY KEY,
	EQ_SYMBOL_ID INT,
	FOREIGN KEY (EQ_SYMBOL_ID) REFERENCES EQUITY_SYMBOL(EQ_SYMBOL_ID)
);
GO

CREATE TABLE NIFTY_MIDCAP_150 (
	NIFTY_50_ID INT PRIMARY KEY,
	EQ_SYMBOL_ID INT,
	FOREIGN KEY (EQ_SYMBOL_ID) REFERENCES EQUITY_SYMBOL(EQ_SYMBOL_ID)
);
GO

CREATE TABLE NIFTY_SMALLCAP_50 (
	NIFTY_50_ID INT PRIMARY KEY,
	EQ_SYMBOL_ID INT,
	FOREIGN KEY (EQ_SYMBOL_ID) REFERENCES EQUITY_SYMBOL(EQ_SYMBOL_ID)
);
GO

CREATE TABLE NIFTY_SMALLCAP_100 (
	NIFTY_50_ID INT PRIMARY KEY,
	EQ_SYMBOL_ID INT,
	FOREIGN KEY (EQ_SYMBOL_ID) REFERENCES EQUITY_SYMBOL(EQ_SYMBOL_ID)
);
GO

CREATE TABLE NIFTY_SMALLCAP_150 (
	NIFTY_50_ID INT PRIMARY KEY,
	EQ_SYMBOL_ID INT,
	FOREIGN KEY (EQ_SYMBOL_ID) REFERENCES EQUITY_SYMBOL(EQ_SYMBOL_ID)
);
GO

CREATE TABLE NIFTY_SMALLCAP_250 (
	NIFTY_50_ID INT PRIMARY KEY,
	EQ_SYMBOL_ID INT,
	FOREIGN KEY (EQ_SYMBOL_ID) REFERENCES EQUITY_SYMBOL(EQ_SYMBOL_ID)
);
GO

CREATE TABLE NIFTY_SMALLCAP_400 (
	NIFTY_50_ID INT PRIMARY KEY,
	EQ_SYMBOL_ID INT,
	FOREIGN KEY (EQ_SYMBOL_ID) REFERENCES EQUITY_SYMBOL(EQ_SYMBOL_ID)
);
GO
delete from DAILY_PRICE;
DBCC CHECKIDENT (DAILY_PRICE, RESEED, 0);

delete from HIGH_LOW_PRICE;
DBCC CHECKIDENT (HIGH_LOW_PRICE, RESEED, 0);

delete from EQUITY_SYMBOL;
DBCC CHECKIDENT (EQUITY_SYMBOL, RESEED, 0);

select count(*) from daily_price;

select count(*) from EQUITY_SYMBOL;

select count(*) from HIGH_LOW_PRICE;