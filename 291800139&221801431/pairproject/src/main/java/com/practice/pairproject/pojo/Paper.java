package com.practice.pairproject.pojo;

import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Builder
public class Paper implements Serializable {
    private Integer pid;

    private String meeting;

    private String year;

    private String publicDate; //

    private String title; //

    private String link; //

    private String authors;

    private String abstractContent; //

    private static final long serialVersionUID = 1L;

    public Paper(Integer pid, String meeting, String year, String publicDate, String title, String link, String authors, String abstractContent) {
        this.pid = pid;
        this.meeting = meeting;
        this.year = year;
        this.publicDate = publicDate;
        this.title = title;
        this.link = link;
        this.authors = authors;
        this.abstractContent = abstractContent;
    }

    public Paper() {
        super();
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getMeeting() {
        return meeting;
    }

    public void setMeeting(String meeting) {
        this.meeting = meeting == null ? null : meeting.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate == null ? null : publicDate.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link == null ? null : link.trim();
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors == null ? null : authors.trim();
    }

    public String getAbstractContent() {
        return abstractContent;
    }

    public void setAbstractContent(String abstractContent) {
        this.abstractContent = abstractContent == null ? null : abstractContent.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", pid=").append(pid);
        sb.append(", meeting=").append(meeting);
        sb.append(", year=").append(year);
        sb.append(", publicDate=").append(publicDate);
        sb.append(", title=").append(title);
        sb.append(", link=").append(link);
        sb.append(", authors=").append(authors);
        sb.append(", abstractContent=").append(abstractContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}