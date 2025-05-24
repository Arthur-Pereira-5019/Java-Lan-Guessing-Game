package pb.art5019;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Card {
    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("tips")
    public String[] tips;

    public Card(int id, String name, String[] tips) {
        this.id = id;
        this.name = name;
        this.tips = new String[tips.length];
        this.tips = tips;
    }

    public Card() {

    }
}
