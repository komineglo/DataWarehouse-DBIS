/*==================================================================*/
/* Database name:  DBXXXXXXX                                        */
/* Database user:  DBXXXXXXX                                        */
/* DBMS name:      SQL-Server 2016                                  */
/* Created on:     12.04.2021                                       */
/* Creater:        Jade HS/Winf                                     */
/* Company:        Jade HS                                          */
/* Aufgabe:        Anlegen der Anwendungsstammdate                  */ 
/*==================================================================*/

BEGIN TRANSACTION

--Bestellpositionsstatus:
INSERT INTO ORDER_ITEM_STATUS VALUES
(1, 'Position aufgegeben');

INSERT INTO ORDER_ITEM_STATUS VALUES
(2, 'Position storniert');

INSERT INTO ORDER_ITEM_STATUS VALUES
(3, 'Position versendet');

INSERT INTO ORDER_ITEM_STATUS VALUES
(4, 'Position retourniert');


--Zahlungsarten:
INSERT INTO PAYMENT_METHOD VALUES
('PM1', 'PayPal');

INSERT INTO PAYMENT_METHOD VALUES
('PM2', 'Vorkasse');

INSERT INTO PAYMENT_METHOD VALUES
('PM3', 'Rechnung');

INSERT INTO PAYMENT_METHOD VALUES
('PM4', 'Sofort�berweisung');

INSERT INTO PAYMENT_METHOD VALUES
('PM5', 'Gutscheinkarte');


--Versandarten:
INSERT INTO SHIPPING_METHOD VALUES
('SP1', 'Standardversand', 5.99);

INSERT INTO SHIPPING_METHOD VALUES
('SP2', 'Premiumversand', 7.99);

INSERT INTO SHIPPING_METHOD VALUES
('SP3', 'Expressversand', 15.99);


--Produktkategorien:
INSERT INTO PRODUCT_CATEGORY VALUES
('P1', 'Bauen', 'Baustoffe, Laminat, Fliesen, T�ren, Treppen und Gel�nder, Fenster, Garagen und Carports, Vord�cher');

INSERT INTO PRODUCT_CATEGORY VALUES
('P2', 'Garten und Freizeit', 'Gew�chsh�user, Gartenger�te und Maschinen, Grills, Gartenm�bel, Pflanzen, Markisen und Sonnenschutz, Z�une und Mauern');

INSERT INTO PRODUCT_CATEGORY VALUES
('P3', 'Technik', 'Elektrowerkzeuge, Kamine und �fen, Sicherheit und Haustechnik, Klima und Ventilatoren, Werkstatt, Smart Home');

INSERT INTO PRODUCT_CATEGORY VALUES
('P4', 'Wohnen', 'Lampen und Leuchten, M�bel, Haushalt, Aufbewahrung und Ordnung, Tapeten, Farben und Zubeh�r, Bodenbel�ge, Wohntextilien, Dekoartikel');

INSERT INTO PRODUCT_CATEGORY VALUES
('P5', 'K�che', 'K�chenzeilen, K�chenm�bel, Dunstabzugshauben, Elektroger�te, Sp�len und Sp�lbecken, K�chenarmaturen, Arbeitsplatten');

INSERT INTO PRODUCT_CATEGORY VALUES
('P6', 'Bad', 'Badm�bel, Spiegel, Duschen, Badewannen, Badarmaturen, Toiletten, Waschbecken, Badtextilien, Badaccessoires');

COMMIT;


--SELECT * FROM PRODUCT_CATEGORY
--SELECT * FROM SHIPPING_METHOD
--SELECT * FROM PAYMENT_METHOD
--SELECT * FROM ORDER_ITEM_STATUS
