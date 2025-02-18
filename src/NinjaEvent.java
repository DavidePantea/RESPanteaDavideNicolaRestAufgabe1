import java.time.LocalDate;

enum NinjaRank {
    GENIN,
    CHUNIN,
    JONIN,
    KAGE
}


public class NinjaEvent {

    private int id;
    private String characterName;
    private NinjaRank rank;
    private String description;
    private String date;
    private double powerLevel;

    public NinjaEvent(int id, String characterName, NinjaRank rank, String description, String date, double powerLevel) {
        this.id = id;
        this.characterName = characterName;
        this.rank = rank;
        this.description = description;
        this.date = date;
        this.powerLevel = powerLevel;
    }

    // Getter und Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCharacterName() {
        return characterName;
    }

    public void setCharacterName(String characterName) {
        this.characterName = characterName;
    }

    public NinjaRank getRank() {
        return rank;
    }

    public void setRank(NinjaRank rank) {
        this.rank = rank;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getPowerLevel() {
        return powerLevel;
    }

    public void setPowerLevel(double powerLevel) {
        this.powerLevel = powerLevel;
    }

    @Override
    public String toString() {
        return "NinjaEvent{" +
                "id=" + id +
                ", characterName='" + characterName + '\'' +
                ", rank=" + rank +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", powerLevel=" + powerLevel +
                '}';
    }
}


