package com.geiyepa.demo.bean;

public class paperWithBLOBs extends paper {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paper.title
     *
     * @mbg.generated
     */
    private String title;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paper.abstractContext
     *
     * @mbg.generated
     */
    private String abstractcontext;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column paper.keyword
     *
     * @mbg.generated
     */
    private String keyword;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column paper.title
     *
     * @return the value of paper.title
     *
     * @mbg.generated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column paper.title
     *
     * @param title the value for paper.title
     *
     * @mbg.generated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column paper.abstractContext
     *
     * @return the value of paper.abstractContext
     *
     * @mbg.generated
     */
    public String getAbstractcontext() {
        return abstractcontext;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column paper.abstractContext
     *
     * @param abstractcontext the value for paper.abstractContext
     *
     * @mbg.generated
     */
    public void setAbstractcontext(String abstractcontext) {
        this.abstractcontext = abstractcontext == null ? null : abstractcontext.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column paper.keyword
     *
     * @return the value of paper.keyword
     *
     * @mbg.generated
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column paper.keyword
     *
     * @param keyword the value for paper.keyword
     *
     * @mbg.generated
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    @Override
    public String toString() {
        return "paperWithBLOBs{" +
                "title='" + title + '\'' +
                ", abstractcontext='" + abstractcontext + '\'' +
                ", keyword='" + keyword + '\'' +
                '}';
    }
}