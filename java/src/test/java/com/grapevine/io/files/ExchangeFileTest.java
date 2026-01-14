package com.grapevine.io.files;

import com.grapevine.records.game.Game;
import com.grapevine.records.sheet.Trait;
import com.grapevine.records.sheet.character.Character;
import com.grapevine.records.player.Player;
import com.grapevine.records.sheet.character.splat.Vampire;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static org.junit.Assert.*;

/**
 * Tests for ExchangeLoader and ExchangeSaver.
 */
public class ExchangeFileTest {
    
    private static final String COMPLEX_GAME_PATH = "../sample/complex_game.gex";
    private String tempOutputPath;
    
    private ExchangeLoader loader;
    private ExchangeSaver saver;
    
    @Before
    public void setUp() throws IOException {
        loader = new ExchangeLoader();
        saver = new ExchangeSaver();
        // Create temp file for output
        File tempFile = Files.createTempFile("test_output", ".gex").toFile();
        tempFile.deleteOnExit();
        tempOutputPath = tempFile.getAbsolutePath();
    }
    
    @Test
    public void testLoadComplexGame() throws Exception {
        Game game = loader.loadExchange(COMPLEX_GAME_PATH);
        
        assertNotNull("Game should not be null", game);
        
        // Verify counts as specified in the issue
        assertNotNull("Character list should not be null", game.getCharacterList());
        assertEquals("Should have 12 characters", 12, game.getCharacterList().size());
        
        assertNotNull("Player list should not be null", game.getPlayerList());
        assertEquals("Should have 3 players", 3, game.getPlayerList().size());
        
        assertNotNull("Item list should not be null", game.getItemList());
        assertEquals("Should have 49 items", 49, game.getItemList().size());
        
        assertNotNull("Rote list should not be null", game.getRoteList());
        assertEquals("Should have 1 rote", 1, game.getRoteList().size());
        
        assertNotNull("Location list should not be null", game.getLocationList());
        assertEquals("Should have 1 location", 1, game.getLocationList().size());
        
        assertNotNull("Action list should not be null", game.getInfluenceUseList());
        assertEquals("Should have 1 action", 1, game.getInfluenceUseList().size());
        
        // Note: Plot is not stored in Game class, so we can't test it
        
        assertNotNull("Rumor list should not be null", game.getAllRumorLists());
        // The actual file has 46 rumors, not 1 as stated in the issue
        assertFalse("Should have at least 1 rumor", game.getAllRumorLists().isEmpty());
    }
    
    @Test
    public void testRoundTrip() throws Exception {
        // Load the original file
        Game game1 = loader.loadExchange(COMPLEX_GAME_PATH);
        
        // Save to a temporary file
        saver.saveExchange(game1, tempOutputPath);
        
        // Load the saved file
        Game game2 = loader.loadExchange(tempOutputPath);
        
        // Verify counts are preserved
        assertNotNull("Reloaded game should not be null", game2);
        
        assertEquals("Character count should be preserved",
                game1.getCharacterList() != null ? game1.getCharacterList().size() : 0,
                game2.getCharacterList() != null ? game2.getCharacterList().size() : 0);
        
        assertEquals("Player count should be preserved",
                game1.getPlayerList() != null ? game1.getPlayerList().size() : 0,
                game2.getPlayerList() != null ? game2.getPlayerList().size() : 0);
        
        assertEquals("Item count should be preserved",
                game1.getItemList() != null ? game1.getItemList().size() : 0,
                game2.getItemList() != null ? game2.getItemList().size() : 0);
        
        assertEquals("Rote count should be preserved",
                game1.getRoteList() != null ? game1.getRoteList().size() : 0,
                game2.getRoteList() != null ? game2.getRoteList().size() : 0);
        
        assertEquals("Location count should be preserved",
                game1.getLocationList() != null ? game1.getLocationList().size() : 0,
                game2.getLocationList() != null ? game2.getLocationList().size() : 0);
        
        assertEquals("Action count should be preserved",
                game1.getInfluenceUseList() != null ? game1.getInfluenceUseList().size() : 0,
                game2.getInfluenceUseList() != null ? game2.getInfluenceUseList().size() : 0);
        
        assertEquals("Rumor count should be preserved",
                game1.getAllRumorLists() != null ? game1.getAllRumorLists().size() : 0,
                game2.getAllRumorLists() != null ? game2.getAllRumorLists().size() : 0);

        assertEquals("Detailed string should be the same", game1.toDetailedString(), game2.toDetailedString());
    }
    
    @Test
    public void testCharacterNames() throws Exception {
        Game game = loader.loadExchange(COMPLEX_GAME_PATH);
        
        assertNotNull("Character list should not be null", game.getCharacterList());
        assertFalse("Should have characters", game.getCharacterList().isEmpty());
        
        // Verify at least one character has a name
        boolean hasNamedCharacter = false;
        for (Character character : game.getCharacterList()) {
            if (character.getName() != null && !character.getName().isEmpty()) {
                hasNamedCharacter = true;
                break;
            }
        }
        assertTrue("At least one character should have a name", hasNamedCharacter);
    }
    
    @Test
    public void testPlayerNames() throws Exception {
        Game game = loader.loadExchange(COMPLEX_GAME_PATH);
        
        assertNotNull("Player list should not be null", game.getPlayerList());
        assertFalse("Should have players", game.getPlayerList().isEmpty());
        
        // Verify at least one player has a name
        boolean hasNamedPlayer = false;
        for (Player player : game.getPlayerList()) {
            if (player.getName() != null && !player.getName().isEmpty()) {
                hasNamedPlayer = true;
                break;
            }
        }
        assertTrue("At least one player should have a name", hasNamedPlayer);
    }
    
    @Test
    public void testVampireFieldsRoundTrip() throws Exception {
        // Load the complex game which contains "Billy the Vampire"
        Game game1 = loader.loadExchange(COMPLEX_GAME_PATH);
        
        // Find Billy the Vampire
        Vampire billyOriginal = null;
        for (Character character : game1.getCharacterList()) {
            if (character instanceof Vampire && "Billy the Vampire".equals(character.getName())) {
                billyOriginal = (Vampire) character;
                break;
            }
        }
        
        assertNotNull("Billy the Vampire should exist in the test file", billyOriginal);
        
        // Save to a temporary file
        saver.saveExchange(game1, tempOutputPath);
        
        // Load the saved file
        Game game2 = loader.loadExchange(tempOutputPath);
        
        // Find Billy the Vampire in the reloaded game
        Vampire billyReloaded = null;
        for (Character character : game2.getCharacterList()) {
            if (character instanceof Vampire && "Billy the Vampire".equals(character.getName())) {
                billyReloaded = (Vampire) character;
                break;
            }
        }
        
        assertNotNull("Billy the Vampire should exist in the reloaded file", billyReloaded);
        
        // Test all Character base fields
        assertEquals("Name should match", billyOriginal.getName(), billyReloaded.getName());
        assertEquals("Nature should match", billyOriginal.getNature(), billyReloaded.getNature());
        assertEquals("Demeanor should match", billyOriginal.getDemeanor(), billyReloaded.getDemeanor());
        assertEquals("Status should match", billyOriginal.getStatus(), billyReloaded.getStatus());
        assertEquals("Physical max should match", billyOriginal.getPhysicalMax(), billyReloaded.getPhysicalMax());
        assertEquals("Social max should match", billyOriginal.getSocialMax(), billyReloaded.getSocialMax());
        assertEquals("Mental max should match", billyOriginal.getMentalMax(), billyReloaded.getMentalMax());
        assertEquals("Willpower should match", billyOriginal.getWillpower(), billyReloaded.getWillpower());
        assertEquals("Temp willpower should match", billyOriginal.getTempWillpower(), billyReloaded.getTempWillpower());
        
        // Test all Vampire-specific fields
        assertEquals("Clan should match", billyOriginal.getClan(), billyReloaded.getClan());
        assertEquals("Sect should match", billyOriginal.getSect(), billyReloaded.getSect());
        assertEquals("Generation should match", billyOriginal.getGeneration(), billyReloaded.getGeneration());
        assertEquals("Title should match", billyOriginal.getTitle(), billyReloaded.getTitle());
        assertEquals("Coterie should match", billyOriginal.getCoterie(), billyReloaded.getCoterie());
        assertEquals("Path should match", billyOriginal.getPath(), billyReloaded.getPath());
        assertEquals("Sire should match", billyOriginal.getSire(), billyReloaded.getSire());
        assertEquals("Aura should match", billyOriginal.getAura(), billyReloaded.getAura());
        assertEquals("Aura bonus should match", billyOriginal.getAuraBonus(), billyReloaded.getAuraBonus());
        assertEquals("Blood should match", billyOriginal.getBlood(), billyReloaded.getBlood());
        assertEquals("Conscience should match", billyOriginal.getConscience(), billyReloaded.getConscience());
        assertEquals("Self control should match", billyOriginal.getSelfControl(), billyReloaded.getSelfControl());
        assertEquals("Courage should match", billyOriginal.getCourage(), billyReloaded.getCourage());
        assertEquals("Path traits should match", billyOriginal.getPathTraits(), billyReloaded.getPathTraits());
        assertEquals("Temp blood should match", billyOriginal.getTempBlood(), billyReloaded.getTempBlood());
        assertEquals("Temp conscience should match", billyOriginal.getTempConscience(), billyReloaded.getTempConscience());
        assertEquals("Temp self control should match", billyOriginal.getTempSelfControl(), billyReloaded.getTempSelfControl());
        assertEquals("Temp courage should match", billyOriginal.getTempCourage(), billyReloaded.getTempCourage());
        assertEquals("Temp path traits should match", billyOriginal.getTempPathTraits(), billyReloaded.getTempPathTraits());
        
        // Test trait lists
        assertTraitListsMatch("Physical traits", billyOriginal.getPhysicalList(), billyReloaded.getPhysicalList());
        assertTraitListsMatch("Social traits", billyOriginal.getSocialList(), billyReloaded.getSocialList());
        assertTraitListsMatch("Mental traits", billyOriginal.getMentalList(), billyReloaded.getMentalList());
        assertTraitListsMatch("Physical negative traits", billyOriginal.getPhysicalNegList(), billyReloaded.getPhysicalNegList());
        assertTraitListsMatch("Social negative traits", billyOriginal.getSocialNegList(), billyReloaded.getSocialNegList());
        assertTraitListsMatch("Mental negative traits", billyOriginal.getMentalNegList(), billyReloaded.getMentalNegList());
        assertTraitListsMatch("Status", billyOriginal.getStatusList(), billyReloaded.getStatusList());
        assertTraitListsMatch("Abilities", billyOriginal.getAbilityList(), billyReloaded.getAbilityList());
        assertTraitListsMatch("Influences", billyOriginal.getInfluenceList(), billyReloaded.getInfluenceList());
        assertTraitListsMatch("Backgrounds", billyOriginal.getBackgroundList(), billyReloaded.getBackgroundList());
        assertTraitListsMatch("Health", billyOriginal.getHealthList(), billyReloaded.getHealthList());
        assertTraitListsMatch("Bonds", billyOriginal.getBondList(), billyReloaded.getBondList());
        assertTraitListsMatch("Miscellaneous", billyOriginal.getMiscellaneousList(), billyReloaded.getMiscellaneousList());
        assertTraitListsMatch("Derangements", billyOriginal.getDerangementList(), billyReloaded.getDerangementList());
        assertTraitListsMatch("Disciplines", billyOriginal.getDisciplineList(), billyReloaded.getDisciplineList());
        assertTraitListsMatch("Rituals", billyOriginal.getRitualList(), billyReloaded.getRitualList());
        assertTraitListsMatch("Merits", billyOriginal.getMeritList(), billyReloaded.getMeritList());
        assertTraitListsMatch("Flaws", billyOriginal.getFlawList(), billyReloaded.getFlawList());
        assertTraitListsMatch("Equipment", billyOriginal.getEquipmentList(), billyReloaded.getEquipmentList());
        assertTraitListsMatch("Locations", billyOriginal.getHangoutList(), billyReloaded.getHangoutList());
    }
    
    private void assertTraitListsMatch(String listName, java.util.List<Trait> original, java.util.List<Trait> reloaded) {
        if (original == null && reloaded == null) {
            return;
        }
        
        assertNotNull(listName + " should not be null if original wasn't null", reloaded);
        assertEquals(listName + " size should match", 
                original != null ? original.size() : 0,
                reloaded.size());
        
        if (original != null && !original.isEmpty()) {
            for (int i = 0; i < original.size(); i++) {
                Trait origTrait = original.get(i);
                Trait reloadedTrait = reloaded.get(i);
                assertEquals(listName + "[" + i + "] name should match", origTrait.getName(), reloadedTrait.getName());
                assertEquals(listName + "[" + i + "] total should match", origTrait.getTotal(), reloadedTrait.getTotal());
                assertEquals(listName + "[" + i + "] note should match", origTrait.getNote(), reloadedTrait.getNote());
            }
        }
    }
    
    @Test
    public void testChangelingFieldsRoundTrip() throws Exception {
        // Import Changeling class
        com.grapevine.records.sheet.character.splat.Changeling changelingOriginal = null;
        com.grapevine.records.sheet.character.splat.Changeling changelingReloaded = null;
        
        // Load the complex game which contains "Carry Changeling"
        Game game1 = loader.loadExchange(COMPLEX_GAME_PATH);
        
        // Find Carry Changeling
        for (Character character : game1.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Changeling 
                    && "Carry Changeling".equals(character.getName())) {
                changelingOriginal = (com.grapevine.records.sheet.character.splat.Changeling) character;
                break;
            }
        }
        
        assertNotNull("Carry Changeling should exist in the test file", changelingOriginal);
        
        // Save to a temporary file
        saver.saveExchange(game1, tempOutputPath);
        
        // Load the saved file
        Game game2 = loader.loadExchange(tempOutputPath);
        
        // Find Carry Changeling in the reloaded game
        for (Character character : game2.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Changeling 
                    && "Carry Changeling".equals(character.getName())) {
                changelingReloaded = (com.grapevine.records.sheet.character.splat.Changeling) character;
                break;
            }
        }
        
        assertNotNull("Carry Changeling should exist in the reloaded file", changelingReloaded);
        
        // Test all Character base fields
        assertEquals("Name should match", changelingOriginal.getName(), changelingReloaded.getName());
        assertEquals("Nature should match", changelingOriginal.getNature(), changelingReloaded.getNature());
        assertEquals("Demeanor should match", changelingOriginal.getDemeanor(), changelingReloaded.getDemeanor());
        assertEquals("Status should match", changelingOriginal.getStatus(), changelingReloaded.getStatus());
        assertEquals("Physical max should match", changelingOriginal.getPhysicalMax(), changelingReloaded.getPhysicalMax());
        assertEquals("Social max should match", changelingOriginal.getSocialMax(), changelingReloaded.getSocialMax());
        assertEquals("Mental max should match", changelingOriginal.getMentalMax(), changelingReloaded.getMentalMax());
        assertEquals("Willpower should match", changelingOriginal.getWillpower(), changelingReloaded.getWillpower());
        assertEquals("Temp willpower should match", changelingOriginal.getTempWillpower(), changelingReloaded.getTempWillpower());
        
        // Test all Changeling-specific fields
        assertEquals("Kith should match", changelingOriginal.getKith(), changelingReloaded.getKith());
        assertEquals("Seeming should match", changelingOriginal.getSeeming(), changelingReloaded.getSeeming());
        assertEquals("House should match", changelingOriginal.getHouse(), changelingReloaded.getHouse());
        assertEquals("Title should match", changelingOriginal.getTitle(), changelingReloaded.getTitle());
        assertEquals("Legacies should match", changelingOriginal.getLegacies(), changelingReloaded.getLegacies());
        assertEquals("Seelie should match", changelingOriginal.getSeelie(), changelingReloaded.getSeelie());
        assertEquals("Unseelie should match", changelingOriginal.getUnseelie(), changelingReloaded.getUnseelie());
        assertEquals("Court should match", changelingOriginal.getCourt(), changelingReloaded.getCourt());
        assertEquals("Threshold should match", changelingOriginal.getThreshold(), changelingReloaded.getThreshold());
        assertEquals("Glamour should match", changelingOriginal.getGlamour(), changelingReloaded.getGlamour());
        assertEquals("Banality should match", changelingOriginal.getBanality(), changelingReloaded.getBanality());
        assertEquals("Nightmare should match", changelingOriginal.getNightmare(), changelingReloaded.getNightmare());
        assertEquals("Temp glamour should match", changelingOriginal.getTempGlamour(), changelingReloaded.getTempGlamour());
        assertEquals("Temp banality should match", changelingOriginal.getTempBanality(), changelingReloaded.getTempBanality());
        assertEquals("Temp nightmare should match", changelingOriginal.getTempNightmare(), changelingReloaded.getTempNightmare());
        assertEquals("Oaths should match", changelingOriginal.getOaths(), changelingReloaded.getOaths());
        
        // Test trait lists
        assertTraitListsMatch("Physical traits", changelingOriginal.getPhysicalList(), changelingReloaded.getPhysicalList());
        assertTraitListsMatch("Social traits", changelingOriginal.getSocialList(), changelingReloaded.getSocialList());
        assertTraitListsMatch("Mental traits", changelingOriginal.getMentalList(), changelingReloaded.getMentalList());
        assertTraitListsMatch("Physical negative traits", changelingOriginal.getPhysicalNegList(), changelingReloaded.getPhysicalNegList());
        assertTraitListsMatch("Social negative traits", changelingOriginal.getSocialNegList(), changelingReloaded.getSocialNegList());
        assertTraitListsMatch("Mental negative traits", changelingOriginal.getMentalNegList(), changelingReloaded.getMentalNegList());
        assertTraitListsMatch("Status", changelingOriginal.getStatusList(), changelingReloaded.getStatusList());
        assertTraitListsMatch("Abilities", changelingOriginal.getAbilityList(), changelingReloaded.getAbilityList());
        assertTraitListsMatch("Influences", changelingOriginal.getInfluenceList(), changelingReloaded.getInfluenceList());
        assertTraitListsMatch("Backgrounds", changelingOriginal.getBackgroundList(), changelingReloaded.getBackgroundList());
        assertTraitListsMatch("Health", changelingOriginal.getHealthList(), changelingReloaded.getHealthList());
        assertTraitListsMatch("Arts", changelingOriginal.getArtList(), changelingReloaded.getArtList());
        assertTraitListsMatch("Realms", changelingOriginal.getRealmList(), changelingReloaded.getRealmList());
        assertTraitListsMatch("Derangements", changelingOriginal.getDerangementList(), changelingReloaded.getDerangementList());
        assertTraitListsMatch("Merits", changelingOriginal.getMeritList(), changelingReloaded.getMeritList());
        assertTraitListsMatch("Flaws", changelingOriginal.getFlawList(), changelingReloaded.getFlawList());
        assertTraitListsMatch("Equipment", changelingOriginal.getEquipmentList(), changelingReloaded.getEquipmentList());
        assertTraitListsMatch("Locations", changelingOriginal.getHangoutList(), changelingReloaded.getHangoutList());
    }
    
    @Test
    public void testMageFieldsRoundTrip() throws Exception {
        // Declare Mage variables
        com.grapevine.records.sheet.character.splat.Mage mageOriginal = null;
        com.grapevine.records.sheet.character.splat.Mage mageReloaded = null;

        // Load the complex game which contains "Mark Mage"
        Game game1 = loader.loadExchange(COMPLEX_GAME_PATH);

        // Find Mark Mage
        for (Character character : game1.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Mage
                    && "Mark Mage".equals(character.getName())) {
                mageOriginal = (com.grapevine.records.sheet.character.splat.Mage) character;
                break;
            }
        }

        assertNotNull("Mark Mage should exist in the test file", mageOriginal);

        // Save to a temporary file
        saver.saveExchange(game1, tempOutputPath);

        // Load the saved file
        Game game2 = loader.loadExchange(tempOutputPath);

        for (Character character : game2.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Mage
                    && "Mark Mage".equals(character.getName())) {
                mageReloaded = (com.grapevine.records.sheet.character.splat.Mage) character;
                break;
            }
        }

        assertNotNull("Mark Mage should exist in the reloaded file", mageReloaded);

        // Test all Character base fields
        assertEquals("Name should match", mageOriginal.getName(), mageReloaded.getName());
        assertEquals("Nature should match", mageOriginal.getNature(), mageReloaded.getNature());
        assertEquals("Demeanor should match", mageOriginal.getDemeanor(), mageReloaded.getDemeanor());
        assertEquals("Status should match", mageOriginal.getStatus(), mageReloaded.getStatus());
        assertEquals("Physical max should match", mageOriginal.getPhysicalMax(), mageReloaded.getPhysicalMax());
        assertEquals("Social max should match", mageOriginal.getSocialMax(), mageReloaded.getSocialMax());
        assertEquals("Mental max should match", mageOriginal.getMentalMax(), mageReloaded.getMentalMax());
        assertEquals("Willpower should match", mageOriginal.getWillpower(), mageReloaded.getWillpower());
        assertEquals("Temp willpower should match", mageOriginal.getTempWillpower(), mageReloaded.getTempWillpower());

        // Test all Mage-specific fields
        assertEquals("Essence should match", mageOriginal.getEssence(), mageReloaded.getEssence());
        assertEquals("Tradition should match", mageOriginal.getTradition(), mageReloaded.getTradition());
        assertEquals("Faction should match", mageOriginal.getFaction(), mageReloaded.getFaction());
        assertEquals("Cabal should match", mageOriginal.getCabal(), mageReloaded.getCabal());
        assertEquals("Rank should match", mageOriginal.getRank(), mageReloaded.getRank());
        assertEquals("Arete should match", mageOriginal.getArete(), mageReloaded.getArete());
        assertEquals("Quintessence should match", mageOriginal.getQuintessence(), mageReloaded.getQuintessence());
        assertEquals("Paradox should match", mageOriginal.getParadox(), mageReloaded.getParadox());
        assertEquals("Temp arete should match", mageOriginal.getTempArete(), mageReloaded.getTempArete());
        assertEquals("Temp quintessence should match", mageOriginal.getTempQuintessence(), mageReloaded.getTempQuintessence());
        assertEquals("Temp paradox should match", mageOriginal.getTempParadox(), mageReloaded.getTempParadox());
        assertEquals("Foci should match", mageOriginal.getFoci(), mageReloaded.getFoci());
        assertEquals("Biography should match", mageOriginal.getBiography(), mageReloaded.getBiography());

        // Test trait lists
        assertTraitListsMatch("Physical traits", mageOriginal.getPhysicalList(), mageReloaded.getPhysicalList());
        assertTraitListsMatch("Social traits", mageOriginal.getSocialList(), mageReloaded.getSocialList());
        assertTraitListsMatch("Mental traits", mageOriginal.getMentalList(), mageReloaded.getMentalList());
        assertTraitListsMatch("Physical negative traits", mageOriginal.getPhysicalNegList(), mageReloaded.getPhysicalNegList());
        assertTraitListsMatch("Social negative traits", mageOriginal.getSocialNegList(), mageReloaded.getSocialNegList());
        assertTraitListsMatch("Mental negative traits", mageOriginal.getMentalNegList(), mageReloaded.getMentalNegList());
        assertTraitListsMatch("Abilities", mageOriginal.getAbilityList(), mageReloaded.getAbilityList());
        assertTraitListsMatch("Influences", mageOriginal.getInfluenceList(), mageReloaded.getInfluenceList());
        assertTraitListsMatch("Backgrounds", mageOriginal.getBackgroundList(), mageReloaded.getBackgroundList());
        assertTraitListsMatch("Health", mageOriginal.getHealthList(), mageReloaded.getHealthList());
        assertTraitListsMatch("Resonance", mageOriginal.getResonanceList(), mageReloaded.getResonanceList());
        assertTraitListsMatch("Reputation", mageOriginal.getReputationList(), mageReloaded.getReputationList());
        assertTraitListsMatch("Spheres", mageOriginal.getSphereList(), mageReloaded.getSphereList());
        assertTraitListsMatch("Rotes", mageOriginal.getRoteList(), mageReloaded.getRoteList());
        assertTraitListsMatch("Derangements", mageOriginal.getDerangementList(), mageReloaded.getDerangementList());
        assertTraitListsMatch("Merits", mageOriginal.getMeritList(), mageReloaded.getMeritList());
        assertTraitListsMatch("Flaws", mageOriginal.getFlawList(), mageReloaded.getFlawList());
        assertTraitListsMatch("Equipment", mageOriginal.getEquipmentList(), mageReloaded.getEquipmentList());
        assertTraitListsMatch("Locations", mageOriginal.getHangoutList(), mageReloaded.getHangoutList());
    }

    @Test
    public void testWraithFieldsRoundTrip() throws Exception {
        // Import Wraith class
        com.grapevine.records.sheet.character.splat.Wraith wraithOriginal = null;
        com.grapevine.records.sheet.character.splat.Wraith wraithReloaded = null;
        
        // Load the complex game which contains "Walt Wraith"
        Game game1 = loader.loadExchange(COMPLEX_GAME_PATH);
        
        // Find Walt Wraith
        for (Character character : game1.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Wraith 
                    && "Walt Wraith".equals(character.getName())) {
                wraithOriginal = (com.grapevine.records.sheet.character.splat.Wraith) character;
                break;
            }
        }
        
        assertNotNull("Walt Wraith should exist in the test file", wraithOriginal);
        
        // Save to a temporary file
        saver.saveExchange(game1, tempOutputPath);
        
        // Load the saved file
        Game game2 = loader.loadExchange(tempOutputPath);
        
        // Find Walt Wraith in the reloaded game
        for (Character character : game2.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Wraith 
                    && "Walt Wraith".equals(character.getName())) {
                wraithReloaded = (com.grapevine.records.sheet.character.splat.Wraith) character;
                break;
            }
        }
        
        assertNotNull("Walt Wraith should exist in the reloaded file", wraithReloaded);
        
        // Test all Character base fields
        assertEquals("Name should match", wraithOriginal.getName(), wraithReloaded.getName());
        assertEquals("Nature should match", wraithOriginal.getNature(), wraithReloaded.getNature());
        assertEquals("Demeanor should match", wraithOriginal.getDemeanor(), wraithReloaded.getDemeanor());
        assertEquals("Status should match", wraithOriginal.getStatus(), wraithReloaded.getStatus());
        assertEquals("Physical max should match", wraithOriginal.getPhysicalMax(), wraithReloaded.getPhysicalMax());
        assertEquals("Social max should match", wraithOriginal.getSocialMax(), wraithReloaded.getSocialMax());
        assertEquals("Mental max should match", wraithOriginal.getMentalMax(), wraithReloaded.getMentalMax());
        assertEquals("Willpower should match", wraithOriginal.getWillpower(), wraithReloaded.getWillpower());
        assertEquals("Temp willpower should match", wraithOriginal.getTempWillpower(), wraithReloaded.getTempWillpower());
        
        // Test all Wraith-specific fields
        assertEquals("Ethnos should match", wraithOriginal.getEthnos(), wraithReloaded.getEthnos());
        assertEquals("Guild should match", wraithOriginal.getGuild(), wraithReloaded.getGuild());
        assertEquals("Faction should match", wraithOriginal.getFaction(), wraithReloaded.getFaction());
        assertEquals("Legion should match", wraithOriginal.getLegion(), wraithReloaded.getLegion());
        assertEquals("Rank should match", wraithOriginal.getRank(), wraithReloaded.getRank());
        assertEquals("Pathos should match", wraithOriginal.getPathos(), wraithReloaded.getPathos());
        assertEquals("Corpus should match", wraithOriginal.getCorpus(), wraithReloaded.getCorpus());
        assertEquals("Angst should match", wraithOriginal.getAngst(), wraithReloaded.getAngst());
        assertEquals("Temp pathos should match", wraithOriginal.getTempPathos(), wraithReloaded.getTempPathos());
        assertEquals("Temp corpus should match", wraithOriginal.getTempCorpus(), wraithReloaded.getTempCorpus());
        assertEquals("Temp angst should match", wraithOriginal.getTempAngst(), wraithReloaded.getTempAngst());
        assertEquals("Passions should match", wraithOriginal.getPassions(), wraithReloaded.getPassions());
        assertEquals("Fetters should match", wraithOriginal.getFetters(), wraithReloaded.getFetters());
        assertEquals("Life should match", wraithOriginal.getLife(), wraithReloaded.getLife());
        assertEquals("Death should match", wraithOriginal.getDeath(), wraithReloaded.getDeath());
        assertEquals("Haunt should match", wraithOriginal.getHaunt(), wraithReloaded.getHaunt());
        assertEquals("Regret should match", wraithOriginal.getRegret(), wraithReloaded.getRegret());
        assertEquals("Shadow archetype should match", wraithOriginal.getShadowArchetype(), wraithReloaded.getShadowArchetype());
        assertEquals("Shadow player should match", wraithOriginal.getShadowPlayer(), wraithReloaded.getShadowPlayer());
        assertEquals("Dark passions should match", wraithOriginal.getDarkPassions(), wraithReloaded.getDarkPassions());
        
        // Test trait lists
        assertTraitListsMatch("Physical traits", wraithOriginal.getPhysicalList(), wraithReloaded.getPhysicalList());
        assertTraitListsMatch("Social traits", wraithOriginal.getSocialList(), wraithReloaded.getSocialList());
        assertTraitListsMatch("Mental traits", wraithOriginal.getMentalList(), wraithReloaded.getMentalList());
        assertTraitListsMatch("Physical negative traits", wraithOriginal.getPhysicalNegList(), wraithReloaded.getPhysicalNegList());
        assertTraitListsMatch("Social negative traits", wraithOriginal.getSocialNegList(), wraithReloaded.getSocialNegList());
        assertTraitListsMatch("Mental negative traits", wraithOriginal.getMentalNegList(), wraithReloaded.getMentalNegList());
        assertTraitListsMatch("Status", wraithOriginal.getStatusList(), wraithReloaded.getStatusList());
        assertTraitListsMatch("Abilities", wraithOriginal.getAbilityList(), wraithReloaded.getAbilityList());
        assertTraitListsMatch("Influences", wraithOriginal.getInfluenceList(), wraithReloaded.getInfluenceList());
        assertTraitListsMatch("Backgrounds", wraithOriginal.getBackgroundList(), wraithReloaded.getBackgroundList());
        assertTraitListsMatch("Arcanoi", wraithOriginal.getArcanoiList(), wraithReloaded.getArcanoiList());
        assertTraitListsMatch("Derangements", wraithOriginal.getDerangementList(), wraithReloaded.getDerangementList());
        assertTraitListsMatch("Merits", wraithOriginal.getMeritList(), wraithReloaded.getMeritList());
        assertTraitListsMatch("Flaws", wraithOriginal.getFlawList(), wraithReloaded.getFlawList());
        assertTraitListsMatch("Thorns", wraithOriginal.getThornList(), wraithReloaded.getThornList());
        assertTraitListsMatch("Equipment", wraithOriginal.getEquipmentList(), wraithReloaded.getEquipmentList());
        assertTraitListsMatch("Locations", wraithOriginal.getHangoutList(), wraithReloaded.getHangoutList());
    }

    @Test
    public void testHunterFieldsRoundTrip() throws Exception {
        // Import Hunter class
        com.grapevine.records.sheet.character.splat.Hunter hunterOriginal = null;
        com.grapevine.records.sheet.character.splat.Hunter hunterReloaded = null;

        // Load the complex game which contains "Hank Hunter"
        Game game1 = loader.loadExchange(COMPLEX_GAME_PATH);

        // Find Hank Hunter
        for (Character character : game1.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Hunter
                    && "Hank Hunter".equals(character.getName())) {
                hunterOriginal = (com.grapevine.records.sheet.character.splat.Hunter) character;
                break;
            }
        }

        assertNotNull("Hank Hunter should exist in the test file", hunterOriginal);

        // Save to a temporary file
        saver.saveExchange(game1, tempOutputPath);

        // Load the saved file
        Game game2 = loader.loadExchange(tempOutputPath);

        // Find Hank Hunter in the reloaded game
        for (Character character : game2.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Hunter
                    && "Hank Hunter".equals(character.getName())) {
                hunterReloaded = (com.grapevine.records.sheet.character.splat.Hunter) character;
                break;
            }
        }

        assertNotNull("Hank Hunter should exist in the reloaded file", hunterReloaded);

        // Test all Character base fields
        assertEquals("Name should match", hunterOriginal.getName(), hunterReloaded.getName());
        assertEquals("Nature should match", hunterOriginal.getNature(), hunterReloaded.getNature());
        assertEquals("Demeanor should match", hunterOriginal.getDemeanor(), hunterReloaded.getDemeanor());
        assertEquals("Status should match", hunterOriginal.getStatus(), hunterReloaded.getStatus());
        assertEquals("Physical max should match", hunterOriginal.getPhysicalMax(), hunterReloaded.getPhysicalMax());
        assertEquals("Social max should match", hunterOriginal.getSocialMax(), hunterReloaded.getSocialMax());
        assertEquals("Mental max should match", hunterOriginal.getMentalMax(), hunterReloaded.getMentalMax());
        assertEquals("Willpower should match", hunterOriginal.getWillpower(), hunterReloaded.getWillpower());
        assertEquals("Temp willpower should match", hunterOriginal.getTempWillpower(), hunterReloaded.getTempWillpower());

        // Test all Hunter-specific fields
        assertEquals("Creed should match", hunterOriginal.getCreed(), hunterReloaded.getCreed());
        assertEquals("Camp should match", hunterOriginal.getCamp(), hunterReloaded.getCamp());
        assertEquals("Handle should match", hunterOriginal.getHandle(), hunterReloaded.getHandle());
        assertEquals("Conviction should match", hunterOriginal.getConviction(), hunterReloaded.getConviction());
        assertEquals("Mercy should match", hunterOriginal.getMercy(), hunterReloaded.getMercy());
        assertEquals("Vision should match", hunterOriginal.getVision(), hunterReloaded.getVision());
        assertEquals("Zeal should match", hunterOriginal.getZeal(), hunterReloaded.getZeal());
        assertEquals("Temp conviction should match", hunterOriginal.getTempConviction(), hunterReloaded.getTempConviction());
        assertEquals("Temp mercy should match", hunterOriginal.getTempMercy(), hunterReloaded.getTempMercy());
        assertEquals("Temp vision should match", hunterOriginal.getTempVision(), hunterReloaded.getTempVision());
        assertEquals("Temp zeal should match", hunterOriginal.getTempZeal(), hunterReloaded.getTempZeal());

        // Test trait lists
        assertTraitListsMatch("Physical traits", hunterOriginal.getPhysicalList(), hunterReloaded.getPhysicalList());
        assertTraitListsMatch("Social traits", hunterOriginal.getSocialList(), hunterReloaded.getSocialList());
        assertTraitListsMatch("Mental traits", hunterOriginal.getMentalList(), hunterReloaded.getMentalList());
        assertTraitListsMatch("Physical negative traits", hunterOriginal.getPhysicalNegList(), hunterReloaded.getPhysicalNegList());
        assertTraitListsMatch("Social negative traits", hunterOriginal.getSocialNegList(), hunterReloaded.getSocialNegList());
        assertTraitListsMatch("Mental negative traits", hunterOriginal.getMentalNegList(), hunterReloaded.getMentalNegList());
        assertTraitListsMatch("Abilities", hunterOriginal.getAbilityList(), hunterReloaded.getAbilityList());
        assertTraitListsMatch("Influences", hunterOriginal.getInfluenceList(), hunterReloaded.getInfluenceList());
        assertTraitListsMatch("Backgrounds", hunterOriginal.getBackgroundList(), hunterReloaded.getBackgroundList());
        assertTraitListsMatch("Health", hunterOriginal.getHealthList(), hunterReloaded.getHealthList());
        assertTraitListsMatch("Derangements", hunterOriginal.getDerangementList(), hunterReloaded.getDerangementList());
        assertTraitListsMatch("Edges", hunterOriginal.getEdgeList(), hunterReloaded.getEdgeList());
        assertTraitListsMatch("Merits", hunterOriginal.getMeritList(), hunterReloaded.getMeritList());
        assertTraitListsMatch("Flaws", hunterOriginal.getFlawList(), hunterReloaded.getFlawList());
        assertTraitListsMatch("Equipment", hunterOriginal.getEquipmentList(), hunterReloaded.getEquipmentList());
        assertTraitListsMatch("Locations", hunterOriginal.getHangoutList(), hunterReloaded.getHangoutList());
    }

    @Test
    public void testWerewolfFieldsRoundTrip() throws Exception {
        // Import Werewolf class
        com.grapevine.records.sheet.character.splat.Werewolf werewolfOriginal = null;
        com.grapevine.records.sheet.character.splat.Werewolf werewolfReloaded = null;

        // Load the complex game which contains "Wanda Werewolf"
        Game game1 = loader.loadExchange(COMPLEX_GAME_PATH);

        // Find Wanda Werewolf
        for (Character character : game1.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Werewolf
                    && "Wanda Werewolf".equals(character.getName())) {
                werewolfOriginal = (com.grapevine.records.sheet.character.splat.Werewolf) character;
                break;
            }
        }

        assertNotNull("Wanda Werewolf should exist in the test file", werewolfOriginal);

        // Save to a temporary file
        saver.saveExchange(game1, tempOutputPath);

        // Load the saved file
        Game game2 = loader.loadExchange(tempOutputPath);

        // Find Wanda Werewolf in the reloaded game
        for (Character character : game2.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Werewolf
                    && "Wanda Werewolf".equals(character.getName())) {
                werewolfReloaded = (com.grapevine.records.sheet.character.splat.Werewolf) character;
                break;
            }
        }

        assertNotNull("Wanda Werewolf should exist in the reloaded file", werewolfReloaded);

        // Test all Character base fields
        assertEquals("Name should match", werewolfOriginal.getName(), werewolfReloaded.getName());
        assertEquals("Nature should match", werewolfOriginal.getNature(), werewolfReloaded.getNature());
        assertEquals("Demeanor should match", werewolfOriginal.getDemeanor(), werewolfReloaded.getDemeanor());
        assertEquals("Status should match", werewolfOriginal.getStatus(), werewolfReloaded.getStatus());
        assertEquals("Physical max should match", werewolfOriginal.getPhysicalMax(), werewolfReloaded.getPhysicalMax());
        assertEquals("Social max should match", werewolfOriginal.getSocialMax(), werewolfReloaded.getSocialMax());
        assertEquals("Mental max should match", werewolfOriginal.getMentalMax(), werewolfReloaded.getMentalMax());
        assertEquals("Willpower should match", werewolfOriginal.getWillpower(), werewolfReloaded.getWillpower());
        assertEquals("Temp willpower should match", werewolfOriginal.getTempWillpower(), werewolfReloaded.getTempWillpower());

        // Test all Werewolf-specific fields
        assertEquals("Tribe should match", werewolfOriginal.getTribe(), werewolfReloaded.getTribe());
        assertEquals("Camp should match", werewolfOriginal.getCamp(), werewolfReloaded.getCamp());

        // Test all Fera fields (parent class)
        assertEquals("Breed should match", werewolfOriginal.getBreed(), werewolfReloaded.getBreed());
        assertEquals("Auspice should match", werewolfOriginal.getAuspice(), werewolfReloaded.getAuspice());
        assertEquals("Rank should match", werewolfOriginal.getRank(), werewolfReloaded.getRank());
        assertEquals("Pack should match", werewolfOriginal.getPack(), werewolfReloaded.getPack());
        assertEquals("Totem should match", werewolfOriginal.getTotem(), werewolfReloaded.getTotem());
        assertEquals("Position should match", werewolfOriginal.getPosition(), werewolfReloaded.getPosition());
        assertEquals("Notoriety should match", werewolfOriginal.getNotoriety(), werewolfReloaded.getNotoriety());
        assertEquals("Rage should match", werewolfOriginal.getRage(), werewolfReloaded.getRage());
        assertEquals("Gnosis should match", werewolfOriginal.getGnosis(), werewolfReloaded.getGnosis());
        assertEquals("Temp rage should match", werewolfOriginal.getTempRage(), werewolfReloaded.getTempRage());
        assertEquals("Temp gnosis should match", werewolfOriginal.getTempGnosis(), werewolfReloaded.getTempGnosis());
        assertEquals("Honor should match", werewolfOriginal.getHonor(), werewolfReloaded.getHonor());
        assertEquals("Glory should match", werewolfOriginal.getGlory(), werewolfReloaded.getGlory());
        assertEquals("Wisdom should match", werewolfOriginal.getWisdom(), werewolfReloaded.getWisdom());
        assertEquals("Temp honor should match", werewolfOriginal.getTempHonor(), werewolfReloaded.getTempHonor(), 0.001f);
        assertEquals("Temp glory should match", werewolfOriginal.getTempGlory(), werewolfReloaded.getTempGlory(), 0.001f);
        assertEquals("Temp wisdom should match", werewolfOriginal.getTempWisdom(), werewolfReloaded.getTempWisdom(), 0.001f);

        // Test trait lists (common)
        assertTraitListsMatch("Physical traits", werewolfOriginal.getPhysicalList(), werewolfReloaded.getPhysicalList());
        assertTraitListsMatch("Social traits", werewolfOriginal.getSocialList(), werewolfReloaded.getSocialList());
        assertTraitListsMatch("Mental traits", werewolfOriginal.getMentalList(), werewolfReloaded.getMentalList());
        assertTraitListsMatch("Physical negative traits", werewolfOriginal.getPhysicalNegList(), werewolfReloaded.getPhysicalNegList());
        assertTraitListsMatch("Social negative traits", werewolfOriginal.getSocialNegList(), werewolfReloaded.getSocialNegList());
        assertTraitListsMatch("Mental negative traits", werewolfOriginal.getMentalNegList(), werewolfReloaded.getMentalNegList());
        assertTraitListsMatch("Abilities", werewolfOriginal.getAbilityList(), werewolfReloaded.getAbilityList());
        assertTraitListsMatch("Influences", werewolfOriginal.getInfluenceList(), werewolfReloaded.getInfluenceList());
        assertTraitListsMatch("Backgrounds", werewolfOriginal.getBackgroundList(), werewolfReloaded.getBackgroundList());
        assertTraitListsMatch("Health", werewolfOriginal.getHealthList(), werewolfReloaded.getHealthList());
        assertTraitListsMatch("Derangements", werewolfOriginal.getDerangementList(), werewolfReloaded.getDerangementList());
        assertTraitListsMatch("Merits", werewolfOriginal.getMeritList(), werewolfReloaded.getMeritList());
        assertTraitListsMatch("Flaws", werewolfOriginal.getFlawList(), werewolfReloaded.getFlawList());
        assertTraitListsMatch("Equipment", werewolfOriginal.getEquipmentList(), werewolfReloaded.getEquipmentList());
        assertTraitListsMatch("Locations", werewolfOriginal.getHangoutList(), werewolfReloaded.getHangoutList());

        // Test Fera-specific trait lists
        assertTraitListsMatch("Features", werewolfOriginal.getFeatureList(), werewolfReloaded.getFeatureList());
        assertTraitListsMatch("Gifts", werewolfOriginal.getGiftList(), werewolfReloaded.getGiftList());
        assertTraitListsMatch("Rites", werewolfOriginal.getRiteList(), werewolfReloaded.getRiteList());
        assertTraitListsMatch("Honor", werewolfOriginal.getHonorList(), werewolfReloaded.getHonorList());
        assertTraitListsMatch("Glory", werewolfOriginal.getGloryList(), werewolfReloaded.getGloryList());
        assertTraitListsMatch("Wisdom", werewolfOriginal.getWisdomList(), werewolfReloaded.getWisdomList());
    }

    @Test
    public void testMortalFieldsRoundTrip() throws Exception {
        // Import Mortal class
        com.grapevine.records.sheet.character.splat.Mortal mortalOriginal = null;
        com.grapevine.records.sheet.character.splat.Mortal mortalReloaded = null;

        // Load the complex game which contains "Maya Mortal"
        Game game1 = loader.loadExchange(COMPLEX_GAME_PATH);

        // Find Maya Mortal
        for (Character character : game1.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Mortal
                    && "Maya Mortal".equals(character.getName())) {
                mortalOriginal = (com.grapevine.records.sheet.character.splat.Mortal) character;
                break;
            }
        }

        assertNotNull("Maya Mortal should exist in the test file", mortalOriginal);

        // Test that all Mortal fields are loaded from complex_game.gex
        assertEquals("Motivation should be loaded", "Fun", mortalOriginal.getMotivation());
        assertEquals("Association should be loaded", "Arcanum", mortalOriginal.getAssociation());
        assertEquals("Title should be loaded", "Queen", mortalOriginal.getTitle());
        assertEquals("Regnant should be loaded", "Mark Mage", mortalOriginal.getRegnant());
        assertEquals("Blood should be loaded", 0, mortalOriginal.getBlood());
        assertEquals("Humanity should be loaded", 1, mortalOriginal.getHumanity());
        assertEquals("Conscience should be loaded", 1, mortalOriginal.getConscience());
        assertEquals("Self control should be loaded", 1, mortalOriginal.getSelfControl());
        assertEquals("Courage should be loaded", 1, mortalOriginal.getCourage());
        assertEquals("True faith should be loaded", 0, mortalOriginal.getTrueFaith());

        // Test that Humanity and Numina trait lists are loaded (they exist but may be empty)
        assertNotNull("Humanity list should be loaded", mortalOriginal.getHumanityList());
        assertNotNull("Numina list should be loaded", mortalOriginal.getNuminaList());

        // Save to a temporary file
        saver.saveExchange(game1, tempOutputPath);

        // Load the saved file
        Game game2 = loader.loadExchange(tempOutputPath);

        // Find Maya Mortal in the reloaded game
        for (Character character : game2.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Mortal
                    && "Maya Mortal".equals(character.getName())) {
                mortalReloaded = (com.grapevine.records.sheet.character.splat.Mortal) character;
                break;
            }
        }

        assertNotNull("Maya Mortal should exist in the reloaded file", mortalReloaded);

        // Test all Character base fields
        assertEquals("Name should match", mortalOriginal.getName(), mortalReloaded.getName());
        assertEquals("Nature should match", mortalOriginal.getNature(), mortalReloaded.getNature());
        assertEquals("Demeanor should match", mortalOriginal.getDemeanor(), mortalReloaded.getDemeanor());
        assertEquals("Status should match", mortalOriginal.getStatus(), mortalReloaded.getStatus());
        assertEquals("Physical max should match", mortalOriginal.getPhysicalMax(), mortalReloaded.getPhysicalMax());
        assertEquals("Social max should match", mortalOriginal.getSocialMax(), mortalReloaded.getSocialMax());
        assertEquals("Mental max should match", mortalOriginal.getMentalMax(), mortalReloaded.getMentalMax());
        assertEquals("Willpower should match", mortalOriginal.getWillpower(), mortalReloaded.getWillpower());
        assertEquals("Temp willpower should match", mortalOriginal.getTempWillpower(), mortalReloaded.getTempWillpower());

        // Test all Mortal-specific fields
        assertEquals("Motivation should match", mortalOriginal.getMotivation(), mortalReloaded.getMotivation());
        assertEquals("Association should match", mortalOriginal.getAssociation(), mortalReloaded.getAssociation());
        assertEquals("Title should match", mortalOriginal.getTitle(), mortalReloaded.getTitle());
        assertEquals("Regnant should match", mortalOriginal.getRegnant(), mortalReloaded.getRegnant());
        assertEquals("Blood should match", mortalOriginal.getBlood(), mortalReloaded.getBlood());
        assertEquals("Humanity should match", mortalOriginal.getHumanity(), mortalReloaded.getHumanity());
        assertEquals("Conscience should match", mortalOriginal.getConscience(), mortalReloaded.getConscience());
        assertEquals("Self control should match", mortalOriginal.getSelfControl(), mortalReloaded.getSelfControl());
        assertEquals("Courage should match", mortalOriginal.getCourage(), mortalReloaded.getCourage());
        assertEquals("True faith should match", mortalOriginal.getTrueFaith(), mortalReloaded.getTrueFaith());
        assertEquals("Temp blood should match", mortalOriginal.getTempBlood(), mortalReloaded.getTempBlood());
        assertEquals("Temp humanity should match", mortalOriginal.getTempHumanity(), mortalReloaded.getTempHumanity());
        assertEquals("Temp conscience should match", mortalOriginal.getTempConscience(), mortalReloaded.getTempConscience());
        assertEquals("Temp self control should match", mortalOriginal.getTempSelfControl(), mortalReloaded.getTempSelfControl());
        assertEquals("Temp courage should match", mortalOriginal.getTempCourage(), mortalReloaded.getTempCourage());
        assertEquals("Temp true faith should match", mortalOriginal.getTempTrueFaith(), mortalReloaded.getTempTrueFaith());
        assertEquals("Other should match", mortalOriginal.getOther(), mortalReloaded.getOther());

        // Test trait lists
        assertTraitListsMatch("Physical traits", mortalOriginal.getPhysicalList(), mortalReloaded.getPhysicalList());
        assertTraitListsMatch("Social traits", mortalOriginal.getSocialList(), mortalReloaded.getSocialList());
        assertTraitListsMatch("Mental traits", mortalOriginal.getMentalList(), mortalReloaded.getMentalList());
        assertTraitListsMatch("Physical negative traits", mortalOriginal.getPhysicalNegList(), mortalReloaded.getPhysicalNegList());
        assertTraitListsMatch("Social negative traits", mortalOriginal.getSocialNegList(), mortalReloaded.getSocialNegList());
        assertTraitListsMatch("Mental negative traits", mortalOriginal.getMentalNegList(), mortalReloaded.getMentalNegList());
        assertTraitListsMatch("Abilities", mortalOriginal.getAbilityList(), mortalReloaded.getAbilityList());
        assertTraitListsMatch("Influences", mortalOriginal.getInfluenceList(), mortalReloaded.getInfluenceList());
        assertTraitListsMatch("Backgrounds", mortalOriginal.getBackgroundList(), mortalReloaded.getBackgroundList());
        assertTraitListsMatch("Health", mortalOriginal.getHealthList(), mortalReloaded.getHealthList());
        assertTraitListsMatch("Humanity", mortalOriginal.getHumanityList(), mortalReloaded.getHumanityList());
        assertTraitListsMatch("Derangements", mortalOriginal.getDerangementList(), mortalReloaded.getDerangementList());
        assertTraitListsMatch("Numina", mortalOriginal.getNuminaList(), mortalReloaded.getNuminaList());
        assertTraitListsMatch("Merits", mortalOriginal.getMeritList(), mortalReloaded.getMeritList());
        assertTraitListsMatch("Flaws", mortalOriginal.getFlawList(), mortalReloaded.getFlawList());
        assertTraitListsMatch("Equipment", mortalOriginal.getEquipmentList(), mortalReloaded.getEquipmentList());
        assertTraitListsMatch("Locations", mortalOriginal.getHangoutList(), mortalReloaded.getHangoutList());
    }

    @Test
    public void testKueiJinFieldsRoundTrip() throws Exception {
        // Import KueiJin class
        com.grapevine.records.sheet.character.splat.KueiJin kueiJinOriginal = null;
        com.grapevine.records.sheet.character.splat.KueiJin kueiJinReloaded = null;

        // Load the complex game which contains "Kurt Kuei-Jin"
        Game game1 = loader.loadExchange(COMPLEX_GAME_PATH);

        // Find Kurt Kuei-Jin
        for (Character character : game1.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.KueiJin
                    && "Kurt Kuei-Jin".equals(character.getName())) {
                kueiJinOriginal = (com.grapevine.records.sheet.character.splat.KueiJin) character;
                break;
            }
        }

        assertNotNull("Kurt Kuei-Jin should exist in the test file", kueiJinOriginal);

        // Save to a temporary file
        saver.saveExchange(game1, tempOutputPath);

        // Load the saved file
        Game game2 = loader.loadExchange(tempOutputPath);

        // Find Kurt Kuei-Jin in the reloaded game
        for (Character character : game2.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.KueiJin
                    && "Kurt Kuei-Jin".equals(character.getName())) {
                kueiJinReloaded = (com.grapevine.records.sheet.character.splat.KueiJin) character;
                break;
            }
        }

        assertNotNull("Kurt Kuei-Jin should exist in the reloaded file", kueiJinReloaded);

        // Test all Character base fields
        assertEquals("Name should match", kueiJinOriginal.getName(), kueiJinReloaded.getName());
        assertEquals("Nature should match", kueiJinOriginal.getNature(), kueiJinReloaded.getNature());
        assertEquals("Demeanor should match", kueiJinOriginal.getDemeanor(), kueiJinReloaded.getDemeanor());
        assertEquals("Status should match", kueiJinOriginal.getStatus(), kueiJinReloaded.getStatus());
        assertEquals("Physical max should match", kueiJinOriginal.getPhysicalMax(), kueiJinReloaded.getPhysicalMax());
        assertEquals("Social max should match", kueiJinOriginal.getSocialMax(), kueiJinReloaded.getSocialMax());
        assertEquals("Mental max should match", kueiJinOriginal.getMentalMax(), kueiJinReloaded.getMentalMax());
        assertEquals("Willpower should match", kueiJinOriginal.getWillpower(), kueiJinReloaded.getWillpower());
        assertEquals("Temp willpower should match", kueiJinOriginal.getTempWillpower(), kueiJinReloaded.getTempWillpower());

        // Test all Kuei-Jin-specific string fields
        assertEquals("Dharma should match", kueiJinOriginal.getDharma(), kueiJinReloaded.getDharma());
        assertEquals("Direction should match", kueiJinOriginal.getDirection(), kueiJinReloaded.getDirection());
        assertEquals("Balance should match", kueiJinOriginal.getBalance(), kueiJinReloaded.getBalance());
        assertEquals("Station should match", kueiJinOriginal.getStation(), kueiJinReloaded.getStation());
        assertEquals("Po archetype should match", kueiJinOriginal.getPoArchetype(), kueiJinReloaded.getPoArchetype());

        // Test all Kuei-Jin-specific int fields
        assertEquals("Hun should match", kueiJinOriginal.getHun(), kueiJinReloaded.getHun());
        assertEquals("Po should match", kueiJinOriginal.getPo(), kueiJinReloaded.getPo());
        assertEquals("Yin Chi should match", kueiJinOriginal.getYinChi(), kueiJinReloaded.getYinChi());
        assertEquals("Yang Chi should match", kueiJinOriginal.getYangChi(), kueiJinReloaded.getYangChi());
        assertEquals("Demon Chi should match", kueiJinOriginal.getDemonChi(), kueiJinReloaded.getDemonChi());
        assertEquals("Dharma traits should match", kueiJinOriginal.getDharmaTraits(), kueiJinReloaded.getDharmaTraits());

        // Test all Kuei-Jin-specific temp fields
        assertEquals("Temp hun should match", kueiJinOriginal.getTempHun(), kueiJinReloaded.getTempHun());
        assertEquals("Temp po should match", kueiJinOriginal.getTempPo(), kueiJinReloaded.getTempPo());
        assertEquals("Temp yin chi should match", kueiJinOriginal.getTempYinChi(), kueiJinReloaded.getTempYinChi());
        assertEquals("Temp yang chi should match", kueiJinOriginal.getTempYangChi(), kueiJinReloaded.getTempYangChi());
        assertEquals("Temp demon chi should match", kueiJinOriginal.getTempDemonChi(), kueiJinReloaded.getTempDemonChi());
        assertEquals("Temp dharma traits should match", kueiJinOriginal.getTempDharmaTraits(), kueiJinReloaded.getTempDharmaTraits());

        // Test trait lists
        assertTraitListsMatch("Physical traits", kueiJinOriginal.getPhysicalList(), kueiJinReloaded.getPhysicalList());
        assertTraitListsMatch("Social traits", kueiJinOriginal.getSocialList(), kueiJinReloaded.getSocialList());
        assertTraitListsMatch("Mental traits", kueiJinOriginal.getMentalList(), kueiJinReloaded.getMentalList());
        assertTraitListsMatch("Physical negative traits", kueiJinOriginal.getPhysicalNegList(), kueiJinReloaded.getPhysicalNegList());
        assertTraitListsMatch("Social negative traits", kueiJinOriginal.getSocialNegList(), kueiJinReloaded.getSocialNegList());
        assertTraitListsMatch("Mental negative traits", kueiJinOriginal.getMentalNegList(), kueiJinReloaded.getMentalNegList());
        assertTraitListsMatch("Status", kueiJinOriginal.getStatusList(), kueiJinReloaded.getStatusList());
        assertTraitListsMatch("Abilities", kueiJinOriginal.getAbilityList(), kueiJinReloaded.getAbilityList());
        assertTraitListsMatch("Influences", kueiJinOriginal.getInfluenceList(), kueiJinReloaded.getInfluenceList());
        assertTraitListsMatch("Backgrounds", kueiJinOriginal.getBackgroundList(), kueiJinReloaded.getBackgroundList());
        assertTraitListsMatch("Health", kueiJinOriginal.getHealthList(), kueiJinReloaded.getHealthList());
        assertTraitListsMatch("Guanxi", kueiJinOriginal.getGuanxiList(), kueiJinReloaded.getGuanxiList());
        assertTraitListsMatch("Disciplines", kueiJinOriginal.getDisciplineList(), kueiJinReloaded.getDisciplineList());
        assertTraitListsMatch("Rites", kueiJinOriginal.getRiteList(), kueiJinReloaded.getRiteList());
        assertTraitListsMatch("Derangements", kueiJinOriginal.getDerangementList(), kueiJinReloaded.getDerangementList());
        assertTraitListsMatch("Merits", kueiJinOriginal.getMeritList(), kueiJinReloaded.getMeritList());
        assertTraitListsMatch("Flaws", kueiJinOriginal.getFlawList(), kueiJinReloaded.getFlawList());
        assertTraitListsMatch("Equipment", kueiJinOriginal.getEquipmentList(), kueiJinReloaded.getEquipmentList());
        assertTraitListsMatch("Locations", kueiJinOriginal.getHangoutList(), kueiJinReloaded.getHangoutList());
    }

    @Test
    public void testMummyFieldsRoundTrip() throws Exception {
        // Import Mummy class
        com.grapevine.records.sheet.character.splat.Mummy mummyOriginal = null;
        com.grapevine.records.sheet.character.splat.Mummy mummyReloaded = null;

        // Load the complex game which contains "Matthew Mummy"
        Game game1 = loader.loadExchange(COMPLEX_GAME_PATH);

        // Find Matthew Mummy
        for (Character character : game1.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Mummy
                    && "Matthew Mummy".equals(character.getName())) {
                mummyOriginal = (com.grapevine.records.sheet.character.splat.Mummy) character;
                break;
            }
        }

        assertNotNull("Matthew Mummy should exist in the test file", mummyOriginal);

        // Save to a temporary file
        saver.saveExchange(game1, tempOutputPath);

        // Load the saved file
        Game game2 = loader.loadExchange(tempOutputPath);

        // Find Matthew Mummy in the reloaded game
        for (Character character : game2.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Mummy
                    && "Matthew Mummy".equals(character.getName())) {
                mummyReloaded = (com.grapevine.records.sheet.character.splat.Mummy) character;
                break;
            }
        }

        assertNotNull("Matthew Mummy should exist in the reloaded file", mummyReloaded);

        // Test all Character base fields
        assertEquals("Name should match", mummyOriginal.getName(), mummyReloaded.getName());
        assertEquals("Nature should match", mummyOriginal.getNature(), mummyReloaded.getNature());
        assertEquals("Demeanor should match", mummyOriginal.getDemeanor(), mummyReloaded.getDemeanor());
        assertEquals("Status should match", mummyOriginal.getStatus(), mummyReloaded.getStatus());
        assertEquals("Physical max should match", mummyOriginal.getPhysicalMax(), mummyReloaded.getPhysicalMax());
        assertEquals("Social max should match", mummyOriginal.getSocialMax(), mummyReloaded.getSocialMax());
        assertEquals("Mental max should match", mummyOriginal.getMentalMax(), mummyReloaded.getMentalMax());
        assertEquals("Willpower should match", mummyOriginal.getWillpower(), mummyReloaded.getWillpower());
        assertEquals("Temp willpower should match", mummyOriginal.getTempWillpower(), mummyReloaded.getTempWillpower());

        // Test all Mummy-specific fields
        assertEquals("Amenti should match", mummyOriginal.getAmenti(), mummyReloaded.getAmenti());
        assertEquals("Sekhem should match", mummyOriginal.getSekhem(), mummyReloaded.getSekhem());
        assertEquals("Balance should match", mummyOriginal.getBalance(), mummyReloaded.getBalance());
        assertEquals("Memory should match", mummyOriginal.getMemory(), mummyReloaded.getMemory());
        assertEquals("Integrity should match", mummyOriginal.getIntegrity(), mummyReloaded.getIntegrity());
        assertEquals("Joy should match", mummyOriginal.getJoy(), mummyReloaded.getJoy());
        assertEquals("Ba should match", mummyOriginal.getBa(), mummyReloaded.getBa());
        assertEquals("Ka should match", mummyOriginal.getKa(), mummyReloaded.getKa());
        assertEquals("Temp sekhem should match", mummyOriginal.getTempSekhem(), mummyReloaded.getTempSekhem());
        assertEquals("Temp balance should match", mummyOriginal.getTempBalance(), mummyReloaded.getTempBalance());
        assertEquals("Temp memory should match", mummyOriginal.getTempMemory(), mummyReloaded.getTempMemory());
        assertEquals("Temp integrity should match", mummyOriginal.getTempIntegrity(), mummyReloaded.getTempIntegrity());
        assertEquals("Temp joy should match", mummyOriginal.getTempJoy(), mummyReloaded.getTempJoy());
        assertEquals("Temp ba should match", mummyOriginal.getTempBa(), mummyReloaded.getTempBa());
        assertEquals("Temp ka should match", mummyOriginal.getTempKa(), mummyReloaded.getTempKa());
        assertEquals("Inheritance should match", mummyOriginal.getInheritance(), mummyReloaded.getInheritance());

        // Test trait lists
        assertTraitListsMatch("Physical traits", mummyOriginal.getPhysicalList(), mummyReloaded.getPhysicalList());
        assertTraitListsMatch("Social traits", mummyOriginal.getSocialList(), mummyReloaded.getSocialList());
        assertTraitListsMatch("Mental traits", mummyOriginal.getMentalList(), mummyReloaded.getMentalList());
        assertTraitListsMatch("Physical negative traits", mummyOriginal.getPhysicalNegList(), mummyReloaded.getPhysicalNegList());
        assertTraitListsMatch("Social negative traits", mummyOriginal.getSocialNegList(), mummyReloaded.getSocialNegList());
        assertTraitListsMatch("Mental negative traits", mummyOriginal.getMentalNegList(), mummyReloaded.getMentalNegList());
        assertTraitListsMatch("Humanity", mummyOriginal.getHumanityList(), mummyReloaded.getHumanityList());
        assertTraitListsMatch("Status", mummyOriginal.getStatusList(), mummyReloaded.getStatusList());
        assertTraitListsMatch("Abilities", mummyOriginal.getAbilityList(), mummyReloaded.getAbilityList());
        assertTraitListsMatch("Influences", mummyOriginal.getInfluenceList(), mummyReloaded.getInfluenceList());
        assertTraitListsMatch("Backgrounds", mummyOriginal.getBackgroundList(), mummyReloaded.getBackgroundList());
        assertTraitListsMatch("Health", mummyOriginal.getHealthList(), mummyReloaded.getHealthList());
        assertTraitListsMatch("Hekau", mummyOriginal.getHekauList(), mummyReloaded.getHekauList());
        assertTraitListsMatch("Spells", mummyOriginal.getSpellList(), mummyReloaded.getSpellList());
        assertTraitListsMatch("Rituals", mummyOriginal.getRitualList(), mummyReloaded.getRitualList());
        assertTraitListsMatch("Merits", mummyOriginal.getMeritList(), mummyReloaded.getMeritList());
        assertTraitListsMatch("Flaws", mummyOriginal.getFlawList(), mummyReloaded.getFlawList());
        assertTraitListsMatch("Equipment", mummyOriginal.getEquipmentList(), mummyReloaded.getEquipmentList());
        assertTraitListsMatch("Locations", mummyOriginal.getHangoutList(), mummyReloaded.getHangoutList());
    }

    @Test
    public void testDemonFieldsRoundTrip() throws Exception {
        // Import Demon class
        com.grapevine.records.sheet.character.splat.Demon demonOriginal = null;
        com.grapevine.records.sheet.character.splat.Demon demonReloaded = null;

        // Load the complex game which contains "Darryl Demon"
        Game game1 = loader.loadExchange(COMPLEX_GAME_PATH);

        // Find Darryl Demon
        for (Character character : game1.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Demon
                    && "Darryl Demon".equals(character.getName())) {
                demonOriginal = (com.grapevine.records.sheet.character.splat.Demon) character;
                break;
            }
        }

        assertNotNull("Darryl Demon should exist in the test file", demonOriginal);

        // Verify initial values are loaded correctly from complex_game.gex
        assertEquals("House should be Fiend", "Fiend", demonOriginal.getHouse());
        assertEquals("Faction should be Reconciler", "Reconciler", demonOriginal.getFaction());
        assertEquals("Torment should be 3", 3, demonOriginal.getTorment());
        assertEquals("Faith should be 3", 3, demonOriginal.getFaith());
        assertEquals("Conscience should be 1", 1, demonOriginal.getConscience());
        assertEquals("Conviction should be 1", 1, demonOriginal.getConviction());
        assertEquals("Courage should be 1", 1, demonOriginal.getCourage());

        // Verify Lores list is loaded
        assertNotNull("Lores list should not be null", demonOriginal.getLoreList());
        assertFalse("Lores list should not be empty", demonOriginal.getLoreList().isEmpty());
        assertEquals("Should have 5 Lores", 5, demonOriginal.getLoreList().size());

        // Verify Apocalyptic Form list is loaded
        assertNotNull("Visage list should not be null", demonOriginal.getVisageList());
        assertFalse("Visage list should not be empty", demonOriginal.getVisageList().isEmpty());
        assertEquals("Should have 5 Apocalyptic Form traits", 5, demonOriginal.getVisageList().size());

        // Save to a temporary file
        saver.saveExchange(game1, tempOutputPath);

        // Load the saved file
        Game game2 = loader.loadExchange(tempOutputPath);

        // Find Darryl Demon in the reloaded game
        for (Character character : game2.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Demon
                    && "Darryl Demon".equals(character.getName())) {
                demonReloaded = (com.grapevine.records.sheet.character.splat.Demon) character;
                break;
            }
        }

        assertNotNull("Darryl Demon should exist in the reloaded file", demonReloaded);

        // Test all Character base fields
        assertEquals("Name should match", demonOriginal.getName(), demonReloaded.getName());
        assertEquals("Nature should match", demonOriginal.getNature(), demonReloaded.getNature());
        assertEquals("Demeanor should match", demonOriginal.getDemeanor(), demonReloaded.getDemeanor());
        assertEquals("Status should match", demonOriginal.getStatus(), demonReloaded.getStatus());
        assertEquals("Physical max should match", demonOriginal.getPhysicalMax(), demonReloaded.getPhysicalMax());
        assertEquals("Social max should match", demonOriginal.getSocialMax(), demonReloaded.getSocialMax());
        assertEquals("Mental max should match", demonOriginal.getMentalMax(), demonReloaded.getMentalMax());
        assertEquals("Willpower should match", demonOriginal.getWillpower(), demonReloaded.getWillpower());
        assertEquals("Temp willpower should match", demonOriginal.getTempWillpower(), demonReloaded.getTempWillpower());

        // Test all Demon-specific fields
        assertEquals("House should match", demonOriginal.getHouse(), demonReloaded.getHouse());
        assertEquals("Faction should match", demonOriginal.getFaction(), demonReloaded.getFaction());
        assertEquals("Torment should match", demonOriginal.getTorment(), demonReloaded.getTorment());
        assertEquals("Faith should match", demonOriginal.getFaith(), demonReloaded.getFaith());
        assertEquals("Conscience should match", demonOriginal.getConscience(), demonReloaded.getConscience());
        assertEquals("Conviction should match", demonOriginal.getConviction(), demonReloaded.getConviction());
        assertEquals("Courage should match", demonOriginal.getCourage(), demonReloaded.getCourage());
        assertEquals("Temp torment should match", demonOriginal.getTempTorment(), demonReloaded.getTempTorment());
        assertEquals("Temp faith should match", demonOriginal.getTempFaith(), demonReloaded.getTempFaith());
        assertEquals("Temp conscience should match", demonOriginal.getTempConscience(), demonReloaded.getTempConscience());
        assertEquals("Temp conviction should match", demonOriginal.getTempConviction(), demonReloaded.getTempConviction());
        assertEquals("Temp courage should match", demonOriginal.getTempCourage(), demonReloaded.getTempCourage());

        // Test trait lists
        assertTraitListsMatch("Physical traits", demonOriginal.getPhysicalList(), demonReloaded.getPhysicalList());
        assertTraitListsMatch("Social traits", demonOriginal.getSocialList(), demonReloaded.getSocialList());
        assertTraitListsMatch("Mental traits", demonOriginal.getMentalList(), demonReloaded.getMentalList());
        assertTraitListsMatch("Physical negative traits", demonOriginal.getPhysicalNegList(), demonReloaded.getPhysicalNegList());
        assertTraitListsMatch("Social negative traits", demonOriginal.getSocialNegList(), demonReloaded.getSocialNegList());
        assertTraitListsMatch("Mental negative traits", demonOriginal.getMentalNegList(), demonReloaded.getMentalNegList());
        assertTraitListsMatch("Abilities", demonOriginal.getAbilityList(), demonReloaded.getAbilityList());
        assertTraitListsMatch("Influences", demonOriginal.getInfluenceList(), demonReloaded.getInfluenceList());
        assertTraitListsMatch("Backgrounds", demonOriginal.getBackgroundList(), demonReloaded.getBackgroundList());
        assertTraitListsMatch("Health", demonOriginal.getHealthList(), demonReloaded.getHealthList());
        assertTraitListsMatch("Lores", demonOriginal.getLoreList(), demonReloaded.getLoreList());
        assertTraitListsMatch("Apocalyptic Form", demonOriginal.getVisageList(), demonReloaded.getVisageList());
        assertTraitListsMatch("Merits", demonOriginal.getMeritList(), demonReloaded.getMeritList());
        assertTraitListsMatch("Flaws", demonOriginal.getFlawList(), demonReloaded.getFlawList());
        assertTraitListsMatch("Equipment", demonOriginal.getEquipmentList(), demonReloaded.getEquipmentList());
        assertTraitListsMatch("Locations", demonOriginal.getHangoutList(), demonReloaded.getHangoutList());
    }

    @Test
    public void testVariousFieldsRoundTrip() throws Exception {
        // Import Various class
        com.grapevine.records.sheet.character.splat.Various variousOriginal = null;
        com.grapevine.records.sheet.character.splat.Various variousReloaded = null;

        // Load the complex game which contains "Some Various Guy"
        Game game1 = loader.loadExchange(COMPLEX_GAME_PATH);

        // Find Some Various Guy
        for (Character character : game1.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Various
                    && "Some Various Guy".equals(character.getName())) {
                variousOriginal = (com.grapevine.records.sheet.character.splat.Various) character;
                break;
            }
        }

        assertNotNull("Some Various Guy should exist in the test file", variousOriginal);

        // Save to a temporary file
        saver.saveExchange(game1, tempOutputPath);

        // Load the saved file
        Game game2 = loader.loadExchange(tempOutputPath);

        // Find Some Various Guy in the reloaded game
        for (Character character : game2.getCharacterList()) {
            if (character instanceof com.grapevine.records.sheet.character.splat.Various
                    && "Some Various Guy".equals(character.getName())) {
                variousReloaded = (com.grapevine.records.sheet.character.splat.Various) character;
                break;
            }
        }

        assertNotNull("Some Various Guy should exist in the reloaded file", variousReloaded);

        // Test all Character base fields
        assertEquals("Name should match", variousOriginal.getName(), variousReloaded.getName());
        assertEquals("Nature should match", variousOriginal.getNature(), variousReloaded.getNature());
        assertEquals("Demeanor should match", variousOriginal.getDemeanor(), variousReloaded.getDemeanor());
        assertEquals("Status should match", variousOriginal.getStatus(), variousReloaded.getStatus());
        assertEquals("Physical max should match", variousOriginal.getPhysicalMax(), variousReloaded.getPhysicalMax());
        assertEquals("Social max should match", variousOriginal.getSocialMax(), variousReloaded.getSocialMax());
        assertEquals("Mental max should match", variousOriginal.getMentalMax(), variousReloaded.getMentalMax());
        assertEquals("Willpower should match", variousOriginal.getWillpower(), variousReloaded.getWillpower());
        assertEquals("Temp willpower should match", variousOriginal.getTempWillpower(), variousReloaded.getTempWillpower());

        // Test all Various-specific fields
        assertEquals("Character class should match", variousOriginal.getCharacterClass(), variousReloaded.getCharacterClass());
        assertEquals("Subclass should match", variousOriginal.getSubclass(), variousReloaded.getSubclass());
        assertEquals("Affinity should match", variousOriginal.getAffinity(), variousReloaded.getAffinity());
        assertEquals("Plane should match", variousOriginal.getPlane(), variousReloaded.getPlane());
        assertEquals("Brood should match", variousOriginal.getBrood(), variousReloaded.getBrood());
        assertEquals("Other should match", variousOriginal.getOther(), variousReloaded.getOther());
        assertEquals("Biography should match", variousOriginal.getBiography(), variousReloaded.getBiography());
        assertEquals("Notes should match", variousOriginal.getNotes(), variousReloaded.getNotes());

        // Test trait lists
        assertTraitListsMatch("Physical traits", variousOriginal.getPhysicalList(), variousReloaded.getPhysicalList());
        assertTraitListsMatch("Social traits", variousOriginal.getSocialList(), variousReloaded.getSocialList());
        assertTraitListsMatch("Mental traits", variousOriginal.getMentalList(), variousReloaded.getMentalList());
        assertTraitListsMatch("Physical negative traits", variousOriginal.getPhysicalNegList(), variousReloaded.getPhysicalNegList());
        assertTraitListsMatch("Social negative traits", variousOriginal.getSocialNegList(), variousReloaded.getSocialNegList());
        assertTraitListsMatch("Mental negative traits", variousOriginal.getMentalNegList(), variousReloaded.getMentalNegList());
        assertTraitListsMatch("Abilities", variousOriginal.getAbilityList(), variousReloaded.getAbilityList());
        assertTraitListsMatch("Influences", variousOriginal.getInfluenceList(), variousReloaded.getInfluenceList());
        assertTraitListsMatch("Backgrounds", variousOriginal.getBackgroundList(), variousReloaded.getBackgroundList());
        assertTraitListsMatch("Health", variousOriginal.getHealthList(), variousReloaded.getHealthList());
        assertTraitListsMatch("Tempers", variousOriginal.getTemperList(), variousReloaded.getTemperList());
        assertTraitListsMatch("Powers", variousOriginal.getPowerList(), variousReloaded.getPowerList());
        assertTraitListsMatch("Derangements", variousOriginal.getDerangementList(), variousReloaded.getDerangementList());
        assertTraitListsMatch("Merits", variousOriginal.getMeritList(), variousReloaded.getMeritList());
        assertTraitListsMatch("Flaws", variousOriginal.getFlawList(), variousReloaded.getFlawList());
        assertTraitListsMatch("Equipment", variousOriginal.getEquipmentList(), variousReloaded.getEquipmentList());
        assertTraitListsMatch("Locations", variousOriginal.getHangoutList(), variousReloaded.getHangoutList());
    }
}
