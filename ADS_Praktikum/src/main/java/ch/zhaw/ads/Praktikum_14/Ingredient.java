package ch.zhaw.ads.Praktikum_14;
public class Ingredient {
    private String name;
    private float gramInBakery;
    private float gramUsedForBaking;


    Ingredient (String name, int gramInBakery) {
        this.name = name;
        this.gramInBakery = gramInBakery;
    }

    void initialiseStockBeforeCooking() {
        gramUsedForBaking = 0;
    }

    boolean removeFromStock(float gramToRemove)
    {
        gramUsedForBaking += gramToRemove;
        return gramUsedForBaking <= gramInBakery;
    }

    String getRemainingStock() {
        return name + ": " + Float.toString(gramInBakery - gramUsedForBaking) + ", ";
    }

    public float getGramInBakery() {
        return gramInBakery;
    }

    public float getGramUsedForBaking() {
        return gramUsedForBaking;
    }
}
