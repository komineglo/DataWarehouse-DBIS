use db6034059


-- Quelle für Produktkategorien https://www.amazon.de/
INSERT INTO PRODUCT_CATEGORY VALUES
('P7', 'Spielzeuge und Baby', 'Spielzeug , Baby , Babybekleidung und Babyschuhe , Brettspiele , Baby-Wunschliste , Amazon Family , Kinder-Wunschzettel');

INSERT INTO PRODUCT_CATEGORY VALUES
('P8', 'Bekleidung und Accessoires', 'Amazon Fashion , Damen , Herren , Mädchen , Jungen , Baby , Koffer,Ruchsäcke und Taschen , The Drop , Fashion-Angebote , Prime Wardrobe');

INSERT INTO PRODUCT_CATEGORY VALUES
('P9', 'Beauty und Drogerie', 'Beauty , Premium Beauty , Männerpflege , Sanitätshaus');

INSERT INTO PRODUCT_CATEGORY VALUES
('P10', 'Lebensmittel und Getränke', 'Lebensmittel und alkoholfreie Getränke , Bier, Wein und Spirituosen , Bio und Clean Eating , Sonderangebote , Spar-Abo');

INSERT INTO PRODUCT_CATEGORY VALUES
('P11', 'Bücher', 'Alle Bücher , Kindle eBooks , Kindle Unlimited , Prime Reading , Fremdsprachige Bücher , Fachbücher , Schulbücher und Lernhilfen , Hörbücher');

--select * from PRODUCT_CATEGORY 

----------------------------------------------------------------------------------------------------------------------------

--  Quelledaten für Produkte https://www.amazon.de/ der Product_id sind von mir gegeben, wenn ich keine Artikelnummer oder Modullnummer gefunden habe.

INSERT INTO PRODUCT VALUES
(980022, 'Grafix Vier Gewinnt Gesellschaftsspiel', 'Der Klassiker für 2 Spieler, Strategiespiel für grenzenlosen Spielspaß', 'P7', 'Arts & Crafts Trading', 6, 28, 30, 100, 14.95);

INSERT INTO PRODUCT VALUES
(980023, 'Game Off Celebrities', 'In der Box verstecken sich 120 Karten mit einzigartigen Herausforderungen', 'P7', 'Captain Fats', 10.4, 10.4, 3.5, 260, 13.99);

INSERT INTO PRODUCT VALUES
(980024, 'AEVOR Daypack', 'erweiterbarer Rucksack, wasserfest, ergonomisch, Laptopfach, Farbe: Proof Lume - Blau', 'P8', 'flac Shop', 14, 33, 48, null, 98.74);

INSERT INTO PRODUCT VALUES
(980025, 'Yidarton Unisex Kappe Outdoor Baseball Cap', 'Verstellbar Erwachsenen Mütze Casual Cool Mode Baseballmütze Hip Hop Flat Hüte , Farbe: 01Schwarz', 'P8', 'DWangTong', null, null, null, null, 10.19);

INSERT INTO PRODUCT VALUES
(980026, 'Joop! Go! Eau de Toilette ', 'Joop! Go ist ein dynamischer Duft für außergwöhnliche Typen, die das abenteuerlustige Leben lieben.', 'P9', 'Joop!, Verkauf und Versand durch Amazon', 15.24, 12.7, 10.16, 305, 19.50);

INSERT INTO PRODUCT VALUES
(81441, 'NIVEA Classic Mild pflegendes Haarshampoo', ' Shampoo mit wertvollem Baumwollsamen-Öl – reinigt besonders mild, Das NIVEA Classic Mild Shampoo pflegt das Haar sanft, ohne es dabei zu beschweren', 'P9', 'NIVEA', 6.6, 3.7, 19.5, 260, 2.25);

INSERT INTO PRODUCT VALUES
(980027, 'Hefeflocken', '1 kg nutritional yeast Melasse edel Hefeflocken - ideal für Vegane Käsesoßen (1 kg)', 'P10', 'Bäckerei Spiegelhauer', 38.6, 25.2, 12.8, 1000, 18.49);

INSERT INTO PRODUCT VALUES
(980028, 'GymQueen Drops', 'Tasty Drops mit Vitaminen und Extrakten aus Guarana und Pfeffer, Flavour Drops mit Energy-Boost, Kalorienfrei, Zuckerfrei und Fettfrei ', 'P10', 'RSH Functional Food GmbH', 3.4, 3.4, 10.6, 65, 12.99);

INSERT INTO PRODUCT VALUES
(980029, 'SQL: Handbuch für Einsteiger', 'Der leichte Weg zum SQL-Experten,  (Deutsch) Taschenbuch – 31. März 2020' , 'P11', 'von Paul Fuchs, Herausgeber : BMU Verlag',14.81, 2.06, 21.01, null, 17.99);

INSERT INTO PRODUCT VALUES
(980030, 'Java Programmieren:Einsteiger', 'Der leichte Weg zum Java-Experten (2. Auflage: komplett neu verfasst) (Deutsch) Taschenbuch', 'P11', ' Herausgeber:Independently published ', 13.97, 1.65, 21.59, null, 14.99);

--select * from product

----------------------------------------------------------------------------------------------------------------------------------------------------

--Bestellungen

INSERT INTO CUSTOMERORDER VALUES
(100101, 101, '30.04.2021', 'SP1', 'PM1', null, 97.25);

INSERT INTO CUSTOMERORDER VALUES
(100102,102 , '01.05.2021', 'SP2', 'PM2', null, 83.45);

INSERT INTO CUSTOMERORDER VALUES
(100103,103 , '02.05.2021', 'SP3', 'PM3', null, 35.98);

INSERT INTO CUSTOMERORDER VALUES
(100104,104 , '03.05.2021', 'SP1', 'PM4', null, 69.95);

INSERT INTO CUSTOMERORDER VALUES
(100105,105 , '04.05.2021', 'SP2', 'PM1', null, 1974.80);

INSERT INTO CUSTOMERORDER VALUES
(100106,106, '05.05.2021', 'SP3', 'PM2', null, 71.33);

INSERT INTO CUSTOMERORDER VALUES
(100107,107 , '06.05.2021', 'SP1', 'PM3', null, 117.00);

INSERT INTO CUSTOMERORDER VALUES
(100108,108 , '07.05.2021', 'SP2', 'PM4', null, 240.37);

INSERT INTO CUSTOMERORDER VALUES
(100109,109 , '08.05.2021', 'SP3', 'PM1', null, 12.99);

INSERT INTO CUSTOMERORDER VALUES
(100110,110 , '09.05.2021', 'SP1', 'PM2', null, 41.96);

INSERT INTO CUSTOMERORDER VALUES
(100111,111, '10.05.2021', 'SP2', 'PM3', null, 269.83);

INSERT INTO CUSTOMERORDER VALUES
(100112,112 , '11.05.2021', 'SP3', 'PM4', null, 169.90);

INSERT INTO CUSTOMERORDER VALUES
(100113,113 , '12.05.2021', 'SP1', 'PM1', null, 23.97);

INSERT INTO CUSTOMERORDER VALUES
(100114, 114 , '13.05.2021', 'SP2', 'PM2', null, 2660.68);

INSERT INTO CUSTOMERORDER VALUES
(100115, 120 , '14.05.2021', 'SP1', 'PM4', null, 219.95);

INSERT INTO CUSTOMERORDER VALUES
(100116, 125, '15.05.2021', 'SP1', 'PM1', null, 129.43);

INSERT INTO CUSTOMERORDER VALUES
(100117, 123, '16.05.2021', 'SP1', 'PM1', null, 31.47);

INSERT INTO CUSTOMERORDER VALUES
(100118, 121, '17.05.2021', 'SP1', 'PM5', null, 46.99);

INSERT INTO CUSTOMERORDER VALUES
(100119, 119, '18.05.2021', 'SP3', 'PM4', null, 34.99);

INSERT INTO CUSTOMERORDER VALUES
(100120, 117, '19.05.2021', 'SP1', 'PM3', null, 164.96);

INSERT INTO CUSTOMERORDER VALUES
(100121, 126, '20.05.2021', 'SP1', 'PM1', null, 25.98);

INSERT INTO CUSTOMERORDER VALUES
(100122, 124, '21.05.2021', 'SP1', 'PM1', null, 19.50);

INSERT INTO CUSTOMERORDER VALUES
(100123, 128, '22.05.2021', 'SP1', 'PM2', null, 26.99);

INSERT INTO CUSTOMERORDER VALUES
(100124, 122, '23.05.2021', 'SP1', 'PM1', null, 199.98);

INSERT INTO CUSTOMERORDER VALUES
(100125, 101, '24.05.2021', 'SP2', 'PM4', null, 7.99);

INSERT INTO CUSTOMERORDER VALUES
(100126, 130, '25.05.2021', 'SP1', 'PM1', null, 74.90);

INSERT INTO CUSTOMERORDER VALUES
(100127, 127, '26.05.2021', 'SP1', 'PM2', null, 49.80);

INSERT INTO CUSTOMERORDER VALUES
(100128, 129, '27.05.2021', 'SP2', 'PM1', null, 63.90);

INSERT INTO CUSTOMERORDER VALUES
(100129, 120, '28.05.2021', 'SP1', 'PM5', null, 69.99);

INSERT INTO CUSTOMERORDER VALUES
(100130, 118, '29.05.2021', 'SP3', 'PM3', null, 43.99);


-- Bestellpositionen


INSERT INTO ORDER_ITEM VALUES
(100101, 81441, 10, 22.50, 1);

INSERT INTO ORDER_ITEM VALUES
(100101, 980022, 5, 74.75, 2);

INSERT INTO ORDER_ITEM VALUES
(100102, 980023, 2, 27.98, 3);

INSERT INTO ORDER_ITEM VALUES
(100102, 980027, 3, 55.47, 1);

INSERT INTO ORDER_ITEM VALUES
(100103, 980029, 2, 35.98, 1);

INSERT INTO ORDER_ITEM VALUES
(100104, 980023, 5, 69.95, 2);

INSERT INTO ORDER_ITEM VALUES
(100105, 980024, 20, 1974.80, 1);

INSERT INTO ORDER_ITEM VALUES
(100106, 980025, 7, 71.33, 4);

INSERT INTO ORDER_ITEM VALUES
(100107, 980026, 6, 117.00, 3);

INSERT INTO ORDER_ITEM VALUES
(100108, 980027, 13, 240.37, 4);

INSERT INTO ORDER_ITEM VALUES
(100109, 980028, 1, 12.99, 3);

INSERT INTO ORDER_ITEM VALUES
(100110, 980029, 1, 17.99, 2);

INSERT INTO ORDER_ITEM VALUES
(100110, 9788381, 3, 23.97, 4);

INSERT INTO ORDER_ITEM VALUES
(100111, 980029, 5, 89.95, 1);

INSERT INTO ORDER_ITEM VALUES
(100111, 8771859, 2, 179.88, 3);

INSERT INTO ORDER_ITEM VALUES
(100112, 980029, 9, 161.91, 4);

INSERT INTO ORDER_ITEM VALUES
(100112, 9788381, 1, 7.99, 2);

INSERT INTO ORDER_ITEM VALUES
(100113, 9788381, 3, 23.97, 3);

INSERT INTO ORDER_ITEM VALUES
(100114, 8771859, 2, 179.88, 1);

INSERT INTO ORDER_ITEM VALUES
(100114, 9097023, 20, 2480.80, 4);

INSERT INTO ORDER_ITEM VALUES
(100115, 3882164, 5, 219.95, 3);

INSERT INTO ORDER_ITEM VALUES
(100116, 980027, 7, 129.43, 3);

INSERT INTO ORDER_ITEM VALUES
(100117, 1512698, 3, 31.47, 3);

INSERT INTO ORDER_ITEM VALUES
(100118, 1861905, 1, 46.99, 4);

INSERT INTO ORDER_ITEM VALUES
(100119, 8699993, 1, 34.99 , 3);

INSERT INTO ORDER_ITEM VALUES
(100120, 2356590, 8, 164.96, 3);

INSERT INTO ORDER_ITEM VALUES
(100121, 980028, 2, 25.98, 3);

INSERT INTO ORDER_ITEM VALUES
(100122, 980026, 1, 19.50 , 3);

INSERT INTO ORDER_ITEM VALUES
(100123, 4729596, 1, 26.99, 2);

INSERT INTO ORDER_ITEM VALUES
(100124, 8168130, 2, 199.98, 3);

INSERT INTO ORDER_ITEM VALUES
(100125, 9788381, 1, 7.99, 1);

INSERT INTO ORDER_ITEM VALUES
(100126, 9761503, 10, 74.90, 3);

INSERT INTO ORDER_ITEM VALUES
(100127, 8454308, 20, 49.80, 3);

INSERT INTO ORDER_ITEM VALUES
(100128, 2379816, 5, 63.90, 3);

INSERT INTO ORDER_ITEM VALUES
(100129, 9444209, 1, 69.99, 2);

INSERT INTO ORDER_ITEM VALUES
(100130, 3882164, 1, 43.99, 4);


--select * from ORDER_ITEM_STATUS
--select * from SHIPPING_METHOD
--select * from PAYMENT_METHOD
--select * from customer
--select * from product
--select * from order_item
--select * from customerorder