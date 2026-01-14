package com.grapevine.io.files;

import com.grapevine.records.game.Game;
import com.grapevine.records.player.Player;
import com.grapevine.records.sheet.Item;
import com.grapevine.records.sheet.Rote;
import com.grapevine.records.sheet.Trait;
import com.grapevine.records.sheet.character.Character;
import com.grapevine.records.sheet.character.splat.*;
import com.grapevine.records.story.Action;
import com.grapevine.records.story.Location;
import com.grapevine.records.story.Rumor;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Loads Game data from an XML exchange file (.gex).
 * Compatible with files produced by SaveExchangeXML in GameClass.cls.
 */
public class ExchangeLoader {

    private static final Logger logger = Logger.getLogger(ExchangeLoader.class.getName());

    /**
     * Load a Game from a .gex file.
     *
     * @param filePath Path to the .gex file
     * @return Game object populated with data from the file
     * @throws Exception if the file cannot be loaded or parsed
     */
    public Game loadExchange(String filePath) throws Exception {
        Document doc = FileUtils.parseXmlFile(filePath);
        Element root = doc.getDocumentElement();

        if (!"grapevine".equals(root.getTagName())) {
            throw new IllegalArgumentException("Not a valid Grapevine file");
        }

        Game game = new Game();

        // Parse all child elements
        List<Element> children = FileUtils.getDirectChildElements(root);

        for (Element element : children) {
            String tagName = element.getTagName();

            try {
                switch (tagName) {
                    case "calendar":
                        // Skip calendar for now - not required by issue
                        logger.warning("Skipping calendar element");
                        break;

                    case "aprsettings":
                        // Skip APR settings - not required by issue
                        logger.warning("Skipping aprsettings element");
                        break;

                    case "award":
                        // Skip awards - not required by issue
                        break;

                    case "template":
                        // Skip templates - not required by issue
                        break;

                    case "query":
                        // Skip queries - mentioned in issue as ignorable
                        logger.warning("Skipping query element");
                        break;

                    case "player":
                        if (game.getPlayerList() == null) {
                            game.setPlayerList(new ArrayList<>());
                        }
                        game.getPlayerList().add(loadPlayer(element));
                        break;

                    case "vampire":
                        if (game.getCharacterList() == null) {
                            game.setCharacterList(new ArrayList<>());
                        }
                        game.getCharacterList().add(loadVampire(element));
                        break;

                    case "werewolf":
                        if (game.getCharacterList() == null) {
                            game.setCharacterList(new ArrayList<>());
                        }
                        game.getCharacterList().add(loadWerewolf(element));
                        break;

                    case "mage":
                        if (game.getCharacterList() == null) {
                            game.setCharacterList(new ArrayList<>());
                        }
                        game.getCharacterList().add(loadMage(element));
                        break;

                    case "changeling":
                        if (game.getCharacterList() == null) {
                            game.setCharacterList(new ArrayList<>());
                        }
                        game.getCharacterList().add(loadChangeling(element));
                        break;

                    case "wraith":
                        if (game.getCharacterList() == null) {
                            game.setCharacterList(new ArrayList<>());
                        }
                        game.getCharacterList().add(loadWraith(element));
                        break;

                    case "mortal":
                        if (game.getCharacterList() == null) {
                            game.setCharacterList(new ArrayList<>());
                        }
                        game.getCharacterList().add(loadMortal(element));
                        break;

                    case "mummy":
                        if (game.getCharacterList() == null) {
                            game.setCharacterList(new ArrayList<>());
                        }
                        game.getCharacterList().add(loadMummy(element));
                        break;

                    case "kueijin":
                        if (game.getCharacterList() == null) {
                            game.setCharacterList(new ArrayList<>());
                        }
                        game.getCharacterList().add(loadKueiJin(element));
                        break;

                    case "fera":
                        if (game.getCharacterList() == null) {
                            game.setCharacterList(new ArrayList<>());
                        }
                        game.getCharacterList().add(loadFera(element));
                        break;

                    case "various":
                        if (game.getCharacterList() == null) {
                            game.setCharacterList(new ArrayList<>());
                        }
                        game.getCharacterList().add(loadVarious(element));
                        break;

                    case "hunter":
                        if (game.getCharacterList() == null) {
                            game.setCharacterList(new ArrayList<>());
                        }
                        game.getCharacterList().add(loadHunter(element));
                        break;

                    case "demon":
                        if (game.getCharacterList() == null) {
                            game.setCharacterList(new ArrayList<>());
                        }
                        game.getCharacterList().add(loadDemon(element));
                        break;

                    case "item":
                        if (game.getItemList() == null) {
                            game.setItemList(new ArrayList<>());
                        }
                        game.getItemList().add(loadItem(element));
                        break;

                    case "rote":
                        if (game.getRoteList() == null) {
                            game.setRoteList(new ArrayList<>());
                        }
                        game.getRoteList().add(loadRote(element));
                        break;

                    case "location":
                        if (game.getLocationList() == null) {
                            game.setLocationList(new ArrayList<>());
                        }
                        game.getLocationList().add(loadLocation(element));
                        break;

                    case "action":
                        if (game.getInfluenceUseList() == null) {
                            game.setInfluenceUseList(new ArrayList<>());
                        }
                        game.getInfluenceUseList().add(loadAction(element));
                        break;

                    case "plot":
                        // Plot is not in the Game class, but we need to count it
                        // We'll skip storing it for now
                        break;

                    case "rumor":
                        if (game.getAllRumorLists() == null) {
                            game.setAllRumorLists(new ArrayList<>());
                        }
                        game.getAllRumorLists().add(loadRumor(element));
                        break;

                    default:
                        logger.warning("Unknown element type: " + tagName);
                        break;
                }
            } catch (Exception e) {
                logger.severe("Error loading " + tagName + ": " + e.getMessage());
                // Continue processing other elements
            }
        }

        return game;
    }

    private Player loadPlayer(Element element) {
        Player player = new Player();
        player.setName(FileUtils.getAttribute(element, "name"));
        player.setId(FileUtils.getAttribute(element, "id"));
        player.setEmail(FileUtils.getAttribute(element, "email"));
        player.setPhone(FileUtils.getAttribute(element, "phone"));
        player.setPosition(FileUtils.getAttribute(element, "position", "Player"));
        player.setStatus(FileUtils.getAttribute(element, "status", "Active"));
        player.setLastModified(FileUtils.getDateAttribute(element, "lastmodified"));

        // Parse child elements
        Element addressElement = FileUtils.getFirstChildElement(element, "address");
        if (addressElement != null) {
            player.setAddress(FileUtils.getTextContent(addressElement));
        }

        Element notesElement = FileUtils.getFirstChildElement(element, "notes");
        if (notesElement != null) {
            player.setNotes(FileUtils.getTextContent(notesElement));
        }

        return player;
    }

    private Character loadVampire(Element element) {
        Vampire vampire = new Vampire();
        loadCharacterBasics(vampire, element);
        loadVampireSpecifics(vampire, element);
        loadCharacterTraitLists(vampire, element);
        return vampire;
    }

    private Character loadWerewolf(Element element) {
        Werewolf werewolf = new Werewolf();
        loadCharacterBasics(werewolf, element);
        loadFeraSpecifics(werewolf, element);
        loadWerewolfSpecifics(werewolf, element);
        loadCharacterTraitLists(werewolf, element);
        return werewolf;
    }

    private Character loadMage(Element element) {
        Mage mage = new Mage();
        loadCharacterBasics(mage, element);
        loadMageSpecifics(mage, element);
        loadCharacterTraitLists(mage, element);
        return mage;
    }

    private Character loadChangeling(Element element) {
        Changeling changeling = new Changeling();
        loadCharacterBasics(changeling, element);
        loadChangelingSpecifics(changeling, element);
        loadCharacterTraitLists(changeling, element);
        return changeling;
    }

    private Character loadWraith(Element element) {
        Wraith wraith = new Wraith();
        loadCharacterBasics(wraith, element);
        loadWraithSpecifics(wraith, element);
        loadCharacterTraitLists(wraith, element);
        return wraith;
    }

    private Character loadMortal(Element element) {
        Mortal mortal = new Mortal();
        loadCharacterBasics(mortal, element);
        loadMortalSpecifics(mortal, element);
        loadCharacterTraitLists(mortal, element);
        return mortal;
    }

    private Character loadMummy(Element element) {
        Mummy mummy = new Mummy();
        loadCharacterBasics(mummy, element);
        loadMummySpecifics(mummy, element);
        loadCharacterTraitLists(mummy, element);
        return mummy;
    }

    private Character loadKueiJin(Element element) {
        KueiJin kueiJin = new KueiJin();
        loadCharacterBasics(kueiJin, element);
        loadKueiJinSpecifics(kueiJin, element);
        loadCharacterTraitLists(kueiJin, element);
        return kueiJin;
    }

    private Character loadFera(Element element) {
        Fera fera = new Fera();
        loadCharacterBasics(fera, element);
        loadFeraSpecifics(fera, element);
        loadCharacterTraitLists(fera, element);
        return fera;
    }

    private Character loadVarious(Element element) {
        Various various = new Various();
        loadCharacterBasics(various, element);
        loadVariousSpecifics(various, element);
        loadVariousTraitLists(various, element);
        return various;
    }

    private Character loadHunter(Element element) {
        Hunter hunter = new Hunter();
        loadCharacterBasics(hunter, element);
        loadHunterSpecifics(hunter, element);
        loadCharacterTraitLists(hunter, element);
        return hunter;
    }

    private Character loadDemon(Element element) {
        Demon demon = new Demon();
        loadCharacterBasics(demon, element);
        loadDemonSpecifics(demon, element);
        loadCharacterTraitLists(demon, element);
        return demon;
    }

    private void loadCharacterBasics(Character character, Element element) {
        character.setName(FileUtils.getAttribute(element, "name"));
        character.setNature(FileUtils.getAttribute(element, "nature"));
        character.setDemeanor(FileUtils.getAttribute(element, "demeanor"));
        character.setPlayer(FileUtils.getAttribute(element, "player"));
        character.setStatus(FileUtils.getAttribute(element, "status", "Active"));
        character.setId(FileUtils.getAttribute(element, "id"));
        character.setStartDate(FileUtils.getDateAttribute(element, "startdate"));
        character.setNarrator(FileUtils.getAttribute(element, "narrator"));
        character.setLastModified(FileUtils.getDateAttribute(element, "lastmodified"));
        character.setPhysicalMax(FileUtils.getIntAttribute(element, "physicalmax", 0));
        character.setSocialMax(FileUtils.getIntAttribute(element, "socialmax", 0));
        character.setMentalMax(FileUtils.getIntAttribute(element, "mentalmax", 0));
        character.setWillpower(FileUtils.getIntAttribute(element, "willpower", 0));
        character.setTempWillpower(FileUtils.getIntAttribute(element, "tempwillpower", 0));
    }

    private void loadVampireSpecifics(Vampire vampire, Element element) {
        vampire.setClan(FileUtils.getAttribute(element, "clan"));
        vampire.setSect(FileUtils.getAttribute(element, "sect"));
        vampire.setGeneration(FileUtils.getIntAttribute(element, "generation", 13));
        vampire.setTitle(FileUtils.getAttribute(element, "title"));
        vampire.setCoterie(FileUtils.getAttribute(element, "coterie"));
        vampire.setPath(FileUtils.getAttribute(element, "path"));
        vampire.setSire(FileUtils.getAttribute(element, "sire"));
        vampire.setAura(FileUtils.getAttribute(element, "aura"));
        vampire.setAuraBonus(FileUtils.getAttribute(element, "aurabonus"));
        vampire.setBlood(FileUtils.getIntAttribute(element, "blood", 10));
        vampire.setConscience(FileUtils.getIntAttribute(element, "conscience", 0));
        vampire.setSelfControl(FileUtils.getIntAttribute(element, "selfcontrol", 0));
        vampire.setCourage(FileUtils.getIntAttribute(element, "courage", 0));
        vampire.setPathTraits(FileUtils.getIntAttribute(element, "pathtraits", 0));
        vampire.setTempBlood(FileUtils.getIntAttribute(element, "tempblood", 0));
        vampire.setTempConscience(FileUtils.getIntAttribute(element, "tempconscience", 0));
        vampire.setTempSelfControl(FileUtils.getIntAttribute(element, "tempselfcontrol", 0));
        vampire.setTempCourage(FileUtils.getIntAttribute(element, "tempcourage", 0));
        vampire.setTempPathTraits(FileUtils.getIntAttribute(element, "temppathtraits", 0));
    }

    private void loadChangelingSpecifics(Changeling changeling, Element element) {
        changeling.setKith(FileUtils.getAttribute(element, "kith"));
        changeling.setSeeming(FileUtils.getAttribute(element, "seeming"));
        changeling.setHouse(FileUtils.getAttribute(element, "house"));
        changeling.setTitle(FileUtils.getAttribute(element, "title"));
        changeling.setLegacies(FileUtils.getAttribute(element, "legacies"));
        changeling.setSeelie(FileUtils.getAttribute(element, "seelie"));
        changeling.setUnseelie(FileUtils.getAttribute(element, "unseelie"));
        changeling.setCourt(FileUtils.getAttribute(element, "court"));
        changeling.setThreshold(FileUtils.getAttribute(element, "threshold"));
        changeling.setGlamour(FileUtils.getIntAttribute(element, "glamour", 0));
        changeling.setBanality(FileUtils.getIntAttribute(element, "banality", 0));
        changeling.setNightmare(FileUtils.getIntAttribute(element, "nightmare", 0));
        changeling.setTempGlamour(FileUtils.getIntAttribute(element, "tempglamour", 0));
        changeling.setTempBanality(FileUtils.getIntAttribute(element, "tempbanality", 0));
        changeling.setTempNightmare(FileUtils.getIntAttribute(element, "tempnightmare", 0));

        // Load oaths child element
        Element oathsElement = FileUtils.getFirstChildElement(element, "oaths");
        if (oathsElement != null) {
            changeling.setOaths(FileUtils.getTextContent(oathsElement));
        }
    }

    private void loadMageSpecifics(Mage mage, Element element) {
        mage.setEssence(FileUtils.getAttribute(element, "essence"));
        mage.setTradition(FileUtils.getAttribute(element, "tradition"));
        mage.setFaction(FileUtils.getAttribute(element, "faction"));
        mage.setCabal(FileUtils.getAttribute(element, "cabal"));
        mage.setRank(FileUtils.getAttribute(element, "rank"));
        mage.setArete(FileUtils.getIntAttribute(element, "arete", 0));
        mage.setQuintessence(FileUtils.getIntAttribute(element, "quintessence", 0));
        mage.setParadox(FileUtils.getIntAttribute(element, "paradox", 0));
        mage.setTempArete(FileUtils.getIntAttribute(element, "temparete", 0));
        mage.setTempQuintessence(FileUtils.getIntAttribute(element, "tempquintessence", 0));
        mage.setTempParadox(FileUtils.getIntAttribute(element, "tempparadox", 0));

        // Load foci child element
        Element fociElement = FileUtils.getFirstChildElement(element, "foci");
        if (fociElement != null) {
            mage.setFoci(FileUtils.getTextContent(fociElement));
        }

        // Load biography child element
        Element biographyElement = FileUtils.getFirstChildElement(element, "biography");
        if (biographyElement != null) {
            mage.setBiography(FileUtils.getTextContent(biographyElement));
        }
    }

    private void loadWraithSpecifics(Wraith wraith, Element element) {
        // Note: In the XML, ethnos is sometimes stored as a string (e.g., "Wraith")
        // but the Wraith.java class defines it as int. When parsing fails, it defaults to 0.
        wraith.setEthnos(FileUtils.getIntAttribute(element, "ethnos", 0));
        wraith.setGuild(FileUtils.getAttribute(element, "guild"));
        wraith.setFaction(FileUtils.getAttribute(element, "faction"));
        wraith.setLegion(FileUtils.getAttribute(element, "legion"));
        wraith.setRank(FileUtils.getAttribute(element, "rank"));
        wraith.setPathos(FileUtils.getIntAttribute(element, "pathos", 0));
        wraith.setCorpus(FileUtils.getIntAttribute(element, "corpus", 0));
        wraith.setAngst(FileUtils.getIntAttribute(element, "angst", 0));
        wraith.setTempPathos(FileUtils.getIntAttribute(element, "temppathos", 0));
        wraith.setTempCorpus(FileUtils.getIntAttribute(element, "tempcorpus", 0));
        wraith.setTempAngst(FileUtils.getIntAttribute(element, "tempangst", 0));

        // Load text child elements
        Element passionsElement = FileUtils.getFirstChildElement(element, "passions");
        if (passionsElement != null) {
            wraith.setPassions(FileUtils.getTextContent(passionsElement));
        }

        Element fettersElement = FileUtils.getFirstChildElement(element, "fetters");
        if (fettersElement != null) {
            wraith.setFetters(FileUtils.getTextContent(fettersElement));
        }

        Element lifeElement = FileUtils.getFirstChildElement(element, "life");
        if (lifeElement != null) {
            wraith.setLife(FileUtils.getTextContent(lifeElement));
        }

        Element deathElement = FileUtils.getFirstChildElement(element, "death");
        if (deathElement != null) {
            wraith.setDeath(FileUtils.getTextContent(deathElement));
        }

        Element hauntElement = FileUtils.getFirstChildElement(element, "haunt");
        if (hauntElement != null) {
            wraith.setHaunt(FileUtils.getTextContent(hauntElement));
        }

        Element regretElement = FileUtils.getFirstChildElement(element, "regret");
        if (regretElement != null) {
            wraith.setRegret(FileUtils.getTextContent(regretElement));
        }

        Element shadowArchetypeElement = FileUtils.getFirstChildElement(element, "shadowarchetype");
        if (shadowArchetypeElement != null) {
            wraith.setShadowArchetype(FileUtils.getTextContent(shadowArchetypeElement));
        }

        Element shadowPlayerElement = FileUtils.getFirstChildElement(element, "shadowplayer");
        if (shadowPlayerElement != null) {
            wraith.setShadowPlayer(FileUtils.getTextContent(shadowPlayerElement));
        }

        Element darkPassionsElement = FileUtils.getFirstChildElement(element, "darkpassions");
        if (darkPassionsElement != null) {
            wraith.setDarkPassions(FileUtils.getTextContent(darkPassionsElement));
        }
    }

    private void loadHunterSpecifics(Hunter hunter, Element element) {
        hunter.setCreed(FileUtils.getAttribute(element, "creed"));
        hunter.setCamp(FileUtils.getAttribute(element, "camp"));
        hunter.setHandle(FileUtils.getAttribute(element, "handle"));
        hunter.setConviction(FileUtils.getIntAttribute(element, "conviction", 0));
        hunter.setMercy(FileUtils.getIntAttribute(element, "mercy", 0));
        hunter.setVision(FileUtils.getIntAttribute(element, "vision", 0));
        hunter.setZeal(FileUtils.getIntAttribute(element, "zeal", 0));
        hunter.setTempConviction(FileUtils.getIntAttribute(element, "tempconviction", 0));
        hunter.setTempMercy(FileUtils.getIntAttribute(element, "tempmercy", 0));
        hunter.setTempVision(FileUtils.getIntAttribute(element, "tempvision", 0));
        hunter.setTempZeal(FileUtils.getIntAttribute(element, "tempzeal", 0));
    }

    private void loadFeraSpecifics(Fera fera, Element element) {
        fera.setBreed(FileUtils.getAttribute(element, "breed"));
        fera.setAuspice(FileUtils.getAttribute(element, "auspice"));
        fera.setRank(FileUtils.getAttribute(element, "rank"));
        fera.setPack(FileUtils.getAttribute(element, "pack"));
        fera.setTotem(FileUtils.getAttribute(element, "totem"));
        fera.setSpecies(FileUtils.getAttribute(element, "fera"));
        fera.setPosition(FileUtils.getAttribute(element, "position"));
        fera.setNotoriety(FileUtils.getIntAttribute(element, "notoriety", 0));
        fera.setRage(FileUtils.getIntAttribute(element, "rage", 0));
        fera.setGnosis(FileUtils.getIntAttribute(element, "gnosis", 0));
        fera.setTempRage(FileUtils.getIntAttribute(element, "temprage", 0));
        fera.setTempGnosis(FileUtils.getIntAttribute(element, "tempgnosis", 0));
        fera.setHonor(FileUtils.getIntAttribute(element, "honor", 0));
        fera.setGlory(FileUtils.getIntAttribute(element, "glory", 0));
        fera.setWisdom(FileUtils.getIntAttribute(element, "wisdom", 0));
        fera.setTempHonor(FileUtils.getFloatAttribute(element, "temphonor", 0.0f));
        fera.setTempGlory(FileUtils.getFloatAttribute(element, "tempglory", 0.0f));
        fera.setTempWisdom(FileUtils.getFloatAttribute(element, "tempwisdom", 0.0f));
    }

    private void loadWerewolfSpecifics(Werewolf werewolf, Element element) {
        werewolf.setTribe(FileUtils.getAttribute(element, "tribe"));
        werewolf.setCamp(FileUtils.getAttribute(element, "camp"));
    }

    private void loadMortalSpecifics(Mortal mortal, Element element) {
        mortal.setMotivation(FileUtils.getAttribute(element, "motivation"));
        mortal.setAssociation(FileUtils.getAttribute(element, "association"));
        mortal.setTitle(FileUtils.getAttribute(element, "title"));
        mortal.setRegnant(FileUtils.getAttribute(element, "regnant"));
        mortal.setBlood(FileUtils.getIntAttribute(element, "blood", 0));
        mortal.setHumanity(FileUtils.getIntAttribute(element, "humanity", 0));
        mortal.setConscience(FileUtils.getIntAttribute(element, "conscience", 0));
        mortal.setSelfControl(FileUtils.getIntAttribute(element, "selfcontrol", 0));
        mortal.setCourage(FileUtils.getIntAttribute(element, "courage", 0));
        mortal.setTrueFaith(FileUtils.getIntAttribute(element, "truefaith", 0));
        mortal.setTempBlood(FileUtils.getIntAttribute(element, "tempblood", 0));
        mortal.setTempHumanity(FileUtils.getIntAttribute(element, "temphumanity", 0));
        mortal.setTempConscience(FileUtils.getIntAttribute(element, "tempconscience", 0));
        mortal.setTempSelfControl(FileUtils.getIntAttribute(element, "tempselfcontrol", 0));
        mortal.setTempCourage(FileUtils.getIntAttribute(element, "tempcourage", 0));
        mortal.setTempTrueFaith(FileUtils.getIntAttribute(element, "temptruefaith", 0));

        // Load other child element
        Element otherElement = FileUtils.getFirstChildElement(element, "other");
        if (otherElement != null) {
            mortal.setOther(FileUtils.getTextContent(otherElement));
        }
    }

    private void loadKueiJinSpecifics(KueiJin kueiJin, Element element) {
        kueiJin.setDharma(FileUtils.getAttribute(element, "dharma"));
        kueiJin.setDirection(FileUtils.getAttribute(element, "direction"));
        kueiJin.setBalance(FileUtils.getAttribute(element, "balance"));
        kueiJin.setStation(FileUtils.getAttribute(element, "station"));
        kueiJin.setPoArchetype(FileUtils.getAttribute(element, "poarchetype"));
        kueiJin.setHun(FileUtils.getIntAttribute(element, "hun", 0));
        kueiJin.setPo(FileUtils.getIntAttribute(element, "po", 0));
        kueiJin.setYinChi(FileUtils.getIntAttribute(element, "yinchi", 0));
        kueiJin.setYangChi(FileUtils.getIntAttribute(element, "yangchi", 0));
        kueiJin.setDemonChi(FileUtils.getIntAttribute(element, "demonchi", 0));
        kueiJin.setDharmaTraits(FileUtils.getIntAttribute(element, "dharmatraits", 0));
        kueiJin.setTempHun(FileUtils.getIntAttribute(element, "temphun", 0));
        kueiJin.setTempPo(FileUtils.getIntAttribute(element, "temppo", 0));
        kueiJin.setTempYinChi(FileUtils.getIntAttribute(element, "tempyinchi", 0));
        kueiJin.setTempYangChi(FileUtils.getIntAttribute(element, "tempyangchi", 0));
        kueiJin.setTempDemonChi(FileUtils.getIntAttribute(element, "tempdemonchi", 0));
        kueiJin.setTempDharmaTraits(FileUtils.getIntAttribute(element, "tempdharmatraits", 0));
    }

    private void loadMummySpecifics(Mummy mummy, Element element) {
        mummy.setAmenti(FileUtils.getAttribute(element, "amenti"));
        mummy.setSekhem(FileUtils.getIntAttribute(element, "sekhem", 0));
        mummy.setBalance(FileUtils.getIntAttribute(element, "balance", 0));
        mummy.setMemory(FileUtils.getIntAttribute(element, "memory", 0));
        mummy.setIntegrity(FileUtils.getIntAttribute(element, "integrity", 0));
        mummy.setJoy(FileUtils.getIntAttribute(element, "joy", 0));
        mummy.setBa(FileUtils.getIntAttribute(element, "ba", 0));
        mummy.setKa(FileUtils.getIntAttribute(element, "ka", 0));
        mummy.setTempSekhem(FileUtils.getIntAttribute(element, "tempsekhem", 0));
        mummy.setTempBalance(FileUtils.getIntAttribute(element, "tempbalance", 0));
        mummy.setTempMemory(FileUtils.getIntAttribute(element, "tempmemory", 0));
        mummy.setTempIntegrity(FileUtils.getIntAttribute(element, "tempintegrity", 0));
        mummy.setTempJoy(FileUtils.getIntAttribute(element, "tempjoy", 0));
        mummy.setTempBa(FileUtils.getIntAttribute(element, "tempba", 0));
        mummy.setTempKa(FileUtils.getIntAttribute(element, "tempka", 0));

        // Load inheritance child element
        Element inheritanceElement = FileUtils.getFirstChildElement(element, "inheritance");
        if (inheritanceElement != null) {
            mummy.setInheritance(FileUtils.getTextContent(inheritanceElement));
        }
    }

    private void loadDemonSpecifics(Demon demon, Element element) {
        demon.setHouse(FileUtils.getAttribute(element, "house"));
        demon.setFaction(FileUtils.getAttribute(element, "faction"));
        demon.setTorment(FileUtils.getIntAttribute(element, "torment", 0));
        demon.setFaith(FileUtils.getIntAttribute(element, "faith", 0));
        demon.setConscience(FileUtils.getIntAttribute(element, "conscience", 0));
        demon.setConviction(FileUtils.getIntAttribute(element, "conviction", 0));
        demon.setCourage(FileUtils.getIntAttribute(element, "courage", 0));
        demon.setTempTorment(FileUtils.getIntAttribute(element, "temptorment", 0));
        demon.setTempFaith(FileUtils.getIntAttribute(element, "tempfaith", 0));
        demon.setTempConscience(FileUtils.getIntAttribute(element, "tempconscience", 0));
        demon.setTempConviction(FileUtils.getIntAttribute(element, "tempconviction", 0));
        demon.setTempCourage(FileUtils.getIntAttribute(element, "tempcourage", 0));
    }

    private void loadVariousSpecifics(Various various, Element element) {
        various.setCharacterClass(FileUtils.getAttribute(element, "class"));
        various.setSubclass(FileUtils.getAttribute(element, "subclass"));
        various.setAffinity(FileUtils.getAttribute(element, "affinity"));
        various.setPlane(FileUtils.getAttribute(element, "plane"));
        various.setBrood(FileUtils.getAttribute(element, "brood"));

        // Load text child elements
        Element otherElement = FileUtils.getFirstChildElement(element, "other");
        if (otherElement != null) {
            various.setOther(FileUtils.getTextContent(otherElement));
        }

        Element biographyElement = FileUtils.getFirstChildElement(element, "biography");
        if (biographyElement != null) {
            various.setBiography(FileUtils.getTextContent(biographyElement));
        }

        Element notesElement = FileUtils.getFirstChildElement(element, "notes");
        if (notesElement != null) {
            various.setNotes(FileUtils.getTextContent(notesElement));
        }
    }

    private void loadVariousTraitLists(Various various, Element element) {
        List<Element> traitLists = FileUtils.getChildElements(element, "traitlist");

        for (Element traitList : traitLists) {
            String listName = FileUtils.getAttribute(traitList, "name");
            List<Trait> traits = loadTraits(traitList);

            switch (listName) {
                case "Physical":
                    various.setPhysicalList(traits);
                    break;
                case "Social":
                    various.setSocialList(traits);
                    break;
                case "Mental":
                    various.setMentalList(traits);
                    break;
                case "Negative Physical":
                    various.setPhysicalNegList(traits);
                    break;
                case "Negative Social":
                    various.setSocialNegList(traits);
                    break;
                case "Negative Mental":
                    various.setMentalNegList(traits);
                    break;
                case "Abilities":
                    various.setAbilityList(traits);
                    break;
                case "Influences":
                    various.setInfluenceList(traits);
                    break;
                case "Backgrounds":
                    various.setBackgroundList(traits);
                    break;
                case "Health Levels":
                case "Health":
                    various.setHealthList(traits);
                    break;
                case "Equipment":
                    various.setEquipmentList(traits);
                    break;
                case "Locations":
                    various.setHangoutList(traits);
                    break;
                case "Tempers":
                    various.setTemperList(traits);
                    break;
                case "Powers":
                    various.setPowerList(traits);
                    break;
                case "Merits":
                    various.setMeritList(traits);
                    break;
                case "Flaws":
                    various.setFlawList(traits);
                    break;
                case "Derangements":
                    various.setDerangementList(traits);
                    break;
            }
        }
    }

    private void loadCharacterTraitLists(Character character, Element element) {
        List<Element> traitLists = FileUtils.getChildElements(element, "traitlist");

        for (Element traitList : traitLists) {
            String listName = FileUtils.getAttribute(traitList, "name");
            List<Trait> traits = loadTraits(traitList);

            switch (listName) {
                case "Physical":
                    character.setPhysicalList(traits);
                    break;
                case "Social":
                    character.setSocialList(traits);
                    break;
                case "Mental":
                    character.setMentalList(traits);
                    break;
                case "Negative Physical":
                    character.setPhysicalNegList(traits);
                    break;
                case "Negative Social":
                    character.setSocialNegList(traits);
                    break;
                case "Negative Mental":
                    character.setMentalNegList(traits);
                    break;
                case "Abilities":
                    character.setAbilityList(traits);
                    break;
                case "Influences":
                    character.setInfluenceList(traits);
                    break;
                case "Backgrounds":
                    character.setBackgroundList(traits);
                    break;
                case "Health Levels":
                case "Health":
                    character.setHealthList(traits);
                    break;
                case "Equipment":
                    character.setEquipmentList(traits);
                    break;
                case "Locations":
                    character.setHangoutList(traits);
                    break;
                case "Merits":
                    character.setMeritList(traits);
                    break;
                case "Flaws":
                    character.setFlawList(traits);
                    break;
                case "Derangements":
                    character.setDerangementList(traits);
                    break;
                default:
                    // Vampire-specific trait lists
                    if (character instanceof Vampire) {
                        Vampire vampire = (Vampire) character;
                        switch (listName) {
                            case "Status":
                                vampire.setStatusList(traits);
                                break;
                            case "Bonds":
                                vampire.setBondList(traits);
                                break;
                            case "Miscellaneous":
                                vampire.setMiscellaneousList(traits);
                                break;
                            case "Disciplines":
                                vampire.setDisciplineList(traits);
                                break;
                            case "Rituals":
                                vampire.setRitualList(traits);
                                break;
                            case "Boons":
                                vampire.setBoonTraitList(traits);
                                break;
                        }
                    }
                    // Changeling-specific trait lists
                    else if (character instanceof Changeling) {
                        Changeling changeling = (Changeling) character;
                        switch (listName) {
                            case "Status":
                                changeling.setStatusList(traits);
                                break;
                            case "Arts":
                                changeling.setArtList(traits);
                                break;
                            case "Realms":
                                changeling.setRealmList(traits);
                                break;
                        }
                    }
                    // Mage-specific trait lists
                    else if (character instanceof Mage) {
                        Mage mage = (Mage) character;
                        switch (listName) {
                            case "Resonance":
                                mage.setResonanceList(traits);
                                break;
                            case "Reputation":
                                mage.setReputationList(traits);
                                break;
                            case "Spheres":
                                mage.setSphereList(traits);
                                break;
                            case "Rotes":
                                mage.setRoteList(traits);
                                // Wraith-specific trait lists
                        }
                    } else if (character instanceof Wraith) {
                        Wraith wraith = (Wraith) character;
                        switch (listName) {
                            case "Status":
                                wraith.setStatusList(traits);
                                break;
                            case "Arcanoi":
                                wraith.setArcanoiList(traits);
                                break;
                            case "Thorns":
                                wraith.setThornList(traits);
                                break;
                        }
                    }
                    // Mortal-specific trait lists
                    else if (character instanceof Mortal) {
                        Mortal mortal = (Mortal) character;
                        switch (listName) {
                            case "Humanity":
                                mortal.setHumanityList(traits);
                                break;
                            case "Numina":
                                mortal.setNuminaList(traits);
                                break;
                        }
                    }
                    // Fera-specific trait lists (includes Werewolf since it extends Fera)
                    else if (character instanceof Fera) {
                        Fera fera = (Fera) character;
                        switch (listName) {
                            case "Features":
                                fera.setFeatureList(traits);
                                break;
                            case "Gifts":
                                fera.setGiftList(traits);
                                break;
                            case "Rites":
                                fera.setRiteList(traits);
                                break;
                            case "Honor":
                                fera.setHonorList(traits);
                                break;
                            case "Glory":
                                fera.setGloryList(traits);
                                break;
                            case "Wisdom":
                                fera.setWisdomList(traits);
                                break;
                        }
                    }
                    // Hunter-specific trait lists
                    else if (character instanceof Hunter) {
                        Hunter hunter = (Hunter) character;
                        if ("Edges".equals(listName)) {
                            hunter.setEdgeList(traits);
                        }
                    }
                    // Kuei-Jin-specific trait lists
                    else if (character instanceof KueiJin) {
                        KueiJin kueiJin = (KueiJin) character;
                        switch (listName) {
                            case "Status":
                                kueiJin.setStatusList(traits);
                                break;
                            case "Guanxi":
                                kueiJin.setGuanxiList(traits);
                                break;
                            case "Disciplines":
                                kueiJin.setDisciplineList(traits);
                                break;
                            case "Rites":
                                kueiJin.setRiteList(traits);
                                break;
                        }
                    } else if (character instanceof Mummy) {
                        Mummy mummy = (Mummy) character;
                        switch (listName) {
                            case "Humanity":
                                mummy.setHumanityList(traits);
                                break;
                            case "Status":
                                mummy.setStatusList(traits);
                                break;
                            case "Hekau":
                                mummy.setHekauList(traits);
                                break;
                            case "Spells":
                                mummy.setSpellList(traits);
                                break;
                            case "Rituals":
                                mummy.setRitualList(traits);
                                break;
                        }
                    }
                    // Demon-specific trait lists
                    else if (character instanceof Demon) {
                        Demon demon = (Demon) character;
                        switch (listName) {
                            case "Lores":
                                demon.setLoreList(traits);
                                break;
                            case "Apocalyptic Form":
                                demon.setVisageList(traits);
                                break;
                        }
                    }
                    break;
            }
        }
    }

    private List<Trait> loadTraits(Element traitListElement) {
        List<Trait> traits = new ArrayList<>();
        List<Element> traitElements = FileUtils.getChildElements(traitListElement, "trait");

        for (Element traitElement : traitElements) {
            Trait trait = new Trait();
            trait.setName(FileUtils.getAttribute(traitElement, "name"));
            trait.setTotal(FileUtils.getAttribute(traitElement, "val"));
            trait.setNote(FileUtils.getAttribute(traitElement, "note"));
            traits.add(trait);
        }

        return traits;
    }

    private Item loadItem(Element element) {
        Item item = new Item();
        item.setName(FileUtils.getAttribute(element, "name"));
        item.setItemType(FileUtils.getAttribute(element, "type"));
        item.setItemSubtype(FileUtils.getAttribute(element, "subtype"));
        item.setLevel(FileUtils.getIntAttribute(element, "level", 0));
        item.setBonus(FileUtils.getIntAttribute(element, "bonus", 0));
        item.setDamageType(FileUtils.getAttribute(element, "damage"));
        item.setDamageAmount(FileUtils.getIntAttribute(element, "amount", 0));
        item.setConcealability(FileUtils.getAttribute(element, "conceal"));
        item.setLastModified(FileUtils.getDateAttribute(element, "lastmodified"));

        Element powersElement = FileUtils.getFirstChildElement(element, "powers");
        if (powersElement != null) {
            item.setPowers(FileUtils.getTextContent(powersElement));
        }

        Element appearanceElement = FileUtils.getFirstChildElement(element, "appearance");
        if (appearanceElement != null) {
            item.setAppearance(FileUtils.getTextContent(appearanceElement));
        }

        Element notesElement = FileUtils.getFirstChildElement(element, "notes");
        if (notesElement != null) {
            item.setNotes(FileUtils.getTextContent(notesElement));
        }

        return item;
    }

    private Rote loadRote(Element element) {
        Rote rote = new Rote();
        rote.setName(FileUtils.getAttribute(element, "name"));
        rote.setLevel(FileUtils.getIntAttribute(element, "level", 0));
        rote.setDuration(FileUtils.getAttribute(element, "duration"));
        rote.setLastModified(FileUtils.getDateAttribute(element, "lastmodified"));

        Element descriptionElement = FileUtils.getFirstChildElement(element, "description");
        if (descriptionElement != null) {
            rote.setDescription(FileUtils.getTextContent(descriptionElement));
        }

        Element gradesElement = FileUtils.getFirstChildElement(element, "grades");
        if (gradesElement != null) {
            rote.setGrades(FileUtils.getTextContent(gradesElement));
        }

        return rote;
    }

    private Location loadLocation(Element element) {
        Location location = new Location();
        location.setName(FileUtils.getAttribute(element, "name"));
        location.setLocType(FileUtils.getAttribute(element, "type"));
        location.setLevel(FileUtils.getIntAttribute(element, "level", 0));
        location.setOwner(FileUtils.getAttribute(element, "owner"));
        location.setAccess(FileUtils.getAttribute(element, "access"));
        location.setSecTraits(FileUtils.getIntAttribute(element, "securitytraits", 0));
        location.setSecRetests(FileUtils.getIntAttribute(element, "securityretests", 0));
        location.setGauntlet(FileUtils.getIntAttribute(element, "gauntlet", 0));
        location.setAffinity(FileUtils.getAttribute(element, "affinity"));
        location.setTotem(FileUtils.getAttribute(element, "totem"));
        location.setLastModified(FileUtils.getDateAttribute(element, "lastmodified"));

        Element whereElement = FileUtils.getFirstChildElement(element, "where");
        if (whereElement != null) {
            location.setWhere(FileUtils.getTextContent(whereElement));
        }

        Element appearanceElement = FileUtils.getFirstChildElement(element, "appearance");
        if (appearanceElement != null) {
            location.setAppearance(FileUtils.getTextContent(appearanceElement));
        }

        Element securityElement = FileUtils.getFirstChildElement(element, "security");
        if (securityElement != null) {
            location.setSecurity(FileUtils.getTextContent(securityElement));
        }

        Element umbraElement = FileUtils.getFirstChildElement(element, "umbra");
        if (umbraElement != null) {
            location.setUmbra(FileUtils.getTextContent(umbraElement));
        }

        Element notesElement = FileUtils.getFirstChildElement(element, "notes");
        if (notesElement != null) {
            location.setNotes(FileUtils.getTextContent(notesElement));
        }

        return location;
    }

    private Action loadAction(Element element) {
        Action action = new Action();
        action.setCharName(FileUtils.getAttribute(element, "character"));
        action.setActDate(FileUtils.getDateAttribute(element, "date"));
        action.setDone(FileUtils.getBooleanAttribute(element, "done", false));
        action.setLastModified(FileUtils.getDateAttribute(element, "lastmodified"));

        return action;
    }

    private Rumor loadRumor(Element element) {
        Rumor rumor = new Rumor();
        rumor.setTitle(FileUtils.getAttribute(element, "title"));
        rumor.setRumorDate(FileUtils.getDateAttribute(element, "date"));
        rumor.setDone(FileUtils.getBooleanAttribute(element, "done", false));
        rumor.setLastModified(FileUtils.getDateAttribute(element, "lastmodified"));

        return rumor;
    }
}
