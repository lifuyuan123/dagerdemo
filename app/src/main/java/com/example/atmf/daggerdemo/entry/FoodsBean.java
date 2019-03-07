package com.example.atmf.daggerdemo.entry;

public class FoodsBean {

    private String name;
    private String html;
    private String imgUrl;
    private String material;

    public FoodsBean(String name,String imgUrl,String html,String material) {
        this.name = name;
        this.html = html;
        this.imgUrl = imgUrl;
        this.material=material;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    @Override
    public String toString() {
        return "FoodsBean{" +
                "name='" + name + '\'' +
                ", html='" + html + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", material='" + material + '\'' +
                '}';
    }
}
