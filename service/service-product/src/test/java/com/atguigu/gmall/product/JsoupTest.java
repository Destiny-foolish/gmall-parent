package com.atguigu.gmall.product;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author lfy
 * @Description
 * @create 2022-12-02 15:28
 */
public class JsoupTest {

    @Test
    public void testSearch() throws IOException {
        Document doc = Jsoup.connect("https://list.suning.com/0-20006-0-2-0-0-0-0-0-0-7459212.html?safp=d488778a.homepagev8.126605238631.3&safc=cate.0.0&safpn=10001#search-path").get();

        Elements elements = doc.getElementsByClass("product-box ");

        for (Element element : elements) {
            String href = element.getElementsByClass("img-block")
                    .get(0).child(0).attr("href");
            //TODO 图片
            String image = getImage(href);
            //TODO 价格，标题，重量，规格
            System.out.println(image);
        }
    }


    String getImage(String href) throws IOException {
        //1、连上某个地址获取页面内容
        String image = "";

            try {
                Document doc = Jsoup.connect("https:"+href).get();

                //document.getElementById("labelPicture").parentNode.children[2].children[0].src
                String attr = doc.getElementById("labelPicture")
                        .parent()
                        .getElementsByTag("a")
                        .get(0)
                        .child(0).attr("src");
                if(!StringUtils.isEmpty(attr)){
                    image = attr;
                }
            }catch (Exception e){

            }
            return image;

    }

    /**
     * 1、某个商品的具体图片
     * https://product.suning.com/0000000000/12389328852.html
     */
    @Test
    void testImg() throws IOException {
        //1、连上某个地址获取页面内容
        Long i = 10000000000L;
        while (true){
            try {
                Document doc = Jsoup.connect("https://product.suning.com/0000000000/"+ ++i +".html").get();

                //document.getElementById("labelPicture").parentNode.children[2].children[0].src
                String attr = doc.getElementById("labelPicture")
                        .parent()
                        .getElementsByTag("a")
                        .get(0)
                        .child(0).attr("src");
                if(!StringUtils.isEmpty(attr)){
                    System.out.println("商品id："+i+" 图片：" +attr);
                }
            }catch (Exception e){

            }

        }

    }
}
