package zephyrseb.pokemontoptrumps;

public enum AIDecks {
    TOWER_RAIN,
    TOWER_SUN;

    /** @noinspection NonAsciiCharacters*/
    public static Deck generateDeck(AIDecks deckName) {
        Deck deck = new Deck("Deck");
        if (deckName == TOWER_RAIN) {
            deck.addCard(CardRegistry.BLASTOISE);
            deck.addCard(CardRegistry.SWAMPERT);
            deck.addCard(CardRegistry.POLIWAG);
            deck.addCard(CardRegistry.POLIWHIRL);
            deck.addCard(CardRegistry.POLIWRATH);
            deck.addCard(CardRegistry.POLITOED);
            deck.addCard(CardRegistry.WINGULL);
            deck.addCard(CardRegistry.PELIPPER);
            deck.addCard(CardRegistry.TYMPOLE);
            deck.addCard(CardRegistry.PALPITOAD);
            deck.addCard(CardRegistry.SEISMITOAD);
            deck.addCard(CardRegistry.CHEWTLE);
            deck.addCard(CardRegistry.DREDNAW);
            deck.addCard(CardRegistry.GOLDEEN);
            deck.addCard(CardRegistry.SEAKING);
            deck.addCard(CardRegistry.FINNEON);
            deck.addCard(CardRegistry.LUMINEON);
            deck.addCard(CardRegistry.DUCKLETT);
            deck.addCard(CardRegistry.SWANNA);
            deck.addCard(CardRegistry.GYARADOS);
        }
        if (deckName == TOWER_SUN) {
            deck.addCard(CardRegistry.CHARIZARD);
            deck.addCard(CardRegistry.BLAZIKEN);
            deck.addCard(CardRegistry.TORKOAL);
            deck.addCard(CardRegistry.NUMEL);
            deck.addCard(CardRegistry.CAMERUPT);
            deck.addCard(CardRegistry.SUNFLORA);
            deck.addCard(CardRegistry.SUNKERN);
            deck.addCard(CardRegistry.ODDISH);
            deck.addCard(CardRegistry.GLOOM);
            deck.addCard(CardRegistry.VILEPLUME);
            deck.addCard(CardRegistry.FOMANTIS);
            deck.addCard(CardRegistry.LURANTIS);
            deck.addCard(CardRegistry.HELIOPTILE);
            deck.addCard(CardRegistry.HELIOLISK);
            deck.addCard(CardRegistry.SEWADDLE);
            deck.addCard(CardRegistry.SWADLOON);
            deck.addCard(CardRegistry.LEAVANNY);
            deck.addCard(CardRegistry.FLABEBE);
            deck.addCard(CardRegistry.FLOETTE);
            deck.addCard(CardRegistry.FLORGES);
        }
        return deck;
    }
}
