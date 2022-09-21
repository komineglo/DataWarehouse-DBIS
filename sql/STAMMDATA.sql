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
('PM4', 'Sofortüberweisung');

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
('P1', 'Bauen', 'Baustoffe, Laminat, Fliesen, Türen, Treppen und Geländer, Fenster, Garagen und Carports, Vordächer');

INSERT INTO PRODUCT_CATEGORY VALUES
('P2', 'Garten und Freizeit', 'Gewächshäuser, Gartengeräte und Maschinen, Grills, Gartenmöbel, Pflanzen, Markisen und Sonnenschutz, Zäune und Mauern');

INSERT INTO PRODUCT_CATEGORY VALUES
('P3', 'Technik', 'Elektrowerkzeuge, Kamine und Öfen, Sicherheit und Haustechnik, Klima und Ventilatoren, Werkstatt, Smart Home');

INSERT INTO PRODUCT_CATEGORY VALUES
('P4', 'Wohnen', 'Lampen und Leuchten, Möbel, Haushalt, Aufbewahrung und Ordnung, Tapeten, Farben und Zubehör, Bodenbeläge, Wohntextilien, Dekoartikel');

INSERT INTO PRODUCT_CATEGORY VALUES
('P5', 'Küche', 'Küchenzeilen, Küchenmöbel, Dunstabzugshauben, Elektrogeräte, Spülen und Spülbecken, Küchenarmaturen, Arbeitsplatten');

INSERT INTO PRODUCT_CATEGORY VALUES
('P6', 'Bad', 'Badmöbel, Spiegel, Duschen, Badewannen, Badarmaturen, Toiletten, Waschbecken, Badtextilien, Badaccessoires');

COMMIT;


--SELECT * FROM PRODUCT_CATEGORY
--SELECT * FROM SHIPPING_METHOD
--SELECT * FROM PAYMENT_METHOD
--SELECT * FROM ORDER_ITEM_STATUS
