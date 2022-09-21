USE DB6034059


DROP PROCEDURE IF EXISTS DW_QUARTALAUSWERTUNG_BESTELLUNG;
DROP PROCEDURE IF EXISTS DW_QUARTALAUSWERTUNG;

/**
 * Diese Prozedur liefert die Auswertung für einen Quartal eines Jahres.
 * Wenn Sie Auswertung für alle Kunden, Artikelnummer oder Produktkategorie
 * ausführen möchten, wird an der Stelle der Wert -100 erwartet.  
 * Z.B. bei der Übergabe der Kundennummer -100 werde alle Kunden ausgewählt. 
 * Dies gilt analog für Artikelnummer oder Produktkategorie.
 * Falls die Ergebnisse eines Quartals ausgeschlossen werden sollen, 
 * muss die Zahl für den Quartal außerhalb des Zahlenbereichs 1-4 liegen.
 * Z.B. wenn die Zahl 5 für Quartal 1 übergeben wird, wird der Quartal aus der
 * Auswertung ausgeschlossen. Bei der Zahl 1 für Quartal 1 wird der Quartal berücksichtigt. 
 */
	


CREATE PROCEDURE DW_QUARTALAUSWERTUNG_BESTELLUNG

	@SHIPPING_METHOD_ID		VARCHAR(5),
	@PAYMENT_METHOD_ID		VARCHAR(5),
	@JAHR					INT,
	@QUARTAL1				INT,
	@QUARTAL2				INT,
	@QUARTAL3				INT,
	@QUARTAL4				INT,
	@ANZAHL_BESTELLUNG		INT OUT

AS

	SELECT		@ANZAHL_BESTELLUNG = COUNT(DISTINCT FO.ORDER_ID)

	FROM		 DBO.DIM_TIME DT, DBO.FACT_OVERVIEW FO, DIM_CUSTOMERORDER CO
	WHERE		JAHR = @JAHR
				/*Wenn Quartal ausgewaehlt ist, dann
				wird der Quartal ausgewertet.
				Wenn für die Quartal eine Zahl > 4 übergeben wird,
				dann wird der Quartal ignoriert.
				Somit werden nur die Quartale in die Auswertung
				einbezogen, die einen gültigen Wert besitzen.
				*/
				AND (QUARTAL = @QUARTAL1	OR	QUARTAL = @QUARTAL2
					OR QUARTAL = @QUARTAL3 OR QUARTAL = @QUARTAL4)
				AND (CO.SHIPPING_METHOD_ID = @SHIPPING_METHOD_ID OR @SHIPPING_METHOD_ID = '')
				AND (CO.PAYMENT_METHOD_ID = @PAYMENT_METHOD_ID OR @PAYMENT_METHOD_ID = '')
				AND FO.ORDER_ID = CO.ORDER_ID
				AND FO.ZEITNUMMER = DT.ZEITNUMMER

GRANT EXECUTE ON DW_QUARTALAUSWERTUNG_BESTELLUNG TO PUBLIC;


DECLARE @ANZAHL_BESTELLUNG INT
		BEGIN
			EXECUTE DB6034059.DBO.DW_QUARTALAUSWERTUNG_BESTELLUNG 'ALLE VERSANDARTEN', 'ALLE ZAHLUNGSARTEN', 2020, 1, 2 ,3 , 4,
					@ANZAHL_BESTELLUNG
					SELECT @ANZAHL_BESTELLUNG
					-- Interaktives Auslesen von Prozedureergebnissen
					SELECT * FROM FACT_OVERVIEW

		END

--==============================================================

CREATE PROCEDURE DW_QUARTALAUSWERTUNG

	@CUSTOMER_ID			INT,
	@PRODUCT_ID				INT,
	@CATEGORY_ID			VARCHAR(5),
	@JAHR					INT,
	@QUARTAL1				INT,
	@QUARTAL2				INT,
	@QUARTAL3				INT,
	@QUARTAL4				INT,
	@QUANTITY				INT OUT,
	@UMSATZ					MONEY OUT,
	@GEWINN					MONEY OUT,
	@ANZAHL_RETOURNIERT		INT OUT,
	@ANZAHL_STORNIERT		INT OUT

AS
	SELECT		 @QUANTITY = SUM(FO.QUANTITY),@UMSATZ = SUM(FO.UMSATZ), @GEWINN = SUM(FO.GEWINN), 
				 @ANZAHL_RETOURNIERT = SUM(FO.ANZAHL_RETOURNIERT), @ANZAHL_STORNIERT = SUM(FO.ANZAHL_STORNIERT)
	FROM		 DBO.FACT_OVERVIEW FO, DBO.DIM_PRODUCT DP, DBO.DIM_TIME DT
	WHERE			 JAHR = @JAHR
			/*Wenn Quartal ausgewaehlt ist, dann
			wird der Quartal ausgewertet.
			Wenn für die Quartal eine Zahl > 4 übergeben wird,
			dann wird der Quartal ignoriert.
			Somit werden nur die Quartale in die Auswertung
			einbezogen, die einen gültigen Wert besitzen.
			*/
				AND (QUARTAL = @QUARTAL1	OR	QUARTAL = @QUARTAL2
					OR QUARTAL = @QUARTAL3 OR QUARTAL = @QUARTAL4)
				AND	(FO.CUSTOMER_ID = @CUSTOMER_ID OR @CUSTOMER_ID = -100)
				AND	(FO.PRODUCT_ID = @PRODUCT_ID OR @PRODUCT_ID = -100)
				AND (DP.CATEGORY_ID = @CATEGORY_ID OR @CATEGORY_ID = '')
				AND	(FO.ZEITNUMMER = DT.ZEITNUMMER)
				AND	(FO.PRODUCT_ID = DP.PRODUCT_ID)
				
				

-- Rechte für den Zugriff auf die Funktion erteilen:
GRANT EXECUTE ON DW_QUARTALAUSWERTUNG TO PUBLIC;

-- Beginn-Block
DECLARE @QUANTITY INT , @UMSATZ MONEY, @GEWINN MONEY, @ANZAHL_RETOURNIERT INT, @ANZAHL_STORNIERT INT

		BEGIN
		-- Interaktives Ausführen der Prozedure
			EXECUTE DB6034059.dbo.DW_QUARTALAUSWERTUNG -100, -100, '', 2020, 1, 2, 3, 4, @QUANTITY,
					@UMSATZ, @GEWINN, @ANZAHL_RETOURNIERT, @ANZAHL_STORNIERT
			SELECT @QUANTITY, @UMSATZ, @GEWINN, @ANZAHL_RETOURNIERT, @ANZAHL_STORNIERT
		-- Interaktives Auslesen von Prozedureergebnissen
			SELECT * FROM FACT_OVERVIEW
		END
-- End-Block



