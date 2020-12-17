package ch.zhaw.ads.Praktikum_14;
import ch.zhaw.ads.CommandExecutor;

import java.util.Random;

public class BakeryCookieServer implements CommandExecutor {
    private Ingredient mehl, zucker, salz, eier, butter, milch, zitrone, konfituere, schokolade;
    private Ingredient[] stockOfIngredients;

    private Cookie mailaenderli, spitzbuben, brownies;
    private Cookie[] cookiesOfBakery;

    private float temperature;
    private Random heatGenerator = new Random();

    public String execute (String input) {
        initialiseIngredients();
        initialiseCookies();
        return simulatedAnnealing();
    }

    /**
     * initializes the ingredients and and their quantity in stock
     */
    private void initialiseIngredients()
    {
        mehl = new Ingredient("Mehl", 20000);
        zucker = new Ingredient("Zucker", 25000);
        salz = new Ingredient("Salz", 5000);
        eier = new Ingredient("Eier", 2000);
        butter = new Ingredient("Butter", 10000);
        milch = new Ingredient("Milch", 5000);
        zitrone = new Ingredient("Zitrone", 10000);
        konfituere = new Ingredient("Konfitüre", 2000);
        schokolade = new Ingredient("Schokolade", 1000);
        stockOfIngredients = new Ingredient[]{mehl, zucker, salz, eier, butter, milch, zitrone, konfituere, schokolade};
    }

    /**
     * initialises the cookies and the ingredients used for one cookie
     */
    private void initialiseCookies()
    {
        mailaenderli = new Cookie("Mailänderli", 7);
        mailaenderli.addIngredient(mehl, 15);
        mailaenderli.addIngredient(zucker,11.25f);
        mailaenderli.addIngredient(salz, 0.2f);
        mailaenderli.addIngredient(eier,5);
        mailaenderli.addIngredient(butter, 12.5f);
        mailaenderli.addIngredient(milch, 1);
        mailaenderli.addIngredient(zitrone, 7.5f);

        spitzbuben = new Cookie("Spitzbuben", 6);
        spitzbuben.addIngredient(mehl, 25.5f);
        spitzbuben.addIngredient(zucker,6.25f);
        spitzbuben.addIngredient(salz, 0.15f);
        spitzbuben.addIngredient(eier,4);
        spitzbuben.addIngredient(butter, 12.5f);
        spitzbuben.addIngredient(konfituere, 10.5f);

        brownies = new Cookie("Brownies", 6);
        brownies.addIngredient(mehl, 15.5f);
        brownies.addIngredient(zucker,10);
        brownies.addIngredient(salz, 0.10f);
        brownies.addIngredient(eier,3);
        brownies.addIngredient(butter, 10.5f);
        brownies.addIngredient(schokolade, 20.5f);

        cookiesOfBakery = new Cookie[] {mailaenderli, spitzbuben, brownies};
    }

    /**
     * uses simulated annealing to generate new possible solutions
     * @return string contains the best found solution for all cookies
     */
    private String simulatedAnnealing()
    {
        String result = "";
        creationOfInitialConfiguration();
        for(double i = 200.0; i >= 1; i -= 0.001){
            for (Cookie cookie : cookiesOfBakery) {
                int next = cookie.getBestNrOfCookies() + (int) (heatGenerator.nextDouble() * i - i/2);
                if (next < 0) next = 0;
                cookie.setTestedNrOfCookies(next);
            }
            if (checkIfValidSolution() && checkIfMoreCookiesAndBetterSolution()){
                for (Cookie cookie : cookiesOfBakery){
                    cookie.setBestNrOfCookies(cookie.getTestedNrOfCookies());
                }
            }
        }

        int total = 0;
        for (Cookie cookie : cookiesOfBakery) {
            result += cookie.getNameOfCookie() + ": " + cookie.getBestNrOfCookies() + ", ";
            total += cookie.getBestNrOfCookies();
        }
        result += "Total: " + total;
        return result + "\n";
    }

    /**
     * initialize very first run (temperature)
     */
    private void creationOfInitialConfiguration(){
        for (Cookie cookie : cookiesOfBakery) {
            cookie.setBestNrOfCookies(120);
        }
    }

    /**
     * Generate a randomized new test set of cookies
     */
    private void generateNextTestConfiguration() {
        // to do
    }

    /**
     * Check if this is a better solution (more cookies) then the solution already found
     * @return true if the newly tested solution is better
     */
    private boolean checkIfMoreCookiesAndBetterSolution() {
        // to do
        int best = 0;
        int current = 0;
        for (Cookie cookie : cookiesOfBakery){
            best += cookie.getBestNrOfCookies();
            current += cookie.getTestedNrOfCookies();
        }
        return best < current;
    }

    /**
     * Check if there are enough ingredients to bake this new solution
     * @return true if enough ingredients
     */
    private boolean checkIfValidSolution() {
        for (Ingredient ingredient : stockOfIngredients) {ingredient.initialiseStockBeforeCooking(); }
        for (Cookie cookie : cookiesOfBakery){
            for(int i = 0; i < cookie.getNrOfIngredients(); ++i){
                if (!cookie.getIngredientOfCookie(i).removeFromStock(cookie.getGramsPerIngredient(i) *
                        cookie.getTestedNrOfCookies())) return false;
            }
        }
        return true;
    }

}
