USE DB6034059



/*		die Datensätze der Tabelle Fact_Overview müssen zum Erst entfernt werden, 
		wenn die Angaben anderer Tabellen gelöscht werden würde, weil
		 die Tabelle Fact_Overview enthält Primärschlussel, der aus Fremdschlussel aus die Tabellen_Dimensionen gebildet ist. 
		 und zusätzlich enthält die Tabelle Dim_Customerorder auch Fremdschlüssel 
		 aus die Tabellen Flake_Shipping_Method und Flake_Payment_Method und Dim_Product 
		 enthält Fremdschlüssel aus Flake_Product_Category.
 */
DELETE FROM FACT_OVERVIEW
/*		die Datensätze von Dimension Tabellen dürfen unabhängig von einander gelöscht 
		werden ohne auf die Reihenfolge zu beachten.
		vorausgesetzt, die Datensätze der Tabelle Dim_Product müssen vor den Datensätze der 
		Tabelle Flake_Produkt_Category entfernt werden, um auch die Konflikt mit
		der Reference-Einschränkung zu vermeiden.
		darüber hinaus müssen die Datensätze der Tabelle Dim_Customerorder vor den Datensätze 
		der Tabellen Flake_Shipping_Methode bzw. Flake_Payment_Method
		entfernt werden, um auch die Konflikt mit der Reference-Einschränkung zu vermeiden.
*/
DELETE FROM DIM_PRODUCT
DELETE FROM DIM_CUSTOMERORDER
DELETE FROM DIM_CUSTOMER
DELETE FROM DIM_TIME
/*		die Angaben von Flake Tabellen dürfen unabhängig von einander gelöscht werden ohne auf 
		die Reihenfolge zu beachten.
		vorausgesetzt nach dem Entfernen der Datensätze der Fakttabellen und Dimensiontabellen.
*/
DELETE FROM FLAKE_PRODUCT_CATEGORY
DELETE FROM FLAKE_SHIPPING_METHOD
DELETE FROM FLAKE_PAYMENT_METHOD
/*		Bei dem Einfügen der Datensätze in der Tabelle muss auf die Reihenfolge geachtet werden
		die Datensätze der Flake Tabellen dürfen wie bei dem Löschen wie gewünscht eingefügt werden.
		die Datensätze der Dimensionen dürfen auch unabhängig voneinander eingefügt werden, vorausgesetzt
		nach dem Einfügen der Datensätze der für die Dimension relevant sind, sonst werden die Angaben 
		nicht übernommen.
		anloge für Fakttabelle nach dem Einfügen der Datensätze in der Tabellen "Flaken" und "Dimensionen"
*/

/*		die Datensätze für Tabelle Flake_Product_Category eingeben

		bei Tabelle Flake_Product_Category wurden für jede Kategorie oder eine Gruppe von Kategorien eine 
		feste prozentualle Anteil in einer separaten Spalten gegeben, die als erzielte Gewinnanteil jeder
		Produktkategorie bzw. der Umsatzsteueranteil fixiert wurden, und werden mit dessen Wert der erzielte 
		Gewinn einzelnes Produkts bzw. der Nettopreis in Tabelle Dim_Product bestimmt.
		Im Einsatz kommen folgende Regel beim Bestimmen des prozentuallen Gewinnanteils der Produktkategorie, 
		Berechnen des Umsatzes sowie Ableiten des Gewinnes.
		1. Achten auf die finanzielle Lage der Nachgefragter und deren Bedarf zum Beispiel ein niedriger 
		Gewinnanteil 5 % für Lebensmittel und Getränke-Kategorie 'P10', aber hoher Gewinnanteil 20 % für 
		Bekleidung und Accessoires- bzw. Beauty und Drogeriekategorie 'P8' bzw. 'P9'. Darüber hinaus wurde
		die Umsatzsteueranteil nährungsweise zur Regelsteuersatz angepasst.
		2. Zur Berechnen des Umsatzes wurde die Umsatzsteueranteil von Bruttopreis des Produkts abgezugen 
		"die Ergbnisse sind als Produkt_Netto_Preis im Tabelle Dim_Product als Attribut bezeichnet" und mit
		dem bestellten Menge multipliziert.
		3. Gewinn wurde als ein fixierter Anteil je nach Produktkategorie wegen fehlende Information über
		Produktkosten berechnet.
		4. Versandkosten ist beim Berechnen des Gesamtkosten der Bestellung in allen Datensätze der Bestellungstabelle
		nicht berücksichtigt, wurde bei der Erweiterung der Datenbestand der Bestellungstabelle auch nicht berücksichtigt,
		und haben keine direkte Auswerkung auf Umsatz oder Gewinn aber indirekt, wenn ein Artikel retourniert wird, 
		genauer zu sagen, wenn die Produkte, die einer Bestellung durch Bestellposition zugeordnet sind, retourniert werden------------------------------------------------------------------

*/

INSERT INTO FLAKE_PRODUCT_CATEGORY (CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION, PROZENTUALLE_MARGE, UMSATZSTEUER_ANTEIL)
		SELECT CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION, 0.05, 1.07
		FROM PRODUCT_CATEGORY
		WHERE CATEGORY_ID IN ( 'P10' , 'P11' )

INSERT INTO FLAKE_PRODUCT_CATEGORY (CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION, PROZENTUALLE_MARGE, UMSATZSTEUER_ANTEIL)
		SELECT CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION, 0.08 , 1.19
		FROM PRODUCT_CATEGORY
		WHERE CATEGORY_ID IN ('P4' , 'P5', 'P6')

INSERT INTO FLAKE_PRODUCT_CATEGORY (CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION, PROZENTUALLE_MARGE, 
									UMSATZSTEUER_ANTEIL)
		SELECT CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION, 0.10 , 1.19
		FROM PRODUCT_CATEGORY
		WHERE CATEGORY_ID IN ('P3' , 'P7')

INSERT INTO FLAKE_PRODUCT_CATEGORY (CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION, PROZENTUALLE_MARGE,
									UMSATZSTEUER_ANTEIL)
		SELECT CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION, 0.15 , 1.19
		FROM PRODUCT_CATEGORY
		WHERE CATEGORY_ID IN ('P1' , 'P2')


INSERT INTO FLAKE_PRODUCT_CATEGORY (CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION, PROZENTUALLE_MARGE, 
									UMSATZSTEUER_ANTEIL)
		SELECT CATEGORY_ID, CATEGORY_NAME, CATEGORY_DESCRIPTION, 0.20 , 1.19
		FROM PRODUCT_CATEGORY
		WHERE CATEGORY_ID IN ('P8' , 'P9')

--======================================================================

-- die Angaben von Versandmethode in die Tabelle Flake_Shipping_Method  werden eingefuegt

INSERT INTO FLAKE_SHIPPING_METHOD (SHIPPING_METHOD_ID, SHIPPING_METHOD_DESCRIPTION, SHIPPING_FEE)
		SELECT SHIPPING_METHOD_ID, SHIPPING_METHOD_DESCRIPTION, SHIPPING_FEE
		FROM SHIPPING_METHOD

--======================================================================
	
--die Angaben von Zahlungsmethod in die Tabelle Flake_Payment_Method werden eingefuegt
INSERT INTO FLAKE_PAYMENT_METHOD (PAYMENT_METHOD_ID, PAYMENT_DESCRIPTION)
		SELECT PAYMENT_METHOD_ID, PAYMENT_DESCRIPTION
		FROM PAYMENT_METHOD

--========================================================================
--die Angaben von Dim_Product Tabelle werden eingefuegt
INSERT INTO DIM_PRODUCT (PRODUCT_ID, PRODUCT_NAME, PRODUCT_DESCRIPTION, CATEGORY_ID, MANUFACTURER, PRODUCT_LENGTH,
						PRODUCT_WIDTH, PRODUCT_HEIGHT, PRODUKT_BRUTTO_PREIS, PRODUKT_NETTO_PREIS, 
						ERZIELTE_GEWINN_PRO_PRODUKT)
		SELECT P.PRODUCT_ID, PRODUCT_NAME, P.PRODUCT_DESCRIPTION, P.CATEGORY_ID, P.MANUFACTURER, P.PRODUCT_LENGTH, 
				P.PRODUCT_WIDTH, P.PRODUCT_HEIGHT, P.PRODUCT_PRICE, P.PRODUCT_PRICE / FPC.UMSATZSTEUER_ANTEIL, 
				(P.PRODUCT_PRICE / FPC.UMSATZSTEUER_ANTEIL) * FPC.PROZENTUALLE_MARGE
		FROM PRODUCT P, FLAKE_PRODUCT_CATEGORY FPC
		WHERE P.CATEGORY_ID = FPC.CATEGORY_ID

--======================================================================

--Angeben der Kunden werden eingefuegt

INSERT INTO DIM_CUSTOMER (CUSTOMER_ID, LASTNAME, FIRSTNAME, GENDER, DATE_OF_BIRTH, STREET, POSTCODE, CITY, EMAIL)
		SELECT CUSTOMER_ID, LASTNAME, FIRSTNAME, GENDER, DATE_OF_BIRTH, STREET, POSTCODE, CITY, EMAIL
		FROM CUSTOMER

--========================================================================

--Angaben von Dimession Bestellungen werden eingefuegt

INSERT INTO DIM_CUSTOMERORDER (ORDER_ID, CUSTOMER_ID, ORDER_DATE, SHIPPING_METHOD_ID, PAYMENT_METHOD_ID, NOTE, TOTAL_PRICE)
		SELECT ORDER_ID, CUSTOMER_ID, ORDER_DATE, SHIPPING_METHOD_ID, PAYMENT_METHOD_ID, NOTE, TOTAL_PRICE
		FROM CUSTOMERORDER


--=============================================================

-- Zeitangaben werden eingefuegt

INSERT INTO DIM_TIME (DATUM, MONAT, QUARTAL, JAHR)
		SELECT DISTINCT ORDER_DATE, MONTH(ORDER_DATE), DATEPART(QUARTER , ORDER_DATE), YEAR(ORDER_DATE)
		FROM CUSTOMERORDER


--==========================================================



INSERT INTO FACT_OVERVIEW (CUSTOMER_ID, PRODUCT_ID, ORDER_ID, ZEITNUMMER,
							QUANTITY, UMSATZ, GEWINN, ANZAHL_RETOURNIERT, ANZAHL_STORNIERT)

SELECT  CO.CUSTOMER_ID, OI.PRODUCT_ID ,OI.ORDER_ID, DT.ZEITNUMMER, 
		SUM(CASE WHEN OI.STATUS_CODE = 1 OR OI.STATUS_CODE = 3 THEN OI.QUANTITY ELSE 0 END)  AS QUANTITY,
		SUM(CASE WHEN OI.STATUS_CODE = 1 OR OI.STATUS_CODE = 3 THEN DP.PRODUKT_NETTO_PREIS * OI.QUANTITY ELSE 0 END)  AS UMSATZ, 
		SUM(CASE WHEN OI.STATUS_CODE = 1 OR OI.STATUS_CODE = 3 THEN DP.ERZIELTE_GEWINN_PRO_PRODUKT * OI.QUANTITY ELSE 0 END) AS GEWINN,
		SUM(CASE WHEN OI.STATUS_CODE = 4 THEN OI.QUANTITY ELSE 0 END) AS ANZAHL_RETOURNIERT,
		SUM(CASE WHEN OI.STATUS_CODE = 2 THEN OI.QUANTITY ELSE 0 END) AS ANZAHL_STORNIERT

FROM	 DIM_PRODUCT DP ,DIM_TIME DT,  ORDER_ITEM OI, CUSTOMERORDER CO

WHERE					
						 CO.ORDER_ID = OI.ORDER_ID
				AND		 DP.PRODUCT_ID = OI.PRODUCT_ID
				AND		 DT.DATUM = CO.ORDER_DATE

GROUP BY CO.CUSTOMER_ID, OI.PRODUCT_ID, DT.ZEITNUMMER, OI.ORDER_ID

--=======================================================================


--SELECT * FROM FACT_OVERVIEW
--SELECT * FROM FLAKE_PRODUCT_CATEGORY;
--SELECT * FROM FLAKE_SHIPPING_METHOD;
--SELECT * FROM FLAKE_PAYMENT_METHOD;
--SELECT * FROM DIM_CUSTOMER;
--SELECT * FROM DIM_CUSTOMERORDER;
--SELECT * FROM DIM_PRODUCT;
--SELECT * FROM DIM_TIME;

--============================================================================









