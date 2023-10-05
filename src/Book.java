public class Book {

    private String title;
    private String authorName;
    private String authorSurname;
    private int yearWhenPublished;
    private double priceInDollars;

    public Book(String title, String authorName, String authorSurname, int yearWhenPublished, double priceInDollars){
        this.title = title;
        this.authorName = authorName;
        this.authorSurname = authorSurname;
        this.yearWhenPublished = yearWhenPublished;
        this.priceInDollars = priceInDollars;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getAuthorName(){
        return authorName;
    }
    public void setAuthorName(String authorName){
        this.authorName = authorName;
    }

    public String getAuthorSurname(){
        return authorSurname;
    }
    public void setAuthorSurname(String authorSurname){
        this.authorSurname = authorSurname;
    }

    public int getYearWhenPublished(){
        return yearWhenPublished;
    }
    public void setYearWhenPublished(int yearWhenPublished){
        this.yearWhenPublished = yearWhenPublished;
    }

    public double getPriceInDollars(){
        return priceInDollars;
    }
    public void setPriceInDollars(double price){
        this.priceInDollars = price;
    }

    public double getPriceInOtherCurrency(String currency){
        return switch (currency) {
            case "UAH" -> priceInDollars * 36.9;
            case "EUR" -> priceInDollars * 0.94;
            case "BTC" -> priceInDollars * 0.00003815;
            case "XMR" -> priceInDollars * 0.00692264;
            case "WOW" -> priceInDollars * 71.7;
            case "DOGE" -> priceInDollars * 16.4;
            default -> -1;
        };
    }

    public String getLongDesc(){
        return "A book called \"" + getTitle()
                + "\" was published by " + getAuthorName()
                + " " + getAuthorSurname() +
                " in the year of " + getYearWhenPublished()
                + "\nCurrently, the book costs " + getPriceInDollars() + " USD on Amazon,\n"
                + "or " + getPriceInOtherCurrency("UAH")
                + " if converting to "+ "UAH" +" (shipment price not included)\n";
    }

    public String getShortDesc(){
        return "\"" + getTitle() + "\" " + getAuthorName().charAt(0) + ". " + getAuthorSurname();
    }

}