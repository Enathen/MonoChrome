package ENSK.Windows.Article;

import org.jfree.ui.RefineryUtilities;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by Enathen on 2017-03-28.
 */
public class Category implements Comparable<Category>{
    String nameCategory;

    public TreeSet<Article> treeSetOfArticles = new TreeSet<>();


    /**
     *
     * @param name to set name to new category.
     */
    public Category(String name){
        nameCategory = name;
    }

    /**
     *
     * @param name to rename category.
     */
    public void setNameCategory(String name){
        nameCategory = name;
    }

    /**
     *
     * @return the category name
     */
    public String getNameCategory(){
        return nameCategory;
    }

    /**
     *
     * @param article to be added.
     */
    public void addArticle(Article article){
        treeSetOfArticles.add(article);
    }

    /**
     *
     * @param listOfArticle Adds noumerous article to the tree set.
     */
    public void addSeveralArticles(TreeSet listOfArticle){
        treeSetOfArticles.addAll(listOfArticle);

    }

    /**
     *
     * @param article remove one article from tree set.
     */
    public void removeArticle(Article article){
        if(treeSetOfArticles.contains(article)){
            if(article.getStock()>0){
                System.out.println("Are you sure you want to delete? Press ENTER to proceed");
                Scanner scanner = new Scanner(System.in);
                String readString = scanner.nextLine();
                if(readString.isEmpty()){
                    treeSetOfArticles.remove(article);
                }
            }
            else{
                treeSetOfArticles.remove(article);
            }
        }
    }

    /**
     * prints out all articles contained in the category
     */
    public void showArticles(){
        Iterator<Article> iterator = treeSetOfArticles.iterator();
        while (iterator.hasNext()){
            Article article = iterator.next();

            System.out.println("Name: "+ article.getArticleName() + " Date: "
                    + article.getDate() + " ImportPrice: " + article.getImportPrice() +"Kr"
                    + " SalePrice: " + article.getSellingPrice() + "Kr" + " SuperSalePrice: " + article.getOnSalePrice()
                    + "Kr"+ " Barcode: " + article.barcode + " Amount of articles: " + article.getStock());
        }
    }

    /**
     *
     * @return get total sell value of category.
     */
    public float getTotalSellValueOfCategory(){
        float totalSellValueOfCategory = 0;
        Iterator<Article> iterator = treeSetOfArticles.iterator();
        while (iterator.hasNext()){
            Article article =  iterator.next();
            totalSellValueOfCategory += article.getTotalSellValueArticle();
        }
        return convertFloatTo2Decimals(totalSellValueOfCategory);
    }
    /**
     *
     * @return get total import value of category.
     */
    public float getTotalImportValueOfCategory(){
        float totalSellValueOfCategory = 0;
        Iterator<Article> iterator = treeSetOfArticles.iterator();
        while (iterator.hasNext()){
            Article article = iterator.next();
            totalSellValueOfCategory += article.getTotalImportValueArticle();
        }
        return convertFloatTo2Decimals(totalSellValueOfCategory);
    }
    /**
     *
     * @return get total difference value of category.
     */
    public float getTotalDifferenceValueOfCategory(){
        float totalSellValueOfCategory = 0;
        totalSellValueOfCategory += getTotalSellValueOfCategory() - getTotalImportValueOfCategory();
        return convertFloatTo2Decimals(totalSellValueOfCategory);
    }

    /**
     *
     * @return the tree set(category) with all articles
     */
    public TreeSet linkedListOfArticles(){
        return (TreeSet) treeSetOfArticles;
    }
    /**
     * recieves a float that converts into a 2 decimal so products do not sell with more than 2 decimal.
     * @param floatToChange
     * @return the converted float.
     */
    public float convertFloatTo2Decimals(float floatToChange){
        BigDecimal bd = new BigDecimal(Float.toString(floatToChange));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }


    public void printPieGraph(){
        PieGraph graphs = new PieGraph(getNameCategory() , linkedListOfArticles(), getTotalDifferenceValueOfCategory());
        graphs.pack();
        graphs.setSize( 1920/2 , 1080/2 );
        RefineryUtilities.centerFrameOnScreen(graphs);
        graphs.setVisible( true );

    }
    public void printBarGraph(){
        BarGraph graph = new BarGraph(getNameCategory() , linkedListOfArticles(), getTotalDifferenceValueOfCategory(),"Category");
        graph.pack();
        graph.setSize( 1920/2 , 1080/2 );
        RefineryUtilities.centerFrameOnScreen(graph);
        graph.setVisible( true );

    }

    /**
     * compare 2 categories to check which are the biggest Name wise.
     * this so the treeset can sort.
     * @param category
     * @return 1 if this category is bigger 0 = equal and -1 if other category is bigger.
     */
    @Override
    public int compareTo(Category category) {
        return this.nameCategory.compareTo(category.getNameCategory());
    }
}
