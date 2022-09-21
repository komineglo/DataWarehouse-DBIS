/*==================================================================*/
/* Database name:  DBXXXXXXX                                        */
/* Database user:  DBXXXXXXX                                        */
/* DBMS name:      SQL-Server 2016                                  */
/* Created on:     12.04.2021                                       */
/* Creater:        Jade HS/Winf                                     */
/* Company:        Jade HS                                          */
/* Aufgabe:        Erstellt alle Tabellen                           */ 
/*==================================================================*/

/*==================================================================*/
/*                              KUNDE                               */
/*==================================================================*/
CREATE TABLE CUSTOMER
(
	CUSTOMER_ID		int				not null,
	LASTNAME		varchar(30)		not null,
	FIRSTNAME		varchar(30)		not null,
	GENDER			char(1)			not null	CHECK(GENDER = 'M' OR GENDER = 'W' OR GENDER = 'D'),
	DATE_OF_BIRTH	datetime		not null,
	STREET			varchar(30)		not null,
	POSTCODE		varchar(5)		not null,
	CITY			varchar(30)		not null,
	EMAIL			varchar(30)		not null,
	CONSTRAINT PK_CUSTOMER PRIMARY KEY (CUSTOMER_ID)
);

/*==================================================================*/
/*                          PRODUKTKATEGORIE                        */
/*==================================================================*/
CREATE TABLE PRODUCT_CATEGORY 
(
	CATEGORY_ID				varchar(5)		not null,
	CATEGORY_NAME			varchar(30)		not null,
	CATEGORY_DESCRIPTION	varchar(500)	null,
	CONSTRAINT PK_PRODUCTCATEGORY PRIMARY KEY (CATEGORY_ID)
);

/*==================================================================*/
/*                             PRODUKT                              */
/*==================================================================*/
CREATE TABLE PRODUCT
(
	PRODUCT_ID				int				not null,
	PRODUCT_NAME			varchar(100)	not null,
	PRODUCT_DESCRIPTION		varchar(500)	null,
	CATEGORY_ID				varchar(5)		not null
			REFERENCES PRODUCT_CATEGORY (CATEGORY_ID),
	MANUFACTURER			varchar(50)		null,
	PRODUCT_LENGTH			float			null,
	PRODUCT_WIDTH			float			null,
	PRODUCT_HEIGHT			float			null,
	PRODUCT_WEIGHT			float			null,
	PRODUCT_PRICE			money			not null,
	CONSTRAINT PK_PRODUCT PRIMARY KEY (PRODUCT_ID)
);

/*==================================================================*/
/*                           Versandart                             */
/*==================================================================*/
CREATE TABLE SHIPPING_METHOD
(
	SHIPPING_METHOD_ID			varchar(5)		not null,
	SHIPPING_METHOD_DESCRIPTION	varchar(30)		not null,
	SHIPPING_FEE				money			not null,
	CONSTRAINT PK_SHIPPING PRIMARY KEY (SHIPPING_METHOD_ID)
);

/*==================================================================*/
/*                           Zahlungsart                            */
/*==================================================================*/
CREATE TABLE PAYMENT_METHOD
(
	PAYMENT_METHOD_ID		varchar(5)		not null,
	PAYMENT_DESCRIPTION		varchar(30)		not null,
	CONSTRAINT PK_PAYMENT PRIMARY KEY (PAYMENT_METHOD_ID)
);

/*==================================================================*/
/*                              Status                              */
/*==================================================================*/
CREATE TABLE ORDER_ITEM_STATUS
(
	STATUS_CODE				int				not null,
	STATUS_DESCRIPTION		varchar(100)	not null,
	CONSTRAINT PK_STATUS PRIMARY KEY (STATUS_CODE)
);

/*==================================================================*/
/*                            Bestellung                            */
/*==================================================================*/
CREATE TABLE CUSTOMERORDER
(
	ORDER_ID		int				not null,
	CUSTOMER_ID		int				not null
		REFERENCES CUSTOMER (CUSTOMER_ID),
	ORDER_DATE		datetime		not null,
	SHIPPING_METHOD_ID varchar(5)	not null
		REFERENCES SHIPPING_METHOD (SHIPPING_METHOD_ID),
	PAYMENT_METHOD_ID	varchar(5)	not null
		REFERENCES PAYMENT_METHOD (PAYMENT_METHOD_ID),
	NOTE			varchar(250)	null,
	TOTAL_PRICE		money			null,
	CONSTRAINT PK_ORDER PRIMARY KEY (ORDER_ID)
);

/*==================================================================*/
/*                          Bestellposition                         */
/*==================================================================*/
CREATE TABLE ORDER_ITEM
(
	ORDER_ID			int			not null
		REFERENCES CUSTOMERORDER (ORDER_ID),
	PRODUCT_ID			int			not null
		REFERENCES PRODUCT (PRODUCT_ID),
	QUANTITY			int			not null,
	ORDER_ITEM_PRICE	money		null,
	STATUS_CODE			int			not null
		REFERENCES ORDER_ITEM_STATUS (STATUS_CODE),
	CONSTRAINT PK_ORDERITEM PRIMARY KEY (ORDER_ID, PRODUCT_ID)
)
