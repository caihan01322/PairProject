package com.practice.pair.pairproject.pojo;

import java.io.Serializable;

public class Keyword implements Serializable {
    private Integer kpid;

    private String content;

    private Integer pid;

    private String meeting;

    private String year;

    private static final long serialVersionUID = 1L;

    public Keyword(Integer kpid, String content, Integer pid, String meeting, String year) {
        this.kpid = kpid;
        this.content = content;
        this.pid = pid;
        this.meeting = meeting;
        this.year = year;
    }

    public Keyword() {
        super();
    }

    public Integer getKpid() {
        return kpid;
    }

    public void setKpid(Integer kpid) {
        this.kpid = kpid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", kpid=").append(kpid);
        sb.append(", content=").append(content);
        sb.append(", pid=").append(pid);
        sb.append(", meeting=").append(meeting);
        sb.append(", year=").append(year);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}