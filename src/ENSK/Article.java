package ENSK;

import java.math.BigDecimal;

/**
 * Created by Enathen on 2017-03-28.
 * Creates an article and store all necessary information.
 */


public class Article implements Comparable<Article>{
    float importPrice, sellingPrice, onSalePrice;
    String articleName;
    int badDay, badMonth, badYear, amountOfArticles;
    int barcode;

    /**
     * @param name Function to call to set name and a new article.
     */
    public Article(String name){
        articleName = name;
    }

    /**
     *
     * @param price set import price and convert it to 2decimals.
     */
    public void setImportPrice(float price){
        importPrice = convertFloatTo2Decimals(price);
    }

    /**
     *
     * @return import price.
     */
    public float getImportPrice(){
        return importPrice;
    }

    /**
     *
     * @param price set selling price and convert it to 2decimals.
     */
    public void setSellingPrice(float price){
        sellingPrice = convertFloatTo2Decimals(price);
    }

    /**
     *
     * @return selling price.
     */
    public float getSellingPrice() {
        return sellingPrice;
    }

    /**
     *
     * @param price set on sale price and convert it to 2decimals.
     */
    public void setOnSalePrice(float price){
        onSalePrice = convertFloatTo2Decimals(price);
    }

    /**
     *
     * @return on sale price.
     */
    public float getOnSalePrice() {
        return onSalePrice;
    }

    /**
     *
     * @param barcode set scanned barcode.
     */
    public void setBarcode(int barcode){
        this.barcode = barcode;
    }

    /**
     *
     * @return barcode.
     */
    public int getBarcode(){
        return barcode;
    }

    /**
     *
     * @param name change the name of the article.
     */
    public void setArticleName(String name){
        articleName = name;
    }

    /**
     *
     * @return the article name.
     */
    public String getArticleName(){
        return articleName;
    }

    /**
     * Sets the expiring date of the product.
     * @param day
     * @param month
     * @param year
     * @return if year was successful.
     */
    public boolean setDate(int day, int month, int year){
        boolean success = true;
        if(day > 31 || day < 0){
            System.out.println("invalid day");
            success = false;
        }
        if(month > 12 || month < 0){
            System.out.println("invalid month");
            success = false;
        }
        if(year < 2017){
            System.out.println("invalid year");
            success = false;
        }
        if(year > 2100){
            System.out.println("something probably went wrong");
            success = false;
        }
        if(success){
            badDay = day;
            badMonth = month;
            badYear = year;
        }
        return success;
    }

    /**
     *
     * @return the expiring date of the article.
     */
    public String getDate(){
        String date = String.format("%02d",badDay);
        date = date.concat("-");
        date = date.concat(String.format("%02d",badMonth));
        date = date.concat("-");
        date = date.concat(String.format("%02d",badYear));
        return date;
    }

    /**
     * decrement amount of articles by one.
     */
    public void decrementStockByOne(){
        amountOfArticles--;
    }
    /**
     * increment amount of articles by one.
     */
    public void incrementStockByOne(){
        amountOfArticles++;
    }

    /**
     *
     * @param amount adds to the total amount of articles.
     */
    public void addStockAmount(int amount){
        amountOfArticles += amount;
    }

    /**
     *
     * @param amount remove to the total amount of articles.
     */
    public void removeStockAmount(int amount){
        amountOfArticles -= amount;
    }
    /**
     *
     * @return amount of article in the stock.
     */
    public int getStock(){
        return amountOfArticles;
    }

    /**
     *
     * @return total sell value of all stock.
     */
    public float getTotalSellValueArticle(){
        if(getOnSalePrice() > 0){
            return (amountOfArticles * getOnSalePrice());
        }
        return convertFloatTo2Decimals(amountOfArticles * getSellingPrice());

    }

    /**
     *
     * @return all total import value of all articles.
     */
    public float getTotalImportValueArticle(){
        return convertFloatTo2Decimals(amountOfArticles * getImportPrice());
    }

    /**
     *
     * @return the difference of sell value and total import value.
     */
    public float getTotalDifferenceValueArticle(){
        return convertFloatTo2Decimals(getTotalSellValueArticle() - getTotalImportValueArticle());
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

    /**
     * compare 2 articles to check which are the biggest Name wise.
     * this so the treeset can sort.
     * @param article
     * @return 1 if this article is bigger 0 = equal and -1 if other article is bigger.
     */
    @Override
    public int compareTo(Article article) {
        return this.articleName.compareTo(article.getArticleName());
    }
}
