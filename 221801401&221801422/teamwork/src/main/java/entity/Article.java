package entity;

public class Article {
    public String title;
    public String articleNumber;
    public String doiLink;
    public String abs;
    public String kwds;
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getArticleNumber() {
        return articleNumber;
    }
    public void setArticleNumber(String articleNumber) {
        this.articleNumber = articleNumber;
    }
    public String getDoiLink() {
        return doiLink;
    }
    public void setDoiLink(String doiLink) {
        this.doiLink = doiLink;
    }
    public String getAbs() {
        return abs;
    }
    public void setAbs(String abs) {
        this.abs = abs;
    }
    public String getKwds() {
        return kwds;
    }
    public void setKwds(String kwds) {
        this.kwds = kwds;
    }  
}
