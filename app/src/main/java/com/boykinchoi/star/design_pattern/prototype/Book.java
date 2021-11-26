package com.boykinchoi.star.design_pattern.prototype;

/**
 * @Author: 蔡佰健
 * @Description:
 * @Date:Create：in 2021/10/27 13:37
 */
public abstract class Book implements Cloneable {
    private String name;
    private String author;
    private String content;

    public Book(String name, String author, String content) {
        this.name = name;
        this.author = author;
        this.content = content;
        System.out.println("实例化book");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    protected Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
