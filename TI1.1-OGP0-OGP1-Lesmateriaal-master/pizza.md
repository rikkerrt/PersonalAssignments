# Huurder

## Beschrijving opdracht (naar een idee van Paul de Mast)

Voor een kamerverhuurbedrijf moet een nieuwe applicatie worden geschreven waarin alle informatie komt te staan van verhuurders, huurders en de (studenten)kamers die aangeboden worden. We beginnen met het ontwikkelen van een klasse Kamer

Ontwikkel een klasse Huurder 

1. 	de kenmerken (attributen)

	Van de huurder willen / moeten we een aantal eigenschappen vastleggen. 
	Voor de hand ligt dat we zijn naam bekend is welk bedrag hij/zij maximaal wenst te besteden voor de huur van een kamer.

    **Opdracht**: 		neem in de Java-code bovenstaande kenmerken met bijbehorend type op. Gebruik hiervoor de attributen `naam` en `maxHuur`


2.	De attributen van een klasse zijn van buitenaf alleen bereikbaar (aanpasbaar en opvraagbaar) d.m.v. bijbehorende get- en set-methoden).

    **Opdracht**:		neem in de Java-code voor elk attribuut een get- en set-methode op.


3.	Elke klasse heeft één of meerdere constructoren. 
	
	We willen een constructor waarbij we bij het aanmaken voor alle attributen zelf een waarde kunnen / meten invullen. 
    Daarnaast is het handig (met het oog om snel de methoden te kunnen testen) te beschikken over een constructor waarbij warden van de attributen automatisch worden ingevuld met enkele door de programmeur gekozen waarden.

    **Opdracht**: 		Voeg beide constructoren toe aan de Java-code 
	en plaats de code net na de attributen.


4.	In praktijk zal er ook behoefte zijn om attributen aan te passen. Over het algemeen veranderen de maten (lengte, breedte en hoogte) van een kamer niet. De huur kan echter wel veranderen.

    **Opdracht**:	Ontwikkel twee methode om de huurprijs van de kamer aan te kunnen passen. Bij de ene methode wordt aangegeven met welk bedrag de huurprijs stijgt en bij de andere methode wordt het percentage van de huurstijging meegegeven. Geef deze 2 methoden de namen
    * `verhoogPercentage`
    * `verhoogVast`
    

5.	Het heel handig om alle gegevens van de huurder in tekst te kunnen weergeven.

    **Opdracht**:	Ontwikkel een (void) methode met de naam `print` die alle waarden die als attribuut zijn opgeslagen op het beeldscherm toont.

6.	In praktijk moet een huurder kunnen besluiten of hij een kamer die hem wordt aangeboden kan huren of anders gezegd kan hij de gevraagde huur betalen. 
	Vertaald naar eerder genoemde attributen van de klasse Kamer en de klasse Huurder:
	is de huurprijs van de kamer kleiner of gelijk aan de maximale Huurpijs van de Huurder ?

    **Opdracht**:	Ontwikkel een methode waarmee dat kan worden vastgesteld.

    > De titel van deze methode luidt:
    >
    > ```java
    >
    >	public boolean kanHuren (Kamer kamer)
    >
    > ```
    >
    {: .tip}

7.  Schrijf een main-methode die deze onderdelen aan elkaar koppelt. Maak een nieuwe `Kamer` aan met een huurprijs van 500 euro per maand, en een `Huurder` met een maximale huurprijs van 400 euro per maand. Controleer of de huurder de kamer kan huren, en print `De huurder kan de kamer huren` als deze de kamer kan huren, of `De huurder kan de kamer niet huren` als de kamer niet gehuurd kan worden.

    Verhoog hierna de maximale huurprijs na 550 euro per maand, en kijk nog een keer of de huurder kan huren, en print de tekst weer uit

    Verhoog hierna de huurprijs van de kamer met 11%, en doe hetzelfde. Je uitvoer zou er als volgt uit moeten zien:

    ```output
    De huurder kan de kamer niet huren
    De huurder kan de kamer huren
    De huurder kan de kamer niet huren
    ``` 


    asduioashda