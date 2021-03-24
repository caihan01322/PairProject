package cn.zcx.pa.PaperImport.pojo;

import java.util.Set;

public class Essay {

    private String title;//标题

    private String abst;//摘要

    private String conference;//会议

    private String publicationYear;//年份

    private String doiLink;//原文链接

    private Set<String> keywords;//关键词

    public Essay() {}

    public Essay(String title, String abst, String conference, String publicationYear,
                 String doiLink, Set<String> keywords) {
        this.title = title;
        this.abst = abst;
        this.conference = conference;
        this.publicationYear = publicationYear;
        this.doiLink = doiLink;
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAbst() {
        return abst;
    }

    public void setAbst(String abst) {
        this.abst = abst;
    }

    public String getConference() {
        return conference;
    }

    public void setConference(String conference) {
        this.conference = conference;
    }

    public String getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getDoiLink() {
        return doiLink;
    }

    public void setDoiLink(String doiLink) {
        this.doiLink = doiLink;
    }

    public Set<String> getKeywords() {
        return keywords;
    }

    public void setKeywords(Set<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "Essay{" +
                "title='" + title + '\'' +
                ", abst='" + abst + '\'' +
                ", conference='" + conference + '\'' +
                ", publicationYear='" + publicationYear + '\'' +
                ", doiLink='" + doiLink + '\'' +
                ", keywords=" + keywords +
                '}';
    }
}
