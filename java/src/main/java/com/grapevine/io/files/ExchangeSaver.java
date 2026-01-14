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

import java.util.List;
import java.util.logging.Logger;

/**
 * Saves Game data to an XML exchange file (.gex).
 * Produces files compatible with LoadExchangeXML in GameClass.cls.
 */
public class ExchangeSaver {

    private static final Logger logger = Logger.getLogger(ExchangeSaver.class.getName());
    private static final String GRAPEVINE_VERSION = "3";

    /**
     * Save a Game to a .gex file.
     *
     * @param game     Game object to save
     * @param filePath Path to the output .gex file
     * @throws Exception if the file cannot be written
     */
    public void saveExchange(Game game, String filePath) throws Exception {
        Document doc = FileUtils.createDocument();

        // Create root element
        Element root = doc.createElement("grapevine");
        root.setAttribute("version", GRAPEVINE_VERSION);
        doc.appendChild(root);

        // Save players
        if (game.getPlayerList() != null) {
            for (Player player : game.getPlayerList()) {
                root.appendChild(savePlayer(doc, player));
            }
        }

        // Save characters
        if (game.getCharacterList() != null) {
            for (Character character : game.getCharacterList()) {
                root.appendChild(saveCharacter(doc, character));
            }
        }

        // Save items
        if (game.getItemList() != null) {
            for (Item item : game.getItemList()) {
                root.appendChild(saveItem(doc, item));
            }
        }

        // Save rotes
        if (game.getRoteList() != null) {
            for (Rote rote : game.getRoteList()) {
                root.appendChild(saveRote(doc, rote));
            }
        }

        // Save locations
        if (game.getLocationList() != null) {
            for (Location location : game.getLocationList()) {
                root.appendChild(saveLocation(doc, location));
            }
        }

        // Save actions
        if (game.getInfluenceUseList() != null) {
            for (Action action : game.getInfluenceUseList()) {
                root.appendChild(saveAction(doc, action));
            }
        }

        // Save rumors
        if (game.getAllRumorLists() != null) {
            for (Rumor rumor : game.getAllRumorLists()) {
                root.appendChild(saveRumor(doc, rumor));
            }
        }

        FileUtils.writeXmlFile(doc, filePath);
    }

    private Element savePlayer(Document doc, Player player) {
        Element element = doc.createElement("player");

        if (player.getName() != null) element.setAttribute("name", player.getName());
        if (player.getId() != null) element.setAttribute("id", player.getId());
        if (player.getEmail() != null) element.setAttribute("email", player.getEmail());
        if (player.getPhone() != null) element.setAttribute("phone", player.getPhone());
        if (player.getPosition() != null) element.setAttribute("position", player.getPosition());
        if (player.getStatus() != null) element.setAttribute("status", player.getStatus());
        if (player.getLastModified() != null) {
            element.setAttribute("lastmodified", FileUtils.formatDate(player.getLastModified()));
        }

        // Add experience placeholder
        Element experience = doc.createElement("experience");
        experience.setAttribute("unspent", "0");
        experience.setAttribute("earned", "0");
        element.appendChild(experience);

        if (player.getAddress() != null && !player.getAddress().isEmpty()) {
            Element address = doc.createElement("address");
            FileUtils.setCDataContent(doc, address, player.getAddress());
            element.appendChild(address);
        }

        if (player.getNotes() != null && !player.getNotes().isEmpty()) {
            Element notes = doc.createElement("notes");
            FileUtils.setCDataContent(doc, notes, player.getNotes());
            element.appendChild(notes);
        }

        return element;
    }

    private Element saveCharacter(Document doc, Character character) {
        String tagName = getCharacterTagName(character);
        Element element = doc.createElement(tagName);

        // Save basic character attributes
        if (character.getName() != null) element.setAttribute("name", character.getName());
        if (character.getNature() != null) element.setAttribute("nature", character.getNature());
        if (character.getDemeanor() != null) element.setAttribute("demeanor", character.getDemeanor());
        if (character.getPlayer() != null) element.setAttribute("player", character.getPlayer());
        if (character.getStatus() != null) element.setAttribute("status", character.getStatus());
        if (character.getId() != null) element.setAttribute("id", character.getId());
        if (character.getStartDate() != null) {
            element.setAttribute("startdate", FileUtils.formatDate(character.getStartDate()));
        }
        if (character.getNarrator() != null) element.setAttribute("narrator", character.getNarrator());
        if (character.getLastModified() != null) {
            element.setAttribute("lastmodified", FileUtils.formatDate(character.getLastModified()));
        }

        // Add character stats
        element.setAttribute("physicalmax", String.valueOf(character.getPhysicalMax()));
        element.setAttribute("socialmax", String.valueOf(character.getSocialMax()));
        element.setAttribute("mentalmax", String.valueOf(character.getMentalMax()));
        element.setAttribute("willpower", String.valueOf(character.getWillpower()));
        if (character.getTempWillpower() != 0) {
            element.setAttribute("tempwillpower", String.valueOf(character.getTempWillpower()));
        }

        // Add character-type-specific attributes
        addCharacterTypeAttributes(element, character);

        // Add experience placeholder
        Element experience = doc.createElement("experience");
        experience.setAttribute("unspent", "0");
        experience.setAttribute("earned", "0");
        element.appendChild(experience);

        // Add trait lists
        saveCharacterTraitLists(doc, element, character);

        // Add character-type-specific child elements
        addCharacterTypeChildElements(doc, element, character);

        return element;
    }

    private String getCharacterTagName(Character character) {
        if (character instanceof Vampire) return "vampire";
        if (character instanceof Werewolf) return "werewolf";
        if (character instanceof Mage) return "mage";
        if (character instanceof Changeling) return "changeling";
        if (character instanceof Wraith) return "wraith";
        if (character instanceof Mortal) return "mortal";
        if (character instanceof Mummy) return "mummy";
        if (character instanceof KueiJin) return "kueijin";
        if (character instanceof Fera) return "fera";
        if (character instanceof Hunter) return "hunter";
        if (character instanceof Demon) return "demon";
        if (character instanceof Various) return "various";

        logger.warning("Unknown character type: " + character.getClass().getName());
        return "various";
    }

    private void addCharacterTypeAttributes(Element element, Character character) {
        if (character instanceof Vampire vampire) {
            if (vampire.getClan() != null) element.setAttribute("clan", vampire.getClan());
            if (vampire.getSect() != null) element.setAttribute("sect", vampire.getSect());
            element.setAttribute("generation", String.valueOf(vampire.getGeneration()));
            if (vampire.getTitle() != null) element.setAttribute("title", vampire.getTitle());
            if (vampire.getCoterie() != null) element.setAttribute("coterie", vampire.getCoterie());
            if (vampire.getPath() != null) element.setAttribute("path", vampire.getPath());
            if (vampire.getSire() != null) element.setAttribute("sire", vampire.getSire());
            if (vampire.getAura() != null) element.setAttribute("aura", vampire.getAura());
            if (vampire.getAuraBonus() != null) element.setAttribute("aurabonus", vampire.getAuraBonus());
            element.setAttribute("blood", String.valueOf(vampire.getBlood()));
            element.setAttribute("conscience", String.valueOf(vampire.getConscience()));
            element.setAttribute("selfcontrol", String.valueOf(vampire.getSelfControl()));
            element.setAttribute("courage", String.valueOf(vampire.getCourage()));
            element.setAttribute("pathtraits", String.valueOf(vampire.getPathTraits()));
            if (vampire.getTempBlood() != 0) {
                element.setAttribute("tempblood", String.valueOf(vampire.getTempBlood()));
            }
            if (vampire.getTempConscience() != 0) {
                element.setAttribute("tempconscience", String.valueOf(vampire.getTempConscience()));
            }
            if (vampire.getTempSelfControl() != 0) {
                element.setAttribute("tempselfcontrol", String.valueOf(vampire.getTempSelfControl()));
            }
            if (vampire.getTempCourage() != 0) {
                element.setAttribute("tempcourage", String.valueOf(vampire.getTempCourage()));
            }
            if (vampire.getTempPathTraits() != 0) {
                element.setAttribute("temppathtraits", String.valueOf(vampire.getTempPathTraits()));
            }
        } else if (character instanceof Changeling changeling) {
            if (changeling.getKith() != null) element.setAttribute("kith", changeling.getKith());
            if (changeling.getSeeming() != null) element.setAttribute("seeming", changeling.getSeeming());
            if (changeling.getHouse() != null) element.setAttribute("house", changeling.getHouse());
            if (changeling.getTitle() != null) element.setAttribute("title", changeling.getTitle());
            if (changeling.getLegacies() != null) element.setAttribute("legacies", changeling.getLegacies());
            if (changeling.getSeelie() != null) element.setAttribute("seelie", changeling.getSeelie());
            if (changeling.getUnseelie() != null) element.setAttribute("unseelie", changeling.getUnseelie());
            if (changeling.getCourt() != null) element.setAttribute("court", changeling.getCourt());
            if (changeling.getThreshold() != null) element.setAttribute("threshold", changeling.getThreshold());
            element.setAttribute("glamour", String.valueOf(changeling.getGlamour()));
            element.setAttribute("banality", String.valueOf(changeling.getBanality()));
            if (changeling.getNightmare() != 0) {
                element.setAttribute("nightmare", String.valueOf(changeling.getNightmare()));
            }
            if (changeling.getTempGlamour() != 0) {
                element.setAttribute("tempglamour", String.valueOf(changeling.getTempGlamour()));
            }
            if (changeling.getTempBanality() != 0) {
                element.setAttribute("tempbanality", String.valueOf(changeling.getTempBanality()));
            }
            if (changeling.getTempNightmare() != 0) {
                element.setAttribute("tempnightmare", String.valueOf(changeling.getTempNightmare()));
            }
        } else if (character instanceof Wraith wraith) {
            element.setAttribute("ethnos", String.valueOf(wraith.getEthnos()));
            if (wraith.getGuild() != null) element.setAttribute("guild", wraith.getGuild());
            if (wraith.getFaction() != null) element.setAttribute("faction", wraith.getFaction());
            if (wraith.getLegion() != null) element.setAttribute("legion", wraith.getLegion());
            if (wraith.getRank() != null) element.setAttribute("rank", wraith.getRank());
            element.setAttribute("pathos", String.valueOf(wraith.getPathos()));
            element.setAttribute("corpus", String.valueOf(wraith.getCorpus()));
            element.setAttribute("angst", String.valueOf(wraith.getAngst()));
            if (wraith.getTempPathos() != 0) {
                element.setAttribute("temppathos", String.valueOf(wraith.getTempPathos()));
            }
            if (wraith.getTempCorpus() != 0) {
                element.setAttribute("tempcorpus", String.valueOf(wraith.getTempCorpus()));
            }
            if (wraith.getTempAngst() != 0) {
                element.setAttribute("tempangst", String.valueOf(wraith.getTempAngst()));
            }
        } else if (character instanceof KueiJin kueiJin) {
            if (kueiJin.getDharma() != null) element.setAttribute("dharma", kueiJin.getDharma());
            if (kueiJin.getDirection() != null) element.setAttribute("direction", kueiJin.getDirection());
            if (kueiJin.getBalance() != null) element.setAttribute("balance", kueiJin.getBalance());
            if (kueiJin.getStation() != null) element.setAttribute("station", kueiJin.getStation());
            if (kueiJin.getPoArchetype() != null) element.setAttribute("poarchetype", kueiJin.getPoArchetype());
            element.setAttribute("hun", String.valueOf(kueiJin.getHun()));
            element.setAttribute("po", String.valueOf(kueiJin.getPo()));
            element.setAttribute("yinchi", String.valueOf(kueiJin.getYinChi()));
            element.setAttribute("yangchi", String.valueOf(kueiJin.getYangChi()));
            element.setAttribute("demonchi", String.valueOf(kueiJin.getDemonChi()));
            element.setAttribute("dharmatraits", String.valueOf(kueiJin.getDharmaTraits()));
            if (kueiJin.getTempHun() != 0) {
                element.setAttribute("temphun", String.valueOf(kueiJin.getTempHun()));
            }
            if (kueiJin.getTempPo() != 0) {
                element.setAttribute("temppo", String.valueOf(kueiJin.getTempPo()));
            }
            if (kueiJin.getTempYinChi() != 0) {
                element.setAttribute("tempyinchi", String.valueOf(kueiJin.getTempYinChi()));
            }
            if (kueiJin.getTempYangChi() != 0) {
                element.setAttribute("tempyangchi", String.valueOf(kueiJin.getTempYangChi()));
            }
            if (kueiJin.getTempDemonChi() != 0) {
                element.setAttribute("tempdemonchi", String.valueOf(kueiJin.getTempDemonChi()));
            }
            if (kueiJin.getTempDharmaTraits() != 0) {
                element.setAttribute("tempdharmatraits", String.valueOf(kueiJin.getTempDharmaTraits()));
            }
        } else if (character instanceof Demon demon) {
            if (demon.getHouse() != null) element.setAttribute("house", demon.getHouse());
            if (demon.getFaction() != null) element.setAttribute("faction", demon.getFaction());
            element.setAttribute("torment", String.valueOf(demon.getTorment()));
            element.setAttribute("faith", String.valueOf(demon.getFaith()));
            element.setAttribute("conscience", String.valueOf(demon.getConscience()));
            element.setAttribute("conviction", String.valueOf(demon.getConviction()));
            element.setAttribute("courage", String.valueOf(demon.getCourage()));
            if (demon.getTempTorment() != 0) {
                element.setAttribute("temptorment", String.valueOf(demon.getTempTorment()));
            }
            if (demon.getTempFaith() != 0) {
                element.setAttribute("tempfaith", String.valueOf(demon.getTempFaith()));
            }
            if (demon.getTempConscience() != 0) {
                element.setAttribute("tempconscience", String.valueOf(demon.getTempConscience()));
            }
            if (demon.getTempConviction() != 0) {
                element.setAttribute("tempconviction", String.valueOf(demon.getTempConviction()));
            }
            if (demon.getTempCourage() != 0) {
                element.setAttribute("tempcourage", String.valueOf(demon.getTempCourage()));
            }
        } else if (character instanceof Werewolf werewolf) {
            // Save Werewolf-specific fields
            if (werewolf.getTribe() != null) element.setAttribute("tribe", werewolf.getTribe());
            if (werewolf.getCamp() != null) element.setAttribute("camp", werewolf.getCamp());
            // Save Fera fields (from parent class)
            if (werewolf.getBreed() != null) element.setAttribute("breed", werewolf.getBreed());
            if (werewolf.getAuspice() != null) element.setAttribute("auspice", werewolf.getAuspice());
            if (werewolf.getRank() != null) element.setAttribute("rank", werewolf.getRank());
            if (werewolf.getPack() != null) element.setAttribute("pack", werewolf.getPack());
            if (werewolf.getTotem() != null) element.setAttribute("totem", werewolf.getTotem());
            if (werewolf.getPosition() != null) element.setAttribute("position", werewolf.getPosition());
            element.setAttribute("notoriety", String.valueOf(werewolf.getNotoriety()));
            element.setAttribute("rage", String.valueOf(werewolf.getRage()));
            element.setAttribute("gnosis", String.valueOf(werewolf.getGnosis()));
            if (werewolf.getTempRage() != 0) {
                element.setAttribute("temprage", String.valueOf(werewolf.getTempRage()));
            }
            if (werewolf.getTempGnosis() != 0) {
                element.setAttribute("tempgnosis", String.valueOf(werewolf.getTempGnosis()));
            }
            element.setAttribute("honor", String.valueOf(werewolf.getHonor()));
            element.setAttribute("glory", String.valueOf(werewolf.getGlory()));
            element.setAttribute("wisdom", String.valueOf(werewolf.getWisdom()));
            if (werewolf.getTempHonor() != 0.0f) {
                element.setAttribute("temphonor", String.valueOf(werewolf.getTempHonor()));
            }
            if (werewolf.getTempGlory() != 0.0f) {
                element.setAttribute("tempglory", String.valueOf(werewolf.getTempGlory()));
            }
            if (werewolf.getTempWisdom() != 0.0f) {
                element.setAttribute("tempwisdom", String.valueOf(werewolf.getTempWisdom()));
            }
        } else if (character instanceof Fera fera) {
            // Save Fera fields for non-Werewolf Fera instances
            if (fera.getBreed() != null) element.setAttribute("breed", fera.getBreed());
            if (fera.getAuspice() != null) element.setAttribute("auspice", fera.getAuspice());
            if (fera.getRank() != null) element.setAttribute("rank", fera.getRank());
            if (fera.getPack() != null) element.setAttribute("pack", fera.getPack());
            if (fera.getTotem() != null) element.setAttribute("totem", fera.getTotem());
            if (fera.getPosition() != null) element.setAttribute("position", fera.getPosition());
            element.setAttribute("notoriety", String.valueOf(fera.getNotoriety()));
            element.setAttribute("rage", String.valueOf(fera.getRage()));
            element.setAttribute("gnosis", String.valueOf(fera.getGnosis()));
            if (fera.getTempRage() != 0) {
                element.setAttribute("temprage", String.valueOf(fera.getTempRage()));
            }
            if (fera.getTempGnosis() != 0) {
                element.setAttribute("tempgnosis", String.valueOf(fera.getTempGnosis()));
            }
            element.setAttribute("honor", String.valueOf(fera.getHonor()));
            element.setAttribute("glory", String.valueOf(fera.getGlory()));
            element.setAttribute("wisdom", String.valueOf(fera.getWisdom()));
            if (fera.getTempHonor() != 0.0f) {
                element.setAttribute("temphonor", String.valueOf(fera.getTempHonor()));
            }
            if (fera.getTempGlory() != 0.0f) {
                element.setAttribute("tempglory", String.valueOf(fera.getTempGlory()));
            }
            if (fera.getTempWisdom() != 0.0f) {
                element.setAttribute("tempwisdom", String.valueOf(fera.getTempWisdom()));
            }
            element.setAttribute("fera", String.valueOf(fera.getSpecies()));
        } else if (character instanceof Mage mage) {
            if (mage.getEssence() != null) element.setAttribute("essence", mage.getEssence());
            if (mage.getTradition() != null) element.setAttribute("tradition", mage.getTradition());
            if (mage.getFaction() != null) element.setAttribute("faction", mage.getFaction());
            if (mage.getCabal() != null) element.setAttribute("cabal", mage.getCabal());
            if (mage.getRank() != null) element.setAttribute("rank", mage.getRank());
            element.setAttribute("arete", String.valueOf(mage.getArete()));
            element.setAttribute("quintessence", String.valueOf(mage.getQuintessence()));
            element.setAttribute("paradox", String.valueOf(mage.getParadox()));
            if (mage.getTempArete() != 0) {
                element.setAttribute("temparete", String.valueOf(mage.getTempArete()));
            }
            if (mage.getTempQuintessence() != 0) {
                element.setAttribute("tempquintessence", String.valueOf(mage.getTempQuintessence()));
            }
            if (mage.getTempParadox() != 0) {
                element.setAttribute("tempparadox", String.valueOf(mage.getTempParadox()));
            }
        } else if (character instanceof Hunter hunter) {
            if (hunter.getCreed() != null) element.setAttribute("creed", hunter.getCreed());
            if (hunter.getCamp() != null) element.setAttribute("camp", hunter.getCamp());
            if (hunter.getHandle() != null) element.setAttribute("handle", hunter.getHandle());
            element.setAttribute("conviction", String.valueOf(hunter.getConviction()));
            element.setAttribute("mercy", String.valueOf(hunter.getMercy()));
            element.setAttribute("vision", String.valueOf(hunter.getVision()));
            element.setAttribute("zeal", String.valueOf(hunter.getZeal()));
            if (hunter.getTempConviction() != 0) {
                element.setAttribute("tempconviction", String.valueOf(hunter.getTempConviction()));
            }
            if (hunter.getTempMercy() != 0) {
                element.setAttribute("tempmercy", String.valueOf(hunter.getTempMercy()));
            }
            if (hunter.getTempVision() != 0) {
                element.setAttribute("tempvision", String.valueOf(hunter.getTempVision()));
            }
            if (hunter.getTempZeal() != 0) {
                element.setAttribute("tempzeal", String.valueOf(hunter.getTempZeal()));
            }
        } else if (character instanceof Various various) {
            if (various.getCharacterClass() != null) element.setAttribute("class", various.getCharacterClass());
            if (various.getSubclass() != null) element.setAttribute("subclass", various.getSubclass());
            if (various.getAffinity() != null) element.setAttribute("affinity", various.getAffinity());
            if (various.getPlane() != null) element.setAttribute("plane", various.getPlane());
            if (various.getBrood() != null) element.setAttribute("brood", various.getBrood());
        } else if (character instanceof Mortal mortal) {
            if (mortal.getMotivation() != null) element.setAttribute("motivation", mortal.getMotivation());
            if (mortal.getAssociation() != null) element.setAttribute("association", mortal.getAssociation());
            if (mortal.getTitle() != null) element.setAttribute("title", mortal.getTitle());
            if (mortal.getRegnant() != null) element.setAttribute("regnant", mortal.getRegnant());
            element.setAttribute("blood", String.valueOf(mortal.getBlood()));
            element.setAttribute("humanity", String.valueOf(mortal.getHumanity()));
            element.setAttribute("conscience", String.valueOf(mortal.getConscience()));
            element.setAttribute("selfcontrol", String.valueOf(mortal.getSelfControl()));
            element.setAttribute("courage", String.valueOf(mortal.getCourage()));
            element.setAttribute("truefaith", String.valueOf(mortal.getTrueFaith()));
            if (mortal.getTempBlood() != 0) {
                element.setAttribute("tempblood", String.valueOf(mortal.getTempBlood()));
            }
            if (mortal.getTempHumanity() != 0) {
                element.setAttribute("temphumanity", String.valueOf(mortal.getTempHumanity()));
            }
            if (mortal.getTempConscience() != 0) {
                element.setAttribute("tempconscience", String.valueOf(mortal.getTempConscience()));
            }
            if (mortal.getTempSelfControl() != 0) {
                element.setAttribute("tempselfcontrol", String.valueOf(mortal.getTempSelfControl()));
            }
            if (mortal.getTempCourage() != 0) {
                element.setAttribute("tempcourage", String.valueOf(mortal.getTempCourage()));
            }
            if (mortal.getTempTrueFaith() != 0) {
                element.setAttribute("temptruefaith", String.valueOf(mortal.getTempTrueFaith()));
            }
        } else if (character instanceof Mummy mummy) {
            if (mummy.getAmenti() != null) element.setAttribute("amenti", mummy.getAmenti());
            element.setAttribute("sekhem", String.valueOf(mummy.getSekhem()));
            element.setAttribute("balance", String.valueOf(mummy.getBalance()));
            element.setAttribute("memory", String.valueOf(mummy.getMemory()));
            element.setAttribute("integrity", String.valueOf(mummy.getIntegrity()));
            element.setAttribute("joy", String.valueOf(mummy.getJoy()));
            element.setAttribute("ba", String.valueOf(mummy.getBa()));
            element.setAttribute("ka", String.valueOf(mummy.getKa()));
            if (mummy.getTempSekhem() != 0) {
                element.setAttribute("tempsekhem", String.valueOf(mummy.getTempSekhem()));
            }
            if (mummy.getTempBalance() != 0) {
                element.setAttribute("tempbalance", String.valueOf(mummy.getTempBalance()));
            }
            if (mummy.getTempMemory() != 0) {
                element.setAttribute("tempmemory", String.valueOf(mummy.getTempMemory()));
            }
            if (mummy.getTempIntegrity() != 0) {
                element.setAttribute("tempintegrity", String.valueOf(mummy.getTempIntegrity()));
            }
            if (mummy.getTempJoy() != 0) {
                element.setAttribute("tempjoy", String.valueOf(mummy.getTempJoy()));
            }
            if (mummy.getTempBa() != 0) {
                element.setAttribute("tempba", String.valueOf(mummy.getTempBa()));
            }
            if (mummy.getTempKa() != 0) {
                element.setAttribute("tempka", String.valueOf(mummy.getTempKa()));
            }
        }
    }

    private void addCharacterTypeChildElements(Document doc, Element element, Character character) {
        if (character instanceof Changeling changeling) {
            if (changeling.getOaths() != null && !changeling.getOaths().isEmpty()) {
                Element oaths = doc.createElement("oaths");
                FileUtils.setCDataContent(doc, oaths, changeling.getOaths());
                element.appendChild(oaths);
            }
        } else if (character instanceof Mage mage) {
            if (mage.getFoci() != null && !mage.getFoci().isEmpty()) {
                Element foci = doc.createElement("foci");
                FileUtils.setCDataContent(doc, foci, mage.getFoci());
                element.appendChild(foci);
            }
            if (mage.getBiography() != null && !mage.getBiography().isEmpty()) {
                Element biography = doc.createElement("biography");
                FileUtils.setCDataContent(doc, biography, mage.getBiography());
                element.appendChild(biography);
            }
        } else if (character instanceof Wraith wraith) {
            if (wraith.getPassions() != null && !wraith.getPassions().isEmpty()) {
                Element passions = doc.createElement("passions");
                FileUtils.setCDataContent(doc, passions, wraith.getPassions());
                element.appendChild(passions);
            }

            if (wraith.getFetters() != null && !wraith.getFetters().isEmpty()) {
                Element fetters = doc.createElement("fetters");
                FileUtils.setCDataContent(doc, fetters, wraith.getFetters());
                element.appendChild(fetters);
            }

            if (wraith.getLife() != null && !wraith.getLife().isEmpty()) {
                Element life = doc.createElement("life");
                FileUtils.setCDataContent(doc, life, wraith.getLife());
                element.appendChild(life);
            }

            if (wraith.getDeath() != null && !wraith.getDeath().isEmpty()) {
                Element death = doc.createElement("death");
                FileUtils.setCDataContent(doc, death, wraith.getDeath());
                element.appendChild(death);
            }

            if (wraith.getHaunt() != null && !wraith.getHaunt().isEmpty()) {
                Element haunt = doc.createElement("haunt");
                FileUtils.setCDataContent(doc, haunt, wraith.getHaunt());
                element.appendChild(haunt);
            }

            if (wraith.getRegret() != null && !wraith.getRegret().isEmpty()) {
                Element regret = doc.createElement("regret");
                FileUtils.setCDataContent(doc, regret, wraith.getRegret());
                element.appendChild(regret);
            }

            if (wraith.getShadowArchetype() != null && !wraith.getShadowArchetype().isEmpty()) {
                Element shadowArchetype = doc.createElement("shadowarchetype");
                FileUtils.setCDataContent(doc, shadowArchetype, wraith.getShadowArchetype());
                element.appendChild(shadowArchetype);
            }

            if (wraith.getShadowPlayer() != null && !wraith.getShadowPlayer().isEmpty()) {
                Element shadowPlayer = doc.createElement("shadowplayer");
                FileUtils.setCDataContent(doc, shadowPlayer, wraith.getShadowPlayer());
                element.appendChild(shadowPlayer);
            }

            if (wraith.getDarkPassions() != null && !wraith.getDarkPassions().isEmpty()) {
                Element darkPassions = doc.createElement("darkpassions");
                FileUtils.setCDataContent(doc, darkPassions, wraith.getDarkPassions());
                element.appendChild(darkPassions);
            }
        } else if (character instanceof Mortal mortal) {
            if (mortal.getOther() != null && !mortal.getOther().isEmpty()) {
                Element other = doc.createElement("other");
                FileUtils.setCDataContent(doc, other, mortal.getOther());
                element.appendChild(other);
            }
        } else if (character instanceof Mummy mummy) {
            if (mummy.getInheritance() != null && !mummy.getInheritance().isEmpty()) {
                Element inheritance = doc.createElement("inheritance");
                FileUtils.setCDataContent(doc, inheritance, mummy.getInheritance());
                element.appendChild(inheritance);
            }
        } else if (character instanceof Various various) {
            if (various.getOther() != null && !various.getOther().isEmpty()) {
                Element other = doc.createElement("other");
                FileUtils.setCDataContent(doc, other, various.getOther());
                element.appendChild(other);
            }

            if (various.getBiography() != null && !various.getBiography().isEmpty()) {
                Element biography = doc.createElement("biography");
                FileUtils.setCDataContent(doc, biography, various.getBiography());
                element.appendChild(biography);
            }

            if (various.getNotes() != null && !various.getNotes().isEmpty()) {
                Element notes = doc.createElement("notes");
                FileUtils.setCDataContent(doc, notes, various.getNotes());
                element.appendChild(notes);
            }
        }
    }

    private void saveCharacterTraitLists(Document doc, Element element, Character character) {
        // Save common trait lists
        saveTraitList(doc, element, "Physical", character.getPhysicalList());
        saveTraitList(doc, element, "Social", character.getSocialList());
        saveTraitList(doc, element, "Mental", character.getMentalList());
        saveTraitList(doc, element, "Negative Physical", character.getPhysicalNegList());
        saveTraitList(doc, element, "Negative Social", character.getSocialNegList());
        saveTraitList(doc, element, "Negative Mental", character.getMentalNegList());

        saveTraitList(doc, element, "Abilities", character.getAbilityList());
        saveTraitList(doc, element, "Influences", character.getInfluenceList());
        saveTraitList(doc, element, "Backgrounds", character.getBackgroundList());
        saveTraitList(doc, element, "Health Levels", character.getHealthList());

        saveTraitList(doc, element, "Backgrounds", character.getBackgroundList());
        saveTraitList(doc, element, "Health Levels", character.getHealthList());

        saveTraitList(doc, element, "Derangements", character.getDerangementList());
        saveTraitList(doc, element, "Merits", character.getMeritList());
        saveTraitList(doc, element, "Flaws", character.getFlawList());
        saveTraitList(doc, element, "Equipment", character.getEquipmentList());
        saveTraitList(doc, element, "Locations", character.getHangoutList());

        // Character-type-specific trait lists continued
        if (character instanceof Vampire vampire) {
            saveTraitList(doc, element, "Disciplines", vampire.getDisciplineList());
            saveTraitList(doc, element, "Rituals", vampire.getRitualList());
            saveTraitList(doc, element, "Status", vampire.getStatusList());
            saveTraitList(doc, element, "Bonds", vampire.getBondList());
            saveTraitList(doc, element, "Boons", vampire.getBoonTraitList());
            saveTraitList(doc, element, "Miscellaneous", vampire.getMiscellaneousList());
        } else if (character instanceof Changeling changeling) {
            saveTraitList(doc, element, "Status", changeling.getStatusList());
            saveTraitList(doc, element, "Arts", changeling.getArtList());
            saveTraitList(doc, element, "Realms", changeling.getRealmList());
        } else if (character instanceof Mage mage) {
            saveTraitList(doc, element, "Resonance", mage.getResonanceList());
            saveTraitList(doc, element, "Reputation", mage.getReputationList());
            saveTraitList(doc, element, "Spheres", mage.getSphereList());
            saveTraitList(doc, element, "Rotes", mage.getRoteList());
        } else if (character instanceof Wraith wraith) {
            saveTraitList(doc, element, "Status", wraith.getStatusList());
            saveTraitList(doc, element, "Arcanoi", wraith.getArcanoiList());
            saveTraitList(doc, element, "Thorns", wraith.getThornList());
        } else if (character instanceof Various various) {
            saveTraitList(doc, element, "Tempers", various.getTemperList());
            saveTraitList(doc, element, "Powers", various.getPowerList());
        } else if (character instanceof Demon demon) {
            saveTraitList(doc, element, "Lores", demon.getLoreList());
            saveTraitList(doc, element, "Apocalyptic Form", demon.getVisageList());
        } else if (character instanceof KueiJin kueiJin) {
            saveTraitList(doc, element, "Status", kueiJin.getStatusList());
            saveTraitList(doc, element, "Guanxi", kueiJin.getGuanxiList());
            saveTraitList(doc, element, "Disciplines", kueiJin.getDisciplineList());
            saveTraitList(doc, element, "Rites", kueiJin.getRiteList());
        } else if (character instanceof Hunter hunter) {
            saveTraitList(doc, element, "Edges", hunter.getEdgeList());
        } else if (character instanceof Mortal mortal) {
            saveTraitList(doc, element, "Humanity", mortal.getHumanityList());
            saveTraitList(doc, element, "Numina", mortal.getNuminaList());
        } else if (character instanceof Mummy mummy) {
            saveTraitList(doc, element, "Humanity", mummy.getHumanityList());
            saveTraitList(doc, element, "Status", mummy.getStatusList());
            saveTraitList(doc, element, "Hekau", mummy.getHekauList());
            saveTraitList(doc, element, "Spells", mummy.getSpellList());
            saveTraitList(doc, element, "Rituals", mummy.getRitualList());
        } else if (character instanceof Fera fera) {
            saveTraitList(doc, element, "Features", fera.getFeatureList());
            saveTraitList(doc, element, "Gifts", fera.getGiftList());
            saveTraitList(doc, element, "Rites", fera.getRiteList());
            saveTraitList(doc, element, "Honor", fera.getHonorList());
            saveTraitList(doc, element, "Glory", fera.getGloryList());
            saveTraitList(doc, element, "Wisdom", fera.getWisdomList());
        }
    }

    private void saveTraitList(Document doc, Element parent, String name, List<Trait> traits) {
        Element traitList = doc.createElement("traitlist");
        traitList.setAttribute("name", name);
        traitList.setAttribute("abc", "yes");

        //Elements not used in Grapevine J.
        traitList.setAttribute("negative", "no");
        traitList.setAttribute("atomic", "yes");
        traitList.setAttribute("display", "1");

        if (traits != null && !traits.isEmpty()) {
            for (Trait trait : traits) {
                Element traitElement = doc.createElement("trait");
                if (trait.getName() != null) {
                    traitElement.setAttribute("name", trait.getName());
                }
                if (trait.getTotal() != null && !trait.getTotal().isEmpty()) {
                    traitElement.setAttribute("val", trait.getTotal());
                }
                if (trait.getNote() != null && !trait.getNote().isEmpty()) {
                    traitElement.setAttribute("note", trait.getNote());
                }
                traitList.appendChild(traitElement);
            }
        }

        parent.appendChild(traitList);
    }

    private Element saveItem(Document doc, Item item) {
        Element element = doc.createElement("item");

        if (item.getName() != null) element.setAttribute("name", item.getName());
        if (item.getItemType() != null) element.setAttribute("type", item.getItemType());
        if (item.getItemSubtype() != null) element.setAttribute("subtype", item.getItemSubtype());
        element.setAttribute("level", String.valueOf(item.getLevel()));
        element.setAttribute("bonus", String.valueOf(item.getBonus()));
        if (item.getDamageType() != null) element.setAttribute("damage", item.getDamageType());
        element.setAttribute("amount", String.valueOf(item.getDamageAmount()));
        if (item.getConcealability() != null) element.setAttribute("conceal", item.getConcealability());
        if (item.getLastModified() != null) {
            element.setAttribute("lastmodified", FileUtils.formatDate(item.getLastModified()));
        }

        if (item.getPowers() != null && !item.getPowers().isEmpty()) {
            Element powers = doc.createElement("powers");
            FileUtils.setCDataContent(doc, powers, item.getPowers());
            element.appendChild(powers);
        }

        if (item.getAppearance() != null && !item.getAppearance().isEmpty()) {
            Element appearance = doc.createElement("appearance");
            FileUtils.setCDataContent(doc, appearance, item.getAppearance());
            element.appendChild(appearance);
        }

        if (item.getNotes() != null && !item.getNotes().isEmpty()) {
            Element notes = doc.createElement("notes");
            FileUtils.setCDataContent(doc, notes, item.getNotes());
            element.appendChild(notes);
        }

        return element;
    }

    private Element saveRote(Document doc, Rote rote) {
        Element element = doc.createElement("rote");

        if (rote.getName() != null) element.setAttribute("name", rote.getName());
        element.setAttribute("level", String.valueOf(rote.getLevel()));
        if (rote.getDuration() != null) element.setAttribute("duration", rote.getDuration());
        if (rote.getLastModified() != null) {
            element.setAttribute("lastmodified", FileUtils.formatDate(rote.getLastModified()));
        }

        if (rote.getDescription() != null && !rote.getDescription().isEmpty()) {
            Element description = doc.createElement("description");
            FileUtils.setCDataContent(doc, description, rote.getDescription());
            element.appendChild(description);
        }

        if (rote.getGrades() != null && !rote.getGrades().isEmpty()) {
            Element grades = doc.createElement("grades");
            FileUtils.setCDataContent(doc, grades, rote.getGrades());
            element.appendChild(grades);
        }

        return element;
    }

    private Element saveLocation(Document doc, Location location) {
        Element element = doc.createElement("location");

        if (location.getName() != null) element.setAttribute("name", location.getName());
        if (location.getLocType() != null) element.setAttribute("type", location.getLocType());
        element.setAttribute("level", String.valueOf(location.getLevel()));
        if (location.getOwner() != null) element.setAttribute("owner", location.getOwner());
        if (location.getAccess() != null) element.setAttribute("access", location.getAccess());
        element.setAttribute("securitytraits", String.valueOf(location.getSecTraits()));
        element.setAttribute("securityretests", String.valueOf(location.getSecRetests()));
        element.setAttribute("gauntlet", String.valueOf(location.getGauntlet()));
        if (location.getAffinity() != null) element.setAttribute("affinity", location.getAffinity());
        if (location.getTotem() != null) element.setAttribute("totem", location.getTotem());
        if (location.getLastModified() != null) {
            element.setAttribute("lastmodified", FileUtils.formatDate(location.getLastModified()));
        }

        if (location.getWhere() != null && !location.getWhere().isEmpty()) {
            Element where = doc.createElement("where");
            FileUtils.setCDataContent(doc, where, location.getWhere());
            element.appendChild(where);
        }

        if (location.getAppearance() != null && !location.getAppearance().isEmpty()) {
            Element appearance = doc.createElement("appearance");
            FileUtils.setCDataContent(doc, appearance, location.getAppearance());
            element.appendChild(appearance);
        }

        if (location.getSecurity() != null && !location.getSecurity().isEmpty()) {
            Element security = doc.createElement("security");
            FileUtils.setCDataContent(doc, security, location.getSecurity());
            element.appendChild(security);
        }

        if (location.getUmbra() != null && !location.getUmbra().isEmpty()) {
            Element umbra = doc.createElement("umbra");
            FileUtils.setCDataContent(doc, umbra, location.getUmbra());
            element.appendChild(umbra);
        }

        if (location.getNotes() != null && !location.getNotes().isEmpty()) {
            Element notes = doc.createElement("notes");
            FileUtils.setCDataContent(doc, notes, location.getNotes());
            element.appendChild(notes);
        }

        return element;
    }

    private Element saveAction(Document doc, Action action) {
        Element element = doc.createElement("action");

        if (action.getCharName() != null) element.setAttribute("character", action.getCharName());
        if (action.getActDate() != null) {
            element.setAttribute("date", FileUtils.formatDate(action.getActDate()));
        }
        if (action.isDone()) {
            element.setAttribute("done", "yes");
        }
        if (action.getLastModified() != null) {
            element.setAttribute("lastmodified", FileUtils.formatDate(action.getLastModified()));
        }

        return element;
    }

    private Element saveRumor(Document doc, Rumor rumor) {
        Element element = doc.createElement("rumor");

        if (rumor.getTitle() != null) element.setAttribute("title", rumor.getTitle());
        if (rumor.getRumorDate() != null) {
            element.setAttribute("date", FileUtils.formatDate(rumor.getRumorDate()));
        }
        if (rumor.isDone()) {
            element.setAttribute("done", "yes");
        }
        if (rumor.getLastModified() != null) {
            element.setAttribute("lastmodified", FileUtils.formatDate(rumor.getLastModified()));
        }

        return element;
    }
}
