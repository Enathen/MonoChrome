package ENSK;

import ENSK.Windows.Article.Article;
import ENSK.Windows.Login.LoginFrame;

import javax.naming.NamingException;
import java.sql.SQLException;


public class Main {
    /**
     * Just trying main function
     *
     * @param args
     */
    public static void main(String[] args) {

        try {
            LoginFrame loginFrame = new LoginFrame();
        } catch (SQLException | NamingException | ClassNotFoundException e) {
            e.printStackTrace();
        }


        /*Article milk = createArticle("Norrmejeri 3,0% fett", 12.74f, 15.75f, 5, 12, 2017, 0000001);
        milk.addStockAmount(105);
        Article bregott = createArticle("Bregott extra Salt", 12.99f, 17.95f, 7, 5, 2017, 0000002);
        bregott.addStockAmount(230);
        Article pucko = createArticle("Pucko", 12.74f, 15.75f, 5, 12, 2017, 0000003);
        pucko.addStockAmount(20);
        Article jäst = createArticle("Jäst", 3.99f, 10f, 8.4f, 7, 5, 2017, 0000004);
        jäst.addStockAmount(512);
        Article magneficoChili = createArticle("Magnefico chili", 15, 32f, 25f, 23, 4, 2017, 0000005);
        magneficoChili.addStockAmount(30);
        Category mejeri = new Category("Mejeri");
        Category deli = new Category("Deli");
        Category allArticlesInStore = new Category("All Articles");
        deli.addArticle(milk);
        deli.addArticle(magneficoChili);
        mejeri.addArticle(bregott);
        mejeri.addArticle(pucko);
        mejeri.addArticle(jäst);
        mejeri.showArticles();
        Statistics statistics = new Statistics("Statistics1");
        statistics.addCategory(mejeri);
        statistics.addCategory(deli);
        statistics.addCategory(deli);
        statistics.addCategory(deli);
        statistics.CompareTwoCategories(mejeri, deli);
        statistics.printAllCategoriesInStatistics();
        allArticlesInStore.addSeveralArticles(deli.treeSetOfArticles);
        allArticlesInStore.addSeveralArticles(mejeri.treeSetOfArticles);


        allArticlesInStore.printPieGraph();
        allArticlesInStore.printBarGraph();*/


    }

    private static Article createArticle(String name, float importPrice, float sellingPrice, float onSalePrice, int day, int month, int year, int barcode) {
        Article article = new Article(name);
        article.setImportPrice(importPrice);
        article.setSellingPrice(sellingPrice);
        article.setOnSalePrice(onSalePrice);
        article.setDate(day, month, year);
        article.setBarcode(barcode);
        return article;
    }


    public static Article createArticle(String name, float importPrice, float sellingPrice, int day, int month, int year, int barcode) {
        Article article = new Article(name);
        article.setImportPrice(importPrice);
        article.setSellingPrice(sellingPrice);
        article.setDate(day, month, year);
        article.setBarcode(barcode);
        return article;
    }
}
