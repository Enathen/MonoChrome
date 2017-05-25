package ENSK;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Enathen on 2017-04-01.
 */
public class Statistics {
    //
    String nameOfStatistic;
    TreeSet<Category> treeSetOfCategories = new TreeSet<Category>();


    public Statistics(String nameOfStatistic){
        this.nameOfStatistic = nameOfStatistic;
    }

    public void setNameOfStatistic(String nameOfStatistic){
        this.nameOfStatistic = nameOfStatistic;
    }

    public String getNameOfStatistic(){
        return nameOfStatistic;
    }

    public void addCategory(Category category){
        treeSetOfCategories.add(category);
    }

    public float getTotalSellValueOfCategories(){
        float totalSellValueOfCategory = 0;
        Iterator<Category> itr = treeSetOfCategories.iterator();
        while (!itr.hasNext()){
            Category category =  itr.next();
            totalSellValueOfCategory += category.getTotalSellValueOfCategory();
        }
        return convertFloatTo2Decimals(totalSellValueOfCategory);
    }

    public float getTotalImportValueOfCategories(){
        float totalSellValueOfCategory = 0;
        Iterator<Category> iterator = treeSetOfCategories.iterator();
        while (!iterator.hasNext()){

            Category category =  iterator.next();
            totalSellValueOfCategory += category.getTotalImportValueOfCategory();
        }
        return convertFloatTo2Decimals(totalSellValueOfCategory);
    }

    public float getTotalDifferenceValueOfCategories(){
        float totalSellValueOfCategory = 0;
        totalSellValueOfCategory += getTotalSellValueOfCategories() - getTotalImportValueOfCategories();
        return convertFloatTo2Decimals(totalSellValueOfCategory);
    }
    public void CompareCategories(){

    }
    public void CompareTwoCategories(Category category1, Category category2){
        float TotalDifferenceOfCategory1 = category1.getTotalDifferenceValueOfCategory();
        float TotalDifferenceOfCategory2 = category2.getTotalDifferenceValueOfCategory();
        System.out.println( category1.getNameCategory() + " has a total Income of: "
                + category1.getTotalImportValueOfCategory()+ " and a sell value of: " + category1.getTotalSellValueOfCategory());

        System.out.println( category2.getNameCategory() + " has a total Income of: "
                + category2.getTotalImportValueOfCategory()+ " and a sell value of: " + category2.getTotalSellValueOfCategory());

        System.out.println( category1.getNameCategory() + " has a total difference of: " +TotalDifferenceOfCategory1
                +" :: "+ category2.getNameCategory() + " has a total difference of: " + TotalDifferenceOfCategory2);

        if(TotalDifferenceOfCategory1 > TotalDifferenceOfCategory2){
            float difference = getDifferenceOfTwoFloats(TotalDifferenceOfCategory1, TotalDifferenceOfCategory2);
            System.out.println(category1.getNameCategory() + " has a bigger difference with: " + difference
                    + " over " + category2.getNameCategory());
        }else {
            float difference = getDifferenceOfTwoFloats(TotalDifferenceOfCategory2, TotalDifferenceOfCategory1);
            System.out.println(category2.getNameCategory() + " has a bigger difference with: " + difference
                    + " over " + category1.getNameCategory());

        }

    }
    //float1 > float2
    public float getDifferenceOfTwoFloats(float float1, float float2){
            return float1-float2;
    }
    public float convertFloatTo2Decimals(float floatToChange){
        BigDecimal bd = new BigDecimal(Float.toString(floatToChange));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }
    public void printAllCategoriesInStatistics(){
        Iterator<Category> iterator = treeSetOfCategories.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().getNameCategory());
        }
    }

}
