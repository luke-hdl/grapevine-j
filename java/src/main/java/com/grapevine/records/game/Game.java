package com.grapevine.records.game;

import com.grapevine.io.printing.Template;
import com.grapevine.records.sheet.Item;
import com.grapevine.records.sheet.Rote;
import com.grapevine.records.sheet.character.Character;
import com.grapevine.records.sheet.experience.ExperienceAward;
import com.grapevine.records.player.Player;
import com.grapevine.records.story.Action;
import com.grapevine.records.story.Location;
import com.grapevine.records.story.Rumor;

import java.util.List;

public class Game {
    protected List<Player> playerList;
    protected List<Character> characterList;
    protected List<Item> itemList;
    protected List<Rote> roteList;
    protected List<Location> locationList;
    protected List<Rumor> allRumorLists;
    protected List<Action> influenceUseList;
    protected String gameFile;
    protected boolean dataChanged;
    protected String chronicleTitle;
    protected String website;
    protected String email;
    protected String phone;
    protected String usualPlace;
    protected String usualTime;
    protected String description;
    protected Calendar calendar;
    protected boolean extendedHealth;
    protected boolean enforceHistory;
    protected boolean linkTraitMaxes;
    protected String randomTraits;
    protected String stCommentStart;
    protected String stCommentEnd;
    protected List<ExperienceAward> xpAwardList;
    protected List<Template> templateList;
    protected boolean fileError;
    protected String fileErrorMessage;
    protected String mergeResults;

    public Game() {
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public List<Character> getCharacterList() {
        return characterList;
    }

    public void setCharacterList(List<Character> characterList) {
        this.characterList = characterList;
    }

    public List<Item> getItemList() {
        return itemList;
    }

    public void setItemList(List<Item> itemList) {
        this.itemList = itemList;
    }

    public List<Rote> getRoteList() {
        return roteList;
    }

    public void setRoteList(List<Rote> roteList) {
        this.roteList = roteList;
    }

    public List<Location> getLocationList() {
        return locationList;
    }

    public void setLocationList(List<Location> locationList) {
        this.locationList = locationList;
    }

    public List<Rumor> getAllRumorLists() {
        return allRumorLists;
    }

    public void setAllRumorLists(List<Rumor> allRumorLists) {
        this.allRumorLists = allRumorLists;
    }

    public List<Action> getInfluenceUseList() {
        return influenceUseList;
    }

    public void setInfluenceUseList(List<Action> influenceUseList) {
        this.influenceUseList = influenceUseList;
    }

    public String getGameFile() {
        return gameFile;
    }

    public void setGameFile(String gameFile) {
        this.gameFile = gameFile;
    }

    public boolean isDataChanged() {
        return dataChanged;
    }

    public void setDataChanged(boolean dataChanged) {
        this.dataChanged = dataChanged;
    }

    public String getChronicleTitle() {
        return chronicleTitle;
    }

    public void setChronicleTitle(String chronicleTitle) {
        this.chronicleTitle = chronicleTitle;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsualPlace() {
        return usualPlace;
    }

    public void setUsualPlace(String usualPlace) {
        this.usualPlace = usualPlace;
    }

    public String getUsualTime() {
        return usualTime;
    }

    public void setUsualTime(String usualTime) {
        this.usualTime = usualTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getCalendar() {
        return calendar;
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
    }

    public boolean isExtendedHealth() {
        return extendedHealth;
    }

    public void setExtendedHealth(boolean extendedHealth) {
        this.extendedHealth = extendedHealth;
    }

    public boolean isEnforceHistory() {
        return enforceHistory;
    }

    public void setEnforceHistory(boolean enforceHistory) {
        this.enforceHistory = enforceHistory;
    }

    public boolean isLinkTraitMaxes() {
        return linkTraitMaxes;
    }

    public void setLinkTraitMaxes(boolean linkTraitMaxes) {
        this.linkTraitMaxes = linkTraitMaxes;
    }

    public String getRandomTraits() {
        return randomTraits;
    }

    public void setRandomTraits(String randomTraits) {
        this.randomTraits = randomTraits;
    }

    public String getStCommentStart() {
        return stCommentStart;
    }

    public void setStCommentStart(String stCommentStart) {
        this.stCommentStart = stCommentStart;
    }

    public String getStCommentEnd() {
        return stCommentEnd;
    }

    public void setStCommentEnd(String stCommentEnd) {
        this.stCommentEnd = stCommentEnd;
    }

    public List<ExperienceAward> getXpAwardList() {
        return xpAwardList;
    }

    public void setXpAwardList(List<ExperienceAward> xpAwardList) {
        this.xpAwardList = xpAwardList;
    }

    public List<Template> getTemplateList() {
        return templateList;
    }

    public void setTemplateList(List<Template> templateList) {
        this.templateList = templateList;
    }

    public boolean isFileError() {
        return fileError;
    }

    public void setFileError(boolean fileError) {
        this.fileError = fileError;
    }

    public String getFileErrorMessage() {
        return fileErrorMessage;
    }

    public void setFileErrorMessage(String fileErrorMessage) {
        this.fileErrorMessage = fileErrorMessage;
    }

    public String getMergeResults() {
        return mergeResults;
    }

    public void setMergeResults(String mergeResults) {
        this.mergeResults = mergeResults;
    }

    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Chronicle Title: ").append(chronicleTitle != null ? chronicleTitle : "").append("\r\n");
        sb.append("Website: ").append(website != null ? website : "").append("\r\n");
        sb.append("Email: ").append(email != null ? email : "").append("\r\n");
        sb.append("Phone: ").append(phone != null ? phone : "").append("\r\n");
        sb.append("Usual Place: ").append(usualPlace != null ? usualPlace : "").append("\r\n");
        sb.append("Usual Time: ").append(usualTime != null ? usualTime : "").append("\r\n");
        sb.append("Description: ").append(description != null ? description : "").append("\r\n");
        sb.append("Extended Health: ").append(extendedHealth).append("\r\n");
        sb.append("Enforce History: ").append(enforceHistory).append("\r\n");
        sb.append("Link Trait Maxes: ").append(linkTraitMaxes).append("\r\n");
        sb.append("Random Traits: ").append(randomTraits != null ? randomTraits : "").append("\r\n");
        sb.append("ST Comment Start: ").append(stCommentStart != null ? stCommentStart : "").append("\r\n");
        sb.append("ST Comment End: ").append(stCommentEnd != null ? stCommentEnd : "").append("\r\n");
        sb.append("Game File: ").append(gameFile != null ? gameFile : "").append("\r\n");
        sb.append("Data Changed: ").append(dataChanged).append("\r\n");
        sb.append("File Error: ").append(fileError).append("\r\n");
        sb.append("File Error Message: ").append(fileErrorMessage != null ? fileErrorMessage : "").append("\r\n");
        sb.append("Merge Results: ").append(mergeResults != null ? mergeResults : "").append("\r\n");
        
        if (calendar != null) {
            sb.append("Calendar:\r\n").append(calendar.toDetailedString());
        }
        
        if (playerList != null && !playerList.isEmpty()) {
            sb.append("Players:\r\n");
            for (Player player : playerList) {
                sb.append(player.toDetailedString());
            }
        }
        
        if (characterList != null && !characterList.isEmpty()) {
            sb.append("Characters:\r\n");
            for (Character character : characterList) {
                sb.append(character.toDetailedString());
            }
        }
        
        if (itemList != null && !itemList.isEmpty()) {
            sb.append("Items:\r\n");
            for (Item item : itemList) {
                sb.append(item.toDetailedString());
            }
        }
        
        if (roteList != null && !roteList.isEmpty()) {
            sb.append("Rotes:\r\n");
            for (Rote rote : roteList) {
                sb.append(rote.toDetailedString());
            }
        }
        
        if (locationList != null && !locationList.isEmpty()) {
            sb.append("Locations:\r\n");
            for (Location location : locationList) {
                sb.append(location.toDetailedString());
            }
        }
        
        if (allRumorLists != null && !allRumorLists.isEmpty()) {
            sb.append("Rumors:\r\n");
            for (Rumor rumor : allRumorLists) {
                sb.append(rumor.toDetailedString());
            }
        }
        
        if (influenceUseList != null && !influenceUseList.isEmpty()) {
            sb.append("Influence Uses:\r\n");
            for (Action action : influenceUseList) {
                sb.append(action.toDetailedString());
            }
        }
        
        if (xpAwardList != null && !xpAwardList.isEmpty()) {
            sb.append("XP Awards:\r\n");
            for (ExperienceAward award : xpAwardList) {
                sb.append(award.toDetailedString());
            }
        }
        
        if (templateList != null && !templateList.isEmpty()) {
            sb.append("Templates:\r\n");
            for (Template template : templateList) {
                sb.append(template.toDetailedString());
            }
        }
        
        return sb.toString();
    }
}
