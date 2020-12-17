package ch.zhaw.ads.Praktikum_14_Loesung;

public class Cookie {
    private String nameOfCookie;
    private int bestNrOfCookies = 0;
    private int testedNrOfCookies;
    private Ingredient[] ingredientsOfCookie;
    private float[] gramsPerIngredient;
    private int nrOfIngredient = 0;


    Cookie(String nameOfCookie, int nrOfIngridients){
        this.nameOfCookie = nameOfCookie;
        ingredientsOfCookie = new Ingredient[nrOfIngridients];
        gramsPerIngredient = new float[nrOfIngridients];
    }

    public String getNameOfCookie() {
        return nameOfCookie;
    }

    public int getBestNrOfCookies() {
        return bestNrOfCookies;
    }

    public void setBestNrOfCookies(int bestNrOfCookies) {
        this.bestNrOfCookies = bestNrOfCookies;
    }

    public int getTestedNrOfCookies() {
        return testedNrOfCookies;
    }

    public void setTestedNrOfCookies(int testedNrOfCookies) {
        this.testedNrOfCookies = testedNrOfCookies;
    }

    void addIngredient(Ingredient ingredient, float gramsPerCookie) {
        ingredientsOfCookie[nrOfIngredient] = ingredient;
        this.gramsPerIngredient[nrOfIngredient] = gramsPerCookie;
        nrOfIngredient++;
    }

    Ingredient getIngredientOfCookie(int nrOfIngredient)
    {
        return ingredientsOfCookie[nrOfIngredient];
    }

    float getGramsPerIngredient(int nrOfIngredient)
    {
        return gramsPerIngredient[nrOfIngredient];
    }

    int getNrOfIngredients()
    {
        return nrOfIngredient;
    }

}
